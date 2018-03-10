package com.acchain.community.contract;

/**
 * function---- ChatContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/05 10:22:20 (+0000).
 */
public interface ChatContract {
    interface Presenter{
        void receiveRedPacket();
    }

     interface View{
         String getRedPacketId();

         void receiveSuccess();
    }
}