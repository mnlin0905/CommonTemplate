package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.RedPacketDetailContract;
import com.acchain.community.activity.friend.RedPacketDetailActivity;

import javax.inject.Inject;

/**
 * function---- RedPacketDetailPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/19 08:58:11 (+0000).
 */
public class RedPacketDetailPresenter extends BasePresenter<RedPacketDetailActivity> implements RedPacketDetailContract.Presenter{
    @Inject
    public RedPacketDetailPresenter() {}

}