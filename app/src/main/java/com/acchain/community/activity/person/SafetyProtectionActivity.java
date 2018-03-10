package com.acchain.community.activity.person;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.SafetyProtectionContract;
import com.acchain.community.presenter.SafetyProtectionPresenter;
import com.acchain.community.view.LineMenuView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;

/**
 * function---- SafetyProtectionActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/19 11:56:31 (+0000).
 */
@Route(path = ARouterConst.Activity_SafetyProtectionActivity, extras = ARouterConst.FLAG_LOGIN)
public class SafetyProtectionActivity extends BaseActivity<SafetyProtectionPresenter> implements SafetyProtectionContract.View, LineMenuView.LineMenuListener {

    @BindView(R.id.lmv_login_password)
    LineMenuView mLmvLoginPassword;
    @BindView(R.id.lmv_transaction_password)
    LineMenuView mLmvTransactionPassword;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_safety_protection;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //如果设置过了交易密码,则显示修改,如果未设置,则显示设置
        mLmvTransactionPassword.setBrief(BasePresenter.singleAccountBean.isPayPwd() ? "重置" : "设置");
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_service, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO: 2018/1/19 客服
        return true;
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
        int position = (int) v.getTag(R.id.LINE_MENU_VIEW_TAG_POSITION);
        switch (position) {
            case 0://修改登录密码
                ARouter.getInstance().build(ARouterConst.Activity_ChangePasswordActivity).navigation();
                break;
            case 1://交易密码
                ARouter.getInstance().build(ARouterConst.Activity_SelectChangeTransPasswordTypeActivity).navigation();
                break;
            case 2://手势
            case 3://指纹
            case 4://刷脸
                showToast("暂不支持,敬请期待");
                break;
        }
    }
}