package com.acchain.community.activity.index;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.acchain.community.R;
import com.acchain.community.adapter.ExerciseAdapter;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.bean.ExerciseProduct;
import com.acchain.community.contract.ExerciseContract;
import com.acchain.community.presenter.ExercisePresenter;
import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * function---- ExerciseActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/16 07:41:57 (+0000).
 */
@Route(path = ARouterConst.Activity_ExerciseActivity)
public class ExerciseActivity extends BaseActivity<ExercisePresenter> implements ExerciseContract.View {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private ExerciseAdapter exerciseAdapter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_pre_sell;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_pre_sell, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return true;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        exerciseAdapter = new ExerciseAdapter(this);
        recycler.setAdapter(exerciseAdapter);
        mPresenter.getExerciseProducts(1);
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @Override
    public void onExerciseProductsFinish(ExerciseProduct exerciseProduct) {
        if(exerciseProduct==null){

        }else {
            exerciseAdapter.setData(exerciseProduct.getList());
        }
    }
}