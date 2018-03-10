package com.acchain.community.contract;

import com.acchain.community.bean.PreSellGoodsDetail;

/**
 * function---- PreSellGoodsContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/12 12:54:14 (+0000).
 */
public interface PreSellGoodsContract {
    interface Presenter{

        void getPreSellProductDetail(int presellId);
    }

     interface View{

        void onPreSellProductDetailFinish(PreSellGoodsDetail preSellGoodsDetail);
    }
}