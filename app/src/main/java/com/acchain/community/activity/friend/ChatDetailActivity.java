package com.acchain.community.activity.friend;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.ChatDetailContract;
import com.acchain.community.presenter.ChatDetailPresenter;
import com.acchain.community.view.LineMenuView;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

import static com.acchain.community.util.Const.MODEL_USERINFO;

/**
 * 聊天详情
 * function---- ChatDetailActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/23 14:33:45 (+0000).
 */
@Route(path = ARouterConst.Activity_ChatDetailActivity)
public class ChatDetailActivity extends BaseActivity<ChatDetailPresenter> implements ChatDetailContract.View, LineMenuView.LineMenuListener {
    @Autowired(name = MODEL_USERINFO, required = true)
    UserInfo userInfo;
    @BindView(R.id.headImg)
    ImageView headImg;
    @BindView(R.id.nikeName)
    TextView nikeName;
    @BindView(R.id.chatTop)
    LineMenuView chatTop;
    @BindView(R.id.mdr)
    LineMenuView mdr;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_char_detail;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        nikeName.setText(userInfo.getName());
        Glide.with(this).load(userInfo.getPortraitUri()).apply(new RequestOptions().centerCrop()).into(headImg);
        RongIM.getInstance().getConversation(Conversation.ConversationType.PRIVATE, userInfo.getUserId(), new RongIMClient.ResultCallback<Conversation>() {
            @Override
            public void onSuccess(Conversation conversation) {
                chatTop.setTransition(conversation.isTop());
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });
        RongIM.getInstance().getConversationNotificationStatus(Conversation.ConversationType.PRIVATE, userInfo.getUserId(), new RongIMClient.ResultCallback<Conversation.ConversationNotificationStatus>() {
            @Override
            public void onSuccess(Conversation.ConversationNotificationStatus conversationNotificationStatus) {
                mdr.setTransition(conversationNotificationStatus.getValue() == Conversation.ConversationNotificationStatus.DO_NOT_DISTURB.getValue());
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });

    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @Override
    public boolean performClickLeft(TextView v) {
        int position = (int) v.getTag(R.id.LINE_MENU_VIEW_TAG_POSITION);
        return !(position == 0 || position == 1);
    }

    @Override
    public boolean performClickRight(TextView v) {
        return false;
    }

    @Override
    public void performSelf(LineMenuView v) {
        int position = (int) v.getTag(R.id.LINE_MENU_VIEW_TAG_POSITION);
        switch (position) {
            case 0:
                RongIM.getInstance().setConversationToTop(Conversation.ConversationType.PRIVATE, userInfo.getUserId(), !v.getTransition(), new RongIMClient.ResultCallback<Boolean>() {
                    @Override
                    public void onSuccess(Boolean aBoolean) {
                        v.setTransition(!v.getTransition());
                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {

                    }
                });
                break;
            case 1:
                RongIM.getInstance().setConversationNotificationStatus(Conversation.ConversationType.PRIVATE, userInfo.getUserId(), (!v.getTransition()) ? Conversation.ConversationNotificationStatus.DO_NOT_DISTURB : Conversation.ConversationNotificationStatus.NOTIFY, new RongIMClient.ResultCallback<Conversation.ConversationNotificationStatus>() {
                    @Override
                    public void onSuccess(Conversation.ConversationNotificationStatus conversationNotificationStatus) {
                        v.setTransition(!v.getTransition());
                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {

                    }
                });
                break;
            case 2:
                RongIM.getInstance().clearMessages(Conversation.ConversationType.PRIVATE, userInfo.getUserId(), new RongIMClient.ResultCallback<Boolean>() {
                    @Override
                    public void onSuccess(Boolean aBoolean) {
                        showToast("聊天记录已清空");
                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {

                    }
                });
                break;
            case 3:
                break;
        }

    }
}