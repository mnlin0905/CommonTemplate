package com.acchain.community.activity.index;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import com.acchain.community.R;
import com.acchain.community.adapter.PreSellAdapter;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.bean.PreSellProduct;
import com.acchain.community.contract.PreSellContract;
import com.acchain.community.presenter.PreSellPresenter;
import com.acchain.community.util.L;
import com.acchain.community.util.ScreenUtils;
import com.acchain.community.window.popupwindow.ClassificationPopup;
import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;

/**
 * function---- PreSellActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/12 11:23:32 (+0000).
 */
@Route(path = ARouterConst.Activity_PreSellActivity)
public class PreSellActivity extends BaseActivity<PreSellPresenter> implements PreSellContract.View {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private ClassificationPopup classificationPopup;
    private PreSellAdapter preSellAdapter;

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
        switch (item.getItemId()) {
            case R.id.classification:
                if (classificationPopup == null) {
                    classificationPopup = new ClassificationPopup(this);
                    classificationPopup.setOnItemListener(position -> {
                    });
                }
                classificationPopup.showAtLocation(toolbar, Gravity.TOP, 0, toolbar.getHeight() + ScreenUtils.getStatusHeight(this));
                break;
        }
        return true;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        preSellAdapter=new PreSellAdapter(this);
        recycler.setAdapter(preSellAdapter);
        mPresenter.getPreSellProducts(1);
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @Override
    public void onPreSellProductFinish(PreSellProduct preSellProduct) {
        if(preSellProduct==null){
            L.i("failed");
        }else {
            preSellAdapter.setData(preSellProduct.getList());
        }
    }
}