package com.acchain.community.activity.wallet;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.bean.AccountBalance;
import com.acchain.community.bean.CurrencyAddressBean;
import com.acchain.community.contract.TakeInCurrencyDetailContract;
import com.acchain.community.presenter.TakeInCurrencyDetailPresenter;
import com.acchain.community.util.ActivityUtil;
import com.acchain.community.util.Const;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.acchain.community.util.Const.KEY_CURRENCY_CHAIN;
import static com.acchain.community.util.Const.KEY_CURRENCY_SHORT_NAME;

/**
 * function---- TakeInCurrencyDetailActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/10 06:46:04 (+0000).
 */
@Route(path = ARouterConst.Activity_TakeInCurrencyDetailActivity)
public class TakeInCurrencyDetailActivity extends BaseActivity<TakeInCurrencyDetailPresenter> implements TakeInCurrencyDetailContract.View {
    @BindView(R.id.tv_currency_name)
    TextView mTvCurrencyName;
    @BindView(R.id.tv_currency_amount)
    TextView mTvCurrencyAmount;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.iv_address_qr)
    ImageView mIvAddressQr;
    @BindView(R.id.bt_copy)
    Button mBtCopy;
    @BindView(R.id.tv_info)
    TextView mTvInfo;

    /**
     * 币种简称
     */
    @Autowired(name = KEY_CURRENCY_SHORT_NAME, required = true)
    String currencyShortName;

    /**
     * 币种所属区链
     * 0:ACC
     * 1:ETH
     * 2:BTC
     */
    @Autowired(name = KEY_CURRENCY_CHAIN, required = true)
    String currencyChain;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_take_in_currency_detail;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mTvCurrencyName.setText(currencyShortName);
        mTvInfo.setText(getResources().getString(R.string.activity_take_in_currency_detail_info, currencyShortName));
    }

    @Override
    protected void onResume() {
        super.onResume();

        //加载币种信息
        mPresenter.accountBlances(DefaultPreferenceUtil.getInstance().getToken(), currencyShortName);
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_record, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //跳转到记录页面
        ARouter.getInstance()
                .build(ARouterConst.Activity_TakeInCurrencyRecordActivity)
                .withString(Const.KEY_CURRENCY_SHORT_NAME, currencyShortName)
                .navigation();
        return true;
    }


    /**
     * 复制钱包地址
     */
    @OnClick(R.id.bt_copy)
    public void onViewClicked() {
        CharSequence address = mTvAddress.getText();
        if (!TextUtils.isEmpty(address)) {
            boolean success = ActivityUtil.saveMsgToClipboard(this, address.toString());
            if (success) {
                showToast("地址已复制,请前往目标界面进行粘贴");
            } else {
                showToast("无法复制到粘贴板,请用本软件重新打开当前界面");
            }
        } else {
            showToast("正在加载地址，请稍等");
        }
    }

    /**
     * 1.1.2 创建ACC链钱包地址
     *
     * @param addressBean 钱包地址
     */
    @Override
    public void createACCFinish(CurrencyAddressBean addressBean) {
        if (addressBean == null) {
            return;
        }

        //生成地址后，再次请求网络，获取账户信息
        //加载币种信息
        mPresenter.accountBlances(DefaultPreferenceUtil.getInstance().getToken(), currencyShortName);
    }

    /**
     * 1.1.3  创建BTC链钱包地址
     *
     * @param addressBean 钱包地址
     */
    @Override
    public void createBTCFinish(CurrencyAddressBean addressBean) {
        createACCFinish(addressBean);
    }

    /**
     * 1.1.4 创建ETH链钱包地址
     *
     * @param addressBean 钱包
     *                    地址
     */
    @Override
    public void createETHFinish(CurrencyAddressBean addressBean) {
        createACCFinish(addressBean);
    }

    @Override
    public void accountBlancesFinish(AccountBalance accountBalance) {
        super.accountBlancesFinish(accountBalance);

        if(accountBalance==null){
            return;
        }

        if (!TextUtils.isEmpty(accountBalance.getWalletAddress())) {
            //可用资产
            mTvCurrencyAmount.setText(String.valueOf(accountBalance.getAccountBalance()));
            mTvAddress.setText(accountBalance.getWalletAddress());
            mIvAddressQr.setImageBitmap(CodeUtils.createImage(accountBalance.getWalletAddress(), mIvAddressQr.getMeasuredWidth(), mIvAddressQr.getMeasuredHeight(), null));
            return;
        }

        //生成钱包地址,如果钱包地址未获取到,则主动要求生成
        switch (currencyChain) {
            case Const.TYPE_CHAIN_ACC:
                mPresenter.createACC(DefaultPreferenceUtil.getInstance().getToken(), currencyShortName);
                break;
            case Const.TYPE_CHAIN_ETH:
                mPresenter.createETH(DefaultPreferenceUtil.getInstance().getToken(), currencyShortName);
                break;
            case Const.TYPE_CHAIN_BTC:
                mPresenter.createBTC(DefaultPreferenceUtil.getInstance().getToken(), currencyShortName);
        }
    }
}