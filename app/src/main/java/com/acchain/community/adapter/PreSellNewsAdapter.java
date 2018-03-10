package com.acchain.community.adapter;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.acchain.community.R;
import com.zhy.autolayout.utils.AutoUtils;

public class PreSellNewsAdapter extends RecyclerView.Adapter<PreSellNewsAdapter.ViewHolder> {
    private Context context;
    private int[]data;

    public PreSellNewsAdapter(Context context){
        this(context,null);
    }
    public PreSellNewsAdapter(Context context, int[]data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pre_sell_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
    }


    @Override
    public int getItemCount() {
//        return dataSet != null ? dataSet.length : 0;
        return 6;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
        }
    }
}

