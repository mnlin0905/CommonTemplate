package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.ChatDetailContract;
import com.acchain.community.activity.friend.ChatDetailActivity;

import javax.inject.Inject;

/**
 * function---- ChatDetailPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/23 14:33:45 (+0000).
 */
public class ChatDetailPresenter extends BasePresenter<ChatDetailActivity> implements ChatDetailContract.Presenter{
    @Inject
    public ChatDetailPresenter() {}

}