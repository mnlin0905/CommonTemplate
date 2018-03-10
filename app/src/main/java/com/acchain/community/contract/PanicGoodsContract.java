package com.acchain.community.contract;

import com.acchain.community.bean.PanicGoodDetail;

/**
 * function---- PanicGoodsContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/09 07:43:07 (+0000).
 */
public interface PanicGoodsContract {
    interface Presenter {
        void getFlashSalesDetail(int productId,int flashSaleId);
    }

    interface View {

        void onFlashSalesDetailFinish(PanicGoodDetail panicGoodDetail);
    }
}