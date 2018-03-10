package com.acchain.community.rongcloud.message;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.bean.Friend;
import com.acchain.community.manager.FriendManager;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rong.imkit.RongIM;
import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.provider.IContainerItemProvider;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;

import static com.acchain.community.util.Const.KEY_HEAD_IMG;
import static com.acchain.community.util.Const.KEY_NIKE_NAME;
import static com.acchain.community.util.Const.KEY_TARGET_FRIEND_ID;
import static com.acchain.community.util.Const.KEY_TYPE_ADD_FRIEND_FROM;
import static com.acchain.community.util.Const.TYPE_ADD_FRIEND_FROM_SHARE;

/**
 * Created by rsp on 2018/1/17.
 */
@ProviderTag(messageContent = CardMessage.class)
public class CardMessageProvider extends IContainerItemProvider.MessageProvider {

    @Override
    public void bindView(View view, int i, MessageContent messageContent, UIMessage uiMessage) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        CardMessage cardMessage = (CardMessage) messageContent;
        viewHolder.name.setText(cardMessage.getNikeName());
        Glide.with(view).load(cardMessage.getHeadImg()).apply(new RequestOptions().centerCrop()).into(viewHolder.headImg);
        if (uiMessage.getMessageDirection() == Message.MessageDirection.SEND) {
            //自己发送的
            view.setBackgroundResource(R.drawable.card_sender);
        } else {
            view.setBackgroundResource(R.drawable.card_recever);
        }
    }

    @Override
    public Spannable getContentSummary(MessageContent messageContent) {
        return new SpannableString("[名片]");
    }

    @Override
    public void onItemClick(View view, int i, MessageContent messageContent, UIMessage uiMessage) {
        CardMessage cardMessage = (CardMessage) messageContent;
        if (uiMessage.getMessageDirection() == Message.MessageDirection.RECEIVE) {
            String userId = cardMessage.getUserId();
            Friend friend = FriendManager.getInstance().findFriendById(userId);
            if (friend != null) {
                RongIM.getInstance().startPrivateChat(view.getContext(), userId, friend.getName());
            } else {
                ARouter.getInstance()
                        .build(ARouterConst.Activity_FriendAddActivity)
                        .withString(KEY_TYPE_ADD_FRIEND_FROM, TYPE_ADD_FRIEND_FROM_SHARE)
                        .withString(KEY_NIKE_NAME, cardMessage.getNikeName())
                        .withString(KEY_TARGET_FRIEND_ID, cardMessage.getUserId())
                        .withString(KEY_HEAD_IMG, cardMessage.getHeadImg())
                        .navigation();
            }
        }

    }

    @Override
    public View newView(Context context, ViewGroup viewGroup) {
        View inflate = View.inflate(context, R.layout.item_message_card, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        inflate.setTag(viewHolder);
        return inflate;
    }

    static class ViewHolder {
        @BindView(R.id.headImg)
        ImageView headImg;
        @BindView(R.id.name)
        TextView name;

        public ViewHolder(View v) {
            ButterKnife.bind(this, v);
        }
    }
}
