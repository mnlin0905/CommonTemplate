package com.acchain.community.contract;

/**
 * function---- TakeOutPlatformContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/11 13:46:19 (+0000).
 */
public interface TakeOutPlatformContract {
    interface Presenter{
        /**
         * 1.1.6  转出数字资产
         *
         * @param token            登录标志
         * @param currency         币种简称
         * @param number           转出数量
         * @param transactionPwd   支付密码
         * @param verificationCode 手机验证码
         * @param walletAddress    转出地址
         */
        void transfer( String token,String currency, String number, String transactionPwd,String verificationCode,String walletAddress);

    }

     interface View{
         /**
          * 1.1.6  转出数字资产
          */
         void transferFinish();
    }
}