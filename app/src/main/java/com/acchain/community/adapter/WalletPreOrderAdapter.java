package com.acchain.community.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.base.BaseRecyclerViewHolder;
import com.acchain.community.bean.WalletPresellList;
import com.acchain.community.fragment.WalletPreOrderFragment;
import com.acchain.community.interfaces.PreOrderKindOperate;
import com.acchain.community.view.LightTextView;
import com.acchain.community.view.RoundRectBorderTextView;
import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created on 2018/1/2
 * function : 钱包币种展示(预购类)
 *
 * @author ACChain
 */

public class WalletPreOrderAdapter extends RecyclerView.Adapter<WalletPreOrderAdapter.ViewHolder> {

    private List<WalletPresellList> datas;
    private BaseRecyclerViewHolder.OnViewClickListener listener;
    private PreOrderKindOperate preOrderKindOperate;

    public WalletPreOrderAdapter(List<WalletPresellList> currencyBeans, WalletPreOrderFragment listener, WalletPreOrderFragment preOrderKindOperate) {
        this.datas = currencyBeans;
        this.listener = listener;
        this.preOrderKindOperate = preOrderKindOperate;
    }

    @Override
    public WalletPreOrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wallet_pre_order, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(WalletPreOrderAdapter.ViewHolder holder, int position) {
        // 绑定具体数据
        WalletPresellList bean = datas.get(position);
        Glide.with(holder.context)
                .load(bean.getPRODUCT_IMG())
                .into(holder.mIvIcon);
        holder.mTvCurrencyName.setText(bean.getPROPERTY_NAME());
        holder.mTvExercise.setVisibility(bean.isIS_EXERCISE()?View.VISIBLE:View.GONE);
        holder.mTvAmount.setText(String.valueOf(bean.getAccount_balance()));
        holder.mTvBuyPrice.setText(String.format(Locale.CHINA,"%s%.6f","￥",bean.getPRESELL_PRICE()));
        holder.mTvTime.setText(bean.getEXERCISE_END_TIME());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends BaseRecyclerViewHolder {
        @BindView(R.id.iv_icon)
        ImageView mIvIcon;
        @BindView(R.id.tv_currency_name)
        TextView mTvCurrencyName;
        @BindView(R.id.tv_amount)
        LightTextView mTvAmount;
        @BindView(R.id.tv_buy_price)
        TextView mTvBuyPrice;
        @BindView(R.id.tv_time)
        TextView mTvTime;
        @BindView(R.id.tv_exercise)
        RoundRectBorderTextView mTvExercise;

        public ViewHolder(View itemView, OnViewClickListener listener) {
            super(itemView, listener);
        }

        @Override
        protected boolean isXRecyclerView() {
            return true;
        }

        @OnClick({R.id.tv_take_in, R.id.tv_take_out, R.id.tv_exercise, R.id.tv_record})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.tv_take_in:
                    preOrderKindOperate.takeIn(getCurrentPosition());
                    break;
                case R.id.tv_take_out:
                    preOrderKindOperate.takeOut(getCurrentPosition());
                    break;
                case R.id.tv_exercise:
                    preOrderKindOperate.exercise(getCurrentPosition());
                    break;
                case R.id.tv_record:
                    preOrderKindOperate.flowRecord(getCurrentPosition());
                    break;
            }
        }
    }
}
