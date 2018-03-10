package com.acchain.community.presenter;


import com.acchain.community.R;
import com.acchain.community.activity.person.LoginActivity;
import com.acchain.community.base.BaseHttpBean;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.LoginContract;
import com.acchain.community.events.LoginSuccess;
import com.acchain.community.rxbus.RxBus;
import com.acchain.community.util.DefaultPreferenceUtil;

import javax.inject.Inject;

/**
 * function---- LoginPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/09 05:59:52 (+0000).
 */
public class LoginPresenter extends BasePresenter<LoginActivity> implements LoginContract.Presenter {
    @Inject
    public LoginPresenter() {
    }

    public void doLogin(String username, String pwd) {
        requestHttp(mView.getString(R.string.login_presenter_login_ing),httpInterface.doLogin(username, pwd),this::setConfigChanged);
    }

    @Override
    public void dynamicLogin(String username, String code) {
        requestHttp(mView.getString(R.string.login_presenter_login_ing),httpInterface.dynamicLogin(username, code),this::setConfigChanged);
    }

    private void setConfigChanged(BaseHttpBean<Object> objectBaseHttpBean){
        //设置登录后需要保存的信息
        DefaultPreferenceUtil.getInstance().setToken(objectBaseHttpBean.token);
        DefaultPreferenceUtil.getInstance().setLogin(true);
        RxBus.getInstance().post(new LoginSuccess());

        mView.dynamicLoginFinish();
    }
}