package com.acchain.community.contract;

/**
 * function---- ChangeNickNameContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/15 12:38:25 (+0000).
 */
public interface ChangeNickNameContract {
    interface Presenter{
        /**
         * 1.1.1 设置昵称
         *
         * @param token   token
         * @param nickname 昵称
         * @return 账户返回对象
         */
        void setNickname (String token, String nickname);
    }

     interface View{
         /**
          * 1.1.1 设置昵称
          */
         void setNicknameFinish ();
    }
}