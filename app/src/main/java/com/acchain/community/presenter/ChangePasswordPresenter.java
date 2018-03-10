package com.acchain.community.presenter;


import com.acchain.community.activity.person.ChangePasswordActivity;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.ChangePasswordContract;

import javax.inject.Inject;

/**
 * function---- ChangePasswordPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/19 11:57:04 (+0000).
 */
public class ChangePasswordPresenter extends BasePresenter<ChangePasswordActivity> implements ChangePasswordContract.Presenter {
    @Inject
    public ChangePasswordPresenter() {
    }

    @Override
    public void updatePwd(String token, String oldPwd, String newPwd) {
        requestHttp(httpInterface.updatePwd(token, oldPwd, newPwd),
                objectBaseHttpBean -> mView.updatePwdFinish());
    }
}