package com.acchain.community.bean;

import com.acchain.community.base.BaseBean;

import java.util.List;

/**
 * Created on 2018/1/10
 * function : 转入数字资产(代理类)
 *
 * @author ACChain
 */

public class TakeInCurrencyAgent extends BaseBean {
    private List<CurrencyBean> ACChain;
    private List<CurrencyBean> Ethereum;
    private List<CurrencyBean> BitChain;

    public List<CurrencyBean> getACChain() {
        return ACChain;
    }

    public void setACChain(List<CurrencyBean> ACChain) {
        this.ACChain = ACChain;
    }

    public List<CurrencyBean> getEthereum() {
        return Ethereum;
    }

    public void setEthereum(List<CurrencyBean> ethereum) {
        Ethereum = ethereum;
    }

    public List<CurrencyBean> getBitChain() {
        return BitChain;
    }

    public void setBitChain(List<CurrencyBean> bitChain) {
        BitChain = bitChain;
    }
}
