package com.acchain.community.rongcloud;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.bean.ContactAddRequest;
import com.acchain.community.bean.Friend;
import com.acchain.community.events.ContactRequestEvent;
import com.acchain.community.events.RefreshFriendList;
import com.acchain.community.events.RefreshRedPacketMessage;
import com.acchain.community.manager.FriendManager;
import com.acchain.community.rongcloud.message.CardMessage;
import com.acchain.community.rongcloud.message.CardMessageProvider;
import com.acchain.community.rongcloud.message.FriendRequestMessage;
import com.acchain.community.rongcloud.message.FriendRequestMessageProvider;
import com.acchain.community.rongcloud.message.RedPacketMessage;
import com.acchain.community.rongcloud.message.RedPacketMessageProvider;
import com.acchain.community.rongcloud.message.RedPacketReceiveMessage;
import com.acchain.community.rongcloud.message.RedPacketReceiveMessageProvider;
import com.acchain.community.rongcloud.message.TranferMessage;
import com.acchain.community.rongcloud.message.TranferMessageProvider;
import com.acchain.community.rongcloud.plugin.MyExtensionModule;
import com.acchain.community.rxbus.RxBus;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.StringUtils;
import com.orhanobut.logger.Logger;

import org.litepal.crud.DataSupport;

import java.util.List;

import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.IExtensionModule;
import io.rong.imkit.RongExtensionManager;
import io.rong.imkit.RongIM;
import io.rong.imkit.model.UIConversation;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;
import io.rong.message.TextMessage;

import static com.acchain.community.util.Const.MODEL_USERINFO;

/**
 * @author 小任
 * @date 2018/1/5
 * version 1.0
 * 描述:
 */

public class RongImConfig {
    private Context context;

    public static void init(Context context) {
        context = context.getApplicationContext();
        RongIM.setConversationListBehaviorListener(new RongIM.ConversationListBehaviorListener() {
            @Override
            public boolean onConversationPortraitClick(Context context, Conversation.ConversationType conversationType, String s) {
                if (conversationType == Conversation.ConversationType.SYSTEM) {
                    //判断是不是添加好友信息
                    Toast.makeText(context, "点击了好友请求,应该要调转到好友请求列表页", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }

            @Override
            public boolean onConversationPortraitLongClick(Context context, Conversation.ConversationType conversationType, String s) {
                return false;
            }

            @Override
            public boolean onConversationLongClick(Context context, View view, UIConversation uiConversation) {
                return false;
            }

            @Override
            public boolean onConversationClick(Context context, View view, UIConversation uiConversation) {
                MessageContent messageContent = uiConversation.getMessageContent();
                if (messageContent instanceof FriendRequestMessage) {
                    //判断是不是添加好友信息
                    Toast.makeText(context, "点击了好友请求,应该要调转到好友请求列表页", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });
        RongIM.getInstance().enableNewComingMessageIcon(true);
        RongIM.getInstance().enableUnreadMessageIcon(true);
        setMyExtensionModule();
        RongIM.getInstance().setSendMessageListener(new RongIM.OnSendMessageListener() {
            @Override
            public Message onSend(Message message) {
                return message;
            }

            @Override
            public boolean onSent(Message message, RongIM.SentMessageErrorCode sentMessageErrorCode) {
                return false;
            }
        });
        //注册自定义消息
        RongIM.registerMessageType(RedPacketMessage.class);
        RongIM.registerMessageType(CardMessage.class);
        RongIM.registerMessageType(FriendRequestMessage.class);
        RongIM.registerMessageType(RedPacketReceiveMessage.class);
        RongIM.registerMessageType(TranferMessage.class);
        //注册自定义消息模版
        RongIM.registerMessageTemplate(new RedPacketMessageProvider());
        RongIM.registerMessageTemplate(new CardMessageProvider());
        RongIM.registerMessageTemplate(new FriendRequestMessageProvider());
        RongIM.registerMessageTemplate(new RedPacketReceiveMessageProvider());
        RongIM.registerMessageTemplate(new TranferMessageProvider());
        RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {

            private UserInfo userInfo;

            @Override
            public UserInfo getUserInfo(String userId) {
                if (userId.equals(DefaultPreferenceUtil.getInstance().getMemberId())) {
                    String nickname = BasePresenter.singleAccountBean.getNickname();
                    String imgSrc = BasePresenter.singleAccountBean.getImgSrc();
                    userInfo = new UserInfo(userId, nickname, Uri.parse(StringUtils.isEmpty(imgSrc) ? "http://....." : imgSrc));
                } else {
                    Friend friend = FriendManager.getInstance().findFriendById(userId);
                    if (friend != null) {
                        userInfo = friend.getUserInfo();
                    } else {
                        userInfo = null;
                    }
                }
                if (userInfo != null) {
                    RongIM.getInstance().refreshUserInfoCache(userInfo);
                }
                return userInfo;
            }
        }, true);
        RongIM.setOnReceiveMessageListener((message, i) -> {
            Logger.d("收到消息:" + message);
            if (message.getContent() instanceof FriendRequestMessage) {
                //收到好友请求信息
                FriendRequestMessage friendRequestMessage = (FriendRequestMessage) message.getContent();
                ContactAddRequest contactAddRequest = friendRequestMessage.getContactAddRequest();
                contactAddRequest.setUpdateTime(System.currentTimeMillis());
                if (contactAddRequest.getOperation().equals(ContactAddRequest.CONTACT_OPERATION_ACCEPT_RESPONSE)) {
                    //同意添加
                    List<ContactAddRequest> contactAddRequests = DataSupport.where("requestId = ?", contactAddRequest.getRequestId()).find(ContactAddRequest.class);
                    if (contactAddRequests != null && !contactAddRequests.isEmpty()) {
                        ContactAddRequest contactAddRequest1 = contactAddRequests.get(0);
                        contactAddRequest.setMessage(contactAddRequest1.getMessage());
                    }
                }
                contactAddRequest.saveOrUpdate("requestId = ?", contactAddRequest.getRequestId());
                RxBus.getInstance().post(new ContactRequestEvent());
            } else if (message.getContent() instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message.getContent();
                String extra = textMessage.getExtra();
                if ("newFriend".equals(extra)) {
                    RxBus.getInstance().post(new RefreshFriendList());
                }
            }else if(message.getContent() instanceof RedPacketReceiveMessage){
                RedPacketReceiveMessage redPacketReceiveMessage = (RedPacketReceiveMessage) message.getContent();
                String envelopesId = redPacketReceiveMessage.getEnvelopesId();
                RongIM.getInstance().getHistoryMessages(Conversation.ConversationType.PRIVATE, message.getTargetId(), "WM:RedPacket", -1, Integer.MAX_VALUE, new RongIMClient.ResultCallback<List<Message>>() {
                    @Override
                    public void onSuccess(List<Message> messages) {
                        for (Message message1 : messages) {
                            if (message1.getMessageDirection() == Message.MessageDirection.SEND) {
                                RedPacketMessage content = (RedPacketMessage) message1.getContent();
                                if (content.getRedPacketId().equals(envelopesId)) {
                                    RongIM.getInstance().setMessageExtra(message1.getMessageId(), "已领取", new RongIMClient.ResultCallback<Boolean>() {
                                        @Override
                                        public void onSuccess(Boolean aBoolean) {
                                            //TODO 这里需要主动刷新聊天界面
                                            RxBus.getInstance().post(new RefreshRedPacketMessage(envelopesId));
                                        }

                                        @Override
                                        public void onError(RongIMClient.ErrorCode errorCode) {

                                        }
                                    });
                                    break;
                                }
                            }

                        }
                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {

                    }
                });
            }
            return false;
        });
        /**
         * 设置会话界面操作的监听器。
         */
        RongIM.setConversationBehaviorListener(new RongIM.ConversationBehaviorListener() {
            @Override
            public boolean onUserPortraitClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo) {
                String userId = userInfo.getUserId();
                if (userId.equals(DefaultPreferenceUtil.getInstance().getMemberId())) {
                    return false;
                } else {
                    ARouter.getInstance().build(ARouterConst.Activity_ChatDetailActivity).withParcelable(MODEL_USERINFO,userInfo).navigation();
                }
                return true;
            }

            @Override
            public boolean onUserPortraitLongClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo) {
                return false;
            }

            @Override
            public boolean onMessageClick(Context context, View view, Message message) {
                return false;
            }

            @Override
            public boolean onMessageLinkClick(Context context, String s) {
                return false;
            }

            @Override
            public boolean onMessageLongClick(Context context, View view, Message message) {
                return false;
            }
        });
    }

    public static void setMyExtensionModule() {
        List<IExtensionModule> moduleList = RongExtensionManager.getInstance().getExtensionModules();
        IExtensionModule defaultModule = null;
        if (moduleList != null) {
            for (IExtensionModule module : moduleList) {
                if (module instanceof DefaultExtensionModule) {
                    defaultModule = module;
                    break;
                }
            }
            if (defaultModule != null) {
                RongExtensionManager.getInstance().unregisterExtensionModule(defaultModule);
                RongExtensionManager.getInstance().registerExtensionModule(new MyExtensionModule());
            }
        }
    }
}
