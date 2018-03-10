package com.common.template.drawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextPaint;

/**
 * Created on 2018/1/17
 * function :
 *
 * @author MNLIN
 */

public class ColorTextDrawable extends Drawable {
    private static final String TAG = "TextDrawable";

    private Context context;
    private TextPaint textPaint;
    private CharSequence charSequence;
    private int textSize;
    private Rect rect;
    private int alpha;
    private int color;
    private int xOffset;
    private int yOffset;

    public ColorTextDrawable(Context context) {
        this.context = context;
        textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.LEFT);
        charSequence = "";
    }

    public ColorTextDrawable(Context context,int xOffset,int yOffset) {
        this(context);
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    @Override
    public void draw(Canvas canvas) {
        textPaint.setTextSize(textSize);
        canvas.drawText(charSequence, 0, charSequence.length(), xOffset, yOffset-textPaint.getFontMetrics().top, textPaint);
    }

    @Override
    public void setBounds(Rect bounds) {
        super.setBounds(bounds);
        this.rect = bounds;
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        this.rect = new Rect(left, top, right, bottom);
    }

    @Override
    public void setAlpha(int alpha) {
        this.alpha = alpha;
        textPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        textPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public int getIntrinsicWidth() {
        return (int) (textPaint.getTextSize() * charSequence.length());
    }

    @Override
    public int getIntrinsicHeight() {
        return (int) (textPaint.getFontMetrics().bottom - textPaint.getFontMetrics().top);
    }

    @Override
    public int getMinimumWidth() {
        return getIntrinsicWidth();
    }

    @Override
    public int getMinimumHeight() {
        return getIntrinsicHeight();
    }

    public ColorTextDrawable setTextSize(int textSize) {
        this.textSize = textSize;
        textPaint.setTextSize(textSize);
        return this;
    }

    public CharSequence getText() {
        return this.charSequence;
    }

    public ColorTextDrawable setText(CharSequence charSequence) {
        this.charSequence = charSequence;
        return this;
    }

    public ColorTextDrawable setColor(int color) {
        this.color = color;
        textPaint.setColor(color);
        return this;
    }

    public int getxOffset() {
        return xOffset;
    }

    public void setxOffset(int xOffset) {
        this.xOffset = xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }

    public void setyOffset(int yOffset) {
        this.yOffset = yOffset;
    }
}
