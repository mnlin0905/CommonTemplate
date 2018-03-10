package com.acchain.community.presenter;


import com.acchain.community.R;
import com.acchain.community.activity.friend.SendRedPacketActivity;
import com.acchain.community.base.BaseHttpBean;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.bean.SendRedPacket;
import com.acchain.community.contract.SendRedPacketContract;
import com.acchain.community.util.HttpListener;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.util.EncodeUtils;
import com.blankj.utilcode.util.StringUtils;

import javax.inject.Inject;

/**
 * function---- SendRedPacketPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/26 07:48:20 (+0000).
 */
public class SendRedPacketPresenter extends BasePresenter<SendRedPacketActivity> implements SendRedPacketContract.Presenter{
    @Inject
    public SendRedPacketPresenter() {}

    @Override
    public void sendRedPacket() {
        String amount = mView.getAmount();
        String currency = mView.getCurrency();
        String remark = mView.getRemark();
        String tranPwd = mView.getTranPwd();
        String targetId = mView.getTargetId();
        if (StringUtils.isEmpty(currency)) {
            mView.showToast(mView.getApplication().getString(R.string.please_select_currency));
            return;
        }
        if (StringUtils.isEmpty(amount)) {
            mView.showToast(mView.getApplication().getString(R.string.please_red_packet_amount));
            return;
        }
        if (StringUtils.isEmpty(remark)) {
            mView.showToast("请输入红包备注");
            return;
        }
        if (StringUtils.isEmpty(remark)) {
            mView.showToast(mView.getApplication().getString(R.string.please_input_remark));
            return;
        }

        tranPwd = EncodeUtils.encode(tranPwd, EncodeUtils.SHA_256);
        requestHttp(httpInterface.sendRedPacket(DefaultPreferenceUtil.getInstance().getToken(),
                targetId,
                currency,
                amount,
                tranPwd,
                remark), new HttpListener<BaseHttpBean<SendRedPacket>>() {
            @Override
            public void onSuccess(BaseHttpBean<SendRedPacket> response) {
                SendRedPacket dataSet = response.dataSet;
                mView.sendSuccess(dataSet);
            }
        });
    }
}