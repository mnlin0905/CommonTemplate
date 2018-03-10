package com.acchain.community.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.base.BaseRecyclerViewHolder;
import com.acchain.community.bean.RefundAftermarketBean;
import com.acchain.community.view.LightTextView;
import com.acchain.community.view.LineMenuView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created on 2018/2/3
 * function : 退货退款
 *
 * @author ACChain
 */
public class RefundAftermarketAdapter extends RecyclerView.Adapter<RefundAftermarketAdapter.ViewHolder> {
    private final OnRefundAftermarketListener listener;
    private List<RefundAftermarketBean> datas;

    public RefundAftermarketAdapter(List<RefundAftermarketBean> datas, OnRefundAftermarketListener listener) {
        this.datas = datas;
        if(listener==null){
            throw  new RuntimeException("监听器不能为null");
        }
        this.listener = listener;
    }

    @Override
    public RefundAftermarketAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RefundAftermarketAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_refund_aftermarket, parent, false));
    }

    @Override
    public void onBindViewHolder(RefundAftermarketAdapter.ViewHolder holder, int position) {
        // TODO: 2018/1/2 绑定具体数据
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public interface OnRefundAftermarketListener {
        /**
         * 当点击查看详情
         *
         * @param position position
         */
        void onClickCheckDetail(int position);

        /**
         * 点击取消退款
         *
         * @param position position
         */
        void onClickCancelRefund(int position);

        /**
         * 当点击item自身
         *
         * @param position position
         */
        void onClickSelf(int position);
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
        @BindView(R.id.tv_status)
        TextView mTvStatus;
        @BindView(R.id.tv_cancel_refund)
        TextView mTvCancelRefund;
        @BindView(R.id.tv_check_detail)
        TextView mTvCheckDetail;

        public ViewHolder(View inflate) {
            super(inflate);
            setOnViewClickListener((v, position) -> listener.onClickSelf(position));
        }

        @Override
        protected boolean isXRecyclerView() {
            return true;
        }

        @OnClick({R.id.tv_cancel_refund, R.id.tv_check_detail})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.tv_cancel_refund://取消退款
                    listener.onClickCancelRefund(getCurrentPosition());
                    break;
                case R.id.tv_check_detail://查看详情
                    listener.onClickCheckDetail(getCurrentPosition());
                    break;
            }
        }
    }
}
