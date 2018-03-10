package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.ForgetPasswordContract;
import com.acchain.community.activity.person.ForgetPasswordActivity;

import javax.inject.Inject;

/**
 * function---- ForgetPasswordPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/19 01:55:53 (+0000).
 */
public class ForgetPasswordPresenter extends BasePresenter<ForgetPasswordActivity> implements ForgetPasswordContract.Presenter{
    @Inject
    public ForgetPasswordPresenter() {}

}