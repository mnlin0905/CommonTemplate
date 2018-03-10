package com.acchain.community.contract;

import com.acchain.community.bean.ReceverRedPacketRecord;
import com.acchain.community.bean.SendRedPacketRecord;

/**
 * function---- RedPacketRecordContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/30 12:12:57 (+0000).
 */
public interface RedPacketRecordContract {
    interface Presenter{
        void loadReceverRedPacket();

        void loadSendRedPacket();
    }

    interface View{
        void showRecever(ReceverRedPacketRecord receverRedPacketRecord);

        void showSender(SendRedPacketRecord sendRedPacketRecord);
    }
}