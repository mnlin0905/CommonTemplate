package com.acchain.community.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.acchain.community.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 2018/1/3
 * function : 将TabLayout和ViewPager进行组合，形成一个layout
 *
 * @author ACChain
 */

public class TabPagerCombineLayout extends LinearLayout implements TabLayout.OnTabSelectedListener {
    @BindView(R.id.tl_tab)
    DisallowClickTabLayout mTlTab;
    @BindView(R.id.vp_pager)
    DisableScrollViewPager mVpPager;

    /**
     * 碎片布局:
     */
    private List<? extends Fragment> fragments;

    /**
     * 适配器
     */
    private FragmentPagerAdapter pagerAdapter;

    /**
     * 标题
     */
    private String[] titles;

    /**
     * 布局管理
     */
    private FragmentManager manager;

    /**
     * 监听器
     */
    private onTabPagerListener listener;

    /**
     * 当前显示的位置
     */
    private int currentPosition;

    public TabPagerCombineLayout(Context context) {
        this(context, null);
    }

    public TabPagerCombineLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabPagerCombineLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //初始化自身
        setOrientation(VERTICAL);

        //加载组合布局
        LayoutInflater.from(getContext()).inflate(R.layout.layout_tab_pager_conbine_layout, this, true);
        ButterKnife.bind(this);

        //监听事件
        mTlTab.addOnTabSelectedListener(this);

        //当首次显示时,让0位置启动回调
        mVpPager.post(() -> {
            if (listener != null && titles.length != 0) {
                listener.onPagerAppear(0);
            }
        });
    }

    /**
     * @param manageScrollInterface 提供管理是否可以滑动的接口
     */
    public TabPagerCombineLayout provideManageScrollInterface(DisableScrollViewPager.ManageScrollInterface manageScrollInterface) {
        mVpPager.setManageScrollInterface(manageScrollInterface);
        return this;
    }

    /**
     * @param manageClickInterface 提供接口,管理是否可以点击tablayout
     */
    public TabPagerCombineLayout provideManageClickInterface(DisallowClickTabLayout.ManageClickInterface manageClickInterface){
        mTlTab.setManageClickInterface(manageClickInterface);
        return this;
    }

    /**
     * 设置碎片
     */
    public TabPagerCombineLayout provideFragments(List<? extends Fragment> fragments) {
        this.fragments = fragments;
        return this;
    }

    /**
     * 提供碎片管理器
     */
    public TabPagerCombineLayout provideFragmentManager(FragmentManager manager) {
        this.manager = manager;
        return this;
    }

    /**
     * 设置默认选中的位置
     */
    public TabPagerCombineLayout provideDefaultPosition(int position) {
        currentPosition = position;
        return this;
    }

    /**
     * 设置标题
     */
    public TabPagerCombineLayout provideTitles(String[] titles) {
        this.titles = titles;
        return this;
    }

    /**
     * 设置监听
     */
    public TabPagerCombineLayout provideListener(onTabPagerListener listener) {
        this.listener = listener;
        return this;
    }

    /**
     * 调用方法完成设置
     */
    public void combine() {
        if (fragments == null || titles == null || titles.length != fragments.size()) {
            throw new RuntimeException("数据源不能为null且tabs与fragments数量应该相同");
        }
        pagerAdapter = new CombinePagerAdapter(manager);
        mVpPager.setAdapter(pagerAdapter);
        mTlTab.setupWithViewPager(mVpPager);
        mTlTab.setScrollPosition(currentPosition,0,true);
        mVpPager.setCurrentItem(currentPosition);
    }

    /**
     * 提供数据源
     */
    public ViewPager getViewPager() {
        return mVpPager;
    }

    /**
     * 提供数据源
     */
    public TabLayout getTabLayout() {
        return mTlTab;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (listener != null) {
            listener.onPagerAppear(tab.getPosition());
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        if (listener != null) {
            listener.onPagerDisappear(tab.getPosition());
        }
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        if (listener != null) {
            listener.onPagerReAppear(tab.getPosition());
        }
    }

    /**
     * 监听事件
     */
    public interface onTabPagerListener {
        /**
         * @param position 第position位置的页面显示
         */
        void onPagerAppear(int position);

        /**
         * @param position 第position位置被重复选中
         */
        void onPagerReAppear(int position);

        /**
         * @param position 消失
         */
        void onPagerDisappear(int position);
    }

    /**
     * 适配器
     */
    private class CombinePagerAdapter extends FragmentPagerAdapter {
        public CombinePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }
    }
}
