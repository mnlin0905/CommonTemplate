package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.QuestionContract;
import com.acchain.community.activity.person.QuestionActivity;

import javax.inject.Inject;

/**
 * function---- QuestionPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/22 05:49:41 (+0000).
 */
public class QuestionPresenter extends BasePresenter<QuestionActivity> implements QuestionContract.Presenter{
    @Inject
    public QuestionPresenter() {}

}