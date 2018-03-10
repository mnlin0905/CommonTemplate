package com.acchain.community.contract;

import com.acchain.community.bean.CartAdoptGoods;
import com.acchain.community.bean.CartPreGoods;

/**
 * function---- CartAdoptContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/02/24 02:40:51 (+0000).
 */
public interface CartAdoptContract {
    interface Presenter{
        void loadAdoptCart(int cartType, String token);
    }

     interface View{
         void onLoadAdoptCartFinish( CartAdoptGoods cartAdoptGoods);
    }
}