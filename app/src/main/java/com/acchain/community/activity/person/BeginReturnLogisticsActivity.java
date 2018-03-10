package com.acchain.community.activity.person;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.BeginReturnLogisticsContract;
import com.acchain.community.presenter.BeginReturnLogisticsPresenter;
import com.acchain.community.view.LightTextView;
import com.acchain.community.view.LineMenuView;
import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * function---- BeginReturnLogisticsActivity
 * <p>
 * 开始进行退货流程,填写退货物流
 * <p>
 * Created(Gradle default create) by ACChain on 2018/02/03 09:03:51 (+0000).
 */
@Route(path = ARouterConst.Activity_BeginReturnLogisticsActivity)
public class BeginReturnLogisticsActivity extends BaseActivity<BeginReturnLogisticsPresenter> implements BeginReturnLogisticsContract.View,LineMenuView.LineMenuListener {

    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_price)
    LightTextView mTvPrice;
    @BindView(R.id.tv_introduce)
    TextView mTvIntroduce;
    @BindView(R.id.tv_spec)
    TextView mTvSpec;
    @BindView(R.id.tv_amount)
    TextView mTvAmount;
    @BindView(R.id.lmv_flow_company)
    LineMenuView mLmvFlowCompany;
    @BindView(R.id.et_flow_serial)
    TextView mEtFlowSerial;
    @BindView(R.id.iv_scan)
    ImageView mIvScan;
    @BindView(R.id.et_phone)
    TextView mEtPhone;
    @BindView(R.id.et_refund_reason)
    EditText mEtRefundReason;
    @BindView(R.id.iv_picture_one)
    ImageView mIvPictureOne;
    @BindView(R.id.iv_picture_two)
    ImageView mIvPictureTwo;
    @BindView(R.id.iv_picture_three)
    ImageView mIvPictureThree;
    @BindView(R.id.ll_upload)
    LinearLayout mLlUpload;
    @BindView(R.id.bt_submit)
    Button mBtSubmit;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_begin_return_logistics;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }




    @OnClick({R.id.iv_scan, R.id.ll_upload, R.id.bt_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_scan://扫描单号
                break;
            case R.id.ll_upload://上传照片
                break;
            case R.id.bt_submit://提交
                break;
        }
    }

    /**
     * 点击左侧文本
     *
     * @param v 被点击到的v;此时应该是左侧的TextView
     * @return 是否消费该点击事件, 如果返回true, 则performSelf将不会被调用
     */
    @Override
    public boolean performClickLeft(TextView v) {
        return false;
    }

    /**
     * @param v 被点击到的v;此时应该是右侧的TextView
     * @return 是否消费该点击事件, 如果返回true, 则performSelf将不会被调用
     */
    @Override
    public boolean performClickRight(TextView v) {
        return false;
    }

    /**
     * @param v 被点击到的v;此时应该是该view自身:LineMenuView
     */
    @Override
    public void performSelf(LineMenuView v) {

    }
}