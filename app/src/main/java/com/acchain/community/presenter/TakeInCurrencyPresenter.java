package com.acchain.community.presenter;


import com.acchain.community.activity.wallet.TakeInCurrencyActivity;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.TakeInCurrencyContract;

import javax.inject.Inject;

/**
 * function---- TakeInCurrencyPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/10 02:15:50 (+0000).
 */
public class TakeInCurrencyPresenter extends BasePresenter<TakeInCurrencyActivity> implements TakeInCurrencyContract.Presenter{
    @Inject
    public TakeInCurrencyPresenter() {}

    /**
     * 1.1.0 查询币种列表集合(转入)
     *
     * @param currency 币种信息,简称,可用于进行过滤,可为null
     */
    @Override
    public void currencyList(String token,String currency) {
        requestHttp(httpInterface.currencyList(token,currency),
                listBaseHttpBean -> mView.currencyListFinish(listBaseHttpBean.dataSet),
                throwable -> mView.currencyListFinish(null));
    }
}