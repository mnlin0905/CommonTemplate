package com.acchain.community.activity.person;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.MessageSettingsContract;
import com.acchain.community.presenter.MessageSettingsPresenter;
import com.acchain.community.view.LineMenuView;
import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;

/**
 * function---- MessageSettingsActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/19 09:58:11 (+0000).
 */
@Route(path = ARouterConst.Activity_MessageSettingsActivity)
public class MessageSettingsActivity extends BaseActivity<MessageSettingsPresenter> implements MessageSettingsContract.View, LineMenuView.LineMenuListener {

    @BindView(R.id.lmv_message_alert)
    LineMenuView mLmvMessageAlert;
    @BindView(R.id.lmv_message_interactive)
    LineMenuView mLmvMessageInteractive;
    @BindView(R.id.lmv_receive_ask)
    LineMenuView mLmvReceiveAsk;
    @BindView(R.id.lmv_notification_detail)
    LineMenuView mLmvNotificationDetail;
    @BindView(R.id.lmv_remind_message)
    LineMenuView mLmvRemindMessage;
    @BindView(R.id.lmv_logistics_message)
    LineMenuView mLmvLogisticsMessage;
    @BindView(R.id.lmv_voice)
    LineMenuView mLmvVoice;
    @BindView(R.id.lmv_vibration)
    LineMenuView mLmvVibration;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_message_settings;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_service, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO: 2018/1/19 客服
        return true;
    }

    @Override
    public boolean performClickLeft(TextView v) {
        return false;
    }

    @Override
    public boolean performClickRight(TextView v) {
        return false;
    }

    @Override
    public void performSelf(LineMenuView v) {
        int position = (int) v.getTag(R.id.LINE_MENU_VIEW_TAG_POSITION);
        v.setTransition(!v.getTransition());
        switch (position) {
            case 0://消息提醒

                break;
            case 1://互动消息

                break;
            case 2://接受通知

                break;
            case 3://消息详情

                break;
            case 4://通知消息

                break;
            case 5://物流消息

                break;
            case 6://声音

                break;
            case 7://震动

                break;
        }
    }
}