package com.acchain.community.presenter;


import com.acchain.community.activity.person.EditProfessionCardActivity;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.EditProfessionCardContract;

import javax.inject.Inject;

/**
 * function---- EditProfessionCardPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/17 10:02:42 (+0000).
 */
public class EditProfessionCardPresenter extends BasePresenter<EditProfessionCardActivity> implements EditProfessionCardContract.Presenter{
    @Inject
    public EditProfessionCardPresenter() {}


    @Override
    public void setBusinessCard(String token, String position, String companyName, String mainBusiness, String phone, String email, String address, String area, String introduction) {
        requestHttp(httpInterface.setBusinessCard(token, position, companyName, mainBusiness, phone,email, address, area,introduction),
                objectBaseHttpBean -> mView.setBusinessCardFinish());
    }
}