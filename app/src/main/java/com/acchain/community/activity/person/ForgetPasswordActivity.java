package com.acchain.community.activity.person;

import android.os.Bundle;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.ForgetPasswordContract;
import com.acchain.community.presenter.ForgetPasswordPresenter;
import com.acchain.community.view.LineMenuView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;

import static com.acchain.community.util.Const.KEY_TYPE_FIND_LOGIN_PASSWORD;
import static com.acchain.community.util.Const.TYPE_FIND_LOGIN_PASSWORD_BY_EMAIL;
import static com.acchain.community.util.Const.TYPE_FIND_LOGIN_PASSWORD_BY_PHONE;

/**
 * function---- ForgetPasswordActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/19 01:55:53 (+0000).
 */
@Route(path = ARouterConst.Activity_ForgetPasswordActivity)
public class ForgetPasswordActivity extends BaseActivity<ForgetPasswordPresenter> implements ForgetPasswordContract.View, LineMenuView.LineMenuListener {

    @BindView(R.id.lmv_phone)
    LineMenuView mLmvPhone;
    @BindView(R.id.lmv_email)
    LineMenuView mLmvEmail;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

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
        ARouter.getInstance()
                .build(ARouterConst.Activity_FindPasswordActivity)
                .withInt(KEY_TYPE_FIND_LOGIN_PASSWORD, v == mLmvPhone ? TYPE_FIND_LOGIN_PASSWORD_BY_PHONE : TYPE_FIND_LOGIN_PASSWORD_BY_EMAIL)
                .navigation();
    }
}