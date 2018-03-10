package com.acchain.community.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.base.BaseRecyclerViewHolder;
import com.acchain.community.bean.TransactionItemBean;
import com.acchain.community.view.DividerView;

import java.util.List;

import butterknife.BindView;

/**
 * Created on 2018/1/11
 * function : 燃料币消耗记录
 *
 * @author ACChain
 */

public class BunkersFlowRecordAdapter extends RecyclerView.Adapter<BunkersFlowRecordAdapter.ViewHolder> {

    private BaseActivity activity;
    private List<TransactionItemBean> datas;
    private BaseRecyclerViewHolder.OnViewClickListener listener;

    public BunkersFlowRecordAdapter(BaseActivity activity,List<TransactionItemBean> datas, BaseRecyclerViewHolder.OnViewClickListener listener) {
        this.activity = activity;
        this.datas = datas;
        this.listener = listener;
    }

    @Override
    public BunkersFlowRecordAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_consume_or_flow_record, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(BunkersFlowRecordAdapter.ViewHolder holder, int position) {
        TransactionItemBean transactionItemBean = datas.get(position);
        holder.mTvAmount.setText(transactionItemBean.getAmountStrWithSymbol());
        holder.mTvAmount.setTextColor(transactionItemBean.getFlowAmoutColor(activity));
        holder.mTvType.setText(transactionItemBean.getOperationType());
        holder.mTvDate.setText(transactionItemBean.getCreateTime());
        holder.mDvDivider.setVisibility(position==datas.size()-1?View.INVISIBLE:View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return  datas.size();
    }

    class ViewHolder extends BaseRecyclerViewHolder {
        @BindView(R.id.tv_amount)
        TextView mTvAmount;
        @BindView(R.id.tv_type)
        TextView mTvType;
        @BindView(R.id.tv_date)
        TextView mTvDate;
        @BindView(R.id.dv_divider)
        DividerView mDvDivider;

        ViewHolder(View itemView, OnViewClickListener listener) {
            super(itemView, listener);
        }

        @Override
        protected boolean isXRecyclerView() {
            return true;
        }
    }
}
