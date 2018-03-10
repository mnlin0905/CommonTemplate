package com.common.template.base;

import android.os.Build;
import android.support.annotation.ColorRes;

import java.io.Serializable;

/**
 * Created on 2018/1/2
 * function : model模型基础
 *
 * @author MNLIN
 */

public class BaseBean implements Cloneable, Serializable {
    /**
     * 序列化
     */
    private static final long serialVersionUID = 357638154L;
    /**
     * 记录当前model是否为选中状态
     * <p>
     * 适用与在ListView等需要多选时记录model所处的状态
     * <p>
     * 默认为false,表示未选中
     */
    private boolean SELECTED_STATUS;

    public boolean isSELECTED_STATUS() {
        return SELECTED_STATUS;
    }

    public void setSELECTED_STATUS(boolean SELECTED_STATUS) {
        this.SELECTED_STATUS = SELECTED_STATUS;
    }

    /**
     * 反选选中状态
     *
     * @return 返回当前的选中状态
     */
    public boolean toggleSELECTED_STATUS() {
        return SELECTED_STATUS = !SELECTED_STATUS;
    }

    /**
     * 获取颜色值
     */
    protected int dispatchGetColor(@ColorRes int resId) {
        if (Build.VERSION.SDK_INT < 23) {
            return BaseApplication.app.getResources().getColor(resId);
        } else {
            return BaseApplication.app.getResources().getColor(resId, null);
        }
    }

}
