package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.HelpContract;
import com.acchain.community.activity.person.HelpActivity;

import javax.inject.Inject;

/**
 * function---- HelpPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/22 05:48:23 (+0000).
 */
public class HelpPresenter extends BasePresenter<HelpActivity> implements HelpContract.Presenter{
    @Inject
    public HelpPresenter() {}

}