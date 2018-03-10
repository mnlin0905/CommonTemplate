package com.acchain.community.presenter;


import com.acchain.community.activity.index.PreSellGoodsActivity;
import com.acchain.community.base.BaseHttpBean;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.bean.PreSellGoodsDetail;
import com.acchain.community.contract.PreSellGoodsContract;
import com.acchain.community.util.HttpListener;
import com.acchain.community.util.HTTPException;

import javax.inject.Inject;

/**
 * function---- PreSellGoodsPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/12 12:54:14 (+0000).
 */
public class PreSellGoodsPresenter extends BasePresenter<PreSellGoodsActivity> implements PreSellGoodsContract.Presenter{
    @Inject
    public PreSellGoodsPresenter() {}

    @Override
    public void getPreSellProductDetail(int presellId) {
        requestHttp(httpInterface.getPresellProductDetail(presellId), new HttpListener<BaseHttpBean<PreSellGoodsDetail>>() {
            @Override
            public void onSuccess(BaseHttpBean<PreSellGoodsDetail> response) {
                mView.onPreSellProductDetailFinish(response.dataSet);
            }

            @Override
            public void onFailed(Throwable e) {
                super.onFailed(e);
                e.printStackTrace();
                mView.onPreSellProductDetailFinish(null);
            }

            @Override
            public void onFailed(HTTPException e) {
                super.onFailed(e);
                mView.onPreSellProductDetailFinish(null);
            }
        });
    }
}