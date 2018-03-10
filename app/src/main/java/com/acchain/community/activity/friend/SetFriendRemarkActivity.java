package com.acchain.community.activity.friend;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.SetFriendRemarkContract;
import com.acchain.community.presenter.SetFriendRemarkPresenter;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.StringUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.acchain.community.util.Const.KEY_NIKE_NAME;

/**
 * function---- SetFriendRemarkActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/23 06:42:14 (+0000).
 */
@Route(path = ARouterConst.Activity_SetFriendRemarkActivity)
public class SetFriendRemarkActivity extends BaseActivity<SetFriendRemarkPresenter> implements SetFriendRemarkContract.View {
    @BindView(R.id.remark)
    EditText remark;
    @BindView(R.id.confirm_button)
    Button confirmButton;
    private String defaultName;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_set_friend_remark;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        defaultName = getIntent().getStringExtra(KEY_NIKE_NAME);
        remark.setText(defaultName);
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }


    @OnClick(R.id.confirm_button)
    public void onViewClicked() {
        String newName = remark.getText().toString();
       /* if (StringUtils.isEmpty(newName)) {
            showToast("请输入备注名称");
            return;
        }*/
        if (defaultName.equals(newName) || StringUtils.isEmpty(newName)) {
            setResult(RESULT_CANCELED);
        } else {
            Intent intent = new Intent();
            intent.putExtra(KEY_NIKE_NAME, newName);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}