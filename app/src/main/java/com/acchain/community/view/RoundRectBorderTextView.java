package com.acchain.community.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.acchain.community.R;

/**
 * Created on 2018/1/5
 * function : 圆角矩形,左右为圆形,内容背景透明,边框绿色,
 * <p>
 * 默认该控件不能设置background
 *
 * @author ACChain
 */

public class RoundRectBorderTextView extends android.support.v7.widget.AppCompatTextView {

    //设置字体默认的效果
    {
        //字体大小默认11sp
        setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen.text_size_smallest_11sp));

        //上下边框边距默认为4dp,左右默认间距为8dp
        int dp_horizontal = getResources().getDimensionPixelSize(R.dimen.view_padding_margin_8dp);
        int dp_vertical = getResources().getDimensionPixelSize(R.dimen.view_padding_margin_4dp);
        setPadding(dp_horizontal,dp_vertical,dp_horizontal,dp_vertical);
    }

    public RoundRectBorderTextView(Context context) {
        super(context);
    }

    public RoundRectBorderTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundRectBorderTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawOutlineUseShape(canvas);
    }



    /**
     * 使用Drawable形式画线
     */
    private void drawOutlineUseDrawable(){
        int height = getHeight() / 2;
        float[] outRect=new float[]{height, height, height, height, height, height, height, height};
        float[] innerRect=new float[]{height, height, height, height, height, height, height, height};
        RectF insert=new RectF(getLeft()-1,getTop()-1,getRight()-1,getBottom()-1);
        @SuppressLint("DrawAllocation")
        RoundRectShape shape = new RoundRectShape(outRect,null,null);
        ShapeDrawable shapeDrawable=new ShapeDrawable(shape);
        shapeDrawable.getPaint().setColor(getResources().getColor(R.color.blue_background_transparent_border));
        shapeDrawable.getPaint().setAntiAlias(true);
        shapeDrawable.getPaint().setStyle(Paint.Style.STROKE);//描边
        shapeDrawable.getPaint().setStrokeWidth(1);
        shapeDrawable.setIntrinsicWidth(getWidth());
        shapeDrawable.setIntrinsicHeight(getHeight());

        setBackground(shapeDrawable);
    }

    /**
     * 使用shape形式画线
     */
    private void drawOutlineUseShape(Canvas canvas){
        // 在所有内容绘制之后,绘制圆角边框
        int height = getHeight() / 2;

        //生成圆角矩形
        float[] outRect=new float[]{height, height, height, height, height, height, height, height};
        @SuppressLint("DrawAllocation")
        RoundRectShape shape = new RoundRectShape(outRect,null,null);
        shape.resize(getWidth(),getHeight());

        //更换画笔颜色,粗细
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(getResources().getColor(R.color.blue_background_transparent_border));
        paint.setStrokeWidth(getResources().getDimensionPixelSize(R.dimen.background_border_width_1px));

        //将shape画出
        shape.draw(canvas, paint);
    }
}
