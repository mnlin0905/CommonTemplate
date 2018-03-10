package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.ChangeNickNameContract;
import com.acchain.community.activity.person.ChangeNickNameActivity;

import javax.inject.Inject;

/**
 * function---- ChangeNickNamePresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/15 12:38:25 (+0000).
 */
public class ChangeNickNamePresenter extends BasePresenter<ChangeNickNameActivity> implements ChangeNickNameContract.Presenter{
    @Inject
    public ChangeNickNamePresenter() {}

    @Override
    public void setNickname(String token, String nickname) {
        requestHttp(httpInterface.setNickname(token,nickname),
                objectBaseHttpBean -> mView.setNicknameFinish());
    }
}