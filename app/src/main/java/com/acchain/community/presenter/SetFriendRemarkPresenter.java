package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.SetFriendRemarkContract;
import com.acchain.community.activity.friend.SetFriendRemarkActivity;

import javax.inject.Inject;

/**
 * function---- SetFriendRemarkPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/23 06:42:14 (+0000).
 */
public class SetFriendRemarkPresenter extends BasePresenter<SetFriendRemarkActivity> implements SetFriendRemarkContract.Presenter{
    @Inject
    public SetFriendRemarkPresenter() {}

}