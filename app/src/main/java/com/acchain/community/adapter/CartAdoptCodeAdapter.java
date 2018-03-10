package com.acchain.community.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.base.BaseRecyclerViewHolder;
import com.acchain.community.bean.AdoptGoodDetail;
import com.acchain.community.bean.CartAdoptGoods;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;

public class CartAdoptCodeAdapter extends RecyclerView.Adapter<CartAdoptCodeAdapter.ViewHolder> {

    private Context context;
    private List<AdoptGoodDetail.DefaultAdoptCodeBean> adoptCodeBeans;

    public CartAdoptCodeAdapter(Context context, List<AdoptGoodDetail.DefaultAdoptCodeBean> adoptCodeBeans) {
        this.context = context;
        this.adoptCodeBeans = adoptCodeBeans;
    }

    /*当增加标的*/
    public void setData(List<AdoptGoodDetail.DefaultAdoptCodeBean> adoptCodeBeans){
        this.adoptCodeBeans.addAll(adoptCodeBeans);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart_adopt_code, parent, false);
        return new ViewHolder(view);
    }

    private OnCountChangeListener onCountChangeListener;

    public interface OnCountChangeListener{
        void onCountChange(int codePosition);
    }

    public void setOnCountChangeListener(OnCountChangeListener onCountChangeListener){
        this.onCountChangeListener=onCountChangeListener;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        AdoptGoodDetail.DefaultAdoptCodeBean bean = adoptCodeBeans.get(position);
        holder.tvName.setText(bean.getAdoptCode());
        holder.tvVideoMonitor.setOnClickListener(v -> {
        });
        holder.tvPictureCode.setOnClickListener(v -> {});
        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onCountChangeListener!=null){
                    onCountChangeListener.onCountChange(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return adoptCodeBeans != null ? adoptCodeBeans.size() : 0;
    }

    public static class ViewHolder extends BaseRecyclerViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_video_monitor)
        TextView tvVideoMonitor;
        @BindView(R.id.tv_picture_code)
        TextView tvPictureCode;
        @BindView(R.id.tv_delete)
        TextView tvDelete;
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

