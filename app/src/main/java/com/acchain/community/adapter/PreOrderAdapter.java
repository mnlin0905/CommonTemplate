package com.acchain.community.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.base.BaseRecyclerViewHolder;
import com.acchain.community.bean.ConfirmPreOrder;
import com.acchain.community.view.AmountView;
import com.bumptech.glide.Glide;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;

public class PreOrderAdapter extends RecyclerView.Adapter<PreOrderAdapter.ViewHolder> {
    private Context context;
    private List<ConfirmPreOrder.OrderInfoBean> orderInfo;

    public PreOrderAdapter(Context context) {
        this(context, null);
    }

    public PreOrderAdapter(Context context, List<ConfirmPreOrder.OrderInfoBean> orderInfo) {
        this.context = context;
        this.orderInfo=orderInfo;
    }

    public void setData(List<ConfirmPreOrder.OrderInfoBean> orderInfo) {
        this.orderInfo=orderInfo;
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pre_sell_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ConfirmPreOrder.OrderInfoBean orderInfoBean = orderInfo.get(position);
        Glide.with(context).load(context.getString(R.string.test_base_image_address)+orderInfoBean.getProductImg());
        holder.goodsName.setText(orderInfoBean.getProductName());
        holder.goodsMoney.setText("￥"+(int)orderInfoBean.getPresellPrice());
        holder.goodsMoney.setTypeface(Typeface.DEFAULT_BOLD);
        holder.tvGoodsDescribe.setText(orderInfoBean.getShortDesc());
        holder.tvAttrs.setText(orderInfoBean.getProductAttr());
        holder.tvCount.setText("*"+orderInfoBean.getItemCount());
        holder.amountView.setNumber(orderInfoBean.getItemCount());
        holder.amountView.setGoods_storage(orderInfoBean.getPresellCirculation());
        holder.amountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, long amount) {
                orderInfoBean.setItemCount((int) amount);
                holder.tvCount.setText("*"+amount);
                if(mListener!=null){
                    mListener.onMoneyChange();
                }
            }
        });
    }

    private OnMoneyChangeListener mListener;
    public interface OnMoneyChangeListener{
        void  onMoneyChange();
    }
    public void setOnMoneyChangeListener(OnMoneyChangeListener mListener){
        this.mListener=mListener;
    }

    @Override
    public int getItemCount() {
        return orderInfo != null ? orderInfo.size() : 0;
    }

    public static class ViewHolder extends BaseRecyclerViewHolder {
        @BindView(R.id.merchant_image)
        ImageView merchantImage;
        @BindView(R.id.merchant_name)
        TextView merchantName;
        @BindView(R.id.goods_image)
        ImageView goodsImage;
        @BindView(R.id.goods_name)
        TextView goodsName;
        @BindView(R.id.goods_money)
        TextView goodsMoney;
        @BindView(R.id.tv_goods_describe)
        TextView tvGoodsDescribe;
        @BindView(R.id.tv_attrs)
        TextView tvAttrs;
        @BindView(R.id.tv_count)
        TextView tvCount;
        @BindView(R.id.amount_view)
        AmountView amountView;
        public ViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
        }

        @Override
        protected boolean isXRecyclerView() {
            return false;
        }
    }
}

