package com.acchain.community.bean;

/**
 * Created by rsp on 2018/2/3.
 *
 * 转账后返回结果信息
 */

public class TransferInfo {

    /**
     * transaction_id : 864cba84b23f4ce888254becf79f48f5
     * payment_model : 钱包支付
     * create_time : 2018-02-03 13:52:46
     * targer_name : wm1000025
     * owen_name : 韭菜
     */

    private String transaction_id;
    private String payment_model;
    private String create_time;
    private String targer_name;
    private String owen_name;

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getPayment_model() {
        return payment_model;
    }

    public void setPayment_model(String payment_model) {
        this.payment_model = payment_model;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getTarger_name() {
        return targer_name;
    }

    public void setTarger_name(String targer_name) {
        this.targer_name = targer_name;
    }

    public String getOwen_name() {
        return owen_name;
    }

    public void setOwen_name(String owen_name) {
        this.owen_name = owen_name;
    }
}
