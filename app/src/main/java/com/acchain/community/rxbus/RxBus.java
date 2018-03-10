package com.acchain.community.rxbus;


import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * 功能----RxBus处理数据总线
 * <p>
 * Created by ACChain on 2017/9/23.
 */
public  class  RxBus {
    private static volatile RxBus defaultInstance;

    private final Subject<Object> bus;

    private static CompositeDisposable group;

    // PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者
    private RxBus() {
        bus =  PublishSubject.create().toSerialized();
    }

    // 单例RxBus
    public static RxBus getInstance() {
        if (defaultInstance == null) {
            synchronized (RxBus.class) {
                if (defaultInstance == null) {
                    defaultInstance = new RxBus();
                    group=new CompositeDisposable();
                }
            }
        }
        return defaultInstance ;
    }

    // 发送一个新的事件
    public void post (Object o) {
       bus.onNext(o);
    }

    // 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
    public <T>  Observable<T> toObservable (Class<T> event) {
        return bus.ofType(event);
    }
}