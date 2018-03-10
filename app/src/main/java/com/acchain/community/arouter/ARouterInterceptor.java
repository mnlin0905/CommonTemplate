package com.acchain.community.arouter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.acchain.community.base.BaseEvent;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.rxbus.RxBus;
import com.acchain.community.util.Const;
import com.acchain.community.util.DefaultPreferenceUtil;
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
 * @author ACChain
 */

@Interceptor(priority = 2, name = "ARouter跳转拦截器")
public class ARouterInterceptor implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        Logger.v("######界面跳转 ： " + postcard.toString());

        //当前所有的权限
        String[] permissions = new String[]{
                "登录",
                "已开始了实名认证",
                "绑定手机",
                "绑定邮箱",
                "设置交易密码",
                "绿色通道",//若目标此flag设定,则表示禁止跳转
                "wifi",
                "手机网络",
                "栈单例模式",//若目标设置此flag,则添加singTask标志
                "名片设置",
                "实名认证未成功",
        };
        int[] FLAGS_ALL = new int[]{
                ARouterConst.FLAG_LOGIN,
                ARouterConst.FLAG_VERIFY_HAS_BEGIN,
                ARouterConst.FLAG_PHONE,
                ARouterConst.FLAG_EMAIL,
                ARouterConst.FLAG_TRANSACTION_PASSWORD,
                ARouterConst.FLAG_FORCE_ACCESS,
                ARouterConst.FLAG_WIFI_NET,
                ARouterConst.FLAG_MOBILE_NET,
                ARouterConst.FLAG_ACTIVITY_CLEAR_TOP,
                ARouterConst.FLAG_BUSINESS_CARD,
                ARouterConst.FLAG_VERIFY_NOT_SUCCESS,
        };

        //当前所有权限对应的boolean值;为false则对应权限设为 ARouterConst.FLAG_NONE
        boolean[] FLAGS_ALL_VALUE = new boolean[]{
                DefaultPreferenceUtil.getInstance().hasLogin(),
                BasePresenter.singleAccountBean.getTbMemberVerifiedInfo() != null,
                !TextUtils.isEmpty(BasePresenter.singleAccountBean.getMobile()),
                !TextUtils.isEmpty(BasePresenter.singleAccountBean.getEmail()),
                BasePresenter.singleAccountBean.isPayPwd(),
                false,
                false,
                false,
                false,
                BasePresenter.singleAccountBean.getBusinessCard() != null,
                BasePresenter.singleAccountBean.isVerifiedSuccess(),
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
                    case 1: //未开始实名认证
                        consume = dispatchVerityHasBegin(postcard, callback);
                        break;
                    case 2: //未绑定手机
                        consume = dispatchMobile(postcard, callback);
                        break;
                    case 3://未绑定邮箱
                        consume = dispatchEmail(postcard, callback);
                        break;
                    case 4: //未设置交易密码
                        consume = dispatchTransactionPassword(postcard, callback);
                        break;
                    case 8: //请求单例启动
                        consume = dispatchSingleTask(postcard, callback);
                        break;
                    case 9: //未设置名片信息
                        consume = dispatchBusinessCard(postcard, callback);
                        break;
                    case 10: //实名认证未成功(包括未开始和失败或者是审核中)
                        consume = dispatchVerityNotSuccess(postcard, callback);
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
     * 未绑定邮箱
     */
    private boolean dispatchEmail(Postcard postcard, InterceptorCallback callback) {
        replaceDes(postcard, ARouterConst.Activity_BindEmailActivity);
        callback.onContinue(postcard);
        return true;
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
     * 实名认证未成功
     */
    private boolean dispatchVerityNotSuccess(Postcard postcard, InterceptorCallback callback) {
        // TODO: 2018/2/1  
        return false;
    }

    /**
     * 未开始实名认证
     */
    private boolean dispatchVerityHasBegin(Postcard postcard, InterceptorCallback callback) {
        replaceDes(postcard, ARouterConst.Activity_BeginCertificationActivity);
        callback.onContinue(postcard);
        return true;
    }

    /**
     * 名片未设置
     */
    private boolean dispatchBusinessCard(Postcard postcard, InterceptorCallback callback) {
        //如果目标为二维码界面,则进行提示
        if (postcard.getPath().equalsIgnoreCase(ARouterConst.Activity_QRAndCommandActivity)) {
            RxBus.getInstance().post(new BaseEvent(Const.SHOW_TOAST, "请先设置名片"));
            return false;
        }

        replaceDes(postcard, ARouterConst.Activity_EditProfessionCardActivity);
        callback.onContinue(postcard);
        return true;
    }

    /**
     * 未绑定手机
     * <p>
     * 默认toast
     * <p>
     * 在未绑定手机时,必须先去实名认证,然后才能显示手机号绑定结果
     *
     * @return 返回true表示逻辑由方法自身来处理, false则会采取默认操作:抛出异常
     */
    private boolean dispatchMobile(Postcard postcard, InterceptorCallback callback) {
        //RxBus.getInstance().post(new BaseEvent(Const.SHOW_TOAST, "未绑定手机,无法设置交易密码"));
        replaceDes(postcard, ARouterConst.Activity_BeginCertificationActivity);
        callback.onContinue(postcard);
        return true;
    }

    /**
     * 未设置交易密码
     * 则跳转到设置交易密码界面
     * <p>
     * 默认提示toast
     */
    private boolean dispatchTransactionPassword(Postcard postcard, InterceptorCallback callback) {
        replaceDes(postcard, ARouterConst.Activity_SetTransactionPasswordActivity);
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