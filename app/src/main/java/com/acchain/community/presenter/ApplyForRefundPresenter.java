package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.ApplyForRefundContract;
import com.acchain.community.activity.person.ApplyForRefundActivity;

import javax.inject.Inject;

/**
 * function---- ApplyForRefundPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/26 02:53:32 (+0000).
 */
public class ApplyForRefundPresenter extends BasePresenter<ApplyForRefundActivity> implements ApplyForRefundContract.Presenter{
    @Inject
    public ApplyForRefundPresenter() {}

}