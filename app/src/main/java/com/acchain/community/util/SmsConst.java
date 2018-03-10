package com.acchain.community.util;

/**
 * Created on 2018/1/18
 * function : 发送短信验证码时，用于区分不同的用途
 *
 * @author ACChain
 */

public class SmsConst {
    public static final String TAG_TOEN = "token";
    /**
     * 登录
     */
    public static final String TAG_LOGIN = "login";
    public static final String TAG_ADMIN_LOGIN = "adminLogin";
    /**
     * 手机号进行注册
     */
    public static final String TAG_REGISTER = "register";
    /**
     * 实名认证
     */
    public static final String TAG_VERIFICATION = "verification";
    /**
     * 邮箱进行注册
     */
    public static final String TAG_REGISTER_EMAIL = "registerEmail";
    /**
     * 修改邮箱
     */
    public static final String TAG_UPDATE_EMAIL = "updateEmail";
    /**
     * 修改交易密码
     */
    public static final String TAG_CHANGE_PWD = "changePassword";
    /**
     * 重置密码
     */
    public static final String TAG_RESET_PWD = "resetPassword";
    /**
     * 设置交易密码
     */
    public static final String TAG_SET_PAY_PWD = "setPayPwd";
    /**
     * 绑定手机号
     */
    public static final String TAG_BOUND_MOBILE = "boundMobile";
    /**
     * 资产转出
     */
    public static final String TAG_TRANSFER_ASSETS = "transfer";
}
