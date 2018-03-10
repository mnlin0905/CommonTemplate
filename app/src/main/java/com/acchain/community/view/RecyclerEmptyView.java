package com.acchain.community.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.acchain.community.R;

/**
 * 功能----空布局;适配RecycleView空列表
 * <p>
 * Created by ACChain on 2017/9/18.
 */

public class RecyclerEmptyView extends LinearLayout {
    public RecyclerEmptyView(Context context) {
        super(context, null);
    }

    public RecyclerEmptyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecyclerEmptyView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public RecyclerEmptyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.layout_empty_view, this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(params);
        setGravity(Gravity.CENTER);
        setOrientation(VERTICAL);
    }

}
