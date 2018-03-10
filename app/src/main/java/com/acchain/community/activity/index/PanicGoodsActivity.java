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
import com.acchain.community.bean.PanicGoodDetail;
import com.acchain.community.contract.PanicGoodsContract;
import com.acchain.community.fragment.PanicGoodDetailFragment;
import com.acchain.community.fragment.PanicGoodFragment;
import com.acchain.community.presenter.PanicGoodsPresenter;
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
 * function---- PanicGoodsActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/08 08:51:59 (+0000).
 */
@Route(path = ARouterConst.Activity_PanicGoodsActivity)
public class PanicGoodsActivity extends BaseActivity<PanicGoodsPresenter> implements PanicGoodsContract.View {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tv_collect)
    TextView tvCollect;
    private ActivityViewPagerAdapter activityViewPagerAdapter;
    private List<BaseFragment> fragments = new ArrayList<>();
    private PanicGoodFragment panicGoodFragment;
    private PanicGoodDetailFragment panicGoodDetailFragment;
    private ListViewPopup listViewPopup;
    String[] itemStr;
    int[] itemImage = {R.drawable.icon_message_white, R.drawable.icon_search_white, R.drawable.icon_share, R.drawable.icon_zuji};
    private PanicGoodDetail panicGoodDetail;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_panic_goods;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_panic_goods, menu);
        return true;
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
        panicGoodFragment = new PanicGoodFragment();
        panicGoodDetailFragment = new PanicGoodDetailFragment();
        fragments.add(panicGoodFragment);
        fragments.add(panicGoodDetailFragment);
        String[] tabs = getResources().getStringArray(R.array.index_panic_goods);
        activityViewPagerAdapter = new ActivityViewPagerAdapter(getSupportFragmentManager(), fragments) {
            @Override
            public CharSequence getPageTitle(int position) {
                return tabs[position];
            }
        };
        viewPager.setAdapter(activityViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        /*拿传过来的参数*/
        int purchaseId = getIntent().getIntExtra("purchaseId", 0);//商品id
        int flashSaleId = getIntent().getIntExtra("flashSaleId", 0);//活动id
        L.i("purchaseId=" + purchaseId + "         flashSaleId=" + flashSaleId);
        mPresenter.getFlashSalesDetail(purchaseId,flashSaleId);
        mPresenter.addUserFooter(Installation.id(this),DefaultPreferenceUtil.getInstance().getToken(),purchaseId,4,flashSaleId);
    }

    @Override
    public void onFlashSalesDetailFinish(PanicGoodDetail panicGoodDetail) {
        if (panicGoodDetail == null) {
            L.i("failed");
        } else {
            this.panicGoodDetail = panicGoodDetail;
            if(panicGoodDetail.getCollectionCount()==0){
                //没有收藏
                isCollect=false;
                tvCollect.setCompoundDrawablesRelativeWithIntrinsicBounds(0,R.drawable.icon_collect,0,0);
            }else {
                isCollect=true;
                tvCollect.setCompoundDrawablesRelativeWithIntrinsicBounds(0,R.drawable.icon_collect,0,0);
            }
            panicGoodFragment.refreshData(panicGoodDetail);
            panicGoodDetailFragment.refreshUi(panicGoodDetail);
        }
    }

    private boolean isCollect;

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (listViewPopup != null) {
            listViewPopup.unbind();
        }
    }

    @OnClick({R.id.tv_index,R.id.tv_collect,R.id.tv_customer_service,R.id.tv_add_cart, R.id.tv_buy_now})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_index:
                ARouter.getInstance().build(ARouterConst.Activity_MainActivity).navigation();
                break;
            case R.id.tv_collect://抢购的不可收藏
//                mPresenter.addOrDeleteCollection(DefaultPreferenceUtil.getInstance().getToken(),panicGoodDetail.getPurchaseId(),0,panicGoodDetail.getProductId());
                break;
            case R.id.tv_customer_service:
                break;
            case R.id.tv_add_cart:
                panicGoodFragment.addCart();
                break;
            case R.id.tv_buy_now:
                panicGoodFragment.confirmOrder();
                break;
        }
    }
}