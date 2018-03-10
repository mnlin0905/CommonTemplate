package com.acchain.community.presenter;


import com.acchain.community.activity.person.PersonInformationActivity;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.PersonInformationContract;
import com.acchain.community.util.DefaultPreferenceUtil;

import java.io.File;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * function---- PersonInformationPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/15 07:20:03 (+0000).
 */
public class PersonInformationPresenter extends BasePresenter<PersonInformationActivity> implements PersonInformationContract.Presenter {
    @Inject
    public PersonInformationPresenter() {
    }

    @Override
    public void setPhoto(File imgFile) {
        final MultipartBody mBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("img", System.currentTimeMillis() + ".jpg", RequestBody.create(MediaType.parse("application/octet-stream"), imgFile))
                .addFormDataPart("token", DefaultPreferenceUtil.getInstance().getToken() == null ? "" : DefaultPreferenceUtil.getInstance().getToken())
                .build();
        requestHttp(httpInterface.setPhoto(mBody), o -> mView.setPhotoFinish());
    }

    public void setPhoto(byte[] imgFile) {
        final MultipartBody mBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("img", System.currentTimeMillis() + ".jpg", RequestBody.create(MediaType.parse("application/octet-stream"), imgFile))
                .addFormDataPart("token", DefaultPreferenceUtil.getInstance().getToken() == null ? "" : DefaultPreferenceUtil.getInstance().getToken())
                .build();
        requestHttp(httpInterface.setPhoto(mBody), o -> mView.setPhotoFinish());
    }
}