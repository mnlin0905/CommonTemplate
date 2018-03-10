package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.FriendAddContract;
import com.acchain.community.activity.friend.FriendAddActivity;

import javax.inject.Inject;

/**
 * function---- FriendAddPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/23 02:13:29 (+0000).
 */
public class FriendAddPresenter extends BasePresenter<FriendAddActivity> implements FriendAddContract.Presenter{
    @Inject
    public FriendAddPresenter() {}

}