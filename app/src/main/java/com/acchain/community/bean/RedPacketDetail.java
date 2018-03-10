package com.acchain.community.bean;

/**
 * Created by rsp on 2018/1/30.
 */

public class RedPacketDetail {

    /**
     * id : 7
     * memberId : 8
     * receiveId : 25
     * amount : 12
     * status : 1
     * currency : ACC
     * transactionId : 1227520533
     * remarks : 发送红包晚餐
     * createTime : 2018-01-29 18:06:10
     */

    private int id;
    private int memberId;
    private int receiveId;
    private String amount;
    private int status;
    private String currency;
    private String transactionId;
    private String remarks;
    private String createTime;

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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
