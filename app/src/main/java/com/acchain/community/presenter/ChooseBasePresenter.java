package com.acchain.community.presenter;


import com.acchain.community.base.BaseHttpBean;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.bean.AdoptBaseLocation;
import com.acchain.community.contract.ChooseBaseContract;
import com.acchain.community.activity.index.ChooseBaseActivity;
import com.acchain.community.util.HttpListener;
import com.acchain.community.util.HTTPException;

import java.util.List;

import javax.inject.Inject;

/**
 * function---- ChooseBasePresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/02/02 05:50:28 (+0000).
 */
public class ChooseBasePresenter extends BasePresenter<ChooseBaseActivity> implements ChooseBaseContract.Presenter {
    @Inject
    public ChooseBasePresenter() {
    }

    @Override
    public void findAdoptLocation(int adoptId) {
        requestHttp(httpInterface.findAdoptLocation(adoptId), new HttpListener<BaseHttpBean<List<AdoptBaseLocation>>>() {
            @Override
            public void onSuccess(BaseHttpBean<List<AdoptBaseLocation>> response) {
                mView.onFindAdoptLocationFinish(response.dataSet);
            }

            @Override
            public void onFailed(Throwable e) {
                super.onFailed(e);
                e.printStackTrace();
                mView.onFindAdoptLocationFinish(null);
            }

            @Override
            public void onFailed(HTTPException e) {
                super.onFailed(e);
                mView.onFindAdoptLocationFinish(null);
            }
        });
    }
}