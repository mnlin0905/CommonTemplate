package com.acchain.community.contract;

/**
 * function---- QRAndCommandContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/15 11:38:29 (+0000).
 */
public interface QRAndCommandContract {
    interface Presenter {
        /**
         * 1.2.8 获取用户微口令
         *
         * @param token 登录标志登录标志
         */
        void getWeiCode(String token);
    }

    interface View {
        /**
         * 1.2.8 获取用户微口令
         */
        void getWeiCodeFinish(String command);
    }
}