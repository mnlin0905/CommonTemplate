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
 * Created on 2018/1/3
 * function : 钱包流水记录
 *
 * @author ACChain
 */

public class WalletRecordAdapter extends RecyclerView.Adapter<WalletRecordAdapter.ViewHolder> {

    private BaseActivity baseActivity;
    private List<TransactionItemBean> datas;
    private BaseRecyclerViewHolder.OnViewClickListener listener;

    public WalletRecordAdapter(BaseActivity baseActivity,List<TransactionItemBean> datas, BaseRecyclerViewHolder.OnViewClickListener listener) {
        this.baseActivity = baseActivity;
        this.datas = datas;
        this.listener = listener;
    }

    @Override
    public WalletRecordAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wallet_record, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(WalletRecordAdapter.ViewHolder holder, int position) {
        TransactionItemBean transactionItemBean = datas.get(position);
        holder.mTvAmount.setText(transactionItemBean.getAmountStrWithSymbol());
        holder.mTvAmount.setTextColor(transactionItemBean.getFlowAmoutColor(baseActivity));
        holder.mTvType.setText(transactionItemBean.getOperationType());
        holder.mTvTime.setText(transactionItemBean.getCreateTime());
        holder.mTvCurrency.setText(transactionItemBean.getCurrency());
        holder.mDvDivider.setVisibility(position==datas.size()-1?View.INVISIBLE:View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return  datas.size();
    }

    class ViewHolder extends BaseRecyclerViewHolder {
        @BindView(R.id.tv_type)
        TextView mTvType;
        @BindView(R.id.tv_time)
        TextView mTvTime;
        @BindView(R.id.tv_currency)
        TextView mTvCurrency;
        @BindView(R.id.tv_amount)
        TextView mTvAmount;
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