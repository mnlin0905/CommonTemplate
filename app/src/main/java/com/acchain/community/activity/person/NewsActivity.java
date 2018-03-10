package com.acchain.community.activity.person;

import android.os.Bundle;
import android.view.View;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.NewsContract;
import com.acchain.community.presenter.NewsPresenter;
import com.acchain.community.view.NewsConstraintLayout;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * function---- NewsActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/22 13:43:05 (+0000).
 */
@Route(path = ARouterConst.Activity_NewsActivity)
public class NewsActivity extends BaseActivity<NewsPresenter> implements NewsContract.View {

    @BindView(R.id.nll_notification)
    NewsConstraintLayout mNllNotification;
    @BindView(R.id.nll_logistics)
    NewsConstraintLayout mNllLogistics;
    @BindView(R.id.nll_wallet)
    NewsConstraintLayout mNllWallet;
    @BindView(R.id.nll_online_chat)
    NewsConstraintLayout mNllOnlineChat;
    @BindView(R.id.nll_online_service)
    NewsConstraintLayout mNllOnlineService;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_news;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mNllNotification.setBadge(5, dispatchGetColor(R.color.white_selected_1), dispatchGetColor(R.color.blue_background_7d));
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @OnClick({R.id.nll_notification, R.id.nll_logistics, R.id.nll_wallet, R.id.nll_online_chat, R.id.nll_online_service})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.nll_notification:
                break;
            case R.id.nll_logistics:
                break;
            case R.id.nll_wallet:
                break;
            case R.id.nll_online_chat:
                break;
            case R.id.nll_online_service:
                break;
        }

        // TODO: 2018/1/23 进入消息列表中
        ARouter.getInstance()
                .build(ARouterConst.Activity_NewsListActivity)
                .navigation();
    }
}