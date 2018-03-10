package com.acchain.community.behavior;

import android.content.Context;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.acchain.community.R;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.SizeUtils;

/**
 * @author 小任
 * @date 2017/12/25
 * version 1.0
 * 描述:
 */

public class FriendSearchShowBehavior extends CoordinatorLayout.Behavior<ImageView> {
    private int scollHeight;
    private int minH;
    public FriendSearchShowBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true);
        minH = TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, ImageView child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, ImageView child, View dependency) {
        if (scollHeight == 0) {
            scollHeight = dependency.getHeight() - minH;
            child.setX(ScreenUtils.getScreenWidth() / 2+ SizeUtils.dp2px(15));
            child.setY((minH-child.getHeight())/2);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                child.setZ(21);
            }
        }
        float v = (dependency.getHeight() - Math.abs(dependency.getY()) - minH) / scollHeight;
        child.setAlpha(1-v);
        return true;
    }
}
