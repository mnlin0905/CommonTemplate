package com.acchain.community.activity.person;

import android.os.Bundle;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.RefundAftermarketDetailContract;
import com.acchain.community.presenter.RefundAftermarketDetailPresenter;
import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * function---- RefundAftermarketDetailActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/02/03 07:31:03 (+0000).
 */
@Route(path = ARouterConst.Activity_RefundAftermarketDetailActivity)
public class RefundAftermarketDetailActivity extends BaseActivity<RefundAftermarketDetailPresenter> implements RefundAftermarketDetailContract.View{

    @Override
    protected int getContentViewId() {
        return R.layout.activity_refund_aftermarket_detail;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {}

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

}