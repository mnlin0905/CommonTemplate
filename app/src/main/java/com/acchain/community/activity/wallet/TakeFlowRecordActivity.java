package com.acchain.community.activity.wallet;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.adapter.TakeFlowRecordAdapter;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.base.BaseRecyclerViewHolder;
import com.acchain.community.bean.TransactionItemBean;
import com.acchain.community.bean.TransactionListBean;
import com.acchain.community.contract.TakeFlowRecordContract;
import com.acchain.community.presenter.TakeFlowRecordPresenter;
import com.acchain.community.util.Const;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.view.LineMenuView;
import com.acchain.community.window.TimePickerViewDialog;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

import static com.acchain.community.util.Const.KEY_CURRENCY_SHORT_NAME;

/**
 * function---- TakeFlowRecordActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/13 09:59:55 (+0000).
 */
@Route(path = ARouterConst.Activity_TakeFlowRecordActivity)
public class TakeFlowRecordActivity extends BaseActivity<TakeFlowRecordPresenter> implements TakeFlowRecordContract.View, LineMenuView.LineMenuListener, BaseRecyclerViewHolder.OnViewClickListener, TimePickerViewDialog.onPickerViewListener {
    @BindView(R.id.tv_expense)
    TextView mTvExpense;
    @BindView(R.id.tv_income)
    TextView mTvIncome;

    @BindView(R.id.xrv_record)
    XRecyclerView mXrvRecord;
    TakeFlowRecordAdapter takeFlowRecordAdapter;
    List<TransactionItemBean> takeFlowRecordBeans;

    @BindView(R.id.lmv_head)
    LineMenuView mLmvHead;

    /**
     * 币种简称字段,
     * 若有,则用于获取对应币种流转记录
     * 若无,则用于获取所有流转记录
     */
    @Autowired(name = KEY_CURRENCY_SHORT_NAME, required = true)
    String currencyShortName;

    /**
     * 年
     * 月
     */
    private String year;
    private String month;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_take_flow_record;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //初始化列表
        takeFlowRecordBeans = new ArrayList<>();
        takeFlowRecordAdapter = new TakeFlowRecordAdapter(this,takeFlowRecordBeans, this);
        mXrvRecord.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mXrvRecord.setAdapter(takeFlowRecordAdapter);
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
        mPresenter.queryTransactionList(DefaultPreferenceUtil.getInstance().getToken(),currencyShortName, Const.TAG_TRANSACTION_RECORD_FLOW,year,month);
    }

    /**
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
        TimePickerViewDialog.getInstance(this)
                .setOnPickerViewListener(this)
                .getPickerView()
                .show(mLmvHead);
        return true;
    }

    /**
     * @param v 被点击到的v;此时应该是该view自身:LineMenuView
     */
    @Override
    public void performSelf(LineMenuView v) {

    }

    /**
     * @param v        被点击的view
     * @param position 所在的position
     */
    @Override
    public void onViewClick(View v, int position) {

    }

    /**
     * 当picker消失时进行回调
     */
    @Override
    public void onPickerDismiss() {

    }

    /**
     * 当选择了一个时间后进行回调
     *
     * @param date 选择的时间节点
     * @param view show方法调用时,绑定的view
     */
    @Override
    public void onPickerFinish(Date date, View view) {
        LineMenuView lmv = (LineMenuView) view;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        year=String.valueOf(calendar.get(Calendar.YEAR));
        month=String.valueOf(calendar.get(Calendar.MONTH)+1);
        lmv.setMenu(String.format(Locale.CHINA,"%s 年 %s 月",year,month));

        //再次刷新数据
        mPresenter.queryTransactionList(DefaultPreferenceUtil.getInstance().getToken(),currencyShortName,Const.TAG_TRANSACTION_RECORD_FLOW,year,month);
    }

    @Override
    public void queryTransactionListFinish(TransactionListBean transactionListBean) {
        super.queryTransactionListFinish(transactionListBean);
        if (transactionListBean == null) {
            return;
        }

        takeFlowRecordBeans.clear();
        takeFlowRecordBeans.addAll(transactionListBean.getAllList());
        takeFlowRecordAdapter.notifyDataSetChanged();
    }
}