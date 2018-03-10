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
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.provider.IContainerItemProvider;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;

import static com.acchain.community.util.Const.KEY_TRANSFER_MESSAGE;

/**
 * Created by rsp on 2018/2/2.
 */
@ProviderTag(messageContent = TranferMessage.class)
public class TranferMessageProvider extends IContainerItemProvider.MessageProvider {
    @Override
    public void bindView(View view, int i, MessageContent messageContent, UIMessage uiMessage) {
        TranferMessage tranferMessage = (TranferMessage) messageContent;
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        if (uiMessage.getMessageDirection() == Message.MessageDirection.SEND) {//消息方向，自己发送的
            view.setBackgroundResource(R.drawable.red_pack_sender_no);
        } else {
            view.setBackgroundResource(R.drawable.red_pack_recever_no);
        }
        viewHolder.title.setText(tranferMessage.getRemark());
        viewHolder.subTitle.setText(tranferMessage.getAmount()+" "+tranferMessage.getCurrency());
    }

    @Override
    public Spannable getContentSummary(MessageContent messageContent) {
        return new SpannableString("[转账]");
    }

    @Override
    public void onItemClick(View view, int i, MessageContent messageContent, UIMessage uiMessage) {
        TranferMessage tranferMessage = (TranferMessage) messageContent;
        ARouter.getInstance().build(ARouterConst.Activity_TransferDetailActivity).withParcelable(KEY_TRANSFER_MESSAGE,tranferMessage).navigation();
    }

    @Override
    public View newView(Context context, ViewGroup viewGroup) {
        View inflate = View.inflate(context, R.layout.item_tranfer, null);
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
