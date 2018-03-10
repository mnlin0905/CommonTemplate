package com.acchain.community.presenter;


import com.acchain.community.activity.wallet.TakeOutFriendActivity;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.bean.TransferInfo;
import com.acchain.community.contract.TakeOutFriendContract;
import com.acchain.community.rongcloud.message.TranferMessage;
import com.acchain.community.util.EncodeUtils;

import javax.inject.Inject;

import io.rong.imkit.RongIM;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;

/**
 * function---- TakeOutFriendPresenter
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/11 13:58:57 (+0000).
 */
public class TakeOutFriendPresenter extends BasePresenter<TakeOutFriendActivity> implements TakeOutFriendContract.Presenter {
    @Inject
    public TakeOutFriendPresenter() {
    }

    /**
     * 1.1.9  转出数字资产(朋友)
     *
     * @param token          登录标志
     * @param currency       接收者ID
     * @param friendId       币种简称
     * @param number         转出数量
     * @param transactionPwd 支付密码
     * @param remarkes       转账备注
     * @param isRedEnvelopes 转账类型  0 钱包转账(默认)   1 红包转账    2 转账至朋友
     */
    @Override
    public void assetsTransferOutFriend(String token, String currency, String friendId, String number, String transactionPwd, String remarkes, int isRedEnvelopes) {
        transactionPwd = EncodeUtils.encode(transactionPwd, EncodeUtils.SHA_256);
        requestHttp(httpInterface.assetsTransferOutFriend(token, currency, friendId, number, transactionPwd, remarkes, isRedEnvelopes),
                tranferInfoBaseHttpBean -> sendMessageToRong(tranferInfoBaseHttpBean.dataSet,friendId,currency,number,remarkes),
                throwable -> mView.assetsTransferOutFriendFinish(null));
    }

    /**
     * 如果转账成功,则向融云发送成功的信息
     */
    private void sendMessageToRong(TransferInfo dataSet, String friendId, String currency, String amount, String remarkes){
        TranferMessage tranferMessage = new TranferMessage(dataSet.getTransaction_id(),
                currency,
                amount,
                remarkes,
                dataSet.getPayment_model(),
                dataSet.getCreate_time(),
                dataSet.getTarger_name(),
                dataSet.getOwen_name(), singleAccountBean.getMemberId(), singleAccountBean.getImgSrc());
        Message message = Message.obtain(friendId, Conversation.ConversationType.PRIVATE, tranferMessage);
        RongIM.getInstance().sendMessage(message, "收到一笔转账", "收到一笔转账", new IRongCallback.ISendMessageCallback() {
            @Override
            public void onAttached(Message message) {

            }

            @Override
            public void onSuccess(Message message) {
                mView.assetsTransferOutFriendFinish(dataSet);
            }

            @Override
            public void onError(Message message, RongIMClient.ErrorCode errorCode) {

            }
        });
    }
}