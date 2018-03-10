package com.acchain.community.window.popupwindow;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.acchain.community.R;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

public class ClassificationPopup extends BasePopupWindow {
    @BindView(R.id.tag_layout)
    TagFlowLayout tagLayout;
    private final LayoutInflater mInflater;

    public ClassificationPopup(Context context) {
        super(context);
        mInflater=LayoutInflater.from(context);
        init();
    }
    private void init() {
        List<String> tags = new ArrayList<>();
        tags.add("商品分类1");
        tags.add("商品分类1");
        tags.add("商品分类1");
        tags.add("商品分类1");
        tags.add("商品分类1");
        tags.add("商品分类1");
        tags.add("商品分类1");
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        TagAdapter<String> tagAdapter = new TagAdapter<String>(tags) {
            @Override
            public View getView(FlowLayout parent, int position, String text) {
                TextView tv = (TextView) mInflater.inflate(R.layout.tag_classification_popup_item, tagLayout, false);
                tv.setText(text);
                return tv;
            }
        };
        tagLayout.setAdapter(tagAdapter);
        tagAdapter.setSelectedList(3);
        tagLayout.setOnSelectListener(selectPosSet -> Log.i("tablayout", selectPosSet.toString()));//拿到选择的所有项
        tagLayout.setOnTagClickListener((view, position, parent) -> {//点击某一项的监听
            if(mListener!=null){
                mListener.onItemClick(position);
            }
            dismiss();
            return true;
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.popup_classification;
    }
    /*监听*/
    private OnItemListener mListener;

    public void setOnItemListener(OnItemListener listener) {
        this.mListener = listener;
    }
    public interface OnItemListener {
        void onItemClick(int position);
    }
}
