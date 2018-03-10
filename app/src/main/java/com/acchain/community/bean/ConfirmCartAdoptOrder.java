package com.acchain.community.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/26.
 */

public class ConfirmCartAdoptOrder implements Serializable {


    private List<OrderInfoBean> orderInfo;

    public List<OrderInfoBean> getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(List<OrderInfoBean> orderInfo) {
        this.orderInfo = orderInfo;
    }

    public static class OrderInfoBean implements Serializable {
        /**
         * flashSaleRefId : -1
         * adoptCodeId : 3
         * productId : 1
         * adoptCode : [{"videoSurveillanceInfo":"","adoptCode":"HDS01-0003","photoAlbumInfo":"","updateTime":"2018-01-18 00:00:00","adoptStatus":0,"createBy":"","createTime":"2018-03-18 00:00:00","updateBy":"","locationId":1,"versionNo":0,"adoptId":1,"id":3,"isDel":0}]
         * cartType : 2
         * id : 1467
         * productSubId : 1
         * memberId : 28
         * itemCount : 2
         * productAttr :
         * products : {"productSpecification":"红豆杉产品规格","adoptPrice":199,"adoptName":"红豆杉 第1期","maxAdoptQty":100,"productBrand":"红豆杉产品品牌","browseNumber":0,"exercisePrice":199,"exerciseSpecification":"红豆杉行权规格","adoptedCount":0,"adoptId":1,"adoptEndTime":"2018-03-18 00:00:00","currencyId":2,"isAutoExercise":1,"traceSource":"","productId":1,"adoptStartTime":"2018-02-18 00:00:00","adoptCirculation":2000,"exerciseStartTime":"2018-04-18 10:00:00","productionPlace":"红豆杉产地","productTypeId":5,"propertyCurrency":"RET","propertyName":"红豆杉","exerciseEndTime":"2018-05-18 10:00:00","circulation":100000,"bid":0}
         */

        private int flashSaleRefId;
        private String adoptCodeId;
        private int productId;
        private int cartType;
        private int id;
        private int productSubId;
        private int memberId;
        private int itemCount;
        private String productAttr;
        private ProductsBean products;
        private List<AdoptCodeBean> adoptCode;

        public int getFlashSaleRefId() {
            return flashSaleRefId;
        }

        public void setFlashSaleRefId(int flashSaleRefId) {
            this.flashSaleRefId = flashSaleRefId;
        }

        public String getAdoptCodeId() {
            return adoptCodeId;
        }

        public void setAdoptCodeId(String adoptCodeId) {
            this.adoptCodeId = adoptCodeId;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public int getCartType() {
            return cartType;
        }

        public void setCartType(int cartType) {
            this.cartType = cartType;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getProductSubId() {
            return productSubId;
        }

        public void setProductSubId(int productSubId) {
            this.productSubId = productSubId;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public int getItemCount() {
            return itemCount;
        }

        public void setItemCount(int itemCount) {
            this.itemCount = itemCount;
        }

        public String getProductAttr() {
            return productAttr;
        }

        public void setProductAttr(String productAttr) {
            this.productAttr = productAttr;
        }

        public ProductsBean getProducts() {
            return products;
        }

        public void setProducts(ProductsBean products) {
            this.products = products;
        }

        public List<AdoptCodeBean> getAdoptCode() {
            return adoptCode;
        }

        public void setAdoptCode(List<AdoptCodeBean> adoptCode) {
            this.adoptCode = adoptCode;
        }

        public static class ProductsBean implements Serializable {
            /**
             * productSpecification : 红豆杉产品规格
             * adoptPrice : 199
             * adoptName : 红豆杉 第1期
             * maxAdoptQty : 100
             * productBrand : 红豆杉产品品牌
             * browseNumber : 0
             * exercisePrice : 199
             * exerciseSpecification : 红豆杉行权规格
             * adoptedCount : 0
             * adoptId : 1
             * adoptEndTime : 2018-03-18 00:00:00
             * currencyId : 2
             * isAutoExercise : 1
             * traceSource :
             * productId : 1
             * adoptStartTime : 2018-02-18 00:00:00
             * adoptCirculation : 2000
             * exerciseStartTime : 2018-04-18 10:00:00
             * productionPlace : 红豆杉产地
             * productTypeId : 5
             * propertyCurrency : RET
             * propertyName : 红豆杉
             * exerciseEndTime : 2018-05-18 10:00:00
             * circulation : 100000
             * bid : 0
             */

            private String productSpecification;
            private int adoptPrice;
            private String adoptName;
            private int maxAdoptQty;
            private String productBrand;
            private int browseNumber;
            private int exercisePrice;
            private String exerciseSpecification;
            private int adoptedCount;
            private int adoptId;
            private String adoptEndTime;
            private int currencyId;
            private int isAutoExercise;
            private String traceSource;
            private int productId;
            private String adoptStartTime;
            private int adoptCirculation;
            private String exerciseStartTime;
            private String productionPlace;
            private int productTypeId;
            private String propertyCurrency;
            private String propertyName;
            private String exerciseEndTime;
            private int circulation;
            private int bid;

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

            public String getExerciseSpecification() {
                return exerciseSpecification;
            }

            public void setExerciseSpecification(String exerciseSpecification) {
                this.exerciseSpecification = exerciseSpecification;
            }

            public int getAdoptedCount() {
                return adoptedCount;
            }

            public void setAdoptedCount(int adoptedCount) {
                this.adoptedCount = adoptedCount;
            }

            public int getAdoptId() {
                return adoptId;
            }

            public void setAdoptId(int adoptId) {
                this.adoptId = adoptId;
            }

            public String getAdoptEndTime() {
                return adoptEndTime;
            }

            public void setAdoptEndTime(String adoptEndTime) {
                this.adoptEndTime = adoptEndTime;
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

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public String getAdoptStartTime() {
                return adoptStartTime;
            }

            public void setAdoptStartTime(String adoptStartTime) {
                this.adoptStartTime = adoptStartTime;
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

            public String getPropertyName() {
                return propertyName;
            }

            public void setPropertyName(String propertyName) {
                this.propertyName = propertyName;
            }

            public String getExerciseEndTime() {
                return exerciseEndTime;
            }

            public void setExerciseEndTime(String exerciseEndTime) {
                this.exerciseEndTime = exerciseEndTime;
            }

            public int getCirculation() {
                return circulation;
            }

            public void setCirculation(int circulation) {
                this.circulation = circulation;
            }

            public int getBid() {
                return bid;
            }

            public void setBid(int bid) {
                this.bid = bid;
            }
        }

        public static class AdoptCodeBean implements Serializable {
            private String videoSurveillanceInfo;
            private String adoptCode;
            private String photoAlbumInfo;
            private String updateTime;
            private int adoptStatus;
            private String createBy;
            private String createTime;
            private String updateBy;
            private int locationId;
            private int versionNo;
            private int adoptId;
            private int id;
            private int isDel;
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

            public AdoptCodeBean() {

            }

            /**
             * 通过其他对象转化进行处理
             */
            public AdoptCodeBean(String adoptCode, int id) {
                this.adoptCode = adoptCode;
                this.id = id;
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

            public String getPhotoAlbumInfo() {
                return photoAlbumInfo;
            }

            public void setPhotoAlbumInfo(String photoAlbumInfo) {
                this.photoAlbumInfo = photoAlbumInfo;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public int getAdoptStatus() {
                return adoptStatus;
            }

            public void setAdoptStatus(int adoptStatus) {
                this.adoptStatus = adoptStatus;
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

            public String getUpdateBy() {
                return updateBy;
            }

            public void setUpdateBy(String updateBy) {
                this.updateBy = updateBy;
            }

            public int getLocationId() {
                return locationId;
            }

            public void setLocationId(int locationId) {
                this.locationId = locationId;
            }

            public int getVersionNo() {
                return versionNo;
            }

            public void setVersionNo(int versionNo) {
                this.versionNo = versionNo;
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

            public int getIsDel() {
                return isDel;
            }

            public void setIsDel(int isDel) {
                this.isDel = isDel;
            }
        }
    }
}
