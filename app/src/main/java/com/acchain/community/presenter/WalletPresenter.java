package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.WalletContract;
import com.acchain.community.fragment.WalletFragment;

import javax.inject.Inject;

/**
 * function---- WalletPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2017/12/20 10:48:55 (+0000).
 */
public class WalletPresenter extends BasePresenter<WalletFragment> implements WalletContract.Presenter {
    @Inject
    public WalletPresenter() {
    }

}