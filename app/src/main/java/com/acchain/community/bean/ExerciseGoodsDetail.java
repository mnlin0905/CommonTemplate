package com.acchain.community.bean;

import com.acchain.community.interfaces.CommonProduceDetail;
import com.acchain.community.window.dialog.CommonGoodsAttrsBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/30.
 */

public class ExerciseGoodsDetail implements CommonProduceDetail{

    /**
     * productSpecification : 357g/饼
     * exerciseId : 1
     * isDeliver : 0
     * productBrand : 太姥山
     * browseNumber : 0
     * exercisePrice : 198
     * exerciseSpecification : 10 HCYH
     * products : [{"soldCount":0,"productDiscountPrice":198.000001,"productImg":"group1/M00/00/02/wKgB_Vpi4tuAd3IyAAFTSLCgDUw599.png","shortDesc":"集长白之精华，采天地之灵气集长白之精华，采天地之灵气","productPackUnitId":"kg","id":1,"productQty":20,"bid":0,"productName":"长白野山参","productPrice":199.000001,"productAttrList":[{"isImage":0,"attrId":1,"aliases":"尺码","productId":1,"attrValueList":[{"attrId":1,"attrValueId":1,"imageUrl":"","attrValue":"S"},{"attrId":1,"attrValueId":2,"imageUrl":"","attrValue":"m"}],"attrName":"尺码"},{"isImage":0,"attrId":2,"aliases":"颜色","productId":1,"attrValueList":[{"attrId":2,"attrValueId":3,"imageUrl":"","attrValue":"深蓝"},{"attrId":2,"attrValueId":4,"imageUrl":"","attrValue":"白色"}],"attrName":"颜色"}]}]
     * isPutaway : 0
     * currencyId : 1
     * traceSource :
     * exerciseStartTime : 2018-01-18 00:00:00
     * exerciseUnits : 饼
     * freightCharge : 10
     * productionPlace : 福建福鼎
     * productTypeId : 7
     * traderAddress :
     * productSaleId : 1
     * propertyCurrency : TLS.HCYH
     * detailDesc : group1/M00/00/00/wKgAyVgFk9aAB8hwAA-8Q6_7tHw351.jpg
     * blockHeight : 101
     * propertyName : 华茶壹号
     * exerciseEndTime : 2018-05-18 00:00:00
     * isRefund : 0
     * circulation : 100
     * assetsPrecision : 100
     * isDel : 0
     * isExemption : 0
     */

    private String productSpecification;
    private int exerciseId;
    private int isDeliver;
    private String productBrand;
    private int browseNumber;
    private int exercisePrice;
    private String exerciseSpecification;
    private int isPutaway;
    private int currencyId;
    private String traceSource;
    private String exerciseStartTime;
    private String exerciseUnits;
    private int freightCharge;
    private String productionPlace;
    private int productTypeId;
    private String traderAddress;
    private int productSaleId;
    private String propertyCurrency;
    private String detailDesc;
    private int blockHeight;
    private String propertyName;
    private String exerciseEndTime;
    private int isRefund;
    private int circulation;
    private int assetsPrecision;
    private int isDel;
    private int isExemption;
    private List<ProductsBean> products;

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

    public List<ProductsBean> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsBean> products) {
        this.products = products;
    }

    @Override
    public double getOldPrice() {
        return exercisePrice;
    }

    @Override
    public double getCurrentPrice() {
        return exercisePrice;
    }

    @Override
    public String getPriceUnit() {
        return exerciseUnits;
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
         * productAttrList : [{"isImage":0,"attrId":1,"aliases":"尺码","productId":1,"attrValueList":[{"attrId":1,"attrValueId":1,"imageUrl":"","attrValue":"S"},{"attrId":1,"attrValueId":2,"imageUrl":"","attrValue":"m"}],"attrName":"尺码"},{"isImage":0,"attrId":2,"aliases":"颜色","productId":1,"attrValueList":[{"attrId":2,"attrValueId":3,"imageUrl":"","attrValue":"深蓝"},{"attrId":2,"attrValueId":4,"imageUrl":"","attrValue":"白色"}],"attrName":"颜色"}]
         */

        private int soldCount;
        private double productDiscountPrice;
        private String productImg;
        public static String shortDesc;
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

//        public static class ProductAttrListBean {
//            /**
//             * isImage : 0
//             * attrId : 1
//             * aliases : 尺码
//             * productId : 1
//             * attrValueList : [{"attrId":1,"attrValueId":1,"imageUrl":"","attrValue":"S"},{"attrId":1,"attrValueId":2,"imageUrl":"","attrValue":"m"}]
//             * attrName : 尺码
//             */
//
//            private int isImage;
//            private int attrId;
//            private String aliases;
//            private int productId;
//            private String attrName;
//            private List<AttrValueListBean> attrValueList;
//
//            public int getIsImage() {
//                return isImage;
//            }
//
//            public void setIsImage(int isImage) {
//                this.isImage = isImage;
//            }
//
//            public int getAttrId() {
//                return attrId;
//            }
//
//            public void setAttrId(int attrId) {
//                this.attrId = attrId;
//            }
//
//            public String getAliases() {
//                return aliases;
//            }
//
//            public void setAliases(String aliases) {
//                this.aliases = aliases;
//            }
//
//            public int getProductId() {
//                return productId;
//            }
//
//            public void setProductId(int productId) {
//                this.productId = productId;
//            }
//
//            public String getAttrName() {
//                return attrName;
//            }
//
//            public void setAttrName(String attrName) {
//                this.attrName = attrName;
//            }
//
//            public List<AttrValueListBean> getAttrValueList() {
//                return attrValueList;
//            }
//
//            public void setAttrValueList(List<AttrValueListBean> attrValueList) {
//                this.attrValueList = attrValueList;
//            }
//
//            public static class AttrValueListBean {
//                /**
//                 * attrId : 1
//                 * attrValueId : 1
//                 * imageUrl :
//                 * attrValue : S
//                 */
//
//                private int attrId;
//                private int attrValueId;
//                private String imageUrl;
//                private String attrValue;
//
//                public int getAttrId() {
//                    return attrId;
//                }
//
//                public void setAttrId(int attrId) {
//                    this.attrId = attrId;
//                }
//
//                public int getAttrValueId() {
//                    return attrValueId;
//                }
//
//                public void setAttrValueId(int attrValueId) {
//                    this.attrValueId = attrValueId;
//                }
//
//                public String getImageUrl() {
//                    return imageUrl;
//                }
//
//                public void setImageUrl(String imageUrl) {
//                    this.imageUrl = imageUrl;
//                }
//
//                public String getAttrValue() {
//                    return attrValue;
//                }
//
//                public void setAttrValue(String attrValue) {
//                    this.attrValue = attrValue;
//                }
//            }
//        }
    }
}
