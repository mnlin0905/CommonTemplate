package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.TakeInCurrencyDetailContract;
import com.acchain.community.activity.wallet.TakeInCurrencyDetailActivity;

import javax.inject.Inject;

/**
 * function---- TakeInCurrencyDetailPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/10 06:46:04 (+0000).
 */
public class TakeInCurrencyDetailPresenter extends BasePresenter<TakeInCurrencyDetailActivity> implements TakeInCurrencyDetailContract.Presenter{
    @Inject
    public TakeInCurrencyDetailPresenter() {}

    /**
     * 1.1.2 创建ACC链钱包地址
     *
     * @param token    token
     * @param currency 币种信息
     */
    @Override
    public void createACC(String token, String currency) {
        requestHttp(httpInterface.createACC(token,currency),
                currencyAddressBeanBaseHttpBean -> mView.createACCFinish(currencyAddressBeanBaseHttpBean.dataSet),
                throwable -> mView.createACCFinish(null));
    }

    /**
     * 1.1.3  创建BTC链钱包地址
     *
     * @param token    token
     * @param currency 币种信息
     */
    @Override
    public void createBTC(String token, String currency) {
        requestHttp(httpInterface.createBTC(token,currency),
                currencyAddressBeanBaseHttpBean -> mView.createBTCFinish(currencyAddressBeanBaseHttpBean.dataSet),
                throwable -> mView.createBTCFinish(null));
    }

    /**
     * 1.1.4 创建ETH链钱包地址
     *
     * @param token    token
     * @param currency 币种信息
     */
    @Override
    public void createETH(String token, String currency) {
        requestHttp(httpInterface.createETH(token,currency),
                currencyAddressBeanBaseHttpBean -> mView.createETHFinish(currencyAddressBeanBaseHttpBean.dataSet),
                throwable -> mView.createETHFinish(null));
    }
}