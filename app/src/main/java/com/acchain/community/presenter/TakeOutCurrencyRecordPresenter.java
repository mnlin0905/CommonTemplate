package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.TakeOutCurrencyRecordContract;
import com.acchain.community.activity.wallet.TakeOutCurrencyRecordActivity;

import javax.inject.Inject;

/**
 * function---- TakeOutCurrencyRecordPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/12 08:00:28 (+0000).
 */
public class TakeOutCurrencyRecordPresenter extends BasePresenter<TakeOutCurrencyRecordActivity> implements TakeOutCurrencyRecordContract.Presenter{
    @Inject
    public TakeOutCurrencyRecordPresenter() {}

    /**
     * 1.2.0  钱包资产转出(撤销)
     *
     * @param token 登录标志
     * @param id    交易ID,主键
     */
    @Override
    public void transferPlatformRevoke(String token, String id) {
        requestHttp(httpInterface.transferPlatformRevoke(token,id),
                objectBaseHttpBean -> mView.transferPlatformRevokeFinish());
    }
}