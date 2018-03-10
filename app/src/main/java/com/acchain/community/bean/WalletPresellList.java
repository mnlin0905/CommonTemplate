package com.acchain.community.bean;

import com.acchain.community.base.BaseBean;

/**
 * Created on 2018/2/7
 * function : 预购类商品(用户登录后信息)
 *
 * @author ACChain
 */

public class WalletPresellList extends BaseBean {
    /**
     * account_balance : 99 //对应预购类币种账户余额
     * PRESELL_PRICE : 198.000003  //交易价格(CNY)
     * ID : 742
     * "PROPERTY_CURRENCY": "NHGH", 行权币种
     * "chain ": "ACC",                所属链
     * PRODUCT_IMG :  wKgB_Vpi4tuAd3IyAAFTSLCgDUw599.png //预购商品图片
     * PROPERTY_CURRENCY : NHGH //行权币种
     * PROPERTY_NAME : 华茶壹号 //商品名称
     * EXERCISE_START_TIME : 2018-02-04 10:00:00 // 行权开始时间
     * EXERCISE_END_TIME : 2018-02-04 10:00:00 //行权结束时间
     * IS_EXERCISE : false //当前是否可行权
     */

    private int account_balance;
    private double PRESELL_PRICE;
    private int ID;
    private String PRODUCT_IMG;
    private String PROPERTY_NAME;
    private String EXERCISE_START_TIME;
    private String EXERCISE_END_TIME;
    private String PROPERTY_CURRENCY;
    private String chain;
    private boolean IS_EXERCISE;

    public boolean isIS_EXERCISE() {
        return IS_EXERCISE;
    }

    public void setIS_EXERCISE(boolean IS_EXERCISE) {
        this.IS_EXERCISE = IS_EXERCISE;
    }

    public String getChain() {
        return chain;
    }

    public void setChain(String chain) {
        this.chain = chain;
    }

    public int getAccount_balance() {
        return account_balance;
    }

    public void setAccount_balance(int account_balance) {
        this.account_balance = account_balance;
    }

    public double getPRESELL_PRICE() {
        return PRESELL_PRICE;
    }

    public void setPRESELL_PRICE(double PRESELL_PRICE) {
        this.PRESELL_PRICE = PRESELL_PRICE;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPRODUCT_IMG() {
        return PRODUCT_IMG;
    }

    public void setPRODUCT_IMG(String PRODUCT_IMG) {
        this.PRODUCT_IMG = PRODUCT_IMG;
    }

    public String getPROPERTY_CURRENCY() {
        return PROPERTY_CURRENCY;
    }

    public void setPROPERTY_CURRENCY(String PROPERTY_CURRENCY) {
        this.PROPERTY_CURRENCY = PROPERTY_CURRENCY;
    }

    public String getPROPERTY_NAME() {
        return PROPERTY_NAME;
    }

    public void setPROPERTY_NAME(String PROPERTY_NAME) {
        this.PROPERTY_NAME = PROPERTY_NAME;
    }

    public String getEXERCISE_START_TIME() {
        return EXERCISE_START_TIME;
    }

    public void setEXERCISE_START_TIME(String EXERCISE_START_TIME) {
        this.EXERCISE_START_TIME = EXERCISE_START_TIME;
    }

    public String getEXERCISE_END_TIME() {
        return EXERCISE_END_TIME;
    }

    public void setEXERCISE_END_TIME(String EXERCISE_END_TIME) {
        this.EXERCISE_END_TIME = EXERCISE_END_TIME;
    }
}
