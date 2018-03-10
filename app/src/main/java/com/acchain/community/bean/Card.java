package com.acchain.community.bean;

/**
 * Created by rsp on 2018/1/25.
 */

public class Card {


    /**
     * member_id : 8
     * area : null
     * address : null
     * create_time : 2018-01-22 20:14:57
     * STANDBY3 : null
     * STANDBY2 : null
     * STANDBY1 : null
     * photo_img : http://192.168.1.253:90/group1/M00/00/02/wKgB_VppVPWAFuuXAACEAPhB84o147.png
     * update_time : 2018-01-22 21:29:06
     * phone : 9
     * company_name : 6667
     * nickname : 韭菜
     * name : 伟林
     * main_business : null
     * id : 2
     * position : 121212
     * email : null
     * introduction : null
     */

    private int member_id;
    private String area;
    private String address;
    private String create_time;
    private String STANDBY3;
    private String STANDBY2;
    private String STANDBY1;
    private String photo_img;
    private String update_time;
    private String phone;
    private String company_name;
    private String nickname;
    private String name;
    private String main_business;
    private int id;
    private String position;
    private String email;
    private String introduction;
    private int is_friends;

    public boolean isIs_friends() {
        return is_friends==0;
    }

    public void setIs_friends(int is_friends) {
        this.is_friends = is_friends;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
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

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getSTANDBY3() {
        return STANDBY3;
    }

    public void setSTANDBY3(String STANDBY3) {
        this.STANDBY3 = STANDBY3;
    }

    public String getSTANDBY2() {
        return STANDBY2;
    }

    public void setSTANDBY2(String STANDBY2) {
        this.STANDBY2 = STANDBY2;
    }

    public String getSTANDBY1() {
        return STANDBY1;
    }

    public void setSTANDBY1(String STANDBY1) {
        this.STANDBY1 = STANDBY1;
    }

    public String getPhoto_img() {
        return photo_img;
    }

    public void setPhoto_img(String photo_img) {
        this.photo_img = photo_img;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMain_business() {
        return main_business;
    }

    public void setMain_business(String main_business) {
        this.main_business = main_business;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
