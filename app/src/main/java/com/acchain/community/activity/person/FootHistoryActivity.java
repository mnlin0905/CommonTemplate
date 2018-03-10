package com.acchain.community.activity.person;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.acchain.community.R;
import com.acchain.community.activity.person.GoodsCollectionActivity;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.interfaces.OnGoodsOperateListener;
import com.acchain.community.util.Const;
import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;

@Route(path = ARouterConst.Activity_FootHistoryActivity)
public class FootHistoryActivity extends GoodsCollectionActivity {
    @Override
    protected int getContentViewId() {
        return R.layout.activity_foot_history;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //手动初始化变量
        super.initData(savedInstanceState);
    }

    @Override
    protected void initFragment() {
        fragments = new ArrayList<>(1);
        Fragment goods = getSupportFragmentManager().findFragmentByTag("goods");
        Bundle bundle = new Bundle();
        bundle.putInt(Const.KEY_POSITION, 5);
        goods.setArguments(bundle);
        fragments.add((OnGoodsOperateListener) goods);
    }
}
