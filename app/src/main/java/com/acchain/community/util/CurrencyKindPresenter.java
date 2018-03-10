package com.acchain.community.util;

import com.acchain.community.interfaces.AdoptKindOperate;
import com.acchain.community.interfaces.CurrencyOperateInterface;
import com.acchain.community.interfaces.PreOrderKindOperate;

/**
 * Created on 2018/1/17
 * function : 用于处理币种类跳转事件
 *
 * @author ACChain
 */

public class CurrencyKindPresenter implements PreOrderKindOperate, AdoptKindOperate {
    private static CurrencyOperateInterface proxy;

    private CurrencyKindPresenter() {
    }

    public synchronized static CurrencyOperateInterface getInstance() {
        if (proxy == null) {
            //生成代理类
             return (CurrencyOperateInterface) new CurrencyOperateProxy().bind(new CurrencyKindPresenter());
        }
        return proxy;
    }


    @Override
    public String declare(String operate) {
        return null;
    }

    @Override
    public void takeIn(int position) {

    }

    @Override
    public void takeOut(int position) {

    }

    @Override
    public void exercise(int position) {

    }

    @Override
    public void flowRecord(int position) {

    }
}
