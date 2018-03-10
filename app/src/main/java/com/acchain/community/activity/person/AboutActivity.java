package com.acchain.community.activity.person;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.AboutContract;
import com.acchain.community.presenter.AboutPresenter;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.view.LineMenuView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.AppUtils;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
import zlc.season.rxdownload3.RxDownload;

/**
 * function---- AboutActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/22 05:45:55 (+0000).
 */
@RuntimePermissions
@Route(path = ARouterConst.Activity_AboutActivity)
public class AboutActivity extends BaseActivity<AboutPresenter> implements AboutContract.View, LineMenuView.LineMenuListener {

    @BindView(R.id.tv_version)
    TextView mTvVersion;
    @BindView(R.id.lmv_switch_mode)
    LineMenuView mLmvSwitchMode;

    /**
     * 当前点击次数,连续七次就可以进行网络切换
     */
    private int currentTimes;
    private long lastTime;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //设置版本号
        mTvVersion.setText(String.format("V%s", AppUtils.getAppVersionName()));
        refreshMode();
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @Override
    public boolean performClickLeft(TextView v) {
        return false;
    }

    @Override
    public boolean performClickRight(TextView v) {
        return false;
    }

    @Override
    public void performSelf(LineMenuView v) {
        int position = ((int) v.getTag(R.id.LINE_MENU_VIEW_TAG_POSITION));
        switch (position) {
            case 0:// 服务协议
                ARouter.getInstance().build(ARouterConst.Activity_ServiceAgreementActivity).navigation();
                break;
            case 1:// 版权信息
                ARouter.getInstance().build(ARouterConst.Activity_CopyrightInformationActivity).navigation();
                break;
            case 2:// 检查更新
                showToast("已是最新版本");
                break;
            case 3://切换网络模式
                DefaultPreferenceUtil.getInstance().setOfficialMode(!DefaultPreferenceUtil.getInstance().isOfficialMode());
                refreshMode();
                break;
        }
    }

    /**
     * 切换网络环境状态
     */
    private void refreshMode() {
        mLmvSwitchMode.setBrief(DefaultPreferenceUtil.getInstance().isOfficialMode() ? "正式环境" : "测试环境");
    }

    /**
     * 开启下载最新版本
     */
    @NeedsPermission({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void startDownload(String url) {
        RxDownload.INSTANCE.create(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(status -> {
                    //  下载的百分比
                    String percent = status.percent();
                });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AboutActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnShowRationale({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onShowRationale(final PermissionRequest request) {
    }

    @OnPermissionDenied({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onPermissionDenied() {
    }

    @OnNeverAskAgain({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onNeverAskAgain() {
    }

    @OnClick(R.id.iv_mode_switch)
    public void onViewClicked() {
        //连续点击七次,则显示切换功能菜单

        if (mLmvSwitchMode.getVisibility() == View.VISIBLE) {
            return;
        }

        if (lastTime == 0) {
            lastTime = System.currentTimeMillis();
            currentTimes = 1;
            return;
        }
        if (System.currentTimeMillis() - lastTime > 1000) {
            lastTime = 0;
            return;
        }

        if (++currentTimes >= 6) {
            mLmvSwitchMode.setVisibility(View.VISIBLE);
            mLmvSwitchMode.setOnClickListener(this);
        }
    }
}