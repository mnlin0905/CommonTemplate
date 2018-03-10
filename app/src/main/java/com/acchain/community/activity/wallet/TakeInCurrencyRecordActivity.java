package com.acchain.community.activity.wallet;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.adapter.TakeInCurrencyRecordAdapter;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.base.BaseRecyclerViewHolder;
import com.acchain.community.bean.TransactionItemBean;
import com.acchain.community.bean.TransactionListBean;
import com.acchain.community.contract.TakeInCurrencyRecordContract;
import com.acchain.community.interfaces.CallBackAfterItemMeasure;
import com.acchain.community.presenter.TakeInCurrencyRecordPresenter;
import com.acchain.community.util.Const;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;

import static com.acchain.community.util.Const.MODEL_TRANSACTION_RECORD_ITEM;

/**
 * function---- TakeInCurrencyRecordActivity
 * <p>
 * 转入记录列表
 * <p>
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/10 08:21:14 (+0000).
 */
@Route(path = ARouterConst.Activity_TakeInCurrencyRecordActivity)
public class TakeInCurrencyRecordActivity extends BaseActivity<TakeInCurrencyRecordPresenter> implements TakeInCurrencyRecordContract.View, BaseRecyclerViewHolder.OnViewClickListener, CallBackAfterItemMeasure {
    @BindView(R.id.tv_label_time)
    TextView mTvLabelTime;
    @BindView(R.id.tv_label_status)
    TextView mTvLabelStatus;

    @BindView(R.id.xrv_record)
    XRecyclerView mXrvRecord;
    ArrayList<TransactionItemBean> transactionItemBeans;
    TakeInCurrencyRecordAdapter transactionItemAdapter;

    /**
     * 币种简称,必须字段
     */
    @Autowired(required = true, name = Const.KEY_CURRENCY_SHORT_NAME)
    String currencyShortName;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_take_in_currency_record;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //列表
        transactionItemBeans = new ArrayList<>();
        transactionItemAdapter = new TakeInCurrencyRecordAdapter(transactionItemBeans, this, this);
        mXrvRecord.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mXrvRecord.setAdapter(transactionItemAdapter);
        mXrvRecord.setEmptyView(findViewById(R.id.empty_view));
        mXrvRecord.setLoadingMoreEnabled(false);
        mXrvRecord.setPullRefreshEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.queryTransactionList(DefaultPreferenceUtil.getInstance().getToken(), currencyShortName, Const.TAG_TRANSACTION_RECORD_INCOME, null, null);
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @Override
    public void onViewClick(View v, int position) {
        ARouter.getInstance()
                .build(ARouterConst.Activity_TakeInRecordDetailActivity)
                .withObject(MODEL_TRANSACTION_RECORD_ITEM, transactionItemBeans.get(position))
                .navigation();
    }

    @Override
    public void queryTransactionListFinish(TransactionListBean transactionListBean) {
        super.queryTransactionListFinish(transactionListBean);
        if (transactionListBean == null) {
            return;
        }

        //刷新布局,同时设定标头的宽度,保证对齐
        transactionItemBeans.clear();
        transactionItemBeans.addAll(transactionListBean.getRechargeList());
        transactionItemAdapter.notifyDataSetChanged();
    }

    /**
     * @param tag           若是同时检测多个宽高,则用于区分
     * @param measureWidth  item中想要获取的view的宽度
     * @param measureHeight item中想要获取的view的高度
     * @return true 则表示此次已经完成了测量, 以后都不需要再调用该方法了
     * false 则表示每次刷新视图都需要实现类执行该方法完成宽度和高度的设置
     */
    @Override
    public boolean doAfterItemMeasure(int tag, int measureWidth, int measureHeight) {
        if (tag == CallBackAfterItemMeasure.TAG_ONE && measureWidth > mTvLabelTime.getMeasuredWidth()) {
            mTvLabelTime.setWidth(measureWidth);
        }
        if (tag == CallBackAfterItemMeasure.TAG_TWO && measureWidth > mTvLabelStatus.getMeasuredWidth()) {
            mTvLabelStatus.setWidth(measureWidth);
        }
        return false;
    }
}