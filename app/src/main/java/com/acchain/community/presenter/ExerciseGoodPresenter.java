package com.acchain.community.presenter;

import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.ExerciseGoodContract;
import com.acchain.community.fragment.ExerciseGoodFragment;

import javax.inject.Inject;

/**
 * function---- ExerciseGoodsPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/16 08:31:29 (+0000).
 */
public class ExerciseGoodPresenter extends BasePresenter<ExerciseGoodFragment> implements ExerciseGoodContract.Presenter{
    @Inject
    public ExerciseGoodPresenter() {

    }
}