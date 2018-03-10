package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.PrivacyContract;
import com.acchain.community.activity.person.PrivacyActivity;

import javax.inject.Inject;

/**
 * function---- PrivacyPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/22 09:51:28 (+0000).
 */
public class PrivacyPresenter extends BasePresenter<PrivacyActivity> implements PrivacyContract.Presenter{
    @Inject
    public PrivacyPresenter() {}

}