package com.acchain.community.behavior;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.acchain.community.R;

/**
 * @author 小任
 * @date 2018/1/4
 * version 1.0
 * 描述:
 */

public class HeaderImageBehavior extends CoordinatorLayout.Behavior<ImageView> {
    private boolean isInit = false;
    private int finalHeight; //头像的最小高度
    private int maxHeight;  //头像的最大高度
    private int maxWidth;
    private int scaleHeight;  //缩放高度的范围
    private int defaultY;    //头像的默认Y值
    private int minY;        //头像的最小Y值
    private int toolBarHeight;
    private int imgScollY;   //头像y值滚动范围
    private int appBarScollY;
    private View contentView;//昵称 和各种状态的view的父控件， 用来控制显示和隐藏
    private View dividerView;

    public HeaderImageBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.HeaderImageBehavior);
        finalHeight = a.getDimensionPixelSize(R.styleable.HeaderImageBehavior_finalHeight, 50);
        a.recycle();
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true);
        toolBarHeight = TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
        minY = (toolBarHeight / 2) - (finalHeight / 2);
        imgScollY = defaultY - minY;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, ImageView child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, ImageView child, View dependency) {
        if (!isInit) {
            maxHeight = child.getHeight();
            maxWidth = child.getWidth();
            scaleHeight = maxHeight - finalHeight;
            defaultY = (int) (dependency.getY() + dependency.getHeight() - child.getHeight());
            child.setY(defaultY);
            appBarScollY = dependency.getHeight() - toolBarHeight;
            ViewGroup coordinatorLayout = (ViewGroup) child.getParent();
            contentView = coordinatorLayout.findViewById(R.id.content);
            dividerView = coordinatorLayout.findViewById(R.id.dividerView);
            imgScollY = defaultY - minY;
            isInit = true;
        }
        float v = (dependency.getHeight() - Math.abs(dependency.getY()) - toolBarHeight) / appBarScollY;
        contentView.setAlpha(v);
        if (v == 0) {
            dividerView.setVisibility(View.VISIBLE);
        } else if (((int)v)==1) {
            dividerView.setVisibility(View.GONE);
        }
        child.setY(defaultY - (imgScollY * (1 - v)));
        ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
        layoutParams.height = (int) (maxHeight - (scaleHeight * (1-v)));
        layoutParams.width = (int) (maxWidth - (scaleHeight * (1-v)));
        child.setLayoutParams(layoutParams);
        return true;
    }
}
