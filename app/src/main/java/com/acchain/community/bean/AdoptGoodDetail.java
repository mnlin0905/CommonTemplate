package com.acchain.community.bean;

import com.acchain.community.interfaces.CommonProduceDetail;
import com.acchain.community.util.DateUtil;

import java.io.Serializable;

/**
 * 领养详情
 * Created by Administrator on 2018/1/23.
 */

public class AdoptGoodDetail implements CommonProduceDetail {


    /**
     * productSpecification : 红豆杉产品规格
     * adoptPrice : 199
     * adoptName : 红豆杉 第1期
     * maxAdoptQty : 100
     * productBrand : 红豆杉产品品牌
     * browseNumber : 0
     * exercisePrice : 199
     * isVerify : 1
     * exerciseSpecification : 红豆杉行权规格
     * products : {"promotionCount":0,"productImg2":"","productImg3":"","productName":"长白野山参","productImg1":"","shareCount":0,"soldCount":0,"updateBy":"","versionNo":0,"id":1,"productImg":"group1/M00/00/02/wKgB_Vpi4tuAd3IyAAFTSLCgDUw599.png","updateTime":"2018-01-04 20:06:28","createBy":"","detailDescApp":"group1/M00/00/02/wKgB_Vpi4tuAd3IyAAFTSLCgDUw599.png","productImg4":"","productImg5":"","createTime":"2018-01-04 20:06:28","productDiscountPrice":198.000001,"collectionCount":0,"productPackUnitId":"kg","shortDesc":"集长白之精华，采天地之灵气集长白之精华，采天地之灵气","productQty":20,"bid":0,"isDel":0,"productPrice":199.000001,"cid":17}
     * isPutaway : 0
     * adoptedCount : 0
     * updateBy :
     * versionNo : 0
     * adoptEndTime : 2018-03-18 00:00:00
     * id : 1
     * currencyId : -1
     * isAutoExercise : 1
     * traceSource :
     * adoptStartTime : 2018-02-18 00:00:00
     * productId : 1
     * adoptCirculation : 2000
     * exerciseStartTime : 2018-04-18 10:00:00
     * updateTime : 2018-01-18 10:00:00
     * productionPlace : 红豆杉产地
     * productTypeId : 5
     * propertyCurrency : 红豆杉资产币种
     * defaultAdoptCode : {"videoSurveillanceInfo":"","adoptCode":"HDS01-0003","createTime":"2018-03-18 00:00:00","photoAlbumInfo":"","locationId":1,"adoptId":1,"id":3,"adoptStatus":0}
     * createBy :
     * createTime : 2018-01-18 10:00:00
     * exerciseEndTime : 2018-05-18 10:00:00
     * propertyName : 红豆杉
     * circulation : 100000
     * isDel : 0
     */

    private String productSpecification;
    private int adoptPrice;
    private String adoptName;
    private int maxAdoptQty;
    private String productBrand;
    private int browseNumber;
    private int exercisePrice;
    private int isVerify;
    private String exerciseSpecification;
    private ProductsBean products;
    private int isPutaway;
    private int adoptedCount;
    private String updateBy;
    private int versionNo;
    private String adoptEndTime;
    private int id;
    private int currencyId;
    private int isAutoExercise;
    private String traceSource;
    private String adoptStartTime;
    private int productId;
    private int adoptCirculation;
    private String exerciseStartTime;
    private String updateTime;
    private String productionPlace;
    private int productTypeId;
    private String propertyCurrency;
    private DefaultAdoptCodeBean defaultAdoptCode;
    private String createBy;
    private String createTime;
    private String exerciseEndTime;
    private String propertyName;
    private int circulation;
    private int isDel;

    public String getProductSpecification() {
        return productSpecification;
    }

    public void setProductSpecification(String productSpecification) {
        this.productSpecification = productSpecification;
    }

    public int getAdoptPrice() {
        return adoptPrice;
    }

    public void setAdoptPrice(int adoptPrice) {
        this.adoptPrice = adoptPrice;
    }

    public String getAdoptName() {
        return adoptName;
    }

    public void setAdoptName(String adoptName) {
        this.adoptName = adoptName;
    }

    public int getMaxAdoptQty() {
        return maxAdoptQty;
    }

    public void setMaxAdoptQty(int maxAdoptQty) {
        this.maxAdoptQty = maxAdoptQty;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public int getBrowseNumber() {
        return browseNumber;
    }

    public void setBrowseNumber(int browseNumber) {
        this.browseNumber = browseNumber;
    }

    public int getExercisePrice() {
        return exercisePrice;
    }

    public void setExercisePrice(int exercisePrice) {
        this.exercisePrice = exercisePrice;
    }

    public int getIsVerify() {
        return isVerify;
    }

    public void setIsVerify(int isVerify) {
        this.isVerify = isVerify;
    }

    public String getExerciseSpecification() {
        return exerciseSpecification;
    }

    public void setExerciseSpecification(String exerciseSpecification) {
        this.exerciseSpecification = exerciseSpecification;
    }

    public ProductsBean getProducts() {
        return products;
    }

    public void setProducts(ProductsBean products) {
        this.products = products;
    }

    public int getIsPutaway() {
        return isPutaway;
    }

    public void setIsPutaway(int isPutaway) {
        this.isPutaway = isPutaway;
    }

    public int getAdoptedCount() {
        return adoptedCount;
    }

    public void setAdoptedCount(int adoptedCount) {
        this.adoptedCount = adoptedCount;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public int getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(int versionNo) {
        this.versionNo = versionNo;
    }

    public String getAdoptEndTime() {
        return adoptEndTime;
    }

    public void setAdoptEndTime(String adoptEndTime) {
        this.adoptEndTime = adoptEndTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    public int getIsAutoExercise() {
        return isAutoExercise;
    }

    public void setIsAutoExercise(int isAutoExercise) {
        this.isAutoExercise = isAutoExercise;
    }

    public String getTraceSource() {
        return traceSource;
    }

    public void setTraceSource(String traceSource) {
        this.traceSource = traceSource;
    }

    public String getAdoptStartTime() {
        return adoptStartTime;
    }

    public void setAdoptStartTime(String adoptStartTime) {
        this.adoptStartTime = adoptStartTime;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getAdoptCirculation() {
        return adoptCirculation;
    }

    public void setAdoptCirculation(int adoptCirculation) {
        this.adoptCirculation = adoptCirculation;
    }

    public String getExerciseStartTime() {
        return exerciseStartTime;
    }

    public void setExerciseStartTime(String exerciseStartTime) {
        this.exerciseStartTime = exerciseStartTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getProductionPlace() {
        return productionPlace;
    }

    public void setProductionPlace(String productionPlace) {
        this.productionPlace = productionPlace;
    }

    public int getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getPropertyCurrency() {
        return propertyCurrency;
    }

    public void setPropertyCurrency(String propertyCurrency) {
        this.propertyCurrency = propertyCurrency;
    }

    public DefaultAdoptCodeBean getDefaultAdoptCode() {
        return defaultAdoptCode;
    }

    public void setDefaultAdoptCode(DefaultAdoptCodeBean defaultAdoptCode) {
        this.defaultAdoptCode = defaultAdoptCode;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getExerciseEndTime() {
        return exerciseEndTime;
    }

    public void setExerciseEndTime(String exerciseEndTime) {
        this.exerciseEndTime = exerciseEndTime;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public int getCirculation() {
        return circulation;
    }

    public void setCirculation(int circulation) {
        this.circulation = circulation;
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

    @Override
    public double getOldPrice() {
        return adoptPrice;
    }

    @Override
    public double getCurrentPrice() {
        return adoptPrice;
    }

    @Override
    public String getPriceUnit() {
        return "元";
    }

    @Override
    public int getProgress() {
        return adoptedCount*100/circulation;
    }

    @Override
    public int getAdoptAmount() {
        return adoptedCount;
    }

    @Override
    public double getDividend() {
        return 0;
    }

    @Override
    public int getBetweenTime() {
        return DateUtil.getBetweenDay(adoptStartTime, adoptEndTime);
    }

    @Override
    public String getTimeUnit() {
        return "天";
    }

    @Override
    public String getIntroduce() {
        return ProductsBean.shortDesc;
    }

    public static class ProductsBean {
        /**
         * promotionCount : 0
         * productImg2 :
         * productImg3 :
         * productName : 长白野山参
         * productImg1 :
         * shareCount : 0
         * soldCount : 0
         * updateBy :
         * versionNo : 0
         * id : 1
         * productImg : group1/M00/00/02/wKgB_Vpi4tuAd3IyAAFTSLCgDUw599.png
         * updateTime : 2018-01-04 20:06:28
         * createBy :
         * detailDescApp : group1/M00/00/02/wKgB_Vpi4tuAd3IyAAFTSLCgDUw599.png
         * productImg4 :
         * productImg5 :
         * createTime : 2018-01-04 20:06:28
         * productDiscountPrice : 198.000001
         * collectionCount : 0
         * productPackUnitId : kg
         * shortDesc : 集长白之精华，采天地之灵气集长白之精华，采天地之灵气
         * productQty : 20
         * bid : 0
         * isDel : 0
         * productPrice : 199.000001
         * cid : 17
         */

        private int promotionCount;
        private String productImg2;
        private String productImg3;
        private String productName;
        private String productImg1;
        private int shareCount;
        private int soldCount;
        private String updateBy;
        private int versionNo;
        private int id;
        private String productImg;
        private String updateTime;
        private String createBy;
        private String detailDescApp;
        private String productImg4;
        private String productImg5;
        private String createTime;
        private double productDiscountPrice;
        private int collectionCount;
        private String productPackUnitId;
        public static String shortDesc;
        private int productQty;
        private int bid;
        private int isDel;
        private double productPrice;
        private int cid;

        public int getPromotionCount() {
            return promotionCount;
        }

        public void setPromotionCount(int promotionCount) {
            this.promotionCount = promotionCount;
        }

        public String getProductImg2() {
            return productImg2;
        }

        public void setProductImg2(String productImg2) {
            this.productImg2 = productImg2;
        }

        public String getProductImg3() {
            return productImg3;
        }

        public void setProductImg3(String productImg3) {
            this.productImg3 = productImg3;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProductImg1() {
            return productImg1;
        }

        public void setProductImg1(String productImg1) {
            this.productImg1 = productImg1;
        }

        public int getShareCount() {
            return shareCount;
        }

        public void setShareCount(int shareCount) {
            this.shareCount = shareCount;
        }

        public int getSoldCount() {
            return soldCount;
        }

        public void setSoldCount(int soldCount) {
            this.soldCount = soldCount;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public int getVersionNo() {
            return versionNo;
        }

        public void setVersionNo(int versionNo) {
            this.versionNo = versionNo;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getProductImg() {
            return productImg;
        }

        public void setProductImg(String productImg) {
            this.productImg = productImg;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getDetailDescApp() {
            return detailDescApp;
        }

        public void setDetailDescApp(String detailDescApp) {
            this.detailDescApp = detailDescApp;
        }

        public String getProductImg4() {
            return productImg4;
        }

        public void setProductImg4(String productImg4) {
            this.productImg4 = productImg4;
        }

        public String getProductImg5() {
            return productImg5;
        }

        public void setProductImg5(String productImg5) {
            this.productImg5 = productImg5;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public double getProductDiscountPrice() {
            return productDiscountPrice;
        }

        public void setProductDiscountPrice(double productDiscountPrice) {
            this.productDiscountPrice = productDiscountPrice;
        }

        public int getCollectionCount() {
            return collectionCount;
        }

        public void setCollectionCount(int collectionCount) {
            this.collectionCount = collectionCount;
        }

        public String getProductPackUnitId() {
            return productPackUnitId;
        }

        public void setProductPackUnitId(String productPackUnitId) {
            this.productPackUnitId = productPackUnitId;
        }

        public String getShortDesc() {
            return shortDesc;
        }

        public void setShortDesc(String shortDesc) {
            this.shortDesc = shortDesc;
        }

        public int getProductQty() {
            return productQty;
        }

        public void setProductQty(int productQty) {
            this.productQty = productQty;
        }

        public int getBid() {
            return bid;
        }

        public void setBid(int bid) {
            this.bid = bid;
        }

        public int getIsDel() {
            return isDel;
        }

        public void setIsDel(int isDel) {
            this.isDel = isDel;
        }

        public double getProductPrice() {
            return productPrice;
        }

        public void setProductPrice(double productPrice) {
            this.productPrice = productPrice;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }
    }

    public static class DefaultAdoptCodeBean implements Serializable{
        /**
         * videoSurveillanceInfo :
         * adoptCode : HDS01-0003
         * createTime : 2018-03-18 00:00:00
         * photoAlbumInfo :
         * locationId : 1
         * adoptId : 1
         * id : 3
         * adoptStatus : 0
         */


        /**
         * videoSurveillanceInfo :
         * adoptCode : HDS01-0003
         * photoAlbumInfo :
         * updateTime : 2018-01-18 00:00:00
         * adoptStatus : 0
         * createBy :
         * createTime : 2018-03-18 00:00:00
         * updateBy :
         * locationId : 1
         * versionNo : 0
         * adoptId : 1
         * id : 3
         * isDel : 0
         */

        private String videoSurveillanceInfo;
        private String adoptCode;
        private String createTime;
        private String photoAlbumInfo;
        private int locationId;
        private int adoptId;
        private int id;
        private int adoptStatus;
        //为了共用这个类，多定义出来的属性
        private String updateTime;
        private String createBy;
        private String updateBy;
        private int isDel;
        private int versionNo;

        public DefaultAdoptCodeBean(String videoSurveillanceInfo, String adoptCode, String photoAlbumInfo, int locationId, int adoptId, int id, int adoptStatus) {
            this.videoSurveillanceInfo = videoSurveillanceInfo;
            this.adoptCode = adoptCode;
            this.photoAlbumInfo = photoAlbumInfo;
            this.locationId = locationId;
            this.adoptId = adoptId;
            this.id = id;
            this.adoptStatus = adoptStatus;
        }

        public String getVideoSurveillanceInfo() {
            return videoSurveillanceInfo;
        }

        public void setVideoSurveillanceInfo(String videoSurveillanceInfo) {
            this.videoSurveillanceInfo = videoSurveillanceInfo;
        }

        public String getAdoptCode() {
            return adoptCode;
        }

        public void setAdoptCode(String adoptCode) {
            this.adoptCode = adoptCode;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getPhotoAlbumInfo() {
            return photoAlbumInfo;
        }

        public void setPhotoAlbumInfo(String photoAlbumInfo) {
            this.photoAlbumInfo = photoAlbumInfo;
        }

        public int getLocationId() {
            return locationId;
        }

        public void setLocationId(int locationId) {
            this.locationId = locationId;
        }

        public int getAdoptId() {
            return adoptId;
        }

        public void setAdoptId(int adoptId) {
            this.adoptId = adoptId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAdoptStatus() {
            return adoptStatus;
        }

        public void setAdoptStatus(int adoptStatus) {
            this.adoptStatus = adoptStatus;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public int getIsDel() {
            return isDel;
        }

        public void setIsDel(int isDel) {
            this.isDel = isDel;
        }

        public int getVersionNo() {
            return versionNo;
        }

        public void setVersionNo(int versionNo) {
            this.versionNo = versionNo;
        }
    }
}
