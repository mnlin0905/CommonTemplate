package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.TransactionRecordContract;
import com.acchain.community.fragment.TransactionRecordFragment;

import javax.inject.Inject;

/**
 * function---- TransactionRecordPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/24 11:23:45 (+0000).
 */
public class TransactionRecordPresenter extends BasePresenter<TransactionRecordFragment> implements TransactionRecordContract.Presenter {
    @Inject
    public TransactionRecordPresenter() {
    }

    /**
     * 1.3.8 获取用户交易列表
     *
     * @param token    登录标志登录标志
     * @param type     交易类型  0:预售 1:领养
     * @param page     页码
     * @param pageSize 页面容量
     */
    @Override
    public void getTransactions(String token, String type, int page, int pageSize) {
        requestHttp(httpInterface.getTransactions(token, type, page, pageSize),
                listBaseHttpBean -> mView.getTransactionsFinish(listBaseHttpBean.dataSet),
                throwable -> mView.getTransactionsFinish(null));
    }

}