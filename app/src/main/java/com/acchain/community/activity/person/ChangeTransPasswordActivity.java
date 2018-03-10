package com.acchain.community.activity.person;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.ChangeTransPasswordContract;
import com.acchain.community.presenter.ChangeTransPasswordPresenter;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.util.EncodeUtils;
import com.acchain.community.util.RegexConst;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import butterknife.OnClick;

import static com.acchain.community.util.Const.KEY_OLD_PASSWORD;
import static com.acchain.community.util.Const.KEY_RANDOM_CODE;
import static com.acchain.community.util.Const.KEY_STAFF_SERVICE_ID;

/**
 * function---- ChangeTransPasswordActivity
 * 修改交易密码界面
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/31 07:28:27 (+0000).
 */
@Route(path = ARouterConst.Activity_ChangeTransPasswordActivity)
public class ChangeTransPasswordActivity extends BaseActivity<ChangeTransPasswordPresenter> implements ChangeTransPasswordContract.View {
    @BindView(R.id.et_password_one)
    EditText mEtPasswordOne;
    @BindView(R.id.et_password_two)
    EditText mEtPasswordTwo;
    @BindView(R.id.bt_submit)
    Button mBtSubmit;

    /**
     * 跳转状态位
     */
    @Autowired(required = false, name = KEY_STAFF_SERVICE_ID)
    int staffServiceID = -1;
    @Autowired(required = false, name = KEY_RANDOM_CODE)
    String verityRandom;
    @Autowired(required = false, name = KEY_OLD_PASSWORD)
    String oldPassword;

    /**
     * 成员变量
     */
    private String passwordTwo;
    private String passwordOne;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_change_trans_password;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        setToolbarNavText(toolbar, "关闭", R.color.white);
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @OnClick({R.id.bt_submit})
    public void onViewClicked(View view) {
        if (!verify_password()) {
            return;
        }

        if (staffServiceID != -1) {
            //人工审核
            mPresenter.changePayPwdForArtificial(DefaultPreferenceUtil.getInstance().getToken(), EncodeUtils.encode(passwordOne, EncodeUtils.SHA_256));
        } else if (verityRandom != null) {
            //短信验证码
            mPresenter.setPayPwd(DefaultPreferenceUtil.getInstance().getToken(),
                    EncodeUtils.encode(passwordTwo, EncodeUtils.SHA_256),
                    EncodeUtils.encode(passwordOne, EncodeUtils.SHA_256),
                    verityRandom);
        } else if (oldPassword != null) {
            //旧密码
            mPresenter.changePayPwd(DefaultPreferenceUtil.getInstance().getToken(),
                    EncodeUtils.encode(oldPassword, EncodeUtils.SHA_256),
                    EncodeUtils.encode(passwordOne, EncodeUtils.SHA_256));
        } else {
            showToast("系统错误:未知的审核方式");
        }
    }

    /**
     * @return true 表示信息验证通过
     */
    private boolean verify_password() {
        passwordOne = mEtPasswordOne.getText().toString();
        passwordTwo = mEtPasswordTwo.getText().toString();
        if(TextUtils.isEmpty(passwordOne)){
            showToast("请输入密码");
            return false;
        }
        if(TextUtils.isEmpty(passwordTwo)){
            showToast("请确认登录密码");
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
     * 人工审核后修改交易密码
     */
    @Override
    public void changePayPwdForArtificialFinish() {
        //需要重置状态位
        mPresenter.cancelArtificial(DefaultPreferenceUtil.getInstance().getToken(), staffServiceID);
    }

    /**
     * 设置交易密码
     */
    @Override
    public void setPayPwdFinish() {
        super.setPayPwdFinish();
        gotoShowResult();
    }

    /**
     * 通过旧密码修改原密码
     */
    @Override
    public void changePayPwdFinish() {
        super.changePayPwdFinish();
        gotoShowResult();
    }

    /**
     * 重置状态位
     */
    @Override
    public void cancelArtificialFinish() {
        super.cancelArtificialFinish();
        gotoShowResult();
    }

    /**
     * 前往修改成功界面
     */
    private void gotoShowResult() {
        ARouter.getInstance().build(ARouterConst.Activity_ChangeTransSuccessActivity).navigation();
    }
}