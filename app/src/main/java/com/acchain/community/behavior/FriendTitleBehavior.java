package com.acchain.community.behavior;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.acchain.community.R;

/**
 * @author 小任
 * @date 2017/12/25
 * version 1.0
 * 描述:
 */

public class FriendTitleBehavior extends CoordinatorLayout.Behavior<TextView> {
    private int scollHeight;
    private int minH;

    public FriendTitleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true);
        minH = TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, TextView child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, TextView child, View dependency) {
        if (scollHeight == 0) {
            scollHeight = dependency.getHeight() - minH;
        }
        float v = (dependency.getHeight() - Math.abs(dependency.getY()) - minH) / scollHeight;
        child.setAlpha(v);
        return true;
    }
}
