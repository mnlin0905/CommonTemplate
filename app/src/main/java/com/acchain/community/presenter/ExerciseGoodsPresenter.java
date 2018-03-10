package com.acchain.community.presenter;


import com.acchain.community.activity.index.ExerciseGoodsActivity;
import com.acchain.community.base.BaseHttpBean;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.bean.ExerciseGoodsDetail;
import com.acchain.community.contract.ExerciseGoodsContract;
import com.acchain.community.util.HttpListener;
import com.acchain.community.util.HTTPException;

import javax.inject.Inject;

/**
 * function---- ExerciseGoodsPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/16 08:31:29 (+0000).
 */
public class ExerciseGoodsPresenter extends BasePresenter<ExerciseGoodsActivity> implements ExerciseGoodsContract.Presenter{
    @Inject
    public ExerciseGoodsPresenter() {

    }

    @Override
    public void getExerciseProductDetail(int exerciseId) {
        requestHttp(httpInterface.getExerciseProductDetail(exerciseId), new HttpListener<BaseHttpBean<ExerciseGoodsDetail>>() {
            @Override
            public void onSuccess(BaseHttpBean<ExerciseGoodsDetail> response) {
                mView.onExerciseProductDetailFinish(response.dataSet);
            }

            @Override
            public void onFailed(Throwable e) {
                super.onFailed(e);
                e.printStackTrace();
                mView.onExerciseProductDetailFinish(null);
            }

            @Override
            public void onFailed(HTTPException e) {
                super.onFailed(e);
                mView.onExerciseProductDetailFinish(null);
            }
        });
    }
}