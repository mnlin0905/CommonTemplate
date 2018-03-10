package com.acchain.community.rongcloud.plugin;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.alibaba.android.arouter.launcher.ARouter;

import io.rong.imkit.RongExtension;
import io.rong.imkit.plugin.IPluginModule;

import static com.acchain.community.util.Const.KEY_TARGET_FRIEND_ID;

/**
 * Created by rsp on 2018/1/15.
 */

public class RedPacketPlugin implements IPluginModule {
    @Override
    public Drawable obtainDrawable(Context context) {
        return context.getResources().getDrawable(R.drawable.red_packet);
    }

    @Override
    public String obtainTitle(Context context) {
        return context.getString(R.string.red_packet);
    }

    @Override
    public void onClick(Fragment fragment, RongExtension rongExtension) {
        ARouter.getInstance()
                .build(ARouterConst.Activity_SendRedPacketActivity)
                .withString(KEY_TARGET_FRIEND_ID,rongExtension.getTargetId())
                .navigation();
        rongExtension.collapseExtension();
        /*RedPacketMessage redPacketMessage = new RedPacketMessage();
        Message obtain = Message.obtain(rongExtension.getTargetId(), rongExtension.getConversationType(), redPacketMessage);
        RongIM.getInstance().sendMessage(obtain, "您收到一个红包", "666", new IRongCallback.ISendMessageCallback() {
            @Override
            public void onAttached(Message message) {

            }

            @Override
            public void onSuccess(Message message) {

            }

            @Override
            public void onError(Message message, RongIMClient.ErrorCode errorCode) {

            }
        });*/
    }

    @Override
    public void onActivityResult(int i, int i1, Intent intent) {

    }
}
