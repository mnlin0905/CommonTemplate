package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.ShowMobileContract;
import com.acchain.community.activity.person.ShowMobileActivity;

import javax.inject.Inject;

/**
 * function---- ShowMobilePresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/20 07:23:31 (+0000).
 */
public class ShowMobilePresenter extends BasePresenter<ShowMobileActivity> implements ShowMobileContract.Presenter{
    @Inject
    public ShowMobilePresenter() {}

}