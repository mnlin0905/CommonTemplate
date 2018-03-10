package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.TransactionDetailContract;
import com.acchain.community.activity.person.TransactionDetailActivity;

import javax.inject.Inject;

/**
 * function---- TransactionDetailPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/25 03:11:26 (+0000).
 */
public class TransactionDetailPresenter extends BasePresenter<TransactionDetailActivity> implements TransactionDetailContract.Presenter{
    @Inject
    public TransactionDetailPresenter() {}


    /**
     * 1.3.9 获取交易详情
     *
     * @param token 登录标志登录标志
     * @param id    交易ID（非交易哈希）
     */
    @Override
    public void getTransactionDetail(String token, int id) {
        requestHttp(httpInterface.getTransactionDetail(token,id),
                listBaseHttpBean -> mView.getTransactionDetailFinish(listBaseHttpBean.dataSet),
                throwable -> mView.getTransactionDetailFinish(null));
    }
}