package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.TakeOutPlatformContract;
import com.acchain.community.activity.wallet.TakeOutPlatformActivity;

import javax.inject.Inject;

/**
 * function---- TakeOutPlatformPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/11 13:46:19 (+0000).
 */
public class TakeOutPlatformPresenter extends BasePresenter<TakeOutPlatformActivity> implements TakeOutPlatformContract.Presenter{
    @Inject
    public TakeOutPlatformPresenter() {}

    /**
     * 1.1.6  转出数字资产
     *
     * @param token            登录标志
     * @param currency         币种简称
     * @param number           转出数量
     * @param transactionPwd   支付密码
     * @param verificationCode 手机验证码
     * @param walletAddress    转出地址
     */
    @Override
    public void transfer(String token, String currency, String number, String transactionPwd, String verificationCode, String walletAddress) {
        requestHttp(httpInterface.transfer(token,currency,number,transactionPwd,verificationCode,walletAddress),
                objectBaseHttpBean -> mView.transferFinish());
    }
}