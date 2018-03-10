package com.acchain.community.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.bean.PanicGood;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

public class PanicAdapter extends RecyclerView.Adapter<PanicAdapter.ViewHolder> {
    private Context context;
    private PanicGood panicGood;
    private List<PanicGood.ProductListBean> productList;

    public PanicAdapter(Context context) {
        this(context, null);
    }

    public PanicAdapter(Context context, PanicGood panicGood) {
        this.context = context;
        this.panicGood = panicGood;
        if (panicGood != null)
            this.productList = panicGood.getProductList();
    }

    public void setData(PanicGood panicGood) {
        this.panicGood = panicGood;
        this.productList = panicGood.getProductList();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_panic, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        PanicGood.ProductListBean productListBean = productList.get(position);
        Glide.with(context).load(context.getString(R.string.test_base_image_address)+productListBean.getProductImg()).into(holder.imageView);
        holder.name.setText(productListBean.getProductName());
        holder.describe.setText(productListBean.getShortDesc());
        holder.nowPrice.setText((int) productListBean.getProductDiscountPrice() + "");
        holder.nowPrice.setTypeface(Typeface.DEFAULT_BOLD);//设置粗体
        holder.beforePrice.setText((int) productListBean.getProductPrice() + "");
        holder.beforePrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
        holder.lastCount.setText("仅剩" + productListBean.getProductQty() + productListBean.getProductPackUnitId());
        holder.toPanic.setOnClickListener(v -> {
            ARouter.getInstance().build(ARouterConst.Activity_PanicGoodsActivity).withInt("purchaseId", productListBean.getPurchaseId()).withInt("flashSaleId", panicGood.getId()).navigation();
        });
    }


    @Override
    public int getItemCount() {
        return productList != null ? productList.size() : 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView name, describe, nowPrice, beforePrice, lastCount, toPanic;

        public ViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            imageView = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            describe = itemView.findViewById(R.id.describe);
            nowPrice = itemView.findViewById(R.id.now_price);
            beforePrice = itemView.findViewById(R.id.before_price);
            lastCount = itemView.findViewById(R.id.last_count);
            toPanic = itemView.findViewById(R.id.to_panic);
        }
    }
}

