package com.acchain.community.bean;

import com.acchain.community.R;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.base.BaseBean;

import java.util.Locale;

/**
 * Created on 2018/2/8
 * function : 钱包中各种记录的item模型
 *
 * @author ACChain
 */

public class TransactionItemBean extends BaseBean {

    /**
     * status : 3  //状态 0 处理中(待审核)  1已审核  2已复核  3已汇出  4已成功 5已失败 6已撤销  7转账给朋友
     * operationType : 转出  //操作类型
     * transactionId : 44f5da9aa28fdddd66  //交易ID
     * amount : 0.01  //交易金额
     * actualPayment : 0.01  //实际转账
     * arrivePayment : 0.0099   实际到帐
     * currency : BTC   //交易币种
     * createTime : 2018/01/20/17/41  //创建时间
     * updateTime : 2018/01/20/17/41    //到达时间
     * affirmNum : 0  // 确认数
     */

    private int status;
    private String operationType;
    private String transactionId;
    private double amount;
    private double actualPayment;
    private double arrivePayment;
    private String currency;
    private String createTime;
    private String updateTime;
    private String walletAddress;
    private int affirmNum;

    /**
     * @return 获取实际状态
     */
    public String getStatusStr() {
        switch (status) {
            case 0:
                return "待审核";
            case 1:
                return "已审核";
            case 2:
                return "已复核";
            case 3:
                return "已汇出";
            case 4:
                return "已成功";
            case 5:
                return "已失败";
            case 6:
                return "已撤销";
            case 7:
                return "转给朋友";
            default:
                return "未知";
        }
    }

    /**
     * @return 获取实际付款数
     */
    public String getActualPaymentStr() {
        return String.valueOf(actualPayment);
    }

    /**
     * @return 实际到账
     */
    public String getArrivePaymentStr() {
        return String.valueOf(arrivePayment);
    }

    /**
     * @return 当前确认数
     */
    public String getAffirmNumStr() {
        return String.valueOf(affirmNum);
    }

    /**
     * @return 交易数量(币数量)
     */
    public String getAmountStr() {
        return String.valueOf(amount);
    }

    /**
     * 带符号的转账数量
     *
     * @return 交易数量(币数量)
     */
    public String getAmountStrWithSymbol() {
        return String.format(Locale.CHINA,amount<=0?"%f":"+%f",amount);
    }

    /**
     * @return 可操作类型
     */
    public String getOperateStr() {
        switch (status) {
            case 0:
            case 1:
            case 2:
            case 3:
                return "撤销";
            case 4:
            case 5:
            case 6:
                return "完成";
            case 7:
                return "转给朋友";
            default:
                return "未知";
        }
    }

    /**
     * @return 获取状态应当显示的颜色值
     */
    public int getStatusColor(BaseActivity context) {
        return (status & 0x00000003) == status ? context.dispatchGetColor(R.color.green_text_1) : context.dispatchGetColor(R.color.white_text_alpha_60);
    }

    /**
     * @return 获取操作应当显示的颜色值
     */
    public int getOperateColor(BaseActivity context) {
        return (status & 0x00000003) == status ? context.dispatchGetColor(R.color.blue_text_5293FF) : context.dispatchGetColor(R.color.white_text_alpha_60);
    }

    /**
     * @return 获取转账的颜色值, 如果为负, 则颜色不同
     */
    public int getFlowAmoutColor(BaseActivity context) {
        return (status & 0x00000003) == status ? context.dispatchGetColor(R.color.green_text_1) : context.dispatchGetColor(R.color.white_selected_1);
    }

    /**
     * @return 是否可以进行撤销等操作
     */
    public boolean getOperateAble() {
        return (status & 0x00000003) == status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getActualPayment() {
        return actualPayment;
    }

    public void setActualPayment(double actualPayment) {
        this.actualPayment = actualPayment;
    }

    public double getArrivePayment() {
        return arrivePayment;
    }

    public void setArrivePayment(double arrivePayment) {
        this.arrivePayment = arrivePayment;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getAffirmNum() {
        return affirmNum;
    }

    public void setAffirmNum(int affirmNum) {
        this.affirmNum = affirmNum;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }
}
