package com.acchain.community.rongcloud.message;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;

/**
 * Created by rsp on 2018/2/2.
 */
@MessageTag(value = "WM:tranfer", flag = MessageTag.ISCOUNTED | MessageTag.ISPERSISTED)
public class TranferMessage extends MessageContent implements Parcelable {
    private String transId;
    private String currency;
    private String amount;
    private String remark;
    private String payment_model;
    private String create_time;
    private String targer_name;
    private String owen_name;
    private String senderUserId;
    private String senderHeadImg;

    public String getSenderHeadImg() {
        return senderHeadImg;
    }

    public void setSenderHeadImg(String senderHeadImg) {
        this.senderHeadImg = senderHeadImg;
    }

    public String getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(String senderUserId) {
        this.senderUserId = senderUserId;
    }

    public String getPayment_model() {
        return payment_model;
    }

    public void setPayment_model(String payment_model) {
        this.payment_model = payment_model;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getTarger_name() {
        return targer_name;
    }

    public void setTarger_name(String targer_name) {
        this.targer_name = targer_name;
    }

    public String getOwen_name() {
        return owen_name;
    }

    public void setOwen_name(String owen_name) {
        this.owen_name = owen_name;
    }



    public TranferMessage(String transId, String currency, String amount, String remark, String payment_model, String create_time, String targer_name, String owen_name,String senderUserId,String senderHeadImg) {
        this.transId = transId;
        this.currency = currency;
        this.amount = amount;
        this.remark = remark;
        this.payment_model = payment_model;
        this.create_time = create_time;
        this.targer_name = targer_name;
        this.owen_name = owen_name;
        this.senderUserId = senderUserId;
        this.senderHeadImg = senderHeadImg;

    }

    public TranferMessage(String transId, String currency, String amount, String remark) {
        this.transId = transId;
        this.currency = currency;
        this.amount = amount;
        this.remark = remark;
    }

    public TranferMessage() {
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public TranferMessage(byte[] data) {
        super(data);
        try {
            String json = new String(data, "utf-8");
            JSONObject jsonObj = new JSONObject(json);
            transId = jsonObj.optString("transId");
            currency = jsonObj.optString("currency");
            amount = jsonObj.optString("amount");
            remark = jsonObj.optString("remark");
            payment_model = jsonObj.optString("payment_model");
            create_time = jsonObj.optString("create_time");
            targer_name = jsonObj.optString("targer_name");
            owen_name = jsonObj.optString("owen_name");
            senderUserId = jsonObj.optString("senderUserId");
            senderHeadImg = jsonObj.optString("senderHeadImg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public byte[] encode() {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("transId", transId);
            jsonObj.put("currency", currency);
            jsonObj.put("amount", amount);
            jsonObj.put("remark", remark);
            jsonObj.put("payment_model", payment_model);
            jsonObj.put("create_time", create_time);
            jsonObj.put("targer_name", targer_name);
            jsonObj.put("owen_name", owen_name);
            jsonObj.put("senderUserId", senderUserId);
            jsonObj.put("senderHeadImg", senderHeadImg);
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
        dest.writeString(this.transId);
        dest.writeString(this.currency);
        dest.writeString(this.amount);
        dest.writeString(this.remark);
        dest.writeString(this.payment_model);
        dest.writeString(this.create_time);
        dest.writeString(this.targer_name);
        dest.writeString(this.owen_name);
        dest.writeString(this.senderUserId);
        dest.writeString(this.senderHeadImg);
    }

    protected TranferMessage(Parcel in) {
        this.transId = in.readString();
        this.currency = in.readString();
        this.amount = in.readString();
        this.remark = in.readString();
        this.payment_model = in.readString();
        this.create_time = in.readString();
        this.targer_name = in.readString();
        this.owen_name = in.readString();
        this.senderUserId = in.readString();
        this.senderHeadImg = in.readString();
    }

    public static final Creator<TranferMessage> CREATOR = new Creator<TranferMessage>() {
        @Override
        public TranferMessage createFromParcel(Parcel source) {
            return new TranferMessage(source);
        }

        @Override
        public TranferMessage[] newArray(int size) {
            return new TranferMessage[size];
        }
    };
}
