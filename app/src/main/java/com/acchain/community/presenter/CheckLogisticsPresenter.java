package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.CheckLogisticsContract;
import com.acchain.community.activity.person.CheckLogisticsActivity;

import javax.inject.Inject;

/**
 * function---- CheckLogisticsPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/02/01 12:27:02 (+0000).
 */
public class CheckLogisticsPresenter extends BasePresenter<CheckLogisticsActivity> implements CheckLogisticsContract.Presenter{
    @Inject
    public CheckLogisticsPresenter() {}

}