package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.WalletRecordContract;
import com.acchain.community.activity.wallet.WalletRecordActivity;

import javax.inject.Inject;

/**
 * function---- WalletRecordPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/03 09:49:58 (+0000).
 */
public class WalletRecordPresenter extends BasePresenter<WalletRecordActivity> implements WalletRecordContract.Presenter{
    @Inject
    public WalletRecordPresenter() {}

}