package com.acchain.community.util;

import android.support.annotation.Keep;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created on 2018/1/23
 * function : view代理类,用于处理高度等动画变化
 *
 * @author ACChain
 */

public class ProxyViewAnimator {
    private View view;

    /**
     * 高度问题
     */
    private float originHeight;
    private float currentHeight;

    public ProxyViewAnimator(View view) {
        this.view = view;
    }

    /**
     * 设置view原始的高度
     */
    public void setOriginHeight(float originHeight){
        this.originHeight = originHeight;
    }

    /**
     * 动态改变布局的高度处理
     */
    @Keep
    public void setHeights(float height) {
        currentHeight = height*originHeight;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height= (int) currentHeight;
        view.requestLayout();
    }

    /**
     * 获取布局的高度
     */
    @Keep
    public float getHeights() {
        return currentHeight;
    }
}
