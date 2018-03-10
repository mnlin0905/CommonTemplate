package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.TakeOutRecordDetailContract;
import com.acchain.community.activity.wallet.TakeOutRecordDetailActivity;

import javax.inject.Inject;

/**
 * function---- TakeOutRecordDetailPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/12 08:01:44 (+0000).
 */
public class TakeOutRecordDetailPresenter extends BasePresenter<TakeOutRecordDetailActivity> implements TakeOutRecordDetailContract.Presenter{
    @Inject
    public TakeOutRecordDetailPresenter() {}

}