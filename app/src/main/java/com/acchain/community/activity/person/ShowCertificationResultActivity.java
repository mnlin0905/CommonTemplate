package com.acchain.community.activity.person;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.ShowCertificationResultContract;
import com.acchain.community.drawable.ColorTextDrawable;
import com.acchain.community.presenter.ShowCertificationResultPresenter;
import com.acchain.community.view.LineMenuView;
import com.acchain.community.window.AlertCloseDialogFragment;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;

import static com.acchain.community.base.BasePresenter.singleAccountBean;

/**
 * function---- ShowCertificationResultActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/17 08:00:22 (+0000).
 */
@Route(path = ARouterConst.Activity_ShowCertificationResultActivity, extras = ARouterConst.FLAG_VERIFY_HAS_BEGIN)
public class ShowCertificationResultActivity extends BaseActivity<ShowCertificationResultPresenter> implements ShowCertificationResultContract.View, LineMenuView.LineMenuListener, AlertCloseDialogFragment.OnAlertListener {

    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.tv_message)
    TextView mTvMessage;
    @BindView(R.id.lmv_name)
    LineMenuView mLmvName;
    @BindView(R.id.lmv_type)
    LineMenuView mLmvType;
    @BindView(R.id.lmv_number)
    LineMenuView mLmvNumber;
    @BindView(R.id.lmv_info)
    LineMenuView mLmvInfo;

    /**
     * 实名认证状态
     * 0:审核中
     * 1:通过
     * 2:失败
     */
    private int certificationStatus;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_show_certification_result;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        certificationStatus = singleAccountBean.getTbMemberVerifiedInfo().getStatus();
        int cardType = singleAccountBean.getTbMemberVerifiedInfo().getCardType();
        //图标
        int[] drawable = {R.drawable.icon_certification_access,
                R.drawable.icon_certification_success,
                R.drawable.icon_certification_fail,
        };
        //提示性信息
        String[] message = {"你的实名认证正在审核中",
                "你的实名认证已通过",
                "你的实名认证审核不成功"
        };
        //状态文字
        String[] navigation = {"审核中",
                "已通过",
                "未通过",
        };
        //状态颜色
        int[] navigationColor = {
                R.color.green_text_9952ff78,
                R.color.white_text_alpha_60,
                R.color.yellow_text_color_60
        };

        if (certificationStatus<0||certificationStatus>2) {
            showToast("异常认证状态");
            finish();
            return;
        }

        if (cardType==-1) {
            showToast("证件类型异常");
            finish();
            return;
        }

        //初始化界面
        mIvIcon.setImageResource(drawable[certificationStatus]);
        mTvMessage.setText(message[certificationStatus]);
        mLmvName.setBrief(singleAccountBean.getTbMemberVerifiedInfo().getName());
        mLmvType.setBrief(getResources().getStringArray(R.array.person_card_type)[cardType]);
        mLmvNumber.setBrief(singleAccountBean.getTbMemberVerifiedInfo().getIdcard());
        setStatus(navigation[certificationStatus], navigationColor[certificationStatus], certificationStatus == 2);
    }

    /**
     * 设置审核状态
     *
     * @param navigation      文字
     * @param navigationColor 颜色
     * @param showBrief       显示brief文字
     */
    private void setStatus(String navigation, @ColorRes int navigationColor, boolean showBrief) {
        ColorTextDrawable textDrawable = new ColorTextDrawable(this).setText(navigation)
                .setColor(dispatchGetColor(navigationColor))
                .setTextSize(getResources().getDimensionPixelSize(R.dimen.text_size_normal_14sp));
        textDrawable.setBounds(0, 0, textDrawable.getIntrinsicWidth(), textDrawable.getIntrinsicHeight());
        mLmvInfo.setBrief(showBrief ? "查看原因" : "");
        mLmvInfo.setNavigation(textDrawable);
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @Override
    public boolean performClickLeft(TextView v) {
        return true;
    }

    @Override
    public boolean performClickRight(TextView v) {
        return false;
    }

    @Override
    public void performSelf(LineMenuView v) {
        if (v == mLmvInfo && certificationStatus == 2) {
            new AlertCloseDialogFragment()
                    .setConfirmText("重新认证")
                    .setTitle(singleAccountBean.getTbMemberVerifiedInfo().getNotPassing())
                    .setOnAlertListener(this)
                    .show(getSupportFragmentManager(), "certification");
        }
    }

    @Override
    public boolean onClose(Dialog dialog) {
        return false;
    }

    @Override
    public boolean onConfirm(Dialog dialog) {
        ARouter.getInstance().build(ARouterConst.Activity_BeginCertificationActivity).navigation();
        return false;
    }
}