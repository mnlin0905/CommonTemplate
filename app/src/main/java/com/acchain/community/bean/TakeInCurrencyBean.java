package com.acchain.community.bean;

import com.acchain.community.base.BaseBean;
import com.acchain.community.util.Const;

import java.util.LinkedList;
import java.util.List;

/**
 * Created on 2018/2/7
 * function : 转入数字资产
 *
 * @author ACChain
 */
public class TakeInCurrencyBean extends BaseBean {
    private String chainName;
    private List<CurrencyBean> currencies;

    public TakeInCurrencyBean(String chainName, List<CurrencyBean> currencies) {
        this.chainName = chainName;
        this.currencies = currencies;
    }

    public static List<TakeInCurrencyBean> translate(TakeInCurrencyAgent agent) {
        if (agent == null) return null;

        List<TakeInCurrencyBean> sources = new LinkedList<>();
        sources.add(new TakeInCurrencyBean(Const.TYPE_CHAIN_ACC, agent.getACChain()));
        sources.add(new TakeInCurrencyBean(Const.TYPE_CHAIN_ETH, agent.getEthereum()));
        sources.add(new TakeInCurrencyBean(Const.TYPE_CHAIN_BTC, agent.getBitChain()));
        return sources;
    }

    public String getChainName() {
        return chainName;
    }

    public void setChainName(String chainName) {
        this.chainName = chainName;
    }

    public List<CurrencyBean> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<CurrencyBean> currencies) {
        this.currencies = currencies;
    }
}
