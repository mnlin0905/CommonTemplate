package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.PreSellNewsContract;
import com.acchain.community.activity.index.PreSellNewsActivity;

import javax.inject.Inject;

/**
 * function---- PreSellNewsPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/15 06:32:20 (+0000).
 */
public class PreSellNewsPresenter extends BasePresenter<PreSellNewsActivity> implements PreSellNewsContract.Presenter{
    @Inject
    public PreSellNewsPresenter() {}

}