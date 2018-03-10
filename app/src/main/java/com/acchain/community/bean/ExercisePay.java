package com.acchain.community.bean;

/**
 * Created by Administrator on 2018/2/28.
 */

public class ExercisePay {

    /**
     * result : 1304
     * state : 1
     * message : 您的钱包余额不足
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
