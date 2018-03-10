package com.acchain.community.contract;

import com.acchain.community.bean.TransferInfo;

/**
 * function---- TakeOutFriendContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/11 13:58:57 (+0000).
 */
public interface TakeOutFriendContract {
    interface Presenter {
        /**
         * 1.1.9  转出数字资产(朋友)
         *
         * @param token          登录标志
         * @param currency       币种简称
         * @param friendId       接收者ID
         * @param number         转出数量
         * @param transactionPwd 支付密码
         * @param remarkes       转账备注
         * @param isRedEnvelopes 转账类型  0 钱包转账(默认)   1 红包转账    2 转账至朋友
         */
        void assetsTransferOutFriend( String token, String currency,String friendId,String number,String transactionPwd, String remarkes,int isRedEnvelopes);
    }

    interface View {
        /**
         * 1.1.9  转出数字资产(朋友)
         *
         * @param transferInfo  转给朋友的结果返回信息
         */
        void assetsTransferOutFriendFinish(TransferInfo transferInfo);
    }
}