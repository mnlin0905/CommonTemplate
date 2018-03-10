package com.acchain.community.activity.person;

import android.os.Bundle;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.CopyrightInformationContract;
import com.acchain.community.presenter.CopyrightInformationPresenter;
import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * function---- CopyrightInformationActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/22 09:16:38 (+0000).
 */
@Route(path = ARouterConst.Activity_CopyrightInformationActivity)
public class CopyrightInformationActivity extends BaseActivity<CopyrightInformationPresenter> implements CopyrightInformationContract.View{

    @Override
    protected int getContentViewId() {
        return R.layout.activity_copyright_information;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {}

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

}