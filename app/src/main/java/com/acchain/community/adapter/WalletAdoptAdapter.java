package com.acchain.community.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.base.BaseRecyclerViewHolder;
import com.acchain.community.bean.WalletAdoptList;
import com.acchain.community.interfaces.AdoptKindOperate;
import com.acchain.community.view.RoundRectBorderTextView;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created on 2018/1/2
 * function : 钱包币种展示(领养类)
 *
 * @author ACChain
 */

public class WalletAdoptAdapter extends RecyclerView.Adapter<WalletAdoptAdapter.ViewHolder> {
    private List<WalletAdoptList> datas;
    private BaseRecyclerViewHolder.OnViewClickListener listener;
    private AdoptKindOperate adoptKindOperate;

    public WalletAdoptAdapter(List<WalletAdoptList> datas, BaseRecyclerViewHolder.OnViewClickListener listener, AdoptKindOperate adoptKindOperate) {
        this.datas = datas;
        this.listener = listener;
        this.adoptKindOperate = adoptKindOperate;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wallet_adopt, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // 绑定具体数据
        WalletAdoptList bean = datas.get(position);
        Glide.with(holder.context)
                .load(bean.getPRODUCT_IMG())
                .into(holder.mIvIcon);
        holder.mTvCurrencyName.setText(bean.getPROPERTY_CURRENCY());
        holder.mTvExercise.setVisibility(bean.isIS_EXERCISE()?View.VISIBLE:View.GONE);
        // TODO: 2018/2/26  添加单位信息
        holder.mTvAdoptPriceNum.setText(String.valueOf(bean.getADOPT_PRICE()));
//        holder.mTvAdoptPriceUnit.setText(bean.);
        holder.mTvAdoptAmountNum.setText(String.valueOf(bean.getADOPT_CODE_IDS()));
//        holder.mTvAdoptAmountUnit.setText(bean.);
        holder.mTvDividendNum.setText(String.valueOf(bean.getMAX_ADOPT_QTY()));
//        holder.mTvDividendUnit.setText(bean.);
        holder.mTvTime.setText(bean.getEXERCISE_END_TIME());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends BaseRecyclerViewHolder {
        @BindView(R.id.iv_icon)
        ImageView mIvIcon;
        @BindView(R.id.tv_currency_name)
        TextView mTvCurrencyName;
        @BindView(R.id.tv_adopt_price_num)
        TextView mTvAdoptPriceNum;
        @BindView(R.id.tv_adopt_price_unit)
        TextView mTvAdoptPriceUnit;
        @BindView(R.id.tv_adopt_amount_num)
        TextView mTvAdoptAmountNum;
        @BindView(R.id.tv_adopt_amount_unit)
        TextView mTvAdoptAmountUnit;
        @BindView(R.id.tv_dividend_num)
        TextView mTvDividendNum;
        @BindView(R.id.tv_dividend_unit)
        TextView mTvDividendUnit;
        @BindView(R.id.tv_time)
        TextView mTvTime;
        @BindView(R.id.tv_exercise)
        RoundRectBorderTextView mTvExercise;

        public ViewHolder(View itemView, OnViewClickListener listener) {
            super(itemView, listener);
        }

        @Override
        protected boolean isXRecyclerView() {
            return true;
        }

        @OnClick({R.id.tv_take_in, R.id.tv_take_out, R.id.tv_exercise, R.id.tv_record})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.tv_take_in:
                    adoptKindOperate.takeIn(getCurrentPosition());
                    break;
                case R.id.tv_take_out:
                    adoptKindOperate.takeOut(getCurrentPosition());
                    break;
                case R.id.tv_exercise:
                    adoptKindOperate.exercise(getCurrentPosition());
                    break;
                case R.id.tv_record:
                    adoptKindOperate.flowRecord(getCurrentPosition());
                    break;
            }
        }
    }
}
