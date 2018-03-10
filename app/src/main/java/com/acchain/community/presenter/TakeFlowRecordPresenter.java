package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.TakeFlowRecordContract;
import com.acchain.community.activity.wallet.TakeFlowRecordActivity;

import javax.inject.Inject;

/**
 * function---- TakeFlowRecordPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/13 09:59:55 (+0000).
 */
public class TakeFlowRecordPresenter extends BasePresenter<TakeFlowRecordActivity> implements TakeFlowRecordContract.Presenter{
    @Inject
    public TakeFlowRecordPresenter() {}

}