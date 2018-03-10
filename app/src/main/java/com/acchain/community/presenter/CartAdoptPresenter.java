package com.acchain.community.presenter;


import com.acchain.community.base.BaseHttpBean;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.bean.CartAdoptGoods;
import com.acchain.community.contract.CartAdoptContract;
import com.acchain.community.fragment.CartAdoptFragment;
import com.acchain.community.util.HttpListener;
import com.acchain.community.util.HTTPException;

import javax.inject.Inject;

/**
 * function---- CartAdoptPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/02/24 02:40:51 (+0000).
 */
public class CartAdoptPresenter extends BasePresenter<CartAdoptFragment> implements CartAdoptContract.Presenter{
    @Inject
    public CartAdoptPresenter() {}

    @Override
    public void loadAdoptCart(int cartType, String token) {
        requestHttp(httpInterface.loadAdoptCart(cartType, token), new HttpListener<BaseHttpBean<CartAdoptGoods>>() {
            @Override
            public void onSuccess(BaseHttpBean<CartAdoptGoods> response) {
                mView.onLoadAdoptCartFinish(response.dataSet);
            }

            @Override
            public void onFailed(Throwable e) {
                super.onFailed(e);
                e.printStackTrace();
                mView.onLoadAdoptCartFinish(null);
            }

            @Override
            public void onFailed(HTTPException e) {
                super.onFailed(e);
                mView.onLoadAdoptCartFinish(null);
            }
        });
    }
}