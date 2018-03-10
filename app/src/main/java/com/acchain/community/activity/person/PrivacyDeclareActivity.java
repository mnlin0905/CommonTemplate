package com.acchain.community.activity.person;

import android.os.Bundle;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.PrivacyDeclareContract;
import com.acchain.community.presenter.PrivacyDeclarePresenter;
import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * function---- PrivacyDeclareActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/22 10:05:11 (+0000).
 */
@Route(path = ARouterConst.Activity_PrivacyDeclareActivity)
public class PrivacyDeclareActivity extends BaseActivity<PrivacyDeclarePresenter> implements PrivacyDeclareContract.View{

    @Override
    protected int getContentViewId() {
        return R.layout.activity_privacy_declare;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {}

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

}