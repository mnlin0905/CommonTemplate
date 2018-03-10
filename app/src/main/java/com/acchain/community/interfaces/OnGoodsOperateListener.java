package com.acchain.community.interfaces;

/**
 * Created on 2018/1/24
 * function : 商品类操作接口
 *
 * @author ACChain
 */
public interface OnGoodsOperateListener {
    /**
     * 点击编辑按钮
     *
     * @return 如果开启编辑模式成功, 则返回true
     */
    boolean onEditModeBegin();

    /**
     * 点击back键
     */
    void onEditModeExit();

    /**
     * 当切换全不选/全选
     *
     * @param selectAll true表示全部选中
     */
    void onSelectAll(boolean selectAll);

    /**
     * 删除某些内容
     *
     * @param positions 删除的item位置
     * @return true表示item删除成功
     */
    boolean onDelete(int[] positions);

    /**
     * @return size==0 if has select item(=0)
     */
    int[] getSelectItem();
}