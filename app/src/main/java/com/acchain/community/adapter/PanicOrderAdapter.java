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
import com.acchain.community.bean.ConfirmPanicOrder;
import com.acchain.community.view.AmountView;
import com.bumptech.glide.Glide;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;

public class PanicOrderAdapter extends RecyclerView.Adapter<PanicOrderAdapter.ViewHolder> {
    private Context context;
    private List<ConfirmPanicOrder.OrderInfoBean> orderInfoBeans;

    public PanicOrderAdapter(Context context) {
        this(context, null);
    }

    public PanicOrderAdapter(Context context, List<ConfirmPanicOrder.OrderInfoBean> orderInfoBeans) {
        this.context = context;
        this.orderInfoBeans = orderInfoBeans;
    }

    public void setData(List<ConfirmPanicOrder.OrderInfoBean> orderInfoBeans) {
        this.orderInfoBeans = orderInfoBeans;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_panic_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ConfirmPanicOrder.OrderInfoBean orderInfoBean = orderInfoBeans.get(position);
        Glide.with(context).load(context.getString(R.string.test_base_image_address) + orderInfoBean.getProductImg());
        holder.goodsName.setText(orderInfoBean.getProductName());
        holder.goodsMoney.setText("￥" + (int) orderInfoBean.getFlashProductPrice());
        holder.goodsMoney.setTypeface(Typeface.DEFAULT_BOLD);
        holder.tvGoodsDescribe.setText(orderInfoBean.getShortDesc());
        holder.tvAttrs.setText(orderInfoBean.getProductAttr());
        holder.tvCount.setText("*" + orderInfoBean.getItemCount());
        holder.amountView.setNumber(orderInfoBean.getItemCount());
        holder.amountView.setGoods_storage(orderInfoBean.getProductQty());
        holder.amountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, long amount) {
                orderInfoBean.setItemCount((int) amount);
                holder.tvCount.setText("*" + amount);
                if (mListener != null) {
                    mListener.onMoneyChange();
                }
            }
        });
    }

    private OnMoneyChangeListener mListener;

    public interface OnMoneyChangeListener {
        void onMoneyChange();
    }

    public void setOnMoneyChangeListener(OnMoneyChangeListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public int getItemCount() {
        return orderInfoBeans != null ? orderInfoBeans.size() : 0;
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

