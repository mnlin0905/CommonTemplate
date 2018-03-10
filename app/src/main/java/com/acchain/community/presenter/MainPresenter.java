package com.acchain.community.presenter;

import com.acchain.community.activity.other.MainActivity;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.MainContract;

import javax.inject.Inject;

/**
 * Created on 2017/12/20
 * function : 主界面
 *
 * @author ACChain
 */

public class MainPresenter extends BasePresenter<MainActivity> implements MainContract.Presenter {
    @Inject
    public MainPresenter() {
    }
}
