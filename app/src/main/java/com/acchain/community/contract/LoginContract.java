package com.acchain.community.contract;

/**
 * function---- LoginContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/09 05:59:52 (+0000).
 */
public interface LoginContract {
    interface Presenter {
        /**
         * 登录,帐号密码形式
         *
         * @param username 帐号
         * @param pwd      密码
         */
        void doLogin(String username, String pwd);

        /**
         * 动态登录,手机验证码
         *
         * @param username 手机号
         * @param code     验证码
         */
        void dynamicLogin(String username, String code);

    }

    interface View {
        /**
         * 登录,帐号密码形式
         */
        void doLoginFinish();

        /**
         * 动态登录,手机验证码
         */
        void dynamicLoginFinish();

    }
}