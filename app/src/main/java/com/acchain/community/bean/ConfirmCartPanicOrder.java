package com.acchain.community.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/31.
 */

public class ConfirmCartPanicOrder implements Serializable {

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
         * cartType : 0
         * id : 1431
         * bid : 1
         * productSubId : 1
         * memberId : 57
         * itemCount : 9
         * productAttr : 尺码 :  m,颜色 :  白色,净含量 :  1000g
         * products : {"flashSaleRefId":1,"purchaseId":1,"productImg":"group1/M00/00/02/wKgB_Vpi4tuAd3IyAAFTSLCgDUw599.png","quotaQty":10,"boughtQty":0,"flashProductPrice":199.998,"productName":"长白野山参","flashProductQty":100,"productSaleId":1,"saleProductDiscountPrice":198.000001,"shortDesc":"集长白之精华，采天地之灵气集长白之精华，采天地之灵气","bid":0,"saleProductPrice":199.000001,"isExemption":0}
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
             * flashSaleRefId : 1
             * purchaseId : 1
             * productImg : group1/M00/00/02/wKgB_Vpi4tuAd3IyAAFTSLCgDUw599.png
             * quotaQty : 10
             * boughtQty : 0
             * flashProductPrice : 199.998
             * productName : 长白野山参
             * flashProductQty : 100
             * productSaleId : 1
             * saleProductDiscountPrice : 198.000001
             * shortDesc : 集长白之精华，采天地之灵气集长白之精华，采天地之灵气
             * bid : 0
             * saleProductPrice : 199.000001
             * isExemption : 0
             */

            private int flashSaleRefId;
            private int purchaseId;
            private String productImg;
            private int quotaQty;
            private int boughtQty;
            private double flashProductPrice;
            private String productName;
            private int flashProductQty;
            private int productSaleId;
            private double saleProductDiscountPrice;
            private String shortDesc;
            private int bid;
            private double saleProductPrice;
            private int isExemption;

            public int getFlashSaleRefId() {
                return flashSaleRefId;
            }

            public void setFlashSaleRefId(int flashSaleRefId) {
                this.flashSaleRefId = flashSaleRefId;
            }

            public int getPurchaseId() {
                return purchaseId;
            }

            public void setPurchaseId(int purchaseId) {
                this.purchaseId = purchaseId;
            }

            public String getProductImg() {
                return productImg;
            }

            public void setProductImg(String productImg) {
                this.productImg = productImg;
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

            public int getFlashProductQty() {
                return flashProductQty;
            }

            public void setFlashProductQty(int flashProductQty) {
                this.flashProductQty = flashProductQty;
            }

            public int getProductSaleId() {
                return productSaleId;
            }

            public void setProductSaleId(int productSaleId) {
                this.productSaleId = productSaleId;
            }

            public double getSaleProductDiscountPrice() {
                return saleProductDiscountPrice;
            }

            public void setSaleProductDiscountPrice(double saleProductDiscountPrice) {
                this.saleProductDiscountPrice = saleProductDiscountPrice;
            }

            public String getShortDesc() {
                return shortDesc;
            }

            public void setShortDesc(String shortDesc) {
                this.shortDesc = shortDesc;
            }

            public int getBid() {
                return bid;
            }

            public void setBid(int bid) {
                this.bid = bid;
            }

            public double getSaleProductPrice() {
                return saleProductPrice;
            }

            public void setSaleProductPrice(double saleProductPrice) {
                this.saleProductPrice = saleProductPrice;
            }

            public int getIsExemption() {
                return isExemption;
            }

            public void setIsExemption(int isExemption) {
                this.isExemption = isExemption;
            }
        }
    }
}
