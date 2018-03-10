package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.ShareCircleContract;
import com.acchain.community.activity.friend.ShareCircleActivity;

import javax.inject.Inject;

/**
 * function---- ShareCirclePresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/12 09:29:46 (+0000).
 */
public class ShareCirclePresenter extends BasePresenter<ShareCircleActivity> implements ShareCircleContract.Presenter{
    @Inject
    public ShareCirclePresenter() {}

}