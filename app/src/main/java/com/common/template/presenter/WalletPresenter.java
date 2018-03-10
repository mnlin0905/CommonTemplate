package com.common.template.presenter;


import com.common.template.base.BasePresenter;
import com.common.template.contract.WalletContract;
import com.common.template.fragment.WalletFragment;

import javax.inject.Inject;

/**
 * function---- WalletPresenter
 * <p>
 * Created(Gradle default create) by MNLIN on 2017/12/20 10:48:55 (+0000).
 */
public class WalletPresenter extends BasePresenter<WalletFragment> implements WalletContract.Presenter {
    @Inject
    public WalletPresenter() {
    }

}