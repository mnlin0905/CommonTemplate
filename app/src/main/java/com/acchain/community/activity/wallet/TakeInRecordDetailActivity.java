package com.acchain.community.activity.wallet;

import android.os.Bundle;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.bean.TransactionItemBean;
import com.acchain.community.contract.TakeInRecordDetailContract;
import com.acchain.community.presenter.TakeInRecordDetailPresenter;
import com.acchain.community.view.LineMenuView;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;

import static com.acchain.community.util.Const.MODEL_TRANSACTION_RECORD_ITEM;

/**
 * function---- TakeInRecordDetailActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/10 09:37:33 (+0000).
 */
@Route(path = ARouterConst.Activity_TakeInRecordDetailActivity)
public class TakeInRecordDetailActivity extends BaseActivity<TakeInRecordDetailPresenter> implements TakeInRecordDetailContract.View {

    /**
     * 创建时间
     * id
     * 总支付
     * 到达数
     * 确认数
     * 到达时间
     * 状态
     */
    @BindView(R.id.lmv_create_time)
    LineMenuView mLmvCreateTime;
    @BindView(R.id.tv_id)
    TextView mTvId;
    @BindView(R.id.lmv_pay)
    LineMenuView mLmvPay;
    @BindView(R.id.lmv_arrive)
    LineMenuView mLmvArrive;
    @BindView(R.id.lmv_confirm)
    LineMenuView mLmvConfirm;
    @BindView(R.id.lmv_arrive_time)
    LineMenuView mLmvArriveTime;
    @BindView(R.id.lmv_status)
    LineMenuView mLmvStatus;

    /**
     * 详细记录model
     */
    @Autowired(name = MODEL_TRANSACTION_RECORD_ITEM, required = true)
    TransactionItemBean itemBean;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_take_in_record_detail;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mLmvCreateTime.setBrief(itemBean.getCreateTime());
        mTvId.setText(itemBean.getTransactionId());
        mLmvPay.setBrief(itemBean.getActualPaymentStr());
        mLmvArrive.setBrief(itemBean.getArrivePaymentStr());
        mLmvConfirm.setBrief(itemBean.getAffirmNumStr());
        mLmvArriveTime.setBrief(itemBean.getUpdateTime());
        mLmvStatus.setBrief(itemBean.getStatusStr());
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

}