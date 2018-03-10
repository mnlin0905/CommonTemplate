package com.acchain.community.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.base.BaseAdapterViewHolder;
import com.acchain.community.bean.CurrencyBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created on 2018/1/12
 * function : 转出资产界面,资产列表适配器
 *
 * @author ACChain
 */
// TODO: 2018/1/12 需要修改的类 ,添加选中和未选中的标志
public class TakeOutCurrencyAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<CurrencyBean> datas;

    public TakeOutCurrencyAdapter(Context context, ArrayList<CurrencyBean> datas) {
        this.context = context;
        this.datas = datas == null ? new ArrayList<>() : datas;
    }

    @Override
    public int getCount() {
        return  datas.size();
    }

    @Override
    public CurrencyBean getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO: 2018/1/11
        //CurrencyBean bean = datas.get(position);
        if (convertView == null) {
            convertView = new TakeOutCurrencyAdapter.ViewHolder(context).getRootView();
        }
        TakeOutCurrencyAdapter.ViewHolder holder = (TakeOutCurrencyAdapter.ViewHolder) convertView.getTag();

        // TODO: 2018/1/10  todo刷新其中的内容
        // TODO: 2018/1/13 如果判断当前为选中状态,则将右上角图标显示出来
        if (true) {
            holder.mIvStatus.setVisibility(View.VISIBLE);
        }
        CurrencyBean item = getItem(position);
        holder.mTvCurrencyName.setText(item.getCurrencyShortNameZh());
        holder.mTvCurrencyAbbreviation.setText(item.getCurrencyShortName());
        Glide.with(convertView).load(item.getCurrencyImg()).apply(new RequestOptions().centerCrop()).into(holder.mIvIcon);
        return convertView;
    }

    class ViewHolder extends BaseAdapterViewHolder {

        @BindView(R.id.iv_icon)
        ImageView mIvIcon;
        @BindView(R.id.tv_currency_name)
        TextView mTvCurrencyName;
        @BindView(R.id.tv_currency_abbreviation)
        TextView mTvCurrencyAbbreviation;
        @BindView(R.id.iv_navigation)
        ImageView mIvNavigation;
        @BindView(R.id.iv_status)
        ImageView mIvStatus;

        public ViewHolder(Context context) {
            super(context, R.layout.item_item_take_in_out_currency);

            //去除右侧箭头
            mIvNavigation.setVisibility(View.INVISIBLE);
        }
    }
}