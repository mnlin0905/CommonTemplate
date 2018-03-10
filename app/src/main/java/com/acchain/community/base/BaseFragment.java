package com.acchain.community.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acchain.community.bean.AccountBalance;
import com.acchain.community.bean.AliPay;
import com.acchain.community.bean.CommitOrderResponse;
import com.acchain.community.bean.CommonCart;
import com.acchain.community.bean.GetAccountBean;
import com.acchain.community.bean.QueryPay;
import com.acchain.community.bean.TransPasswordStatus;
import com.acchain.community.bean.TransactionListBean;
import com.acchain.community.bean.WeiXinPay;
import com.acchain.community.dagger.component.DaggerFragmentComponent;
import com.acchain.community.dagger.component.FragmentComponent;
import com.acchain.community.dagger.module.FragmentModule;
import com.acchain.community.retrofit.HttpInterface;
import com.acchain.community.rxbus.RxBus;
import com.acchain.community.util.Const;
import com.acchain.community.util.L;
import com.alibaba.android.arouter.launcher.ARouter;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 3/9/17.
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {
    /*&&&&liubin：懒加载，其实就是抽两个方法出来start&&&&*/
    /**
     * 是否已经初初始化布局
     */
    protected boolean isInit = false;
    /**
     * 是否已经在前台过
     */
    protected boolean isLoad = false;
    //上下文对象
    @Inject
    protected BaseActivity baseActivity;
    //根部局
    @Inject
    protected ViewGroup rootView;
    @Inject
    protected T mPresenter;
    @Inject
    protected HttpInterface httpInterface;
    protected FragmentComponent fragmentComponent;
    /**
     * 用于在fragment销毁时接触绑定
     */
    Unbinder unbinder;

    /**
     * 当fragment到前台
     */
    protected void onCurrent(boolean isWake) {

        Log.i("&&&&&&&&&&", "onCurrent,isWake=" + isWake);
    }

    /**
     * 当fragment从前台到不可见
     */
    protected void notCurrent() {
        Log.i("&&&&&&&&&&", "notCurrent");
    }

    /**
     * 视图是否已经对用户可见，系统的方法
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isCanLoadData();
    }

    /**
     * 是否可以加载数据
     * 可以加载数据的条件：
     * 1.视图已经初始化
     * 2.视图对用户可见
     */
    private void isCanLoadData() {
        if (!isInit) {//试图没有初始化
            return;
        }
        if (getUserVisibleHint()) {//试图可见
            onCurrent(false);
            isLoad = true;
        } else {//试图不可见
            if (isLoad) {
                notCurrent();
            }
        }
    }

    /**
     * 数据处理
     */
    protected abstract void initData(Bundle savedInstanceState);

    /**
     * 获取xml布局文件
     */
    protected abstract @LayoutRes
    int getContentViewId();

    /**
     * 使用dagger注入自身
     */
    protected abstract void injectSelf();

    /**
     * @param msg 需要显示的toast消息
     */
    public void showToast(String msg) {
        RxBus.getInstance().post(new BaseEvent(Const.SHOW_TOAST, msg == null ? "null" : msg));
    }

    @Override
    public void onAttach(Context context) {
        Logger.v("onAttach: " + getClass().getSimpleName());
        super.onAttach(context);
    }

    @Override
    final public void onCreate(@Nullable Bundle savedInstanceState) {
        Logger.v("onCreate: " + getClass().getSimpleName());
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    final public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        Logger.v("onCreateView: " + getClass().getSimpleName());
        /*liubin*/
        isInit = true;
        isLoad = true;
        /*liubin*/
        return rootView == null ? inflater.inflate(getContentViewId(), null, false) : rootView;
    }

    @Override
    final public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Logger.v("onActivityCreated: " + getClass().getSimpleName());
        super.onActivityCreated(savedInstanceState);

        //只有第一次初始化view时,才会进行如下操作
        if (rootView == null) {
            fragmentComponent = DaggerFragmentComponent.builder().applicationComponent(BaseApplication.getApplicationComponent()).fragmentModule(new FragmentModule(this)).build();
            injectSelf();
            if (mPresenter != null) {
                mPresenter.mView = this;
            }
            unbinder = ButterKnife.bind(this, rootView);
            ARouter.getInstance().inject(this);
            Log.i("&&&&&&&&&&", "initData");
            initData(savedInstanceState);
        }

        //每次碎片视图销毁重现,都会调用主动刷新的方法
        refreshData(savedInstanceState);
    }

    /**
     * 碎片每次初始化时,屏蔽onCreateView与onActivityCreated的重复操作
     * 但每次会执行该方法
     */
    protected void refreshData(Bundle savedInstanceState) {
    }

    @Override
    public void onResume() {
        Logger.v("onResume: " + getClass().getSimpleName());
        /*liubin*/
        if (isInit && getUserVisibleHint()) {
            onCurrent(true);
        }
        /*liubin*/
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        Logger.v("onDestroyView: " + getClass().getSimpleName());
        /*liubin*/
        isInit = false;
        isLoad = false;
        /*liubin*/

        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Logger.v("onDestroy: " + getClass().getSimpleName());
        if (mPresenter != null) {
            mPresenter.removeDisposable();
            mPresenter.mView = null;
        }
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Logger.v("onDetach: " + getClass().getSimpleName());
        super.onDetach();
    }

    @Override
    public BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    @Override
    public FragmentManager getSFManager() {
        synchronized (BaseView.class) {
            return baseActivity.getSupportFragmentManager();
        }
    }

    @Override
    public void sendMessageFinish() {
        showToast("短信验证码发送成功,请注意查看");
    }

    @Override
    public void sendEmailFinish() {
        showToast("邮箱验证码发送成功,请注意查看");
    }

    @Override
    public void checkCodeFinish() {

    }

    @Override
    public void getAccountFinish(GetAccountBean accountBean) {

    }

    @Override
    public void setPersonalInfoFinish() {

    }

    @Override
    public void onCheckProductStateFinish() {

    }

    @Override
    public void onAddCartFinish(CommonCart commonCart) {
        if (commonCart == null) {
            L.i("failed");
            showToast("加入购物车失败");
            return;
        }
        if (commonCart.getState() != 0) {
            showToast(commonCart.getMessage());
        } else {
            L.i("");
            showToast("加入购物车成功");
        }
    }

    @Override
    public void onEditCartFinish(CommonCart dataSet, int type) {

    }

    @Override
    public void onDeleteCartFinish(CommonCart dataSet, int cartType,int type) {

    }

    @Override
    public void onConfirmOrderFinish(Object object, int type) {
    }

    @Override
    public void onCommitOrderFinish(CommitOrderResponse commitOrderResponse, int type) {

    }

    @Override
    public void onWeiXinPayFinish(WeiXinPay dataSet, int type) {

    }

    @Override
    public void onAliPayFinish(AliPay dataSet, int type) {

    }

    @Override
    public void onQueryWechatPayFinish(QueryPay queryPay) {

    }

    @Override
    public void onQueryAliPayFinish(QueryPay queryPay, int type) {

    }

    @Override
    public void updateAddressFinish() {

    }

    @Override
    public void deleteAddressFinish() {

    }

    @Override
    public void updateEmailOrMobileFinish() {

    }

    @Override
    public void setPayPwdFinish() {

    }

    @Override
    public void cancelArtificialFinish() {

    }

    @Override
    public void changePayPwdFinish() {

    }

    @Override
    public void getPayPwdArtificialFinish(TransPasswordStatus status) {

    }

    @Override
    public void accountBlancesFinish(AccountBalance AccountBalance) {

    }

    @Override
    public void queryTransactionListFinish(TransactionListBean transactionListBean) {

    }

    @Override
    public void onAddOrDeleteCollectFinish(int type, boolean b) {

    }
}
