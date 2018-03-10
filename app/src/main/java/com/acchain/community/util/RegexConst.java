package com.acchain.community.util;

/**
 * Created on 2018/1/18
 * function : 正则常量
 *
 * @author ACChain
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
     * 实名认证姓名
     */
    public static final String REGEX_VERITY_NAME = "[\\S]{1,16}";

    /**
     * 身份证号
     * 军官证
     * 护照
     */
    public static final String REGEX_ID_CARD_NUMBER = "(^[0-9a-zA-Z]{16}$)|(^[0-9a-zA-Z]{18}$)";
    public static final String REGEX_POLICE_CARD_NUMBER = "(^[0-9a-zA-Z]{16}$)|(^[0-9a-zA-Z]{18}$)";
    public static final String REGEX_FOREIGN_CARD_NUMBER = "(^[0-9a-zA-Z]{16}$)|(^[0-9a-zA-Z]{18}$)";

    /**
     * 移除@Field标志
     */
    public static final String REGEX_REMOVE_AT_FIELD = "\\s+@Field[\\S]+";
}
