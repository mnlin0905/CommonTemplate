package com.acchain.community.interfaces;

/**
 * Created on 2018/2/6
 * function : 商品共同接口
 *
 * @author ACChain
 */

public interface CommonProduceDetail {

    /**
     * 获取之前价格
     *
     * @return 价格信息
     */
    double getOldPrice();

    /**
     * 获取当前价格
     *
     * @return 价格信息
     */
    double getCurrentPrice();

    /**
     * 返回价格单位
     *
     * @return 单位, 如元, 或者NHGH等
     */
    String getPriceUnit();

    /**
     * 获取领养进度
     *
     * @return 百分比%
     */
    int getProgress();

    /**
     * 获取领养数量(int类型)
     *
     * @return 已领养的数量
     */
    int getAdoptAmount();

    /**
     * 获取年化分红
     *
     * @return fraction, 如0.118代表11.8%
     */
    double getDividend();

    /**
     * 返回期限时间
     *
     * @return 时间值
     */
    int getBetweenTime();

    /**
     * 获取时间单位
     *
     * @return 如日,
     */
    String getTimeUnit();

    /**
     * 返回商品简介
     *
     * @return 商品的简介或说明性信息
     */
    String getIntroduce();
}
