package com.acchain.community.bean;

import android.net.Uri;

import org.litepal.crud.DataSupport;

import io.rong.imlib.model.UserInfo;

/**
 * Created by rsp on 2018/1/19.
 */

public class ContactAddRequest extends DataSupport {
    public static final String CONTACT_OPERATION_REQUEST = "WMRequest";
    public static final String CONTACT_OPERATION_ACCEPT_RESPONSE = "WMAcceptResponse";
    public static final String CONTACT_OPERATION_REJECT_RESPONSE = "WMRejectResponse";

    private long id;
    private String requestId ="";
    private String operation = "";
    private String extra = "";
    private String sourceUserId = "";
    private String targetUserId = "";
    private String message = "";
    private String name;
    private String headImg;
    private long updateTime;

    public UserInfo getUserInfo() {
        return new UserInfo(sourceUserId,name, Uri.parse(headImg));
    }
    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public ContactAddRequest() {
    }

    @Override
    public String toString() {
        return "ContactAddRequest{" +
                "id=" + id +
                ", operation='" + operation + '\'' +
                ", extra='" + extra + '\'' +
                ", sourceUserId='" + sourceUserId + '\'' +
                ", targetUserId='" + targetUserId + '\'' +
                ", message='" + message + '\'' +
                ", name='" + name + '\'' +
                ", headImg='" + headImg + '\'' +
                '}';
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public ContactAddRequest(String operation, String extra, String sourceUserId, String targetUserId, String message, String name, String headImg) {
        this.operation = operation;
        this.extra = extra;
        this.sourceUserId = sourceUserId;
        this.targetUserId = targetUserId;
        this.message = message;
        this.name = name;
        this.headImg = headImg;
        this.requestId = sourceUserId + targetUserId;
    }
}

