package com.acchain.community.activity.person;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.bean.GetAccountBean;
import com.acchain.community.contract.LoginContract;
import com.acchain.community.presenter.LoginPresenter;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.util.EncodeUtils;
import com.acchain.community.util.RegexConst;
import com.acchain.community.util.SmsConst;
import com.acchain.community.view.RandomCodeTextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * function---- LoginActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/09 05:59:52 (+0000).
 */
@Route(path = ARouterConst.Activity_LoginActivity)
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View, TabLayout.OnTabSelectedListener {
    @BindView(R.id.tl_type)
    TabLayout mTlType;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.ed_randomCode)
    EditText mEdRandomCode;
    @BindView(R.id.rctv_randomCode)
    RandomCodeTextView mRctvRandomCode;
    @BindView(R.id.linear_loginType1)
    LinearLayout mLinearLoginType1;
    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.iv_pwd_show_hide)
    ImageView mIvPwdShowHide;
    @BindView(R.id.linear_loginType2)
    LinearLayout mLinearLoginType2;
    @BindView(R.id.bt_login)
    Button mBtLogin;
    @BindView(R.id.register)
    TextView mRegister;
    @BindView(R.id.forgetPwd)
    TextView mForgetPwd;

    /**
     * 是否为手机号码动态登录
     * <p>
     * 默认为true
     */
    boolean isLoginByRandom = true;

    /**
     * 成员变量
     */
    private String phoneStr;
    private String phoneRandomCode;
    private String username;
    private String password;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //登录类型
        mTlType.addTab(mTlType.newTab().setText(R.string.loginType1));
        mTlType.addTab(mTlType.newTab().setText(R.string.loginType2));
        mTlType.addOnTabSelectedListener(this);
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @Override
    public void doLoginFinish() {
        dynamicLoginFinish();
    }

    @Override
    public void dynamicLoginFinish() {
        //登录成功,更新账户数据
        mPresenter.getAccount(DefaultPreferenceUtil.getInstance().getToken());
    }

    @Override
    public void getAccountFinish(GetAccountBean accountBean) {
        super.getAccountFinish(accountBean);
        if (accountBean != null) {
            finish();
        } else {
            showToast("无法刷新账户信息");
        }
    }

    @Override
    public void checkCodeFinish() {
        //验证码验证通过
        mPresenter.dynamicLogin(phoneStr, phoneRandomCode);
    }

    @Override
    public void sendMessageFinish() {
        super.sendMessageFinish();
        mRctvRandomCode.start();
    }

    /**
     * @return true表示验证通过
     */
    private boolean verify_account() {
        phoneStr = mEtPhone.getText().toString();
        if(TextUtils.isEmpty(phoneStr)){
            showToast("请输入手机号码");
            return false;
        }
        if (!phoneStr.matches(RegexConst.REGEX_PHONE)) {
            showToast("手机号格式错误");
            return false;
        }
        return true;
    }

    /**
     * @return true表示动态登录的信息验证通过
     */
    private boolean verify_dynamicLogin() {
        if (!verify_account()) {
            return false;
        }
        phoneRandomCode = mEdRandomCode.getText().toString();
        if(TextUtils.isEmpty(phoneRandomCode)){
            showToast("请输入验证码");
            return false;
        }
        if (!phoneRandomCode.matches(RegexConst.REGEX_RANDOM_NUMBER_6)) {
            showToast("验证码格式错误");
            return false;
        }
        return true;
    }

    /**
     * @return true 表示密码登录的信息验证通过
     */
    private boolean verify_doLogin() {
        username = mEtUsername.getText().toString();
        password = mEtPassword.getText().toString();
        if(TextUtils.isEmpty(username)){
            showToast("请输入帐号");
            return false;
        }
        if (!username.matches(RegexConst.REGEX_PHONE) && !username.matches(RegexConst.REGEX_EMAIL)) {
            showToast("帐号格式错误");
            return false;
        }
        if(TextUtils.isEmpty(password)){
            showToast("请输入密码");
            return false;
        }
        if (!password.matches(RegexConst.REGEX_PASSWORD)) {
            showToast("密码格式错误");
            return false;
        }
        return true;
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        //动态登录 或者 密码登录
        isLoginByRandom = tab.getPosition() == 0;
        mForgetPwd.setVisibility(isLoginByRandom ? View.GONE : View.VISIBLE);
        mLinearLoginType1.setVisibility(isLoginByRandom ? View.VISIBLE : View.GONE);
        mLinearLoginType2.setVisibility(isLoginByRandom ? View.GONE : View.VISIBLE);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @OnClick({R.id.rctv_randomCode, R.id.iv_pwd_show_hide, R.id.bt_login, R.id.register, R.id.forgetPwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rctv_randomCode://获取手机验证码
                if (verify_account()) {
                    mPresenter.sendMessage(phoneStr, SmsConst.TAG_LOGIN, null);
                }
                break;
            case R.id.iv_pwd_show_hide://密码可见性
                view.setSelected(!view.isSelected());
                mEtPassword.setTransformationMethod(view.isSelected() ? new HideReturnsTransformationMethod() : new PasswordTransformationMethod());
                break;
            case R.id.bt_login://动态登录(手机验证码)/密码登录
                if (isLoginByRandom) {
                    if (verify_dynamicLogin()) {
                        mPresenter.checkCode(phoneStr, SmsConst.TAG_LOGIN, phoneRandomCode);
                    }
                } else if (verify_doLogin()) {
                    mPresenter.doLogin(username, EncodeUtils.encode(password, EncodeUtils.SHA_256));
                }

                break;
            case R.id.register://注册
                ARouter.getInstance().build(ARouterConst.Activity_RegisterActivity).navigation();
                break;
            case R.id.forgetPwd://忘记密码
                ARouter.getInstance().build(ARouterConst.Activity_ForgetPasswordActivity).navigation();
                break;
        }
    }

    /**
     * 屏蔽返回键,不登陆则会退出应用
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}