package com.acchain.community.adapter;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.bean.IndexDataBean;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

public class IndexPanicAdapter extends RecyclerView.Adapter<IndexPanicAdapter.ViewHolder> {
    private Context context;
    List<IndexDataBean.FlashSalesBean.ProductListBeanX> productList;

    public IndexPanicAdapter(Context context){
        this(context,null);
    }
    public IndexPanicAdapter(Context context, List<IndexDataBean.FlashSalesBean.ProductListBeanX> productList) {
        this.context = context;
        this.productList=productList;
    }

    public void setData(List<IndexDataBean.FlashSalesBean.ProductListBeanX> productList){
        this.productList=productList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_index_panic, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        IndexDataBean.FlashSalesBean.ProductListBeanX productListBeanX = productList.get(position);
        Glide.with(context).load(context.getString(R.string.test_base_image_address)+productListBeanX.getProductImg());
        holder.name.setText(productListBeanX.getProductName());
        holder.beforePrice.setText("￥"+(int)productListBeanX.getFlashProductPrice()+"");
        holder.beforePrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
        holder.nowPrice.setText((int)productListBeanX.getProductPrice()+"");
        holder.nowPrice.setTypeface(Typeface.DEFAULT_BOLD);//设置粗体
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(ARouterConst.Activity_PanicGoodsActivity).withInt("purchaseId", productListBeanX.getPurchaseId()).withInt("flashSaleId", productListBeanX.getFlashSaleId()).navigation();
            }
        });
    }


    @Override
    public int getItemCount() {
        return productList != null ? productList.size() : 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private View item;
        private FrameLayout fl;
        private ImageView image;
        private TextView name;
        private TextView beforePrice;
        private TextView nowPrice;
        public ViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            item=itemView.findViewById(R.id.item);
            fl=itemView.findViewById(R.id.fl);
            image=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.name);
            beforePrice=itemView.findViewById(R.id.before_price);
            nowPrice=itemView.findViewById(R.id.now_price);
        }
    }
}

