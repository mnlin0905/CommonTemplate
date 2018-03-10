package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.ShoppingCartContract;
import com.acchain.community.fragment.ShoppingCartFragment;

import javax.inject.Inject;

/**
 * function---- ShoppingCartPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2017/12/25 06:20:29 (+0000).
 */
public class ShoppingCartPresenter extends BasePresenter<ShoppingCartFragment> implements ShoppingCartContract.Presenter{
    @Inject
    public ShoppingCartPresenter() {}

}