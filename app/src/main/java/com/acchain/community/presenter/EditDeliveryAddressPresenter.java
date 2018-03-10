package com.acchain.community.presenter;


import com.acchain.community.activity.person.EditDeliveryAddressActivity;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.EditDeliveryAddressContract;

import javax.inject.Inject;

/**
 * function---- EditDeliveryAddressPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/16 11:23:45 (+0000).
 */
public class EditDeliveryAddressPresenter extends BasePresenter<EditDeliveryAddressActivity> implements EditDeliveryAddressContract.Presenter {
    @Inject
    public EditDeliveryAddressPresenter() {
    }

    @Override
    public void addAddress(String token, String name, String mobile, String province, String city, String area, String address,int defaultStatus) {
        requestHttp(httpInterface.addAddress(token, name, mobile, province, city, area, address,defaultStatus), objectBaseHttpBean -> mView.addAddressFinish());
    }
}