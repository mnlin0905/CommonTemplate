package com.acchain.community.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseRecyclerViewHolder;
import com.acchain.community.bean.AdoptProduct;
import com.acchain.community.util.DateUtil;
import com.acchain.community.util.L;
import com.acchain.community.view.NumberProgressView;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AdoptAdapter extends RecyclerView.Adapter<AdoptAdapter.ViewHolder> {

    private Context context;
    private List<AdoptProduct.ListBean> list;

    public AdoptAdapter(Context context) {
        this(context, null);
    }

    public AdoptAdapter(Context context, List<AdoptProduct.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setData(List<AdoptProduct.ListBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_adopt, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        AdoptProduct.ListBean listBean = list.get(position);
        holder.adoptName.setText(listBean.getAdoptName());
        holder.numberProgress.setProgress(listBean.getAdoptedCount()*100/listBean.getAdoptCirculation());
        holder.tvPrice.setText(listBean.getAdoptPrice()+"");
        holder.timeLimit.setText(DateUtil.getBetweenDay(listBean.getAdoptStartTime(),listBean.getAdoptEndTime())+"天");
        holder.adoptCount.setTag(listBean.getAdoptedCount()+"人已领养");
        holder.ivAdopt.setOnClickListener(v -> {
            ARouter.getInstance().build(ARouterConst.Activity_AdoptGoodsActivity).withInt("adoptId",listBean.getAdoptId()).navigation();
        });
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public static class ViewHolder extends BaseRecyclerViewHolder {
        @BindView(R.id.adopt_name)
        TextView adoptName;
        @BindView(R.id.number_progress)
        NumberProgressView numberProgress;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.time_limit)
        TextView timeLimit;
        @BindView(R.id.adopt_count)
        TextView adoptCount;
        @BindView(R.id.iv_adopt)
        ImageView ivAdopt;

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

