package com.acchain.community.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.acchain.community.util.L;

/**
 * Created by Administrator on 2018/1/15.
 */

public class NumberProgressView extends View {

    /**
     * 进度条画笔的宽度（dp）
     */
    private int paintProgressWidth = 1;

    /**
     * 文字百分比的字体大小（sp）
     */
    private int paintTextSize = 30;

    /**
     * 左侧已完成进度条的颜色
     */
    private int paintLeftColor = 0xff4076fe;

    /**
     * 右侧未完成进度条的颜色
     */
    private int paintRightColor = 0x4dffffff;


    /**
     * 百分比文字的颜色
     */
    private int paintTextColor = 0xffffffff;

    /**
     * 圆角矩形的填充色
     */
    private int paintRoundColor = 0xff4076fe;

    /**
     * Contxt
     */
    private Context context;

    /**
     * 主线程传过来进程 0 - 100
     */
    private int progress = 0;

    /**
     * 得到自定义视图的宽度
     */
    private int viewWidth;

    /**
     * 得到自定义视图的Y轴中心点
     */
    private int viewCenterY;

    /**
     * 画左边已完成进度条的画笔
     */
    private Paint paintLeft = new Paint();

    /**
     * 画右边未完成进度条的画笔
     */
    private Paint paintRight = new Paint();

    /**
     * 画圆角矩形
     */
    private Paint paintRound = new Paint();

    /**
     * 画中间的百分比文字的画笔
     */
    private Paint paintText = new Paint();

    /**
     * 要画的文字的宽度
     */
    private int roundWidth;
    /**
     * 要画的文字的高度
     */
    private int roundHeight;

    /**
     * 要画的文字的宽度
     */
    private int textWidth;

    /**
     * 画文字时底部的坐标
     */
    private float textBottomY;

    /**
     * 包裹文字的矩形
     */
    private Rect rect = new Rect();

    /**
     * 文字总共移动的长度（即从0%到100%文字左侧移动的长度）
     */
    private int totalMovedLength;

    /*要画的文字*/
    private String text;


    public NumberProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        // 构造器中初始化数据
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {

        //设置进度条画笔的宽度
        int paintProgressWidthPx = paintProgressWidth;

        //设置百分比文字的尺寸
        int paintTextSizePx = paintTextSize;

        // 已完成进度条画笔的属性
        paintLeft.setColor(paintLeftColor);
        paintLeft.setStrokeWidth(paintProgressWidthPx);
        paintLeft.setAntiAlias(true);
        paintLeft.setStyle(Paint.Style.FILL);

        // 未完成进度条画笔的属性
        paintRight.setColor(paintRightColor);
        paintRight.setStrokeWidth(paintProgressWidthPx);
        paintRight.setAntiAlias(true);
        paintRight.setStyle(Paint.Style.FILL);

        //圆角矩形画笔的属性
        paintRound.setColor(paintRoundColor);
        paintRight.setAntiAlias(true);
        paintRound.setStyle(Paint.Style.FILL);


        // 百分比文字画笔的属性
        paintText.setColor(paintTextColor);
        paintText.setTextSize(paintTextSizePx);
        paintText.setAntiAlias(true);
        paintText.setTypeface(Typeface.DEFAULT_BOLD);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        getWidthAndHeight();
    }

    /**
     * 得到视图等的高度宽度尺寸数据
     */
    private void getWidthAndHeight() {

        //得到自定义视图的高度
        int viewHeight = getMeasuredHeight();
        viewWidth = getMeasuredWidth();
        viewCenterY = viewHeight / 2;
        totalMovedLength = viewWidth - roundWidth;
//        Log.i("NumberProgressView", "\ntextWidth=" + textWidth + "\ntextHeight=" + rect.height() + "\nroundWidth=" + roundWidth + "\nroundHeight=" + roundHeight + "\nviewWidth=" + viewWidth + "\nviewHeight=" + viewHeight);
        //得到包围文字的矩形的宽高
        text = progress + "%";
        paintText.getTextBounds(text, 0, text.length(), rect);
        textWidth = rect.width();
//        L.i("viewCenterY="+viewCenterY+"rectHeight="+rect.height());
        textBottomY = viewCenterY + rect.height() / 2;

        roundWidth = textWidth + 40;
        roundHeight = rect.height() + 20;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        Log.i("NumberProgressView", "\nprogress=" + progress + "\ntotalMovedLength=" + totalMovedLength);

        //得到float型进度
        float progressFloat = progress / 100.0f;

        //当前文字移动的长度
        float currentMovedLentgh = totalMovedLength * progressFloat;

        //画左侧已经完成的进度条，长度为从Veiw左端到文字的左侧
        canvas.drawLine(0, viewCenterY, currentMovedLentgh, viewCenterY, paintLeft);

        //画右侧未完成的进度条，这个进度条的长度不是严格按照百分比来缩放的，因为文字的长度会变化，所以它的长度缩放比例也会变化
        canvas.drawLine(currentMovedLentgh + roundWidth, viewCenterY, viewWidth, viewCenterY, paintRight);
//        if (progress < 10) {
//            canvas.drawLine(currentMovedLentgh + textWidth * 0.5f, viewCenterY, viewWidth, viewCenterY, paintRight);
//        } else if (progress < 100) {
//            canvas.drawLine(currentMovedLentgh + textWidth * 0.75f, viewCenterY, viewWidth, viewCenterY, paintRight);
//        } else {
//            canvas.drawLine(currentMovedLentgh + textWidth, viewCenterY, viewWidth, viewCenterY, paintRight);
//        }

        RectF rect = new RectF(currentMovedLentgh, viewCenterY - roundHeight / 2, roundWidth + currentMovedLentgh, viewCenterY + roundHeight / 2);
        // 画圆角矩形
        canvas.drawRoundRect(rect, 12, 12, paintRound);

        //画文字(注意：文字要最后画，因为文字和进度条可能会有重合部分，所以要最后画文字，用文字盖住重合的部分)
//        L.i("textBottomY="+textBottomY);
        canvas.drawText(text, currentMovedLentgh + (roundWidth - textWidth) / 2, textBottomY, paintText);
    }

    /**
     * @param progress 外部传进来的当前进度
     */
    public void setProgress(int progress) {
        this.progress = progress;
        L.i("progress="+progress);
        invalidate();
    }
}
