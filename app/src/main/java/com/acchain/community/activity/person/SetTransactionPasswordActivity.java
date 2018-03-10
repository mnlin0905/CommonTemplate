package com.acchain.community.activity.person;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.SetTransactionPasswordContract;
import com.acchain.community.presenter.SetTransactionPasswordPresenter;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.util.EncodeUtils;
import com.acchain.community.util.RegexConst;
import com.acchain.community.util.SmsConst;
import com.acchain.community.view.RandomCodeTextView;
import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * function---- SetTransactionPasswordActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/19 12:07:12 (+0000).
 */
@Route(path = ARouterConst.Activity_SetTransactionPasswordActivity, extras = ARouterConst.FLAG_PHONE | ARouterConst.FLAG_LOGIN)
public class SetTransactionPasswordActivity extends BaseActivity<SetTransactionPasswordPresenter> implements SetTransactionPasswordContract.View {

    @BindView(R.id.et_password_one)
    EditText mEtPasswordOne;
    @BindView(R.id.et_password_two)
    EditText mEtPasswordTwo;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.et_random_code)
    EditText mEtRandomCode;
    @BindView(R.id.rctv_randomCode)
    RandomCodeTextView mRctvRandomCode;
    @BindView(R.id.bt_submit)
    Button mBtSubmit;

    /**
     * 成员变量
     */
    private String passwordTwo;
    private String passwordOne;
    private String randomCode;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_set_transaction_password;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //设置显示信息
        mTvPhone.setText(BasePresenter.singleAccountBean.getMobile());
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @OnClick({R.id.rctv_randomCode, R.id.bt_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rctv_randomCode://获取验证码
                if (verify_password()) {
                    mPresenter.sendMessage(BasePresenter.singleAccountBean.getMobile(), SmsConst.TAG_SET_PAY_PWD, null);
                }
                break;
            case R.id.bt_submit://设置交易密码
                if (verify_submit()) {
                    mPresenter.setPayPwd(DefaultPreferenceUtil.getInstance().getToken(), EncodeUtils.encode(passwordTwo, EncodeUtils.SHA_256), EncodeUtils.encode(passwordOne, EncodeUtils.SHA_256), randomCode);
                }
                break;
        }
    }

    /**
     * @return true 表示信息验证通过
     */
    private boolean verify_password() {
        passwordOne = mEtPasswordOne.getText().toString();
        passwordTwo = mEtPasswordTwo.getText().toString();
        if(TextUtils.isEmpty(passwordOne)){
            showToast("请输入交易密码");
            return false;
        }
        if(TextUtils.isEmpty(passwordTwo)){
            showToast("请确认交易密码");
            return false;
        }
        if (!passwordOne.equals(passwordTwo)) {
            showToast("两次输入的交易密码不同");
            return false;
        }
        if (!passwordOne.matches(RegexConst.REGEX_TRANSACTION_PASSWORD)) {
            showToast("交易密码应为6位数字");
            return false;
        }
        return true;
    }

    /**
     * @return true 表示信息验证通过
     */
    private boolean verify_submit() {
        randomCode = mEtRandomCode.getText().toString();
        if (!randomCode.matches(RegexConst.REGEX_RANDOM_NUMBER_6)) {
            showToast("验证码格式错误");
            return false;
        }
        return true;
    }

    @Override
    public void sendMessageFinish() {
        super.sendMessageFinish();
        mRctvRandomCode.start();
    }

    @Override
    public void setPayPwdFinish() {
        showToast("交易密码设置成功");
        finish();
    }
}