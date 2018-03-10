package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.PreSellGoodContract;
import com.acchain.community.contract.PreSellGoodsContract;
import com.acchain.community.fragment.PreSellGoodFragment;

import javax.inject.Inject;

/**
 * function---- PreSellGoodsPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/12 12:54:14 (+0000).
 */
public class PreSellGoodPresenter extends BasePresenter<PreSellGoodFragment> implements PreSellGoodContract.Presenter{
    @Inject
    public PreSellGoodPresenter() {}

}