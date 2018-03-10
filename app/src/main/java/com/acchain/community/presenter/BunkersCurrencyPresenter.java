package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.BunkersCurrencyContract;
import com.acchain.community.activity.wallet.BunkersCurrencyActivity;

import javax.inject.Inject;

/**
 * function---- BunkersCurrencyPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/11 01:53:21 (+0000).
 */
public class BunkersCurrencyPresenter extends BasePresenter<BunkersCurrencyActivity> implements BunkersCurrencyContract.Presenter{
    @Inject
    public BunkersCurrencyPresenter() {}

}