package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.ConfirmCartAdoptOrderContract;
import com.acchain.community.activity.index.ConfirmCartAdoptOrderActivity;

import javax.inject.Inject;

/**
 * function---- ConfirmCartAdoptOrderPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/03/01 09:36:13 (+0000).
 */
public class ConfirmCartAdoptOrderPresenter extends BasePresenter<ConfirmCartAdoptOrderActivity> implements ConfirmCartAdoptOrderContract.Presenter{
    @Inject
    public ConfirmCartAdoptOrderPresenter() {}

}