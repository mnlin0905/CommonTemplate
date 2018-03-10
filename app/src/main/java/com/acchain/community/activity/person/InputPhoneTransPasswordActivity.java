package com.acchain.community.activity.person;

import android.os.Bundle;
import android.widget.EditText;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.InputPhoneTransPasswordContract;
import com.acchain.community.presenter.InputPhoneTransPasswordPresenter;
import com.acchain.community.util.RegexConst;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import butterknife.OnClick;

import static com.acchain.community.util.Const.KEY_PHONE_NUMBER;

/**
 * function---- InputPhoneTransPasswordActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/31 11:32:52 (+0000).
 */
@Route(path = ARouterConst.Activity_InputPhoneTransPasswordActivity)
public class InputPhoneTransPasswordActivity extends BaseActivity<InputPhoneTransPasswordPresenter> implements InputPhoneTransPasswordContract.View {

    @BindView(R.id.et_phone)
    EditText mEtPhone;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_input_phone_trans_password;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @OnClick(R.id.bt_next)
    public void onViewClicked() {
        String phone = mEtPhone.getText().toString();
        if (!phone.matches(RegexConst.REGEX_PHONE)) {
            showToast("手机号格式有误");
            return;
        }

        //携带手机号跳转
        ARouter.getInstance()
                .build(ARouterConst.Activity_UploadPictureTransPwdActivity)
                .withString(KEY_PHONE_NUMBER,phone)
                .navigation();
    }

}