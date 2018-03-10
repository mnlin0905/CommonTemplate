package com.acchain.community.contract;

/**
 * function---- BeginCertificationContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/17 03:18:45 (+0000).
 */
public interface BeginCertificationContract {
    interface Presenter {
        /**
         * 1.1.2 实名认证
         *
         * @param token    登录标志
         * @param country  国家地区
         * @param name     真实姓名
         * @param cardType 证件类型 1:身份证 2:军官证 3:护照
         * @param idNo     证件号码
         * @param mobile   手机号码
         * @param code     短信验证码
         * @param positive 证件正面照
         * @param contrary 证件反面照
         * @param hord     手持证件照
         * @return 请求返回信息
         */
        void verified(String token, String country, String name, int cardType, String idNo, String mobile, String code, String positive, String contrary, String hord);
    }

    interface View {
        /**
         * 1.1.2 实名认证
         */
        void verifiedFinish();
    }
}