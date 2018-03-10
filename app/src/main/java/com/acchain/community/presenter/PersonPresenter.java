package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.PersonContract;
import com.acchain.community.fragment.PersonFragment;

import javax.inject.Inject;

/**
 * function---- PersonPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2017/12/20 10:49:35 (+0000).
 */
public class PersonPresenter extends BasePresenter<PersonFragment> implements PersonContract.Presenter {
    @Inject
    public PersonPresenter() {
    }
}