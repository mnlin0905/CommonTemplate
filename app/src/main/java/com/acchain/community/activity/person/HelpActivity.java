package com.acchain.community.activity.person;

import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.HelpContract;
import com.acchain.community.presenter.HelpPresenter;
import com.acchain.community.view.LineMenuView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * function---- HelpActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/22 05:48:23 (+0000).
 */
@Route(path = ARouterConst.Activity_HelpActivity)
public class HelpActivity extends BaseActivity<HelpPresenter> implements HelpContract.View, LineMenuView.LineMenuListener {

    @BindView(R.id.sv_content)
    ScrollView mSvContent;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_help;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @OnClick({R.id.iv_question, R.id.tv_question, R.id.tv_chat, R.id.tv_feedback})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_question:// TODO: 2018/1/22 问题搜索界面 ,然后跳转到具体的问题详细activity
                break;
            case R.id.tv_question://点击常见问题,还是本界面
                mSvContent.smoothScrollTo(0, 0);
                break;
            case R.id.tv_chat:// TODO: 2018/1/22 在线客服模块
                showToast("暂未开放,敬请期待");
                break;
            case R.id.tv_feedback://意见反馈界面
                ARouter.getInstance().build(ARouterConst.Activity_FeedbackActivity).navigation();
                break;
        }
    }

    @Override
    public boolean performClickLeft(TextView v) {
        return false;
    }

    @Override
    public boolean performClickRight(TextView v) {
        int position = (int) v.getTag(R.id.LINE_MENU_VIEW_TAG_POSITION);
        switch (position) {
            case 0:
                // TODO: 2018/1/22 账户问题
                ARouter.getInstance()
                        .build(ARouterConst.Activity_QuestionListActivity)
                        .navigation();
                break;
            case 4:
                // TODO: 2018/1/22 区块链商城问题
                ARouter.getInstance()
                        .build(ARouterConst.Activity_QuestionListActivity)
                        .navigation();
                break;
            default:
                return false;
        }
        return true;
    }

    @Override
    public void performSelf(LineMenuView v) {
        int position = (int) v.getTag(R.id.LINE_MENU_VIEW_TAG_POSITION);
        switch (position) {
            // TODO: 2018/1/22 点击具体item传入的参数
        }
        ARouter.getInstance()
                .build(ARouterConst.Activity_QuestionActivity)
                .navigation();
    }
}