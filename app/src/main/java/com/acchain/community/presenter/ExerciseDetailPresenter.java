package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.ExerciseDetailContract;
import com.acchain.community.fragment.ExerciseDetailFragment;

import javax.inject.Inject;

/**
 * function---- ExerciseDetailPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/30 11:39:29 (+0000).
 */
public class ExerciseDetailPresenter extends BasePresenter<ExerciseDetailFragment> implements ExerciseDetailContract.Presenter{
    @Inject
    public ExerciseDetailPresenter() {}

}