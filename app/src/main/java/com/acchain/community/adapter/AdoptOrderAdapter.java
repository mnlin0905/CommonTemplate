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
import com.acchain.community.util.Const;
import com.acchain.community.util.DateUtil;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;

public class AdoptOrderAdapter extends RecyclerView.Adapter<AdoptOrderAdapter.ViewHolder> {
    private Context context;
    private List<ConfirmAdoptOrder.OrderInfoBean> orderInfo;

    public AdoptOrderAdapter(Context context) {
        this(context, null);
    }

    public AdoptOrderAdapter(Context context, List<ConfirmAdoptOrder.OrderInfoBean> orderInfo) {
        this.context = context;
        this.orderInfo = orderInfo;
    }

    public void setData(List<ConfirmAdoptOrder.OrderInfoBean> orderInfo) {
        this.orderInfo = orderInfo;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_adopt_confirm_order, parent, false);
        return new ViewHolder(view);
    }

    /*当前操作的项*/
    public int currentPosition;

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        AdoptOrderCodeAdapter adoptOrderCodeAdapter;
        ConfirmAdoptOrder.OrderInfoBean orderInfoBean = orderInfo.get(position);
        orderInfoBean.setAdoptedCount(orderInfoBean.getAdoptCode().size());
        holder.tvName.setText(orderInfoBean.getAdoptName());
        holder.adoptPrice.setText(orderInfoBean.getAdoptPrice()+"");
        holder.adoptTime.setText(DateUtil.getBetweenDay(orderInfoBean.getAdoptStartTime(),orderInfoBean.getAdoptEndTime())+"天");
        holder.adoptLimmitCount.setText(orderInfoBean.getMaxAdoptQty()+"");
        holder.adoptCount.setText(orderInfoBean.getAdoptedCount()+"");
        holder.goodsMoney.setText("￥"+orderInfoBean.getAdoptedCount()*orderInfoBean.getAdoptPrice());
        holder.codeRecycler.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false));
        adoptOrderCodeAdapter =new AdoptOrderCodeAdapter(context,orderInfoBean.getAdoptCode());
        adoptOrderCodeAdapter.setOnCountChangeListener(() -> {
            orderInfoBean.setAdoptedCount(orderInfoBean.getAdoptedCount()-1);//数量减1
            holder.adoptCount.setText(orderInfoBean.getAdoptedCount()+"");
            holder.goodsMoney.setText("￥"+orderInfoBean.getAdoptedCount()*orderInfoBean.getAdoptPrice());//重置money
            if(moneyChangeListener!=null){
                moneyChangeListener.onMoneyChange();
            }
        });
        holder.codeRecycler.setAdapter(adoptOrderCodeAdapter);
        holder.addCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPosition=position;
                ARouter.getInstance().build(ARouterConst.Activity_ChooseBaseActivity).withInt(Const.KEY_ADOPT_ID, orderInfoBean.getAdoptId()).withInt(Const.KEY_OBJECT_TYPE, Const.TYPE_CHOOSE_OBJECT).withString(Const.KEY_PRODUCT_NAME, orderInfoBean.getPropertyName()).navigation();
            }
        });
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
        @BindView(R.id.add_code)
        TextView addCode;
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

