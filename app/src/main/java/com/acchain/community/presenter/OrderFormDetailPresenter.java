package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.OrderFormDetailContract;
import com.acchain.community.activity.person.OrderFormDetailActivity;

import javax.inject.Inject;

/**
 * function---- OrderFormDetailPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/25 09:18:10 (+0000).
 */
public class OrderFormDetailPresenter extends BasePresenter<OrderFormDetailActivity> implements OrderFormDetailContract.Presenter{
    @Inject
    public OrderFormDetailPresenter() {}

}