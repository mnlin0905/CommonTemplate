package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.StaffServiceChangeTransContract;
import com.acchain.community.activity.person.StaffServiceChangeTransActivity;

import javax.inject.Inject;

/**
 * function---- StaffServiceChangeTransPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/31 09:09:46 (+0000).
 */
public class StaffServiceChangeTransPresenter extends BasePresenter<StaffServiceChangeTransActivity> implements StaffServiceChangeTransContract.Presenter{
    @Inject
    public StaffServiceChangeTransPresenter() {}

}