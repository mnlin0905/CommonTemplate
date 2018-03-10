package com.acchain.community.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.base.BaseRecyclerViewHolder;
import com.acchain.community.bean.TransactionItemBean;
import com.acchain.community.interfaces.CallBackAfterItemMeasure;

import java.util.List;

import butterknife.BindView;

/**
 * Created on 2018/1/10
 * function : 转入记录查询
 *
 * @author ACChain
 */

public class TakeInCurrencyRecordAdapter extends RecyclerView.Adapter<TakeInCurrencyRecordAdapter.ViewHolder> {
    private CallBackAfterItemMeasure callback;
    private List<TransactionItemBean> datas;
    private BaseRecyclerViewHolder.OnViewClickListener listener;

    /**
     * 是否禁止处理callback
     */
    private volatile boolean forbid;

    public TakeInCurrencyRecordAdapter(List<TransactionItemBean> datas, BaseRecyclerViewHolder.OnViewClickListener listener) {
        this.datas = datas;
        this.listener = listener;
    }

    public TakeInCurrencyRecordAdapter(List<TransactionItemBean> datas, BaseRecyclerViewHolder.OnViewClickListener listener, CallBackAfterItemMeasure callback) {
        this(datas,listener);
        this.callback = callback;
    }

    @Override
    public TakeInCurrencyRecordAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TakeInCurrencyRecordAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_take_in_currency_record, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(TakeInCurrencyRecordAdapter.ViewHolder holder, int position) {
        TransactionItemBean transactionItemBean = datas.get(position);
        holder.mTvTime.setText(transactionItemBean.getCreateTime());
        holder.mTvPay.setText(transactionItemBean.getActualPaymentStr());
        holder.mTvStatus.setText(transactionItemBean.getStatusStr());
        holder.mDvDivider.setVisibility(position==datas.size()-1?View.INVISIBLE:View.VISIBLE);
        if(callback!=null&&!forbid) {
            postMeasure(holder.mTvTime,CallBackAfterItemMeasure.TAG_ONE);
            postMeasure(holder.mTvStatus,CallBackAfterItemMeasure.TAG_TWO);
        }
    }

    /**
     * 通知callback执行
     */
    private void postMeasure(View view,int tag){
        view.post(() -> {
            if(forbid){
                return;
            }

            int measuredWidth = view.getMeasuredWidth();
            int measuredHeight =view.getMeasuredHeight();
            //只有宽度和高度为有效值时,才会进行调用
            if(measuredHeight>0&&measuredWidth>0&&callback.doAfterItemMeasure(tag,measuredWidth,measuredHeight)) {
                forbid=true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return  datas.size();
    }

    class ViewHolder extends BaseRecyclerViewHolder {
        @BindView(R.id.tv_time)
        TextView mTvTime;
        @BindView(R.id.tv_pay)
        TextView mTvPay;
        @BindView(R.id.tv_status)
        TextView mTvStatus;
        @BindView(R.id.iv_navigation)
        ImageView mIvNavigation;
        @BindView(R.id.dv_divider)
        ImageView mDvDivider;

        ViewHolder(View itemView, OnViewClickListener listener) {
            super(itemView, listener);
        }

        @Override
        protected boolean isXRecyclerView() {
            return true;
        }
    }
}
