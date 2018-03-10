package com.acchain.community.presenter;


import com.acchain.community.base.BaseHttpBean;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.ChatContract;
import com.acchain.community.activity.friend.ChatActivity;
import com.acchain.community.util.HttpListener;
import com.acchain.community.util.DefaultPreferenceUtil;

import javax.inject.Inject;

/**
 * function---- ChatPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/05 10:22:20 (+0000).
 */
public class ChatPresenter extends BasePresenter<ChatActivity> implements ChatContract.Presenter{
    @Inject
    public ChatPresenter() {}

    @Override
    public void receiveRedPacket() {
        String redPacketId = mView.getRedPacketId();
        requestHttp(httpInterface.openRedPacket(DefaultPreferenceUtil.getInstance().getToken(), redPacketId), new HttpListener<BaseHttpBean<Object>>() {
            @Override
            public void onSuccess(BaseHttpBean<Object> response) {
                mView.receiveSuccess();
            }
        });
    }
}