package com.acchain.community.bean;

/**
 * Created by rsp on 2018/2/2.
 */

public class Blances {

    /**
     * id : 18
     * turnout_fee : 1
     * recharge_fee : 10
     * memberId : 25
     * accountBalance : 200
     * freezeBalance : 120
     * currency : ACC
     * walletAddress : ANu6E26fvkd5GBV8dtBgvBps79U7R6PMiP
     * createTime : 2018-01-27 16:09:36
     * updateTime : 2018-01-27 16:09:36
     */

    private int id;
    private int turnout_fee;
    private int recharge_fee;
    private String memberId;
    private int accountBalance;
    private int freezeBalance;
    private String currency;
    private String walletAddress;
    private String createTime;
    private String updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTurnout_fee() {
        return turnout_fee;
    }

    public void setTurnout_fee(int turnout_fee) {
        this.turnout_fee = turnout_fee;
    }

    public int getRecharge_fee() {
        return recharge_fee;
    }

    public void setRecharge_fee(int recharge_fee) {
        this.recharge_fee = recharge_fee;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public int getFreezeBalance() {
        return freezeBalance;
    }

    public void setFreezeBalance(int freezeBalance) {
        this.freezeBalance = freezeBalance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
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
}
