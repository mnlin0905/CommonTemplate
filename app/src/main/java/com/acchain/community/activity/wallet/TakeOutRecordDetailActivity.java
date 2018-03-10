package com.acchain.community.activity.wallet;

import android.os.Bundle;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.bean.TransactionItemBean;
import com.acchain.community.contract.TakeOutRecordDetailContract;
import com.acchain.community.presenter.TakeOutRecordDetailPresenter;
import com.acchain.community.view.LineMenuView;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;

import static com.acchain.community.util.Const.MODEL_TRANSACTION_RECORD_ITEM;

/**
 * function---- TakeOutRecordDetailActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/12 08:01:44 (+0000).
 */
@Route(path = ARouterConst.Activity_TakeOutRecordDetailActivity)
public class TakeOutRecordDetailActivity extends BaseActivity<TakeOutRecordDetailPresenter> implements TakeOutRecordDetailContract.View {

    @BindView(R.id.lmv_time)
    LineMenuView mLmvTime;
    @BindView(R.id.tv_reward_id)
    TextView mTvRewardId;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.lmv_out_amount)
    LineMenuView mLmvOutAmount;
    @BindView(R.id.lmv_arrive_amount)
    LineMenuView mLmvArriveAmount;
    @BindView(R.id.lmv_status)
    LineMenuView mLmvStatus;

    /**
     * 详细记录model
     */
    @Autowired(name = MODEL_TRANSACTION_RECORD_ITEM, required = true)
    TransactionItemBean itemBean;

    @Override
    protected void initData(Bundle savedInstanceState) {
        mLmvTime.setBrief(itemBean.getCreateTime());
        mTvRewardId.setText(itemBean.getTransactionId());
        mTvAddress.setText(itemBean.getWalletAddress());
        mLmvOutAmount.setBrief(itemBean.getAmountStr());
        mLmvArriveAmount.setBrief(itemBean.getArrivePaymentStr());
        mLmvStatus.setBrief(itemBean.getStatusStr());
    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_take_out_record_detail;
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }
}