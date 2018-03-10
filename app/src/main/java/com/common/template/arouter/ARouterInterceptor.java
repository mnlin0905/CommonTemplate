package com.common.template.arouter;

import android.content.Context;
import android.content.Intent;

import com.common.template.base.BaseEvent;
import com.common.template.rxbus.RxBus;
import com.common.template.util.Const;
import com.common.template.util.DefaultPreferenceUtil;
import com.alibaba.android.arouter.core.LogisticsCenter;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;
import com.orhanobut.logger.Logger;

/**
 * function : 跳转拦截器(权限拦截)
 * <p>
 * 比较经典的应用就是在跳转过程中处理登陆事件，这样就不需要在目标页重复做登陆检查
 * 拦截器会在跳转之间执行，多个拦截器会按优先级顺序依次执行
 *
 * @author MNLIN
 */

@Interceptor(priority = 2, name = "ARouter跳转拦截器")
public class ARouterInterceptor implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        Logger.v("######界面跳转 ： " + postcard.toString());

        //当前所有的权限
        String[] permissions = new String[]{
                "登录",
                "绿色通道",//若目标此flag设定,则表示禁止跳转
                "栈单例模式"//若目标设置此flag,则添加singTask标志
        };
        int[] FLAGS_ALL = new int[]{
                ARouterConst.FLAG_LOGIN,
                ARouterConst.FLAG_FORCE_ACCESS,
                ARouterConst.FLAG_ACTIVITY_CLEAR_TOP
        };

        //当前所有权限对应的boolean值;为false则对应权限设为 ARouterConst.FLAG_NONE
        boolean[] FLAGS_ALL_VALUE = new boolean[]{
                DefaultPreferenceUtil.getInstance().hasLogin(),
                false,
                false
        };

        //当前所有的权限
        int currentFlags = Integer.MIN_VALUE;
        for (int position = 0; position < FLAGS_ALL.length; position++) {
            currentFlags |= FLAGS_ALL_VALUE[position] ? FLAGS_ALL[position] : ARouterConst.FLAG_NONE;
        }
        Logger.v("######当前所有权限 : " + Integer.toBinaryString(currentFlags));

        //目标界面需要的权限
        int requireFlags = postcard.getExtra() | Integer.MIN_VALUE;
        Logger.v("######目标所需权限 : " + Integer.toBinaryString(requireFlags));

        //如果需要的权限都已存在,则直接跳转,不做处理
        if ((requireFlags & currentFlags) == requireFlags) {
            callback.onContinue(postcard);
            return;
        }

        //如果发现不一致,说明某些权限不存在,则需要依次判断哪个权限不存在
        for (int position = 0; position < FLAGS_ALL.length; position++) {
            if ((requireFlags & FLAGS_ALL[position]) != 0 && (currentFlags & FLAGS_ALL[position]) == 0) {
                // TODO: 2018/1/20 没有对应的f权限
                boolean consume = false;
                switch (position) {
                    case 0: //未登录
                        consume = dispatchLogin(postcard, callback);
                        break;
                    case 1:
                        break;
                    case 2: //栈单例模式
                        consume = dispatchSingleTask(postcard, callback);
                        break;
                    default: {
                        callback.onInterrupt(new RuntimeException("没有 " + permissions[position] + " 权限"));
                    }
                }

                if (!consume) {
                    callback.onInterrupt(new RuntimeException("界面无法跳转"));
                }

                return;
            }
        }

        //权限定义错误
        RxBus.getInstance().post(new BaseEvent(Const.SHOW_TOAST, "未知权限"));
    }

    /**
     * 请求单例启动
     *
     * 清除栈上其他活动
     */
    private boolean dispatchSingleTask(Postcard postcard, InterceptorCallback callback) {
        postcard.withFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        callback.onContinue(postcard);
        return true;
    }

    /**
     * 处理未登录操作
     */
    private boolean dispatchLogin(Postcard postcard, InterceptorCallback callback) {
        RxBus.getInstance().post(new BaseEvent(Const.SHOW_LOGIN_DIALOG, null));
        return false;
    }


    @Override
    public void init(Context context) {
        // 拦截器的初始化，会在sdk初始化的时候调用该方法，仅会调用一次
    }

    /**
     * 更换意图的跳转路径
     * 然后进行跳转处理
     *
     * @param postcard 意图
     * @param des      目的 string
     */
    private void replaceDes(Postcard postcard, String des) {
        //动态的修改postcard信息,更换跳转路径
        Postcard newPostcard = ARouter.getInstance().build(des);
        LogisticsCenter.completion(newPostcard);
        postcard.setPath(newPostcard.getPath()).setGroup(newPostcard.getGroup()).setDestination(newPostcard.getDestination());
    }
}