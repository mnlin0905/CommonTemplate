package com.acchain.community.activity.friend;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.SendAddFriendRequestContract;
import com.acchain.community.presenter.SendAddFriendRequestPresenter;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;

import static com.acchain.community.util.Const.KEY_REMARK;
import static com.acchain.community.util.Const.KEY_TARGET_FRIEND_ID;

/**
 * function---- SendAddFriendRequestActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/23 07:45:18 (+0000).
 */
@Route(path = ARouterConst.Activity_SendAddFriendRequestActivity)
public class SendAddFriendRequestActivity extends BaseActivity<SendAddFriendRequestPresenter> implements SendAddFriendRequestContract.View {

    @BindView(R.id.info)
    EditText info;
    @Autowired(name = KEY_TARGET_FRIEND_ID, required = true)
    public String userId;
    @Autowired(name = KEY_REMARK)
    public String remarks;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_send_addfriend_request;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_send_add_friend_request, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mPresenter.sendRequest();
        return true;
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public String getMessage() {
        return info.getText().toString().trim();
    }

    @Override
    public String getRemarks() {
        return remarks;
    }

    @Override
    public void sendSuccess() {
        finish();
    }
}