package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.CopyrightInformationContract;
import com.acchain.community.activity.person.CopyrightInformationActivity;

import javax.inject.Inject;

/**
 * function---- CopyrightInformationPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/22 09:16:38 (+0000).
 */
public class CopyrightInformationPresenter extends BasePresenter<CopyrightInformationActivity> implements CopyrightInformationContract.Presenter{
    @Inject
    public CopyrightInformationPresenter() {}

}