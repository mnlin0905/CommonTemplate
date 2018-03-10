package com.acchain.community.activity.person;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.SoftSettingContract;
import com.acchain.community.presenter.SoftSettingPresenter;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.view.LineMenuView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * function---- SoftSettingActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/15 05:50:53 (+0000).
 */
@Route(path = ARouterConst.Activity_SoftSettingActivity)
public class SoftSettingActivity extends BaseActivity<SoftSettingPresenter> implements SoftSettingContract.View, LineMenuView.LineMenuListener {

    /**
     * 需要跳转到的活动或界面
     */
    // TODO: 2018/1/15 添加活动
    private static final String[] NAVIGATION_ACTIVITY = {
            ARouterConst.Activity_PersonInformationActivity,//个人设置
            //ARouterConst.Activity_MessageSettingsActivity,//消息设置
            ARouterConst.Activity_SafetyProtectionActivity,//安全保护
            ARouterConst.Activity_ShowMobileActivity,//手机号
            ARouterConst.Activity_ShowEmailActivity,//邮箱
            ARouterConst.Activity_HelpActivity,//帮助&反馈
            ARouterConst.Activity_AboutActivity,//关于
    };

    @BindView(R.id.lmv_person_info)
    LineMenuView mLmvPersonInfo;
    @BindView(R.id.lmv_visit_card)
    LineMenuView mLmvVisitCard;
    @BindView(R.id.lmv_message_alert)
    LineMenuView mLmvMessageAlert;
    @BindView(R.id.lmv_safe_protected)
    LineMenuView mLmvSafeProtected;
    @BindView(R.id.lmv_phone)
    LineMenuView mLmvPhone;
    @BindView(R.id.lmv_email)
    LineMenuView mLmvEmail;
    @BindView(R.id.lmv_help)
    LineMenuView mLmvHelp;
    @BindView(R.id.lmv_about)
    LineMenuView mLmvAbout;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_soft_setting;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //初始化数据
        String phone = BasePresenter.singleAccountBean.getMobile();
        String email = BasePresenter.singleAccountBean.getEmail();
        mLmvPhone.setBrief(TextUtils.isEmpty(phone) ? "未绑定" : phone);
        mLmvEmail.setBrief(TextUtils.isEmpty(email) ? "未绑定" : email);
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_service, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO: 客服中心
        return true;
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
        int position = (int) v.getTag(R.id.LINE_MENU_VIEW_TAG_POSITION);

        //position是指在布局中出现的顺序
        if (position < NAVIGATION_ACTIVITY.length) {
            ARouter.getInstance().build(NAVIGATION_ACTIVITY[position]).navigation();
        }
    }

    @OnClick(R.id.bt_exit)
    public void onViewClicked() {
        DefaultPreferenceUtil.getInstance().setToken(null);
        DefaultPreferenceUtil.getInstance().setLogin(false);
        ARouter.getInstance().build(ARouterConst.Activity_LoginActivity).navigation();
    }
}