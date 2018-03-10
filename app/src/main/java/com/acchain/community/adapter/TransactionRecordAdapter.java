package com.acchain.community.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.base.BaseRecyclerViewHolder;
import com.acchain.community.bean.TransactionRecordBean;

import java.util.List;

import butterknife.BindView;

/**
 * Created on 2018/1/24
 * function : 交易记录
 *
 * @author ACChain
 */

public class TransactionRecordAdapter extends RecyclerView.Adapter<TransactionRecordAdapter.ViewHolder> {
    private List<TransactionRecordBean> datas;
    private BaseRecyclerViewHolder.OnViewClickListener listener;

    public TransactionRecordAdapter(List<TransactionRecordBean> datas, BaseRecyclerViewHolder.OnViewClickListener listener) {
        this.datas = datas;
        this.listener = listener;
    }

    @Override
    public TransactionRecordAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TransactionRecordAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaction_record, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(TransactionRecordAdapter.ViewHolder holder, int position) {
        TransactionRecordBean bean = datas.get(position);
        holder.mTvType.setText(bean.getTypeStr());
        holder.mTvName.setText(bean.getName());
        holder.mTvAmount.setText(bean.getNum());
        holder.mTvTime.setText(bean.getCreateTime());
        holder.mTvStatus.setText(bean.getStatusStr());
        holder.mTvStatus.setTextColor(bean.getStatusColor());
        holder.mDvDivider.setVisibility(position==datas.size()-1?View.GONE:View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return  datas.size();
    }

    class ViewHolder extends BaseRecyclerViewHolder {

        @BindView(R.id.iv_icon)
        ImageView mIvIcon;
        @BindView(R.id.tv_type)
        TextView mTvType;
        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.tv_amount)
        TextView mTvAmount;
        @BindView(R.id.tv_time)
        TextView mTvTime;
        @BindView(R.id.tv_status)
        TextView mTvStatus;
        @BindView(R.id.dv_divider)
        ImageView mDvDivider;

        public ViewHolder(View itemView, OnViewClickListener listener) {
            super(itemView, listener);
        }

        @Override
        protected boolean isXRecyclerView() {
            return true;
        }
    }
}