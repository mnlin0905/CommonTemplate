package com.common.template.base;

import android.support.v4.app.FragmentManager;

import com.common.template.interfaces.CommonHttpRealize;

/**
 * function : 基础View，MVP专用
 *
 * @author MNLIN
 * @date 2017/12/1
 */

public interface BaseView extends CommonHttpRealize {
    /**
     * @param msg 显示toast
     */
    void showToast(String msg);

    /**
     * @return 获取活动对象
     */
    BaseActivity getBaseActivity();

    /**
     * @return 获取碎片管理器
     */
    FragmentManager getSFManager();

}
