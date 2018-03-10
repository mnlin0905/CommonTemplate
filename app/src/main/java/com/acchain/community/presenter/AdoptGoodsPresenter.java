package com.acchain.community.presenter;


import com.acchain.community.activity.index.AdoptGoodsActivity;
import com.acchain.community.base.BaseHttpBean;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.bean.AdoptGoodDetail;
import com.acchain.community.contract.AdoptGoodsContract;
import com.acchain.community.util.HttpListener;
import com.acchain.community.util.HTTPException;

import javax.inject.Inject;

/**
 * function---- AdoptGoodsPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/16 03:01:17 (+0000).
 */
public class AdoptGoodsPresenter extends BasePresenter<AdoptGoodsActivity> implements AdoptGoodsContract.Presenter{
    @Inject
    public AdoptGoodsPresenter() {}

    @Override
    public void getAdoptProductDetail(int adoptId) {
            requestHttp(httpInterface.getAdoptProductDetail(adoptId), new HttpListener<BaseHttpBean<AdoptGoodDetail>>() {
                @Override
                public void onSuccess(BaseHttpBean<AdoptGoodDetail> response) {
                    mView.onAdoptProductDetailFinish(response.dataSet);
                }

                @Override
                public void onFailed(Throwable e) {
                    super.onFailed(e);
                    e.printStackTrace();
                    mView.onAdoptProductDetailFinish(null);
                }

                @Override
                public void onFailed(HTTPException e) {
                    super.onFailed(e);
                    mView.onAdoptProductDetailFinish(null);
                }
            });


    }
}