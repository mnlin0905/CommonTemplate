package com.acchain.community.activity.person;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.ShowEmailContract;
import com.acchain.community.presenter.ShowEmailPresenter;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * function---- ShowEmailActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/20 07:25:31 (+0000).
 */
@Route(path = ARouterConst.Activity_ShowEmailActivity,extras = ARouterConst.FLAG_EMAIL)
public class ShowEmailActivity extends BaseActivity<ShowEmailPresenter> implements ShowEmailContract.View {

    @BindView(R.id.tv_email)
    TextView mTvEmail;
    @BindView(R.id.bt_change)
    Button mBtChange;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_show_email;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTvEmail.setText(BasePresenter.singleAccountBean.getEmail());
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @OnClick({R.id.bt_change})
    public void onViewClicked(View view) {
        ARouter.getInstance().build(ARouterConst.Activity_ChangeEmailActivity).navigation();
    }
}