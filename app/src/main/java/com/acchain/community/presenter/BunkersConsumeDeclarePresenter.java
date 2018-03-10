package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.BunkersConsumeDeclareContract;
import com.acchain.community.activity.wallet.BunkersConsumeDeclareActivity;

import javax.inject.Inject;

/**
 * function---- BunkersConsumeDeclarePresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/11 13:37:06 (+0000).
 */
public class BunkersConsumeDeclarePresenter extends BasePresenter<BunkersConsumeDeclareActivity> implements BunkersConsumeDeclareContract.Presenter{
    @Inject
    public BunkersConsumeDeclarePresenter() {}

}