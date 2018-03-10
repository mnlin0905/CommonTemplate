package com.acchain.community.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.base.BaseAdapterViewHolder;
import com.acchain.community.bean.WalletFunctionBean;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created on 2018/1/2
 * function : 钱包模块主功能入口
 *
 * @author ACChain
 */

public class WalletFunctionAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<WalletFunctionBean> datas;

    public WalletFunctionAdapter(Context context, ArrayList<WalletFunctionBean> datas) {
        this.context = context;
        this.datas = datas == null ? new ArrayList<>() : datas;
    }

    @Override
    public int getCount() {
        return datas.size();
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
        WalletFunctionBean bean = datas.get(position);
        if (convertView == null) {
            convertView = new WalletFunctionAdapter.ViewHolder(context).getRootView();
        }

        WalletFunctionAdapter.ViewHolder tag = (WalletFunctionAdapter.ViewHolder) convertView.getTag();
        tag.mIvIcon.setImageResource(bean.getResOrImageId());
        tag.mTvTitle.setText(bean.getTitle());
        tag.mTvSubtitle.setText(bean.getSubTitle());
        return convertView;
    }

    class ViewHolder extends BaseAdapterViewHolder {
        @BindView(R.id.iv_icon)
        ImageView mIvIcon;
        @BindView(R.id.tv_title)
        TextView mTvTitle;
        @BindView(R.id.tv_subtitle)
        TextView mTvSubtitle;

        public ViewHolder(Context context) {
            super(context, R.layout.item_wallet_function);
        }
    }
}
