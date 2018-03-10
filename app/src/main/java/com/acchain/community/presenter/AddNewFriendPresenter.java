package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.AddNewFriendContract;
import com.acchain.community.activity.friend.AddNewFriendActivity;

import javax.inject.Inject;

/**
 * function---- AddNewFriendPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/16 06:45:02 (+0000).
 */
public class AddNewFriendPresenter extends BasePresenter<AddNewFriendActivity> implements AddNewFriendContract.Presenter{
    @Inject
    public AddNewFriendPresenter() {}

}