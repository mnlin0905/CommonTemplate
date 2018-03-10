package com.acchain.community.contract;

import com.acchain.community.bean.WalletAdoptList;

import java.util.List;

/**
 * function---- WalletAdoptContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/03 06:51:20 (+0000).
 */
public interface WalletAdoptContract {
    interface Presenter{
        /**
         * 1.2.2  钱包领养类
         *
         * @param token 登录标志
         */
        void adoptList(String token);
    }

     interface View{
         /**
          * 1.2.2  钱包领养类
          *
          * @param adoptLists 领养列表
          */
         void adoptListFinish(List<WalletAdoptList> adoptLists);
    }
}