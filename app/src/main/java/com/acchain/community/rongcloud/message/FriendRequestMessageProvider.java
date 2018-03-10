package com.acchain.community.rongcloud.message;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;
import android.view.ViewGroup;

import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.provider.IContainerItemProvider;
import io.rong.imlib.model.MessageContent;

/**
 * Created by rsp on 2018/1/22.
 */
@ProviderTag(messageContent = FriendRequestMessage.class)
public class FriendRequestMessageProvider extends IContainerItemProvider.MessageProvider {
    @Override
    public void bindView(View view, int i, MessageContent messageContent, UIMessage uiMessage) {

    }

    @Override
    public Spannable getContentSummary(MessageContent messageContent) {
        FriendRequestMessage friendRequestMessage = (FriendRequestMessage) messageContent;
        return new SpannableString(friendRequestMessage.getName()+friendRequestMessage.getMessage());
    }

    @Override
    public void onItemClick(View view, int i, MessageContent messageContent, UIMessage uiMessage) {

    }

    @Override
    public View newView(Context context, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public void bindView(View view, int i, Object o) {

    }
}
