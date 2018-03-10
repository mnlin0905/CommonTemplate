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
import com.acchain.community.rxbus.RxBus;
import com.acchain.community.window.OpenRedPacketDialog;
import com.acchain.community.window.RedPacketExpiredDialog;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.StringUtils;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.provider.IContainerItemProvider;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;

import static com.acchain.community.util.Const.KEY_RED_PACKAGE_ID;

/**
 * Created by rsp on 2018/1/16.
 */
@ProviderTag(messageContent = RedPacketMessage.class)
public class RedPacketMessageProvider extends IContainerItemProvider.MessageProvider {


    @Override
    public void bindView(View view, int i, MessageContent messageContent, UIMessage uiMessage) {
        ViewHolder holder = (ViewHolder) view.getTag();
        RedPacketMessage redPacketMessage = (RedPacketMessage) messageContent;
        if (uiMessage.getMessageDirection() == Message.MessageDirection.SEND) {//消息方向，自己发送的
            if (!StringUtils.isEmpty(uiMessage.getExtra())) {
                view.setBackgroundResource(R.drawable.red_pack_sender_yes);
                holder.title.setText("红包已被领完");
                holder.redIcon.setImageResource(R.drawable.red_p2);
            } else {
                holder.title.setText(redPacketMessage.getRedPacketTitle());
                view.setBackgroundResource(R.drawable.red_pack_sender_no);
            }
        } else {
            if (!StringUtils.isEmpty(uiMessage.getExtra())) {
                view.setBackgroundResource(R.drawable.red_pack_recever_yes);
                holder.title.setText("红包已被领完");
                holder.redIcon.setImageResource(R.drawable.red_p2);
            } else {
                holder.title.setText(redPacketMessage.getRedPacketTitle());
                view.setBackgroundResource(R.drawable.red_pack_recever_no);
            }
        }
    }

    @Override
    public Spannable getContentSummary(MessageContent messageContent) {
        return new SpannableString("[红包]");
    }

    @Override
    public void onItemClick(View view, int i, MessageContent messageContent, UIMessage uiMessage) {
        String extra = uiMessage.getExtra();
        RedPacketMessage redPacketMessage = (RedPacketMessage) messageContent;
        if (uiMessage.getMessageDirection() == Message.MessageDirection.SEND) {//消息方向，自己发送的
            //自己发出去的消息
            ARouter.getInstance()
                    .build(ARouterConst.Activity_SendRedPacketDetailActivity)
                    .withString(KEY_RED_PACKAGE_ID, redPacketMessage.getRedPacketId())
                    .navigation();
            return;
        }
        if (StringUtils.isEmpty(extra)) {
            String targetId = uiMessage.getTargetId();
            Friend friend = FriendManager.getInstance().findFriendById(targetId);
            Date date = new Date();
            Date redPacketExpireDate = new Date(redPacketMessage.getExpireDate());
            if (redPacketExpireDate.before(date)) {
                RedPacketExpiredDialog redPacketExpiredDialog = new RedPacketExpiredDialog(view.getContext(), friend.getName(), redPacketMessage.getRedPacketTitle(), friend.getPortraitUri(),redPacketMessage.getExpireDate());
                redPacketExpiredDialog.show();
                return;
            }
            OpenRedPacketDialog openRedPacketDialog = new OpenRedPacketDialog(view.getContext(), friend.getName(), redPacketMessage.getRedPacketTitle(), friend.getPortraitUri());
            openRedPacketDialog.show();
            openRedPacketDialog.setOnOpenListener((dialog) -> {
                redPacketMessage.setUiMessage(uiMessage);
                redPacketMessage.setView(view);
                redPacketMessage.setRedPacketOpenDialog(dialog);
                RxBus.getInstance().post(redPacketMessage);
            });
        } else {
            //别人发给自己的红包并且已领取
        }
    }

    @Override
    public View newView(Context context, ViewGroup viewGroup) {
        View inflate = View.inflate(context, R.layout.item_red_packet, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        inflate.setTag(viewHolder);
        return inflate;
    }

    public static class ViewHolder {
        @BindView(R.id.red_icon)
        public ImageView redIcon;
        @BindView(R.id.title)
        public TextView title;
        @BindView(R.id.subTitle)
        public TextView subTitle;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

