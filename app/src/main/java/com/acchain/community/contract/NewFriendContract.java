package com.acchain.community.contract;

/**
 * function---- NewFriendContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/16 08:42:38 (+0000).
 */
public interface NewFriendContract {
    interface Presenter {


        void accept();
    }

    interface View {

        String getUserId();

        void acceptSuccess();
    }
}