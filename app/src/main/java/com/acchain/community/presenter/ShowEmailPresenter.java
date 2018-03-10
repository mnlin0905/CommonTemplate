package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.ShowEmailContract;
import com.acchain.community.activity.person.ShowEmailActivity;

import javax.inject.Inject;

/**
 * function---- ShowEmailPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/20 07:25:31 (+0000).
 */
public class ShowEmailPresenter extends BasePresenter<ShowEmailActivity> implements ShowEmailContract.Presenter{
    @Inject
    public ShowEmailPresenter() {}

}