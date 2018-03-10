package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.ChangeSexContract;
import com.acchain.community.activity.person.ChangeSexActivity;

import javax.inject.Inject;

/**
 * function---- ChangeSexPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/15 12:39:14 (+0000).
 */
public class ChangeSexPresenter extends BasePresenter<ChangeSexActivity> implements ChangeSexContract.Presenter{
    @Inject
    public ChangeSexPresenter() {}

}