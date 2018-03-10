package com.acchain.community.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.activity.index.PanicActivity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

public class PanicTimeAdapter extends RecyclerView.Adapter<PanicTimeAdapter.ViewHolder> {
    private Context context;
    List<PanicActivity.PanicTime> timeList;

    public PanicTimeAdapter(Context context) {
        this(context, null);
    }

    public PanicTimeAdapter(Context context, List<PanicActivity.PanicTime> timeList) {
        this.context = context;
        this.timeList = timeList;
    }

    public void setData(List<PanicActivity.PanicTime> timeList){
        this.timeList = timeList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_panic_time, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        PanicActivity.PanicTime panicTime = timeList.get(position);
        holder.time.setText(panicTime.time);
        if(panicTime.type==0){//即将开抢
            holder.describe.setText(context.getString(R.string.tip_will_panic));
        }else if(panicTime.type==1){//
            holder.describe.setText(context.getString(R.string.tip_have_panic));
        }else {
            holder.describe.setText(context.getString(R.string.tip_is_panic));
            holder.time.setTextColor(context.getResources().getColor(R.color.white));
            holder.time.setTypeface(Typeface.DEFAULT_BOLD);//设置粗体
            holder.describe.setTextColor(context.getResources().getColor(R.color.white));
            holder.describe.setTypeface(Typeface.DEFAULT_BOLD);//设置粗体
        }

    }


    @Override
    public int getItemCount() {
        return timeList != null ? timeList.size() : 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView time;
        private TextView describe;

        public ViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            time = itemView.findViewById(R.id.time);
            describe = itemView.findViewById(R.id.describe);
        }
    }
}

