package com.acchain.community.activity.person;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.acchain.community.R;
import com.acchain.community.adapter.RefundAftermarketAdapter;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.bean.RefundAftermarketBean;
import com.acchain.community.contract.RefundAftermarketContract;
import com.acchain.community.presenter.RefundAftermarketPresenter;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * function---- RefundAftermarketActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/02/03 06:27:46 (+0000).
 */
@Route(path = ARouterConst.Activity_RefundAftermarketActivity)
public class RefundAftermarketActivity extends BaseActivity<RefundAftermarketPresenter> implements RefundAftermarketContract.View, RefundAftermarketAdapter.OnRefundAftermarketListener, XRecyclerView.LoadingListener {
    /**
     * 数据源,适配器
     */
    @BindView(R.id.xrv_record)
    XRecyclerView mXrvRecord;
    private ArrayList<RefundAftermarketBean> aftermarketBeans;
    private RefundAftermarketAdapter aftermarketAdapter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_refund_aftermarket;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //XRecyclerView适配
        aftermarketBeans = new ArrayList<>();
        aftermarketAdapter = new RefundAftermarketAdapter(aftermarketBeans, this);
        mXrvRecord.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mXrvRecord.setAdapter(aftermarketAdapter);
        mXrvRecord.setLoadingMoreEnabled(true);
        mXrvRecord.setPullRefreshEnabled(true);
        mXrvRecord.setLoadingListener(this);

    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    /**
     * 当点击查看详情
     */
    @Override
    public void onClickCheckDetail(int position) {
        ARouter.getInstance()
                .build(ARouterConst.Activity_RefundAftermarketDetailActivity)
                // TODO: 2018/2/3
                .navigation();
    }

    /**
     * 点击取消退款
     */
    @Override
    public void onClickCancelRefund(int position) {

    }

    /**
     * 当点击item自身
     */
    @Override
    public void onClickSelf(int position) {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}