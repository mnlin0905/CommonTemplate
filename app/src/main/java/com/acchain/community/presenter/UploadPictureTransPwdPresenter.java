package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.UploadPictureTransPwdContract;
import com.acchain.community.activity.person.UploadPictureTransPwdActivity;

import javax.inject.Inject;

/**
 * function---- UploadPictureTransPwdPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/31 11:46:08 (+0000).
 */
public class UploadPictureTransPwdPresenter extends BasePresenter<UploadPictureTransPwdActivity> implements UploadPictureTransPwdContract.Presenter{
    @Inject
    public UploadPictureTransPwdPresenter() {}


    @Override
    public void resetPayPwdArtificial(String token, String mobile, String positive, String back, String handheld, int cardType) {
        requestHttp(httpInterface.resetPayPwdArtificial(token, mobile, positive, back, handheld, cardType), objectBaseHttpBean -> mView.resetPayPwdArtificialFinish());
    }
}