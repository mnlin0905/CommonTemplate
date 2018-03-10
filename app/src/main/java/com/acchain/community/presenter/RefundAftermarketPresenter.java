package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.RefundAftermarketContract;
import com.acchain.community.activity.person.RefundAftermarketActivity;

import javax.inject.Inject;

/**
 * function---- RefundAftermarketPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/02/03 06:27:47 (+0000).
 */
public class RefundAftermarketPresenter extends BasePresenter<RefundAftermarketActivity> implements RefundAftermarketContract.Presenter{
    @Inject
    public RefundAftermarketPresenter() {}

}