package com.acchain.community.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.base.BaseRecyclerViewHolder;
import com.acchain.community.bean.ReceverRedPacketRecord;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by rsp on 2018/1/31.
 */

public class ReceverRedPacketRecordAdapter extends RecyclerView.Adapter<ReceverRedPacketRecordAdapter.ViewHolder> {
    private List<ReceverRedPacketRecord.ReceivedRedEnvelopesListBean> listBeans;

    public ReceverRedPacketRecordAdapter(List<ReceverRedPacketRecord.ReceivedRedEnvelopesListBean> listBeans) {
        this.listBeans = listBeans;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recever_red_packet, parent, false);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ReceverRedPacketRecord.ReceivedRedEnvelopesListBean item = listBeans.get(position);
        Glide.with(holder.itemView)
                .load(item.getPhoto_img())
                .apply(new RequestOptions().centerCrop())
                .into(holder.receverHeadImg);
        holder.receverAmount.setText(String.valueOf(item.getAmount()));
        holder.receverCurrency.setText(item.getCurrency());
        holder.receverNikeName.setText(item.getNickname());
        holder.receverTime.setText(item.getCreate_time());
    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    static class ViewHolder extends BaseRecyclerViewHolder {
        @BindView(R.id.recever_HeadImg)
        RoundedImageView receverHeadImg;
        @BindView(R.id.recever_nikeName)
        TextView receverNikeName;
        @BindView(R.id.recever_Time)
        TextView receverTime;
        @BindView(R.id.recever_Amount)
        TextView receverAmount;
        @BindView(R.id.recever_Currency)
        TextView receverCurrency;
        public ViewHolder(View itemView) {
            super(itemView);
        }

        public ViewHolder(View itemView, OnViewClickListener listener) {
            super(itemView, listener);
        }

        @Override
        protected boolean isXRecyclerView() {
            return true;
        }
    }
}
