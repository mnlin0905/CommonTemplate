package com.acchain.community.bean;

import android.text.TextUtils;

import com.acchain.community.base.BaseBean;

/**
 * Created on 2018/1/19
 * function : 1.9 获取会员账户信息
 *
 * @author ACChain
 */

public class GetAccountBean extends BaseBean {


    /**
     * nickname : 白菜
     * imgSrc : http://192.168.1.253:90/group1/M00/00/02/wK
     * payPwd : false
     * mobile : 18676389508
     * email :
     * collections : 4
     * attentions : 0
     * browses : 2
     * assets : 0
     * onlyName : 123afdsaf
     * rongCouldToken : V8qnCjSGGjR7ymCiHPq9SC09e8XUqu4UbxmLCIearkdJ1+Ia7Q==
     * sex : 2
     * province : 广东省
     * city : 123afdsafSRRW7O
     * memberId : 19
     * tbMemberVerifiedInfo : {"id":3,"memberId":19,"name":"黄聪123","idcard":"420115199508084018","country":"中国","cardType":1,"cardPositive":"http://192.168.1.253/group1/M00/00/01/wKgB_VpcnK-.jpg","cardContrary":"http://192.168.1.253/group1/M00/00/01/wKgB_VpcnK-.jpg","cardHord":"http://192.168.1.253/group1/M00/00/02/wKgB_VpcnK-_Q927.jpg","createTime":1516018834000,"examineTime":null,"mobile":"18676389508","status":0,"adminUserId":null,"notPassing":null,"standby1":null,"standby2":null,"standby3":null}
     * businessCard : {"id":1,"position":"CEO","companyName":"阿里巴巴","memberId":19,"mainBusiness":"大宗商品","phone":"13688888888","address":null,"createTime":1516623297000,"updateTime":1516627746000,"standby1":null,"standby2":null,"standby3":null,"introduction":null}
     */

    private String nickname;
    private String imgSrc;
    private boolean payPwd;
    private String mobile;
    private String email;
    private int collections;
    private int attentions;
    private int browses;
    private int assets;
    private String onlyName;
    private String rongCouldToken;
    private int sex;
    private String province;
    private String city;
    private String memberId;
    private TbMemberVerifiedInfo tbMemberVerifiedInfo;
    private BusinessCard businessCard;

    /**
     * @return 是否实名认证通过
     */
    public boolean isVerifiedSuccess() {
        return tbMemberVerifiedInfo != null && tbMemberVerifiedInfo.getStatus() == 0;
    }

    /**
     * @return 获取性别字符串形式
     */
    public String getSexStr() {
        return sex == 0 ? "未知" : (sex == 1 ? "男" : "女");
    }

    /**
     * @return 获取区域信息
     */
    public String getLocation() {
        return (TextUtils.isEmpty(province) || TextUtils.isEmpty(city)) ? "" : (province + " " + city);
    }

    /**
     * @return 获取认证信息
     */
    public String getCertification() {
        return tbMemberVerifiedInfo == null ? "未认证" :
                (tbMemberVerifiedInfo.status == 0 ? "待审核" :
                        (tbMemberVerifiedInfo.status == 1 ? "审核通过" : "审核失败"));
    }

    /**
     * @return 获取职业信息
     */
    public String getBusinessCardStr() {
        return businessCard == null ? "未填写" : businessCard.position;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public boolean isPayPwd() {
        return payPwd;
    }

    public void setPayPwd(boolean payPwd) {
        this.payPwd = payPwd;
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

    public int getCollections() {
        return collections;
    }

    public void setCollections(int collections) {
        this.collections = collections;
    }

    public int getAttentions() {
        return attentions;
    }

    public void setAttentions(int attentions) {
        this.attentions = attentions;
    }

    public int getBrowses() {
        return browses;
    }

    public void setBrowses(int browses) {
        this.browses = browses;
    }

    public int getAssets() {
        return assets;
    }

    public void setAssets(int assets) {
        this.assets = assets;
    }

    public String getOnlyName() {
        return onlyName;
    }

    public void setOnlyName(String onlyName) {
        this.onlyName = onlyName;
    }

    public String getRongCouldToken() {
        return rongCouldToken;
    }

    public void setRongCouldToken(String rongCouldToken) {
        this.rongCouldToken = rongCouldToken;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
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

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public TbMemberVerifiedInfo getTbMemberVerifiedInfo() {
        return tbMemberVerifiedInfo;
    }

    public void setTbMemberVerifiedInfo(TbMemberVerifiedInfo tbMemberVerifiedInfo) {
        this.tbMemberVerifiedInfo = tbMemberVerifiedInfo;
    }

    public BusinessCard getBusinessCard() {
        return businessCard;
    }

    public void setBusinessCard(BusinessCard businessCard) {
        this.businessCard = businessCard;
    }

    public static class TbMemberVerifiedInfo {
        /**
         * id : 3
         * memberId : 19
         * name : 黄聪123
         * idcard : 420115199508084018
         * country : 中国
         * cardType : 1
         * cardPositive : http://192.168.1.253/group1/M00/00/01/wKgB_VpcnK-.jpg
         * cardContrary : http://192.168.1.253/group1/M00/00/01/wKgB_VpcnK-.jpg
         * cardHord : http://192.168.1.253/group1/M00/00/02/wKgB_VpcnK-_Q927.jpg
         * createTime : 1516018834000
         * examineTime : null
         * mobile : 18676389508
         * status : 0 //0:待审核(审核中) 1:审核通过 2:审核失败
         * adminUserId : null
         * notPassing : null
         * standby1 : null
         * standby2 : null
         * standby3 : null
         */

        private int id;
        private int memberId;
        private String name;
        private String idcard;
        private String country;
        private int cardType;
        private String cardPositive;
        private String cardContrary;
        private String cardHord;
        private long createTime;
        private Object examineTime;
        private String mobile;
        private int status;
        private Object adminUserId;
        private String notPassing;
        private Object standby1;
        private Object standby2;
        private Object standby3;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public int getCardType() {
            return cardType;
        }

        public void setCardType(int cardType) {
            this.cardType = cardType;
        }

        public String getCardPositive() {
            return cardPositive;
        }

        public void setCardPositive(String cardPositive) {
            this.cardPositive = cardPositive;
        }

        public String getCardContrary() {
            return cardContrary;
        }

        public void setCardContrary(String cardContrary) {
            this.cardContrary = cardContrary;
        }

        public String getCardHord() {
            return cardHord;
        }

        public void setCardHord(String cardHord) {
            this.cardHord = cardHord;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public Object getExamineTime() {
            return examineTime;
        }

        public void setExamineTime(Object examineTime) {
            this.examineTime = examineTime;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getAdminUserId() {
            return adminUserId;
        }

        public void setAdminUserId(Object adminUserId) {
            this.adminUserId = adminUserId;
        }

        public String getNotPassing() {
            return notPassing;
        }

        public void setNotPassing(String notPassing) {
            this.notPassing = notPassing;
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

    public static class BusinessCard {
        /**
         * id : 1
         * position : CEO
         * companyName : 阿里巴巴
         * memberId : 19
         * mainBusiness : 大宗商品
         * phone : 13688888888
         * address : null
         * createTime : 1516623297000
         * updateTime : 1516627746000
         * standby1 : null
         * standby2 : null
         * standby3 : null
         * introduction : null
         */

        private int id;
        private String position;
        private String companyName;
        private int memberId;
        private String mainBusiness;
        private String phone;
        private String email;
        private String address;
        private long createTime;
        private long updateTime;
        private Object standby1;
        private Object standby2;
        private Object standby3;
        private String  introduction;

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

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public String getMainBusiness() {
            return mainBusiness;
        }

        public void setMainBusiness(String mainBusiness) {
            this.mainBusiness = mainBusiness;
        }

        public String getPhone() {
            return phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
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

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }
    }
}
