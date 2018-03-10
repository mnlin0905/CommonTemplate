package com.common.template.bean;

import com.blankj.utilcode.util.StringUtils;
import com.google.gson.annotations.SerializedName;

import org.litepal.crud.DataSupport;

import java.io.Serializable;


/**
 * Created by rsp on 2018/1/15.
 */

public class Friend extends DataSupport implements Serializable {
    private long id;
    @SerializedName("name")
    private String name;  //昵称
    @SerializedName("photo_img")
    private String portraitUri;
    @SerializedName("remarks")
    private String remarkName;//备注名
    private String actualName;//真实姓名
    private String letters;
    private String nameSpelling;
    private int status; //好友状态 0 验证中  1 已通过  2 黑名单  3 已过期
    @SerializedName("friend_id")
    private String userId;
    @SerializedName("applyContent")
    private String message;
    private String member_id;
    private String only_name;//微脉号

    public Friend() {
    }

    public Friend(long id, String name, String portraitUri) {
        this.id = id;
        this.name = name;
        this.portraitUri = portraitUri;
    }

    public Friend(long id, String name, String portraitUri, String remarkName, String actualName, String letters, String nameSpelling, int status) {
        this.id = id;
        this.name = name;
        this.portraitUri = portraitUri;
        this.remarkName = remarkName;
        this.actualName = actualName;
        this.letters = letters;
        this.nameSpelling = nameSpelling;
        this.status = status;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        if (!StringUtils.isEmpty(remarkName)) {
            return remarkName;
        }
        if (!StringUtils.isEmpty(name)) {
            return name;
        }
        return only_name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPortraitUri() {
        return portraitUri;
    }

    public void setPortraitUri(String portraitUri) {
        this.portraitUri = portraitUri;
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

    public String getLetters() {
        return letters;
    }

    public void setLetters(String letters) {
        this.letters = letters;
    }

    public String getNameSpelling() {
        return nameSpelling;
    }

    public void setNameSpelling(String nameSpelling) {
        this.nameSpelling = nameSpelling;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
