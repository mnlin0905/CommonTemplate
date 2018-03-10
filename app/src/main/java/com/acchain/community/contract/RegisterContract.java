package com.acchain.community.contract;

/**
 * function---- RegisterContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/09 07:44:40 (+0000).
 */
public interface RegisterContract {
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

        /**
         * 用户注册
         *
         * @param username 手机号/邮箱
         * @param pwd      密码
         * @param code     短信验证码
         */
        void newUser(String username, String pwd, String code);
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

        /**
         * 用户注册
         */
        void newUserFinish();
    }
}