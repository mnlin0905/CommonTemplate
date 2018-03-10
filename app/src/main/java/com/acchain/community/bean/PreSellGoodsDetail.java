package com.acchain.community.bean;

import com.acchain.community.interfaces.CommonProduceDetail;
import com.acchain.community.window.dialog.CommonGoodsAttrsBean;

import java.util.List;

/**
 * 预购商品详情
 *
 * Created by Administrator on 2018/1/24.
 */

public class PreSellGoodsDetail implements CommonProduceDetail{

    /**
     * productSpecification : 357g/饼
     * productBrand : 太姥山
     * browseNumber : 0
     * presellSoldCount : 10
     * exercisePrice : 198.000004
     * exerciseSpecification : 10 HCYH
     * products : [{"soldCount":0,"productDiscountPrice":198.000001,"productImg":"group1/M00/00/02/wKgB_Vpi4tuAd3IyAAFTSLCgDUw599.png","shortDesc":"集长白之精华，采天地之灵气集长白之精华，采天地之灵气","productPackUnitId":"kg","id":1,"productQty":20,"bid":0,"productName":"长白野山参","productPrice":199.000001,"productAttrList":[{"isImage":0,"attrId":1,"aliases":"尺码","productId":1,"attrValueList":[{"attrId":1,"attrValueId":1,"imageUrl":"","attrValue":"S"},{"attrId":1,"attrValueId":2,"imageUrl":"","attrValue":"m"}],"attrName":"尺码"},{"isImage":0,"attrId":2,"aliases":"颜色","productId":1,"attrValueList":[{"attrId":2,"attrValueId":3,"imageUrl":"","attrValue":"深蓝"},{"attrId":2,"attrValueId":4,"imageUrl":"","attrValue":"白色"}],"attrName":"颜色"},{"isImage":0,"attrId":3,"aliases":"净含量","productId":1,"attrValueList":[{"attrId":3,"attrValueId":5,"imageUrl":"","attrValue":"500g"},{"attrId":3,"attrValueId":6,"imageUrl":"","attrValue":"1000g"}],"attrName":"净含量"}]}]
     * isPutaway : 0
     * id : 1
     * currencyId : 0
     * isAutoExercise : 0
     * traceSource :
     * productId : 1
     * presellEndTime : 2018-02-04 10:00:00
     * exerciseStartTime : 2018-02-04 10:00:00
     * freightCharge : 10
     * presellStartTime : 2018-01-23 18:09:00
     * productionPlace : 福建福鼎
     * productTypeId : 3
     * propertyCurrency : TLS.HCYH
     * presellPrice : 198.000003
     * presellCirculation : 20
     * propertyName : 华茶壹号
     * exerciseEndTime : 2018-02-04 10:00:00
     * circulation : 100
     * isDel : 0
     */

    private String productSpecification;
    private String productBrand;
    private int browseNumber;
    private int presellSoldCount;
    private double exercisePrice;
    private String exerciseSpecification;
    private int isPutaway;
    private int id;
    private int currencyId;
    private int isAutoExercise;
    private String traceSource;
    private int productId;
    private String presellEndTime;
    private String exerciseStartTime;
    private int freightCharge;
    private String presellStartTime;
    private String productionPlace;
    private int productTypeId;
    private String propertyCurrency;
    private double presellPrice;
    private int presellCirculation;
    private String propertyName;
    private String exerciseEndTime;
    private int circulation;
    private int isDel;
    private List<ProductsBean> products;

    public String getProductSpecification() {
        return productSpecification;
    }

    public void setProductSpecification(String productSpecification) {
        this.productSpecification = productSpecification;
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

    public int getPresellSoldCount() {
        return presellSoldCount;
    }

    public void setPresellSoldCount(int presellSoldCount) {
        this.presellSoldCount = presellSoldCount;
    }

    public double getExercisePrice() {
        return exercisePrice;
    }

    public void setExercisePrice(double exercisePrice) {
        this.exercisePrice = exercisePrice;
    }

    public String getExerciseSpecification() {
        return exerciseSpecification;
    }

    public void setExerciseSpecification(String exerciseSpecification) {
        this.exerciseSpecification = exerciseSpecification;
    }

    public int getIsPutaway() {
        return isPutaway;
    }

    public void setIsPutaway(int isPutaway) {
        this.isPutaway = isPutaway;
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

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

    public List<ProductsBean> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsBean> products) {
        this.products = products;
    }

    @Override
    public double getOldPrice() {
        return presellPrice;
    }

    @Override
    public double getCurrentPrice() {
        return presellPrice;
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
        return ProductsBean.shortDesc;
    }

    public static class ProductsBean {
        /**
         * soldCount : 0
         * productDiscountPrice : 198.000001
         * productImg : group1/M00/00/02/wKgB_Vpi4tuAd3IyAAFTSLCgDUw599.png
         * shortDesc : 集长白之精华，采天地之灵气集长白之精华，采天地之灵气
         * productPackUnitId : kg
         * id : 1
         * productQty : 20
         * bid : 0
         * productName : 长白野山参
         * productPrice : 199.000001
         * productAttrList : [{"isImage":0,"attrId":1,"aliases":"尺码","productId":1,"attrValueList":[{"attrId":1,"attrValueId":1,"imageUrl":"","attrValue":"S"},{"attrId":1,"attrValueId":2,"imageUrl":"","attrValue":"m"}],"attrName":"尺码"},{"isImage":0,"attrId":2,"aliases":"颜色","productId":1,"attrValueList":[{"attrId":2,"attrValueId":3,"imageUrl":"","attrValue":"深蓝"},{"attrId":2,"attrValueId":4,"imageUrl":"","attrValue":"白色"}],"attrName":"颜色"},{"isImage":0,"attrId":3,"aliases":"净含量","productId":1,"attrValueList":[{"attrId":3,"attrValueId":5,"imageUrl":"","attrValue":"500g"},{"attrId":3,"attrValueId":6,"imageUrl":"","attrValue":"1000g"}],"attrName":"净含量"}]
         */

        private int soldCount;
        private double productDiscountPrice;
        private String productImg;
        private static String shortDesc;
        private String productPackUnitId;
        private int id;
        private int productQty;
        private int bid;
        private String productName;
        private double productPrice;
        private List<CommonGoodsAttrsBean.AttrListBean> productAttrList;

        public int getSoldCount() {
            return soldCount;
        }

        public void setSoldCount(int soldCount) {
            this.soldCount = soldCount;
        }

        public double getProductDiscountPrice() {
            return productDiscountPrice;
        }

        public void setProductDiscountPrice(double productDiscountPrice) {
            this.productDiscountPrice = productDiscountPrice;
        }

        public String getProductImg() {
            return productImg;
        }

        public void setProductImg(String productImg) {
            this.productImg = productImg;
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public double getProductPrice() {
            return productPrice;
        }

        public void setProductPrice(double productPrice) {
            this.productPrice = productPrice;
        }

        public List<CommonGoodsAttrsBean.AttrListBean> getProductAttrList() {
            return productAttrList;
        }

        public void setProductAttrList(List<CommonGoodsAttrsBean.AttrListBean> productAttrList) {
            this.productAttrList = productAttrList;
        }
    }
}
