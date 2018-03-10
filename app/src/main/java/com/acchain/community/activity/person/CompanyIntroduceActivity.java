package com.acchain.community.activity.person;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.window.CancelConfirmDialogFragment;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;

import static com.acchain.community.util.Const.KEY_INTRODUCE;

/**
 * function---- CompanyIntroduceActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/17 12:33:17 (+0000).
 */
@Route(path = ARouterConst.Activity_CompanyIntroduceActivity)
public class CompanyIntroduceActivity extends BaseActivity implements View.OnClickListener, CancelConfirmDialogFragment.OnOperateListener {


    @BindView(R.id.et_introduce)
    EditText mEtIntroduce;

    @Autowired(name = KEY_INTRODUCE,required = false)
    String introduce;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_company_introduce;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //设置左侧导航按钮为文字
        setToolbarNavText(toolbar,"取消",R.color.blue_text_color);
        toolbar.setNavigationOnClickListener(this);

        //如果为修改,需要先赋值
        mEtIntroduce.setText(introduce);
    }

    @Override
    protected void injectSelf() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save_text, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        introduce = mEtIntroduce.getText().toString();
        /*if(TextUtils.isEmpty(introduce)){
            showToast("保存失败,简介内容为空");
            return true;
        }*/
        Intent intent=new Intent();
        intent.putExtra(KEY_INTRODUCE,introduce);
        setResult(RESULT_OK,intent);
        showToast("内容已保存");
        finish();
        return true;
    }

    @Override
    public void onClick(View v) {
        showToast("已取消");
        finish();
    }

    @Override
    public void onBackPressed() {
        new CancelConfirmDialogFragment()
                .setTitle("是否放弃保存,直接退出?")
                .setConfirmText("保存")
                .setCancelText("退出")
                .setOnOperateListener(this)
                .show(getSupportFragmentManager(),"introduce");
        super.onBackPressed();
    }

    @Override
    public boolean onCancel(Dialog dialog) {
        finish();
        return false;
    }

    @Override
    public boolean onConfirm(Dialog dialog) {
        onOptionsItemSelected(null);
        finish();
        return false;
    }
}