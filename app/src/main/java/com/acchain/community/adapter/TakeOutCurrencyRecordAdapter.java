package com.acchain.community.adapter;

import android.app.Dialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.base.BaseRecyclerViewHolder;
import com.acchain.community.bean.TransactionItemBean;
import com.acchain.community.interfaces.CallBackAfterItemMeasure;
import com.acchain.community.window.CancelConfirmDialogFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created on 2018/1/12
 * function : 转出记录
 *
 * @author ACChain
 */

public class TakeOutCurrencyRecordAdapter extends RecyclerView.Adapter<TakeOutCurrencyRecordAdapter.ViewHolder> {
    private BaseActivity baseActivity;
    private List<TransactionItemBean> datas;
    private BaseRecyclerViewHolder.OnViewClickListener listener;
    private onCancelTakeOutListener cancelTakeOutListener;
    private CallBackAfterItemMeasure callBackAfterItemMeasure;

    /**
     * 是否禁止处理callback
     */
    private volatile boolean forbid;

    public TakeOutCurrencyRecordAdapter(BaseActivity baseActivity, List<TransactionItemBean> datas, BaseRecyclerViewHolder.OnViewClickListener listener, onCancelTakeOutListener cancelTakeOutListener, CallBackAfterItemMeasure callBackAfterItemMeasure) {
        this.baseActivity = baseActivity;
        this.datas = datas;
        this.listener = listener;
        this.cancelTakeOutListener = cancelTakeOutListener;
        this.callBackAfterItemMeasure = callBackAfterItemMeasure;
    }

    @Override
    public TakeOutCurrencyRecordAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TakeOutCurrencyRecordAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_take_out_currency_record, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(TakeOutCurrencyRecordAdapter.ViewHolder holder, int position) {
        TransactionItemBean transactionItemBean = datas.get(position);
        holder.mTvTime.setText(transactionItemBean.getCreateTime());
        holder.mTvAmount.setText(transactionItemBean.getAmountStrWithSymbol());
        holder.mTvStatus.setText(transactionItemBean.getStatusStr());
        holder.mTvStatus.setTextColor(transactionItemBean.getStatusColor(baseActivity));
        holder.mTvOperate.setText(transactionItemBean.getOperateStr());
        holder.mTvOperate.setTextColor(transactionItemBean.getOperateColor(baseActivity));
        if(position==datas.size()-1){
            holder.mDvDivider.setVisibility(View.INVISIBLE);
        }

        if(callBackAfterItemMeasure!=null&&!forbid) {
            postMeasure(holder.mTvTime,CallBackAfterItemMeasure.TAG_ONE);
            postMeasure(holder.mTvStatus,CallBackAfterItemMeasure.TAG_TWO);
            postMeasure(holder.mTvOperate,CallBackAfterItemMeasure.TAG_THREE);
        }
    }

    /**
     * 通知callback执行
     */
    private void postMeasure(View view,int tag){
        view.post(() -> {
            int measuredWidth = view.getMeasuredWidth();
            int measuredHeight =view.getMeasuredHeight();
            //只有宽度和高度为有效值时,才会进行调用
            if(measuredHeight>0&&measuredWidth>0&&callBackAfterItemMeasure.doAfterItemMeasure(tag,measuredWidth,measuredHeight)) {
                forbid=true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return  datas.size();
    }

    class ViewHolder extends BaseRecyclerViewHolder implements CancelConfirmDialogFragment.OnOperateListener {
        @BindView(R.id.tv_time)
        TextView mTvTime;
        @BindView(R.id.tv_amount)
        TextView mTvAmount;
        @BindView(R.id.tv_status)
        TextView mTvStatus;
        @BindView(R.id.tv_operate)
        TextView mTvOperate;
        @BindView(R.id.iv_navigation)
        ImageView mIvNavigation;
        @BindView(R.id.dv_divider)
        ImageView mDvDivider;

        ViewHolder(View itemView, OnViewClickListener listener) {
            super(itemView, listener);
        }

        @OnClick({R.id.tv_operate})
        public void onViewClicked(View view) {
            if(datas.get(getCurrentPosition()).getOperateAble()){
                new CancelConfirmDialogFragment()
                        .setTitle("确定要撤销吗?")
                        .setCancelText("取消")
                        .setConfirmText("确认")
                        .setOnOperateListener(this)
                        .show(baseActivity.getSupportFragmentManager(),"cancel");
            }else{
                //如果不可操作,则默认为是点击了item
                itemView.performClick();
            }
        }

        @Override
        protected boolean isXRecyclerView() {
            return true;
        }

        /**
         * 点击左侧按钮
         *
         * @param dialog dialog
         * @return 返回true则默认不会关闭dialog
         */
        @Override
        public boolean onCancel(Dialog dialog) {
            return false;
        }

        /**
         * 点击右侧按钮
         *
         * @param dialog dialog
         */
        @Override
        public boolean onConfirm(Dialog dialog) {
            cancelTakeOutListener.onCancelTakeOut(getCurrentPosition());
            return false;
        }
    }

    /**
     * 撤销监听事件
     */
    public interface onCancelTakeOutListener{
        /**
         * @param position 需要撤销的item位置
         */
        void onCancelTakeOut(int position);
    }
}
