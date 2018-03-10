package com.acchain.community.interfaces;

/**
 * Created on 2018/3/6
 * function : 当容器类组件中item有布局变化时,通知刷新布局效果
 *
 * @author ACChain
 */

public interface CallBackAfterItemMeasure {
    /**
     * 标志位
     */
    int TAG_DEFAULT = 0;
    int TAG_ONE = 1;
    int TAG_TWO = 2;
    int TAG_THREE = 3;

    /**
     * @param tag           若是同时检测多个宽高,则用于区分
     * @param measureWidth  item中想要获取的view的宽度
     * @param measureHeight item中想要获取的view的高度
     * @return true则表示此次已经完成了测量, 以后都不需要再调用该方法了;
     * false则表示每次刷新视图都需要实现类执行该方法完成宽度和高度的设置
     */
    boolean doAfterItemMeasure(int tag, int measureWidth, int measureHeight);
}
