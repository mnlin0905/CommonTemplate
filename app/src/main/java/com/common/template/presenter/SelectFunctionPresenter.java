package com.common.template.presenter;

import com.common.template.base.BasePresenter;
import com.common.template.activity.other.SelectFunctionActivity;
import com.common.template.contract.SelectFunctionContract;

import javax.inject.Inject;

/**
 * 功能----功能选择界面,首页,中间键
 * <p>
 * Created by MNLIN on 2017/9/22.
 */

public class SelectFunctionPresenter extends BasePresenter<SelectFunctionActivity> implements SelectFunctionContract.Presenter  {
    @Inject
    public SelectFunctionPresenter(){
    }
}
