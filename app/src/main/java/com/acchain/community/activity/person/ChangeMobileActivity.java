package com.acchain.community.activity.person;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.bean.GetAccountBean;
import com.acchain.community.contract.ChangeMobileContract;
import com.acchain.community.presenter.ChangeMobilePresenter;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.util.RegexConst;
import com.acchain.community.util.SmsConst;
import com.acchain.community.view.RandomCodeTextView;
import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * function---- ChangeMobileActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/20 07:24:09 (+0000).
 */
@Route(path = ARouterConst.Activity_ChangeMobileActivity)
public class ChangeMobileActivity extends BaseActivity<ChangeMobilePresenter> implements ChangeMobileContract.View {
    @BindView(R.id.et_transaction_password)
    EditText mEtTransactionPassword;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_randomCode)
    EditText mEtRandomCode;
    @BindView(R.id.rctv_photo_email_code)
    RandomCodeTextView mRctvPhotoEmailCode;

    /**
     * 参数
     */
    private String transactionPassword;
    private String phone;
    private String randomCode;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_change_mobile;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @OnClick({R.id.rctv_photo_email_code, R.id.bt_bind})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rctv_photo_email_code://验证码
                if(verity_account()){
                    mPresenter.sendMessage(phone, SmsConst.TAG_BOUND_MOBILE,null);
                }
                break;
            case R.id.bt_bind://绑定手机号
                if(verity_random()){
                    mPresenter.checkCode(phone,SmsConst.TAG_BOUND_MOBILE,randomCode);
                }
                break;
        }
    }

    /**
     * 验证通过
     */
    private boolean verity_account() {
        transactionPassword = mEtTransactionPassword.getText().toString();
        phone = mEtPhone.getText().toString();
        if(TextUtils.isEmpty(transactionPassword)){
            showToast("请输入交易密码");
            return false;
        }
        if (!transactionPassword.matches(RegexConst.REGEX_TRANSACTION_PASSWORD)) {
            showToast("交易密码格式错误");
            return false;
        }
        if(TextUtils.isEmpty(phone)){
            showToast("请输入要绑定的手机号");
            return false;
        }
        if (!phone.matches(RegexConst.REGEX_PHONE)) {
            showToast("手机号格式错误");
            return false;
        }
        return true;
    }


    /**
     * 验证通过
     */
    private boolean verity_random() {
        randomCode = mEtRandomCode.getText().toString();
        if(!verity_account()){
            return false;
        }
        if(TextUtils.isEmpty(randomCode)){
            showToast("请输入手机验证码");
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
        mRctvPhotoEmailCode.start();
    }

    @Override
    public void checkCodeFinish() {
        super.checkCodeFinish();
        mPresenter.updateEmailOrMobile(DefaultPreferenceUtil.getInstance().getToken(),phone,transactionPassword,randomCode);
    }

    @Override
    public void updateEmailOrMobileFinish() {
        super.updateEmailOrMobileFinish();
        mPresenter.getAccount(DefaultPreferenceUtil.getInstance().getToken());
    }

    @Override
    public void getAccountFinish(GetAccountBean accountBean) {
        super.getAccountFinish(accountBean);
        showToast("手机号修改成功");
        finish();
    }

    class  TTTT{
        public static final int wqwqw=1;
        public static final String aaa="121";
        public String afsdfsfaa="121";
    }
}