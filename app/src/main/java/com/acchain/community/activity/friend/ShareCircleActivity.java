package com.acchain.community.activity.friend;

import android.os.Bundle;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.ShareCircleContract;
import com.acchain.community.presenter.ShareCirclePresenter;
import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * function---- ShareCircleActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/12 09:29:46 (+0000).
 */
@Route(path = ARouterConst.Activity_ShareCircleActivity)
public class ShareCircleActivity extends BaseActivity<ShareCirclePresenter> implements ShareCircleContract.View{

    @Override
    protected int getContentViewId() {
        return R.layout.activity_circle;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

}