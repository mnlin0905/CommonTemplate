package com.acchain.community.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * Created on 2018/1/9
 * function : 自布局无法获取点击事件,同时设定measure的规格
 *
 * @author ACChain
 */

public class DisableChildGridView extends GridView {
    public DisableChildGridView(Context context) {
        super(context);
    }

    public DisableChildGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DisableChildGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 控制子布局无法获取事件处理权限
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 控制子布局无法获取事件处理权限
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //修改高度上测量规格,保证所有的item都能显示
        int spec = MeasureSpec.makeMeasureSpec(0x3FFFFFFF, MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, spec);
    }
}
