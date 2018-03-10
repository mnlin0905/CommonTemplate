package com.acchain.community.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/26.
 */

public class ConfirmPreOrder implements Serializable{
    private List<OrderInfoBean> orderInfo;

    public List<OrderInfoBean> getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(List<OrderInfoBean> orderInfo) {
        this.orderInfo = orderInfo;
    }

    public static class OrderInfoBean implements Serializable{
        /**
         * presellEndTime : 2018-02-04 10:00:00
         * productImg : group1/M00/00/02/wKgB_Vpi4tuAd3IyAAFTSLCgDUw599.png
         * presellSoldCount : 0
         * freightCharge : 10
         * presellStartTime : 2018-01-24 10:00:00
         * productName : 长白野山参
         * itemCount : 1
         * productAttr :
         * productSaleId : 1
         * isPutaway : 0
         * presellPrice : 198.000003
         * presellCirculation : 20
         * shortDesc : 集长白之精华，采天地之灵气集长白之精华，采天地之灵气
         * presellId : 1
         * bid : 0
         * isDel : 0
         */

        private String presellEndTime;
        private String productImg;
        private int presellSoldCount;
        private int freightCharge;
        private String presellStartTime;
        private String productName;
        private int itemCount;
        private String productAttr;
        private int productSaleId;
        private int isPutaway;
        private double presellPrice;
        private int presellCirculation;
        private String shortDesc;
        private int presellId;
        private int bid;
        private int isDel;

        public String getPresellEndTime() {
            return presellEndTime;
        }

        public void setPresellEndTime(String presellEndTime) {
            this.presellEndTime = presellEndTime;
        }

        public String getProductImg() {
            return productImg;
        }

        public void setProductImg(String productImg) {
            this.productImg = productImg;
        }

        public int getPresellSoldCount() {
            return presellSoldCount;
        }

        public void setPresellSoldCount(int presellSoldCount) {
            this.presellSoldCount = presellSoldCount;
        }

        public int getFreightCharge() {
            return freightCharge;
        }

        public void setFreightCharge(int freightCharge) {
            this.freightCharge = freightCharge;
        }

        public String getPresellStartTime() {
            return presellStartTime;
        }

        public void setPresellStartTime(String presellStartTime) {
            this.presellStartTime = presellStartTime;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
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

        public int getProductSaleId() {
            return productSaleId;
        }

        public void setProductSaleId(int productSaleId) {
            this.productSaleId = productSaleId;
        }

        public int getIsPutaway() {
            return isPutaway;
        }

        public void setIsPutaway(int isPutaway) {
            this.isPutaway = isPutaway;
        }

        public double getPresellPrice() {
            return presellPrice;
        }

        public void setPresellPrice(double presellPrice) {
            this.presellPrice = presellPrice;
        }

        public int getPresellCirculation() {
            return presellCirculation;
        }

        public void setPresellCirculation(int presellCirculation) {
            this.presellCirculation = presellCirculation;
        }

        public String getShortDesc() {
            return shortDesc;
        }

        public void setShortDesc(String shortDesc) {
            this.shortDesc = shortDesc;
        }

        public int getPresellId() {
            return presellId;
        }

        public void setPresellId(int presellId) {
            this.presellId = presellId;
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
    }
}
