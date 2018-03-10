package com.acchain.community.presenter;

import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.PanicGoodContract;
import com.acchain.community.fragment.PanicGoodFragment;
import javax.inject.Inject;

/**
 * function---- PanicGoodsPresenter
 * <p> fragment的presenter
 * Created(Gradle default create) by ACChain on 2018/01/09 07:43:07 (+0000).
 */
public class PanicGoodPresenter extends BasePresenter<PanicGoodFragment> implements PanicGoodContract.Presenter{
    @Inject
    public PanicGoodPresenter() {}


}