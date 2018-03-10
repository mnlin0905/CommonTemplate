package com.acchain.community.bean;

import java.util.List;

/**
 * Created by rsp on 2018/1/31.
 */

public class SendRedPacketRecord {

    /**
     * issueRedEnvelopesList : [{"member_id":25,"transaction_id":"babe1d206ebd4a96951ee82ec8aa9593","amount":20,"create_time":"2018-01-29 20:28:19","name":"小任","currency":"ACC","id":32,"remarks":"发送红包晚餐","receive_id":25,"status":0,"photo_img":"http://192.168.1.253:90/group1/M00/00/02/wKgB_VppVPWAFuuXAACEAPhB84o147.png"},{"member_id":25,"transaction_id":"babe1d206ebd4a96951ee82ec8aa9593","amount":1,"create_time":"2018-01-29 21:12:51","name":"伟林","nickname":"韭菜","currency":"NHGH","id":33,"remarks":"fhdg","receive_id":8,"status":0,"photo_img":"http://192.168.1.253:90/group1/M00/00/02/wKgB_VppVPWAFuuXAACEAPhB84o147.png"},{"member_id":25,"transaction_id":"babe1d206ebd4a96951ee82ec8aa9593","amount":1,"create_time":"2018-01-29 21:16:10","name":"伟林","nickname":"韭菜","currency":"NHGH","id":34,"remarks":"gvcj","receive_id":8,"status":0,"photo_img":"http://192.168.1.253:90/group1/M00/00/02/wKgB_VppVPWAFuuXAACEAPhB84o147.png"},{"member_id":25,"transaction_id":"babe1d206ebd4a96951ee82ec8aa9593","amount":1,"create_time":"2018-01-29 21:19:53","name":"伟林","nickname":"韭菜","currency":"NHGH","id":35,"remarks":"bfgb","receive_id":8,"status":1,"photo_img":"http://192.168.1.253:90/group1/M00/00/02/wKgB_VppVPWAFuuXAACEAPhB84o147.png"},{"member_id":25,"transaction_id":"2107070893","amount":1,"create_time":"2018-01-30 14:42:27","name":"伟林","nickname":"韭菜","currency":"NHGH","id":36,"remarks":"666","receive_id":8,"status":1,"photo_img":"http://192.168.1.253:90/group1/M00/00/02/wKgB_VppVPWAFuuXAACEAPhB84o147.png"},{"member_id":25,"transaction_id":"-1776871936","amount":1,"create_time":"2018-01-30 15:05:01","name":"伟林","nickname":"韭菜","currency":"NHGH","id":37,"remarks":"hvcvf","receive_id":8,"status":1,"photo_img":"http://192.168.1.253:90/group1/M00/00/02/wKgB_VppVPWAFuuXAACEAPhB84o147.png"},{"member_id":25,"transaction_id":"1564815414","amount":1,"create_time":"2018-01-30 15:53:15","name":"伟林","nickname":"韭菜","currency":"NHGH","id":38,"remarks":"hdjfj","receive_id":8,"status":1,"photo_img":"http://192.168.1.253:90/group1/M00/00/02/wKgB_VppVPWAFuuXAACEAPhB84o147.png"},{"member_id":25,"transaction_id":"-1632378520","amount":1,"create_time":"2018-01-30 15:54:40","name":"伟林","nickname":"韭菜","currency":"NHGH","id":39,"remarks":"bgf","receive_id":8,"status":1,"photo_img":"http://192.168.1.253:90/group1/M00/00/02/wKgB_VppVPWAFuuXAACEAPhB84o147.png"},{"member_id":25,"transaction_id":"-1567988801","amount":1,"create_time":"2018-01-30 16:07:12","name":"伟林","nickname":"韭菜","currency":"NHGH","id":40,"remarks":"xc","receive_id":8,"status":1,"photo_img":"http://192.168.1.253:90/group1/M00/00/02/wKgB_VppVPWAFuuXAACEAPhB84o147.png"},{"member_id":25,"transaction_id":"-1408869800","amount":1,"create_time":"2018-01-30 16:08:03","name":"伟林","nickname":"韭菜","currency":"NHGH","id":41,"remarks":"ydjfj","receive_id":8,"status":1,"photo_img":"http://192.168.1.253:90/group1/M00/00/02/wKgB_VppVPWAFuuXAACEAPhB84o147.png"},{"member_id":25,"transaction_id":"-1907791317","amount":1,"create_time":"2018-01-30 16:09:28","name":"伟林","nickname":"韭菜","currency":"NHGH","id":42,"remarks":"dvbv","receive_id":8,"status":1,"photo_img":"http://192.168.1.253:90/group1/M00/00/02/wKgB_VppVPWAFuuXAACEAPhB84o147.png"},{"member_id":25,"transaction_id":"1133521613","amount":1,"create_time":"2018-01-30 16:13:16","name":"伟林","nickname":"韭菜","currency":"NHGH","id":43,"remarks":"chxhd","receive_id":8,"status":1,"photo_img":"http://192.168.1.253:90/group1/M00/00/02/wKgB_VppVPWAFuuXAACEAPhB84o147.png"},{"member_id":25,"transaction_id":"1809677460","amount":1,"create_time":"2018-01-30 16:13:54","name":"伟林","nickname":"韭菜","currency":"NHGH","id":44,"remarks":"fjfjfj","receive_id":8,"status":1,"photo_img":"http://192.168.1.253:90/group1/M00/00/02/wKgB_VppVPWAFuuXAACEAPhB84o147.png"},{"member_id":25,"transaction_id":"1110873797","amount":33,"create_time":"2018-01-30 16:42:45","name":"伟林","nickname":"韭菜","currency":"NHGH","id":45,"remarks":"ikhg","receive_id":8,"status":1,"photo_img":"http://192.168.1.253:90/group1/M00/00/02/wKgB_VppVPWAFuuXAACEAPhB84o147.png"},{"member_id":25,"transaction_id":"1480224466","amount":1,"create_time":"2018-01-30 17:00:44","name":"伟林","nickname":"韭菜","currency":"NHGH","id":46,"remarks":"ggfhw","receive_id":8,"status":1,"photo_img":"http://192.168.1.253:90/group1/M00/00/02/wKgB_VppVPWAFuuXAACEAPhB84o147.png"},{"member_id":25,"transaction_id":"-2012006649","amount":1,"create_time":"2018-01-30 20:23:43","name":"伟林","nickname":"韭菜","currency":"NHGH","id":47,"remarks":"jdbdjdn","receive_id":8,"status":0,"photo_img":"http://192.168.1.253:90/group1/M00/00/02/wKgB_VppVPWAFuuXAACEAPhB84o147.png"}]
     * redEnvelopesCount : 16
     * currencyCount : 2
     */

    private int redEnvelopesCount;
    private int currencyCount;
    private List<IssueRedEnvelopesListBean> issueRedEnvelopesList;

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

    public List<IssueRedEnvelopesListBean> getIssueRedEnvelopesList() {
        return issueRedEnvelopesList;
    }

    public void setIssueRedEnvelopesList(List<IssueRedEnvelopesListBean> issueRedEnvelopesList) {
        this.issueRedEnvelopesList = issueRedEnvelopesList;
    }

    public static class IssueRedEnvelopesListBean {
        /**
         * member_id : 25
         * transaction_id : babe1d206ebd4a96951ee82ec8aa9593
         * amount : 20
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
        private int amount;
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

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
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
