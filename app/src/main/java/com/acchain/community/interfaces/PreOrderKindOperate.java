package com.acchain.community.interfaces;

/**
 * Created on 2018/1/13
 * function : 预购类资产操作
 *
 * @author ACChain
 */

public interface PreOrderKindOperate extends CurrencyOperateInterface {
    /**
     * 转入数字资产
     */
    void takeIn(int position);

    /**
     * 转出数字资产
     */
    void takeOut(int position);

    /**
     * 行权
     */
    void exercise(int position);

    /**
     * 流转记录
     */
    void flowRecord(int position);
}
