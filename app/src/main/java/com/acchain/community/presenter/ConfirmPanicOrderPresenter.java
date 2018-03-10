package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.ConfirmPanicOrderContract;
import com.acchain.community.activity.index.ConfirmPanicOrderActivity;

import javax.inject.Inject;

/**
 * function---- ConfirmPanicOrderPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/10 09:47:57 (+0000).
 */
public class ConfirmPanicOrderPresenter extends BasePresenter<ConfirmPanicOrderActivity> implements ConfirmPanicOrderContract.Presenter{
    @Inject
    public ConfirmPanicOrderPresenter() {}

}