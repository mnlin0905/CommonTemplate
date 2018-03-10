package com.acchain.community.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.window.dialog.CommonGoodsAttrsBean;
import com.acchain.community.view.flowLayout.FlowLayout;
import com.acchain.community.view.flowLayout.TagAdapter;
import com.acchain.community.view.flowLayout.TagFlowLayout;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

public class DialogChooseGoodsAdapter extends RecyclerView.Adapter<DialogChooseGoodsAdapter.ViewHolder> {
    private Context context;
    private List<CommonGoodsAttrsBean.AttrListBean> attrList;
    private int[] valueIds;
    private LayoutInflater mInflater;

    public DialogChooseGoodsAdapter(Context context) {
        this(context,null,null);
    }

    public DialogChooseGoodsAdapter(Context context, List<CommonGoodsAttrsBean.AttrListBean> attrList, int[] valueIds) {
        this.context = context;
        this.attrList = attrList;
        mInflater = LayoutInflater.from(context);
        this.valueIds = valueIds;
    }

    public void setData(List<CommonGoodsAttrsBean.AttrListBean> attrList, int[] valueIds) {
        this.attrList = attrList;
        this.valueIds = valueIds;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dialog_choose_goods, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        CommonGoodsAttrsBean.AttrListBean attrListBean = attrList.get(position);
        holder.tvName.setText(attrListBean.getAttrName());
        List<CommonGoodsAttrsBean.AttrListBean.AttrValueListBean> attrValueList = attrListBean.getAttrValueList();
        TagAdapter<CommonGoodsAttrsBean.AttrListBean.AttrValueListBean> tagAdapter = new TagAdapter<CommonGoodsAttrsBean.AttrListBean.AttrValueListBean>(attrValueList) {
            @Override
            public View getView(FlowLayout parent, int position, CommonGoodsAttrsBean.AttrListBean.AttrValueListBean attrValueListBean) {
                TextView tv = (TextView) mInflater.inflate(R.layout.tag_choose_goods_dialog_item, holder.tagLayout, false);
                tv.setText(attrValueListBean.getAttrValue());
                return tv;
            }
        };
        holder.tagLayout.setAdapter(tagAdapter);
        holder.tagLayout.setMaxSelectCount(1);
        tagAdapter.setSelectedList(valueIds[position]);
        holder.tagLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int pos, FlowLayout parent) {
                if (mListener != null) {
                    mListener.onItemClick(position, pos);
                }
                return false;
            }
        });
    }

    public interface OnItemClickListener {
        void onItemClick(int attrPosition, int attrValuePosition);
    }

    private DialogChooseGoodsAdapter.OnItemClickListener mListener;

    public void setOnItemClickListener(DialogChooseGoodsAdapter.OnItemClickListener listener) {
        mListener = listener;
    }


    @Override
    public int getItemCount() {
        return attrList != null ? attrList.size() : 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TagFlowLayout tagLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tagLayout = itemView.findViewById(R.id.tag_layout);
        }
    }
}

