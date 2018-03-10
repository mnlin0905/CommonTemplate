package com.acchain.community.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.acchain.community.R;

/**
 * @author 小任
 * @date 2018/1/4
 * version 1.0
 * 描述:
 */

public class DividerView extends android.support.v7.widget.AppCompatImageView {
    ColorDrawable colorDrawable;

    {
        colorDrawable = new ColorDrawable(getResources().getColor(R.color.white_color_divider_1px_15));
    }

    public DividerView(Context context) {
        this(context, null);
    }

    public DividerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DividerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if(measuredHeight==1||measuredWidth==1){
            colorDrawable.setColor(getResources().getColor(R.color.white_color_divider_1px_15));
        }else{
            colorDrawable.setColor(getResources().getColor(R.color.white_color_divider_6dp_5));
        }
        colorDrawable.setBounds(getPaddingLeft(), 0, getMeasuredWidth() - getPaddingRight(), getMeasuredHeight());
        setImageDrawable(colorDrawable);
    }
}
