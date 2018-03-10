package com.acchain.community.contract;

import com.acchain.community.bean.RedPacketDetail;

/**
 * function---- SendRedPacketDetailContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/30 09:22:48 (+0000).
 */
public interface SendRedPacketDetailContract {
    interface Presenter{
        void loadRedDetails();
    }

    interface View{
        String getRedPacketId();

        void showRedDetails(RedPacketDetail redPacketDetail);
    }
}