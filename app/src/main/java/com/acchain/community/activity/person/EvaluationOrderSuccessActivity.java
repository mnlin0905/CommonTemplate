package com.acchain.community.activity.person;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.EvaluationOrderSuccessContract;
import com.acchain.community.presenter.EvaluationOrderSuccessPresenter;
import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * function---- EvaluationOrderSuccessActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/02/06 09:14:59 (+0000).
 */
@Route(path = ARouterConst.Activity_EvaluationOrderSuccessActivity)
public class EvaluationOrderSuccessActivity extends BaseActivity<EvaluationOrderSuccessPresenter> implements EvaluationOrderSuccessContract.View{

    @Override
    protected int getContentViewId() {
        return R.layout.activity_evaluation_order_success;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_text_finish, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO: 2018/2/6 提交信息
        return true;
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

}