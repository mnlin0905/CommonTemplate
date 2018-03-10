package com.acchain.community.bean;

import com.acchain.community.base.BaseBean;

import java.util.List;

/**
 * Created on 2018/1/30
 * function : 获取流转记录等数据
 *
 * @author ACChain
 */

public class TransactionListBean extends BaseBean {
    private List<TransactionItemBean> allList; //全部流水记录
    private List<TransactionItemBean> transferList;//转出平台记录
    private List<TransactionItemBean> transferFriend;//转出给朋友的记录
    private List<TransactionItemBean> rechargeList;//转入钱包记录

    public List<TransactionItemBean> getAllList() {
        return allList;
    }

    public void setAllList(List<TransactionItemBean> allList) {
        this.allList = allList;
    }

    public List<TransactionItemBean> getTransferList() {
        return transferList;
    }

    public void setTransferList(List<TransactionItemBean> transferList) {
        this.transferList = transferList;
    }

    public List<TransactionItemBean> getTransferFriend() {
        return transferFriend;
    }

    public void setTransferFriend(List<TransactionItemBean> transferFriend) {
        this.transferFriend = transferFriend;
    }

    public List<TransactionItemBean> getRechargeList() {
        return rechargeList;
    }

    public void setRechargeList(List<TransactionItemBean> rechargeList) {
        this.rechargeList = rechargeList;
    }
}
