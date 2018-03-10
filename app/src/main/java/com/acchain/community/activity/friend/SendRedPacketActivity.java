package com.acchain.community.activity.friend;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.bean.SendRedPacket;
import com.acchain.community.contract.SendRedPacketContract;
import com.acchain.community.presenter.SendRedPacketPresenter;
import com.acchain.community.rongcloud.message.RedPacketMessage;
import com.acchain.community.view.LineMenuView;
import com.acchain.community.window.PasswordDialogFragment;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;

import static com.acchain.community.util.Const.KEY_CURRENCY_SHORT_NAME;
import static com.acchain.community.util.Const.KEY_IS_FROM_OTHER_ACTIVITY;
import static com.acchain.community.util.Const.KEY_TARGET_FRIEND_ID;

/**
 * function---- SendRedPacketActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/26 07:48:20 (+0000).
 */
@Route(path = ARouterConst.Activity_SendRedPacketActivity)
public class SendRedPacketActivity extends BaseActivity<SendRedPacketPresenter> implements SendRedPacketContract.View ,LineMenuView.LineMenuListener{
    @BindView(R.id.select_currency)
    LineMenuView selectCurrency;
    @BindView(R.id.number)
    EditText number;
    @BindView(R.id.title)
    EditText title;
    private String currency;
    private String tranPwd;
    public Conversation.ConversationType conversationType = Conversation.ConversationType.PRIVATE;

    @Autowired(name = KEY_TARGET_FRIEND_ID,required = true)
    public String targetId;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_send_red_packet;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }


    @OnClick(R.id.send_redPacket)
    public void onViewClicked() {
        PasswordDialogFragment passwordDialogFragment = new PasswordDialogFragment();
        passwordDialogFragment.setOnSelectTurnOutListener(new PasswordDialogFragment.onPasswordListener() {
            @Override
            public void onPasswordCancel(Dialog dialog) {
                dialog.dismiss();
            }

            @Override
            public void onPasswordConfirm(Dialog dialog, String passWord) {
                dialog.dismiss();
                tranPwd = passWord;
                mPresenter.sendRedPacket();
            }

            @Override
            public void onPasswordChanged(Dialog dialog, String psw) {

            }
        });
        passwordDialogFragment.show(getSupportFragmentManager(),"");

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
        ARouter.getInstance()
                .build(ARouterConst.Activity_TakeOutCurrencyActivity)
                .withBoolean(KEY_IS_FROM_OTHER_ACTIVITY, true)
                .navigation(this, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==1 && resultCode == RESULT_OK) {
            currency = data.getStringExtra(KEY_CURRENCY_SHORT_NAME);
            selectCurrency.setBrief(currency);
        }
    }

    @Override
    public String getCurrency() {
        return currency;
    }

    @Override
    public String getAmount() {
        return number.getText().toString();
    }

    @Override
    public String getRemark() {
        return title.getText().toString().trim();
    }

    @Override
    public String getTranPwd() {
        return tranPwd;
    }

    @Override
    public String getTargetId() {
        return targetId;
    }

    @Override
    public void sendSuccess(SendRedPacket sendRedPacket) {
        RedPacketMessage redPacketMessage = new RedPacketMessage(String.valueOf(sendRedPacket.getId()),getRemark(),currency,getAmount(),sendRedPacket.getExpireDate());
        Message obtain = Message.obtain(targetId, conversationType, redPacketMessage);
        RongIM.getInstance().sendMessage(obtain, "您收到一个红包", "您收到一个红包", new IRongCallback.ISendMessageCallback() {
            @Override
            public void onAttached(Message message) {

            }

            @Override
            public void onSuccess(Message message) {
               finish();
            }

            @Override
            public void onError(Message message, RongIMClient.ErrorCode errorCode) {
                Logger.e("红包发送失败:"+errorCode);
            }
        });
    }
}