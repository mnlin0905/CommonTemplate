package com.acchain.community.contract;

import com.acchain.community.bean.AdoptGoodDetail;

/**
 * function---- AdoptGoodsContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/16 03:01:17 (+0000).
 */
public interface AdoptGoodsContract {
    interface Presenter {
        void getAdoptProductDetail(int adoptId);
    }

    interface View {
        void onAdoptProductDetailFinish(AdoptGoodDetail adoptGoodDetail);
    }
}