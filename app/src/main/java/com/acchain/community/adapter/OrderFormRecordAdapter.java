package com.acchain.community.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.base.BaseRecyclerViewHolder;
import com.acchain.community.bean.OrderFormRecordBean;
import com.acchain.community.view.LightTextView;
import com.acchain.community.view.LineMenuView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created on 2018/1/25
 * function : 订单记录
 *
 * @author ACChain
 */

public class OrderFormRecordAdapter extends RecyclerView.Adapter<OrderFormRecordAdapter.ViewHolder> {
    private List<OrderFormRecordBean> datas;
    private BaseRecyclerViewHolder.OnViewClickListener listener;

    public OrderFormRecordAdapter(List<OrderFormRecordBean> datas, BaseRecyclerViewHolder.OnViewClickListener listener) {
        this.datas = datas;
        this.listener = listener;
    }

    @Override
    public OrderFormRecordAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrderFormRecordAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_form_record, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(OrderFormRecordAdapter.ViewHolder holder, int position) {
        // TODO: 2018/1/2 绑定具体数据
    }

    @Override
    public int getItemCount() {
        return  datas.size();
    }

    class ViewHolder extends BaseRecyclerViewHolder {

        @BindView(R.id.lmv_head)
        LineMenuView mLmvHead;
        @BindView(R.id.iv_icon)
        ImageView mIvIcon;
        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.tv_price)
        LightTextView mTvPrice;
        @BindView(R.id.tv_introduce)
        TextView mTvIntroduce;
        @BindView(R.id.tv_spec)
        TextView mTvSpec;
        @BindView(R.id.tv_amount)
        TextView mTvAmount;
        @BindView(R.id.tv_transport_type)
        TextView mTvTransportType;
        @BindView(R.id.tv_all_money)
        TextView mTvAllMoney;
        @BindView(R.id.tv_cancel_order)
        TextView mTvCancelOrder;
        @BindView(R.id.tv_go_pay)
        TextView mTvGoPay;
        @BindView(R.id.tv_remind_deliver)
        TextView mTvRemindDeliver;
        @BindView(R.id.tv_view_logistics)
        TextView mTvViewLogistics;
        @BindView(R.id.tv_confirm_receive)
        TextView mTvConfirmReceive;
        @BindView(R.id.tv_delete_order)
        TextView mTvDeleteOrder;

        public ViewHolder(View itemView, OnViewClickListener listener) {
            super(itemView, listener);
        }

        @Override
        protected boolean isXRecyclerView() {
            return true;
        }

        @OnClick({R.id.tv_cancel_order, R.id.tv_go_pay, R.id.tv_remind_deliver, R.id.tv_view_logistics, R.id.tv_confirm_receive, R.id.tv_delete_order})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.tv_cancel_order:
                    break;
                case R.id.tv_go_pay:
                    break;
                case R.id.tv_remind_deliver:
                    break;
                case R.id.tv_view_logistics:
                    break;
                case R.id.tv_confirm_receive:
                    break;
                case R.id.tv_delete_order:
                    break;
            }
        }
    }
}