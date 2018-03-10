package com.acchain.community.contract;

import com.acchain.community.bean.WalletPresellList;

import java.util.List;

/**
 * function---- WalletPreOrderContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/03 06:51:00 (+0000).
 */
public interface WalletPreOrderContract {
    interface Presenter{
        /**
         * 1.2.1  钱包预购类
         *
         * @param token 登录标志
         */
        void presellList(String token);
    }

     interface View{
         /**
          * 1.2.1  钱包预购类
          *
          * @param walletPresellLists 预购类列表
          */
         void presellListFinish(List<WalletPresellList> walletPresellLists);
    }
}