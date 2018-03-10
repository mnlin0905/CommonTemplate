package com.acchain.community.activity.person;

import android.os.Bundle;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.NewsListContract;
import com.acchain.community.presenter.NewsListPresenter;
import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * function---- NewsListActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/23 03:34:03 (+0000).
 */
@Route(path = ARouterConst.Activity_NewsListActivity)
public class NewsListActivity extends BaseActivity<NewsListPresenter> implements NewsListContract.View{

    @Override
    protected int getContentViewId() {
        return R.layout.activity_news_list;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {}

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

}