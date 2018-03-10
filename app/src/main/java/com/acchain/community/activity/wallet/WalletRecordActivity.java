package com.acchain.community.activity.wallet;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.adapter.WalletRecordAdapter;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.base.BaseRecyclerViewHolder;
import com.acchain.community.bean.TransactionItemBean;
import com.acchain.community.bean.TransactionListBean;
import com.acchain.community.contract.WalletRecordContract;
import com.acchain.community.presenter.WalletRecordPresenter;
import com.acchain.community.util.Const;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.view.LineMenuView;
import com.acchain.community.window.TimePickerViewDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

/**
 * function---- WalletRecordActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/03 09:49:58 (+0000).
 */
@Route(path = ARouterConst.Activity_WalletRecordActivity)
public class WalletRecordActivity extends BaseActivity<WalletRecordPresenter> implements WalletRecordContract.View, BaseRecyclerViewHolder.OnViewClickListener, LineMenuView.LineMenuListener, TimePickerViewDialog.onPickerViewListener {
    @BindView(R.id.xrv_record)
    XRecyclerView mXrvRecord;
    WalletRecordAdapter walletRecordAdapter;
    List<TransactionItemBean> walletRecordBeans;

    @BindView(R.id.lmv_head)
    LineMenuView mLmvHead;

    /**
     * 年
     * 月
     */
    private String year;
    private String month;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_wallet_record;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //初始化列表
        walletRecordBeans = new ArrayList<>();
        walletRecordAdapter = new WalletRecordAdapter(this,walletRecordBeans, this);
        mXrvRecord.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mXrvRecord.setAdapter(walletRecordAdapter);
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
        mPresenter.queryTransactionList(DefaultPreferenceUtil.getInstance().getToken(),null, Const.TAG_TRANSACTION_RECORD_FLOW,year,month);
    }

    @Override
    public void onViewClick(View v, int position) {
        // TODO: 2018/1/4 点击自身
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
        mPresenter.queryTransactionList(DefaultPreferenceUtil.getInstance().getToken(),null, Const.TAG_TRANSACTION_RECORD_FLOW,year,month);
    }

    @Override
    public void queryTransactionListFinish(TransactionListBean transactionListBean) {
        super.queryTransactionListFinish(transactionListBean);
        if (transactionListBean == null) {
            return;
        }

        walletRecordBeans.clear();
        walletRecordBeans.addAll(transactionListBean.getAllList());
        walletRecordAdapter.notifyDataSetChanged();
    }
}