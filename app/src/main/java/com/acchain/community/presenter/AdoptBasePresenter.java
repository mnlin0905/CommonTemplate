package com.acchain.community.presenter;


import com.acchain.community.base.BaseHttpBean;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.bean.AdoptBaseCode;
import com.acchain.community.contract.AdoptBaseContract;
import com.acchain.community.activity.index.AdoptBaseActivity;
import com.acchain.community.util.HttpListener;
import com.acchain.community.util.HTTPException;

import javax.inject.Inject;

/**
 * function---- AdoptBasePresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/19 07:23:18 (+0000).
 */
public class AdoptBasePresenter extends BasePresenter<AdoptBaseActivity> implements AdoptBaseContract.Presenter{
    @Inject
    public AdoptBasePresenter() {}

    @Override
    public void chooseAdoptCode(int adoptId, int locationId, int pageIndex) {
        requestHttp(httpInterface.chooseAdoptCode(adoptId, locationId, pageIndex), new HttpListener<BaseHttpBean<AdoptBaseCode>>() {
            @Override
            public void onSuccess(BaseHttpBean<AdoptBaseCode> response) {
                mView.onChooseAdoptCodeFinish(response.dataSet);
            }

            @Override
            public void onFailed(Throwable e) {
                super.onFailed(e);
                e.printStackTrace();
                mView.onChooseAdoptCodeFinish(null);
            }

            @Override
            public void onFailed(HTTPException e) {
                super.onFailed(e);
                mView.onChooseAdoptCodeFinish(null);
            }
        });
    }
}