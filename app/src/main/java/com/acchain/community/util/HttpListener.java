package com.acchain.community.util;

public abstract class HttpListener<E> {
    public abstract void onSuccess(E response);

    public void onFailed(HTTPException e){

    }

    public void onFailed(Throwable e){

    }
}