package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.BanCommandContract;
import com.acchain.community.activity.person.BanCommandActivity;

import javax.inject.Inject;

/**
 * function---- BanCommandPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/27 02:22:23 (+0000).
 */
public class BanCommandPresenter extends BasePresenter<BanCommandActivity> implements BanCommandContract.Presenter{
    @Inject
    public BanCommandPresenter() {}

}