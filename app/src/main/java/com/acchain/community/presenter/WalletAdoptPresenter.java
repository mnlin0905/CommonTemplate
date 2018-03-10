package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.WalletAdoptContract;
import com.acchain.community.fragment.WalletAdoptFragment;

import javax.inject.Inject;

/**
 * function---- WalletAdoptPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/03 06:51:20 (+0000).
 */
public class WalletAdoptPresenter extends BasePresenter<WalletAdoptFragment> implements WalletAdoptContract.Presenter{
    @Inject
    public WalletAdoptPresenter() {}

    /**
     * 1.2.2  钱包领养类
     *
     * @param token 登录标志
     */
    @Override
    public void adoptList(String token) {
        requestHttp(httpInterface.adoptList(token),
                listBaseHttpBean -> mView.adoptListFinish(listBaseHttpBean.dataSet),
                throwable -> mView.adoptListFinish(null));
    }
}