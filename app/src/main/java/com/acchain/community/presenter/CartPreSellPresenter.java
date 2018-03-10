package com.acchain.community.presenter;


import com.acchain.community.base.BaseHttpBean;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.bean.CartPreGoods;
import com.acchain.community.contract.CartPreSellContract;
import com.acchain.community.fragment.CartPreSellFragment;
import com.acchain.community.util.HttpListener;
import com.acchain.community.util.HTTPException;

import javax.inject.Inject;

/**
 * function---- CartPreSellPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/02/06 01:50:13 (+0000).
 */
public class CartPreSellPresenter extends BasePresenter<CartPreSellFragment> implements CartPreSellContract.Presenter{
    @Inject
    public CartPreSellPresenter() {}

    @Override
    public void loadPreCart(int cartType, String token) {
        requestHttp(httpInterface.loadPreCart(cartType, token), new HttpListener<BaseHttpBean<CartPreGoods>>() {
            @Override
            public void onSuccess(BaseHttpBean<CartPreGoods> response) {
                mView.onLoadPreCartFinish(response.dataSet);
            }

            @Override
            public void onFailed(Throwable e) {
                super.onFailed(e);
                e.printStackTrace();
                mView.onLoadPreCartFinish(null);
            }

            @Override
            public void onFailed(HTTPException e) {
                super.onFailed(e);
                mView.onLoadPreCartFinish(null);
            }
        });
    }
}