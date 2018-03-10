package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.EvaluationOrderContract;
import com.acchain.community.activity.person.EvaluationOrderActivity;

import javax.inject.Inject;

/**
 * function---- EvaluationOrderPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/02/06 05:59:13 (+0000).
 */
public class EvaluationOrderPresenter extends BasePresenter<EvaluationOrderActivity> implements EvaluationOrderContract.Presenter{
    @Inject
    public EvaluationOrderPresenter() {}

}