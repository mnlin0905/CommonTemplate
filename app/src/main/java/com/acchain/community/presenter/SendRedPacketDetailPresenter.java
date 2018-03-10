package com.acchain.community.presenter;


import com.acchain.community.base.BaseHttpBean;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.bean.RedPacketDetail;
import com.acchain.community.contract.SendRedPacketDetailContract;
import com.acchain.community.activity.friend.SendRedPacketDetailActivity;
import com.acchain.community.util.HttpListener;
import com.acchain.community.util.DefaultPreferenceUtil;

import javax.inject.Inject;

/**
 * function---- SendRedPacketDetailPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/30 09:22:48 (+0000).
 */
public class SendRedPacketDetailPresenter extends BasePresenter<SendRedPacketDetailActivity> implements SendRedPacketDetailContract.Presenter{
    @Inject
    public SendRedPacketDetailPresenter() {}

    @Override
    public void loadRedDetails() {
        String redPacketId = mView.getRedPacketId();
        requestHttp(httpInterface.loadRedDetail(DefaultPreferenceUtil.getInstance().getToken(), redPacketId), new HttpListener<BaseHttpBean<RedPacketDetail>>() {
            @Override
            public void onSuccess(BaseHttpBean<RedPacketDetail> response) {
                mView.showRedDetails(response.dataSet);
            }
        });
    }
}