package com.acchain.community.activity.person;

import android.os.Bundle;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.OnClick;

@Route(path = ARouterConst.Activity_ChangeTransSuccessActivity)
public class ChangeTransSuccessActivity extends BaseActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_change_trans_success;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        setToolbarNavText(toolbar, "关闭", R.color.white);
    }

    @Override
    protected void injectSelf() {
    }

    @OnClick(R.id.bt_success)
    public void onViewClicked() {
        ARouter.getInstance().build(ARouterConst.Activity_SafetyProtectionActivity).navigation();
    }
}
