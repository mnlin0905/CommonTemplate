package com.acchain.community.presenter;


import com.acchain.community.activity.friend.CardDetailActivity;
import com.acchain.community.base.BaseHttpBean;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.bean.CardDetail;
import com.acchain.community.bean.GetAccountBean;
import com.acchain.community.contract.CardDetailContract;
import com.acchain.community.util.HttpListener;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.blankj.utilcode.util.StringUtils;

import java.util.List;

import javax.inject.Inject;

/**
 * function---- CardDetailPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/18 05:58:49 (+0000).
 */
public class CardDetailPresenter extends BasePresenter<CardDetailActivity> implements CardDetailContract.Presenter {
    @Inject
    public CardDetailPresenter() {
    }

    @Override
    public void loadCardDetail() {
        String cardId = mView.getCardId();
        if (StringUtils.isEmpty(cardId)) {
            GetAccountBean.BusinessCard businessCard = singleAccountBean.getBusinessCard();
            mView.showCardDetail(businessCard);
        } else {
            requestHttp(httpInterface.loadCardDetail(DefaultPreferenceUtil.getInstance().getToken(), cardId, "queryBusinessCardDetail"), new HttpListener<BaseHttpBean<List<CardDetail>>>() {
                @Override
                public void onSuccess(BaseHttpBean<List<CardDetail>> response) {
                    List<CardDetail> dataSet = response.dataSet;
                    if (dataSet != null && !dataSet.isEmpty()) {
                        mView.showCardDetail(response.dataSet.get(0));
                    }
                }
            });
        }
    }
}