package com.acchain.community.bean;

import com.acchain.community.base.BaseBean;

/**
 * Created on 2018/2/7
 * function : 获取账户中币种的信息
 *
 * @author ACChain
 */

public class AccountBalance extends BaseBean {

    /**
     * id : 3
     * turnout_fee : 1
     * recharge_fee : 20        //转入手续费
     * memberId : 8
     * accountBalance : 141.55      //可用余额
     * freezeBalance : -6       //冻结余额
     * currency : RET           //所属币种
     * walletAddress : AG6KJXT8rAH2ovHyxZK9riemdoWR7247
     * createTime : 2018-01-20 14:08:50
     * updateTime : 2018-01-20 14:08:50
     */

    private int id;
    private int turnout_fee;
    private int recharge_fee;
    private String memberId;
    private double accountBalance;
    private int freezeBalance;
    private String currency;
    private String walletAddress;
    private String createTime;
    private String updateTime;

    /**
     * @return 获取可用余额
     */
    public String getAccountBalanceStr() {
        return String.valueOf(accountBalance);
    }

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

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
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
