package com.acchain.community.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created on 2018/1/2
 * function : recyclerView 的视图存储器
 *
 * @author ACChain
 */

public abstract class BaseRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    OnViewClickListener listener;
    public Context context;

    public BaseRecyclerViewHolder(View itemView) {
        this(itemView, null);
    }

    public BaseRecyclerViewHolder(View itemView, OnViewClickListener listener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        context = itemView.getContext();
        this.listener = listener;
        if (isClickable()) {
            itemView.setOnClickListener(this);
        }
    }

    @Override
    public final void onClick(View v) {
        if (listener != null) {
            listener.onViewClick(v, getCurrentPosition());
        }
    }

    /**
     * 获取当前位置
     */
    protected int getCurrentPosition() {
        return getAdapterPosition() - (isXRecyclerView() ? 1 : 0);
    }

    /**
     * 是否可点击
     */
    protected boolean isClickable() {
        return true;
    }

    /**
     * 是否是XRecyclerView
     */
    protected abstract boolean isXRecyclerView();

    /**
     * 自定义view点击事件
     */
    public interface OnViewClickListener {
        /**
         * @param v        被点击的view
         * @param position 所在的position
         */
        void onViewClick(View v, int position);
    }

    protected void setOnViewClickListener(OnViewClickListener listener){
        this.listener=listener;
    }

    protected Context getContext(){
        return itemView.getContext();
    }
}
