package com.acchain.community.activity.person;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.base.BaseFragment;
import com.acchain.community.util.Const;
import com.acchain.community.view.DisableScrollViewPager;
import com.acchain.community.view.DisallowClickTabLayout;
import com.acchain.community.view.TabPagerCombineLayout;
import com.acchain.community.window.popupwindow.ListViewPopup;
import com.acchain.community.window.CommonPopWindow;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * function---- OrderFormRecordActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/25 07:04:08 (+0000).
 */
@Route(path = ARouterConst.Activity_OrderFormRecordActivity)
public class OrderFormRecordActivity extends BaseActivity implements  DisableScrollViewPager.ManageScrollInterface, DisallowClickTabLayout.ManageClickInterface, ListViewPopup.OnItemListener{
    /**
     * 碎片列表
     */
    protected List<BaseFragment> fragments;
    @BindView(R.id.tpcl_records)
    TabPagerCombineLayout mTpclRecords;

    /**
     * 设置默认的选中位置
     */
    @Autowired(name = Const.KEY_POSITION,required = false)
    int defaultPosition;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_order_form_record;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //初始化界面
        initFragment();
    }

    @Override
    protected void injectSelf() {
    }

    /**
     * 初始化碎片数据
     */
    protected void initFragment() {
        fragments = new ArrayList<>();
        String[] stringArray = getResources().getStringArray(R.array.order_form_record_kind_arrays);
        for (int i = 0; i < stringArray.length; i++) {
            fragments.add((BaseFragment) ARouter.getInstance()
                    .build(ARouterConst.Fragment_OrderFormRecordFragment)
                    .withInt(Const.KEY_POSITION, i)
                    .navigation());
        }
        mTpclRecords.provideFragmentManager(getSupportFragmentManager())
                .provideFragments(fragments)
                .provideTitles(stringArray)
                .provideManageScrollInterface(this)
                .provideManageClickInterface(this)
                .provideDefaultPosition(defaultPosition)
                .combine();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_more, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        CommonPopWindow.getInstance(this, this)
                .showAtLocation(toolbar, Gravity.TOP | Gravity.RIGHT, 0, toolbar.getHeight());
        return true;
    }

    @Override
    public boolean currentCanScroll() {
        return true;
    }

    @Override
    public boolean currentCanClick() {
        return true;
    }

    @Override
    public void onItemClick(int position) {
        // TODO: 2018/1/23 menu功能:搜索
    }
}