package com.acchain.community.bean;

/**
 * Created on 2018/2/1
 * function : 闲置状态
 * <p>
 * 若不能默认为null,则在对象未赋值时设置为闲置状态
 * 可以通过判断是否为限制状态来查看访问网络数据是否成功
 *
 * @author ACChain
 */

public final class IdleStatusBean {
    private static final IdleStatusBean instance = new IdleStatusBean();

    private IdleStatusBean() {

    }

    /**
     * @return 获取唯一的引用
     */
    public static IdleStatusBean getInstance() {
        return instance;
    }
}
