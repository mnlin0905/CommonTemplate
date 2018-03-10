package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.ConfirmPreSellOrderContract;
import com.acchain.community.activity.index.ConfirmPreSellOrderActivity;

import javax.inject.Inject;

/**
 * function---- ConfirmPreSellOrderPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/15 07:19:06 (+0000).
 */
public class ConfirmPreSellOrderPresenter extends BasePresenter<ConfirmPreSellOrderActivity> implements ConfirmPreSellOrderContract.Presenter{
    @Inject
    public ConfirmPreSellOrderPresenter() {}

}