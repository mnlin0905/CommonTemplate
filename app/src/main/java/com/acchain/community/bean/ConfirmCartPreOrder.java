package com.acchain.community.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/2/1.
 */

public class ConfirmCartPreOrder implements Serializable{

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
         * adoptCodeId : -1
         * productId : 1
         * cartType : 1
         * id : 1456
         * bid : 1
         * productSubId : 2
         * memberId : 123
         * itemCount : 5
         * productAttr : 尺码 : S,颜色 : 深蓝
         * products : {"traceSource":"","productId":2,"presellEndTime":"2018-02-04 10:00:00","exerciseStartTime":"2018-02-04 10:00:00","productImg":"group1/M00/00/02/wKgB_Vpi4tuAd3IyAAFTSLCgDUw599.png","browseNumber":0,"exercisePrice":199.000004,"presellSoldCount":0,"freightCharge":10,"presellStartTime":"2018-01-24 10:00:00","productTypeId":4,"productName":"雪山古树1","isPutaway":0,"presellPrice":199.000003,"presellCirculation":20,"exerciseEndTime":"2018-02-04 10:00:00","productDiscountPrice":299.000002,"circulation":100,"shortDesc":"雪山古树2010普洱生茶，茶饼外形完整紧实，撒面肥壮、显毫，香气陈香，滋味醇厚，汤色红褐明亮，叶底红褐、明亮、柔软，具有较高的市场价值。","productPackUnitId":"kg","presellId":2,"currencyId":0,"bid":0,"productPrice":399.000002}
         */

        private int flashSaleRefId;
        private String adoptCodeId;
        private int productId;
        private int cartType;
        private int id;
        private int bid;
        private int productSubId;
        private int memberId;
        private int itemCount;
        private String productAttr;
        private ProductsBean products;

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

        public int getBid() {
            return bid;
        }

        public void setBid(int bid) {
            this.bid = bid;
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

        public static class ProductsBean implements Serializable{
            /**
             * traceSource :
             * productId : 2
             * presellEndTime : 2018-02-04 10:00:00
             * exerciseStartTime : 2018-02-04 10:00:00
             * productImg : group1/M00/00/02/wKgB_Vpi4tuAd3IyAAFTSLCgDUw599.png
             * browseNumber : 0
             * exercisePrice : 199.000004
             * presellSoldCount : 0
             * freightCharge : 10
             * presellStartTime : 2018-01-24 10:00:00
             * productTypeId : 4
             * productName : 雪山古树1
             * isPutaway : 0
             * presellPrice : 199.000003
             * presellCirculation : 20
             * exerciseEndTime : 2018-02-04 10:00:00
             * productDiscountPrice : 299.000002
             * circulation : 100
             * shortDesc : 雪山古树2010普洱生茶，茶饼外形完整紧实，撒面肥壮、显毫，香气陈香，滋味醇厚，汤色红褐明亮，叶底红褐、明亮、柔软，具有较高的市场价值。
             * productPackUnitId : kg
             * presellId : 2
             * currencyId : 0
             * bid : 0
             * productPrice : 399.000002
             */

            private String traceSource;
            private int productId;
            private String presellEndTime;
            private String exerciseStartTime;
            private String productImg;
            private int browseNumber;
            private double exercisePrice;
            private int presellSoldCount;
            private int freightCharge;
            private String presellStartTime;
            private int productTypeId;
            private String productName;
            private int isPutaway;
            private double presellPrice;
            private int presellCirculation;
            private String exerciseEndTime;
            private double productDiscountPrice;
            private int circulation;
            private String shortDesc;
            private String productPackUnitId;
            private int presellId;
            private int currencyId;
            private int bid;
            private double productPrice;

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

            public String getPresellEndTime() {
                return presellEndTime;
            }

            public void setPresellEndTime(String presellEndTime) {
                this.presellEndTime = presellEndTime;
            }

            public String getExerciseStartTime() {
                return exerciseStartTime;
            }

            public void setExerciseStartTime(String exerciseStartTime) {
                this.exerciseStartTime = exerciseStartTime;
            }

            public String getProductImg() {
                return productImg;
            }

            public void setProductImg(String productImg) {
                this.productImg = productImg;
            }

            public int getBrowseNumber() {
                return browseNumber;
            }

            public void setBrowseNumber(int browseNumber) {
                this.browseNumber = browseNumber;
            }

            public double getExercisePrice() {
                return exercisePrice;
            }

            public void setExercisePrice(double exercisePrice) {
                this.exercisePrice = exercisePrice;
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

            public int getProductTypeId() {
                return productTypeId;
            }

            public void setProductTypeId(int productTypeId) {
                this.productTypeId = productTypeId;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
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

            public String getExerciseEndTime() {
                return exerciseEndTime;
            }

            public void setExerciseEndTime(String exerciseEndTime) {
                this.exerciseEndTime = exerciseEndTime;
            }

            public double getProductDiscountPrice() {
                return productDiscountPrice;
            }

            public void setProductDiscountPrice(double productDiscountPrice) {
                this.productDiscountPrice = productDiscountPrice;
            }

            public int getCirculation() {
                return circulation;
            }

            public void setCirculation(int circulation) {
                this.circulation = circulation;
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

            public int getPresellId() {
                return presellId;
            }

            public void setPresellId(int presellId) {
                this.presellId = presellId;
            }

            public int getCurrencyId() {
                return currencyId;
            }

            public void setCurrencyId(int currencyId) {
                this.currencyId = currencyId;
            }

            public int getBid() {
                return bid;
            }

            public void setBid(int bid) {
                this.bid = bid;
            }

            public double getProductPrice() {
                return productPrice;
            }

            public void setProductPrice(double productPrice) {
                this.productPrice = productPrice;
            }
        }
    }
}
