package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.NewsContract;
import com.acchain.community.activity.person.NewsActivity;

import javax.inject.Inject;

/**
 * function---- NewsPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/22 13:43:05 (+0000).
 */
public class NewsPresenter extends BasePresenter<NewsActivity> implements NewsContract.Presenter{
    @Inject
    public NewsPresenter() {}

}