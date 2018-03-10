package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.SoftSettingContract;
import com.acchain.community.activity.person.SoftSettingActivity;

import javax.inject.Inject;

/**
 * function---- SoftSettingPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/15 05:50:53 (+0000).
 */
public class SoftSettingPresenter extends BasePresenter<SoftSettingActivity> implements SoftSettingContract.Presenter{
    @Inject
    public SoftSettingPresenter() {}

}