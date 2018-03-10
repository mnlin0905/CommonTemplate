package com.acchain.community.bean;

import com.acchain.community.interfaces.CommonProduceDetail;
import com.acchain.community.window.dialog.CommonGoodsAttrsBean;

import java.util.List;

/**
 * 抢购商品详情
 * Created by Administrator on 2018/1/22.
 */

public class PanicGoodDetail implements CommonProduceDetail{
    /**
     * flashSaleRefId : 1
     * isPastDue : 0
     * promotionCount : 0
     * isDeliver : 0
     * purchaseId : 1
     * quotaQty : 10
     * boughtQty : 0
     * flashProductPrice : 199.998
     * productName : 长白野山参
     * saleName : 限时抢购10:00
     * soldCount : 0
     * shareCount : 0
     * beginTime : 2018-01-04 10:00:00
     * productId : 1
     * productImg : group1/M00/00/02/wKgB_Vpi4tuAd3IyAAFTSLCgDUw599.png
     * attrList : [{"isImage":0,"attrId":1,"aliases":"尺码","productId":1,"attrValueList":[{"attrId":1,"attrValueId":1,"imageUrl":"","attrValue":"S"},{"attrId":1,"attrValueId":2,"imageUrl":"","attrValue":"m"}],"attrName":"尺码"},{"isImage":0,"attrId":2,"aliases":"颜色","productId":1,"attrValueList":[{"attrId":2,"attrValueId":3,"imageUrl":"","attrValue":"深蓝"},{"attrId":2,"attrValueId":4,"imageUrl":"","attrValue":"白色"}],"attrName":"颜色"},{"isImage":0,"attrId":3,"aliases":"净含量","productId":1,"attrValueList":[{"attrId":3,"attrValueId":5,"imageUrl":"","attrValue":"500g"},{"attrId":3,"attrValueId":6,"imageUrl":"","attrValue":"1000g"}],"attrName":"净含量"}]
     * flashProductQty : 100
     * flashSaleId : 1
     * detailDescApp : group1/M00/00/02/wKgB_Vpi4tuAd3IyAAFTSLCgDUw599.png
     * isForbidden : 0
     * productDiscountPrice : 198.000001
     * isRefund : 0
     * collectionCount : 0
     * shortDesc : 集长白之精华，采天地之灵气集长白之精华，采天地之灵气
     * productPackUnitId : kg
     * endTime : 2018-02-04 12:00:00
     * productQty : 20
     * isDel : 0
     * productPrice : 199.000001
     * isExemption : 0
     */

    private int flashSaleRefId;
    private int isPastDue;
    private int promotionCount;
    private int isDeliver;
    private int purchaseId;
    private int quotaQty;
    private int boughtQty;
    private double flashProductPrice;
    private String productName;
    private String saleName;
    private int soldCount;
    private int shareCount;
    private String beginTime;
    private int productId;
    private String productImg;
    private int flashProductQty;
    private int flashSaleId;
    private String detailDescApp;
    private int isForbidden;
    private double productDiscountPrice;
    private int isRefund;
    private int collectionCount;
    private String shortDesc;
    private String productPackUnitId;
    private String endTime;
    private int productQty;
    private int isDel;
    private double productPrice;
    private int isExemption;
    private List<CommonGoodsAttrsBean.AttrListBean> attrList;

    public int getFlashSaleRefId() {
        return flashSaleRefId;
    }

    public void setFlashSaleRefId(int flashSaleRefId) {
        this.flashSaleRefId = flashSaleRefId;
    }

    public int getIsPastDue() {
        return isPastDue;
    }

    public void setIsPastDue(int isPastDue) {
        this.isPastDue = isPastDue;
    }

    public int getPromotionCount() {
        return promotionCount;
    }

    public void setPromotionCount(int promotionCount) {
        this.promotionCount = promotionCount;
    }

    public int getIsDeliver() {
        return isDeliver;
    }

    public void setIsDeliver(int isDeliver) {
        this.isDeliver = isDeliver;
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

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
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

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
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

    public String getDetailDescApp() {
        return detailDescApp;
    }

    public void setDetailDescApp(String detailDescApp) {
        this.detailDescApp = detailDescApp;
    }

    public int getIsForbidden() {
        return isForbidden;
    }

    public void setIsForbidden(int isForbidden) {
        this.isForbidden = isForbidden;
    }

    public double getProductDiscountPrice() {
        return productDiscountPrice;
    }

    public void setProductDiscountPrice(double productDiscountPrice) {
        this.productDiscountPrice = productDiscountPrice;
    }

    public int getIsRefund() {
        return isRefund;
    }

    public void setIsRefund(int isRefund) {
        this.isRefund = isRefund;
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

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getProductQty() {
        return productQty;
    }

    public void setProductQty(int productQty) {
        this.productQty = productQty;
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

    public int getIsExemption() {
        return isExemption;
    }

    public void setIsExemption(int isExemption) {
        this.isExemption = isExemption;
    }

    public List<CommonGoodsAttrsBean.AttrListBean> getAttrList() {
        return attrList;
    }

    public void setAttrList(List<CommonGoodsAttrsBean.AttrListBean> attrList) {
        this.attrList = attrList;
    }

    @Override
    public double getOldPrice() {
        return productPrice;
    }

    @Override
    public double getCurrentPrice() {
        return flashProductPrice;
    }

    @Override
    public String getPriceUnit() {
        return "￥";
    }

    @Override
    public int getProgress() {
        return 0;
    }

    @Override
    public int getAdoptAmount() {
        return 0;
    }

    @Override
    public double getDividend() {
        return 0;
    }

    @Override
    public int getBetweenTime() {
        return 0;
    }

    @Override
    public String getTimeUnit() {
        return null;
    }

    @Override
    public String getIntroduce() {
        return shortDesc;
    }

/*    public static class AttrListBean {
        *//**
         * isImage : 0
         * attrId : 1
         * aliases : 尺码
         * productId : 1
         * attrValueList : [{"attrId":1,"attrValueId":1,"imageUrl":"","attrValue":"S"},{"attrId":1,"attrValueId":2,"imageUrl":"","attrValue":"m"}]
         * attrName : 尺码
         *//*

        private int isImage;
        private int attrId;
        private String aliases;
        private int productId;
        private String attrName;
        private List<AttrValueListBean> attrValueList;

        public int getIsImage() {
            return isImage;
        }

        public void setIsImage(int isImage) {
            this.isImage = isImage;
        }

        public int getAttrId() {
            return attrId;
        }

        public void setAttrId(int attrId) {
            this.attrId = attrId;
        }

        public String getAliases() {
            return aliases;
        }

        public void setAliases(String aliases) {
            this.aliases = aliases;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public String getAttrName() {
            return attrName;
        }

        public void setAttrName(String attrName) {
            this.attrName = attrName;
        }

        public List<AttrValueListBean> getAttrValueList() {
            return attrValueList;
        }

        public void setAttrValueList(List<AttrValueListBean> attrValueList) {
            this.attrValueList = attrValueList;
        }

//        public static class AttrValueListBean {
//            *//**
//             * attrId : 1
//             * attrValueId : 1
//             * imageUrl :
//             * attrValue : S
//             *//*
//
//            private int attrId;
//            private int attrValueId;
//            private String imageUrl;
//            private String attrValue;
//
//            public int getAttrId() {
//                return attrId;
//            }
//
//            public void setAttrId(int attrId) {
//                this.attrId = attrId;
//            }
//
//            public int getAttrValueId() {
//                return attrValueId;
//            }
//
//            public void setAttrValueId(int attrValueId) {
//                this.attrValueId = attrValueId;
//            }
//
//            public String getImageUrl() {
//                return imageUrl;
//            }
//
//            public void setImageUrl(String imageUrl) {
//                this.imageUrl = imageUrl;
//            }
//
//            public String getAttrValue() {
//                return attrValue;
//            }
//
//            public void setAttrValue(String attrValue) {
//                this.attrValue = attrValue;
//            }
//        }
    }*/
}
