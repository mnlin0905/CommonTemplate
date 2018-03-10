package com.acchain.community.bean;

/**
 * Created by rsp on 2018/1/25.
 */

public class AliPay {

    /**
     * result : 0
     * data : {"out_trade_no":"SC_20180308163249489","form":"alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2018012602079572&biz_content=%7B%22out_trade_no%22%3A%22SC_20180308163249489%22%2C%22total_amount%22%3A0.01%2C%22subject%22%3A%22%E5%BE%AE%E8%BF%88%E5%95%86%E5%9F%8E%E8%AE%A2%E5%8D%95%22%2C%22timeout_express%22%3A%2215m%22%2C%22disable_paymethod%22%3A%22creditCardExpress%22%2C%22body%22%3A%22%E5%BE%AE%E8%BF%88%E5%95%86%E5%9F%8E%E6%94%AF%E4%BB%98%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fmallweb.free.ngrok.cc%2Fmall-web-consumer%2Fpay%2FaliPayCallBack&return_url=http%3A%2F%2Fmallweb.free.ngrok.cc%2Fmall-web-consumer%2Fpay%2FaliPayCallBack&sign=fP2Ct6TP9NUxLHzyaq2mZ3mb4%2FQhSvJqUcQkJGFZrG%2FjnERdZZqqVBq3BH4cV%2BV20ukUl08M%2BDbERav3BWnKPKkoFhX9N5A%2FSJUlEf341DJlBKMkX6vxF543YkgKscGfiH7wuPyjV7ouLF3cudC13wTPiuxL2zXcd25NGUbrT1byVmkA6JFvg2WbUL0%2BAcjkH%2FEwVkcKmPDPnN6wlbWDGZQgY%2FvCRg8bajxlH%2FuDgyryAcABetj1KrwOnzXsOFmusHwoQIXWsKLko8wI%2FzxZXhM%2FUm34%2Fm3Ey3pUdYCmTDtTtXLMr%2BqOYve8ncWi9N9AecQkpe%2BPe8xyt7oPovUM6w%3D%3D&sign_type=RSA2&timestamp=2018-03-08+16%3A32%3A50&version=1.0","state":0}
     * state : 0
     * message : 预支付成功
     */

    private int result;
    private DataBean data;
    private int state;
    private String message;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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

    public static class DataBean {
        /**
         * out_trade_no : SC_20180308163249489
         * form : alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2018012602079572&biz_content=%7B%22out_trade_no%22%3A%22SC_20180308163249489%22%2C%22total_amount%22%3A0.01%2C%22subject%22%3A%22%E5%BE%AE%E8%BF%88%E5%95%86%E5%9F%8E%E8%AE%A2%E5%8D%95%22%2C%22timeout_express%22%3A%2215m%22%2C%22disable_paymethod%22%3A%22creditCardExpress%22%2C%22body%22%3A%22%E5%BE%AE%E8%BF%88%E5%95%86%E5%9F%8E%E6%94%AF%E4%BB%98%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fmallweb.free.ngrok.cc%2Fmall-web-consumer%2Fpay%2FaliPayCallBack&return_url=http%3A%2F%2Fmallweb.free.ngrok.cc%2Fmall-web-consumer%2Fpay%2FaliPayCallBack&sign=fP2Ct6TP9NUxLHzyaq2mZ3mb4%2FQhSvJqUcQkJGFZrG%2FjnERdZZqqVBq3BH4cV%2BV20ukUl08M%2BDbERav3BWnKPKkoFhX9N5A%2FSJUlEf341DJlBKMkX6vxF543YkgKscGfiH7wuPyjV7ouLF3cudC13wTPiuxL2zXcd25NGUbrT1byVmkA6JFvg2WbUL0%2BAcjkH%2FEwVkcKmPDPnN6wlbWDGZQgY%2FvCRg8bajxlH%2FuDgyryAcABetj1KrwOnzXsOFmusHwoQIXWsKLko8wI%2FzxZXhM%2FUm34%2Fm3Ey3pUdYCmTDtTtXLMr%2BqOYve8ncWi9N9AecQkpe%2BPe8xyt7oPovUM6w%3D%3D&sign_type=RSA2&timestamp=2018-03-08+16%3A32%3A50&version=1.0
         * state : 0
         */

        private String out_trade_no;
        private String form;
        private int state;

        public String getOut_trade_no() {
            return out_trade_no;
        }

        public void setOut_trade_no(String out_trade_no) {
            this.out_trade_no = out_trade_no;
        }

        public String getForm() {
            return form;
        }

        public void setForm(String form) {
            this.form = form;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }
}
