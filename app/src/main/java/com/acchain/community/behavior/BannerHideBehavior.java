package com.acchain.community.behavior;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.acchain.community.R;

/**
 * @author 小任
 * @date 2018/1/4
 * version 1.0
 * 描述:
 */

public class BannerHideBehavior extends CoordinatorLayout.Behavior<View> {
    private boolean isInit = false;
    private int toolBarHeight;
    private int appBarScollY;
    private View banner;
    private  Animation showAnimation;
    private  Animation hideAnimation;
    private boolean isShow = true;

    public BannerHideBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true);
        toolBarHeight = TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
        showAnimation = new AlphaAnimation(0, 1);
        showAnimation.setDuration(200);
        showAnimation.setInterpolator(new LinearOutSlowInInterpolator());
        showAnimation.setFillAfter(true);
        hideAnimation = new AlphaAnimation(1, 0);
        hideAnimation.setDuration(200);
        hideAnimation.setInterpolator(new FastOutLinearInInterpolator());
        hideAnimation.setFillAfter(true);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        if (!isInit) {
            appBarScollY = dependency.getHeight() - toolBarHeight;
            ViewGroup viewGroup = (ViewGroup) child.getParent();
            banner = viewGroup.findViewById(R.id.banner);
            isInit = true;
        }
        float v = (dependency.getHeight() - Math.abs(dependency.getY()) - toolBarHeight) / appBarScollY;
        if (v < 0.5) {
            if (isShow) {
                banner.startAnimation(hideAnimation);
                isShow = false;
            }
        } else {
            if (!isShow) {
                banner.startAnimation(showAnimation);
                isShow = true;
            }
        }
        return true;
    }
}
