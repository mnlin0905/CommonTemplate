package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.ServiceAgreementContract;
import com.acchain.community.activity.person.ServiceAgreementActivity;

import javax.inject.Inject;

/**
 * function---- ServiceAgreementPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/22 09:16:12 (+0000).
 */
public class ServiceAgreementPresenter extends BasePresenter<ServiceAgreementActivity> implements ServiceAgreementContract.Presenter{
    @Inject
    public ServiceAgreementPresenter() {}

}