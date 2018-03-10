package com.acchain.community.activity.wallet;

import android.os.Bundle;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.BunkersConsumeDeclareContract;
import com.acchain.community.presenter.BunkersConsumeDeclarePresenter;
import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * function---- BunkersConsumeDeclareActivity;
 * <p>
 * 燃料声明模块
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/11 13:37:06 (+0000).
 */
@Route(path = ARouterConst.Activity_BunkersConsumeDeclareActivity)
public class BunkersConsumeDeclareActivity extends BaseActivity<BunkersConsumeDeclarePresenter> implements BunkersConsumeDeclareContract.View {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_bunkers_consume_declare;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

}