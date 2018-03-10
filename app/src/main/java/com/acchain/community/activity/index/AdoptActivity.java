package com.acchain.community.activity.index;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.acchain.community.R;
import com.acchain.community.adapter.AdoptAdapter;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.bean.AdoptProduct;
import com.acchain.community.contract.AdoptContract;
import com.acchain.community.presenter.AdoptPresenter;
import com.acchain.community.util.L;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * function---- AdoptActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/15 07:32:23 (+0000).
 */
@Route(path = ARouterConst.Activity_AdoptActivity)
public class AdoptActivity extends BaseActivity<AdoptPresenter> implements AdoptContract.View {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    private AdoptAdapter adoptAdapter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_adopt;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_adopt,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.classification:
                break;
        }
        return true;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        adoptAdapter=new AdoptAdapter(this);
        recycler.setAdapter(adoptAdapter);
        mPresenter.getAdoptProducts(1);
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @Override
    public void onAdoptProductFinish(AdoptProduct adoptProduct) {
        if(adoptProduct==null){
            L.i("failed");
        }else {
            adoptAdapter.setData(adoptProduct.getList());
        }
    }
}