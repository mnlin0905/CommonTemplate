package com.acchain.community.bean;

import com.blankj.utilcode.util.StringUtils;

/**
 * Created by rsp on 2018/3/1.
 */

public class PhoneContactResult {

    /**
     * id : 8
     * onlyName : 123afdsaf
     * mobile : 17318060047
     * email : zhengweilins@foxmail.com
     * nickname : 韭菜
     * name : 伟林
     * photoImg : http://192.168.1.253:90/group14o147.png
     * createTime : 2018-01-05 16:09:38
     */

    private int id;
    private String onlyName;
    private String mobile;
    private String email;
    private String nickname;
    private String name;
    private String photoImg;
    private String createTime;
    private String phoneName;
    private int isFriend;

    public void setIsFriend(int isFriend) {
        this.isFriend = isFriend;
    }

    public boolean isFriend() {
        return isFriend == 0;
    }
    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOnlyName() {
        return onlyName;
    }

    public void setOnlyName(String onlyName) {
        this.onlyName = onlyName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        if (!StringUtils.isEmpty(nickname)) {
            return nickname;
        }
        if (!StringUtils.isEmpty(name)) {
            return name;
        }
        return onlyName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoImg() {
        return photoImg;
    }

    public void setPhotoImg(String photoImg) {
        this.photoImg = photoImg;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
