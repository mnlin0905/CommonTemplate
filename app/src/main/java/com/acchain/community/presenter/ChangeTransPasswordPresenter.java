package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.ChangeTransPasswordContract;
import com.acchain.community.activity.person.ChangeTransPasswordActivity;

import javax.inject.Inject;

/**
 * function---- ChangeTransPasswordPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/31 07:28:27 (+0000).
 */
public class ChangeTransPasswordPresenter extends BasePresenter<ChangeTransPasswordActivity> implements ChangeTransPasswordContract.Presenter{
    @Inject
    public ChangeTransPasswordPresenter() {}

    @Override
    public void changePayPwdForArtificial(String token, String payPwd) {
        requestHttp(httpInterface.changePayPwdForArtificial(token,payPwd),objectBaseHttpBean -> mView.changePayPwdForArtificialFinish());
    }
}