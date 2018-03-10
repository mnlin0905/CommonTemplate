package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.BindEmailContract;
import com.acchain.community.activity.person.BindEmailActivity;

import javax.inject.Inject;

/**
 * function---- BindEmailPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/20 07:25:43 (+0000).
 */
public class BindEmailPresenter extends BasePresenter<BindEmailActivity> implements BindEmailContract.Presenter{
    @Inject
    public BindEmailPresenter() {}

}