package com.acchain.community.presenter;


import com.acchain.community.base.BaseHttpBean;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.bean.PanicGood;
import com.acchain.community.contract.PanicContract;
import com.acchain.community.activity.index.PanicActivity;
import com.acchain.community.util.HttpListener;
import com.acchain.community.util.HTTPException;
import com.acchain.community.util.L;

import java.util.List;

import javax.inject.Inject;

/**
 * function---- PanicPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/06 07:35:16 (+0000).
 */
public class PanicPresenter extends BasePresenter<PanicActivity> implements PanicContract.Presenter{
    @Inject
    public PanicPresenter() {}
    @Override
    public void getFlashSales(){
        requestHttp(httpInterface.getFlashSales(1), new HttpListener<BaseHttpBean<List<PanicGood>>>() {
            @Override
            public void onSuccess(BaseHttpBean<List<PanicGood>> response) {
                mView.onFlashSalesFinish(response.dataSet);
            }

            @Override
            public void onFailed(Throwable e) {
                super.onFailed(e);
                e.printStackTrace();
                L.i(e.getMessage());
                mView.onFlashSalesFinish(null);
            }

            @Override
            public void onFailed(HTTPException e) {
                super.onFailed(e);
                mView.onFlashSalesFinish(null);
            }
        });
    }
}