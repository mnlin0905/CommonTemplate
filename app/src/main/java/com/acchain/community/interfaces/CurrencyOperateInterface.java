package com.acchain.community.interfaces;

/**
 * Created on 2018/1/13
 * function : 所有数字资产的操作集合
 * <p>
 * 因数字资产可能种类较多,若在每个界面中都对操作进行处理会比较混乱;
 * 定义所有数字资产操作的接口,之后资产对应的操作应由被实现的interface来处理
 *
 * @author ACChain
 */

public interface CurrencyOperateInterface {
    /**
     * @param operate 不同类别中对应的可操作类型
     * @return 返回对该操作的简要说明
     */
    String declare(String operate);
}
