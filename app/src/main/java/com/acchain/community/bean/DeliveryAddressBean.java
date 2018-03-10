package com.acchain.community.bean;

import com.acchain.community.base.BaseBean;

/**
 * Created on 2018/1/16
 * function : 修改收货地址
 *
 * @author ACChain
 */

public class DeliveryAddressBean extends BaseBean {

    /**
     * id : 143
     * memberId : 19
     * telephone : null
     * mobile : 15071377907
     * name : 黄聪
     * zipcode : null
     * province : 广东省
     * city : 深圳市
     * area : 南山区
     * address : 后海大道
     * stamp : 1516182081000
     * createTime : 1516182053000
     * updateTime : null
     * status : 0
     * defaultStatus : 1 //0为默认,1为普通收货地址
     * standby1 : null
     * standby2 : null
     * standby3 : null
     */

    private int id;
    private int memberId;
    private Object telephone;
    private String mobile;
    private String name;
    private Object zipcode;
    private String province;
    private String city;
    private String area;
    private String address;
    private String stamp;
    private String createTime;
    private String updateTime;
    private int status;
    private int defaultStatus;
    private Object standby1;
    private Object standby2;
    private Object standby3;

    /**
     * @return  判断是否为默认地址
     */
    public boolean isDefaultAddress() {
        return defaultStatus==0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public Object getTelephone() {
        return telephone;
    }

    public void setTelephone(Object telephone) {
        this.telephone = telephone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getZipcode() {
        return zipcode;
    }

    public void setZipcode(Object zipcode) {
        this.zipcode = zipcode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDefaultStatus() {
        return defaultStatus;
    }

    public void setDefaultStatus(int defaultStatus) {
        this.defaultStatus = defaultStatus;
    }

    public Object getStandby1() {
        return standby1;
    }

    public void setStandby1(Object standby1) {
        this.standby1 = standby1;
    }

    public Object getStandby2() {
        return standby2;
    }

    public void setStandby2(Object standby2) {
        this.standby2 = standby2;
    }

    public Object getStandby3() {
        return standby3;
    }

    public void setStandby3(Object standby3) {
        this.standby3 = standby3;
    }
}
