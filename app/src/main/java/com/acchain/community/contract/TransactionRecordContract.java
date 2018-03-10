package com.acchain.community.contract;

import com.acchain.community.bean.TransactionRecordBean;

import java.util.List;

/**
 * function---- TransactionRecordContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/24 11:23:45 (+0000).
 */
public interface TransactionRecordContract {
    interface Presenter {
        /**
         * 1.3.8 获取用户交易列表
         *
         * @param token    登录标志登录标志
         * @param type     交易类型  0:预售 1:领养
         * @param page     页码
         * @param pageSize 页面容量
         */
        void getTransactions(String token, String type, int page, int pageSize);

    }

    interface View {
        /**
         * 1.3.8 获取用户交易列表
         *
         * @param transactionRecordBeans 交易记录列表
         */
        void getTransactionsFinish(List<TransactionRecordBean> transactionRecordBeans);


    }
}