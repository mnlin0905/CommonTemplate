package com.acchain.community.activity.person;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.BindMobileContract;
import com.acchain.community.presenter.BindMobilePresenter;
import com.acchain.community.view.RandomCodeTextView;
import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * function---- BindMobileActivity
 * 绑定手机号,不能主动调用,只能在实名认证时候绑定
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/20 07:23:58 (+0000).
 */
@Route(path = ARouterConst.Activity_BindMobileActivity, extras = ARouterConst.FLAG_FORCE_ACCESS)
public class BindMobileActivity extends BaseActivity<BindMobilePresenter> implements BindMobileContract.View{
    @BindView(R.id.et_transaction_password)
    EditText mEtTransactionPassword;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_randomCode)
    EditText mEtRandomCode;
    @BindView(R.id.rctv_photo_email_code)
    RandomCodeTextView mRctvPhotoEmailCode;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_bind_mobile;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {}

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @OnClick({R.id.rctv_photo_email_code, R.id.bt_bind})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rctv_photo_email_code:
                break;
            case R.id.bt_bind:
                break;
        }
    }
}