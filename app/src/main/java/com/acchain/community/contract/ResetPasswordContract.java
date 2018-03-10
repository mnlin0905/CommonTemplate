package com.acchain.community.contract;

/**
 * function---- ResetPasswordContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/19 03:55:14 (+0000).
 */
public interface ResetPasswordContract {
    interface Presenter {
        /**
         * 重置登录密码(手机/邮箱)
         *
         * @param username 手机号/邮箱
         * @param pwd      密码(sha256)
         * @param code     验证码
         * @param confPwd  密码再次确认
         * @return 登录返回对象
         */
        void resetPwd(String username, String pwd, String code, String confPwd);
    }

    interface View {
        /**
         * 重置登录密码(手机/邮箱)
         */
        void resetPwdFinish();
    }
}