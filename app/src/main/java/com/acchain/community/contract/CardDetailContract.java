package com.acchain.community.contract;

import com.acchain.community.bean.CardDetail;
import com.acchain.community.bean.GetAccountBean;

/**
 * function---- CardDetailContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/18 05:58:49 (+0000).
 */
public interface CardDetailContract {
    interface Presenter {
        void loadCardDetail();
    }

    interface View {
        String getCardId();

        void showCardDetail(GetAccountBean.BusinessCard businessCard);

        void showCardDetail(CardDetail cardDetail);
    }
}