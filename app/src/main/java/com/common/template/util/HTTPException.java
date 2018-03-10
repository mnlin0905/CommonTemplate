package com.common.template.util;


import com.common.template.base.BaseHttpBean;

/**
 * Created on 2018/1/18
 * function :
 *
 * @author MNLIN
 */

/**
 * 当返回值不为0或对象为null时,上报异常
 */
public final class HTTPException extends RuntimeException {
    public BaseHttpBean<? extends Object> bean;

    public <TT extends Object> HTTPException(String message, BaseHttpBean<TT> bean) {
        super(message);
        this.bean = bean;
    }
}
