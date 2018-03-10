package com.acchain.community.bean;

import com.acchain.community.base.BaseBean;

/**
 * Created on 2018/1/10
 * function : 转入/转出数字资产
 *
 * @author ACChain
 */

public class CurrencyBean extends BaseBean {


    /**
     * id : 1
     * currencyName : NHJT.NHGH          //币种名称
     * currencyShortName : NHGH         //币种简称
     * currencyShortNameZh : 宁红港宏
     * tradingRange : NPC               //所属交易区
     * chain : ACC                      //所属链
     * currencyImg : 图片路径           //图片路径
     * isLine : 0
     * isTurnout : 1
     * isRecharge : 1
     * isTransfer : 1
     * createTime : 2018-01-0511:46:34
     * updateTime : 2018-01-0511:46:34
     * deleteFlag : 0
     */

    private int id;
    private String currencyName;
    private String currencyShortName;
    private String currencyShortNameZh;
    private String tradingRange;
    private String chain;
    private String currencyImg;
    private int isLine;
    private int isTurnout;
    private int isRecharge;
    private int isTransfer;
    private String createTime;
    private String updateTime;
    private int deleteFlag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencyShortName() {
        return currencyShortName;
    }

    public void setCurrencyShortName(String currencyShortName) {
        this.currencyShortName = currencyShortName;
    }

    public String getCurrencyShortNameZh() {
        return currencyShortNameZh;
    }

    public void setCurrencyShortNameZh(String currencyShortNameZh) {
        this.currencyShortNameZh = currencyShortNameZh;
    }

    public String getTradingRange() {
        return tradingRange;
    }

    public void setTradingRange(String tradingRange) {
        this.tradingRange = tradingRange;
    }

    public String getChain() {
        return chain;
    }

    public void setChain(String chain) {
        this.chain = chain;
    }

    public String getCurrencyImg() {
        return currencyImg;
    }

    public void setCurrencyImg(String currencyImg) {
        this.currencyImg = currencyImg;
    }

    public int getIsLine() {
        return isLine;
    }

    public void setIsLine(int isLine) {
        this.isLine = isLine;
    }

    public int getIsTurnout() {
        return isTurnout;
    }

    public void setIsTurnout(int isTurnout) {
        this.isTurnout = isTurnout;
    }

    public int getIsRecharge() {
        return isRecharge;
    }

    public void setIsRecharge(int isRecharge) {
        this.isRecharge = isRecharge;
    }

    public int getIsTransfer() {
        return isTransfer;
    }

    public void setIsTransfer(int isTransfer) {
        this.isTransfer = isTransfer;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return currencyShortName + " " + currencyName + " " + currencyShortNameZh;
    }
}
