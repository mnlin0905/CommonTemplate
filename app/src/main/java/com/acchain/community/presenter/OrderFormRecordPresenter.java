package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.OrderFormRecordContract;
import com.acchain.community.fragment.OrderFormRecordFragment;

import javax.inject.Inject;

/**
 * function---- OrderFormRecordPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/25 07:04:18 (+0000).
 */
public class OrderFormRecordPresenter extends BasePresenter<OrderFormRecordFragment> implements OrderFormRecordContract.Presenter{
    @Inject
    public OrderFormRecordPresenter() {}

}