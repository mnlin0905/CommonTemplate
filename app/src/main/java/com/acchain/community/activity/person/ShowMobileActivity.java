package com.acchain.community.activity.person;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.ShowMobileContract;
import com.acchain.community.presenter.ShowMobilePresenter;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * function---- ShowMobileActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/20 07:23:31 (+0000).
 */
@Route(path = ARouterConst.Activity_ShowMobileActivity, extras = ARouterConst.FLAG_PHONE)
public class ShowMobileActivity extends BaseActivity<ShowMobilePresenter> implements ShowMobileContract.View {
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.bt_change)
    Button mBtChange;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_show_mobile;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mTvPhone.setText(BasePresenter.singleAccountBean.getMobile());
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @OnClick({R.id.bt_change})
    public void onViewClicked(View view) {
        ARouter.getInstance().build(ARouterConst.Activity_ChangeMobileActivity).navigation();
    }
}