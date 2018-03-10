package com.acchain.community.activity.person;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.SelectRefundServiceContract;
import com.acchain.community.presenter.SelectRefundServicePresenter;
import com.acchain.community.view.LightTextView;
import com.acchain.community.view.MenuTitleSubTitleView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * function---- SelectRefundServiceActivity
 * 退款操作时,先选择退款类别
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/26 02:52:26 (+0000).
 */
@Route(path = ARouterConst.Activity_SelectRefundServiceActivity)
public class SelectRefundServiceActivity extends BaseActivity<SelectRefundServicePresenter> implements SelectRefundServiceContract.View {

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
    @BindView(R.id.mtstv_refund)
    MenuTitleSubTitleView mMtstvRefund;
    @BindView(R.id.mtstv_refund_with_goods)
    MenuTitleSubTitleView mMtstvRefundWithGoods;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_select_refund_service;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @OnClick({R.id.mtstv_refund, R.id.mtstv_refund_with_goods})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mtstv_refund:
                break;
            case R.id.mtstv_refund_with_goods:
                break;
        }

        ARouter.getInstance()
                .build(ARouterConst.Activity_ApplyForRefundActivity)
                // TODO: 2018/1/26 添加退货参数
                .navigation();
    }
}