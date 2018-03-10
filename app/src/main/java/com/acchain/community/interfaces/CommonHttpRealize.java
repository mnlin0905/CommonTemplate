package com.acchain.community.interfaces;

import com.acchain.community.bean.AccountBalance;
import com.acchain.community.bean.AliPay;
import com.acchain.community.bean.CommitOrderResponse;
import com.acchain.community.bean.CommonCart;
import com.acchain.community.bean.GetAccountBean;
import com.acchain.community.bean.QueryPay;
import com.acchain.community.bean.TransPasswordStatus;
import com.acchain.community.bean.TransactionListBean;
import com.acchain.community.bean.WeiXinPay;

/**
 * Created on 2018/1/19
 * function : 对于公用http接口,请求成功后返回的处理
 *
 * @author ACChain
 */

public interface CommonHttpRealize {
    /**
     * 获取短信验证码
     */
    void sendMessageFinish();

    /**
     * 获取邮箱验证码
     */
    void sendEmailFinish();

    /**
     * 校验验证码
     */
    void checkCodeFinish();

    /**
     * 获取会员账户信息
     */
    void getAccountFinish(GetAccountBean accountBean);

    /**
     * 1.2.3 设置个人信息
     */
    void setPersonalInfoFinish();

    /**
     * 1.1.9 修改收货地址
     */
    void updateAddressFinish();

    /**
     * 1.2.0 删除收货地址
     */
    void deleteAddressFinish();

    /**
     * 1.1.5 绑定邮箱或手机
     */
    void updateEmailOrMobileFinish();

    /**
     * 1.1.5  查询钱包用户余额
     *
     * @param accountBalance 账户对应币种信息
     */
    void accountBlancesFinish(AccountBalance accountBalance);

    /**
     * 1.1.8  钱包流水记录查询
     *
     * @param transactionListBean 各种记录的集合
     */
    void queryTransactionListFinish(TransactionListBean transactionListBean);

    /**
     * 加入购物车前检查商品状态
     */
    void onCheckProductStateFinish();

    /**
     * 加入购物车
     */
    void onAddCartFinish(CommonCart commonCart);

    /**
     * 编辑购物车
     */
    void onEditCartFinish(CommonCart dataSet, int cartType);


    /**
     * 删除购物车
     */
    void onDeleteCartFinish(CommonCart dataSet,int cartType,int type);

    /**
     * 确认订单
     */
    void onConfirmOrderFinish(Object object,int type);

    /**
     * 提交订单
     */
    void onCommitOrderFinish(CommitOrderResponse commitOrderResponse, int type);

    /**
     * 微信支付
     */
    void onWeiXinPayFinish(WeiXinPay dataSet, int type);

    /**
     * 微信支付
     */
    void onAliPayFinish(AliPay dataSet, int type);


    /**
     * 查询微信支付结果
     */
    void onQueryWechatPayFinish(QueryPay queryPay);


    /**
     * 查询支付宝支付结果
     */
    void onQueryAliPayFinish(QueryPay queryPay,int type);

    /**
     * 1.1.6  设置交易密码
     */
    void setPayPwdFinish();

    /**
     * 1.3.4 获取支付密码重置申请审核状态
     *
     * @param status 人工审核交易密码申请的状态
     */
    void getPayPwdArtificialFinish(TransPasswordStatus status);

    /**
     * 1.3.5 修改支付密码(通过原密码)
     */
    void changePayPwdFinish();

    /**
     * 1.3.6 撤销支付密码重置申请
     */
    void cancelArtificialFinish();

    /**
     * 添加/删除收藏
     * @param type   1--预购，2--领养，3--行权
     * @param b      是否成功
     */
    void onAddOrDeleteCollectFinish(int type,boolean b);
}
