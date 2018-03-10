package com.acchain.community.presenter;


import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.NegotiationHistoryContract;
import com.acchain.community.activity.person.NegotiationHistoryActivity;

import javax.inject.Inject;

/**
 * function---- NegotiationHistoryPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/02/03 08:58:16 (+0000).
 */
public class NegotiationHistoryPresenter extends BasePresenter<NegotiationHistoryActivity> implements NegotiationHistoryContract.Presenter{
    @Inject
    public NegotiationHistoryPresenter() {}

}