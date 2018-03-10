package com.acchain.community.presenter;


import com.acchain.community.activity.friend.RedPacketRecordActivity;
import com.acchain.community.base.BaseHttpBean;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.bean.ReceverRedPacketRecord;
import com.acchain.community.bean.SendRedPacketRecord;
import com.acchain.community.contract.RedPacketRecordContract;
import com.acchain.community.util.HttpListener;
import com.acchain.community.util.DefaultPreferenceUtil;

import javax.inject.Inject;

/**
 * function---- RedPacketRecordPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/30 12:12:57 (+0000).
 */
public class RedPacketRecordPresenter extends BasePresenter<RedPacketRecordActivity> implements RedPacketRecordContract.Presenter{
    @Inject
    public RedPacketRecordPresenter() {}

    @Override
    public void loadReceverRedPacket() {
        requestHttp(httpInterface.loadReceverRedPacket(DefaultPreferenceUtil.getInstance().getToken()), new HttpListener<BaseHttpBean<ReceverRedPacketRecord>>() {
            @Override
            public void onSuccess(BaseHttpBean<ReceverRedPacketRecord> response) {
                mView.showRecever(response.dataSet);
            }
        });
    }

    @Override
    public void loadSendRedPacket() {
        requestHttp(httpInterface.loadSendRedPacket(DefaultPreferenceUtil.getInstance().getToken()), new HttpListener<BaseHttpBean<SendRedPacketRecord>>() {
            @Override
            public void onSuccess(BaseHttpBean<SendRedPacketRecord> response) {
                mView.showSender(response.dataSet);
            }
        });
    }
}