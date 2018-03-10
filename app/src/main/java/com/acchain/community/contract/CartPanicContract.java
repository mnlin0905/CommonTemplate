package com.acchain.community.contract;

import com.acchain.community.bean.CartPanicGoods;

/**
 * function---- CartPanicContract
 * <p>
 * Created(Gradle default create) by MNLIN on 2018/01/11 09:55:51 (+0000).
 */
public interface CartPanicContract {
    interface Presenter{
        void loadPanicCart(int cartType, String memberId);
    }

     interface View{
        void onLoadPanicCartFinish(CartPanicGoods cartPanicGoods);
    }
}