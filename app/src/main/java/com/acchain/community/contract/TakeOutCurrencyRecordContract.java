package com.acchain.community.contract;

/**
 * function---- TakeOutCurrencyRecordContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/12 08:00:28 (+0000).
 */
public interface TakeOutCurrencyRecordContract {
    interface Presenter {
        /**
         * 1.2.0  钱包资产转出(撤销)
         *
         * @param token 登录标志
         * @param id    交易ID,主键
         */
        void transferPlatformRevoke(String token, String id);
    }

    interface View {
        /**
         * 1.2.0  钱包资产转出(撤销)
         */
        void transferPlatformRevokeFinish();
    }
}