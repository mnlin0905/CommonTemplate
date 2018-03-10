package com.acchain.community.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created on 2018/1/13
 * function : 动态代理类,可处理跳转中具体逻辑部分
 *
 * @author ACChain
 */

public class CurrencyOperateProxy implements InvocationHandler {

    /**
     * 需要代理的对象
     */
    private Object originObj;

    /**
     * @param proxyHandler 实际操作类
     */
    public CurrencyOperateProxy() {

    }

    /**
     * @param originObj 绑定需要代理的对象
     */
    public <T extends Object> Object bind(T originObj){
        this.originObj = originObj;
        return Proxy.newProxyInstance(originObj.getClass().getClassLoader(), originObj.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(originObj, args);

        // TODO: 2018/1/15 额外操作
        return result;
    }
}
