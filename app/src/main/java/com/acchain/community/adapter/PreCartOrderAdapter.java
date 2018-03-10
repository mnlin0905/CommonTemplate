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
import com.acchain.community.bean.ConfirmCartPreOrder;
import com.bumptech.glide.Glide;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;

public class PreCartOrderAdapter extends RecyclerView.Adapter<PreCartOrderAdapter.ViewHolder> {
    private Context context;
    private List<ConfirmCartPreOrder.OrderInfoBean> orderInfo;

    public PreCartOrderAdapter(Context context) {
        this(context, null);
    }

    public PreCartOrderAdapter(Context context, List<ConfirmCartPreOrder.OrderInfoBean> orderInfo) {
        this.context = context;
        this.orderInfo = orderInfo;
    }

    public void setData(List<ConfirmCartPreOrder.OrderInfoBean> orderInfo) {
        this.orderInfo = orderInfo;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart_pre_sell_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ConfirmCartPreOrder.OrderInfoBean orderInfoBean = orderInfo.get(position);
        ConfirmCartPreOrder.OrderInfoBean.ProductsBean products = orderInfoBean.getProducts();
        Glide.with(context).load(context.getString(R.string.test_base_image_address) + products.getProductImg());
        holder.goodsName.setText(products.getProductName());
        holder.goodsMoney.setText("￥" + (int)products.getPresellPrice());
        holder.goodsMoney.setTypeface(Typeface.DEFAULT_BOLD);
        holder.tvGoodsDescribe.setText(products.getShortDesc());
        holder.tvAttrs.setText(orderInfoBean.getProductAttr());
        holder.tvCount.setText("*" + orderInfoBean.getItemCount());
        holder.startTime.setText(products.getExerciseStartTime());
        holder.tvGoodFee.setText("￥" + orderInfoBean.getItemCount() * (int)products.getPresellPrice());
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
        @BindView(R.id.start_time)
        TextView startTime;
        @BindView(R.id.tv_goods_fee)
        TextView tvGoodFee;

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

