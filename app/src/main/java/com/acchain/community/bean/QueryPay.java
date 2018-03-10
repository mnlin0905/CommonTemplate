package com.acchain.community.bean;

/**
 * Created by Administrator on 2018/1/24.
 * 增加购物车和修改购物车共用的bean,以及删除购物车
 */

public class QueryPay {

    /**
     * result : 1307
     * state : 0
     * message : 添加成功
     */

    private int result;
    private int state;
    private String message;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
