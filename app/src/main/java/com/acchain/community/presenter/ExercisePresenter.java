package com.acchain.community.presenter;


import com.acchain.community.base.BaseHttpBean;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.bean.ExerciseProduct;
import com.acchain.community.contract.ExerciseContract;
import com.acchain.community.activity.index.ExerciseActivity;
import com.acchain.community.util.HttpListener;
import com.acchain.community.util.HTTPException;

import javax.inject.Inject;

/**
 * function---- ExercisePresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/16 07:41:57 (+0000).
 */
public class ExercisePresenter extends BasePresenter<ExerciseActivity> implements ExerciseContract.Presenter{
    @Inject
    public ExercisePresenter() {}

    @Override
    public void getExerciseProducts(int pageIndex) {
        requestHttp(httpInterface.getExerciseProducts(pageIndex), new HttpListener<BaseHttpBean<ExerciseProduct>>() {
            @Override
            public void onSuccess(BaseHttpBean<ExerciseProduct> response) {
                mView.onExerciseProductsFinish(response.dataSet);
            }

            @Override
            public void onFailed(Throwable e) {
                super.onFailed(e);
                e.printStackTrace();
                mView.onExerciseProductsFinish(null);
            }

            @Override
            public void onFailed(HTTPException e) {
                super.onFailed(e);
                mView.onExerciseProductsFinish(null);
            }
        });
    }
}