package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.TransferDetailContract;
import com.acchain.community.activity.friend.TransferDetailActivity;

import javax.inject.Inject;

/**
 * function---- TransferDetailPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/02/03 07:19:35 (+0000).
 */
public class TransferDetailPresenter extends BasePresenter<TransferDetailActivity> implements TransferDetailContract.Presenter{
    @Inject
    public TransferDetailPresenter() {}

}