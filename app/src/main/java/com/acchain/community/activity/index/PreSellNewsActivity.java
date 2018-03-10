package com.acchain.community.activity.index;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.acchain.community.R;
import com.acchain.community.adapter.PreSellNewsAdapter;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.PreSellNewsContract;
import com.acchain.community.presenter.PreSellNewsPresenter;
import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * function---- PreSellNewsActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/15 06:32:20 (+0000).
 */
@Route(path = ARouterConst.Activity_PreSellNewsActivity)
public class PreSellNewsActivity extends BaseActivity<PreSellNewsPresenter> implements PreSellNewsContract.View {

    @BindView(R.id.recycler)
    RecyclerView recycler;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_pre_sell_news;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recycler.setAdapter(new PreSellNewsAdapter(this));
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

}