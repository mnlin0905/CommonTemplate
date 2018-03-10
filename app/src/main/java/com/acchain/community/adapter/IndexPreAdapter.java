package com.acchain.community.adapter;
import android.content.Context;
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

public class IndexPreAdapter extends RecyclerView.Adapter<IndexPreAdapter.ViewHolder> {
    private Context context;
    private List<IndexDataBean.ChannelsBean.BoothListBean.ProductListBean> productList;

    public IndexPreAdapter(Context context){
        this(context,null);
    }
    public IndexPreAdapter(Context context,  List<IndexDataBean.ChannelsBean.BoothListBean.ProductListBean> productList) {
        this.context = context;
        this.productList=productList;
    }

    public void setData(List<IndexDataBean.ChannelsBean.BoothListBean.ProductListBean> productList){
        this.productList=productList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_index_pre, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        IndexDataBean.ChannelsBean.BoothListBean.ProductListBean productBean = productList.get(position);
        if(position%2==0){
            holder.fl.setBackgroundResource(R.drawable.bg_pre_left);
        }else
//            holder.fl.setBackgroundResource(R.drawable.bg_pre_right);
            holder.fl.setBackgroundResource(R.drawable.bg_pre_left);
        Glide.with(context).load(context.getString(R.string.test_base_image_address)+productBean.getProductImg()).into(holder.image);
        holder.name.setText(productBean.getProductName());
        holder.price.setText("￥"+productBean.getPresellPrice());
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(ARouterConst.Activity_PreSellGoodsActivity).withInt("presellId",productBean.getPresellId()).navigation();
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
        private TextView price;

        public ViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            item=itemView.findViewById(R.id.item);
            fl=itemView.findViewById(R.id.fl);
            image=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.now_price);
        }
    }
}

