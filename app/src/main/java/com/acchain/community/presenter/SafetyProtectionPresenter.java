package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.SafetyProtectionContract;
import com.acchain.community.activity.person.SafetyProtectionActivity;

import javax.inject.Inject;

/**
 * function---- SafetyProtectionPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/19 11:56:31 (+0000).
 */
public class SafetyProtectionPresenter extends BasePresenter<SafetyProtectionActivity> implements SafetyProtectionContract.Presenter{
    @Inject
    public SafetyProtectionPresenter() {}

}