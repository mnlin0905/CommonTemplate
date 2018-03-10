package com.acchain.community.presenter;


import com.acchain.community.activity.person.BeginCertificationActivity;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.BeginCertificationContract;
import com.acchain.community.util.DefaultPreferenceUtil;

import java.io.File;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * function---- BeginCertificationPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/17 03:18:45 (+0000).
 */
public class BeginCertificationPresenter extends BasePresenter<BeginCertificationActivity> implements BeginCertificationContract.Presenter {
    @Inject
    public BeginCertificationPresenter() {
    }

    @Override
    public void verified(String token, String country, String name, int cardType, String idNo, String mobile, String code, String positive, String contrary, String hord) {
        final MultipartBody mBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("token", DefaultPreferenceUtil.getInstance().getToken() == null ? "" : DefaultPreferenceUtil.getInstance().getToken())
                .addFormDataPart("country", country)
                .addFormDataPart("name", name)
                .addFormDataPart("cardType", String.valueOf(cardType))
                .addFormDataPart("idNo", idNo)
                .addFormDataPart("mobile", mobile)
                .addFormDataPart("code", code)
                .addFormDataPart("positive", System.currentTimeMillis() + "positive.jpg", RequestBody.create(MediaType.parse("application/octet-stream"), new File(positive)))
                .addFormDataPart("contrary", System.currentTimeMillis() + "contrary.jpg", RequestBody.create(MediaType.parse("application/octet-stream"), new File(contrary)))
                .addFormDataPart("hord", System.currentTimeMillis() + "hord.jpg", RequestBody.create(MediaType.parse("application/octet-stream"), new File(hord)))
                .build();

        requestHttp("实名认证...",httpInterface.verified(mBody),
                objectBaseHttpBean -> mView.verifiedFinish());
    }
}