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

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created on 2018/1/10
 * function : 转入资产列表中子想gridview适配器
 *
 * @author ACChain
 */

public class TakeInCurrencyItemAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<CurrencyBean> datas;

    public TakeInCurrencyItemAdapter(Context context, ArrayList<CurrencyBean> datas) {
        this.context = context;
        this.datas = datas == null ? new ArrayList<>() : datas;
    }

    @Override
    public int getCount() {
        return  datas.size();
    }

    @Override
    public Object getItem(int position) {
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
            convertView = new TakeInCurrencyItemAdapter.ViewHolder(context).getRootView();
        }
        TakeInCurrencyItemAdapter.ViewHolder holder = (TakeInCurrencyItemAdapter.ViewHolder) convertView.getTag();

        // TODO: 2018/1/10  todo刷新其中的内容

        return convertView;
    }

    class ViewHolder extends BaseAdapterViewHolder {

        @BindView(R.id.iv_icon)
        ImageView mIvIcon;
        @BindView(R.id.tv_currency_name)
        TextView mTvCurrencyName;
        @BindView(R.id.tv_currency_abbreviation)
        TextView mTvCurrencyAbbreviation;

        public ViewHolder(Context context) {
            super(context, R.layout.item_item_take_in_out_currency);
        }
    }
}


