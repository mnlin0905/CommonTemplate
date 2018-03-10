package com.acchain.community.rongcloud.plugin;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.bean.Friend;
import com.acchain.community.manager.FriendManager;
import com.acchain.community.util.Const;
import com.alibaba.android.arouter.launcher.ARouter;

import io.rong.imkit.RongExtension;
import io.rong.imkit.plugin.IPluginModule;

import static com.acchain.community.util.Const.KEY_IS_FROM_OTHER_ACTIVITY;

/**
 * Created by rsp on 2018/1/15.
 */

public class TransferPlugin implements IPluginModule {
    @Override
    public Drawable obtainDrawable(Context context) {
        return context.getResources().getDrawable(R.drawable.transfer);
    }

    @Override
    public String obtainTitle(Context context) {
        return context.getResources().getString(R.string.label_take_out_friend);
    }

    @Override
    public void onClick(Fragment fragment, RongExtension rongExtension) {
//        Toast.makeText(fragment.getActivity(), "转账", Toast.LENGTH_SHORT).show();
        String targetId = rongExtension.getTargetId();
        Friend friend = FriendManager.getInstance().findFriendById(targetId);
        ARouter.getInstance().build(ARouterConst.Activity_TakeOutCurrencyActivity)
                .withObject(Const.MODEL_CONTACT_FRIEND, friend)
                .withBoolean(KEY_IS_FROM_OTHER_ACTIVITY, true)
                .navigation();
        rongExtension.collapseExtension();
    }

    @Override
    public void onActivityResult(int i, int i1, Intent intent) {

    }
}
