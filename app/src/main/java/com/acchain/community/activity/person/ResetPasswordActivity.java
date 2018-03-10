package com.acchain.community.activity.person;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.ResetPasswordContract;
import com.acchain.community.presenter.ResetPasswordPresenter;
import com.acchain.community.util.EncodeUtils;
import com.acchain.community.util.RegexConst;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import butterknife.OnClick;

import static com.acchain.community.util.Const.KEY_ACCOUNT;
import static com.acchain.community.util.Const.KEY_RANDOM_CODE;

/**
 * function---- ResetPasswordActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/19 03:55:14 (+0000).
 */
@Route(path = ARouterConst.Activity_ResetPasswordActivity)
public class ResetPasswordActivity extends BaseActivity<ResetPasswordPresenter> implements ResetPasswordContract.View {
        @BindView(R.id.et_password_one)
    EditText mEtPasswordOne;
    @BindView(R.id.et_password_two)
    EditText mEtPasswordTwo;
    @BindView(R.id.bt_submit)
    Button mBtSubmit;

    /**
     * 成员变量
     */
    @Autowired(required = true, name = KEY_ACCOUNT)
    String account;
    @Autowired(required = true, name = KEY_RANDOM_CODE)
    String randomCode;

    private String passwordTwo;
    private String passwordOne;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_reset_transaction_password;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //验证传入信息
        if (account == null || randomCode == null) {
            showToast("必须传入帐号和验证码");
            finish();
        }
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }


    @OnClick(R.id.bt_submit)
    public void onViewClicked() {
        if (verify_reset()) {
            mPresenter.resetPwd(account, EncodeUtils.encode(passwordOne, EncodeUtils.SHA_256), randomCode, EncodeUtils.encode(passwordTwo, EncodeUtils.SHA_256));
        }
    }

    /**
     * @return true 表示信息验证通过
     */
    private boolean verify_reset() {
        passwordOne = mEtPasswordOne.getText().toString();
        passwordTwo = mEtPasswordTwo.getText().toString();
        if(TextUtils.isEmpty(passwordOne)){
            showToast("请输入登录密码");
            return false;
        }
        if(TextUtils.isEmpty(passwordTwo)){
            showToast("请确认登录密码");
            return false;
        }
        if (!passwordOne.equals(passwordTwo)) {
            showToast("两次输入的密码不同");
            return false;
        }
        if (!passwordOne.matches(RegexConst.REGEX_PASSWORD)) {
            showToast("密码须为6-18位有效字符");
            return false;
        }
        return true;
    }

    @Override
    public void resetPwdFinish() {
        showToast("密码重置成功,请重新登录");
        ARouter.getInstance().build(ARouterConst.Activity_LoginActivity).navigation();
    }
}