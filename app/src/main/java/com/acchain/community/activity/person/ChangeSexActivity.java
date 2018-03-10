package com.acchain.community.activity.person;

import android.os.Bundle;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.bean.GetAccountBean;
import com.acchain.community.contract.ChangeSexContract;
import com.acchain.community.presenter.ChangeSexPresenter;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.view.LineMenuView;
import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;

/**
 * function---- ChangeSexActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/15 12:39:14 (+0000).
 */
@Route(path = ARouterConst.Activity_ChangeSexActivity)
public class ChangeSexActivity extends BaseActivity<ChangeSexPresenter> implements ChangeSexContract.View, LineMenuView.LineMenuListener {

    @BindView(R.id.lmv_man)
    LineMenuView mLmvMan;
    @BindView(R.id.lmv_woman)
    LineMenuView mLmvWoman;

    /**
     * 1:男
     * 2:女
     */
    private int currentSex;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_change_sex;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //设置性别
        mLmvMan.setRightSelect(BasePresenter.singleAccountBean.getSex() == 1);
        mLmvWoman.setRightSelect(BasePresenter.singleAccountBean.getSex() == 2);
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @Override
    public boolean performClickLeft(TextView v) {
        return false;
    }

    @Override
    public boolean performClickRight(TextView v) {
        return false;
    }

    @Override
    public void performSelf(LineMenuView v) {
        currentSex = v == mLmvMan ? 1 : 2;
        mPresenter.setPersonalInfo(DefaultPreferenceUtil.getInstance().getToken(), currentSex, null, null);
    }

    @Override
    public void getAccountFinish(GetAccountBean accountBean) {
        super.getAccountFinish(accountBean);

        //更新视图
        updateSex();
    }

    @Override
    public void setPersonalInfoFinish() {
        super.setPersonalInfoFinish();
        mPresenter.getAccount(DefaultPreferenceUtil.getInstance().getToken());
    }

    /**
     * 更改显示 1男,2女
     */
    private void updateSex() {
        mLmvMan.setRightSelect(currentSex == 1);
        mLmvWoman.setRightSelect(currentSex == 2);
    }
}