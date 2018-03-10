package com.acchain.community.contract;

/**
 * function---- FindPasswordContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/19 02:31:05 (+0000).
 */
public interface FindPasswordContract {
    interface Presenter {
        /**
         * 获取短信验证码
         *
         * @param mobile 手机号
         * @param tag    操作类型
         * @param code   图形验证码
         */
        void sendMessage(String mobile, String tag, String code);

        /**
         * 获取邮箱验证码
         *
         * @param email 邮箱
         * @param tag   操作类型
         * @param code  图形验证码
         */
        void sendEmail(String email, String tag, String code);


        /**
         * 校验验证码
         *
         * @param username 手机号/邮箱
         * @param tag      操作类型
         * @param code     短信验证码
         */
        void checkCode(String username, String tag, String code);
    }

    interface View {
        /**
         * 获取短信验证码
         */
        void sendMessageFinish();

        /**
         * 获取邮箱验证码
         */
        void sendEmailFinish();

        /**
         * 校验验证码
         */
        void checkCodeFinish();
    }
}