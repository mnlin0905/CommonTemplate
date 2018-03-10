package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.ChangeLocationContract;
import com.acchain.community.activity.person.ChangeLocationActivity;

import javax.inject.Inject;

/**
 * function---- ChangeLocationPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/15 12:40:55 (+0000).
 */
public class ChangeLocationPresenter extends BasePresenter<ChangeLocationActivity> implements ChangeLocationContract.Presenter{
    @Inject
    public ChangeLocationPresenter() {}

}