package com.acchain.community.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.base.BaseRecyclerViewHolder;
import com.acchain.community.bean.AdoptBaseCode;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;

public class AdoptBaseAdapter extends RecyclerView.Adapter<AdoptBaseAdapter.ViewHolder> {
    private Context context;
    private int type;
    private List<AdoptBaseCode.ListBean> list;

    public AdoptBaseAdapter(Context context, int type) {
        this.context = context;
        this.type = type;
    }

    public void setData(List<AdoptBaseCode.ListBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_adopt_base, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        AdoptBaseCode.ListBean listBean = list.get(position);
        holder.cb.setVisibility(type == 0 ? View.GONE : View.VISIBLE);
        holder.tvName.setText(listBean.getAdoptCode());
        holder.tvState.setText(listBean.getAdoptStatus() == 0 ? "无主人" : "被领养");
        holder.tvVideoMonitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.tvPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        if (type == 1||type==2) {
            holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    listBean.setChoose(isChecked);
                    if (mListener != null) {
                        mListener.onChoose(position, isChecked);
                    }
                }
            });
        }
    }

    public interface OnChooseListener {
        void onChoose(int position, boolean isChecked);
    }

    private OnChooseListener mListener;

    public void setOnChooseListener(OnChooseListener listener) {
        mListener = listener;
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }


    public static class ViewHolder extends BaseRecyclerViewHolder {
        @BindView(R.id.cb)
        CheckBox cb;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_state)
        TextView tvState;
        @BindView(R.id.tv_video_monitor)
        TextView tvVideoMonitor;
        @BindView(R.id.tv_picture)
        TextView tvPicture;
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

