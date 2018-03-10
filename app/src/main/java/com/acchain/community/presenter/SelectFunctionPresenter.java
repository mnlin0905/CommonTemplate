package com.acchain.community.presenter;

import com.acchain.community.base.BasePresenter;
import com.acchain.community.activity.other.SelectFunctionActivity;
import com.acchain.community.contract.SelectFunctionContract;

import javax.inject.Inject;

/**
 * 功能----功能选择界面,首页,中间键
 * <p>
 * Created by ACChain on 2017/9/22.
 */

public class SelectFunctionPresenter extends BasePresenter<SelectFunctionActivity> implements SelectFunctionContract.Presenter  {
    @Inject
    public SelectFunctionPresenter(){
    }
}
