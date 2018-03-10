package com.acchain.community.activity.wallet;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.BunkersCurrencyContract;
import com.acchain.community.presenter.BunkersCurrencyPresenter;
import com.acchain.community.util.Const;
import com.acchain.community.window.SelectTurnOutDialogFragment;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * function---- BunkersCurrencyActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/11 01:53:22 (+0000).
 */
@Route(path = ARouterConst.Activity_BunkersCurrencyActivity)
public class BunkersCurrencyActivity extends BaseActivity<BunkersCurrencyPresenter> implements BunkersCurrencyContract.View, SelectTurnOutDialogFragment.OnSelectTurnOutListener {
    /**
     * 燃料币
     */
    private static final String BUNKER=Const.TYPE_CHAIN_ACC;

    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.tv_bunkers)
    TextView mTvBunkers;
    @BindView(R.id.bt_take_in)
    Button mBtTakeIn;
    @BindView(R.id.bt_take_out)
    Button mBtTakeOut;
    @BindView(R.id.tv_info)
    TextView mTvInfo;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_bunkers_currency;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @OnClick({R.id.bt_take_in, R.id.bt_take_out, R.id.tv_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_take_in:
                ARouter.getInstance()
                        .build(ARouterConst.Activity_TakeInCurrencyDetailActivity)
                        .withString(Const.KEY_CURRENCY_CHAIN,BUNKER)
                        .withString(Const.KEY_CURRENCY_SHORT_NAME,BUNKER)
                        .navigation();
                break;
            case R.id.bt_take_out:
                new SelectTurnOutDialogFragment()
                        .setOnSelectTurnOutListener(this)
                        .show(getSupportFragmentManager(), "turn_out");
                break;
            case R.id.tv_info:
                ARouter.getInstance()
                        .build(ARouterConst.Activity_BunkersConsumeDeclareActivity)
                        .withString(Const.KEY_CURRENCY_SHORT_NAME,BUNKER)
                        .navigation();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_record, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ARouter.getInstance()
                .build(ARouterConst.Activity_BunkersFlowRecordActivity)
                .withString(Const.KEY_CURRENCY_SHORT_NAME,BUNKER)
                .navigation();
        return true;
    }

    @Override
    public void onSelectFriends() {
        ARouter.getInstance()
                .build(ARouterConst.Activity_TakeOutSelectFriendActivity)
                .withString(Const.KEY_CURRENCY_SHORT_NAME,BUNKER)
                .navigation();
    }

    @Override
    public void onSelectPlatform() {
        ARouter.getInstance()
                .build(ARouterConst.Activity_TakeOutPlatformActivity)
                .withString(Const.KEY_CURRENCY_SHORT_NAME,BUNKER)
                .navigation();
    }
}