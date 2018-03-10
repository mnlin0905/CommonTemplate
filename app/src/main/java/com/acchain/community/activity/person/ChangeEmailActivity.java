package com.acchain.community.activity.person;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.bean.GetAccountBean;
import com.acchain.community.contract.ChangeEmailContract;
import com.acchain.community.presenter.ChangeEmailPresenter;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.util.RegexConst;
import com.acchain.community.util.SmsConst;
import com.acchain.community.view.RandomCodeTextView;
import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * function---- ChangeEmailActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/20 07:25:21 (+0000).
 */
@Route(path = ARouterConst.Activity_ChangeEmailActivity)
public class ChangeEmailActivity extends BaseActivity<ChangeEmailPresenter> implements ChangeEmailContract.View {
    @BindView(R.id.tv_old_email)
    TextView mTvOldEmail;
    @BindView(R.id.et_transaction_password)
    EditText mEtTransactionPassword;
    @BindView(R.id.et_email)
    EditText mEtEmail;
    @BindView(R.id.et_randomCode)
    EditText mEtRandomCode;
    @BindView(R.id.rctv_photo_email_code)
    RandomCodeTextView mRctvPhotoEmailCode;

    /**
     * 参数
     */
    private String transactionPassword;
    private String email;
    private String randomCode;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_change_email;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //显示邮箱
        mTvOldEmail.setText(BasePresenter.singleAccountBean.getEmail().replaceAll("^(\\S{2}).*(@.*)$", "$1****$2"));
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @OnClick({R.id.rctv_photo_email_code, R.id.bt_bind})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rctv_photo_email_code://验证码
                if (verity_account()) {
                    mPresenter.sendEmail(email, SmsConst.TAG_UPDATE_EMAIL, null);
                }
                break;
            case R.id.bt_bind://修改邮箱
                if (verity_random()) {
                    mPresenter.checkCode(email, SmsConst.TAG_UPDATE_EMAIL, randomCode);
                }
                break;
        }
    }

    /**
     * 验证通过
     */
    private boolean verity_account() {
        transactionPassword = mEtTransactionPassword.getText().toString();
        email = mEtEmail.getText().toString();
        if(TextUtils.isEmpty(transactionPassword)){
            showToast("请输入交易密码");
            return false;
        }
        if (!transactionPassword.matches(RegexConst.REGEX_TRANSACTION_PASSWORD)) {
            showToast("交易密码格式错误");
            return false;
        }
        if(TextUtils.isEmpty(email)){
            showToast("请输入要绑定的邮箱");
            return false;
        }
        if (!email.matches(RegexConst.REGEX_EMAIL)) {
            showToast("邮箱格式错误");
            return false;
        }
        return true;
    }


    /**
     * 验证通过
     */
    private boolean verity_random() {
        randomCode = mEtRandomCode.getText().toString();
        if (!verity_account()) {
            return false;
        }
        if(TextUtils.isEmpty(randomCode)){
            showToast("请输入邮箱验证码");
            return false;
        }
        if (!randomCode.matches(RegexConst.REGEX_RANDOM_NUMBER_6)) {
            showToast("验证码格式错误");
            return false;
        }
        return true;
    }

    @Override
    public void sendEmailFinish() {
        super.sendEmailFinish();
        mRctvPhotoEmailCode.start();
    }

    @Override
    public void checkCodeFinish() {
        super.checkCodeFinish();
        mPresenter.updateEmailOrMobile(DefaultPreferenceUtil.getInstance().getToken(), email, transactionPassword, randomCode);
    }

    @Override
    public void updateEmailOrMobileFinish() {
        super.updateEmailOrMobileFinish();
        mPresenter.getAccount(DefaultPreferenceUtil.getInstance().getToken());
    }

    @Override
    public void getAccountFinish(GetAccountBean accountBean) {
        super.getAccountFinish(accountBean);
        showToast("邮箱修改成功");
        finish();
    }
}