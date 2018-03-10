package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.ContactContract;
import com.acchain.community.activity.friend.ContactActivity;

import javax.inject.Inject;

/**
 * function---- ContactPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/15 11:11:19 (+0000).
 */
public class ContactPresenter extends BasePresenter<ContactActivity> implements ContactContract.Presenter{
    @Inject
    public ContactPresenter() {}

}