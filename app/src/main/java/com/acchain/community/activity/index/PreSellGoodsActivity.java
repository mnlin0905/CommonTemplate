package com.acchain.community.activity.index;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.adapter.ActivityViewPagerAdapter;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.base.BaseFragment;
import com.acchain.community.bean.PreSellGoodsDetail;
import com.acchain.community.contract.PreSellGoodsContract;
import com.acchain.community.fragment.PreSellDetailFragment;
import com.acchain.community.fragment.PreSellGoodFragment;
import com.acchain.community.presenter.PreSellGoodsPresenter;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.util.Installation;
import com.acchain.community.util.L;
import com.acchain.community.window.popupwindow.ListViewPopup;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * function---- PreSellGoodsActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/12 12:18:40 (+0000).
 */
@Route(path = ARouterConst.Activity_PreSellGoodsActivity)
public class PreSellGoodsActivity extends BaseActivity<PreSellGoodsPresenter> implements PreSellGoodsContract.View {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tv_collect)
    TextView tvCollect;
    private ActivityViewPagerAdapter activityViewPagerAdapter;
    private List<BaseFragment> fragments = new ArrayList<>();
    private PreSellGoodFragment preSellGoodFragment;
    private PreSellDetailFragment preSellDetailFragment;
    private ListViewPopup listViewPopup;
    String[] itemStr;
    int[] itemImage = {R.drawable.icon_message_white, R.drawable.icon_search_white, R.drawable.icon_share, R.drawable.icon_zuji};
    private PreSellGoodsDetail preSellGoodsDetail;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_pre_sell_goods;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_panic_goods, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.shopping_cart:
                ARouter.getInstance().build(ARouterConst.Activity_ShoppingCartActivity).navigation();
                break;
            case R.id.more:
                if (listViewPopup == null) {
                    itemStr = getResources().getStringArray(R.array.popup_panic_goods);
                    listViewPopup = new ListViewPopup(this, itemStr, itemImage);
                    listViewPopup.setOnItemListener(new ListViewPopup.OnItemListener() {
                        @Override
                        public void onItemClick(int position) {
                            Log.i("onItemClick", "position=" + itemStr[position]);
                        }
                    });
                }
                listViewPopup.showAtLocation(toolbar, Gravity.TOP | Gravity.RIGHT, 0, toolbar.getHeight());
                break;
        }
        return true;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        setTitle("");
        //初始化tabLayout和viewpager
        fragments = new ArrayList<>();
        preSellGoodFragment = new PreSellGoodFragment();
        preSellDetailFragment = new PreSellDetailFragment();
        fragments.add(preSellGoodFragment);
        fragments.add(preSellDetailFragment);
        fragments.add(new PreSellDetailFragment());
        String[] tabs = getResources().getStringArray(R.array.index_pre_sell_goods);
        activityViewPagerAdapter = new ActivityViewPagerAdapter(getSupportFragmentManager(), fragments) {
            @Override
            public CharSequence getPageTitle(int position) {
                return tabs[position];
            }
        };
        viewPager.setAdapter(activityViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        int presellId = getIntent().getIntExtra("presellId", 0);
        mPresenter.getPreSellProductDetail(presellId);
        mPresenter.addUserFooter(Installation.id(this),DefaultPreferenceUtil.getInstance().getToken(),presellId,1,presellId);
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }


    @OnClick({R.id.tv_index,R.id.tv_collect,R.id.tv_customer_service,R.id.tv_add_cart, R.id.tv_buy_now})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_index:
                ARouter.getInstance().build(ARouterConst.Activity_MainActivity).navigation();
                break;
            case R.id.tv_collect://抢购的不可收藏
                mPresenter.addOrDeleteCollection(DefaultPreferenceUtil.getInstance().getToken(),preSellGoodsDetail.getProductId(),1,preSellGoodsDetail.getProductId());
                break;
            case R.id.tv_customer_service:
                break;
            case R.id.tv_add_cart:
                if (preSellGoodFragment.isAgreement())
                    preSellGoodFragment.addCart();
                else
                    showToast("请先阅读并同意服务协议");
                break;
            case R.id.tv_buy_now:
                preSellGoodFragment.confirmOrder();
                break;
        }
    }

    @Override
    public void onAddOrDeleteCollectFinish(int type, boolean b) {
        super.onAddOrDeleteCollectFinish(type, b);
        if(type==1) {
            if (b) {
                showToast("操作成功");
            } else
                showToast("操作失败");
        }
    }

    @Override
    public void onPreSellProductDetailFinish(PreSellGoodsDetail preSellGoodsDetail) {
        if (preSellGoodsDetail == null) {
            L.i("failed");
        } else {
            this.preSellGoodsDetail=preSellGoodsDetail;
            preSellGoodFragment.refreshUi(preSellGoodsDetail);
            preSellDetailFragment.refreshUi(preSellGoodsDetail);
        }
    }
}