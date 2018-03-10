package com.acchain.community.activity.person;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.bean.TransPasswordStatus;
import com.acchain.community.contract.ChangeTransResultContract;
import com.acchain.community.presenter.ChangeTransResultPresenter;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.window.CancelConfirmDialogFragment;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import butterknife.OnClick;

import static com.acchain.community.util.Const.KEY_STAFF_SERVICE_ID;
import static com.acchain.community.util.Const.KEY_TRANS_PWD_STATUS;

/**
 * function---- ChangeTransResultActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/31 12:29:57 (+0000).
 */
@Route(path = ARouterConst.Activity_ChangeTransResultActivity)
public class ChangeTransResultActivity extends BaseActivity<ChangeTransResultPresenter> implements ChangeTransResultContract.View, CancelConfirmDialogFragment.OnOperateListener {

    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.tv_status)
    TextView mTvStatus;
    @BindView(R.id.tv_info)
    TextView mTvInfo;
    @BindView(R.id.bt_cancel_apply)
    Button mBtCancelApply;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_change)
    TextView mTvChange;
    @BindView(R.id.tv_declare)
    TextView mTvDeclare;

    /**
     * 人工申请的状态
     */
    @Autowired(required = true, name = KEY_TRANS_PWD_STATUS)
    TransPasswordStatus transPasswordStatus;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_change_trans_result;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        setToolbarNavText(toolbar, "关闭", R.color.white);

        /*
        * 当前状态
        * 显示的消息信息
        * 按钮文字
        * 切换按钮文字的可见性
        * 说明性文字的可见性
        * */
        String[] statusMsgs = {
                "您已经申请了服务",
                "审核通过",
                "审核不通过"
        };
        String[] infoMsgs = {
                "重置交易密码",
                "您提交的账户资料已通过审核",
                "您提交的资料信息与账户不匹配"
        };
        String[] buttonMsgs = {
                "取消并重新申请",
                "重置交易密码",
                "重新申请"
        };
        int[] switchVisibility = {
                View.GONE, View.GONE, View.VISIBLE
        };
        int[] declareVisibility = {
                View.GONE, View.VISIBLE, View.GONE
        };

        int status = transPasswordStatus.getStatus();

        mTvStatus.setText(statusMsgs[status]);
        mTvInfo.setText(infoMsgs[status]);
        mBtCancelApply.setText(buttonMsgs[status]);
        mTvChange.setVisibility(switchVisibility[status]);
        mTvDeclare.setVisibility(declareVisibility[status]);
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @OnClick({R.id.bt_cancel_apply, R.id.tv_change})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_cancel_apply:// 根据状态处理不同逻辑:
                switch (transPasswordStatus.getStatus()) {
                    case 1://如果成功,则去修改密码
                        ARouter.getInstance()
                                .build(ARouterConst.Activity_ChangeTransPasswordActivity)
                                .withInt(KEY_STAFF_SERVICE_ID, transPasswordStatus.getId())
                                .navigation();
                        break;
                    case 2://失败,重新审核(在重置审核状态之后)
                        mPresenter.cancelArtificial(DefaultPreferenceUtil.getInstance().getToken(), transPasswordStatus.getId());
                        break;
                    case 0://正在审核中
                        new CancelConfirmDialogFragment()
                                .setTitle("当前正在处理中,是否确认重新审核?")
                                .setCancelText("重新申请")
                                .setConfirmText("继续等待")
                                .setOnOperateListener(this)
                                .show(getSupportFragmentManager(), "cancel_staff");
                }
                break;
            case R.id.tv_change://更换验证方式
                ARouter.getInstance().build(ARouterConst.Activity_SelectChangeTransPasswordTypeActivity).navigation();
                break;
        }
    }

    @Override
    public void cancelArtificialFinish() {
        super.cancelArtificialFinish();
        ARouter.getInstance().build(ARouterConst.Activity_SelectChangeTransPasswordTypeActivity).navigation();
    }


    @Override
    public boolean onCancel(Dialog dialog) {
        //重新申请
        mPresenter.cancelArtificial(DefaultPreferenceUtil.getInstance().getToken(), transPasswordStatus.getId());
        return false;
    }

    @Override
    public boolean onConfirm(Dialog dialog) {
        return false;
    }
}