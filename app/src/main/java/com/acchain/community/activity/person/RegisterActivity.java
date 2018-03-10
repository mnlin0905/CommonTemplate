package com.acchain.community.activity.person;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.base.BaseApplication;
import com.acchain.community.contract.RegisterContract;
import com.acchain.community.presenter.RegisterPresenter;
import com.acchain.community.util.EncodeUtils;
import com.acchain.community.util.RegexConst;
import com.acchain.community.util.SmsConst;
import com.acchain.community.view.RandomCodeTextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

import static com.acchain.community.util.EncodeUtils.SHA_256;

/**
 * function---- RegisterActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/09 07:44:40 (+0000).
 */
@Route(path = ARouterConst.Activity_RegisterActivity)
public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterContract.View {

    @BindView(R.id.et_account)
    EditText mEtAccount;
    @BindView(R.id.tv_picture_code)
    EditText mTvPictureCode;
    @BindView(R.id.iv_picture_code)
    ImageView mIvPictureCode;
    @BindView(R.id.ed_randomCode)
    EditText mEdRandomCode;
    @BindView(R.id.et_password_one)
    EditText mEtPasswordOne;
    @BindView(R.id.et_password_two)
    EditText mEtPasswordTwo;
    @BindView(R.id.bt_register)
    Button mBtRegister;
    @BindView(R.id.rctv_photo_email_code)
    RandomCodeTextView mRctvPhotoEmailCode;

    /**
     * 成员变量
     */
    private String account;
    private String randomCode;
    private String pictureCode;
    private int registerType;
    private String passwordOne;
    private String passwordTwo;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //加载图形验证码
        Glide.with(this)
                .load(BaseApplication.app.getBaseNetUrl() + "randomCode/getImgCode")
                .apply(new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(mIvPictureCode);

    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @OnClick({R.id.rctv_photo_email_code, R.id.bt_register, R.id.iv_picture_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rctv_photo_email_code://获取手机/邮箱验证码
                if ((registerType = verity_account()) == 1) {
                    mPresenter.sendMessage(account, SmsConst.TAG_REGISTER, pictureCode);
                } else if (registerType == 2) {
                    mPresenter.sendEmail(account, SmsConst.TAG_REGISTER, pictureCode);
                }
                break;
            case R.id.bt_register://注册/先验证随机码
                if (verity_register()) {
                    mPresenter.checkCode(account, registerType == 1 ? SmsConst.TAG_REGISTER : SmsConst.TAG_REGISTER, randomCode);
                }
                break;
            case R.id.iv_picture_code://刷新一次图片二维码
                Glide.with(this).clear(mIvPictureCode);
                Glide.with(this)
                        .load(getResources().getString(R.string.base_net_address) + "randomCode/getImgCode")
                        .apply(new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE))
                        .into(mIvPictureCode);
                break;
        }
    }

    /**
     * @return 验证帐号类型
     * <p>
     * 1: 手机
     * 2: 邮箱
     * -1: 出错
     */
    private int verity_account() {
        account = mEtAccount.getText().toString();
        pictureCode = mTvPictureCode.getText().toString();
        if (TextUtils.isEmpty(account)) {
            showToast("请输入邮箱或手机号");
            return -1;
        }
        int resultCode = -1;
        if (account.matches(RegexConst.REGEX_PHONE)) {
            resultCode = 1;
        }
        if (account.matches(RegexConst.REGEX_EMAIL)) {
            resultCode = 2;
        }
        if (resultCode == -1) {
            showToast("帐号格式错误");
            return -1;
        }
        if (TextUtils.isEmpty(pictureCode)) {
            showToast("请输入图形验证码");
            return -1;
        }
        if (!pictureCode.matches(RegexConst.REGEX_RANDOM_TEXT_4)) {
            showToast("图形验证码格式错误");
            return -1;
        }

        return resultCode;
    }

    /**
     * 验证帐号密码验证码等是否可行
     *
     * @return true表示数据格式无误
     */
    private boolean verity_register() {
        if ((registerType = verity_account()) == -1) {
            return false;
        }
        randomCode = mEdRandomCode.getText().toString();
        passwordOne = mEtPasswordOne.getText().toString();
        passwordTwo = mEtPasswordTwo.getText().toString();
        if (TextUtils.isEmpty(randomCode)) {
            showToast(String.format(Locale.CHINA, "请输入%s验证码", registerType == 1 ? "短信" : "邮箱"));
            return false;
        }
        if (!randomCode.matches(RegexConst.REGEX_RANDOM_NUMBER_6)) {
            showToast(String.format(Locale.CHINA, "%s验证码格式错误", registerType == 1 ? "短信" : "邮箱"));
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
    public void sendMessageFinish() {
        super.sendMessageFinish();
        mRctvPhotoEmailCode.start();
    }

    @Override
    public void sendEmailFinish() {
        super.sendEmailFinish();
        mRctvPhotoEmailCode.start();
    }

    @Override
    public void checkCodeFinish() {
        mPresenter.newUser(account, EncodeUtils.encode(passwordOne, SHA_256), randomCode);
    }

    @Override
    public void newUserFinish() {
        showToast("注册成功,请重新登录");
        ARouter.getInstance().build(ARouterConst.Activity_LoginActivity).navigation();
    }

}