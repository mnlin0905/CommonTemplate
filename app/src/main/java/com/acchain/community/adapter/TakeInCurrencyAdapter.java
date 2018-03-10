package com.acchain.community.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.base.BaseRecyclerViewHolder;
import com.acchain.community.bean.CurrencyBean;
import com.acchain.community.bean.TakeInCurrencyBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created on 2018/1/10
 * function : 数字资产转入
 *
 * @author ACChain
 */

public class TakeInCurrencyAdapter extends RecyclerView.Adapter<TakeInCurrencyAdapter.ViewHolder> {
    private List<TakeInCurrencyBean> datas;
    private OnItemItemClickListener listener;

    public TakeInCurrencyAdapter(List<TakeInCurrencyBean> datas, OnItemItemClickListener listener) {
        this.datas = datas;
        this.listener = listener;
    }

    @Override
    public TakeInCurrencyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TakeInCurrencyAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_take_in_currency, parent, false));
    }

    @Override
    public void onBindViewHolder(TakeInCurrencyAdapter.ViewHolder holder, int position) {
        TakeInCurrencyBean takeInCurrencyBean = datas.get(position);
        holder.mTvLabel.setText(takeInCurrencyBean.getChainName());
        holder.currencyBeans.clear();
        holder.currencyBeans.addAll(takeInCurrencyBean.getCurrencies());
        holder.takeInCurrencyItemAdapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return  datas.size();
    }

    /**
     * 当recycler中的grid中item被点击时，触发事件
     */
    public interface OnItemItemClickListener {
        /**
         * @param outerPosition recyclerView被点击的位置
         * @param innerPosition gridView被点击的位置
         * @param view          被点击的view
         */
        void onItemItemClick(int outerPosition, int innerPosition, View view);
    }

    class ViewHolder extends BaseRecyclerViewHolder {
        @BindView(R.id.tv_label)
        TextView mTvLabel;

        /**
         * 币种信息
         */
        @BindView(R.id.gv_currency)
        GridView mGvCurrency;
        ArrayList<CurrencyBean> currencyBeans;
        TakeInCurrencyItemAdapter takeInCurrencyItemAdapter;

        public ViewHolder(View itemView) {
            super(itemView);

            //初始化GridView,刷新数据源,设定适配器
            currencyBeans = new ArrayList<>();
            takeInCurrencyItemAdapter = new TakeInCurrencyItemAdapter(itemView.getContext(), currencyBeans);
            mGvCurrency.setAdapter(takeInCurrencyItemAdapter);
            mGvCurrency.setOnItemClickListener((parent, view, position, id) -> {
                if (listener != null) {
                    listener.onItemItemClick(getCurrentPosition(), position, view);
                }
            });
        }

        @Override
        protected boolean isXRecyclerView() {
            return true;
        }
    }
}
