package com.acchain.community.contract;

import com.acchain.community.bean.CurrencyAddressBean;

import retrofit2.http.Field;

/**
 * function---- TakeInCurrencyDetailContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/10 06:46:04 (+0000).
 */
public interface TakeInCurrencyDetailContract {
    interface Presenter {
        /**
         * 1.1.2 创建ACC链钱包地址
         *
         * @param token    token
         * @param currency 币种信息
         */
        void createACC(String token, String currency);

        /**
         * 1.1.3  创建BTC链钱包地址
         *
         * @param token    token
         * @param currency 币种信息
         */
        void createBTC(@Field("token") String token, String currency);

        /**
         * 1.1.4 创建ETH链钱包地址
         *
         * @param token    token
         * @param currency 币种信息
         */
        void createETH(String token, String currency);


    }

    interface View {
        /**
         * 1.1.2 创建ACC链钱包地址
         *
         * @param addressBean 钱包地址
         */
        void createACCFinish(CurrencyAddressBean addressBean);

        /**
         * 1.1.3  创建BTC链钱包地址
         *
         * @param addressBean 钱包地址
         */
        void createBTCFinish(CurrencyAddressBean addressBean);

        /**
         * 1.1.4 创建ETH链钱包地址
         *
         * @param addressBean 钱包地址
         */
        void createETHFinish(CurrencyAddressBean addressBean);
    }
}