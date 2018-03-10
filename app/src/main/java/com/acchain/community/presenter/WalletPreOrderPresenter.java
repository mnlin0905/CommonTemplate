package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.WalletPreOrderContract;
import com.acchain.community.fragment.WalletPreOrderFragment;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * function---- WalletPreOrderPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/03 06:51:00 (+0000).
 */
public class WalletPreOrderPresenter extends BasePresenter<WalletPreOrderFragment> implements WalletPreOrderContract.Presenter {
    @Inject
    public WalletPreOrderPresenter() {
    }

    /**
     * 1.2.1  钱包预购类
     *
     * @param token 登录标志
     */
    @Override
    public void presellList(String token) {
        requestHttp(httpInterface.presellList(token),
                listBaseHttpBean -> mView.presellListFinish(listBaseHttpBean.dataSet),
                (Consumer<Throwable>) throwable -> mView.presellListFinish(null));
    }
}