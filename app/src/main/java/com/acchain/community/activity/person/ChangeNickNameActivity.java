package com.acchain.community.activity.person;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.bean.GetAccountBean;
import com.acchain.community.contract.ChangeNickNameContract;
import com.acchain.community.presenter.ChangeNickNamePresenter;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.util.RegexConst;
import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;

import static com.acchain.community.base.BasePresenter.singleAccountBean;

/**
 * function---- ChangeNickNameActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/15 12:38:25 (+0000).
 */
@Route(path = ARouterConst.Activity_ChangeNickNameActivity)
public class ChangeNickNameActivity extends BaseActivity<ChangeNickNamePresenter> implements ChangeNickNameContract.View {

    @BindView(R.id.et_nick_name)
    EditText mEtNickName;

    /**
     * 成员变量
     */
    private String nickName;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_change_nick_name;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //设置昵称
        mEtNickName.setText(singleAccountBean.getNickname());
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save_text, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (verify_nickName()) {
            mPresenter.setNickname(DefaultPreferenceUtil.getInstance().getToken(), nickName);
        }
        return true;
    }

    /**
     * @return true表示验证通过
     */
    private boolean verify_nickName() {
        nickName = mEtNickName.getText().toString();
        if (!nickName.matches(RegexConst.REGEX_NICKNAME)) {
            showToast("昵称格式错误");
            return false;
        }
        return true;
    }


    @Override
    public void setNicknameFinish() {
        mPresenter.getAccount(DefaultPreferenceUtil.getInstance().getToken());
    }

    @Override
    public void getAccountFinish(GetAccountBean accountBean) {
        super.getAccountFinish(accountBean);
        showToast("修改成功");
        finish();
    }
}