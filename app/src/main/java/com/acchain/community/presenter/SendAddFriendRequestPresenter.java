package com.acchain.community.presenter;


import com.acchain.community.base.BaseHttpBean;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.SendAddFriendRequestContract;
import com.acchain.community.activity.friend.SendAddFriendRequestActivity;
import com.acchain.community.util.HttpListener;
import com.acchain.community.util.DefaultPreferenceUtil;

import javax.inject.Inject;

/**
 * function---- SendAddFriendRequestPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/23 09:09:28 (+0000).
 */
public class SendAddFriendRequestPresenter extends BasePresenter<SendAddFriendRequestActivity> implements SendAddFriendRequestContract.Presenter{
    @Inject
    public SendAddFriendRequestPresenter() {}

    @Override
    public void sendRequest() {
        String userId = mView.getUserId();
        String message = mView.getMessage();
        String remarks = mView.getRemarks();
        requestHttp(httpInterface.sendAddFriendRequest(DefaultPreferenceUtil.getInstance().getToken(), userId, message,remarks), new HttpListener<BaseHttpBean<Object>>() {
            @Override
            public void onSuccess(BaseHttpBean<Object> response) {
                mView.sendSuccess();
            }
        });
    }
}