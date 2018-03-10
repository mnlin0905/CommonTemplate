package com.acchain.community.view;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created on 2018/1/5
 * function : 文字带光晕的textView,不允许设置背景
 *
 * @author ACChain
 */

public class LightTextView extends android.support.v7.widget.AppCompatTextView {
    private BlurMaskFilter blurMaskFilter;

    {
        //初始化数据
        blurMaskFilter = new BlurMaskFilter(10, BlurMaskFilter.Blur.SOLID);
        //setTextColor(getResources().getColor(R.color.white_selected));
        getPaint().setMaskFilter(blurMaskFilter);
    }

    public LightTextView(Context context) {
        super(context);
    }

    public LightTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LightTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getPaint().setMaskFilter(null);
    }
}
