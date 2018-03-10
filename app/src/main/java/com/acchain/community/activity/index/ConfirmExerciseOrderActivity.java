package com.acchain.community.activity.index;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.base.BaseApplication;
import com.acchain.community.bean.ConfirmExerciseOrder;
import com.acchain.community.bean.ExercisePay;
import com.acchain.community.contract.ConfirmExerciseOrderContract;
import com.acchain.community.presenter.ConfirmExerciseOrderPresenter;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.util.L;
import com.acchain.community.view.AmountView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * function---- ConfirmExerciseOrderActivity
 * 行权订单只有从详情过来的这一种情况
 * Created(Gradle default create) by ACChain on 2018/01/16 09:05:47 (+0000).
 */
@Route(path = ARouterConst.Activity_ConfirmExerciseOrderActivity)
public class ConfirmExerciseOrderActivity extends BaseActivity<ConfirmExerciseOrderPresenter> implements ConfirmExerciseOrderContract.View {

    @BindView(R.id.ll_address)
    LinearLayout llAddress;
    @BindView(R.id.head_image)
    ImageView headImage;
    @BindView(R.id.head_name)
    TextView headName;
    @BindView(R.id.goods_image)
    ImageView goodsImage;
    @BindView(R.id.goods_name)
    TextView goodsName;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.describe)
    TextView describe;
    @BindView(R.id.tv_attrs)
    TextView tvAttrs;
    @BindView(R.id.tv_acount)
    TextView tvAcount;
    @BindView(R.id.amount_view)
    AmountView amountView;
    @BindView(R.id.goods_money)
    TextView goodsMoney;
    @BindView(R.id.tv_total_money)
    TextView tvTotalMoney;
    @BindView(R.id.tv_pay_now)
    TextView tvPayNow;
    private ConfirmExerciseOrder confirmExerciseOrder;
    private ConfirmExerciseOrder.OrderInfoBean orderInfo;
    private double totalFee;
    private int count = 1;
    private String valueIds;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_confirm_exercise_order;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getBundleExtra("bundle");
        confirmExerciseOrder = (ConfirmExerciseOrder) bundle.getSerializable("confirmExerciseOrder");
        valueIds = bundle.getString("valueIds");
        orderInfo = confirmExerciseOrder.getOrderInfo().get(0);
        Glide.with(this).load(BaseApplication.getApplication().getBaseImageUrl() + orderInfo.getProductImg());
        goodsName.setText(orderInfo.getProductName());
        price.setText(orderInfo.getExercisePrice() + "");
        describe.setText(orderInfo.getShortDesc());
        setMoney(orderInfo.getItemCount());
        amountView.setNumber(1);
        amountView.setGoods_storage(orderInfo.getCirculation());
        amountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, long amount) {
                setMoney((int) amount);
            }
        });
    }

    public void setMoney(int count) {
        this.count = count;
        totalFee = orderInfo.getExercisePrice() * count;
        tvAcount.setText("*" + count);
        goodsMoney.setText(totalFee + "");
        tvTotalMoney.setText("￥" + totalFee);
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }


    @OnClick({R.id.ll_address, R.id.tv_pay_now})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_address:
                break;
            case R.id.tv_pay_now:
                mPresenter.commitExerciseOrder(3, DefaultPreferenceUtil.getInstance().getToken(), count, orderInfo.getProductSaleId(), orderInfo.getProductId(), valueIds, 154);
                break;
        }
    }

    @Override
    public void onCommitExerciseOrderFinish(ExercisePay exercisePay) {
        if (exercisePay != null) {
            if (exercisePay.getResult() == 0) {
                showToast("支付成功");
            } else
                showToast(exercisePay.getMessage());
        } else
            L.i("失败");

    }
}