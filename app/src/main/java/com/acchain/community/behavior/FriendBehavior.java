package com.acchain.community.behavior;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
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
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acchain.community.R;
import com.blankj.utilcode.util.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 小任
 * @date 2017/12/25
 * version 1.0
 * 描述:
 */

public class FriendBehavior extends CoordinatorLayout.Behavior<LinearLayout> {
    private int defaultY;
    private int minY;
    private int minW;
    private int maxW;
    private int minH;
    private int maxH;
    private int scollHeight;
    private int searchMaxHeight;
    private int searchMaxWidth;
    private float textSize;
    private final Animation showAnimation;
    private final Animation hideAnimation;
    private final Animation showAnimationTv;
    private final Animation hideAnimationTv;
    private boolean isShow = true;
    private View childAt;
    private List<TextView> textViews;
    private int textViewHeight;
    private View sub;
    private Drawable defaultBackground;
    private View chatListView;


    public FriendBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        minY = 0;
        minW = ScreenUtils.getScreenWidth() / 2;
        maxW = ScreenUtils.getScreenWidth();
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true);
        minH = TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());

        showAnimation = AnimationUtils.loadAnimation(
                context, android.support.design.R.anim.abc_fade_in);
        showAnimation.setDuration(200);
        showAnimation.setInterpolator(new LinearOutSlowInInterpolator());
        hideAnimation = AnimationUtils.loadAnimation(
                context, android.support.design.R.anim.abc_fade_out);
        hideAnimation.setDuration(200);
        hideAnimation.setInterpolator(new FastOutLinearInInterpolator());
        hideAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                childAt.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        showAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                childAt.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        showAnimationTv = new AlphaAnimation(0, 1);
        showAnimationTv.setDuration(200);
        showAnimationTv.setInterpolator(new LinearOutSlowInInterpolator());
        showAnimationTv.setFillAfter(true);
        hideAnimationTv = new AlphaAnimation(1, 0);
        hideAnimationTv.setDuration(200);
        hideAnimationTv.setInterpolator(new FastOutLinearInInterpolator());
        hideAnimationTv.setFillAfter(true);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, LinearLayout child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, LinearLayout child, View dependency) {
        if (defaultY == 0) {
            defaultY = dependency.getHeight() - child.getHeight();
            child.setY(defaultY);
            maxH = child.getHeight();
            scollHeight = dependency.getHeight() - minH;
            childAt = child.getChildAt(0);
            searchMaxHeight = childAt.getHeight();
            searchMaxWidth = childAt.getWidth();
            textViews = new ArrayList<>();
            findTextView(child);
            textViewHeight = textViews.get(0).getHeight();
            sub = child.findViewById(R.id.sub_view);
            defaultBackground = sub.getBackground();
            chatListView = parent.findViewById(R.id.rc_list);
        }
        float v = (dependency.getHeight() - Math.abs(dependency.getY()) - minH) / scollHeight;
        ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
        layoutParams.width = (int) (maxW - ((maxW - minW) * (1 - v)));
        layoutParams.height = (int) (maxH - ((maxH - minH) * (1 - v)));
        child.setLayoutParams(layoutParams);
        float y = defaultY - defaultY * (1 - v);
        child.setY(y);
        if (y == 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                child.setZ(20);
            }
        }
        ViewGroup.LayoutParams layoutParams1 = childAt.getLayoutParams();
        layoutParams1.height = (int) (searchMaxHeight * v);
        childAt.setLayoutParams(layoutParams1);
        for (TextView textView : textViews) {
            ViewGroup.LayoutParams layoutParams2 = textView.getLayoutParams();
            layoutParams2.height = (int) (textViewHeight * v);
            textView.setLayoutParams(layoutParams2);
        }
        if (v < 1) {
            if (!isShow) {
                return true;
            }
            sub.setBackgroundColor(Color.TRANSPARENT);
            childAt.startAnimation(hideAnimation);
            for (TextView textView : textViews) {
                textView.startAnimation(hideAnimationTv);
            }
            isShow = false;
        } else {
            if (isShow) {
                return true;
            }
            sub.setBackground(defaultBackground);
            childAt.startAnimation(showAnimation);
            for (TextView textView : textViews) {
                textView.startAnimation(showAnimationTv);
            }
            isShow = true;
        }
        return true;
    }

    private void findTextView(ViewGroup view) {
        int childCount = view.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = view.getChildAt(i);
            if (childAt instanceof ViewGroup) {
                findTextView((ViewGroup) childAt);
            } else if (childAt instanceof TextView) {
                textViews.add((TextView) childAt);
            }
        }
    }

}
