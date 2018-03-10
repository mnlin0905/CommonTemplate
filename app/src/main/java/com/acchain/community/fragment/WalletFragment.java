package com.acchain.community.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.adapter.ActivityViewPagerAdapter;
import com.acchain.community.adapter.WalletFunctionAdapter;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseFragment;
import com.acchain.community.bean.WalletFunctionBean;
import com.acchain.community.contract.WalletContract;
import com.acchain.community.drawable.RoundRectShapeDrawable;
import com.acchain.community.interfaces.OnChildCountChanged;
import com.acchain.community.interfaces.OnNotificationCountChanged;
import com.acchain.community.presenter.WalletPresenter;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

/**
 * function---- WalletFragment
 * <p>
 * Created(Gradle default create) by ACChain on 2017/12/20 10:48:55 (+0000).
 */
@Route(path = ARouterConst.Fragment_WalletFragment)
public class WalletFragment extends BaseFragment<WalletPresenter> implements WalletContract.View, Toolbar.OnMenuItemClickListener, TabLayout.OnTabSelectedListener, AdapterView.OnItemClickListener, OnNotificationCountChanged, View.OnClickListener {

    /**
     * 搜索框
     */
    @BindView(R.id.tv_search)
    TextView mTvSearch;

    /**
     * toolbar工具栏
     */
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    /**
     * 主功能:包括转入,转出,燃料
     */
    @BindView(R.id.gv_mainFunction)
    GridView mGvMainFunction;
    WalletFunctionAdapter functionAdapter;
    ArrayList<WalletFunctionBean> functionBeans;

    /**
     * 数字资产类型数量
     */
    @BindView(R.id.tv_currencyAmount)
    TextView mTvCurrencyAmount;


    /**
     * 控制显示的关键布局
     */
    @BindView(R.id.ll_controlShow)
    RelativeLayout mLlControlShow;


    /**
     * tabLayout控件,控制切换显示的数字资产
     */
    @BindView(R.id.tl_currency)
    TabLayout mTlCurrency;

    /**
     * viewPager
     */
    @BindView(R.id.vp_fragments)
    ViewPager mVpFragments;

    /**
     * 总收益
     */
    @BindView(R.id.tv_income)
    TextView mTvIncome;

    /**
     * 碎片布局:
     * 预购类;consume_fragment
     * 红包型;dividend_fragment
     */
    private List<BaseFragment> fragments;

    /**
     * 适配器
     */
    private ActivityViewPagerAdapter pagerAdapter;

    /**
     * 保持子类fragment的引用
     */
    private LinkedList<OnChildCountChanged> childFragments;


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_wallet_backup;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initData(Bundle savedInstanceState) {
        //toolbar初始化
        mToolbar.inflateMenu(R.menu.menu_record);
        mToolbar.setOnMenuItemClickListener(this);

        //当布局显示时,动态设置toolbar高度
        //mTlCurrency.post(() -> layoutAppLayout());

        //gv_mainFunction 适配
        functionBeans = WalletFunctionBean.getListDatas(
                new int[]{R.drawable.icon_zhuanru, R.drawable.icon_ranliao, R.drawable.icon_zhuanchu},
                getResources().getStringArray(R.array.wallet_function_filter_tabs),
                new String[]{"", "0 ACC", ""});
        functionAdapter = new WalletFunctionAdapter(baseActivity, functionBeans);
        mGvMainFunction.setAdapter(functionAdapter);
        mGvMainFunction.setOnItemClickListener(this);

        //创建碎片实例,初始化ViewPager
        fragments = new ArrayList<>();
        childFragments = new LinkedList<>();
        fragments.add((BaseFragment) ARouter.getInstance().build(ARouterConst.Fragment_WalletPreOrderFragment).navigation());
        fragments.add((BaseFragment) ARouter.getInstance().build(ARouterConst.Fragment_WalletAdoptFragment).navigation());
        childFragments.addAll((List) fragments);
        String[] tabs = getResources().getStringArray(R.array.wallet_currency_filter_tabs);
        pagerAdapter = new ActivityViewPagerAdapter(getChildFragmentManager(), fragments) {
            @Override
            public CharSequence getPageTitle(int position) {
                return tabs[position];
            }
        };
        mVpFragments.setAdapter(pagerAdapter);

        //绑定tabLayout和viewpager
        mTlCurrency.setupWithViewPager(mVpFragments);
        mTlCurrency.addOnTabSelectedListener(this);

        //为tabLayout设置分割线
        mTlCurrency.post(() -> {
            LinearLayout linearLayout = (LinearLayout) mTlCurrency.getChildAt(0);
            linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            linearLayout.setDividerDrawable(getResources().getDrawable(R.drawable.shape_linearlayout_divider_vertical));
        });

        //mTvCurrencyAmount设置背景图
        mTvSearch.post(() -> mTvSearch.setBackground(new RoundRectShapeDrawable(mTvSearch.getWidth(), mTvSearch.getHeight(), getResources().getColor(R.color.blue_search_background))));
        mTvSearch.setOnClickListener(this);
    }

    /**
     * 复杂布局下,刷新appLayout中内容
     */
    private void layoutAppLayout() {
        //将toolbar和被折叠的布局,都增加一个margin的高度;将总收益文本框设置对其方式
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) mToolbar.getLayoutParams();
        TypedValue typedValue = new TypedValue();
        baseActivity.getTheme().resolveAttribute(android.R.attr.actionBarSize, typedValue, true);
        layoutParams.height = getResources().getDimensionPixelSize(typedValue.resourceId) + mGvMainFunction.getMeasuredHeight();

        layoutParams = (ViewGroup.MarginLayoutParams) mLlControlShow.getLayoutParams();
        layoutParams.bottomMargin = mTlCurrency.getMeasuredHeight() + getResources().getDimensionPixelSize(R.dimen.view_padding_margin_16dp);

        ConstraintLayout.LayoutParams marginLayoutParams = (ConstraintLayout.LayoutParams) mTvIncome.getLayoutParams();
        View child = mTlCurrency.getChildAt(0);
        mTvIncome.setPadding(0, child.getTop(), 0, 0);
        marginLayoutParams.height = child.getHeight() + child.getTop();

        mTlCurrency.getParent().requestLayout();
    }

    @Override
    protected void injectSelf() {
        fragmentComponent.inject(this);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        ARouter.getInstance().build(ARouterConst.Activity_WalletRecordActivity).navigation();
        return false;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    /**
     * 点击功能入口
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0://转入
                ARouter.getInstance().build(ARouterConst.Activity_TakeInCurrencyActivity).navigation();
                break;
            case 1://燃料
                ARouter.getInstance().build(ARouterConst.Activity_BunkersCurrencyActivity).navigation();
                break;
            case 2://转出
                ARouter.getInstance().build(ARouterConst.Activity_TakeOutCurrencyActivity).navigation();
                break;
        }
    }

    @Override
    public void onCountChange() {
        int count = 0;
        for (OnChildCountChanged childFragment : childFragments) {
            count += childFragment.getCurrencyCount();
        }
        mTvCurrencyAmount.setText(String.format(Locale.CHINA, "共计%d种", count));
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        //进行搜索
        ARouter.getInstance()
                .build(ARouterConst.Activity_SearchFilterActivity)
                // TODO: 2018/2/7
                .navigation();
    }
}