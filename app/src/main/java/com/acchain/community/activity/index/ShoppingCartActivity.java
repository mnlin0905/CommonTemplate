package com.acchain.community.activity.index;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.adapter.ActivityViewPagerAdapter;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.base.BaseFragment;
import com.acchain.community.contract.ShoppingCartContract;
import com.acchain.community.fragment.CartAdoptFragment;
import com.acchain.community.fragment.CartPanicFragment;
import com.acchain.community.fragment.CartPreSellFragment;
import com.acchain.community.presenter.ShoppingCartPresenter;
import com.alibaba.android.arouter.facade.annotation.Route;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * function---- ShoppingCartActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/11 07:35:37 (+0000).
 */
@Route(path = ARouterConst.Activity_ShoppingCartActivity,extras = ARouterConst.FLAG_LOGIN)
public class ShoppingCartActivity extends BaseActivity<ShoppingCartPresenter> implements ShoppingCartContract.View {
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private List<BaseFragment> fragments = new ArrayList<>();
    private CartPanicFragment cartPanicFragment;
    private CartPreSellFragment cartPreSellFragment;
    private CartAdoptFragment cartAdoptFragment;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_shopping_cart;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_shopping_cart, menu);
        return true;
    }

    private boolean isEdit = false;

    private int currentFragmentIndex=0;

    private MenuItem item;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                break;
            case R.id.edit:
                this.item=item;
                if (isEdit) {
                    item.setTitle("编辑");
                    isEdit = false;
                } else {
                    item.setTitle("完成");
                    isEdit = true;
                }
                if(currentFragmentIndex==0){
                    cartPanicFragment.editOrCompleteAll(isEdit);
                }else if(currentFragmentIndex==1){
                    cartPreSellFragment.editOrCompleteAll(isEdit);
                }else if(currentFragmentIndex==2){
                    cartAdoptFragment.editOrCompleteAll(isEdit);
                }
                break;
        }
        return true;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        cartPanicFragment=new CartPanicFragment();
        cartPreSellFragment=new CartPreSellFragment();
        cartAdoptFragment=new CartAdoptFragment();
        fragments.clear();
        fragments.add(cartPanicFragment);
        fragments.add(cartPreSellFragment);
        fragments.add(cartAdoptFragment);
        String[] tabs = getResources().getStringArray(R.array.shopping_cart);
        ActivityViewPagerAdapter activityViewPagerAdapter = new ActivityViewPagerAdapter(getSupportFragmentManager(), fragments) {
            @Override
            public CharSequence getPageTitle(int position) {
                return tabs[position];
            }
        };
        viewPager.setAdapter(activityViewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                /*切换fragment时，如果当前fragment是编辑状态，把它改为原始状态*/
                if(isEdit){
                    item.setTitle("编辑");
                    isEdit = false;
                    if(currentFragmentIndex==0){
                        cartPanicFragment.editOrCompleteAll(isEdit);
                    }else if(currentFragmentIndex==1){
                        cartPreSellFragment.editOrCompleteAll(isEdit);
                    }else if(currentFragmentIndex==2){
                        cartAdoptFragment.editOrCompleteAll(isEdit);
                    }
                }
                    currentFragmentIndex=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.post(() -> setIndicator(tabLayout, 40, 40));
    }

    /*改变指示器的宽度*/
    public static void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();//拿到类型类
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");//获取一个类的所有字段 ，getField是获取所有public字段
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return;
        }
        tabStrip.setAccessible(true);//允许拿到私有成员变量
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);//拿到
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return;
        }
        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());
        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            if (child instanceof ViewGroup) {//TabView
                View childAt1 = ((ViewGroup) child).getChildAt(1);//TextView
                ((TextView) childAt1).setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
            }
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

}