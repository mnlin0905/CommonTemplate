package com.acchain.community.presenter;


import com.acchain.community.activity.person.ChangeDeliveryAddressActivity;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.ChangeDeliveryAddressContract;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * function---- ChangeDeliveryAddressPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/15 12:42:34 (+0000).
 */
public class ChangeDeliveryAddressPresenter extends BasePresenter<ChangeDeliveryAddressActivity> implements ChangeDeliveryAddressContract.Presenter {
    @Inject
    public ChangeDeliveryAddressPresenter() {
    }

    @Override
    public void getUserAddress(String token, int page, int pageSize) {
        requestHttp(httpInterface.getUserAddress(token, page, pageSize),
                listBaseHttpBean -> mView.getUserAddressFinish(listBaseHttpBean.dataSet),
                (Consumer<Throwable>) throwable -> mView.getUserAddressFinish(null));
    }

}