package com.acchain.community.contract;

import com.acchain.community.bean.Card;

import java.util.List;

/**
 * function---- CardListContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/18 02:36:54 (+0000).
 */
public interface CardListContract {
    interface Presenter{
        void loadAllCard();

    }

    interface View{
        void showAllCard(List<Card> cardList);
    }
}