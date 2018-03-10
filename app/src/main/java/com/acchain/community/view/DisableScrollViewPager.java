package com.acchain.community.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created on 2018/1/3
 * function : 不可以左右滑动的viewpager;同时关闭预加载
 *
 * @author ACChain
 */

public class DisableScrollViewPager extends ViewPager {

    private ManageScrollInterface manageScrollInterface;

    public DisableScrollViewPager(Context context) {
        super(context);
    }

    public DisableScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (manageScrollInterface != null && manageScrollInterface.currentCanScroll()) {
            return super.onInterceptTouchEvent(ev);
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (manageScrollInterface != null && manageScrollInterface.currentCanScroll()) {
            return super.onTouchEvent(ev);
        }
        return true;
    }

    /**
     * @param manageScrollInterface 设置管理类
     */
    public void setManageScrollInterface(ManageScrollInterface manageScrollInterface) {
        this.manageScrollInterface = manageScrollInterface;
    }

    /**
     * 控制滑动的接口
     */
    public interface ManageScrollInterface {
        /**
         * @return true表示当前状态可以触发滑动
         */
        boolean currentCanScroll();
    }
}
