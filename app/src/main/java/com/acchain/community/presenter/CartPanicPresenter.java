package com.acchain.community.presenter;


import com.acchain.community.base.BaseHttpBean;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.bean.CartPanicGoods;
import com.acchain.community.contract.CartPanicContract;
import com.acchain.community.fragment.CartPanicFragment;
import com.acchain.community.util.HttpListener;
import com.acchain.community.util.HTTPException;
import com.acchain.community.util.L;

import javax.inject.Inject;

/**
 * function---- CartPanicPresenter
 * <p>
 * Created(Gradle default create) by MNLIN on 2018/01/11 09:55:51 (+0000).
 */
public class CartPanicPresenter extends BasePresenter<CartPanicFragment> implements CartPanicContract.Presenter{
    @Inject
    public CartPanicPresenter() {}

    @Override
    public void loadPanicCart(int cartType, String token) {
        L.i("loadPanicCart");
        requestHttp(httpInterface.loadPanicCart(cartType, token), new HttpListener<BaseHttpBean<CartPanicGoods>>() {
            @Override
            public void onSuccess(BaseHttpBean<CartPanicGoods> response) {
                mView.onLoadPanicCartFinish(response.dataSet);
            }

            @Override
            public void onFailed(Throwable e) {
                super.onFailed(e);
                e.printStackTrace();
                mView.onLoadPanicCartFinish(null);
            }

            @Override
            public void onFailed(HTTPException e) {
                super.onFailed(e);
                mView.onLoadPanicCartFinish(null);
            }
        });
    }
}