package com.acchain.community.contract;

/**
 * function---- ChangeTransPasswordContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/31 07:28:27 (+0000).
 */
public interface ChangeTransPasswordContract {


    interface Presenter {
        /**
         * 1.3.7 修改支付密码(人工申请通过)
         *
         * @param token  登录标志登录标志
         * @param payPwd 支付密码(SHA256加密)
         */
        void changePayPwdForArtificial(String token, String payPwd);
    }

    interface View {
        /**
         * 1.3.7 修改支付密码(人工申请通过)
         */
        void changePayPwdForArtificialFinish();
    }
}