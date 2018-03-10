package com.acchain.community.contract;

import com.acchain.community.bean.AdoptProduct;

/**
 * function---- AdoptContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/15 07:32:23 (+0000).
 */
public interface AdoptContract {
    interface Presenter {
        /**
         * 获取领养类商品列表
         */
        void getAdoptProducts(int pageIndex);
    }

    interface View {
        /**
         * 领养类商品列表
         */
        void onAdoptProductFinish(AdoptProduct adoptProduct);
    }
}