package com.acchain.community.contract;

import com.acchain.community.bean.TakeInCurrencyAgent;

import java.util.List;

/**
 * function---- TakeInCurrencyContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/10 02:15:50 (+0000).
 */
public interface TakeInCurrencyContract {
    interface Presenter{
        /**
         * 1.1.0 查询币种列表集合(转入)
         *
         * @param currency 币种信息,简称,可用于进行过滤,可为null
         */
        void currencyList(String token,String currency);
    }

     interface View{
         /**
          * 1.1.0 查询币种列表集合(转入)
          *
          * @param takeInCurrencyAgents 转入时的资产列表
          */
         void currencyListFinish(List<TakeInCurrencyAgent> takeInCurrencyAgents);
    }
}