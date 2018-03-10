package com.acchain.community.presenter;


import com.acchain.community.activity.person.RegisterActivity;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.RegisterContract;

import javax.inject.Inject;

/**
 * function---- RegisterPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/09 07:44:40 (+0000).
 */
public class RegisterPresenter extends BasePresenter<RegisterActivity> implements RegisterContract.Presenter {
    @Inject
    public RegisterPresenter() {
    }

    @Override
    public void newUser(String username, String pwd, String code) {
        requestHttp(httpInterface.newUser(username, pwd, code),
                objectBaseHttpBean -> mView.newUserFinish());
    }
}