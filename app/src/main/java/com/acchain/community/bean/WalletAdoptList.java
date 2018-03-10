package com.acchain.community.bean;

import com.acchain.community.base.BaseBean;

/**
 * Created on 2018/2/7
 * function : 钱包:领养列表
 *
 * @author ACChain
 */

public class WalletAdoptList extends BaseBean {

    /**
     * ADOPT_NAME : 红豆杉 第1期         //商品名称
     * MAX_ADOPT_QTY : 100              //领养期限
     * EXERCISE_START_TIME : 2018-04-18 10:00:00    //行权开始时间
     * ADOPT_CODE_IDS : 3               //领养数量
     * "PROPERTY_CURRENCY": "NHGH",     行权币种
     * "chain ": "ACC",                    所属链
     * ID : 746
     * PRODUCT_IMG :  wKgB_Vpi4tuAd3IyAAFTSLCgDUw599.png                // 图片路径
     * ADOPT_PRICE : 199                //领养价格
     * EXERCISE_END_TIME : 2018-05-18 10:00:00           //行权结束时间
     * IS_EXERCISE : false //当前是否可行权
     */

    private String ADOPT_NAME;
    private int MAX_ADOPT_QTY;
    private String EXERCISE_START_TIME;
    private int ADOPT_CODE_IDS;
    private int ID;
    private String PRODUCT_IMG;
    private int ADOPT_PRICE;
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

    public String getPROPERTY_CURRENCY() {
        return PROPERTY_CURRENCY;
    }

    public void setPROPERTY_CURRENCY(String PROPERTY_CURRENCY) {
        this.PROPERTY_CURRENCY = PROPERTY_CURRENCY;
    }

    public String getChain() {
        return chain;
    }

    public void setChain(String chain) {
        this.chain = chain;
    }

    public String getADOPT_NAME() {
        return ADOPT_NAME;
    }

    public void setADOPT_NAME(String ADOPT_NAME) {
        this.ADOPT_NAME = ADOPT_NAME;
    }

    public int getMAX_ADOPT_QTY() {
        return MAX_ADOPT_QTY;
    }

    public void setMAX_ADOPT_QTY(int MAX_ADOPT_QTY) {
        this.MAX_ADOPT_QTY = MAX_ADOPT_QTY;
    }

    public String getEXERCISE_START_TIME() {
        return EXERCISE_START_TIME;
    }

    public void setEXERCISE_START_TIME(String EXERCISE_START_TIME) {
        this.EXERCISE_START_TIME = EXERCISE_START_TIME;
    }

    public int getADOPT_CODE_IDS() {
        return ADOPT_CODE_IDS;
    }

    public void setADOPT_CODE_IDS(int ADOPT_CODE_IDS) {
        this.ADOPT_CODE_IDS = ADOPT_CODE_IDS;
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

    public int getADOPT_PRICE() {
        return ADOPT_PRICE;
    }

    public void setADOPT_PRICE(int ADOPT_PRICE) {
        this.ADOPT_PRICE = ADOPT_PRICE;
    }

    public String getEXERCISE_END_TIME() {
        return EXERCISE_END_TIME;
    }

    public void setEXERCISE_END_TIME(String EXERCISE_END_TIME) {
        this.EXERCISE_END_TIME = EXERCISE_END_TIME;
    }
}
