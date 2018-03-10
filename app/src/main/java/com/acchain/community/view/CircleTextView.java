package com.acchain.community.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewOutlineProvider;

import com.acchain.community.R;


/**
 * Created by admin on 2017/4/18.
 */

public class CircleTextView extends android.support.v7.widget.AppCompatTextView {
    private static final String TAG = "CircleTextView";

    private Context context;
    private AttributeSet attrs;
    private int size;
    private boolean isFirstDraw = true;

    public CircleTextView(Context context) {
        this(context, null);
    }

    public CircleTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        this.attrs = attrs;
        init();
    }

    private void init() {
        setGravity(Gravity.CENTER);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        size = Math.max(getMeasuredWidth(), getMeasuredHeight());
        int temp =getResources().getDimensionPixelSize(R.dimen.prefer_view_height_48dp);
        if (size < temp) {
            size = temp;
        }
        setMeasuredDimension(size, size);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (Build.VERSION.SDK_INT >= 21) {
            setClipToOutline(true);
            setOutlineProvider(new ViewOutlineProvider() {
                @Override
                public void getOutline(View view, Outline outline) {
                    outline.setOval(0, 0, size, size);
                }
            });
        } else if (Build.VERSION.SDK_INT >= 19) {
            setBackground(getResources().getDrawable(R.drawable.shape_cirle_little_gray_backgroud, null));
        } else {
            setBackground(getResources().getDrawable(R.drawable.shape_cirle_little_gray_backgroud));
        }
    }
}
