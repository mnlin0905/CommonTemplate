package com.acchain.community.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.base.BaseRecyclerViewHolder;
import com.acchain.community.bean.DeliveryAddressBean;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created on 2018/1/16
 * function : 修改收货地址
 *
 * @author ACChain
 */

public class ChangeDeliveryAddressAdapter extends RecyclerView.Adapter<ChangeDeliveryAddressAdapter.ViewHolder> {

    private List<DeliveryAddressBean> datas;
    private onDeliveryItemClickerListener listener;

    public ChangeDeliveryAddressAdapter(List<DeliveryAddressBean> datas, onDeliveryItemClickerListener listener) {
        this.datas = datas;
        this.listener = listener;
    }

    @Override
    public ChangeDeliveryAddressAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_change_delivery_address, parent, false));
    }

    @Override
    public void onBindViewHolder(ChangeDeliveryAddressAdapter.ViewHolder holder, int position) {
        // 当为defaut和非default时,文字会有变化,颜色也会有变化
        DeliveryAddressBean bean=datas.get(position);
        holder.mTvName.setText(bean.getName());
        holder.mTvPhone.setText(bean.getMobile().replaceAll("^([\\d]{3}).*([\\d]{3})$", "$1****$2"));
        holder.mTvAddress.setText(bean.getAddress());
        if(bean.isDefaultAddress()){
            holder.setDefault(position);
        }else{
            holder.cancelDefault(position);
        }
    }

    @Override
    public int getItemCount() {
        return  datas.size();
    }

    /**
     * 点击操作处理
     */
    public interface onDeliveryItemClickerListener {
        /**
         * @param position  更改的位置
         */
        void onClickDefault(int position, DefaultCallback callback);

        /**
         * @param position 删除的位置
         */
        void onDelete(int position);

        /**
         * @param position 修改的位置
         */
        void onEdit(int position);
    }

    /**
     * 回调函数
     */
    public interface DefaultCallback{
        /**
         * @param position 将位置postion设置选中默认
         */
        void setDefault(int position);

        /**
         * @param position 将posisiton位置取消默认的选中
         */
        void cancelDefault(int position);
    }

    class ViewHolder extends BaseRecyclerViewHolder implements ChangeDeliveryAddressAdapter.DefaultCallback{

        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.tv_phone)
        TextView mTvPhone;
        @BindView(R.id.tv_address)
        TextView mTvAddress;
        @BindView(R.id.iv_default)
        ImageView mIvDefault;
        @BindView(R.id.tv_default)
        TextView mTvDefault;
        @BindView(R.id.tv_delete)
        TextView mTvDelete;
        @BindView(R.id.tv_edit)
        TextView mTvEdit;

        ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected boolean isXRecyclerView() {
            return true;
        }

        @Override
        protected boolean isClickable() {
            return false;
        }

        @OnClick({R.id.iv_default, R.id.tv_default, R.id.tv_delete, R.id.tv_edit})
        public void onViewClicked(View view) {
            if(listener==null){
                return;
            }

            switch (view.getId()) {
                case R.id.iv_default:
                case R.id.tv_default:
                    // 切换默认的选中地址
                    // 判断是否需要调用切换事件,本来就是选中状态的话,不需要调用
                    listener.onClickDefault(getCurrentPosition(),this);
                    break;
                case R.id.tv_delete:
                    // 删除
                    listener.onDelete(getCurrentPosition());
                    break;
                case R.id.tv_edit:
                    // 编辑
                    listener.onEdit(getCurrentPosition());
                    break;
            }
        }

        @Override
        public void setDefault(int position) {
            mIvDefault.setImageResource(R.drawable.icon_address_default);
            mTvDefault.setText(context.getResources().getStringArray(R.array.delivery_address_default)[0]);
            mTvDefault.setTextColor(context.getResources().getColor(R.color.blue_text_color));
        }

        @Override
        public void cancelDefault(int position) {
            mIvDefault.setImageResource(R.drawable.icon_address_not_default);
            mTvDefault.setText(context.getResources().getStringArray(R.array.delivery_address_default)[1]);
            mTvDefault.setTextColor(context.getResources().getColor(R.color.white_text_alpha_60));
        }
    }
}