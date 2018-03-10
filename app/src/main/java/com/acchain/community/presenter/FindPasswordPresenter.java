package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.FindPasswordContract;
import com.acchain.community.activity.person.FindPasswordActivity;

import javax.inject.Inject;

/**
 * function---- FindPasswordPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/19 02:31:05 (+0000).
 */
public class FindPasswordPresenter extends BasePresenter<FindPasswordActivity> implements FindPasswordContract.Presenter{
    @Inject
    public FindPasswordPresenter() {}
}