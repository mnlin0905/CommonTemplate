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
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.bean.PreSellProduct;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

public class PreSellAdapter extends RecyclerView.Adapter<PreSellAdapter.ViewHolder> {
    private Context context;

   private List<PreSellProduct.ListBean> listBeans;

    public PreSellAdapter(Context context){
        this(context,null);
    }
    public PreSellAdapter(Context context, List<PreSellProduct.ListBean> productList) {
        this.context = context;
        this.listBeans = productList;
    }

    public void setData(List<PreSellProduct.ListBean> productList){
        this.listBeans =productList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pre_sell, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        PreSellProduct.ListBean.ProductsBean products = listBeans.get(position).getProducts();
        Glide.with(context).load(context.getString(R.string.test_base_image_address)+products.getProductImg()).into(holder.imageView);
        holder.name.setText(products.getProductName());
        holder.describe.setText(products.getShortDesc());
        holder.nowPrice.setText((int)products.getProductDiscountPrice()+"");
        holder.nowPrice.setTypeface(Typeface.DEFAULT_BOLD);//设置粗体
        holder.soldCount.setText(products.getProductQty()+"人已预购");
        holder.toPreSell.setOnClickListener(v -> {
            ARouter.getInstance().build(ARouterConst.Activity_PreSellGoodsActivity).withInt("presellId",listBeans.get(position).getId()).navigation();
        });
    }

    @Override
    public int getItemCount() {
        return listBeans != null ? listBeans.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView name,describe,nowPrice,soldCount,toPreSell;
        public ViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            imageView=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.name);
            describe=itemView.findViewById(R.id.describe);
            nowPrice=itemView.findViewById(R.id.now_price);
            soldCount=itemView.findViewById(R.id.soldCount);
            toPreSell=itemView.findViewById(R.id.to_pre_sell);
        }
    }
}

