package com.acchain.community.contract;

import com.acchain.community.bean.CartPreGoods;

/**
 * function---- CartPreSellContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/02/06 01:50:13 (+0000).
 */
public interface CartPreSellContract {
    interface Presenter{
        void loadPreCart(int cartType, String token);
    }

     interface View{
         void onLoadPreCartFinish( CartPreGoods cartPreGoods);
    }
}