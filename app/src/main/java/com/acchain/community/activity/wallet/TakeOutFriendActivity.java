package com.acchain.community.activity.wallet;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.bean.AccountBalance;
import com.acchain.community.bean.Friend;
import com.acchain.community.bean.TransferInfo;
import com.acchain.community.contract.TakeOutFriendContract;
import com.acchain.community.presenter.TakeOutFriendPresenter;
import com.acchain.community.util.Const;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.window.PasswordDialogFragment;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;

/**
 * function---- TakeOutFriendActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/11 13:58:57 (+0000).
 */
@Route(path = ARouterConst.Activity_TakeOutFriendActivity)
public class TakeOutFriendActivity extends BaseActivity<TakeOutFriendPresenter> implements TakeOutFriendContract.View, PasswordDialogFragment.onPasswordListener {
    @BindView(R.id.riv_head)
    RoundedImageView mRivHead;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_currency)
    TextView mTvCurrency;
    @BindView(R.id.et_available)
    TextView mEtAvailable;
    @BindView(R.id.et_amount)
    EditText mEtAmount;
    @BindView(R.id.et_remark)
    EditText mEtRemark;
    @BindView(R.id.bt_confirm)
    Button mBtConfirm;
    @BindView(R.id.iv_edit)
    ImageView mIvEdit;

    /**
     * 转账的联系人
     */
    @Autowired(name = Const.MODEL_CONTACT_FRIEND, required = true)
    Friend friendModel;

    /**
     * 转账的币种信息
     */
    @Autowired(name = Const.KEY_CURRENCY_SHORT_NAME, required = true)
    String currencyName;

    /**
     * 可用资产
     * 转账资产
     * 备注信息
     */
    private String available;
    private String amount;
    private String remark;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_take_out_friend;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        Glide.with(this).load(friendModel.getPortraitUri()).apply(new RequestOptions().centerCrop()).into(mRivHead);
        mTvName.setText(friendModel.getName());
        mTvPhone.setText(BasePresenter.singleAccountBean.getMobile());
        mTvCurrency.setText(currencyName);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.accountBlances(DefaultPreferenceUtil.getInstance().getToken(),currencyName);
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
        //查看转出详情信息
        ARouter.getInstance()
                .build(ARouterConst.Activity_TakeOutCurrencyRecordActivity)
                .withString(Const.KEY_TAG_TRANSACTION_RECORD,Const.TAG_TRANSACTION_RECORD_FRIEND)
                .withString(Const.KEY_CURRENCY_SHORT_NAME, currencyName)
                .navigation();
        return true;
    }

    @OnClick({R.id.riv_head, R.id.bt_confirm, R.id.iv_edit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.riv_head://点击头像
                break;
            case R.id.bt_confirm:// 弹出密码框
                //先验证输入的信息是否正确
                if (!verity_info()) {
                    return;
                }
                new PasswordDialogFragment()
                        .setAmount(amount)
                        .setUnit(currencyName)
                        .setOnSelectTurnOutListener(this)
                        .show(getSupportFragmentManager(), "password");
                break;
            case R.id.iv_edit://获取文本焦点
                mIvEdit.requestFocus();
                mIvEdit.findFocus();
                break;
        }
    }

    /**
     * 验证通过
     */
    private boolean verity_info() {
        available = mEtAvailable.getText().toString();
        amount = mEtAmount.getText().toString();
        remark = mEtRemark.getText().toString();
        if (TextUtils.isEmpty(available)) {
            showToast("未获取到可用资产信息");
            mPresenter.accountBlances(DefaultPreferenceUtil.getInstance().getToken(), currencyName);
            return false;
        }

        try {
            double double_amount = Double.parseDouble(amount);
            double double_available = Double.parseDouble(available);
            if (double_amount > double_available) {
                showToast("转账数目不能超过可用资产");
                return false;
            }
            if(double_amount==0){
                showToast("转账数目应大于0");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            showToast("请输入正确的转账数目");
            return false;
        }

        return true;
    }

    /**
     * 当点击取消时回调
     *
     * @param dialog 弹出框
     */
    @Override
    public void onPasswordCancel(Dialog dialog) {

    }

    /**
     * 当输入密码完毕后回调
     *
     * @param dialog   弹出框
     * @param passWord 密码
     */
    @Override
    public void onPasswordConfirm(Dialog dialog, String passWord) {
        mPresenter.assetsTransferOutFriend(DefaultPreferenceUtil.getInstance().getToken(),
                currencyName,
                friendModel.getUserId(),
                amount,
                passWord,
                remark,
                Const.TYPE_TRANSFER_TO_FRIEND);
    }

    /**
     * 当密码框中内容有变化时回调
     * <p>
     * 可以主动判断是否输入了足够的位数,然后自动进行界面跳转
     *
     * @param dialog 弹出框
     * @param psw    密码
     */
    @Override
    public void onPasswordChanged(Dialog dialog, String psw) {

    }

    /**
     * 1.1.9  转出数字资产(朋友)
     *
     * @param transferInfo 转给朋友的结果返回信息
     */
    @Override
    public void assetsTransferOutFriendFinish(TransferInfo transferInfo) {
        RongIM.getInstance().startPrivateChat(TakeOutFriendActivity.this,friendModel.getUserId(),friendModel.getName());
        finish();
        //转账成功,则进入聊天界面
       /* TranferMessage tranferMessage = new TranferMessage(transferInfo.getTransaction_id(),
                currencyName,
                mEtAmount.getText().toString(),
                mEtRemark.getText().toString(),
                transferInfo.getPayment_model(),
                transferInfo.getCreate_time(),
                transferInfo.getTarger_name(),
                transferInfo.getOwen_name(),
                DefaultPreferenceUtil.getInstance().getMemberId(),
                BasePresenter.singleAccountBean.getImgSrc());
        Message obtain = Message.obtain(friendModel.getUserId(), Conversation.ConversationType.PRIVATE, tranferMessage);
        RongIM.getInstance().sendMessage(obtain, transferInfo.getOwen_name() + "给您转了一笔钱", transferInfo.getOwen_name() + "给您转了一笔钱", new IRongCallback.ISendMessageCallback() {
            @Override
            public void onAttached(Message message) {

            }

            @Override
            public void onSuccess(Message message) {
                RongIM.getInstance().startPrivateChat(TakeOutFriendActivity.this,friendModel.getUserId(),friendModel.getName());
                finish();
            }

            @Override
            public void onError(Message message, RongIMClient.ErrorCode errorCode) {

            }
        });*/
    }

    @Override
    public void accountBlancesFinish(AccountBalance accountBalance) {
        super.accountBlancesFinish(accountBalance);
        if(accountBalance==null){
            return;
        }

        mEtAvailable.setText(accountBalance.getAccountBalanceStr());
    }
}