package com.acchain.community.activity.person;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.bean.GetAccountBean;
import com.acchain.community.contract.EditProfessionCardContract;
import com.acchain.community.presenter.EditProfessionCardPresenter;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.view.LineMenuView;
import com.acchain.community.window.CancelConfirmDialogFragment;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;

import static com.acchain.community.util.Const.KEY_INTRODUCE;

/**
 * function---- EditProfessionCardActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/17 10:02:42 (+0000).
 */
@Route(path = ARouterConst.Activity_EditProfessionCardActivity)
public class EditProfessionCardActivity extends BaseActivity<EditProfessionCardPresenter> implements EditProfessionCardContract.View, LineMenuView.LineMenuListener, CancelConfirmDialogFragment.OnOperateListener {
    private static final int REQUEST_CODE = 1;

    @BindView(R.id.et_company_name)
    EditText mEtCompanyName;
    @BindView(R.id.et_business)
    EditText mEtBusiness;
    @BindView(R.id.et_duty)
    EditText mEtDuty;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_email)
    EditText mEtEmail;
    @BindView(R.id.et_location)
    EditText mEtLocation;
    @BindView(R.id.et_location_detail)
    EditText mEtLocationDetail;
    @BindView(R.id.lmv_introduce)
    LineMenuView mLmvIntroduce;

    /**
     * 参数信息
     */
    private String introduce;
    private String companyName;
    private String mainBusiness;
    private String duty;
    private String phone;
    private String email;
    private String location;
    private String locationDetail;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_edit_profession_card;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //判断是修改还是新建
        GetAccountBean.BusinessCard businessCard = BasePresenter.singleAccountBean.getBusinessCard();
        if (businessCard != null) {
            introduce=businessCard.getIntroduction();
            mEtCompanyName.setText(businessCard.getCompanyName());
            mEtBusiness.setText(businessCard.getMainBusiness());
            mEtDuty.setText(businessCard.getPosition());
            mEtPhone.setText(businessCard.getPhone());
            mEtPhone.setEnabled(false);
            mEtEmail.setText(businessCard.getEmail());
            mEtEmail.setEnabled(false);
            mEtLocation.setText(businessCard.getAddress());
            // TODO: 2018/1/30 详细地址
            mEtLocationDetail.setText(businessCard.getAddress());
        }else {
            if(!TextUtils.isEmpty(BasePresenter.singleAccountBean.getMobile())){
                mEtPhone.setText(BasePresenter.singleAccountBean.getMobile());
                mEtPhone.setEnabled(false);
            }
            if(!TextUtils.isEmpty(BasePresenter.singleAccountBean.getEmail())){
                mEtEmail.setText(BasePresenter.singleAccountBean.getEmail());
                mEtEmail.setEnabled(false);
            }
        }
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
        companyName = mEtCompanyName.getText().toString();
        mainBusiness = mEtBusiness.getText().toString();
        duty = mEtDuty.getText().toString();
        phone = mEtPhone.getText().toString().replaceAll("\\s","");
        email = mEtEmail.getText().toString();
        location = mEtLocation.getText().toString();
        locationDetail = mEtLocationDetail.getText().toString();

        mPresenter.setBusinessCard(DefaultPreferenceUtil.getInstance().getToken(),
                duty, companyName, mainBusiness, phone, email, locationDetail, location, introduce);
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
        if (v == mLmvIntroduce) {
            ARouter.getInstance()
                    .build(ARouterConst.Activity_CompanyIntroduceActivity)
                    .navigation(this, REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            introduce = data.getStringExtra(KEY_INTRODUCE);
        }
    }

    @Override
    public void setBusinessCardFinish() {
        mPresenter.getAccount(DefaultPreferenceUtil.getInstance().getToken());
    }

    @Override
    public void getAccountFinish(GetAccountBean accountBean) {
        showToast("设置成功");
        finish();
    }

    @Override
    public void onBackPressed() {
        //询问是否保持本地设置,默认都会弹出提示
        new CancelConfirmDialogFragment()
                .setTitle("保存本次设置")
                .setCancelText("不保存")
                .setConfirmText("保存")
                .setOnOperateListener(this)
                .show(getSupportFragmentManager(), "save");
    }

    /**
     * 点击左侧按钮
     *
     * @param dialog dialog
     * @return 返回true则默认不会关闭dialog
     */
    @Override
    public boolean onCancel(Dialog dialog) {
        finish();
        return false;
    }

    /**
     * 点击右侧按钮
     *
     * @param dialog dialog
     * @return 返回true则默认不会关闭dialog
     */
    @Override
    public boolean onConfirm(Dialog dialog) {
        onOptionsItemSelected(null);
        return false;
    }
}