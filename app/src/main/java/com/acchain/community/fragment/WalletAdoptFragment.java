package com.acchain.community.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.acchain.community.R;
import com.acchain.community.adapter.WalletAdoptAdapter;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseFragment;
import com.acchain.community.base.BaseRecyclerViewHolder;
import com.acchain.community.bean.WalletAdoptList;
import com.acchain.community.contract.WalletAdoptContract;
import com.acchain.community.interfaces.AdoptKindOperate;
import com.acchain.community.interfaces.CurrencyOperateInterface;
import com.acchain.community.interfaces.OnChildCountChanged;
import com.acchain.community.interfaces.OnNotificationCountChanged;
import com.acchain.community.presenter.WalletAdoptPresenter;
import com.acchain.community.util.Const;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.window.SelectTurnOutDialogFragment;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.acchain.community.util.Const.KEY_CURRENCY_CHAIN;
import static com.acchain.community.util.Const.KEY_CURRENCY_SHORT_NAME;

/**
 * function---- WalletAdoptFragment
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/03 06:51:20 (+0000).
 */
@Route(path = ARouterConst.Fragment_WalletAdoptFragment)
public class WalletAdoptFragment extends BaseFragment<WalletAdoptPresenter> implements WalletAdoptContract.View, BaseRecyclerViewHolder.OnViewClickListener, AdoptKindOperate, OnChildCountChanged, XRecyclerView.LoadingListener, SelectTurnOutDialogFragment.OnSelectTurnOutListener {

    @BindView(R.id.xrv_currency)
    XRecyclerView mXrvCurrency;

    /**
     * 数据源,适配器
     */
    private ArrayList<WalletAdoptList> currencyBeans;
    private WalletAdoptAdapter currencyAdapter;

    /**
     * 逻辑代理处理
     */
    private AdoptKindOperate proxyHandler;

    /**
     * 设置代理类
     */
    public WalletAdoptFragment setProxy(CurrencyOperateInterface proxyHandler) {
        this.proxyHandler = (AdoptKindOperate) proxyHandler;
        return this;
    }

    /**
     * 当前位置
     */
    private int currentPosition=-1;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_wallet_adopt;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //XRecyclerView适配
        currencyBeans = new ArrayList<>();
        currencyAdapter = new WalletAdoptAdapter(currencyBeans, this, this);
        mXrvCurrency.setLayoutManager(new LinearLayoutManager(baseActivity, LinearLayoutManager.VERTICAL, false));
        mXrvCurrency.setAdapter(currencyAdapter);
        mXrvCurrency.setLoadingMoreEnabled(false);
        mXrvCurrency.setPullRefreshEnabled(false);
        mXrvCurrency.setLoadingListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        //只有当已经登录的情况下,才会去加载网络数据
        mPresenter.adoptList(DefaultPreferenceUtil.getInstance().getToken());
    }

    @Override
    protected void injectSelf() {
        fragmentComponent.inject(this);
    }

    @Override
    public void onViewClick(View v, int position) {
        // TODO: 2018/1/2 点击具体的currency信息时,跳转到详情界面
    }

    @Override
    public void takeIn(int position) {
        ARouter.getInstance()
                .build(ARouterConst.Activity_TakeInCurrencyDetailActivity)
                .withString(KEY_CURRENCY_SHORT_NAME,currencyBeans.get(position).getPROPERTY_CURRENCY())
                .withString(KEY_CURRENCY_CHAIN,currencyBeans.get(position).getChain())
                .navigation();
    }

    @Override
    public void takeOut(int position) {
        currentPosition=position;
        new SelectTurnOutDialogFragment()
                .setOnSelectTurnOutListener(this)
                .show(baseActivity.getSupportFragmentManager(), "turn_out");
    }

    @Override
    public void exercise(int position) {
        // TODO: 2018/2/9 行权
    }

    @Override
    public void flowRecord(int position) {
        ARouter.getInstance()
                .build(ARouterConst.Activity_TakeFlowRecordActivity)
                .withString(KEY_CURRENCY_SHORT_NAME, currencyBeans.get(position).getPROPERTY_CURRENCY())
                .navigation();
    }

    @Override
    public String declare(String operate) {
        return "领养类操作";
    }

    @Override
    public int getCurrencyCount() {
        return currencyBeans.size();
    }

    @Override
    public void onRefresh() {
    }

    @Override
    public void onLoadMore() {

    }

    /**
     * 1.2.2  钱包领养类
     *
     * @param adoptLists 领养列表
     */
    @Override
    public void adoptListFinish(List<WalletAdoptList> adoptLists) {
        if (adoptLists == null) {
            return;
        }

        currencyBeans.clear();
        currencyBeans.addAll(adoptLists);
        currencyAdapter.notifyDataSetChanged();

        //通知父类刷新信息
        if (getParentFragment() != null && getParentFragment() instanceof OnNotificationCountChanged) {
            ((OnNotificationCountChanged) getParentFragment()).onCountChange();
        }
    }

    @Override
    public void onSelectFriends() {
        ARouter.getInstance()
                .build(ARouterConst.Activity_TakeOutSelectFriendActivity)
                .withString(Const.KEY_CURRENCY_SHORT_NAME,currencyBeans.get(currentPosition).getPROPERTY_CURRENCY())
                .navigation();
    }

    @Override
    public void onSelectPlatform() {
        ARouter.getInstance()
                .build(ARouterConst.Activity_TakeOutPlatformActivity)
                .withString(Const.KEY_CURRENCY_SHORT_NAME,currencyBeans.get(currentPosition).getPROPERTY_CURRENCY())
                .navigation();
    }
}
