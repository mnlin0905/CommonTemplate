package com.acchain.community.behavior;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created on 2018/1/5
 * function : 控制tablayout从之前的宽度,在吸顶时达到最大宽度,即去除margin_horizontal值
 *
 * @author ACChain
 */

public class ExpandTabLayoutBehavior extends CoordinatorLayout.Behavior<TextView> {

    /**
     * 用于初始化数据
     */
    private boolean beginAnimation;

    /**
     * 默认的水平方向margin值,当前margin占总margin的百分比
     */
    private int leftMargin;
    private int rightMargin;
    private float currentPercent;

    /**
     * AppBarLayout
     * 初始信息和最终位置
     */
    private int initialY;
    private int finallyY;

    public ExpandTabLayoutBehavior() {
        super();
    }

    public ExpandTabLayoutBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 监视NestedScrollView布局的变化,底部内容移动则自身margin值进行更改
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, TextView child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, TextView child, View dependency) {
        if (!beginAnimation) {
            beginAnimation = true;

            //记录开始时候的margin值
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
            leftMargin = layoutParams.leftMargin;
            rightMargin = layoutParams.rightMargin;
            currentPercent = 1;

            //记录NestedScrollView初始位置
            initialY = dependency.getBottom();
            finallyY = dependency.getTop() + child.getHeight();

            //初始化时只为获取信息
            return true;
        }

        //获取AppBarLayout在父布局中的坐标信息
        int currentY = dependency.getBottom();
        currentPercent = (float) (currentY - finallyY) / (initialY - finallyY);

        //child修改自身参数
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
        layoutParams.leftMargin = (int) (currentPercent * leftMargin);
        layoutParams.rightMargin = (int) (rightMargin * currentPercent);

        child.requestLayout();

        return true;
    }
}
