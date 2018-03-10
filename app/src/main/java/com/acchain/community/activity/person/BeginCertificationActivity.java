package com.acchain.community.activity.person;

import android.content.Intent;
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
import com.acchain.community.bean.GetAccountBean;
import com.acchain.community.contract.BeginCertificationContract;
import com.acchain.community.presenter.BeginCertificationPresenter;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.util.RegexConst;
import com.acchain.community.util.SmsConst;
import com.acchain.community.view.LineMenuView;
import com.acchain.community.view.RandomCodeTextView;
import com.acchain.community.window.CardTypePickerViewDialog;
import com.acchain.community.window.CountryPickerViewDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import butterknife.OnClick;

import static com.acchain.community.util.Const.KEY_PICTURE_MULTIPLE;
import static com.acchain.community.util.Const.KEY_PICTURE_NEGATIVE;
import static com.acchain.community.util.Const.KEY_PICTURE_POSITIVE;
import static com.acchain.community.util.Const.TYPE_ID_CARD_FOREIGN;
import static com.acchain.community.util.Const.TYPE_ID_CARD_IDENTIFICATION;
import static com.acchain.community.util.Const.TYPE_ID_CARD_POLICE;
import static com.acchain.community.util.Const.VALUE_POSITION_NULL;

/**
 * function---- BeginCertificationActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/17 03:18:45 (+0000).
 */
@Route(path = ARouterConst.Activity_BeginCertificationActivity,extras = ARouterConst.FLAG_LOGIN)
public class BeginCertificationActivity extends BaseActivity<BeginCertificationPresenter> implements BeginCertificationContract.View, LineMenuView.LineMenuListener, CardTypePickerViewDialog.onCardTypePickerViewListener, CountryPickerViewDialog.onCountryPickerViewListener {

    /**
     * 请求码,用于获取需要上传的证件路径
     */
    private final int REQUEST_CODE = 1111;

    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_card_number)
    EditText mEtCardNumber;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_verify_code)
    EditText mEtVerifyCode;
    @BindView(R.id.rctv_get_code)
    RandomCodeTextView mRctvGetCode;
    @BindView(R.id.bt_take_out)
    Button mBtTakeOut;
    @BindView(R.id.lmv_location)
    LineMenuView mLmvLocation;
    @BindView(R.id.lmv_card_type)
    LineMenuView mLmvCardType;
    @BindView(R.id.lmv_upload_photo)
    LineMenuView mLmvUploadPhoto;

    /**
     * 存储的图片位置
     * <p>
     * 默认为null
     * <p>
     * 如果都有值时,表示上传图片成功
     */
    private String picture_positive;
    private String picture_negative;
    private String picture_multiple;

    /**
     * 国家/地区
     */
    private String country;

    /**
     * 身份验证类型
     */
    private int cardType = VALUE_POSITION_NULL;

    /**
     * 实名认证信息
     */
    private String name;
    private String cardNumber;
    private String phone;
    private String verifyCode;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_begin_certification;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //当当前账号是手机号登陆的状态下，实名认证这里的手机号应该是不可输入直接在后台取出的
        if(DefaultPreferenceUtil.getInstance().hasLogin()&& !TextUtils.isEmpty(BasePresenter.singleAccountBean.getMobile())){
            mEtPhone.setText(BasePresenter.singleAccountBean.getMobile());
            mEtPhone.setEnabled(false);
        }
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @OnClick({R.id.rctv_get_code, R.id.bt_take_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rctv_get_code://  获取验证码
                if (verity_info()) {
                    mPresenter.sendMessage(phone, SmsConst.TAG_VERIFICATION, null);
                }
                break;
            case R.id.bt_take_out://验证随机码,成功后直接进行实名认证
                if (verity_random()) {
                    mPresenter.checkCode(phone, SmsConst.TAG_VERIFICATION, verifyCode);
                }
                break;
        }
    }

    @Override
    public boolean performClickLeft(TextView v) {
        return false;
    }

    @Override
    public boolean performClickRight(TextView v) {
        return false;
    }

    @Override
    public void performSelf(LineMenuView v) {
        int position = (int) v.getTag(R.id.LINE_MENU_VIEW_TAG_POSITION);

        switch (position) {
            case 0:// 2018/1/17 选择国家地区
                CountryPickerViewDialog.getInstance(this).setOnPickerViewListener(this).show();
                break;
            case 1:// 2018/1/17 选择证件类型
                if (!CardTypePickerViewDialog.getInstance(this).setOnPickerViewListener(this).show()) {
                    showToast("抱歉!无法选择证件");
                }
                break;
            case 2:// 选择证件照片
                ARouter.getInstance()
                        .build(ARouterConst.Activity_UploadCertificationPhotoActivity)
                        .withString(KEY_PICTURE_POSITIVE, picture_positive)
                        .withString(KEY_PICTURE_NEGATIVE, picture_negative)
                        .withString(KEY_PICTURE_MULTIPLE, picture_multiple)
                        .navigation(this, REQUEST_CODE);
                break;
        }
    }

    @Override
    public void onTypePickerDismiss() {

    }

    @Override
    public void sendMessageFinish() {
        super.sendMessageFinish();
        mRctvGetCode.start();
    }

    @Override
    public void onTypePickerFinish(int position, String type) {
        cardType = position;

        mLmvCardType.setBrief(type);
    }

    @Override
    public void checkCodeFinish() {
        super.checkCodeFinish();
        mPresenter.verified(DefaultPreferenceUtil.getInstance().getToken(), country, name, cardType, cardNumber, phone, verifyCode,
                picture_positive, picture_negative, picture_multiple);
    }

    @Override
    public void verifiedFinish() {
       mPresenter.getAccount(DefaultPreferenceUtil.getInstance().getToken());
    }

    @Override
    public void getAccountFinish(GetAccountBean accountBean) {
        super.getAccountFinish(accountBean);
        showToast("实名认证申请成功,请耐心等待");
        finish();
    }

    /**
     * @return 验证是否通过
     */
    private boolean verity_info() {
        name = mEtName.getText().toString();
        cardNumber = mEtCardNumber.getText().toString();
        phone = mEtPhone.getText().toString();

        if (country == null) {
            showToast("请选择国家或地区");
            return false;
        }
        if (TextUtils.isEmpty(name)) {
            showToast("请输入姓名");
            return false;
        }
        if (!name.matches(RegexConst.REGEX_VERITY_NAME)) {
            showToast("姓名格式错误");
            return false;
        }
        if (cardType ==VALUE_POSITION_NULL) {
            showToast("请选择证件类型");
            return false;
        }
        if(TextUtils.isEmpty(cardNumber)){
            showToast("请输入证件号码");
            return false;
        }
        if (cardType == TYPE_ID_CARD_IDENTIFICATION && !cardNumber.matches(RegexConst.REGEX_ID_CARD_NUMBER)) {
            showToast("身份证号码格式错误");
            return false;
        }
        if (cardType == TYPE_ID_CARD_POLICE && !cardNumber.matches(RegexConst.REGEX_POLICE_CARD_NUMBER)) {
            showToast("军官证号码格式错误");
            return false;
        }
        if (cardType == TYPE_ID_CARD_FOREIGN && !cardNumber.matches(RegexConst.REGEX_FOREIGN_CARD_NUMBER)) {
            showToast("护照号码格式错误");
            return false;
        }
        if (TextUtils.isEmpty(picture_multiple) || TextUtils.isEmpty(picture_negative) || TextUtils.isEmpty(picture_positive)) {
            showToast("请上传必须的证件照片");
            return false;
        }
        if(TextUtils.isEmpty(phone)){
            showToast("请输入手机号");
            return false;
        }
        if (!phone.matches(RegexConst.REGEX_PHONE)) {
            showToast("手机号格式错误");
            return false;
        }

        return true;
    }

    /**
     * @return 验证是否通过
     */
    private boolean verity_random() {
        if (!verity_info()) {
            return false;
        }
        verifyCode = mEtVerifyCode.getText().toString();
        if (!verifyCode.matches(RegexConst.REGEX_RANDOM_NUMBER_6)) {
            showToast("验证码输入错误");
            return false;
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            picture_positive = data.getStringExtra(KEY_PICTURE_POSITIVE);
            picture_negative = data.getStringExtra(KEY_PICTURE_NEGATIVE);
            picture_multiple = data.getStringExtra(KEY_PICTURE_MULTIPLE);

            mLmvUploadPhoto.setBrief("证件已选择");
        }
    }

    @Override
    public void onCountryPickerDismiss() {

    }

    @Override
    public void onCountryPickerFinish(CountryPickerViewDialog.Country country) {
        this.country = country.getChineseName();
        mLmvLocation.setBrief(this.country);
    }
}