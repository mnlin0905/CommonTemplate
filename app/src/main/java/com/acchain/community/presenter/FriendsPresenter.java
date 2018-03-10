package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.FriendsContract;
import com.acchain.community.fragment.FriendsFragment;

import javax.inject.Inject;

/**
 * function---- FriendsPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2017/12/20 10:49:19 (+0000).
 */
public class FriendsPresenter extends BasePresenter<FriendsFragment> implements FriendsContract.Presenter{
    @Inject
    public FriendsPresenter() {}

}