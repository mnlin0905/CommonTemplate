package com.acchain.community.activity.person;

import android.os.Bundle;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.OrderFormDetailContract;
import com.acchain.community.presenter.OrderFormDetailPresenter;
import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * function---- OrderFormDetailActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/25 09:18:10 (+0000).
 */
@Route(path = ARouterConst.Activity_OrderFormDetailActivity)
public class OrderFormDetailActivity extends BaseActivity<OrderFormDetailPresenter> implements OrderFormDetailContract.View{

    @Override
    protected int getContentViewId() {
        return R.layout.activity_order_form_detail;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {}

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

}