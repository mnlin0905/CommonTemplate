package com.acchain.community.activity.person;

import android.os.Bundle;
import android.widget.Button;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.InputOldTransPasswordContract;
import com.acchain.community.presenter.InputOldTransPasswordPresenter;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.util.EncodeUtils;
import com.acchain.community.util.RegexConst;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jungly.gridpasswordview.GridPasswordView;

import butterknife.BindView;
import butterknife.OnClick;

import static com.acchain.community.util.Const.KEY_OLD_PASSWORD;

/**
 * function---- InputOldTransPasswordActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/31 07:05:14 (+0000).
 */
@Route(path = ARouterConst.Activity_InputOldTransPasswordActivity)
public class InputOldTransPasswordActivity extends BaseActivity<InputOldTransPasswordPresenter> implements InputOldTransPasswordContract.View, GridPasswordView.OnPasswordChangedListener {

    @BindView(R.id.gpv_password)
    GridPasswordView mGpvPassword;
    @BindView(R.id.bt_next)
    Button mBtNext;

    private String password;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_input_old_trans_password;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mGpvPassword.setOnPasswordChangedListener(this);
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @OnClick(R.id.bt_next)
    public void onViewClicked() {
        password = mGpvPassword.getPassWord();
        if (!password.matches(RegexConst.REGEX_TRANSACTION_PASSWORD)) {
            showToast("密码格式错误");
            return;
        }

        mPresenter.changePayPwd(DefaultPreferenceUtil.getInstance().getToken(),EncodeUtils.encode(password,EncodeUtils.SHA_256),null);
    }

    @Override
    public void onTextChanged(String psw) {

    }

    @Override
    public void onInputFinish(String psw) {

    }

    @Override
    public void changePayPwdFinish() {
        super.changePayPwdFinish();

        // 修改交易密码,成功后结束finish,然后跳转修改密码
        ARouter.getInstance()
                .build(ARouterConst.Activity_ChangeTransPasswordActivity)
                .withString(KEY_OLD_PASSWORD, password)
                .navigation();
    }

}