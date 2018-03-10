package com.acchain.community.activity.person;

import android.os.Bundle;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.ServiceAgreementContract;
import com.acchain.community.presenter.ServiceAgreementPresenter;
import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * function---- ServiceAgreementActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/22 09:16:13 (+0000).
 */
@Route(path = ARouterConst.Activity_ServiceAgreementActivity)
public class ServiceAgreementActivity extends BaseActivity<ServiceAgreementPresenter> implements ServiceAgreementContract.View{

    @Override
    protected int getContentViewId() {
        return R.layout.activity_service_agreement;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {}

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

}