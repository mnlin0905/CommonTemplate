package com.acchain.community.activity.person;

import android.os.Bundle;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.ChangeLocationContract;
import com.acchain.community.presenter.ChangeLocationPresenter;
import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * function---- ChangeLocationActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/15 12:40:55 (+0000).
 */
@Route(path = ARouterConst.Activity_ChangeLocationActivity)
public class ChangeLocationActivity extends BaseActivity<ChangeLocationPresenter> implements ChangeLocationContract.View{

    @Override
    protected int getContentViewId() {
        return R.layout.activity_change_location;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {}

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

}