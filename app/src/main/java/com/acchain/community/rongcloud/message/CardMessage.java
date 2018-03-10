package com.acchain.community.rongcloud.message;

import android.os.Parcel;

import org.json.JSONObject;

import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;

import static com.acchain.community.util.Const.KEY_TARGET_FRIEND_ID;

/**
 * 自定义名片消息
 * Created by rsp on 2018/1/17.
 */
@MessageTag(value = "WM:card", flag = MessageTag.ISCOUNTED | MessageTag.ISPERSISTED)
public class CardMessage extends MessageContent {
    private String nikeName;
    private String headImg;
    private String remarkName;//备注名
    private String actualName;//真实姓名
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public CardMessage(String userId, String nikeName, String headImg) {
        this.nikeName = nikeName;
        this.headImg = headImg;
        this.userId = userId;
    }
    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getRemarkName() {
        return remarkName;
    }

    public void setRemarkName(String remarkName) {
        this.remarkName = remarkName;
    }

    public String getActualName() {
        return actualName;
    }

    public void setActualName(String actualName) {
        this.actualName = actualName;
    }

    @Override
    public byte[] encode() {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("nikeName", nikeName);
            jsonObj.put("headImg", headImg);
            jsonObj.put("remarkName", remarkName);
            jsonObj.put("actualName", actualName);
            jsonObj.put(KEY_TARGET_FRIEND_ID, userId);
            return jsonObj.toString().getBytes("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public CardMessage(byte[] data) {
        super(data);
        try {
            String json = new String(data, "utf-8");
            JSONObject jsonObj = new JSONObject(json);
            nikeName = jsonObj.optString("nikeName");
            headImg = jsonObj.optString("headImg");
            remarkName = jsonObj.optString("remarkName");
            actualName = jsonObj.optString("actualName");
            userId = jsonObj.optString(KEY_TARGET_FRIEND_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nikeName);
        dest.writeString(this.headImg);
        dest.writeString(this.remarkName);
        dest.writeString(this.actualName);
        dest.writeString(this.userId);
    }

    protected CardMessage(Parcel in) {
        this.nikeName = in.readString();
        this.headImg = in.readString();
        this.remarkName = in.readString();
        this.actualName = in.readString();
        this.userId = in.readString();
    }

    public static final Creator<CardMessage> CREATOR = new Creator<CardMessage>() {
        @Override
        public CardMessage createFromParcel(Parcel source) {
            return new CardMessage(source);
        }

        @Override
        public CardMessage[] newArray(int size) {
            return new CardMessage[size];
        }
    };
}
