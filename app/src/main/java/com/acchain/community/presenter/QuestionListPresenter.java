package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.QuestionListContract;
import com.acchain.community.activity.person.QuestionListActivity;

import javax.inject.Inject;

/**
 * function---- QuestionListPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/22 08:32:22 (+0000).
 */
public class QuestionListPresenter extends BasePresenter<QuestionListActivity> implements QuestionListContract.Presenter{
    @Inject
    public QuestionListPresenter() {}

}