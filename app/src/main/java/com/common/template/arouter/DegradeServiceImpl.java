package com.common.template.arouter;

import android.content.Context;

import com.common.template.base.BaseEvent;
import com.common.template.rxbus.RxBus;
import com.common.template.util.Const;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.DegradeService;
import com.umeng.analytics.MobclickAgent;

/**
 * function : 自定义ARouter跳转全局降级，当跳转失败的话，可以进行处理，保证程序健壮性
 * <p>
 * 必须实现DegradeService接口，并加上一个Path内容任意的注解
 *
 * @author MNLIN
 * @date 2017/11/30
 */

@Route(path = "/degrade/DegradeServiceImpl")
public class DegradeServiceImpl implements DegradeService {
    @Override
    public void onLost(Context context, Postcard postcard) {
        MobclickAgent.reportError(context, "无法跳转:\npath:" + postcard.getPath() + "\ndestination" + postcard.getDestination());
        RxBus.getInstance().post(new BaseEvent(Const.SHOW_TOAST, "抱歉！界面跳转失败"));
    }

    @Override
    public void init(Context context) {

    }
}