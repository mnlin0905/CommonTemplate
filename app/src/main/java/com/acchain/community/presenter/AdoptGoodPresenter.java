package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.AdoptGoodContract;
import com.acchain.community.fragment.AdoptGoodFragment;

import javax.inject.Inject;

/**
 * function---- AdoptGoodsPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/16 03:01:17 (+0000).
 */
public class AdoptGoodPresenter extends BasePresenter<AdoptGoodFragment> implements AdoptGoodContract.Presenter{
    @Inject
    public AdoptGoodPresenter() {}

}