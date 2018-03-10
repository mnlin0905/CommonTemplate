package com.acchain.community.presenter;


import com.acchain.community.activity.friend.CardListActivity;
import com.acchain.community.base.BaseHttpBean;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.bean.Card;
import com.acchain.community.contract.CardListContract;
import com.acchain.community.util.HTTPException;
import com.acchain.community.util.HttpListener;
import com.acchain.community.util.DefaultPreferenceUtil;

import java.util.List;

import javax.inject.Inject;

/**
 * function---- CardListPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/18 02:36:54 (+0000).
 */
public class CardListPresenter extends BasePresenter<CardListActivity> implements CardListContract.Presenter{
    @Inject
    public CardListPresenter() {}

    @Override
    public void loadAllCard() {
         requestHttp(httpInterface.getCardList(DefaultPreferenceUtil.getInstance().getToken(), null), new HttpListener<BaseHttpBean<List<Card>>>() {
             @Override
             public void onSuccess(BaseHttpBean<List<Card>> response) {
                 List<Card> dataSet = response.dataSet;
                 mView.showAllCard(dataSet);
             }

             @Override
             public void onFailed(Throwable e) {
                 super.onFailed(e);
                 e.printStackTrace();
             }

             @Override
             public void onFailed(HTTPException e) {
                 super.onFailed(e);
                 e.printStackTrace();
             }
         });
    }
}