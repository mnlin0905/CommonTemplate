package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.MessageSettingsContract;
import com.acchain.community.activity.person.MessageSettingsActivity;

import javax.inject.Inject;

/**
 * function---- MessageSettingsPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/19 09:58:11 (+0000).
 */
public class MessageSettingsPresenter extends BasePresenter<MessageSettingsActivity> implements MessageSettingsContract.Presenter{
    @Inject
    public MessageSettingsPresenter() {}

}