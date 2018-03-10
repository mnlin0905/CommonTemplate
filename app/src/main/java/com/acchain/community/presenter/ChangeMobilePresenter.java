package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.ChangeMobileContract;
import com.acchain.community.activity.person.ChangeMobileActivity;

import javax.inject.Inject;

/**
 * function---- ChangeMobilePresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/20 07:24:09 (+0000).
 */
public class ChangeMobilePresenter extends BasePresenter<ChangeMobileActivity> implements ChangeMobileContract.Presenter{
    @Inject
    public ChangeMobilePresenter() {}

}