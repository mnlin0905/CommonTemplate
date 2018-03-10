package com.acchain.community.activity.person;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.BanCommandContract;
import com.acchain.community.presenter.BanCommandPresenter;
import com.acchain.community.util.ActivityUtil;
import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * function---- BanCommandActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/27 02:22:23 (+0000).
 */
@Route(path = ARouterConst.Activity_BanCommandActivity)
public class BanCommandActivity extends BaseActivity<BanCommandPresenter> implements BanCommandContract.View, View.OnLongClickListener {

    @BindView(R.id.tv_command)
    TextView mTvCommand;
    @BindView(R.id.bt_ban)
    Button mBtBan;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_ban_command;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //长按复制
        mTvCommand.setOnLongClickListener(this);
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @OnClick(R.id.bt_ban)
    public void onViewClicked() {

    }

    @Override
    public boolean onLongClick(View v) {
        String s = mTvCommand.getText().toString();
        if (TextUtils.isEmpty(s)) {

        } else {
            showToast("口令已复制");
            ActivityUtil.saveMsgToClipboard(this, s);
        }
        return true;
    }
}