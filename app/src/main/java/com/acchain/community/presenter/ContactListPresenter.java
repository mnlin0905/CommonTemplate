package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.ContactListContract;
import com.acchain.community.fragment.ContactListFragment;

import javax.inject.Inject;

/**
 * function---- ContactListPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/16 02:18:23 (+0000).
 */
public class ContactListPresenter extends BasePresenter<ContactListFragment> implements ContactListContract.Presenter{
    @Inject
    public ContactListPresenter() {}

}