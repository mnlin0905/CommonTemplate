package com.acchain.community.window.dialog;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.acchain.community.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 底部弹出的dialog,带取消
 */
public class ChooseAddressDialog extends DialogViewHolder {

    @BindView(R.id.tv_close)
    TextView tvClose;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    private SelectListAdapter mAdapter;
    private String[] itemTexts;
    private OnCommitAddressListener onCommitAddressListener;

    public ChooseAddressDialog(Context context, String[] itemTexts, OnCommitAddressListener onCommitAddressListener) {
        super(context);
        this.itemTexts = itemTexts;
        this.onCommitAddressListener = onCommitAddressListener;
        initView(context);
    }

    public void setData(String[] itemTexts, int choosePosition) {
        if (mAdapter != null) {
            mAdapter.setData(itemTexts, choosePosition);
        }
    }

    private void initView(Context context) {
        this.setCanceledOnTouchOutside(true);//点击外面是否取消
        mAdapter = new SelectListAdapter(context, itemTexts);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener((parent, view, position, id) -> mAdapter.updateView(position));
        //最多7行，超过滚动
//        int height = ScreenUtils.dip2px((mData.length * 44));
//        if (height > ScreenUtils.dip2px(200)) {
//            height = ScreenUtils.dip2px(200);
//        }
        int height = 300;
        if (null != itemTexts && itemTexts.length > 4) {
            ViewGroup.LayoutParams lp = listView.getLayoutParams();
            lp.height = height;
            listView.setLayoutParams(lp);
            mAdapter.notifyDataSetChanged();
        }
    }

    @OnClick({R.id.tv_close, R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_close:
                dismiss();
                break;
            case R.id.tv_commit:
                dismiss();
                if (onCommitAddressListener != null) {
                    onCommitAddressListener.onCommit(mAdapter.getChoosePosition());
                }
                break;
        }
    }

    public interface OnCommitAddressListener {
        void onCommit(int position);
    }

    @Override
    protected int getStyle() {
        return super.getStyle();
    }

    @Override
    protected int getWindowAnimations() {
        return R.style.dialog_bottom_to_up;
    }

    @Override
    protected int getWidth() {
        return WindowManager.LayoutParams.MATCH_PARENT;
    }

    @Override
    protected int getHeight() {
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_choose_address;
    }

    @Override
    protected int getGravity() {
        return Gravity.BOTTOM;
    }


    private class SelectListAdapter extends BaseAdapter {
        private Context mContext;
        private String[] itemTexts;
        private int choosePosition = 0;

        public SelectListAdapter(Context context, String[] itemTexts) {
            this.mContext = context;
            this.itemTexts = itemTexts;
        }

        public void setData(String[] itemTexts, int choosePosition) {
            this.itemTexts = itemTexts;
            this.choosePosition = choosePosition;
            notifyDataSetChanged();
        }

        public void updateView(int choosePosition) {
            if (this.choosePosition != choosePosition) {
                this.choosePosition = choosePosition;
                notifyDataSetChanged();
            }
        }

        public int getChoosePosition() {
            return choosePosition;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHold viewHold = null;
//            String itemValue = itemTexts[position];
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.dialog_choose_address_item, parent, false);
                viewHold = new ViewHold();
                viewHold.tv_item = convertView.findViewById(R.id.address);

                convertView.setTag(viewHold);
            } else {
                viewHold = (ViewHold) convertView.getTag();
            }

            if (position == choosePosition) {
                viewHold.tv_item.setTextColor(Color.parseColor("#4076fe"));
                viewHold.tv_item.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.icon_addiess_selected, 0, R.drawable.icon_choose_address, 0);
            } else {
                viewHold.tv_item.setTextColor(Color.parseColor("#333333"));
                viewHold.tv_item.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.icon_addiess_normal, 0, 0, 0);
            }
            return convertView;
        }

        @Override
        public int getCount() {
            if (itemTexts == null) {
                return 3;
            }
            return itemTexts.length;
        }

        @Override
        public Object getItem(int position) {
            if (itemTexts != null && position < itemTexts.length) {
                return itemTexts[position];
            } else {
                return null;
            }
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        class ViewHold {
            TextView tv_item;
        }
    }


}
