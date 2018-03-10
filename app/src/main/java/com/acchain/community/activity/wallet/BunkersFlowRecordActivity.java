package com.acchain.community.activity.wallet;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.adapter.BunkersFlowRecordAdapter;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.base.BaseRecyclerViewHolder;
import com.acchain.community.bean.TransactionItemBean;
import com.acchain.community.bean.TransactionListBean;
import com.acchain.community.contract.BunkersFlowRecordContract;
import com.acchain.community.presenter.BunkersFlowRecordPresenter;
import com.acchain.community.util.Const;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.view.LineMenuView;
import com.acchain.community.window.TimePickerViewDialog;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

/**
 * function---- BunkersFlowRecordActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/11 06:50:00 (+0000).
 */
@Route(path = ARouterConst.Activity_BunkersFlowRecordActivity)
public class BunkersFlowRecordActivity extends BaseActivity<BunkersFlowRecordPresenter> implements BunkersFlowRecordContract.View, BaseRecyclerViewHolder.OnViewClickListener, LineMenuView.LineMenuListener, TimePickerViewDialog.onPickerViewListener {

    @BindView(R.id.lmv_head)
    LineMenuView mLmvHead;

    @BindView(R.id.xrv_record)
    XRecyclerView mXrvRecord;
    BunkersFlowRecordAdapter bunkersConsumeRecordAdapter;
    List<TransactionItemBean> bunkersConsumeRecordBeans;

    /**
     * 选择器
     */
    OptionsPickerView pvOptions;
    TimePickerView pvTime;

    /**
     * 币种简称
     */
    @Autowired(required = true,name = Const.KEY_CURRENCY_SHORT_NAME)
    String currencyName;

    /**
     * 年
     * 月
     */
    private String year;
    private String month;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_bunkers_consume_record;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //初始化列表
        bunkersConsumeRecordBeans = new ArrayList<>();
        bunkersConsumeRecordAdapter = new BunkersFlowRecordAdapter(this,bunkersConsumeRecordBeans, this);
        mXrvRecord.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mXrvRecord.setAdapter(bunkersConsumeRecordAdapter);
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
        mPresenter.queryTransactionList(DefaultPreferenceUtil.getInstance().getToken(),currencyName,Const.TAG_TRANSACTION_RECORD_FLOW,year,month);
    }

    @Override
    public void onViewClick(View v, int position) {

    }

    @Override
    public boolean performClickLeft(TextView v) {
        return false;
    }

    @Override
    public boolean performClickRight(TextView v) {
        TimePickerViewDialog.getInstance(this)
                .setOnPickerViewListener(this)
                .getPickerView()
                .show(mLmvHead);
        return true;
    }

    @Override
    public void performSelf(LineMenuView v) {

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
        mPresenter.queryTransactionList(DefaultPreferenceUtil.getInstance().getToken(),currencyName,Const.TAG_TRANSACTION_RECORD_FLOW,year,month);
    }

    @Override
    public void queryTransactionListFinish(TransactionListBean transactionListBean) {
        super.queryTransactionListFinish(transactionListBean);
        if (transactionListBean == null) {
            return;
        }

        bunkersConsumeRecordBeans.clear();
        bunkersConsumeRecordBeans.addAll(transactionListBean.getAllList());
        bunkersConsumeRecordAdapter.notifyDataSetChanged();
    }
}