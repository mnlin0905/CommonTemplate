package com.common.template.arouter;

/**
 * 功能----路径跳转activity/fragment
 * <p>
 * Created by MNLIN on 2017/11/24.
 */

public final class ARouterConst {
    /**
     * 无权限
     * 登录
     * 绿色通道(若设定则无法跳转,相当于禁止功能)
     * activity启动:清除任务栈
     */
    public static final int FLAG_NONE = 0x00000000;
    public static final int FLAG_LOGIN = 0x00000003;
    public static final int FLAG_FORCE_ACCESS = 0x00000040;
    public static final int FLAG_ACTIVITY_CLEAR_TOP = 0x00000200;

    /**
     * activity/fragment
     */
    public static final String Activity_SelectFunctionActivity = "/activity/SelectFunctionActivity";
    public static final String Activity_SearchFilterActivity = "/activity/SearchFilterActivity";
    public static final String Fragment_WalletFragment = "/fragment/WalletFragment";
}
