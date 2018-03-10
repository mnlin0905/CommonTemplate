package com.acchain.community.activity.person;

import android.os.Bundle;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.bean.TransPasswordStatus;
import com.acchain.community.contract.SelectChangeTransPasswordTypeContract;
import com.acchain.community.presenter.SelectChangeTransPasswordTypePresenter;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.view.LineMenuView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;

import static com.acchain.community.util.Const.KEY_TRANS_PWD_STATUS;

/**
 * function---- SelectChangeTransPasswordTypeActivity
 * <p>
 * 选择修改交易密码的方式
 * 必须是已经设置过交易密码才可以进入
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/19 12:09:02 (+0000).
 */
@Route(path = ARouterConst.Activity_SelectChangeTransPasswordTypeActivity, extras = ARouterConst.FLAG_TRANSACTION_PASSWORD|ARouterConst.FLAG_ACTIVITY_CLEAR_TOP)
public class SelectChangeTransPasswordTypeActivity extends BaseActivity<SelectChangeTransPasswordTypePresenter> implements SelectChangeTransPasswordTypeContract.View, LineMenuView.LineMenuListener {

    @BindView(R.id.lmv_old_password)
    LineMenuView mLmvOldPassword;
    @BindView(R.id.lmv_face_phone)
    LineMenuView mLmvFacePhone;
    @BindView(R.id.lmv_service_check)
    LineMenuView mLmvServiceCheck;

    /**
     * 人工审核状态
     */
    private TransPasswordStatus transPasswordStatus;
    private boolean hasCheckStatus;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_select_change_trans_password_type;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getPayPwdArtificial(DefaultPreferenceUtil.getInstance().getToken());
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
        //使用旧密码修改
        if (v == mLmvOldPassword) {
            ARouter.getInstance().build(ARouterConst.Activity_InputOldTransPasswordActivity).navigation();
            return;
        }

        //使用手机验证码修改
        if (v == mLmvFacePhone) {
            ARouter.getInstance().build(ARouterConst.Activity_VerityIDWhenChangeTransActivity).navigation();
            return;
        }

        //使用人工审核修改,如果已经在进行了人工审核,则跳转到结果界面,否则重新开始审核
        if (v == mLmvServiceCheck) {
            if (!hasCheckStatus) {
                showToast("正在查询审核状态,请稍后");
                return;
            }

            //如果为待审核,已通过,未通过三种状态,则跳转到结果界面
            if (transPasswordStatus != null &&
                    transPasswordStatus.getStatus() >= 0 &&
                    transPasswordStatus.getStatus() <= 2) {
                ARouter.getInstance()
                        .build(ARouterConst.Activity_ChangeTransResultActivity)
                        .withObject(KEY_TRANS_PWD_STATUS,transPasswordStatus)
                        .navigation();
            } else {
                ARouter.getInstance().build(ARouterConst.Activity_StaffServiceChangeTransActivity).navigation();
            }
        }
    }

    @Override
    public void getPayPwdArtificialFinish(TransPasswordStatus status) {
        hasCheckStatus = true;
        this.transPasswordStatus = status;
    }
}