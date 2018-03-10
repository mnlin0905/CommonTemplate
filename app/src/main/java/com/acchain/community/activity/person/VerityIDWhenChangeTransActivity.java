package com.acchain.community.activity.person;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.VerityIDWhenChangeTransContract;
import com.acchain.community.presenter.VerityIDWhenChangeTransPresenter;
import com.acchain.community.util.RegexConst;
import com.acchain.community.util.SmsConst;
import com.acchain.community.view.RandomCodeTextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

import static com.acchain.community.base.BasePresenter.singleAccountBean;
import static com.acchain.community.util.Const.KEY_RANDOM_CODE;

/**
 * function---- VerityIDWhenChangeTransActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/31 07:47:14 (+0000).
 */
@Route(path = ARouterConst.Activity_VerityIDWhenChangeTransActivity)
public class VerityIDWhenChangeTransActivity extends BaseActivity<VerityIDWhenChangeTransPresenter> implements VerityIDWhenChangeTransContract.View {

    @BindView(R.id.et_random_code)
    EditText mEtRandomCode;
    @BindView(R.id.rctv_randomCode)
    RandomCodeTextView mRctvRandomCode;
    @BindView(R.id.bt_next)
    Button mBtNext;
    @BindView(R.id.tv_change)
    TextView mTvChange;
    @BindView(R.id.tv_label)
    TextView mTvLabel;

    private String random;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_verity_id_when_change_trans;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        setToolbarNavText(toolbar, "关闭", R.color.white);
        mTvLabel.setText(String.format(Locale.CHINA, "请输入%s收到的校验码",
                singleAccountBean.getMobile().replaceAll("^([\\d]{3}).*([\\d]{3})$", "$1****$2")));

    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @OnClick({R.id.rctv_randomCode, R.id.bt_next, R.id.tv_change})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rctv_randomCode:
                mPresenter.sendMessage(singleAccountBean.getMobile(), SmsConst.TAG_SET_PAY_PWD, null);
                break;
            case R.id.bt_next://下一步,验证验证码
                random = mEtRandomCode.getText().toString();
                if (!random.matches(RegexConst.REGEX_RANDOM_NUMBER_6)) {
                    showToast("验证码格式错误");
                    break;
                }
                mPresenter.checkCode(singleAccountBean.getMobile(), SmsConst.TAG_SET_PAY_PWD, random);
                break;
            case R.id.tv_change://更换校验方式
                ARouter.getInstance().build(ARouterConst.Activity_SelectChangeTransPasswordTypeActivity).navigation();
                break;
        }
    }

    @Override
    public void sendMessageFinish() {
        super.sendMessageFinish();
    }

    @Override
    public void checkCodeFinish() {
        super.checkCodeFinish();

        //携带短信验证码前往修改交易密码
        ARouter.getInstance()
                .build(ARouterConst.Activity_ChangeTransPasswordActivity)
                .withString(KEY_RANDOM_CODE,random)
                .navigation();
    }
}