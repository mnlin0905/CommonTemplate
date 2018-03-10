package com.acchain.community.activity.other;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.SelectFunctionContract;
import com.acchain.community.presenter.SelectFunctionPresenter;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
@Route(path = ARouterConst.Activity_SelectFunctionActivity)
public class SelectFunctionActivity extends BaseActivity<SelectFunctionPresenter> implements SelectFunctionContract.View {
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

    @OnClick(R.id.fab)
    protected void onClickFab() {
        Intent intent=new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent activity = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification compat=new NotificationCompat.Builder(this,"community")
                .setContentTitle("New Message")
                .setContentText("You've received new messages.")
                .setProgress(100,30,false)
                .setContentIntent(activity)
                .setAutoCancel(true)
                .build();
        NotificationManager manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(11,compat);
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
}
