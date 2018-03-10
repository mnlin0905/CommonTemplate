package com.acchain.community.contract;

import com.acchain.community.bean.PreSellProduct;

/**
 * function---- PreSellContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/12 11:23:32 (+0000).
 */
public interface PreSellContract {
    interface Presenter{
        /**
         * 获取预售类商品列表
         *
         * @param pageIndex 当前页
         */
        void getPreSellProducts(int pageIndex);

    }

     interface View{
         /**
          * 商品预售列表
          */
         void onPreSellProductFinish(PreSellProduct preSellProduct);
    }
}