package com.acchain.community.activity.person;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.CheckLogisticsContract;
import com.acchain.community.presenter.CheckLogisticsPresenter;
import com.acchain.community.view.FlowLinearLayout;
import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;

/**
 * function---- CheckLogisticsActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/02/01 12:27:02 (+0000).
 */
@Route(path = ARouterConst.Activity_CheckLogisticsActivity)
public class CheckLogisticsActivity extends BaseActivity<CheckLogisticsPresenter> implements CheckLogisticsContract.View {

    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.tv_status)
    TextView mTvStatus;
    @BindView(R.id.tv_from)
    TextView mTvFrom;
    @BindView(R.id.tv_serial_number)
    TextView mTvSerialNumber;
    @BindView(R.id.tv_telephone)
    TextView mTvTelephone;
    @BindView(R.id.fll_goods_flow)
    FlowLinearLayout mFllGoodsFlow;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_check_logistics;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //加载数据模型

        //加载物流列表
        List<FlowLinearLayout.FlowModel> models=new LinkedList<>();
        models.add(new FlowLinearLayout.FlowModel(true,"brief","fjskldfjskdlfjsklfjsfkl"));
        models.add(new FlowLinearLayout.FlowModel(false,"brief","fjskldfjskdlfjsklfjsfkl"));
        models.add(new FlowLinearLayout.FlowModel(true,"brief","fjskldfjskdlfjsklfjsfkl"));
        models.add(new FlowLinearLayout.FlowModel(false,"brief","fjskldfjskdlfjsklfjsfkl"));
        models.add(new FlowLinearLayout.FlowModel(false,"brief","fjskldfjskdlfjsklfjsfkl"));
        models.add(new FlowLinearLayout.FlowModel(true,"brief","fjskldfjskdlfjsklfjsfkl"));
        mFllGoodsFlow.addDatas(models);
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }


}