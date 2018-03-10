package com.acchain.community.activity.person;

import android.os.Bundle;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.PrivacyContract;
import com.acchain.community.presenter.PrivacyPresenter;
import com.acchain.community.view.LineMenuView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * function---- PrivacyActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/22 09:51:28 (+0000).
 */
@Route(path = ARouterConst.Activity_PrivacyActivity)
public class PrivacyActivity extends BaseActivity<PrivacyPresenter> implements PrivacyContract.View, LineMenuView.LineMenuListener {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_privacy;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @Override
    public boolean performClickLeft(TextView v) {
        int position = ((int) v.getTag(R.id.LINE_MENU_VIEW_TAG_POSITION));
        if (position == 9 || position == 10) {
            showSnackbar("关闭后，你其他用户在转账、添加朋友时将不能通过手机号/微脉号搜索到你。", "明白", null);
            return true;
        }
        return false;
    }

    @Override
    public boolean performClickRight(TextView v) {
        return false;
    }

    @Override
    public void performSelf(LineMenuView v) {
        int position = (int) v.getTag(R.id.LINE_MENU_VIEW_TAG_POSITION);
        switch (position) {
            case 0://隐私声明
                ARouter.getInstance().build(ARouterConst.Activity_PrivacyDeclareActivity).navigation();
                break;
            // TODO: 2018/1/22 dosomethings
            case 12://停用微口令
                ARouter.getInstance().build(ARouterConst.Activity_BanCommandActivity).navigation();
                break;
        }
    }
}