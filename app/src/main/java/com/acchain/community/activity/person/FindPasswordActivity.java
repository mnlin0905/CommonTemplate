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
import com.acchain.community.contract.FindPasswordContract;
import com.acchain.community.presenter.FindPasswordPresenter;
import com.acchain.community.util.RegexConst;
import com.acchain.community.util.SmsConst;
import com.acchain.community.view.RandomCodeTextView;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import butterknife.OnClick;

import static com.acchain.community.util.Const.KEY_ACCOUNT;
import static com.acchain.community.util.Const.KEY_RANDOM_CODE;
import static com.acchain.community.util.Const.KEY_TYPE_FIND_LOGIN_PASSWORD;
import static com.acchain.community.util.Const.TYPE_FIND_LOGIN_PASSWORD_BY_EMAIL;
import static com.acchain.community.util.Const.TYPE_FIND_LOGIN_PASSWORD_BY_PHONE;

/**
 * function---- FindPasswordActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/19 02:31:05 (+0000).
 */
@Route(path = ARouterConst.Activity_FindPasswordActivity)
public class FindPasswordActivity extends BaseActivity<FindPasswordPresenter> implements FindPasswordContract.View {
    @BindView(R.id.et_account)
    EditText mEtAccount;
    @BindView(R.id.et_random_code)
    EditText mEtRandomCode;
    @BindView(R.id.rctv_randomCode)
    RandomCodeTextView mRctvRandomCode;
    @BindView(R.id.bt_reset)
    Button mBtReset;
    @BindView(R.id.tv_declare)
    TextView mTvDeclare;

    /**
     * 成员变量
     */
    @Autowired(name = KEY_TYPE_FIND_LOGIN_PASSWORD, required = true)
    int type;
    private String account;
    private String randomCode;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_find_password;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //验证传递的值
        if (type != TYPE_FIND_LOGIN_PASSWORD_BY_PHONE && type != TYPE_FIND_LOGIN_PASSWORD_BY_EMAIL) {
            showToast("未知的验证方式");
            finish();
            return;
        }

        //初始化界面信息
        mEtAccount.setHint(type == TYPE_FIND_LOGIN_PASSWORD_BY_PHONE ? "请输入您已绑定的手机号码" : "请输入您已绑定的邮箱号码");
        mEtRandomCode.setHint(type == TYPE_FIND_LOGIN_PASSWORD_BY_PHONE ? "请输入手机验证码" : "请输入邮箱验证码");
        mTvDeclare.setText(type == TYPE_FIND_LOGIN_PASSWORD_BY_PHONE ? "输入验证码，验证成功后即可重置密码" : "点击“获取验证码”系统会发送邮件到您的邮箱，验证成功后即可重置密码");
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @OnClick({R.id.rctv_randomCode, R.id.bt_reset})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rctv_randomCode://获取验证码
                if (verify_account()) {
                    if (type == TYPE_FIND_LOGIN_PASSWORD_BY_PHONE) {
                        mPresenter.sendMessage(account, SmsConst.TAG_RESET_PWD, null);
                    } else {
                        mPresenter.sendEmail(account, SmsConst.TAG_RESET_PWD, null);
                    }
                }
                break;
            case R.id.bt_reset://重置
                if (verify_reset()) {
                    mPresenter.checkCode(account, SmsConst.TAG_RESET_PWD, randomCode);
                }
                break;
        }
    }

    /**
     * @return true表示验证通过
     */
    private boolean verify_account() {
        account = mEtAccount.getText().toString();
        if (type == TYPE_FIND_LOGIN_PASSWORD_BY_PHONE) {
            if (TextUtils.isEmpty(account)) {
                showToast("请输入手机号");
                return false;
            }
            if (!account.matches(RegexConst.REGEX_PHONE)) {
                showToast("手机号格式错误");
                return false;
            }
        }
        if (type == TYPE_FIND_LOGIN_PASSWORD_BY_EMAIL) {
            if (TextUtils.isEmpty(account)) {
                showToast("请输入邮箱");
                return false;
            }
            if (!account.matches(RegexConst.REGEX_EMAIL)) {
                showToast("邮箱格式错误");
                return false;
            }
        }
        return true;
    }

    /**
     * @return true 表示信息验证通过
     */
    private boolean verify_reset() {
        if (!verify_account()) {
            return false;
        }
        randomCode = mEtRandomCode.getText().toString();
        if (TextUtils.isEmpty(randomCode)) {
            showToast("请输入验证码");
            return false;
        }
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
    public void sendEmailFinish() {
        super.sendEmailFinish();
        mRctvRandomCode.start();
    }

    @Override
    public void checkCodeFinish() {
        super.checkCodeFinish();
        ARouter.getInstance()
                .build(ARouterConst.Activity_ResetPasswordActivity)
                .withString(KEY_ACCOUNT, account)
                .withString(KEY_RANDOM_CODE, randomCode)
                .navigation();
    }
}