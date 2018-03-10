package com.acchain.community.presenter;


import com.acchain.community.activity.wallet.TakeOutCurrencyActivity;
import com.acchain.community.base.BaseHttpBean;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.bean.CurrencyBean;
import com.acchain.community.contract.TakeOutCurrencyContract;
import com.acchain.community.util.HttpListener;

import java.util.List;

import javax.inject.Inject;

/**
 * function---- TakeOutCurrencyPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/11 13:47:31 (+0000).
 */
public class TakeOutCurrencyPresenter extends BasePresenter<TakeOutCurrencyActivity> implements TakeOutCurrencyContract.Presenter{
    @Inject
    public TakeOutCurrencyPresenter() {}


    @Override
    public void getCurrencyListTransfer(String token, String currency) {
        requestHttp(httpInterface.getCurrencyListTransfer(token, currency), new HttpListener<BaseHttpBean<List<CurrencyBean>>>() {
            @Override
            public void onSuccess(BaseHttpBean<List<CurrencyBean>> response) {
                mView.getCurrencyListTransferFinish(response.dataSet);
            }

            @Override
            public void onFailed(Throwable e) {
                super.onFailed(e);
                mView.getCurrencyListTransferFinish(null);
                e.printStackTrace();
            }
        });
    }
}