package com.acchain.community.activity.person;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.StaffServiceChangeTransContract;
import com.acchain.community.presenter.StaffServiceChangeTransPresenter;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * function---- StaffServiceChangeTransActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/31 09:09:46 (+0000).
 */
@Route(path = ARouterConst.Activity_StaffServiceChangeTransActivity)
public class StaffServiceChangeTransActivity extends BaseActivity<StaffServiceChangeTransPresenter> implements StaffServiceChangeTransContract.View {

    @BindView(R.id.bt_apply)
    Button mBtApply;
    @BindView(R.id.cb_verity)
    CheckBox mCbVerity;
    @BindView(R.id.cb_upload)
    CheckBox mCbUpload;
    @BindView(R.id.cb_success)
    CheckBox mCbSuccess;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_staff_service_change_trans;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }


    @OnClick(R.id.bt_apply)
    public void onViewClicked() {
        ARouter.getInstance().build(ARouterConst.Activity_InputPhoneTransPasswordActivity).navigation();
    }
}