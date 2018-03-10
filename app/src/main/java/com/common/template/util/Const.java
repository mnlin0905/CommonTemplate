package com.common.template.util;

/**
 * Created by Administrator on 17-1-6.
 */
public class Const {
    //闹铃服务发送的广播,pendingIntent对应的requestCode
    public static final String ALARM_BROADCAST = "com.mnlin.hotchpotch.alarm";
    public static final int ALARM_REQUESTCODE = 1001;

    /**
     * RxBus传递信息,BaseEventBean,信息类型
     * <p>
     * 弹出toast
     * 弹出登录框
     * ...
     */
    public static final int SHOW_TOAST = 2001;
    public static final int SHOW_LOGIN_DIALOG = SHOW_TOAST + 6;

    public static final String KEY_POSITION = "key_position";

    public static final String KEY_FILTER_SOURCE = "key_filter_source";
    public static final String KEY_FILTER_INPUT_KEYS = "key_filter_input_keys";



    /**
     * 有明确值的常量
     * <p>
     * 非法的位置
     */
    public static final int VALUE_POSITION_NULL = -1;

    /**
     * 请求码,用于请求其他activity返回bundle信息
     */
    public static final int REQUEST_CODE_ONE = 1;
    public static final int REQUEST_CODE_TWO = REQUEST_CODE_ONE + 1;
    public static final int REQUEST_CODE_THREE = REQUEST_CODE_ONE + 2;

}
