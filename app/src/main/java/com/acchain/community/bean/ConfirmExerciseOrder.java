package com.acchain.community.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/26.
 */

public class ConfirmExerciseOrder implements Serializable{


    private List<OrderInfoBean> orderInfo;

    public List<OrderInfoBean> getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(List<OrderInfoBean> orderInfo) {
        this.orderInfo = orderInfo;
    }

    public static class OrderInfoBean implements Serializable{
        /**
         * productSpecification : 357g/饼
         * exerciseId : 1
         * isDeliver : 0
         * productBrand : 太姥山
         * browseNumber : 0
         * exercisePrice : 198
         * productName : 长白野山参
         * exerciseSpecification : 10 HCYH
         * productAttr :
         * isPutaway : 0
         * currencyId : 1
         * traceSource : 6666666666666666666
         * productId : 1
         * exerciseStartTime : 2018-02-18 00:00:00
         * exerciseUnits : 饼
         * productImg : group1/M00/00/02/wKgB_Vpi4tuAd3IyAAFTSLCgDUw599.png
         * freightCharge : 10
         * productionPlace : 福建福鼎
         * productTypeId : 7
         * traderAddress :
         * itemCount : 1
         * productSaleId : 1
         * propertyCurrency : TLS.HCYH
         * detailDesc : group1/M00/00/00/wKgAyVgFk9aAB8hwAA-8Q6_7tHw351.jpg
         * blockHeight : 101
         * propertyName : 华茶壹号
         * exerciseCount : 0
         * exerciseEndTime : 2018-05-18 00:00:00
         * isRefund : 0
         * circulation : 100
         * assetsPrecision : 100
         * shortDesc : 集长白之精华，采天地之灵气集长白之精华，采天地之灵气
         * isDel : 0
         * isExemption : 0
         */

        private String productSpecification;
        private int exerciseId;
        private int isDeliver;
        private String productBrand;
        private int browseNumber;
        private int exercisePrice;
        private String productName;
        private String exerciseSpecification;
        private String productAttr;
        private int isPutaway;
        private int currencyId;
        private String traceSource;
        private int productId;
        private String exerciseStartTime;
        private String exerciseUnits;
        private String productImg;
        private int freightCharge;
        private String productionPlace;
        private int productTypeId;
        private String traderAddress;
        private int itemCount;
        private int productSaleId;
        private String propertyCurrency;
        private String detailDesc;
        private int blockHeight;
        private String propertyName;
        private int exerciseCount;
        private String exerciseEndTime;
        private int isRefund;
        private int circulation;
        private int assetsPrecision;
        private String shortDesc;
        private int isDel;
        private int isExemption;

        public String getProductSpecification() {
            return productSpecification;
        }

        public void setProductSpecification(String productSpecification) {
            this.productSpecification = productSpecification;
        }

        public int getExerciseId() {
            return exerciseId;
        }

        public void setExerciseId(int exerciseId) {
            this.exerciseId = exerciseId;
        }

        public int getIsDeliver() {
            return isDeliver;
        }

        public void setIsDeliver(int isDeliver) {
            this.isDeliver = isDeliver;
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

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getExerciseSpecification() {
            return exerciseSpecification;
        }

        public void setExerciseSpecification(String exerciseSpecification) {
            this.exerciseSpecification = exerciseSpecification;
        }

        public String getProductAttr() {
            return productAttr;
        }

        public void setProductAttr(String productAttr) {
            this.productAttr = productAttr;
        }

        public int getIsPutaway() {
            return isPutaway;
        }

        public void setIsPutaway(int isPutaway) {
            this.isPutaway = isPutaway;
        }

        public int getCurrencyId() {
            return currencyId;
        }

        public void setCurrencyId(int currencyId) {
            this.currencyId = currencyId;
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

        public String getExerciseStartTime() {
            return exerciseStartTime;
        }

        public void setExerciseStartTime(String exerciseStartTime) {
            this.exerciseStartTime = exerciseStartTime;
        }

        public String getExerciseUnits() {
            return exerciseUnits;
        }

        public void setExerciseUnits(String exerciseUnits) {
            this.exerciseUnits = exerciseUnits;
        }

        public String getProductImg() {
            return productImg;
        }

        public void setProductImg(String productImg) {
            this.productImg = productImg;
        }

        public int getFreightCharge() {
            return freightCharge;
        }

        public void setFreightCharge(int freightCharge) {
            this.freightCharge = freightCharge;
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

        public String getTraderAddress() {
            return traderAddress;
        }

        public void setTraderAddress(String traderAddress) {
            this.traderAddress = traderAddress;
        }

        public int getItemCount() {
            return itemCount;
        }

        public void setItemCount(int itemCount) {
            this.itemCount = itemCount;
        }

        public int getProductSaleId() {
            return productSaleId;
        }

        public void setProductSaleId(int productSaleId) {
            this.productSaleId = productSaleId;
        }

        public String getPropertyCurrency() {
            return propertyCurrency;
        }

        public void setPropertyCurrency(String propertyCurrency) {
            this.propertyCurrency = propertyCurrency;
        }

        public String getDetailDesc() {
            return detailDesc;
        }

        public void setDetailDesc(String detailDesc) {
            this.detailDesc = detailDesc;
        }

        public int getBlockHeight() {
            return blockHeight;
        }

        public void setBlockHeight(int blockHeight) {
            this.blockHeight = blockHeight;
        }

        public String getPropertyName() {
            return propertyName;
        }

        public void setPropertyName(String propertyName) {
            this.propertyName = propertyName;
        }

        public int getExerciseCount() {
            return exerciseCount;
        }

        public void setExerciseCount(int exerciseCount) {
            this.exerciseCount = exerciseCount;
        }

        public String getExerciseEndTime() {
            return exerciseEndTime;
        }

        public void setExerciseEndTime(String exerciseEndTime) {
            this.exerciseEndTime = exerciseEndTime;
        }

        public int getIsRefund() {
            return isRefund;
        }

        public void setIsRefund(int isRefund) {
            this.isRefund = isRefund;
        }

        public int getCirculation() {
            return circulation;
        }

        public void setCirculation(int circulation) {
            this.circulation = circulation;
        }

        public int getAssetsPrecision() {
            return assetsPrecision;
        }

        public void setAssetsPrecision(int assetsPrecision) {
            this.assetsPrecision = assetsPrecision;
        }

        public String getShortDesc() {
            return shortDesc;
        }

        public void setShortDesc(String shortDesc) {
            this.shortDesc = shortDesc;
        }

        public int getIsDel() {
            return isDel;
        }

        public void setIsDel(int isDel) {
            this.isDel = isDel;
        }

        public int getIsExemption() {
            return isExemption;
        }

        public void setIsExemption(int isExemption) {
            this.isExemption = isExemption;
        }
    }
}
