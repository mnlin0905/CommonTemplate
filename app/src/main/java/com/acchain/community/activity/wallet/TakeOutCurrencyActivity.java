package com.acchain.community.activity.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.activity.other.SearchFilterActivity;
import com.acchain.community.adapter.TakeOutCurrencyAdapter;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.bean.CurrencyBean;
import com.acchain.community.bean.Friend;
import com.acchain.community.contract.TakeOutCurrencyContract;
import com.acchain.community.drawable.RoundRectShapeDrawable;
import com.acchain.community.interfaces.OnFilterResultListener;
import com.acchain.community.presenter.TakeOutCurrencyPresenter;
import com.acchain.community.util.Const;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.view.DisableChildGridView;
import com.acchain.community.window.SelectTurnOutDialogFragment;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.acchain.community.util.Const.KEY_FILTER_SOURCE;
import static com.acchain.community.util.Const.KEY_IS_FROM_OTHER_ACTIVITY;

/**
 * function---- TakeOutCurrencyActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/11 13:47:31 (+0000).
 */
@Route(path = ARouterConst.Activity_TakeOutCurrencyActivity)
public class TakeOutCurrencyActivity extends BaseActivity<TakeOutCurrencyPresenter> implements TakeOutCurrencyContract.View, AdapterView.OnItemClickListener, SelectTurnOutDialogFragment.OnSelectTurnOutListener, OnFilterResultListener {
    @BindView(R.id.fl_search)
    FrameLayout mFlSearch;
    @BindView(R.id.tv_friends)
    TextView mTvFriends;
    @BindView(R.id.tv_platform)
    TextView mTvPlatform;
    @BindView(R.id.linear_transOut)
    LinearLayout linearTransOut;

    /**
     * 资产列表
     */
    @BindView(R.id.gv_currency)
    DisableChildGridView mGvCurrency;
    ArrayList<CurrencyBean> currencyBeans;
    TakeOutCurrencyAdapter takeOutCurrencyAdapter;


    /**
     * 是否是从其他界面跳转过来
     */
    @Autowired(name = KEY_IS_FROM_OTHER_ACTIVITY, required = false)
    boolean isFromOtherActivity;

    /**
     * 如果从其他activity中传入该字段,则下一步直接跳转到给朋友转账的界面,而不再进行朋友选择
     */
    @Autowired(name = Const.MODEL_CONTACT_FRIEND, required = false)
    Friend friendModel;

    /**
     * 上次选中的位置
     * <p>
     * 默认为-1,表示未选中任何选项
     */
    private int oldPosition = -1;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_take_out_currency;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //初始化GridView,刷新数据源,设定适配器
        currencyBeans = new ArrayList<>();
        takeOutCurrencyAdapter = new TakeOutCurrencyAdapter(this, currencyBeans);
        mGvCurrency.setAdapter(takeOutCurrencyAdapter);
        mGvCurrency.setOnItemClickListener(this);
        mGvCurrency.setEmptyView(findViewById(R.id.empty_view));
        if (friendModel != null) {
            linearTransOut.setVisibility(View.GONE);
        }

        //更换搜索框背景
        mFlSearch.post(() -> mFlSearch.setBackground(new RoundRectShapeDrawable(mFlSearch.getWidth(), mFlSearch.getHeight(), getResources().getColor(R.color.blue_search_background))));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getCurrencyListTransfer(DefaultPreferenceUtil.getInstance().getToken(), null);
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @OnClick({R.id.fl_search, R.id.tv_friends, R.id.tv_platform})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fl_search://搜索过滤
                ARouter.getInstance()
                        .build(ARouterConst.Activity_SearchFilterActivity)
                        .withObject(KEY_FILTER_SOURCE, currencyBeans)
                        .navigation(this, Const.REQUEST_CODE_ONE);
                break;
            case R.id.tv_friends://转给朋友
                if (oldPosition == -1) {
                    showToast("无选中资产,无法转给朋友");
                    return;
                }

                onSelectFriends();
                break;
            case R.id.tv_platform://转出平台
                if (oldPosition == -1) {
                    showToast("无选中资产,无法转出平台");
                    return;
                }

                onSelectPlatform();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SearchFilterActivity.dealSearchResult(data, requestCode == Const.REQUEST_CODE_ONE && resultCode == RESULT_OK, this);
    }

    /**
     * 当点击时,将上次选中的位置设置为未选中状态;
     * 再将自身设置为选中状态
     * <p>
     * 同一时刻,只能有一个item处于选中状态
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //如果为其他界面需要
        if (isFromOtherActivity) {
            //如果friend有值,下一步直接跳转到给朋友转账的界面
            if (friendModel != null) {
                ARouter.getInstance()
                        .build(ARouterConst.Activity_TakeOutFriendActivity)
                        .withString(Const.KEY_CURRENCY_SHORT_NAME, currencyBeans.get(position).getCurrencyShortName())
                        .withObject(Const.MODEL_CONTACT_FRIEND, friendModel)
                        .navigation();
                finish();
                return;
            }
            //如果friend无值,直接将选择的对象字段返回即可
            Intent intent = new Intent();
            intent.putExtra(Const.KEY_CURRENCY_SHORT_NAME, currencyBeans.get(position).getCurrencyShortName());
            setResult(RESULT_OK, intent);
            finish();
            return;
        }

        //切换选中状态
        if (oldPosition >= 0 && oldPosition < currencyBeans.size()) {
            currencyBeans.get(oldPosition).toggleSELECTED_STATUS();
        }
        currencyBeans.get(position).toggleSELECTED_STATUS();
        oldPosition = position;
    }

    @Override
    public void getCurrencyListTransferFinish(List<CurrencyBean> currencyBeans) {
        if (currencyBeans == null) {
            return;
        }

        this.currencyBeans.clear();
        this.currencyBeans.addAll(currencyBeans);
        takeOutCurrencyAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSelectFriends() {
        ARouter.getInstance()
                .build(ARouterConst.Activity_TakeOutSelectFriendActivity)
                .withString(Const.KEY_CURRENCY_SHORT_NAME, currencyBeans.get(oldPosition).getCurrencyShortName())
                .navigation();
        finish();
    }

    @Override
    public void onSelectPlatform() {
        ARouter.getInstance()
                .build(ARouterConst.Activity_TakeOutPlatformActivity)
                .withString(Const.KEY_CURRENCY_SHORT_NAME, currencyBeans.get(oldPosition).getCurrencyShortName())
                .navigation();
        finish();
    }

    /**
     * @param position 点击的位置
     */
    @Override
    public void onFilterSelectItem(int position) {
        //切换到刚才选中的位置
        onItemClick(mGvCurrency, null, position, -1);
        if (!isFromOtherActivity) {
            new SelectTurnOutDialogFragment()
                    .setOnSelectTurnOutListener(this)
                    .show(getSupportFragmentManager(), "select");
        }
    }

    /**
     * @param filter 输入的用于过滤的信息
     */
    @Override
    public void onFilterInputKeys(String filter) {
        oldPosition = -1;
        mPresenter.getCurrencyListTransfer(DefaultPreferenceUtil.getInstance().getToken(), filter);
    }
}