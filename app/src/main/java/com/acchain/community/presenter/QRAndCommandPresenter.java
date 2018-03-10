package com.acchain.community.presenter;


import com.acchain.community.activity.person.QRAndCommandActivity;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.QRAndCommandContract;

import javax.inject.Inject;

/**
 * function---- QRAndCommandPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/15 11:38:29 (+0000).
 */
public class QRAndCommandPresenter extends BasePresenter<QRAndCommandActivity> implements QRAndCommandContract.Presenter{
    @Inject
    public QRAndCommandPresenter() {}

    @Override
    public void getWeiCode(String token) {
        requestHttp(httpInterface.getWeiCode(token), command -> mView.getWeiCodeFinish(command.dataSet));
    }
}