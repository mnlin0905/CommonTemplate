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
import com.acchain.community.bean.AdoptGoodDetail;
import com.acchain.community.contract.AdoptGoodsContract;
import com.acchain.community.fragment.AdoptGoodFragment;
import com.acchain.community.fragment.AdoptGoodsDetailFragment;
import com.acchain.community.presenter.AdoptGoodsPresenter;
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
 * function---- AdoptGoodsActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/16 02:44:20 (+0000).
 */
@Route(path = ARouterConst.Activity_AdoptGoodsActivity)
public class AdoptGoodsActivity extends BaseActivity<AdoptGoodsPresenter> implements AdoptGoodsContract.View {
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tv_buy_now)
    TextView tvBuyNow;
    @BindView(R.id.tv_collect)
    TextView tvCollect;
    private ListViewPopup listViewPopup;
    private String[] itemStr;
    int[] itemImage = {R.drawable.icon_message_white, R.drawable.icon_search_white, R.drawable.icon_share, R.drawable.icon_zuji};
    private ActivityViewPagerAdapter activityViewPagerAdapter;
    private List<BaseFragment> fragments = new ArrayList<>();
    private AdoptGoodFragment adoptGoodFragment;
    private AdoptGoodsDetailFragment adoptGoodsDetailFragment;
    private AdoptGoodDetail adoptGoodDetail;

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
                    listViewPopup.setOnItemListener(position -> Log.i("onItemClick", "position=" + itemStr[position]));
                }
                listViewPopup.showAtLocation(toolbar, Gravity.TOP | Gravity.RIGHT, 0, toolbar.getHeight());
                break;
        }
        return true;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        tvBuyNow.setText(getString(R.string.tip_adopt_now));
        setTitle("");
        //初始化tabLayout和viewpager
        fragments = new ArrayList<>();
        adoptGoodFragment = new AdoptGoodFragment();
        adoptGoodsDetailFragment = new AdoptGoodsDetailFragment();
        fragments.add(adoptGoodFragment);
        fragments.add(adoptGoodsDetailFragment);
        fragments.add(new AdoptGoodsDetailFragment());
        String[] tabs = getResources().getStringArray(R.array.index_pre_sell_goods);
        activityViewPagerAdapter = new ActivityViewPagerAdapter(getSupportFragmentManager(), fragments) {
            @Override
            public CharSequence getPageTitle(int position) {
                return tabs[position];
            }
        };
        viewPager.setAdapter(activityViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        int adoptId = getIntent().getIntExtra("adoptId", 0);
        mPresenter.getAdoptProductDetail(adoptId);
        mPresenter.addUserFooter(Installation.id(this),DefaultPreferenceUtil.getInstance().getToken(),adoptId,2,adoptId);
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
                mPresenter.addOrDeleteCollection(DefaultPreferenceUtil.getInstance().getToken(),adoptGoodDetail.getProductId(),2,adoptGoodDetail.getProductId());
                break;
            case R.id.tv_customer_service:
                break;
            case R.id.tv_add_cart:
                if (adoptGoodFragment.isAgreement())
                    adoptGoodFragment.addCart();
                else
                    showToast("请先阅读并同意服务协议");
                break;
            case R.id.tv_buy_now:
                if (adoptGoodFragment.isAgreement())
                    adoptGoodFragment.confirmOrder();
                else
                    showToast("请先阅读并同意服务协议");
                break;
        }
    }
    @Override
    public void onAddOrDeleteCollectFinish(int type, boolean b) {
        super.onAddOrDeleteCollectFinish(type, b);
        if(type==2) {
            if (b) {
                showToast("操作成功");
            } else
                showToast("操作失败");
        }
    }

    @Override
    public void onAdoptProductDetailFinish(AdoptGoodDetail adoptGoodDetail) {
        if(adoptGoodDetail==null){
            L.i("failed");
        }else {
            this.adoptGoodDetail=adoptGoodDetail;
            adoptGoodFragment.refreshUi(adoptGoodDetail);
            adoptGoodsDetailFragment.refreshUi(adoptGoodDetail);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (listViewPopup != null) {
            listViewPopup.unbind();
        }
    }
}