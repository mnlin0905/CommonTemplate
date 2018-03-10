package com.acchain.community.interfaces;

/**
 * Created on 2018/1/30
 * function : 父类获取子碎片中币种数量
 *
 * @author ACChain
 */
public interface OnChildCountChanged {
    /**
     * @return 获取币种数量
     */
    int getCurrencyCount();
}