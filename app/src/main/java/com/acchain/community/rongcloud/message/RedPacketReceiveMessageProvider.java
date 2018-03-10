package com.acchain.community.rongcloud.message;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.acchain.community.R;

import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.provider.IContainerItemProvider;
import io.rong.imlib.model.MessageContent;

/**
 * Created by rsp on 2018/2/1.
 */
@ProviderTag(messageContent = RedPacketReceiveMessage.class, centerInHorizontal = true, showPortrait = false, showSummaryWithName = false)
public class RedPacketReceiveMessageProvider extends IContainerItemProvider.MessageProvider {
    @Override
    public void bindView(View view, int i, MessageContent messageContent, UIMessage uiMessage) {
        RedPacketReceiveMessage redPacketReceiveMessage = (RedPacketReceiveMessage) messageContent;
        TextView message = (TextView) view;
        message.setText(redPacketReceiveMessage.getMessage());
    }

    @Override
    public Spannable getContentSummary(MessageContent messageContent) {
        RedPacketReceiveMessage message = (RedPacketReceiveMessage) messageContent;
        return new SpannableString(message.getMessage());
    }

    @Override
    public void onItemClick(View view, int i, MessageContent messageContent, UIMessage uiMessage) {

    }

    @Override
    public View newView(Context context, ViewGroup viewGroup) {
        TextView message = (TextView) View.inflate(context, R.layout.item_red_packet_recever, null);
        return message;
    }

}
