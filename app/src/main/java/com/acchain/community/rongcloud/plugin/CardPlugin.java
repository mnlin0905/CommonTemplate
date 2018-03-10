package com.acchain.community.rongcloud.plugin;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;

import com.acchain.community.R;
import com.acchain.community.activity.wallet.TakeOutSelectFriendActivity;
import com.acchain.community.arouter.ARouterConst;
import com.alibaba.android.arouter.launcher.ARouter;

import io.rong.imkit.RongExtension;
import io.rong.imkit.plugin.IPluginModule;
import io.rong.imlib.model.Conversation;

import static com.acchain.community.util.Const.KEY_TARGET_FRIEND_ID;

/**
 * Created by rsp on 2018/1/15.
 */

public class CardPlugin implements IPluginModule {
    private String targetId;
    private Conversation.ConversationType conversationType;

    @Override
    public Drawable obtainDrawable(Context context) {
        return context.getResources().getDrawable(R.drawable.card);
    }

    @Override
    public String obtainTitle(Context context) {
        return context.getString(R.string.card);
    }

    @Override
    public void onClick(Fragment fragment, RongExtension rongExtension) {
        targetId = rongExtension.getTargetId();
        conversationType = rongExtension.getConversationType();
        Intent intent = new Intent(fragment.getActivity(), TakeOutSelectFriendActivity.class);
        rongExtension.startActivityForPluginResult(intent, 1, this);
        ARouter.getInstance()
                .build(ARouterConst.Activity_TakeOutSelectFriendActivity)
                .withString(KEY_TARGET_FRIEND_ID,targetId)
                .navigation();
        rongExtension.collapseExtension();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

    }
}
