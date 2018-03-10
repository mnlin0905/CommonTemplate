package com.acchain.community.activity.wallet;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.bean.AccountBalance;
import com.acchain.community.contract.TakeOutPlatformContract;
import com.acchain.community.presenter.TakeOutPlatformPresenter;
import com.acchain.community.util.ActivityUtil;
import com.acchain.community.util.Const;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.util.RegexConst;
import com.acchain.community.util.SmsConst;
import com.acchain.community.view.RandomCodeTextView;
import com.acchain.community.window.BigToast;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * function---- TakeOutPlatformActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/11 13:46:19 (+0000).
 */
@RuntimePermissions
@Route(path = ARouterConst.Activity_TakeOutPlatformActivity)
public class TakeOutPlatformActivity extends BaseActivity<TakeOutPlatformPresenter> implements TakeOutPlatformContract.View {

    @BindView(R.id.tv_currency_name)
    TextView mTvCurrencyName;
    @BindView(R.id.tv_currency_amount)
    TextView mTvCurrencyAmount;
    @BindView(R.id.et_address)
    EditText mEtAddress;
    @BindView(R.id.iv_scan)
    ImageView mIvScan;
    @BindView(R.id.et_amount)
    EditText mEtAmount;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.tv_message_code)
    RandomCodeTextView mTvMessageCode;
    @BindView(R.id.tv_voice_code)
    RandomCodeTextView mTvVoiceCode;
    @BindView(R.id.et_verify_code)
    EditText mEtVerifyCode;
    @BindView(R.id.bt_take_out)
    Button mBtTakeOut;
    @BindView(R.id.tv_info)
    TextView mTvInfo;

    /**
     * 已选中的资产
     */
    @Autowired(name = Const.KEY_CURRENCY_SHORT_NAME, required = true)
    String  currencyName;

    /**
     * 可用余额
     * 钱包地址
     * 转账数量
     * 密码
     * 验证码
     */
    private String available;
    private String address;
    private String amount;
    private String password;
    private String randomCode;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_take_out_platform;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        setTitle(String.format(Locale.CHINA, "转出%s",currencyName));
        mTvCurrencyName.setText(currencyName);
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.accountBlances(DefaultPreferenceUtil.getInstance().getToken(),currencyName);
    }

    /**
     * 验证通过
     */
    private boolean verity_account() {
        available = mTvCurrencyAmount.getText().toString();
        address = mEtAddress.getText().toString();
        amount = mEtAmount.getText().toString();
        password = mEtPassword.getText().toString();

        if (TextUtils.isEmpty(available)) {
            showToast("未获取到可用资产信息");
            mPresenter.accountBlances(DefaultPreferenceUtil.getInstance().getToken(), currencyName);
            return false;
        }

        if (TextUtils.isEmpty(address)) {
            showToast("请输入钱包地址");
            return false;
        }

        try {
            double double_amount = Double.parseDouble(amount);
            double double_available = Double.parseDouble(available);
            if (double_amount > double_available) {
                showToast("转账数目不能超过可用资产");
                return false;
            }
            if (double_amount == 0) {
                showToast("转账数目应大于0");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            showToast("请输入正确的转账数目");
            return false;
        }

        if (!password.matches(RegexConst.REGEX_TRANSACTION_PASSWORD)) {
            showToast("密码格式错误");
            return false;
        }

        return true;
    }

    /**
     * 验证通过
     */
    private boolean verity_random() {
        if (!verity_account()) {
            return false;
        }

        randomCode = mEtVerifyCode.getText().toString();
        if (!randomCode.matches(RegexConst.REGEX_RANDOM_NUMBER_6)) {
            showToast("验证码错误");
            return false;
        }

        return true;
    }

    @OnClick({R.id.iv_scan, R.id.tv_message_code, R.id.tv_voice_code, R.id.bt_take_out})
    void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.iv_scan://扫描二维码
                TakeOutPlatformActivityPermissionsDispatcher.toParseFromPhotoWithPermissionCheck(this);
                break;
            case R.id.tv_message_code://短信验证码
            case R.id.tv_voice_code://TODO 语音验证码
                if (verity_account()) {
                    mPresenter.sendMessage(BasePresenter.singleAccountBean.getMobile(), SmsConst.TAG_TRANSFER_ASSETS, null);
                }
                break;
            case R.id.bt_take_out://转出资产
                if(verity_random()){
                    mPresenter.transfer(DefaultPreferenceUtil.getInstance().getToken(),currencyName,amount,password,randomCode,address);
                }
                break;
        }
    }

    /**
     * 这里需要单独定义改方法,该方法需要框架去调用
     */
    @NeedsPermission({Manifest.permission.CAMERA})
    void toParseFromPhoto() {
        Intent intent = new Intent(this, CaptureActivity.class);
        startActivityForResult(intent, Const.REQUEST_CODE_ONE);
    }

    /**
     * 当用户设定“不在弹出权限请求框”时调用,相机
     */
    @OnNeverAskAgain({Manifest.permission.CAMERA})
    void onNeverAskAgainCamera() {
        showSnackbar("无相机权限,可以由此前往设置中心开启相机权限", "前往", v -> ActivityUtil.intoPermissionSettingPage(getBaseContext()));
    }

    /**
     * 当相机权限请求被拒绝时
     */
    @OnPermissionDenied({Manifest.permission.CAMERA})
    void onPermissionDeniedCamera() {
        showToast("权限启用失败");
    }

    /**
     * 当第一次请求外部存储权限被拒绝时，再次发起请求需要先进行说明
     */
    @OnShowRationale({Manifest.permission.CAMERA})
    void showRationaleCamera(final PermissionRequest request) {
        showToast("需要打开相机权限才能扫描二维码");
        request.proceed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == Const.REQUEST_CODE_ONE) {
            //扫描二维码成功,处理扫描结果
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle != null && (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS)) {
                    mEtAddress.setText(bundle.getString(CodeUtils.RESULT_STRING));
                }
            }
            showToast("无法解析二维码内容");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_record, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ARouter.getInstance()
                .build(ARouterConst.Activity_TakeOutCurrencyRecordActivity)
                .withString(Const.KEY_TAG_TRANSACTION_RECORD, Const.TAG_TRANSACTION_RECORD_PLATFORM)
                .withString(Const.KEY_CURRENCY_SHORT_NAME, currencyName)
                .navigation();
        return true;
    }

    @Override
    public void accountBlancesFinish(AccountBalance accountBalance) {
        super.accountBlancesFinish(accountBalance);
        if (accountBalance == null) {
            return;
        }

        mTvCurrencyAmount.setText(accountBalance.getAccountBalanceStr());
        mTvInfo.setText(getResources().getString(R.string.activity_take_out_platform_info, accountBalance.getTurnout_fee()));
    }

    @Override
    public void sendMessageFinish() {
        super.sendMessageFinish();
        mTvMessageCode.start();
        mTvVoiceCode.start();
    }

    @Override
    public void transferFinish() {
        BigToast.makeText(this,"转出成功", Toast.LENGTH_SHORT).show();

        //恢复默认的数据
        mEtAmount.setText(null);
        mEtPassword.setText(null);
        mEtVerifyCode.setText(null);
        mTvVoiceCode.stop();
        mTvMessageCode.stop();

        //刷新已有资产
        mPresenter.accountBlances(DefaultPreferenceUtil.getInstance().getToken(),currencyName);
    }
}