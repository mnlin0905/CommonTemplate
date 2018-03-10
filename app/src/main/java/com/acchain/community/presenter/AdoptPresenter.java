package com.acchain.community.presenter;


import com.acchain.community.activity.index.AdoptActivity;
import com.acchain.community.base.BaseHttpBean;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.bean.AdoptProduct;
import com.acchain.community.contract.AdoptContract;
import com.acchain.community.util.HttpListener;
import com.acchain.community.util.HTTPException;

import javax.inject.Inject;

/**
 * function---- AdoptPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/15 07:32:23 (+0000).
 */
public class AdoptPresenter extends BasePresenter<AdoptActivity> implements AdoptContract.Presenter{
    @Inject
    public AdoptPresenter() {}

    @Override
    public void getAdoptProducts(int pageIndex) {
        requestHttp(httpInterface.getAdoptProducts(pageIndex), new HttpListener<BaseHttpBean<AdoptProduct>>() {
            @Override
            public void onSuccess(BaseHttpBean<AdoptProduct> response) {
                mView.onAdoptProductFinish(response.dataSet);
            }

            @Override
            public void onFailed(Throwable e) {
                super.onFailed(e);
                e.printStackTrace();
                mView.onAdoptProductFinish(null);
            }

            @Override
            public void onFailed(HTTPException e) {
                super.onFailed(e);
                mView.onAdoptProductFinish(null);
            }
        });

    }
}