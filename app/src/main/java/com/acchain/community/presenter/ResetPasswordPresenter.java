package com.acchain.community.presenter;


import com.acchain.community.activity.person.ResetPasswordActivity;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.ResetPasswordContract;

import javax.inject.Inject;

/**
 * function---- ResetPasswordPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/19 03:55:14 (+0000).
 */
public class ResetPasswordPresenter extends BasePresenter<ResetPasswordActivity> implements ResetPasswordContract.Presenter {
    @Inject
    public ResetPasswordPresenter() {
    }

    @Override
    public void resetPwd(String username, String pwd, String code, String confPwd) {
        requestHttp(httpInterface.resetPwd(username, pwd, code, confPwd),
                objectBaseHttpBean -> mView.resetPwdFinish());
    }
}