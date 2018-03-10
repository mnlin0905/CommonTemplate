package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.TakeInRecordDetailContract;
import com.acchain.community.activity.wallet.TakeInRecordDetailActivity;

import javax.inject.Inject;

/**
 * function---- TakeInRecordDetailPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/10 09:37:33 (+0000).
 */
public class TakeInRecordDetailPresenter extends BasePresenter<TakeInRecordDetailActivity> implements TakeInRecordDetailContract.Presenter{
    @Inject
    public TakeInRecordDetailPresenter() {}

}