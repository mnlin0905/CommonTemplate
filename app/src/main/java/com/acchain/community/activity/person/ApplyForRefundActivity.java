package com.acchain.community.activity.person;

import android.app.Dialog;
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
import com.acchain.community.contract.ApplyForRefundContract;
import com.acchain.community.presenter.ApplyForRefundPresenter;
import com.acchain.community.view.DividerView;
import com.acchain.community.view.LightTextView;
import com.acchain.community.view.LineMenuView;
import com.acchain.community.window.GoodsStatusDialogFragment;
import com.acchain.community.window.RefundReasonDialogFragment;
import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * function---- ApplyForRefundActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/26 02:53:32 (+0000).
 */
@Route(path = ARouterConst.Activity_ApplyForRefundActivity)
public class ApplyForRefundActivity extends BaseActivity<ApplyForRefundPresenter> implements ApplyForRefundContract.View, LineMenuView.LineMenuListener, RefundReasonDialogFragment.OnRefundReasonListener, GoodsStatusDialogFragment.OnGoodsStatusListener {

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
    @BindView(R.id.et_money)
    TextView mEtMoney;
    @BindView(R.id.et_mark)
    EditText mEtMark;
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
    @BindView(R.id.lmv_refund_reason)
    LineMenuView mLmvRefundReason;

    /**
     * 动态的消失或者隐藏
     */
    @BindView(R.id.divider)
    DividerView mDivider;
    @BindView(R.id.lmv_goods_status)
    LineMenuView mLmvGoodsStatus;

    /**
     * 货物状态,0或者1
     * 退货原因
     */
    private int currentGoodsStatus = -1;
    private int currentRefundReason = -1;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_apply_for_refund;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @OnClick({R.id.iv_picture_one, R.id.iv_picture_two, R.id.iv_picture_three, R.id.ll_upload, R.id.bt_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_picture_one:
                break;
            case R.id.iv_picture_two:
                break;
            case R.id.iv_picture_three:
                break;
            case R.id.ll_upload://选择图片
                break;
            case R.id.bt_submit:
                break;
        }
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
        //分别进行状态选择

        if (v == mLmvRefundReason) {
            new RefundReasonDialogFragment()
                    .setOnRefundReasonListener(this)
                    .show(getSupportFragmentManager(), "refund");
        }
        if (v == mLmvGoodsStatus) {
            new GoodsStatusDialogFragment()
                    .setSelectItem(currentGoodsStatus)
                    .setOnGoodsStatusListener(this)
                    .show(getSupportFragmentManager(), "goods_status");
        }
    }

    @Override
    public boolean onSelect(int position, Dialog dialog) {
        currentRefundReason = position;
        // TODO: 2018/2/1 区分是否收到货
        String[] stringArray = getResources().getStringArray(R.array.dialog_fragment_refund_reason_with_goods_arrays);
        mLmvRefundReason.setBrief(stringArray[position]);
        return false;
    }

    @Override
    public boolean onClose(Dialog dialog) {
        return false;
    }

    @Override
    public boolean onItemSelect(int position, Dialog dialog) {
        currentGoodsStatus = position;
        String[] stringArray = getResources().getStringArray(R.array.dialog_fragment_goods_status_arrays);
        mLmvGoodsStatus.setBrief(stringArray[position]);
        return false;
    }

    @Override
    public boolean onItemClose(Dialog dialog) {
        return false;
    }
}