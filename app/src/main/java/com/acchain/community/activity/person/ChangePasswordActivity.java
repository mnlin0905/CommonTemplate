package com.acchain.community.activity.person;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.ChangePasswordContract;
import com.acchain.community.presenter.ChangePasswordPresenter;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.util.EncodeUtils;
import com.acchain.community.util.RegexConst;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * function---- ChangePasswordActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/19 11:57:04 (+0000).
 */
@Route(path = ARouterConst.Activity_ChangePasswordActivity)
public class ChangePasswordActivity extends BaseActivity<ChangePasswordPresenter> implements ChangePasswordContract.View {

    @BindView(R.id.et_old_password)
    EditText mEtOldPassword;
    @BindView(R.id.et_password_one)
    EditText mEtPasswordOne;
    @BindView(R.id.et_password_two)
    EditText mEtPasswordTwo;
    @BindView(R.id.bt_submit)
    Button mBtSubmit;


    /**
     * 成员变量
     */
    private String oldPassword;
    private String passwordOne;
    private String passwordTwo;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_change_password;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    /**
     * @return true if ok
     */
    private boolean verity_password() {
        oldPassword = mEtOldPassword.getText().toString();
        passwordOne = mEtPasswordOne.getText().toString();
        passwordTwo = mEtPasswordTwo.getText().toString();
        if(TextUtils.isEmpty(oldPassword)){
            showToast("请输入原密码");
            return false;
        }
        if (!oldPassword.matches(RegexConst.REGEX_PASSWORD)) {
            showToast("旧密码错误");
            return false;
        }
        if(TextUtils.isEmpty(passwordOne)){
            showToast("请输入登录密码");
            return false;
        }
        if(TextUtils.isEmpty(passwordTwo)){
            showToast("请确认登录密码");
            return false;
        }
        if (!passwordOne.equals(passwordTwo)) {
            showToast("两次输入的新密码不同");
            return false;
        }
        if (!passwordOne.matches(RegexConst.REGEX_PASSWORD)) {
            showToast("新密码须为6-18位有效字符");
            return false;
        }
        return true;
    }

    @Override
    public void updatePwdFinish() {
        showToast("密码修改成功,请重新登陆");
        finish();
        ARouter.getInstance().build(ARouterConst.Activity_LoginActivity).navigation();
    }

    @OnClick(R.id.bt_submit)
    public void onViewClicked() {
        if (verity_password()) {
            mPresenter.updatePwd(DefaultPreferenceUtil.getInstance().getToken(),
                    EncodeUtils.encode(oldPassword, EncodeUtils.SHA_256),
                    EncodeUtils.encode(passwordOne, EncodeUtils.SHA_256));
        }
    }
}