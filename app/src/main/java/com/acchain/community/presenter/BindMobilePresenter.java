package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.BindMobileContract;
import com.acchain.community.activity.person.BindMobileActivity;

import javax.inject.Inject;

/**
 * function---- BindMobilePresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/20 07:23:58 (+0000).
 */
public class BindMobilePresenter extends BasePresenter<BindMobileActivity> implements BindMobileContract.Presenter{
    @Inject
    public BindMobilePresenter() {}

}