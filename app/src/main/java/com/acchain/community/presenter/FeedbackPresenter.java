package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.FeedbackContract;
import com.acchain.community.activity.person.FeedbackActivity;

import javax.inject.Inject;

/**
 * function---- FeedbackPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/22 05:48:48 (+0000).
 */
public class FeedbackPresenter extends BasePresenter<FeedbackActivity> implements FeedbackContract.Presenter{
    @Inject
    public FeedbackPresenter() {}

}