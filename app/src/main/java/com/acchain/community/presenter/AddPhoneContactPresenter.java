package com.acchain.community.presenter;


import com.acchain.community.activity.friend.AddPhoneContactActivity;
import com.acchain.community.base.BaseHttpBean;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.bean.PhoneContactResult;
import com.acchain.community.contract.AddPhoneContactContract;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.util.HttpListener;

import java.util.List;

import javax.inject.Inject;

/**
 * function---- AddPhoneContactPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/23 12:15:28 (+0000).
 */
public class AddPhoneContactPresenter extends BasePresenter<AddPhoneContactActivity> implements AddPhoneContactContract.Presenter{
    @Inject
    public AddPhoneContactPresenter() {}

    @Override
    public void getRegisterContact() {
        requestHttp(httpInterface.phoneList(DefaultPreferenceUtil.getInstance().getToken(), mView.phoneNumbers()), new HttpListener<BaseHttpBean<List<PhoneContactResult>>>() {
            @Override
            public void onSuccess(BaseHttpBean<List<PhoneContactResult>> response) {
                    mView.showContact(response.dataSet);
            }
        });
    }
}