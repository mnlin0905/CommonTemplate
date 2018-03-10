package com.acchain.community.activity.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.FrameLayout;

import com.acchain.community.R;
import com.acchain.community.activity.other.SearchFilterActivity;
import com.acchain.community.adapter.TakeInCurrencyAdapter;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.bean.CurrencyBean;
import com.acchain.community.bean.TakeInCurrencyAgent;
import com.acchain.community.bean.TakeInCurrencyBean;
import com.acchain.community.contract.TakeInCurrencyContract;
import com.acchain.community.drawable.RoundRectShapeDrawable;
import com.acchain.community.interfaces.OnFilterResultListener;
import com.acchain.community.presenter.TakeInCurrencyPresenter;
import com.acchain.community.util.Const;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.acchain.community.util.Const.KEY_CURRENCY_CHAIN;
import static com.acchain.community.util.Const.KEY_CURRENCY_SHORT_NAME;
import static com.acchain.community.util.Const.KEY_FILTER_SOURCE;

/**
 * function---- TakeInCurrencyActivity: 转入数字资产
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/10 02:15:50 (+0000).
 */
@Route(path = ARouterConst.Activity_TakeInCurrencyActivity)
public class TakeInCurrencyActivity extends BaseActivity<TakeInCurrencyPresenter> implements TakeInCurrencyContract.View, TakeInCurrencyAdapter.OnItemItemClickListener, OnFilterResultListener {

    /**
     * 搜索框
     */
    @BindView(R.id.fl_search)
    FrameLayout mFlSearch;

    /**
     * 数字资产列表
     */
    @BindView(R.id.xrv_currency)
    XRecyclerView mXrvCurrency;
    ArrayList<TakeInCurrencyBean> takeInCurrencyBeans;
    TakeInCurrencyAdapter takeInCurrencyAdapter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_take_in_currency;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //资产列表
        takeInCurrencyBeans = new ArrayList<>();
        takeInCurrencyAdapter = new TakeInCurrencyAdapter(takeInCurrencyBeans, this);
        mXrvCurrency.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mXrvCurrency.setAdapter(takeInCurrencyAdapter);
        mXrvCurrency.setEmptyView(findViewById(R.id.empty_view));
        mXrvCurrency.setLoadingMoreEnabled(false);
        mXrvCurrency.setPullRefreshEnabled(false);

        //更换搜索框背景
        mFlSearch.post(() -> mFlSearch.setBackground(new RoundRectShapeDrawable(mFlSearch.getWidth(), mFlSearch.getHeight(), getResources().getColor(R.color.blue_search_background))));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.currencyList(DefaultPreferenceUtil.getInstance().getToken(),null);
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @OnClick(R.id.fl_search)
    public void onViewClicked() {
        ArrayList<CurrencyBean> source = new ArrayList<CurrencyBean>();
        for (int i = 0; i < source.size(); i++) {
            source.addAll(takeInCurrencyBeans.get(i).getCurrencies());
        }
        ARouter.getInstance()
                .build(ARouterConst.Activity_SearchFilterActivity)
                .withObject(KEY_FILTER_SOURCE, source)
                .navigation(this, Const.REQUEST_CODE_ONE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SearchFilterActivity.dealSearchResult(data,requestCode == Const.REQUEST_CODE_ONE && resultCode == RESULT_OK,this);
    }

    /**
     * @param outerPosition recyclerView被点击的位置
     * @param innerPosition gridView被点击的位置
     * @param view          被点击的view
     */
    @Override
    public void onItemItemClick(int outerPosition, int innerPosition, View view) {
        ARouter.getInstance()
                .build(ARouterConst.Activity_TakeInCurrencyDetailActivity)
                .withString(Const.KEY_CURRENCY_CHAIN,takeInCurrencyBeans.get(outerPosition).getChainName())
                .withString(KEY_CURRENCY_SHORT_NAME, takeInCurrencyBeans.get(outerPosition).getCurrencies().get(innerPosition).getCurrencyShortName())
                .navigation();
    }

    /**
     * 1.1.0 查询币种列表集合(转入)
     *
     * @param takeInCurrencyAgents 转入时的资产列表
     */
    @Override
    public void currencyListFinish(List<TakeInCurrencyAgent> takeInCurrencyAgents) {
        if (takeInCurrencyAgents == null) {
            return;
        }

        if (takeInCurrencyAgents.size() == 0) {
            showToast("无币种信息");
            return;
        }

        takeInCurrencyBeans.clear();
        takeInCurrencyBeans.addAll(TakeInCurrencyBean.translate(takeInCurrencyAgents.get(0)));
        takeInCurrencyAdapter.notifyDataSetChanged();
    }

    /**
     * @param position 点击的位置
     */
    @Override
    public void onFilterSelectItem(int position) {
        ArrayList<CurrencyBean> source = new ArrayList<CurrencyBean>();
        for (int i = 0; i < source.size(); i++) {
            source.addAll(takeInCurrencyBeans.get(i).getCurrencies());
        }

        //获取选中位置的bean
        CurrencyBean selectedBean = source.get(position);
        for (TakeInCurrencyBean coll : takeInCurrencyBeans) {
            if(coll.getCurrencies().indexOf(selectedBean)!=-1){
                ARouter.getInstance()
                        .build(ARouterConst.Activity_TakeInCurrencyDetailActivity)
                        .withString(KEY_CURRENCY_SHORT_NAME, selectedBean.getCurrencyShortName())
                        .withString(KEY_CURRENCY_CHAIN,coll.getChainName())
                        .navigation();
                return;
            }
        }
    }

    /**
     * @param editable 输入的用于过滤的信息
     */
    @Override
    public void onFilterInputKeys(String filter) {
        mPresenter.currencyList(DefaultPreferenceUtil.getInstance().getToken(),filter);
    }
}