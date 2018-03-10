package com.acchain.community.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseRecyclerViewHolder;
import com.acchain.community.bean.ConfirmAdoptOrder;
import com.acchain.community.bean.ConfirmCartAdoptOrder;
import com.acchain.community.util.DateUtil;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;

public class AdoptCartOrderAdapter extends RecyclerView.Adapter<AdoptCartOrderAdapter.ViewHolder> {
    private Context context;
    private List<ConfirmCartAdoptOrder.OrderInfoBean> orderInfo;

    public AdoptCartOrderAdapter(Context context) {
        this(context, null);
    }

    public AdoptCartOrderAdapter(Context context, List<ConfirmCartAdoptOrder.OrderInfoBean> orderInfo) {
        this.context = context;
        this.orderInfo = orderInfo;
    }

    public void setData(List<ConfirmCartAdoptOrder.OrderInfoBean> orderInfo) {
        this.orderInfo = orderInfo;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart_adopt_confirm_order, parent, false);
        return new ViewHolder(view);
    }

    /*当前操作的项*/
    public int currentPosition;

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        AdoptCartOrderCodeAdapter adoptCartOrderCodeAdapter;
        ConfirmCartAdoptOrder.OrderInfoBean orderInfoBean = orderInfo.get(position);
        ConfirmCartAdoptOrder.OrderInfoBean.ProductsBean products = orderInfoBean.getProducts();
        products.setAdoptedCount(orderInfoBean.getAdoptCode().size());
        holder.tvName.setText(products.getAdoptName());
        holder.adoptPrice.setText(products.getAdoptPrice()+"");
        holder.adoptTime.setText(DateUtil.getBetweenDay(products.getAdoptStartTime(),products.getAdoptEndTime())+"天");
        holder.adoptLimmitCount.setText(products.getMaxAdoptQty()+"");
        holder.adoptCount.setText(products.getAdoptedCount()+"");
        holder.goodsMoney.setText("￥"+products.getAdoptedCount()*(int)products.getAdoptPrice());
        holder.codeRecycler.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false));
        adoptCartOrderCodeAdapter =new AdoptCartOrderCodeAdapter(context,orderInfoBean.getAdoptCode());
        holder.codeRecycler.setAdapter(adoptCartOrderCodeAdapter);
    }

    private OnMoneyChangeListener moneyChangeListener;

    public interface OnMoneyChangeListener{
        void onMoneyChange();
    }

    public void setOnMoneyChangeListener(OnMoneyChangeListener moneyChangeListener){
        this.moneyChangeListener=moneyChangeListener;
    }

    @Override
    public int getItemCount() {
        return orderInfo != null ? orderInfo.size() : 0;
    }

    public static class ViewHolder extends BaseRecyclerViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.adopt_price)
        TextView adoptPrice;
        @BindView(R.id.adopt_time)
        TextView adoptTime;
        @BindView(R.id.adopt_limmit_count)
        TextView adoptLimmitCount;
        @BindView(R.id.adopt_count)
        TextView adoptCount;
        @BindView(R.id.goods_money)
        TextView goodsMoney;
        @BindView(R.id.code_image)
        ImageView codeImage;
        @BindView(R.id.code_name)
        TextView codeName;
        @BindView(R.id.code_recycler)
        RecyclerView codeRecycler;
        @BindView(R.id.start_time)
        TextView startTime;
        @BindView(R.id.item)
        LinearLayout item;
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

