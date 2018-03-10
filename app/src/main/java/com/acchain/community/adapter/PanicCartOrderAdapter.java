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
import com.acchain.community.bean.ConfirmCartPanicOrder;
import com.bumptech.glide.Glide;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;

public class PanicCartOrderAdapter extends RecyclerView.Adapter<PanicCartOrderAdapter.ViewHolder> {
    private Context context;
    private List<ConfirmCartPanicOrder.OrderInfoBean> orderInfoBeans;

    public PanicCartOrderAdapter(Context context) {
        this(context, null);
    }

    public PanicCartOrderAdapter(Context context, List<ConfirmCartPanicOrder.OrderInfoBean> orderInfoBeans) {
        this.context = context;
        this.orderInfoBeans = orderInfoBeans;
    }

    public void setData(List<ConfirmCartPanicOrder.OrderInfoBean> orderInfoBeans) {
        this.orderInfoBeans = orderInfoBeans;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart_panic_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ConfirmCartPanicOrder.OrderInfoBean orderInfoBean = orderInfoBeans.get(position);
        ConfirmCartPanicOrder.OrderInfoBean.ProductsBean products = orderInfoBean.getProducts();
        Glide.with(context).load(context.getString(R.string.test_base_image_address) + products.getProductImg());
        holder.goodsName.setText(products.getProductName());
        holder.goodsMoney.setText("￥" + (int)products.getSaleProductDiscountPrice());
        holder.goodsMoney.setTypeface(Typeface.DEFAULT_BOLD);
        holder.tvGoodsDescribe.setText(products.getShortDesc());
        holder.tvAttrs.setText(orderInfoBean.getProductAttr());
        holder.tvCount.setText("*" + orderInfoBean.getItemCount());
        holder.tvGoodsFee.setText("￥"+(int)products.getSaleProductDiscountPrice()*orderInfoBean.getItemCount());
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
        @BindView(R.id.shipping_fee)
        TextView shippingFee;
        @BindView(R.id.tv_goods_fee)
        TextView tvGoodsFee;

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

