package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.AboutContract;
import com.acchain.community.activity.person.AboutActivity;

import javax.inject.Inject;

/**
 * function---- AboutPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/22 05:45:55 (+0000).
 */
public class AboutPresenter extends BasePresenter<AboutActivity> implements AboutContract.Presenter{
    @Inject
    public AboutPresenter() {}

}