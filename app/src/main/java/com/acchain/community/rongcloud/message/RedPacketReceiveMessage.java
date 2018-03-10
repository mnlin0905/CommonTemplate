package com.acchain.community.rongcloud.message;

import android.os.Parcel;

import org.json.JSONObject;

import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;

/**
 * Created by rsp on 2018/2/1.
 */
@MessageTag(value = "WM:RedPacketReceive", flag = MessageTag.ISCOUNTED | MessageTag.ISPERSISTED)
public class RedPacketReceiveMessage extends MessageContent {
    private String message;
    private String extra;
    private String envelopesId;

    public String getEnvelopesId() {
        return envelopesId;
    }

    public void setEnvelopesId(String envelopesId) {
        this.envelopesId = envelopesId;
    }

    public RedPacketReceiveMessage(byte[] data) {
        super(data);
        try {
            String json = new String(data, "utf-8");
            JSONObject jsonObj = new JSONObject(json);
            message = jsonObj.optString("message");
            extra = jsonObj.optString("extra");
            envelopesId = jsonObj.optString("envelopesId");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public byte[] encode() {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("message", message);
            jsonObj.put("extra", extra);
            jsonObj.put("envelopesId", envelopesId);
            return jsonObj.toString().getBytes("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.message);
        dest.writeString(this.extra);
        dest.writeString(this.envelopesId);
    }

    public RedPacketReceiveMessage() {
    }

    protected RedPacketReceiveMessage(Parcel in) {
        this.message = in.readString();
        this.extra = in.readString();
        this.envelopesId = in.readString();
    }

    public static final Creator<RedPacketReceiveMessage> CREATOR = new Creator<RedPacketReceiveMessage>() {
        @Override
        public RedPacketReceiveMessage createFromParcel(Parcel source) {
            return new RedPacketReceiveMessage(source);
        }

        @Override
        public RedPacketReceiveMessage[] newArray(int size) {
            return new RedPacketReceiveMessage[size];
        }
    };
}
