package com.acchain.community.activity.wallet;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.adapter.TakeOutCurrencyRecordAdapter;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.base.BaseRecyclerViewHolder;
import com.acchain.community.bean.TransactionItemBean;
import com.acchain.community.bean.TransactionListBean;
import com.acchain.community.contract.TakeOutCurrencyRecordContract;
import com.acchain.community.interfaces.CallBackAfterItemMeasure;
import com.acchain.community.presenter.TakeOutCurrencyRecordPresenter;
import com.acchain.community.util.Const;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.view.RecyclerEmptyView;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;

import static com.acchain.community.util.Const.MODEL_TRANSACTION_RECORD_ITEM;

/**
 * function---- TakeOutCurrencyRecordActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/12 08:00:28 (+0000).
 */
@Route(path = ARouterConst.Activity_TakeOutCurrencyRecordActivity)
public class TakeOutCurrencyRecordActivity extends BaseActivity<TakeOutCurrencyRecordPresenter> implements TakeOutCurrencyRecordContract.View, BaseRecyclerViewHolder.OnViewClickListener, TakeOutCurrencyRecordAdapter.onCancelTakeOutListener, CallBackAfterItemMeasure {
    /**
     * 列表信息
     */
    @BindView(R.id.xrv_record)
    XRecyclerView mXrvRecord;
    ArrayList<TransactionItemBean> transactionItemBeans;
    TakeOutCurrencyRecordAdapter transactionItemAdapter;

    @BindView(R.id.empty_view)
    RecyclerEmptyView mEmptyView;
    @BindView(R.id.tv_label_time)
    TextView mTvLabelTime;
    @BindView(R.id.tv_label_operate)
    TextView mTvLabelOperate;
    @BindView(R.id.tv_label_status)
    TextView mTvLabelStatus;

    /**
     * 币种简称,必须字段
     */
    @Autowired(required = true, name = Const.KEY_CURRENCY_SHORT_NAME)
    String currencyShortName;

    /**
     * 请求来源
     * <p>
     * 1 表示请求转给朋友的记录
     * 2 表示请求转出平台的记录
     */
    @Autowired(required = true, name = Const.KEY_TAG_TRANSACTION_RECORD)
    String tagTransaction;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_take_out_currency_record;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //列表数据
        transactionItemBeans = new ArrayList<>();
        transactionItemAdapter = new TakeOutCurrencyRecordAdapter(this, transactionItemBeans, this, this, this);
        mXrvRecord.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mXrvRecord.setAdapter(transactionItemAdapter);
        mXrvRecord.setEmptyView(findViewById(R.id.empty_view));
        mXrvRecord.setLoadingMoreEnabled(false);
        mXrvRecord.setPullRefreshEnabled(false);
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.queryTransactionList(DefaultPreferenceUtil.getInstance().getToken(), currencyShortName, tagTransaction, null, null);
    }

    /**
     * @param v        被点击的view
     * @param position 所在的position
     */
    @Override
    public void onViewClick(View v, int position) {
        ARouter.getInstance()
                .build(ARouterConst.Activity_TakeOutRecordDetailActivity)
                .withObject(MODEL_TRANSACTION_RECORD_ITEM, transactionItemBeans.get(position))
                .navigation();
    }

    @Override
    public void queryTransactionListFinish(TransactionListBean transactionListBean) {
        super.queryTransactionListFinish(transactionListBean);
        if (transactionListBean == null) {
            return;
        }

        transactionItemBeans.clear();
        if (tagTransaction.equalsIgnoreCase(Const.TAG_TRANSACTION_RECORD_FRIEND)) {
            transactionItemBeans.addAll(transactionListBean.getTransferFriend());
        }
        if (tagTransaction.equalsIgnoreCase(Const.TAG_TRANSACTION_RECORD_PLATFORM)) {
            transactionItemBeans.addAll(transactionListBean.getTransferList());
        }
        transactionItemAdapter.notifyDataSetChanged();
    }

    /**
     * @param position 需要撤销的item位置
     */
    @Override
    public void onCancelTakeOut(int position) {
        mPresenter.transferPlatformRevoke(DefaultPreferenceUtil.getInstance().getToken(), transactionItemBeans.get(position).getTransactionId());
    }

    /**
     * 1.2.0  钱包资产转出(撤销)
     */
    @Override
    public void transferPlatformRevokeFinish() {
        showToast("已撤销");

        //撤销后刷新列表
        mPresenter.queryTransactionList(DefaultPreferenceUtil.getInstance().getToken(), currencyShortName, tagTransaction, null, null);
    }

    /**
     * @param tag           若是同时检测多个宽高,则用于区分
     * @param measureWidth  item中想要获取的view的宽度
     * @param measureHeight item中想要获取的view的高度
     * @return true则表示此次已经完成了测量, 以后都不需要再调用该方法了;
     * false则表示每次刷新视图都需要实现类执行该方法完成宽度和高度的设置
     */
    @Override
    public boolean doAfterItemMeasure(int tag, int measureWidth, int measureHeight) {
        if (tag == CallBackAfterItemMeasure.TAG_ONE && measureWidth > mTvLabelTime.getMeasuredWidth()) {
            mTvLabelTime.setWidth(measureWidth);
        }
        if (tag == CallBackAfterItemMeasure.TAG_TWO && measureWidth > mTvLabelStatus.getMeasuredWidth()) {
            mTvLabelStatus.setWidth(measureWidth);
        }
        if (tag == CallBackAfterItemMeasure.TAG_THREE && measureWidth > mTvLabelOperate.getMeasuredWidth()) {
            mTvLabelOperate.setWidth(measureWidth);
        }
        return false;
    }
}