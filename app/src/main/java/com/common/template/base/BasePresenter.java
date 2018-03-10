package com.common.template.base;

import com.common.template.bean.GetAccountBean;
import com.common.template.interfaces.CommonHttpInterface;
import com.common.template.retrofit.HttpInterface;
import com.common.template.rxbus.RxBus;
import com.common.template.util.Const;
import com.common.template.util.DefaultPreferenceUtil;
import com.common.template.util.HTTPException;
import com.common.template.window.ProgressDialog;
import com.blankj.utilcode.util.ActivityUtils;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 功能----基础中间键,mvp模式
 * <p>
 * Created by MNLIN on 2017/9/22.
 */

public abstract class BasePresenter<T extends BaseView> implements CommonHttpInterface {
    /**
     * 网络成功返回,用0表示
     */
    protected static final int RESULT_OK = 0;
    /**
     * 会员信息
     * <p>
     * 设置默认值,放置空指针异常
     */
    public static GetAccountBean singleAccountBean = new GetAccountBean();
    protected T mView;
    /**
     * 网络请求对象
     */
    @Inject
    protected HttpInterface httpInterface;
    /**
     * 存储rxjava网络请求,用于取消事件
     * <p>
     * 管理网络的事件队列
     */
    private CompositeDisposable group;
    private int taskCount;

    /**
     * 网络请求成功回调接口
     * <p>
     * 如果有该callback,则网络请求成功后将执行该callback方法
     */
    public interface HttpCallback<CB> {
        /**
         * @param tag 回调时用于区分某个请求,tag为null则表示dataSet字段自身为null或异常
         */
        void run(CB tag);
    }

    /**
     * 如果加载框存在,则让其消失
     */
    private void disappearLoading() {
        //如果loading还在显示,则将弹出框隐去(如果还有其他任务,则不消失)
        if (taskCount <= 0) {
            //由于mview等原因可能无法关闭,但必须尝试关闭一次,否则可能导致弹出框一直在显示状态
            ProgressDialog.getInstance(mView.getBaseActivity()).dismiss();
        }
    }

    /**
     * 显示加载框
     *
     * @param loadingMessage 为null时不显示加载框
     */
    private void appearLoading(String loadingMessage) {
        //判断是否显示进度框
        if (loadingMessage == null) {
            return;
        }

        //如果activity不在栈顶,则不显示(同一个类加载器只会对同一个类加载一次)
        if (ActivityUtils.getTopActivity().getClass() != mView.getBaseActivity().getClass()) {
            return;
        }

        //如果baseActivity中还未添加loading碎片,则显示进度框
        ProgressDialog.getInstance(mView.getBaseActivity())
                .setMessage(loadingMessage)
                .show();
    }

    /**
     * 进行网络请求
     */
    @SafeVarargs
    protected final <BEAN extends Object> void requestHttp(Observable<BaseHttpBean<BEAN>> observable, Consumer<BaseHttpBean<BEAN>> onNextListener, Consumer<Throwable>... onErrorListener) {
        requestHttp(null, observable, onNextListener, onErrorListener);
    }

    /**
     * 进行网络请求
     *
     * @param loadingMessage  null mesons no loading
     * @param observable      observable
     * @param onNextListener  listener to deal result
     * @param onErrorListener any error(HTTPException or throwable)
     */
    @SafeVarargs
    protected final <BEAN extends Object> void requestHttp(String loadingMessage, Observable<BaseHttpBean<BEAN>> observable, Consumer<BaseHttpBean<BEAN>> onNextListener, Consumer<Throwable>... onErrorListener) {
        //onError回调
        Consumer<Throwable> onError = throwable -> {
            try {
                //如果不是数据异常,而是网络异常,则提示网络错误
                if(!(throwable instanceof HTTPException)){
                    mView.showToast("网络异常");
                }

                if (onErrorListener.length != 0) {
                    onErrorListener[0].accept(throwable);
                } else {
                    throwable.printStackTrace();
                }
            } catch (Exception e) {
                //统一处理各种未知的异常情况
                e.printStackTrace();
                mView.showToast("未知异常");
            } finally {
                taskCount--;
                disappearLoading();
            }
        };

        //onNext回调
        Consumer<BaseHttpBean<BEAN>> onNext = beanBaseHttpBean -> {
            //如果视图丢失,则不处理网络事件
            if (mView != null && beanBaseHttpBean != null) {
                if (beanBaseHttpBean.result == RESULT_OK) {
                    onNextListener.accept(beanBaseHttpBean);
                    taskCount--;
                    disappearLoading();
                    return;
                } else if (beanBaseHttpBean.result == 1007) {
                    DefaultPreferenceUtil.getInstance().setToken(null);
                    DefaultPreferenceUtil.getInstance().setLogin(false);
                    RxBus.getInstance().post(new BaseEvent(Const.SHOW_LOGIN_DIALOG, null));
                } else {
                    mView.showToast(beanBaseHttpBean.message);
                }
                onError.accept(new HTTPException("ERROR", beanBaseHttpBean));
            }
        };

        //设定请求关系
        taskCount++;
        addDisposable(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> appearLoading(loadingMessage))
                .subscribe(onNext, onError));
    }

    /**
     * 移除rx监听
     */
    protected void removeDisposable() {
        if (group != null) {
            group.dispose();
        }
    }

    /**
     * 添加到监听组
     */
    public void addDisposable(Disposable disposable) {
        if (group == null) {
            group = new CompositeDisposable();
        }
        group.add(disposable);
    }
}
