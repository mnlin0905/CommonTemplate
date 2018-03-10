package com.acchain.community.contract;

import com.acchain.community.bean.PanicGood;

import java.util.List;

/**
 * function---- PanicContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/06 07:35:16 (+0000).
 */
public interface PanicContract {
    interface Presenter{
        void getFlashSales();
    }

     interface View{
        void onFlashSalesFinish(List<PanicGood> panicGood);
    }
}