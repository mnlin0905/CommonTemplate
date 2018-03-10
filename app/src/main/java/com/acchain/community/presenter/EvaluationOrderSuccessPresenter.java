package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.EvaluationOrderSuccessContract;
import com.acchain.community.activity.person.EvaluationOrderSuccessActivity;

import javax.inject.Inject;

/**
 * function---- EvaluationOrderSuccessPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/02/06 09:14:59 (+0000).
 */
public class EvaluationOrderSuccessPresenter extends BasePresenter<EvaluationOrderSuccessActivity> implements EvaluationOrderSuccessContract.Presenter{
    @Inject
    public EvaluationOrderSuccessPresenter() {}

}