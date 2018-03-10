package com.acchain.community.presenter;


import com.acchain.community.activity.index.ConfirmExerciseOrderActivity;
import com.acchain.community.base.BaseHttpBean;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.bean.CommitOrderRequest;
import com.acchain.community.bean.ExercisePay;
import com.acchain.community.contract.ConfirmExerciseOrderContract;
import com.acchain.community.util.HttpListener;
import com.acchain.community.util.HTTPException;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * function---- ConfirmExerciseOrderPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/16 09:05:47 (+0000).
 */
public class ConfirmExerciseOrderPresenter extends BasePresenter<ConfirmExerciseOrderActivity> implements ConfirmExerciseOrderContract.Presenter{
    @Inject
    public ConfirmExerciseOrderPresenter() {}

    @Override
    public void commitExerciseOrder(int productType, String token, int itemCount, int productSubId, int productId, String productAttrValueIds, int addressId) {
        CommitOrderRequest CommitOrderRequest = new CommitOrderRequest(token, productType, itemCount, productSubId,productId, productAttrValueIds, addressId);
        List<CommitOrderRequest> list=new ArrayList<>();
        list.add(CommitOrderRequest);
        requestHttp(httpInterface.commitExerciseOrder(list), new HttpListener<BaseHttpBean<ExercisePay>>() {
            @Override
            public void onSuccess(BaseHttpBean<ExercisePay> response) {
               mView.onCommitExerciseOrderFinish(response.dataSet);
            }

            @Override
            public void onFailed(Throwable e) {
                super.onFailed(e);
                e.printStackTrace();
                mView.onCommitExerciseOrderFinish(null);
            }

            @Override
            public void onFailed(HTTPException e) {
                super.onFailed(e);
                mView.onCommitExerciseOrderFinish(null);
            }
        });
    }
}