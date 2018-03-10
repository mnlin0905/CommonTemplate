package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.NewsListContract;
import com.acchain.community.activity.person.NewsListActivity;

import javax.inject.Inject;

/**
 * function---- NewsListPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/23 03:34:03 (+0000).
 */
public class NewsListPresenter extends BasePresenter<NewsListActivity> implements NewsListContract.Presenter{
    @Inject
    public NewsListPresenter() {}

}