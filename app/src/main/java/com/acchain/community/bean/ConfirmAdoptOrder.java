package com.acchain.community.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/26.
 */

public class ConfirmAdoptOrder implements Serializable {

    private List<OrderInfoBean> orderInfo;

    public List<OrderInfoBean> getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(List<OrderInfoBean> orderInfo) {
        this.orderInfo = orderInfo;
    }

    public static class OrderInfoBean implements Serializable {
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
         * currencyId : -1
         * isAutoExercise : 1
         * traceSource :
         * productId : 1
         * adoptStartTime : 2018-02-18 00:00:00
         * adoptCode : [{"videoSurveillanceInfo":"","adoptCode":"HDS01-0003","photoAlbumInfo":"","updateTime":"2018-01-18 00:00:00","adoptStatus":0,"createBy":"","createTime":"2018-03-18 00:00:00","updateBy":"","locationId":1,"versionNo":0,"adoptId":1,"id":3,"isDel":0}]
         * adoptCirculation : 2000
         * exerciseStartTime : 2018-04-18 10:00:00
         * productionPlace : 红豆杉产地
         * productTypeId : 5
         * propertyCurrency : 红豆杉资产币种
         * propertyName : 红豆杉
         * exerciseEndTime : 2018-05-18 10:00:00
         * circulation : 100000
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
        private List<AdoptGoodDetail.DefaultAdoptCodeBean> adoptCode;

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

        public List<AdoptGoodDetail.DefaultAdoptCodeBean> getAdoptCode() {
            return adoptCode;
        }

        public void setAdoptCode(List<AdoptGoodDetail.DefaultAdoptCodeBean> adoptCode) {
            this.adoptCode = adoptCode;
        }
    }
}
