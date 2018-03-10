package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.TakeInCurrencyRecordContract;
import com.acchain.community.activity.wallet.TakeInCurrencyRecordActivity;

import javax.inject.Inject;

/**
 * function---- TakeInCurrencyRecordPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/10 08:21:14 (+0000).
 */
public class TakeInCurrencyRecordPresenter extends BasePresenter<TakeInCurrencyRecordActivity> implements TakeInCurrencyRecordContract.Presenter{
    @Inject
    public TakeInCurrencyRecordPresenter() {}

}