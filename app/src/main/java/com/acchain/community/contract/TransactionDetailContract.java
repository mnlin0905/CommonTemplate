package com.acchain.community.contract;

import com.acchain.community.bean.TransactionDetailBean;

/**
 * function---- TransactionDetailContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/25 03:11:26 (+0000).
 */
public interface TransactionDetailContract {
    interface Presenter{

        /**
         * 1.3.9 获取交易详情
         *
         * @param token 登录标志登录标志
         * @param id    交易ID（非交易哈希）
         */
        void getTransactionDetail(String token, int id);
    }

    interface View{
        /**
         * 1.3.9 获取交易详情
         *
         * @param detailBean 交易详细信息
         */
        void getTransactionDetailFinish(TransactionDetailBean detailBean);
    }
}