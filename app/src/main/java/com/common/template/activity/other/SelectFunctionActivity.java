package com.common.template.activity.other;

import android.Manifest;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.BarUtils;
import com.common.template.R;
import com.common.template.arouter.ARouterConst;
import com.common.template.base.BaseActivity;
import com.common.template.base.BaseHttpBean;
import com.common.template.contract.SelectFunctionContract;
import com.common.template.presenter.SelectFunctionPresenter;
import com.common.template.retrofit.HttpInterface;
import com.jaeger.library.StatusBarUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
@Route(path = ARouterConst.Activity_SelectFunctionActivity)
public class SelectFunctionActivity extends BaseActivity<SelectFunctionPresenter> implements SelectFunctionContract.View {
    /**
     * 网络请求对象
     */
    @Inject
    protected HttpInterface httpInterface;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.ll_content)
    LinearLayout mLlContent;
    @BindView(R.id.nv_slideBar)
    NavigationView mNvSlideBar;
    @BindView(R.id.dl_drawerLayout)
    DrawerLayout mDlDrawerLayout;
    //上次点击back时间
    private long lastPressBackTime;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_select_function;
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        StatusBarUtil.setTranslucentForDrawerLayout(this, mDlDrawerLayout);

        toolbar.setNavigationOnClickListener(v -> mDlDrawerLayout.openDrawer(GravityCompat.START));
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDlDrawerLayout, R.string
                .drawer_layout_open, R.string.drawer_layout_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if (drawerView == mNvSlideBar) {
                    mLlContent.setLeft((int) (slideOffset * drawerView.getWidth()));
                }
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        //设置padding
        toolbar.post(() -> toolbar.setPadding(0, BarUtils.getStatusBarHeight(), 0, 0));

        //设置左上角的图标形状随着滑动发生变化；设置滑动之后应当出现遮罩窗口的地方现设为透明
        toggle.syncState();
        mDlDrawerLayout.addDrawerListener(toggle);
        mDlDrawerLayout.setScrimColor(Color.TRANSPARENT);

        //设置navigation默认选中状态,位置等
        mNvSlideBar.setItemTextColor(getResources().getColorStateList(R.color.selector_slide_bar_text_icon_color));
        mNvSlideBar.setItemIconTintList(getResources().getColorStateList(R.color.selector_slide_bar_text_icon_color));
        mNvSlideBar.setCheckedItem(0);

        //启动时请求权限
        SelectFunctionActivityPermissionsDispatcher.needsPermissionPhoneWithPermissionCheck(this, null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_message, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SelectFunctionActivityPermissionsDispatcher.needsPermissionPhoneWithPermissionCheck(this, item);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mDlDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDlDrawerLayout.closeDrawer(GravityCompat.START);
        } else if (System.currentTimeMillis() - lastPressBackTime > 1000) {
            showToast("再按一次退出程序");
            lastPressBackTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();
        }
    }


    @NeedsPermission(Manifest.permission.READ_PHONE_STATE)
    void needsPermissionPhone(MenuItem item) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        SelectFunctionActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnShowRationale(Manifest.permission.READ_PHONE_STATE)
    void onShowRationaleMethod(final PermissionRequest request) {
        showToast("为了更安全的使用软件,请开启该权限");
        request.proceed();
    }

    /**
     * 举例请求网络数据
     */
    @OnClick(R.id.fab)
    public void onViewClicked() {
        httpInterface.doLogin("name", "password")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseHttpBean<Object>>() {
                    @Override
                    public void accept(BaseHttpBean<Object> objectBaseHttpBean) throws Exception {
                        // TODO: 2018/3/10 成功
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        // TODO: 2018/3/10 失败
                    }
                });
    }
}
