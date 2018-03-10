package com.common.template.util;

/**
 * Created on 2018/1/18
 * function : 正则常量
 *
 * @author MNLIN
 */

public class RegexConst {
    /**
     * 匹配手机号
     */
    public static final String REGEX_PHONE = "^[0-9]{11}$";

    /**
     * 匹配邮箱
     */
    public static final String REGEX_EMAIL = "^([_0-9a-zA-Z]+)@([_0-9a-zA-Z]+)(\\.com)$";

    /**
     * 匹配手机验证码
     */
    public static final String REGEX_RANDOM_NUMBER_6 = "^[0-9]{6}$";
    public static final String REGEX_RANDOM_TEXT_4 = "^[0-9a-zA-Z]{4}$";

    /**
     * 匹配账户密码
     */
    public static final String REGEX_PASSWORD = "^[_0-9a-zA-Z]{6,18}$";
    public static final String REGEX_TRANSACTION_PASSWORD = "^[0-9]{6}$";

    /**
     * 昵称
     */
    public static final String REGEX_NICKNAME = "^[\\S]+$";

    /**
     * 移除@Field标志
     */
    public static final String REGEX_REMOVE_AT_FIELD = "\\s+@Field[\\S]+";
}
