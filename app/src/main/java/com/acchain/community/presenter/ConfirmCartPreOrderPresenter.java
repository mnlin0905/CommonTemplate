package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.ConfirmCartPreOrderContract;
import com.acchain.community.activity.index.ConfirmCartPreOrderActivity;

import javax.inject.Inject;

/**
 * function---- ConfirmCartPreOrderPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/02/01 07:50:51 (+0000).
 */
public class ConfirmCartPreOrderPresenter extends BasePresenter<ConfirmCartPreOrderActivity> implements ConfirmCartPreOrderContract.Presenter{
    @Inject
    public ConfirmCartPreOrderPresenter() {}

}