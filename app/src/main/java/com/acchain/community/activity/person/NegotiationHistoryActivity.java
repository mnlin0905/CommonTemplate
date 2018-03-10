package com.acchain.community.activity.person;

import android.os.Bundle;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.NegotiationHistoryContract;
import com.acchain.community.presenter.NegotiationHistoryPresenter;
import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * function---- NegotiationHistoryActivity
 * 协商历史
 * <p>
 * Created(Gradle default create) by ACChain on 2018/02/03 08:58:16 (+0000).
 */
@Route(path = ARouterConst.Activity_NegotiationHistoryActivity)
public class NegotiationHistoryActivity extends BaseActivity<NegotiationHistoryPresenter> implements NegotiationHistoryContract.View{

    @Override
    protected int getContentViewId() {
        return R.layout.activity_negotiation_history;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {}

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

}