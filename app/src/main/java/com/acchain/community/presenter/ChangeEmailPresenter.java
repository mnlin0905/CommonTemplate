package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.ChangeEmailContract;
import com.acchain.community.activity.person.ChangeEmailActivity;

import javax.inject.Inject;

/**
 * function---- ChangeEmailPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/20 07:25:21 (+0000).
 */
public class ChangeEmailPresenter extends BasePresenter<ChangeEmailActivity> implements ChangeEmailContract.Presenter{
    @Inject
    public ChangeEmailPresenter() {}

}