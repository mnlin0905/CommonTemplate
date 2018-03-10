package com.acchain.community.presenter;


import com.acchain.community.activity.index.PreSellActivity;
import com.acchain.community.base.BaseHttpBean;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.bean.PreSellProduct;
import com.acchain.community.contract.PreSellContract;
import com.acchain.community.util.HttpListener;
import com.acchain.community.util.HTTPException;

import javax.inject.Inject;

/**
 * function---- PreSellPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/12 11:23:32 (+0000).
 */
public class PreSellPresenter extends BasePresenter<PreSellActivity> implements PreSellContract.Presenter{
    @Inject
    public PreSellPresenter() {}

    @Override
    public void getPreSellProducts(int pageIndex) {
        requestHttp(httpInterface.getPreSellProducts(pageIndex), new HttpListener<BaseHttpBean<PreSellProduct>>() {
            @Override
            public void onSuccess(BaseHttpBean<PreSellProduct> response) {
                mView.onPreSellProductFinish(response.dataSet);
            }

            @Override
            public void onFailed(Throwable e) {
                super.onFailed(e);
                e.printStackTrace();
                mView.onPreSellProductFinish(null);
            }

            @Override
            public void onFailed(HTTPException e) {
                super.onFailed(e);
                mView.onPreSellProductFinish(null);
            }
        });
    }
}