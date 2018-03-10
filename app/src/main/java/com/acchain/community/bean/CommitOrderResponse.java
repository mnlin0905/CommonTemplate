package com.acchain.community.bean;

/**
 * Created by Administrator on 2018/2/9.
 */

public class CommitOrderResponse {
    /**
     * result : 1304
     * orderCode : OC_QG_20180305170612969
     * state : 0
     * serialCode : SC_20180305170612891
     * message : 生成订单成功
     */

    private int result;
    private String orderCode;
    private int state;
    private String serialCode;
    private String message;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getSerialCode() {
        return serialCode;
    }

    public void setSerialCode(String serialCode) {
        this.serialCode = serialCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
