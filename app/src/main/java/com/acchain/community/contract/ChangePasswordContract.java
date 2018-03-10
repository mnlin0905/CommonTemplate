package com.acchain.community.contract;

/**
 * function---- ChangePasswordContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/19 11:57:04 (+0000).
 */
public interface ChangePasswordContract {
    interface Presenter {
        /**
         * 1.1.4 修改登录密码
         *
         * @param token  登录标志
         * @param oldPwd 旧密码(sha256)
         * @param newPwd 新密码(sha256)
         * @return 请求返回信息
         */
        void updatePwd(String token, String oldPwd, String newPwd);
    }

    interface View {
        /**
         * 1.1.4 修改登录密码
         */
        void updatePwdFinish();
    }
}