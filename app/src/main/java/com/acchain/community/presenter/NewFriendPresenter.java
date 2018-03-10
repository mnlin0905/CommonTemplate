package com.acchain.community.presenter;


import com.acchain.community.activity.friend.NewFriendActivity;
import com.acchain.community.base.BaseHttpBean;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.NewFriendContract;
import com.acchain.community.util.HttpListener;
import com.acchain.community.util.DefaultPreferenceUtil;

import javax.inject.Inject;

/**
 * function---- NewFriendPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/16 08:42:39 (+0000).
 */
public class NewFriendPresenter extends BasePresenter<NewFriendActivity> implements NewFriendContract.Presenter{
    @Inject
    public NewFriendPresenter() {}
    @Override
    public void accept() {
        requestHttp(httpInterface.friendAction(DefaultPreferenceUtil.getInstance().getToken(), mView.getUserId(), "1"), new HttpListener<BaseHttpBean<Object>>() {
            @Override
            public void onSuccess(BaseHttpBean<Object> response) {
                mView.acceptSuccess();
            }
        });
    }
}