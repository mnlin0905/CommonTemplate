package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.SelectRefundServiceContract;
import com.acchain.community.activity.person.SelectRefundServiceActivity;

import javax.inject.Inject;

/**
 * function---- SelectRefundServicePresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/26 02:52:26 (+0000).
 */
public class SelectRefundServicePresenter extends BasePresenter<SelectRefundServiceActivity> implements SelectRefundServiceContract.Presenter{
    @Inject
    public SelectRefundServicePresenter() {}

}