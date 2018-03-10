package com.acchain.community.view;

import android.content.Context;
import android.support.v7.widget.SearchView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.acchain.community.R;

/**
 * Created on 2017/12/21
 * function : 只能点击的搜索框
 *
 * @author ACChain
 */

public class UnEnableSearchView extends SearchView {

    public UnEnableSearchView(Context context) {
        this(context, null);
    }

    public UnEnableSearchView(Context context, AttributeSet attrs) {
        this(context, attrs, android.support.v7.appcompat.R.attr.searchViewStyle);
    }

    public UnEnableSearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View searchEdit = findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEdit.setEnabled(false);
        searchEdit.setFocusable(false);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            //当手指抬起时,默认该控件触发click事件
            case MotionEvent.ACTION_UP: {
                performClick();
            }
        }
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //设置最大高度
        int customHeightSpec = MeasureSpec.makeMeasureSpec(getContext().getResources().getDimensionPixelSize(R.dimen.toolbar_search_view_max_height), MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, customHeightSpec);
    }
}
