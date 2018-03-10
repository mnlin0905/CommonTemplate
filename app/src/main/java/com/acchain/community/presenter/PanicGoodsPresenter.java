package com.acchain.community.presenter;


import com.acchain.community.activity.index.PanicGoodsActivity;
import com.acchain.community.base.BaseHttpBean;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.bean.PanicGoodDetail;
import com.acchain.community.contract.PanicGoodsContract;
import com.acchain.community.util.HttpListener;
import com.acchain.community.util.HTTPException;

import javax.inject.Inject;

/**
 * function---- PanicGoodsPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/09 07:43:07 (+0000).
 */
public class PanicGoodsPresenter extends BasePresenter<PanicGoodsActivity> implements PanicGoodsContract.Presenter{
    @Inject
    public PanicGoodsPresenter() {}

    @Override
    public void getFlashSalesDetail(int purchaseId,int flashSaleId) {
        requestHttp(httpInterface.getFlashSalesDetail(purchaseId,flashSaleId), new HttpListener<BaseHttpBean<PanicGoodDetail>>() {
            @Override
            public void onSuccess(BaseHttpBean<PanicGoodDetail> response) {
               mView.onFlashSalesDetailFinish(response.dataSet);
            }

            @Override
            public void onFailed(Throwable e) {
                super.onFailed(e);
                e.printStackTrace();
                mView.onFlashSalesDetailFinish(null);
            }

            @Override
            public void onFailed(HTTPException e) {
                super.onFailed(e);
                mView.onFlashSalesDetailFinish(null);
            }
        });


    }

}