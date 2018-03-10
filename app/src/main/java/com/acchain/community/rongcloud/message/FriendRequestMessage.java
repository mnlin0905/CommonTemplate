package com.acchain.community.rongcloud.message;

import android.os.Parcel;

import com.acchain.community.bean.ContactAddRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;

/**
 * Created by rsp on 2018/1/22.
 */
@MessageTag(
        value = "WM:ContactNtf",
        flag =  MessageTag.NONE
)
public class FriendRequestMessage extends MessageContent {
    public static final String CONTACT_OPERATION_REQUEST = "WMRequest";
    public static final String CONTACT_OPERATION_ACCEPT_RESPONSE = "WMAcceptResponse";
    public static final String CONTACT_OPERATION_REJECT_RESPONSE = "WMRejectResponse";
    private String operation = "";//当前的操作
    private String extra = "";//扩展参数
    private String sourceUserId = "";//who
    private String targetUserId = "";//被添加的人
    private String message = "";//验证信息
    private transient static final String TYPE = "WM:ContactNtf";
    private String name="";
    private String headImg="";

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getSourceUserId() {
        return sourceUserId;
    }

    public void setSourceUserId(String sourceUserId) {
        this.sourceUserId = sourceUserId;
    }

    public String getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(String targetUserId) {
        this.targetUserId = targetUserId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String getTYPE() {
        return TYPE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    @Override
    public byte[] encode() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(operation, operation);
            jsonObject.put(extra, extra);
            jsonObject.put(sourceUserId, sourceUserId);
            jsonObject.put(targetUserId, targetUserId);
            jsonObject.put(message, message);
            jsonObject.put(name, name);
            jsonObject.put(headImg, headImg);
            return jsonObject.toString().getBytes("utf-8");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public FriendRequestMessage(byte[] data) {
        super(data);
        try {
            String json = new String(data, "utf-8");
            JSONObject jsonObject = new JSONObject(json);
            operation = jsonObject.optString("operation");
            extra = jsonObject.optString("extra");
            sourceUserId = jsonObject.optString("sourceUserId");
            targetUserId = jsonObject.optString("targetUserId");
            message = jsonObject.optString("message");
            name = jsonObject.optString("name");
            headImg = jsonObject.optString("headImg");
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
        dest.writeString(this.operation);
        dest.writeString(this.extra);
        dest.writeString(this.sourceUserId);
        dest.writeString(this.targetUserId);
        dest.writeString(this.message);
        dest.writeString(this.name);
        dest.writeString(this.headImg);
    }

    protected FriendRequestMessage(Parcel in) {
        this.operation = in.readString();
        this.extra = in.readString();
        this.sourceUserId = in.readString();
        this.targetUserId = in.readString();
        this.message = in.readString();
        this.name = in.readString();
        this.headImg = in.readString();
    }

    public static final Creator<FriendRequestMessage> CREATOR = new Creator<FriendRequestMessage>() {
        @Override
        public FriendRequestMessage createFromParcel(Parcel source) {
            return new FriendRequestMessage(source);
        }

        @Override
        public FriendRequestMessage[] newArray(int size) {
            return new FriendRequestMessage[size];
        }
    };
    public ContactAddRequest getContactAddRequest() {
        return new ContactAddRequest(operation,extra,sourceUserId,targetUserId,message,name,headImg);
    }
}
