package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.CompanyIntroduceContract;
import com.acchain.community.activity.person.CompanyIntroduceActivity;

import javax.inject.Inject;

/**
 * function---- CompanyIntroducePresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/17 12:33:17 (+0000).
 */
public class CompanyIntroducePresenter extends BasePresenter<CompanyIntroduceActivity> implements CompanyIntroduceContract.Presenter{
    @Inject
    public CompanyIntroducePresenter() {}

}