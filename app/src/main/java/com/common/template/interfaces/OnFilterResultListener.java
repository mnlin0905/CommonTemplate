package com.common.template.interfaces;

/**
 * Created on 2018/3/2
 * function : 过滤监听
 *
 * @author MNLIN
 */

/**
 * 监听过滤结果信息
 */
public interface OnFilterResultListener {
    /**
     * @param position 点击的位置
     */
    void onFilterSelectItem(int position);

    /**
     * @param filter 输入的用于过滤的信息
     */
    void onFilterInputKeys(String filter);
}