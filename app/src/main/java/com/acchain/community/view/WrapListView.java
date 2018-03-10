package com.acchain.community.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

/**
 * 功能----可以自适应布局的listview
 * <p>
 * Created by ACChain on 2017/11/9.
 */

public class WrapListView extends ListView {
    public WrapListView(Context context) {
        super(context);
    }

    public WrapListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureChildWidth() + getPaddingLeft() + getPaddingRight(), getMeasuredHeight());
    }

    /**
     * 测量子布局的宽度
     */
    public int measureChildWidth() {
        int maxWidth = 0;
        View view = null;
        for (int i = 0; i < getAdapter().getCount(); i++) {
            view = getAdapter().getView(i, view, this);
            view.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
            if (view.getMeasuredWidth() > maxWidth) {
                maxWidth = view.getMeasuredWidth();
            }
            view = null;
        }
        return maxWidth;
    }

}
