package com.acchain.community.window.popupwindow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.util.ScreenUtils;

import butterknife.BindView;

public class ListViewPopup extends BasePopupWindow {
    @BindView(R.id.list_view)
    ListView listView;
    private Context context;
    private ListPopupAdapter adapter;
    private String[] itemStr;
    private int[] itemImage;

    public ListViewPopup(Context context, String[] itemStr, int[] itemImage) {
        super(context);
        this.context = context;
        this.itemStr = itemStr;
        this.itemImage = itemImage;
        init();
    }

    private void init() {
        setHeight(ScreenUtils.dip2px(itemStr.length * 40 + 24));
        adapter = new ListPopupAdapter(context, itemStr, itemImage);
        listView.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.popup_listview;
    }

    /*监听*/
    private OnItemListener mListener;

    public void setOnItemListener(OnItemListener listener) {
        this.mListener = listener;
    }

    public interface OnItemListener {
        void onItemClick(int position);
    }

    private class ListPopupAdapter extends BaseAdapter {
        private Context mContext;
        private String[] itemStr;
        private int[] itemImage;

        public ListPopupAdapter(Context context, String[] itemStr, int[] itemImage) {
            this.mContext = context;
            this.itemStr = itemStr;
            this.itemImage = itemImage;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHold viewHold = null;
            String text = itemStr[position];
            int imageId = itemImage[position];
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.popup_item, parent, false);
                viewHold = new ViewHold();
                viewHold.item = convertView.findViewById(R.id.item);
                viewHold.text = convertView.findViewById(R.id.text);
                viewHold.image = convertView.findViewById(R.id.image);
                convertView.setTag(viewHold);
            } else {
                viewHold = (ViewHold) convertView.getTag();
            }
            viewHold.text.setText(text);
            viewHold.image.setImageResource(imageId);
            viewHold.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onItemClick(position);
                    }
                    dismiss();
                }
            });
            return convertView;
        }

        @Override
        public int getCount() {
            if (itemImage == null) {
                return 0;
            }
            return itemImage.length;
        }

        @Override
        public Object getItem(int position) {
            if (itemStr != null && position < itemStr.length) {
                return itemStr.length;
            } else {
                return null;
            }
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        class ViewHold {
            TextView text;
            ImageView image;
            View item;

        }
    }

}
