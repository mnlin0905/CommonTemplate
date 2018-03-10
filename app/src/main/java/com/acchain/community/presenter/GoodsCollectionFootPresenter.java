package com.acchain.community.presenter;


import com.acchain.community.base.BaseHttpBean;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.bean.AdoptGoodDetail;
import com.acchain.community.bean.ExerciseGoodsDetail;
import com.acchain.community.bean.PanicGoodDetail;
import com.acchain.community.bean.PreSellGoodsDetail;
import com.acchain.community.contract.GoodsCollectionFootContract;
import com.acchain.community.fragment.GoodsCollectionFootFragment;
import com.acchain.community.util.HttpListener;
import com.acchain.community.util.HTTPException;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * function---- GoodsCollectionFootPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/23 07:05:11 (+0000).
 */
public class GoodsCollectionFootPresenter extends BasePresenter<GoodsCollectionFootFragment> implements GoodsCollectionFootContract.Presenter{
    @Inject
    public GoodsCollectionFootPresenter() {}

    @Override
    public void getUserCollections(String token, String commodityId, String commodityType, int page, int pageSize) {
        requestHttp(httpInterface.getUserCollections(token,commodityId,commodityType,page,pageSize),
                bean -> mView.getUserCollectionsFinish(bean.dataSet),
                (Consumer<Throwable>) throwable -> mView.getUserCollectionsFinish(null));
    }

    @Override
    public void delCollections(String token, String ids) {
        requestHttp(httpInterface.delCollections(token, ids), objectBaseHttpBean -> mView.delCollectionsFinish());
    }

    @Override
    public void getAccessLog(String token, String deviceId, int page, int pageSize) {
        requestHttp(httpInterface.getAccessLog(token,deviceId,page,pageSize),
                bean -> mView.getAccessLogFinish(bean.dataSet),
                (Consumer<Throwable>) throwable -> mView.getAccessLogFinish(null));
    }

    @Override
    public void delAccessLogs(String token, String ids) {
        requestHttp(httpInterface.delAccessLogs(token, ids), objectBaseHttpBean -> mView.delAccessLogsFinish());
    }

    /**
     * 预售
     */
    @Override
    public void getPreSellProductDetail(int presellId,HttpCallback<PreSellGoodsDetail> httpCallback) {
        requestHttp(httpInterface.getPresellProductDetail(presellId), new HttpListener<BaseHttpBean<PreSellGoodsDetail>>() {
            @Override
            public void onSuccess(BaseHttpBean<PreSellGoodsDetail> response) {
                httpCallback.run(response.dataSet);
            }

            @Override
            public void onFailed(Throwable e) {
                super.onFailed(e);
                e.printStackTrace();
                httpCallback.run(null);
            }

            @Override
            public void onFailed(HTTPException e) {
                super.onFailed(e);
                httpCallback.run(null);
            }
        });
    }

    /**
     * @param adoptId 领养
     */
    @Override
    public void getAdoptProductDetail(int adoptId,HttpCallback<AdoptGoodDetail> httpCallback) {
        requestHttp(httpInterface.getAdoptProductDetail(adoptId), new HttpListener<BaseHttpBean<AdoptGoodDetail>>() {
            @Override
            public void onSuccess(BaseHttpBean<AdoptGoodDetail> response) {
                httpCallback.run(response.dataSet);
            }

            @Override
            public void onFailed(Throwable e) {
                super.onFailed(e);
                e.printStackTrace();
                httpCallback.run(null);
            }

            @Override
            public void onFailed(HTTPException e) {
                super.onFailed(e);
                httpCallback.run(null);
            }
        });
    }

    /**
     * @param exerciseId 行权
     */
    @Override
    public void getExerciseProductDetail(int exerciseId,HttpCallback<ExerciseGoodsDetail> httpCallback) {
        requestHttp(httpInterface.getExerciseProductDetail(exerciseId), new HttpListener<BaseHttpBean<ExerciseGoodsDetail>>() {
            @Override
            public void onSuccess(BaseHttpBean<ExerciseGoodsDetail> response) {
                httpCallback.run(response.dataSet);
            }

            @Override
            public void onFailed(Throwable e) {
                super.onFailed(e);
                e.printStackTrace();
                httpCallback.run(null);
            }

            @Override
            public void onFailed(HTTPException e) {
                super.onFailed(e);
                httpCallback.run(null);
            }
        });
    }

    /**
     * 获取限时抢购详情
     *
     * @param purchaseId   商品id
     * @param flashSaleId  活动id
     */
    @Override
    public void getFlashSalesDetail(int purchaseId, int flashSaleId, HttpCallback<PanicGoodDetail> httpCallback) {
        requestHttp(httpInterface.getFlashSalesDetail(purchaseId,flashSaleId), new HttpListener<BaseHttpBean<PanicGoodDetail>>() {
            @Override
            public void onSuccess(BaseHttpBean<PanicGoodDetail> response) {
                httpCallback.run(response.dataSet);
            }

            @Override
            public void onFailed(Throwable e) {
                super.onFailed(e);
                e.printStackTrace();
                httpCallback.run(null);
            }

            @Override
            public void onFailed(HTTPException e) {
                super.onFailed(e);
                httpCallback.run(null);
            }
        });

    }
}