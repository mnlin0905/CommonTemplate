package com.acchain.community.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/26.
 */

public class ConfirmPanicOrder implements Serializable{


    private List<OrderInfoBean> orderInfo;

    public List<OrderInfoBean> getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(List<OrderInfoBean> orderInfo) {
        this.orderInfo = orderInfo;
    }

    public static class OrderInfoBean implements Serializable{
        /**
         * flashSaleRefId : 1
         * promotionCount : 0
         * purchaseId : 1
         * quotaQty : 10
         * boughtQty : 0
         * productImg2 :
         * productImg3 :
         * flashProductPrice : 199.998
         * productName : 长白野山参
         * productImg1 :
         * productAttr :
         * soldCount : 0
         * shareCount : 0
         * productSaleTypeId : 1
         * productImg : group1/M00/00/02/wKgB_Vpi4tuAd3IyAAFTSLCgDUw599.png
         * itemCount : 1
         * productSaleId : 1
         * flashProductQty : 100
         * flashSaleId : 1
         * productImg4 :
         * detailDescApp : group1/M00/00/02/wKgB_Vpi4tuAd3IyAAFTSLCgDUw599.png
         * productImg5 :
         * productDiscountPrice : 198.000001
         * collectionCount : 0
         * shortDesc : 集长白之精华，采天地之灵气集长白之精华，采天地之灵气
         * productPackUnitId : kg
         * productQty : 20
         * bid : 0
         * isDel : 0
         * productPrice : 199.000001
         */

        private int flashSaleRefId;
        private int promotionCount;
        private int purchaseId;
        private int quotaQty;
        private int boughtQty;
        private String productImg2;
        private String productImg3;
        private double flashProductPrice;
        private String productName;
        private String productImg1;
        private String productAttr;
        private int soldCount;
        private int shareCount;
        private int productSaleTypeId;
        private String productImg;
        private int itemCount;
        private int productSaleId;
        private int flashProductQty;
        private int flashSaleId;
        private String productImg4;
        private String detailDescApp;
        private String productImg5;
        private double productDiscountPrice;
        private int collectionCount;
        private String shortDesc;
        private String productPackUnitId;
        private int productQty;
        private int bid;
        private int isDel;
        private double productPrice;

        public int getFlashSaleRefId() {
            return flashSaleRefId;
        }

        public void setFlashSaleRefId(int flashSaleRefId) {
            this.flashSaleRefId = flashSaleRefId;
        }

        public int getPromotionCount() {
            return promotionCount;
        }

        public void setPromotionCount(int promotionCount) {
            this.promotionCount = promotionCount;
        }

        public int getPurchaseId() {
            return purchaseId;
        }

        public void setPurchaseId(int purchaseId) {
            this.purchaseId = purchaseId;
        }

        public int getQuotaQty() {
            return quotaQty;
        }

        public void setQuotaQty(int quotaQty) {
            this.quotaQty = quotaQty;
        }

        public int getBoughtQty() {
            return boughtQty;
        }

        public void setBoughtQty(int boughtQty) {
            this.boughtQty = boughtQty;
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

        public double getFlashProductPrice() {
            return flashProductPrice;
        }

        public void setFlashProductPrice(double flashProductPrice) {
            this.flashProductPrice = flashProductPrice;
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

        public String getProductAttr() {
            return productAttr;
        }

        public void setProductAttr(String productAttr) {
            this.productAttr = productAttr;
        }

        public int getSoldCount() {
            return soldCount;
        }

        public void setSoldCount(int soldCount) {
            this.soldCount = soldCount;
        }

        public int getShareCount() {
            return shareCount;
        }

        public void setShareCount(int shareCount) {
            this.shareCount = shareCount;
        }

        public int getProductSaleTypeId() {
            return productSaleTypeId;
        }

        public void setProductSaleTypeId(int productSaleTypeId) {
            this.productSaleTypeId = productSaleTypeId;
        }

        public String getProductImg() {
            return productImg;
        }

        public void setProductImg(String productImg) {
            this.productImg = productImg;
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

        public int getFlashProductQty() {
            return flashProductQty;
        }

        public void setFlashProductQty(int flashProductQty) {
            this.flashProductQty = flashProductQty;
        }

        public int getFlashSaleId() {
            return flashSaleId;
        }

        public void setFlashSaleId(int flashSaleId) {
            this.flashSaleId = flashSaleId;
        }

        public String getProductImg4() {
            return productImg4;
        }

        public void setProductImg4(String productImg4) {
            this.productImg4 = productImg4;
        }

        public String getDetailDescApp() {
            return detailDescApp;
        }

        public void setDetailDescApp(String detailDescApp) {
            this.detailDescApp = detailDescApp;
        }

        public String getProductImg5() {
            return productImg5;
        }

        public void setProductImg5(String productImg5) {
            this.productImg5 = productImg5;
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

        public String getShortDesc() {
            return shortDesc;
        }

        public void setShortDesc(String shortDesc) {
            this.shortDesc = shortDesc;
        }

        public String getProductPackUnitId() {
            return productPackUnitId;
        }

        public void setProductPackUnitId(String productPackUnitId) {
            this.productPackUnitId = productPackUnitId;
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
    }
}
