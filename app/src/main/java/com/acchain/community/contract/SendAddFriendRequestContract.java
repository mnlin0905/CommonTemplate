package com.acchain.community.contract;

/**
 * function---- SendAddFriendRequestContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/23 09:09:29 (+0000).
 */
public interface SendAddFriendRequestContract {
    interface Presenter{
        void sendRequest();
    }

    interface View{
        String getUserId();
        String getMessage();
        String getRemarks();
        void sendSuccess();
    }
}