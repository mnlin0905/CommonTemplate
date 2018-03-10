package com.acchain.community.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.RotateDrawable;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.acchain.community.R;
import com.orhanobut.logger.Logger;

import java.lang.reflect.Field;


/**
 * Created on 2017/12/22
 * function : 边框可选中的imageview
 * <p>
 * 为此控制设置width和height时,需同时为固定dp;或同时为wrap_content
 *
 * @author ACChain
 */

public class RefinementImageView extends FrameLayout {

    private final ImageView iv_flower;
    private final ImageView iv_first;
    private final Handler handler;
    private final RotateDrawable drawable;
    private Runnable runnable;

    public RefinementImageView(@NonNull Context context) {
        this(context, null);
    }

    public RefinementImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefinementImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.layout_refinement_image_view, this);

        iv_first = findViewById(R.id.iv_first);
        iv_flower = findViewById(R.id.iv_flower);

        handler = new Handler(getContext().getMainLooper());

        drawable = (RotateDrawable) iv_flower.getDrawable();
    }


    /**
     * 设置布局大小为真实图片的大小
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //取图片和自身高度中较小的;
        setMeasuredDimension(Math.min(Math.min(iv_first.getMeasuredWidth(), getMeasuredWidth()), getDrawableSize(iv_first, "mDrawableWidth")), Math.min(Math.min(iv_first.getMeasuredHeight(), getMeasuredHeight()), getDrawableSize(iv_first, "mDrawableHeight")));
    }

    /**
     * 获取隐藏的属性值
     */
    private int getDrawableSize(ImageView iv, String param) {
        try {
            Class clazz = ImageView.class;
            Field field = clazz.getDeclaredField(param);
            field.setAccessible(true);
            return field.getInt(iv);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        //修改中心位置
        int x_flower = (iv_flower.getLeft() + iv_flower.getRight()) / 2;
        int y_flower = (iv_flower.getTop() + iv_flower.getBottom()) / 2;
        int x_first = (iv_first.getLeft() + iv_first.getRight()) / 2;
        int y_first = (iv_first.getTop() + iv_first.getBottom()) / 2;

        iv_flower.setScrollX(x_flower - x_first);
        iv_flower.setScrollY(y_flower - y_first);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        handler.postDelayed(() -> startAnimate(), 500);
    }

    private void startAnimate() {
        if (runnable != null) handler.removeCallbacks(runnable);
        boolean is = drawable.setLevel((drawable.getLevel() + 100) % 10000);
        runnable = () -> {
            startAnimate();
            Logger.v("start animator");
        };
        handler.postDelayed(runnable, 500);
    }
}
