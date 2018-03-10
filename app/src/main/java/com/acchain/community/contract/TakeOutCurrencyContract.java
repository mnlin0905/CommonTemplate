package com.acchain.community.contract;

import com.acchain.community.bean.CurrencyBean;

import java.util.List;

/**
 * function---- TakeOutCurrencyContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/11 13:47:31 (+0000).
 */
public interface TakeOutCurrencyContract {
    interface Presenter{
        void getCurrencyListTransfer(String token,String currency);
    }

     interface View{
         void getCurrencyListTransferFinish(List<CurrencyBean> currencyBeans);
    }
}