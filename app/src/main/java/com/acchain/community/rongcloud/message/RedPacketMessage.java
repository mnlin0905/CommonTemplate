package com.acchain.community.rongcloud.message;

import android.app.Dialog;
import android.os.Parcel;
import android.view.View;

import org.json.JSONObject;

import io.rong.imkit.model.UIMessage;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;

/**
 * 自定义红包消息
 * Created by rsp on 2018/1/16.
 */
@MessageTag(value = "WM:RedPacket", flag = MessageTag.ISCOUNTED | MessageTag.ISPERSISTED)
public class RedPacketMessage extends MessageContent {
    private String redPacketId;    //红包id
    private String redPacketTitle; //红包的标题
    private String amount;       //金额
    private String currency;    //币种
    private UIMessage uiMessage;
    private View view;
    private Dialog redPacketOpenDialog;
    private long expireDate;    //红包到期时间

    public long getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(long expireDate) {
        this.expireDate = expireDate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Dialog getRedPacketOpenDialog() {
        return redPacketOpenDialog;
    }

    public void setRedPacketOpenDialog(Dialog redPacketOpenDialog) {
        this.redPacketOpenDialog = redPacketOpenDialog;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public UIMessage getUiMessage() {
        return uiMessage;
    }

    public void setUiMessage(UIMessage uiMessage) {
        this.uiMessage = uiMessage;
    }

    public String getRedPacketTitle() {
        return redPacketTitle;
    }

    public void setRedPacketTitle(String redPacketTitle) {
        this.redPacketTitle = redPacketTitle;
    }

    public String getRedPacketId() {
        return redPacketId;
    }

    public void setRedPacketId(String redPacketId) {
        this.redPacketId = redPacketId;
    }

    @Override
    public byte[] encode() {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("redPacketId", redPacketId);
            jsonObj.put("redPacketTitle", redPacketTitle);
            jsonObj.put("amount", amount);
            jsonObj.put("currency", currency);
            jsonObj.put("expireDate", expireDate);
            return jsonObj.toString().getBytes("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public RedPacketMessage(byte[] data) {
        super(data);
        try {
            String json = new String(data, "utf-8");
            JSONObject jsonObj = new JSONObject(json);
            redPacketId = jsonObj.optString("redPacketId");
            redPacketTitle = jsonObj.optString("redPacketTitle");
            amount = jsonObj.optString("amount");
            currency = jsonObj.optString("currency");
            expireDate = jsonObj.optLong("expireDate");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RedPacketMessage() {
    }
    public RedPacketMessage(String redPacketId,String redPacketTitle,String currency,String amount,long expireDate) {
        this.redPacketId = redPacketId;
        this.redPacketTitle = redPacketTitle;
        this.currency = currency;
        this.amount = amount;
        this.expireDate = expireDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.redPacketId);
        dest.writeString(this.redPacketTitle);
        dest.writeString(this.amount);
        dest.writeString(this.currency);
        dest.writeLong(this.expireDate);
    }

    protected RedPacketMessage(Parcel in) {
        this.redPacketId = in.readString();
        this.redPacketTitle = in.readString();
        this.amount = in.readString();
        this.currency = in.readString();
        this.expireDate = in.readLong();
    }

    public static final Creator<RedPacketMessage> CREATOR = new Creator<RedPacketMessage>() {
        @Override
        public RedPacketMessage createFromParcel(Parcel source) {
            return new RedPacketMessage(source);
        }

        @Override
        public RedPacketMessage[] newArray(int size) {
            return new RedPacketMessage[size];
        }
    };
}
