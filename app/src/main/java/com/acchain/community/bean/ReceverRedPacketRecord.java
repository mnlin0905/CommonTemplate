package com.acchain.community.bean;

import java.util.List;

/**
 * Created by rsp on 2018/1/31.
 */

public class ReceverRedPacketRecord {

    /**
     * redEnvelopesCount : 3
     * receivedRedEnvelopesList : [{"member_id":25,"transaction_id":"babe1d206ebd4a96951ee82ec8aa9593","amount":20,"create_time":"2018-01-29 20:28:19","name":"小任","currency":"ACC","id":32,"remarks":"发送红包晚餐","receive_id":25,"status":0,"photo_img":"http://192.168.1.253:90/group1/M00/00/02/wKgB_VppVPWAFuuXAACEAPhB84o147.png"},{"member_id":8,"transaction_id":"-2012006649","amount":1,"create_time":"2018-01-30 20:23:43","name":"伟林","nickname":"韭菜","currency":"NHGH","id":48,"remarks":"jdbdjdn","receive_id":25,"status":0,"photo_img":"http://192.168.1.253:90/group1/M00/00/02/wKgB_VppVPWAFuuXAACEAPhB84o147.png"},{"member_id":8,"transaction_id":"-2012006649","amount":1,"create_time":"2018-01-30 20:23:43","name":"伟林","nickname":"韭菜","currency":"NHGH","id":49,"remarks":"jdbdjdn","receive_id":25,"status":0,"photo_img":"http://192.168.1.253:90/group1/M00/00/02/wKgB_VppVPWAFuuXAACEAPhB84o147.png"}]
     * currencyCount : 2
     */

    private int redEnvelopesCount;
    private int currencyCount;
    private List<ReceivedRedEnvelopesListBean> receivedRedEnvelopesList;

    public int getRedEnvelopesCount() {
        return redEnvelopesCount;
    }

    public void setRedEnvelopesCount(int redEnvelopesCount) {
        this.redEnvelopesCount = redEnvelopesCount;
    }

    public int getCurrencyCount() {
        return currencyCount;
    }

    public void setCurrencyCount(int currencyCount) {
        this.currencyCount = currencyCount;
    }

    public List<ReceivedRedEnvelopesListBean> getReceivedRedEnvelopesList() {
        return receivedRedEnvelopesList;
    }

    public void setReceivedRedEnvelopesList(List<ReceivedRedEnvelopesListBean> receivedRedEnvelopesList) {
        this.receivedRedEnvelopesList = receivedRedEnvelopesList;
    }

    public static class ReceivedRedEnvelopesListBean {
        /**
         * member_id : 25
         * transaction_id : babe1d206ebd4a96951ee82ec8aa9593
         * amount : 20.0
         * create_time : 2018-01-29 20:28:19
         * name : 小任
         * currency : ACC
         * id : 32
         * remarks : 发送红包晚餐
         * receive_id : 25
         * status : 0
         * photo_img : http://192.168.1.253:90/group1/M00/00/02/wKgB_VppVPWAFuuXAACEAPhB84o147.png
         * nickname : 韭菜
         */

        private int member_id;
        private String transaction_id;
        private double amount;
        private String create_time;
        private String name;
        private String currency;
        private int id;
        private String remarks;
        private int receive_id;
        private int status;
        private String photo_img;
        private String nickname;

        public int getMember_id() {
            return member_id;
        }

        public void setMember_id(int member_id) {
            this.member_id = member_id;
        }

        public String getTransaction_id() {
            return transaction_id;
        }

        public void setTransaction_id(String transaction_id) {
            this.transaction_id = transaction_id;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public int getReceive_id() {
            return receive_id;
        }

        public void setReceive_id(int receive_id) {
            this.receive_id = receive_id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getPhoto_img() {
            return photo_img;
        }

        public void setPhoto_img(String photo_img) {
            this.photo_img = photo_img;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }
}
