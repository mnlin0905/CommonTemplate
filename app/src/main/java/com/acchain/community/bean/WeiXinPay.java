package com.acchain.community.bean;

/**
 * Created by Administrator on 2018/2/28.
 */

public class WeiXinPay {

    /**
     * result : 0
     * data : {"package":"Sign=WXPay","out_trade_no":"SC_20180301164951936","appid":"wx05de913a165f4d9c","sign":"D0A864B7CB0D69D90D5F4D2A2FD0AE73","partnerid":"1498819262","prepayid":"wx2018030116495321a74455620967444259","noncestr":"2de30034a6e840fab1d4458f75428e91","timestamp":"1519894191"}
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
         * package : Sign=WXPay
         * out_trade_no : SC_20180301164951936
         * appid : wx05de913a165f4d9c
         * sign : D0A864B7CB0D69D90D5F4D2A2FD0AE73
         * partnerid : 1498819262
         * prepayid : wx2018030116495321a74455620967444259
         * noncestr : 2de30034a6e840fab1d4458f75428e91
         * timestamp : 1519894191
         */
        private String packageX;
        private String out_trade_no;
        private String appid;
        private String sign;
        private String partnerid;
        private String prepayid;
        private String noncestr;
        private String timestamp;

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getOut_trade_no() {
            return out_trade_no;
        }

        public void setOut_trade_no(String out_trade_no) {
            this.out_trade_no = out_trade_no;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
    }
}
