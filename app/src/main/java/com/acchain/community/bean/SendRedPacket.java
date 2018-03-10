package com.acchain.community.bean;

/**
 * Created by rsp on 2018/1/29.
 */

public class SendRedPacket {

    /**
     * id : 23
     * memberId : 25
     * receiveId : 25
     * amount : 20
     * status : 0
     * currency : ACC
     * transactionId : 1505046815
     * remarks : 发送红包晚餐
     * createTime : 1517227228119
     */

    private int id;
    private int memberId;
    private int receiveId;
    private int amount;
    private int status;
    private String currency;
    private String transactionId;
    private String remarks;
    private long createTime;
    private long expireDate;

    public long getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(long expireDate) {
        this.expireDate = expireDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(int receiveId) {
        this.receiveId = receiveId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
