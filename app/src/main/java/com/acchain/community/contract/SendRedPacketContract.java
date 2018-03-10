package com.acchain.community.contract;

import com.acchain.community.bean.SendRedPacket;

/**
 * function---- SendRedPacketContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/26 07:48:20 (+0000).
 */
public interface SendRedPacketContract {
    interface Presenter{
        void sendRedPacket();
    }

    interface View{
        String getCurrency();

        String getAmount();

        String getRemark();

        String getTranPwd();

        String getTargetId();

        void sendSuccess(SendRedPacket sendRedPacket);

    }
}