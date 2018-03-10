package com.acchain.community.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/20.
 */

public class IndexDataBean {

    private List<ChannelsBean> channels;
    private List<FlashSalesBean> flashSales;

    public List<ChannelsBean> getChannels() {
        return channels;
    }

    public void setChannels(List<ChannelsBean> channels) {
        this.channels = channels;
    }

    public List<FlashSalesBean> getFlashSales() {
        return flashSales;
    }

    public void setFlashSales(List<FlashSalesBean> flashSales) {
        this.flashSales = flashSales;
    }

    public static class ChannelsBean {
        /**
         * channelAliasName : mall_app_index
         * seoDescription : mall app
         * channelType : 3
         * updateTime : 2016-11-30 21:49:14
         * seoTitle : mall app首页
         * seoKeywords : mall app
         * boothList : [{"boothAliasName":"mall_app_index_banner_booth","boothState":1,"boothExclusive":3,"dataSize":0,"updateTime":"2016-04-04 20:06:28","boothType":1,"advertList":[{"adState":1,"updateTime":"2018-02-07 18:09:30","adImgUrl":"group1/M00/00/04/wKgB_Vp6ocKAHFUiAAD31oHmeRw703.jpg","boothId":1,"adShortTitle":"banner1","adType":1,"createBy":"","adFullTitle":"banner1","adSn":0,"adFCount":0,"adCCount":0,"createTime":"2018-01-04 10:12:28","updateBy":"yangqiang","id":5,"isDel":0},{"adState":1,"updateTime":"2018-02-07 18:09:30","adImgUrl":"group1/M00/00/04/wKgB_Vp6rMWAGfnkAAD31oHmeRw668.jpg","boothId":1,"adShortTitle":"banner2","adType":1,"createBy":"","adFullTitle":"banner2","adSn":1,"adFCount":0,"adCCount":0,"createTime":"2018-01-04 10:12:28","updateBy":"yangqiang","id":6,"isDel":0},{"adState":1,"updateTime":"2018-02-07 18:09:30","adImgUrl":"group1/M00/00/04/wKgB_Vp6taCAGRJzAAD31oHmeRw851.jpg","boothId":1,"adShortTitle":"banner3","adType":1,"createBy":"","adFullTitle":"banner3","adSn":2,"adFCount":0,"adCCount":0,"createTime":"2018-01-04 10:12:28","updateBy":"yangqiang","id":7,"isDel":0}],"boothSort":0,"createBy":"","boothDesc":"app网站首页","createTime":"2016-04-04 20:06:28","updateBy":"","boothName":"app网站首页banner","id":1,"isDel":0,"channelId":1},{"boothAliasName":"mall_app_index_product_booth","boothState":1,"boothExclusive":3,"dataSize":0,"updateTime":"2016-04-04 20:06:28","boothType":1,"boothSort":0,"createBy":"","boothDesc":"app网站首页","createTime":"2016-04-04 20:06:28","updateBy":"","boothName":"app网站商品类型展位","id":2,"isDel":0,"channelId":1},{"boothAliasName":"mall_app_index_purchase_product_booth","boothState":1,"boothExclusive":3,"dataSize":0,"updateTime":"2016-04-04 20:06:28","boothType":4,"boothSort":0,"createBy":"","boothDesc":"app网站首页","createTime":"2016-04-04 20:06:28","updateBy":"","boothName":"app网站抢购商品展位","id":3,"isDel":0,"channelId":1},{"boothAliasName":"mall_app_index_presell_product_booth","boothState":1,"boothExclusive":3,"dataSize":0,"updateTime":"2016-04-04 20:06:28","boothType":5,"boothSort":0,"createBy":"","boothDesc":"app网站首页","createTime":"2016-04-04 20:06:28","updateBy":"","boothName":"app网站预售商品展位","id":4,"isDel":0,"channelId":1,"productList":[{"presellEndTime":"2018-02-04 10:00:00","productImg":"group1/M00/00/02/wKgB_Vpi4tuAd3IyAAFTSLCgDUw599.png","presellSoldCount":0,"freightCharge":10,"presellStartTime":"2018-01-24 10:00:00","productName":"长白野山参","boothId":4,"productSaleId":1,"isPutaway":1,"presellPrice":198,"presellCirculation":20,"shortDesc":"集长白之精华，采天地之灵气集长白之精华，采天地之灵气","presellId":1,"bid":0,"isDel":0},{"presellEndTime":"2018-02-04 10:00:00","productImg":"group1/M00/00/02/wKgB_Vpi4tuAd3IyAAFTSLCgDUw592.png","presellSoldCount":0,"freightCharge":10,"presellStartTime":"2018-01-24 10:00:00","productName":"","boothId":4,"productSaleId":2,"isPutaway":0,"presellPrice":199,"presellCirculation":20,"shortDesc":"","presellId":2,"bid":0,"isDel":0}]}]
         * channelState : 1
         * createBy :
         * createTime : 2016-04-04 20:06:28
         * updateBy :
         * channelName : 首页
         * id : 1
         * channelDesc : app网站首页
         * bid : 57
         * isDel : 0
         */

        private String channelAliasName;
        private String seoDescription;
        private int channelType;
        private String updateTime;
        private String seoTitle;
        private String seoKeywords;
        private int channelState;
        private String createBy;
        private String createTime;
        private String updateBy;
        private String channelName;
        private int id;
        private String channelDesc;
        private int bid;
        private int isDel;
        private List<BoothListBean> boothList;

        public String getChannelAliasName() {
            return channelAliasName;
        }

        public void setChannelAliasName(String channelAliasName) {
            this.channelAliasName = channelAliasName;
        }

        public String getSeoDescription() {
            return seoDescription;
        }

        public void setSeoDescription(String seoDescription) {
            this.seoDescription = seoDescription;
        }

        public int getChannelType() {
            return channelType;
        }

        public void setChannelType(int channelType) {
            this.channelType = channelType;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getSeoTitle() {
            return seoTitle;
        }

        public void setSeoTitle(String seoTitle) {
            this.seoTitle = seoTitle;
        }

        public String getSeoKeywords() {
            return seoKeywords;
        }

        public void setSeoKeywords(String seoKeywords) {
            this.seoKeywords = seoKeywords;
        }

        public int getChannelState() {
            return channelState;
        }

        public void setChannelState(int channelState) {
            this.channelState = channelState;
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

        public String getChannelName() {
            return channelName;
        }

        public void setChannelName(String channelName) {
            this.channelName = channelName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getChannelDesc() {
            return channelDesc;
        }

        public void setChannelDesc(String channelDesc) {
            this.channelDesc = channelDesc;
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

        public List<BoothListBean> getBoothList() {
            return boothList;
        }

        public void setBoothList(List<BoothListBean> boothList) {
            this.boothList = boothList;
        }

        public static class BoothListBean {
            /**
             * boothAliasName : mall_app_index_banner_booth
             * boothState : 1
             * boothExclusive : 3
             * dataSize : 0
             * updateTime : 2016-04-04 20:06:28
             * boothType : 1
             * advertList : [{"adState":1,"updateTime":"2018-02-07 18:09:30","adImgUrl":"group1/M00/00/04/wKgB_Vp6ocKAHFUiAAD31oHmeRw703.jpg","boothId":1,"adShortTitle":"banner1","adType":1,"createBy":"","adFullTitle":"banner1","adSn":0,"adFCount":0,"adCCount":0,"createTime":"2018-01-04 10:12:28","updateBy":"yangqiang","id":5,"isDel":0},{"adState":1,"updateTime":"2018-02-07 18:09:30","adImgUrl":"group1/M00/00/04/wKgB_Vp6rMWAGfnkAAD31oHmeRw668.jpg","boothId":1,"adShortTitle":"banner2","adType":1,"createBy":"","adFullTitle":"banner2","adSn":1,"adFCount":0,"adCCount":0,"createTime":"2018-01-04 10:12:28","updateBy":"yangqiang","id":6,"isDel":0},{"adState":1,"updateTime":"2018-02-07 18:09:30","adImgUrl":"group1/M00/00/04/wKgB_Vp6taCAGRJzAAD31oHmeRw851.jpg","boothId":1,"adShortTitle":"banner3","adType":1,"createBy":"","adFullTitle":"banner3","adSn":2,"adFCount":0,"adCCount":0,"createTime":"2018-01-04 10:12:28","updateBy":"yangqiang","id":7,"isDel":0}]
             * boothSort : 0
             * createBy :
             * boothDesc : app网站首页
             * createTime : 2016-04-04 20:06:28
             * updateBy :
             * boothName : app网站首页banner
             * id : 1
             * isDel : 0
             * channelId : 1
             * productList : [{"presellEndTime":"2018-02-04 10:00:00","productImg":"group1/M00/00/02/wKgB_Vpi4tuAd3IyAAFTSLCgDUw599.png","presellSoldCount":0,"freightCharge":10,"presellStartTime":"2018-01-24 10:00:00","productName":"长白野山参","boothId":4,"productSaleId":1,"isPutaway":1,"presellPrice":198,"presellCirculation":20,"shortDesc":"集长白之精华，采天地之灵气集长白之精华，采天地之灵气","presellId":1,"bid":0,"isDel":0},{"presellEndTime":"2018-02-04 10:00:00","productImg":"group1/M00/00/02/wKgB_Vpi4tuAd3IyAAFTSLCgDUw592.png","presellSoldCount":0,"freightCharge":10,"presellStartTime":"2018-01-24 10:00:00","productName":"","boothId":4,"productSaleId":2,"isPutaway":0,"presellPrice":199,"presellCirculation":20,"shortDesc":"","presellId":2,"bid":0,"isDel":0}]
             */

            private String boothAliasName;
            private int boothState;
            private int boothExclusive;
            private int dataSize;
            private String updateTime;
            private int boothType;
            private int boothSort;
            private String createBy;
            private String boothDesc;
            private String createTime;
            private String updateBy;
            private String boothName;
            private int id;
            private int isDel;
            private int channelId;
            private List<AdvertListBean> advertList;
            private List<ProductListBean> productList;

            public String getBoothAliasName() {
                return boothAliasName;
            }

            public void setBoothAliasName(String boothAliasName) {
                this.boothAliasName = boothAliasName;
            }

            public int getBoothState() {
                return boothState;
            }

            public void setBoothState(int boothState) {
                this.boothState = boothState;
            }

            public int getBoothExclusive() {
                return boothExclusive;
            }

            public void setBoothExclusive(int boothExclusive) {
                this.boothExclusive = boothExclusive;
            }

            public int getDataSize() {
                return dataSize;
            }

            public void setDataSize(int dataSize) {
                this.dataSize = dataSize;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public int getBoothType() {
                return boothType;
            }

            public void setBoothType(int boothType) {
                this.boothType = boothType;
            }

            public int getBoothSort() {
                return boothSort;
            }

            public void setBoothSort(int boothSort) {
                this.boothSort = boothSort;
            }

            public String getCreateBy() {
                return createBy;
            }

            public void setCreateBy(String createBy) {
                this.createBy = createBy;
            }

            public String getBoothDesc() {
                return boothDesc;
            }

            public void setBoothDesc(String boothDesc) {
                this.boothDesc = boothDesc;
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

            public String getBoothName() {
                return boothName;
            }

            public void setBoothName(String boothName) {
                this.boothName = boothName;
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

            public int getChannelId() {
                return channelId;
            }

            public void setChannelId(int channelId) {
                this.channelId = channelId;
            }

            public List<AdvertListBean> getAdvertList() {
                return advertList;
            }

            public void setAdvertList(List<AdvertListBean> advertList) {
                this.advertList = advertList;
            }

            public List<ProductListBean> getProductList() {
                return productList;
            }

            public void setProductList(List<ProductListBean> productList) {
                this.productList = productList;
            }

            public static class AdvertListBean {
                /**
                 * adState : 1
                 * updateTime : 2018-02-07 18:09:30
                 * adImgUrl : group1/M00/00/04/wKgB_Vp6ocKAHFUiAAD31oHmeRw703.jpg
                 * boothId : 1
                 * adShortTitle : banner1
                 * adType : 1
                 * createBy :
                 * adFullTitle : banner1
                 * adSn : 0
                 * adFCount : 0
                 * adCCount : 0
                 * createTime : 2018-01-04 10:12:28
                 * updateBy : yangqiang
                 * id : 5
                 * isDel : 0
                 */

                private int adState;
                private String updateTime;
                private String adImgUrl;
                private int boothId;
                private String adShortTitle;
                private int adType;
                private String createBy;
                private String adFullTitle;
                private int adSn;
                private int adFCount;
                private int adCCount;
                private String createTime;
                private String updateBy;
                private int id;
                private int isDel;

                public int getAdState() {
                    return adState;
                }

                public void setAdState(int adState) {
                    this.adState = adState;
                }

                public String getUpdateTime() {
                    return updateTime;
                }

                public void setUpdateTime(String updateTime) {
                    this.updateTime = updateTime;
                }

                public String getAdImgUrl() {
                    return adImgUrl;
                }

                public void setAdImgUrl(String adImgUrl) {
                    this.adImgUrl = adImgUrl;
                }

                public int getBoothId() {
                    return boothId;
                }

                public void setBoothId(int boothId) {
                    this.boothId = boothId;
                }

                public String getAdShortTitle() {
                    return adShortTitle;
                }

                public void setAdShortTitle(String adShortTitle) {
                    this.adShortTitle = adShortTitle;
                }

                public int getAdType() {
                    return adType;
                }

                public void setAdType(int adType) {
                    this.adType = adType;
                }

                public String getCreateBy() {
                    return createBy;
                }

                public void setCreateBy(String createBy) {
                    this.createBy = createBy;
                }

                public String getAdFullTitle() {
                    return adFullTitle;
                }

                public void setAdFullTitle(String adFullTitle) {
                    this.adFullTitle = adFullTitle;
                }

                public int getAdSn() {
                    return adSn;
                }

                public void setAdSn(int adSn) {
                    this.adSn = adSn;
                }

                public int getAdFCount() {
                    return adFCount;
                }

                public void setAdFCount(int adFCount) {
                    this.adFCount = adFCount;
                }

                public int getAdCCount() {
                    return adCCount;
                }

                public void setAdCCount(int adCCount) {
                    this.adCCount = adCCount;
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

            public static class ProductListBean {
                /**
                 * presellEndTime : 2018-02-04 10:00:00
                 * productImg : group1/M00/00/02/wKgB_Vpi4tuAd3IyAAFTSLCgDUw599.png
                 * presellSoldCount : 0
                 * freightCharge : 10
                 * presellStartTime : 2018-01-24 10:00:00
                 * productName : 长白野山参
                 * boothId : 4
                 * productSaleId : 1
                 * isPutaway : 1
                 * presellPrice : 198
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
                private int boothId;
                private int productSaleId;
                private int isPutaway;
                private int presellPrice;
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

                public int getBoothId() {
                    return boothId;
                }

                public void setBoothId(int boothId) {
                    this.boothId = boothId;
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

                public int getPresellPrice() {
                    return presellPrice;
                }

                public void setPresellPrice(int presellPrice) {
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
    }

    public static class FlashSalesBean {
        /**
         * saleName : 限时抢购10:00
         * isPastDue : 0
         * isForbidden : 0
         * createTime : 2018-01-04 10:12:28
         * updateTime : 2018-01-04 10:12:28
         * id : 1
         * beginTime : 2018-01-04 10:00:00
         * endTime : 2018-05-04 12:00:00
         * isDel : 0
         * productList : [{"flashSaleRefId":1,"promotionCount":0,"purchaseId":1,"quotaQty":10,"boughtQty":0,"productImg2":"","productImg3":"","flashProductPrice":199.998,"productName":"","productImg1":"","soldCount":0,"shareCount":0,"productSaleTypeId":1,"productImg":"group1/M00/00/02/wKgB_Vpi4tuAd3IyAAFTSLCgDUw599.png","productSaleId":1,"flashProductQty":100,"flashSaleId":1,"productImg4":"","detailDescApp":"group1/M00/00/02/wKgB_Vpi4tuAd3IyAAFTSLCgDUw599.png","productImg5":"","productDiscountPrice":198,"collectionCount":0,"shortDesc":"","productPackUnitId":"","productQty":150,"bid":0,"isDel":0,"productPrice":199}]
         */

        private String saleName;
        private int isPastDue;
        private int isForbidden;
        private String createTime;
        private String updateTime;
        private int id;
        private String beginTime;
        private String endTime;
        private int isDel;
        private List<ProductListBeanX> productList;

        public String getSaleName() {
            return saleName;
        }

        public void setSaleName(String saleName) {
            this.saleName = saleName;
        }

        public int getIsPastDue() {
            return isPastDue;
        }

        public void setIsPastDue(int isPastDue) {
            this.isPastDue = isPastDue;
        }

        public int getIsForbidden() {
            return isForbidden;
        }

        public void setIsForbidden(int isForbidden) {
            this.isForbidden = isForbidden;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getIsDel() {
            return isDel;
        }

        public void setIsDel(int isDel) {
            this.isDel = isDel;
        }

        public List<ProductListBeanX> getProductList() {
            return productList;
        }

        public void setProductList(List<ProductListBeanX> productList) {
            this.productList = productList;
        }

        public static class ProductListBeanX {
            /**
             * flashSaleRefId : 1
             * promotionCount : 0
             * purchaseId : 1
             * quotaQty : 10
             * boughtQty : 0
             * productImg2 :
             * productImg3 :
             * flashProductPrice : 199.998
             * productName :
             * productImg1 :
             * soldCount : 0
             * shareCount : 0
             * productSaleTypeId : 1
             * productImg : group1/M00/00/02/wKgB_Vpi4tuAd3IyAAFTSLCgDUw599.png
             * productSaleId : 1
             * flashProductQty : 100
             * flashSaleId : 1
             * productImg4 :
             * detailDescApp : group1/M00/00/02/wKgB_Vpi4tuAd3IyAAFTSLCgDUw599.png
             * productImg5 :
             * productDiscountPrice : 198
             * collectionCount : 0
             * shortDesc :
             * productPackUnitId :
             * productQty : 150
             * bid : 0
             * isDel : 0
             * productPrice : 199
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
            private int soldCount;
            private int shareCount;
            private int productSaleTypeId;
            private String productImg;
            private int productSaleId;
            private int flashProductQty;
            private int flashSaleId;
            private String productImg4;
            private String detailDescApp;
            private String productImg5;
            private int productDiscountPrice;
            private int collectionCount;
            private String shortDesc;
            private String productPackUnitId;
            private int productQty;
            private int bid;
            private int isDel;
            private int productPrice;

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

            public int getProductDiscountPrice() {
                return productDiscountPrice;
            }

            public void setProductDiscountPrice(int productDiscountPrice) {
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

            public int getProductPrice() {
                return productPrice;
            }

            public void setProductPrice(int productPrice) {
                this.productPrice = productPrice;
            }
        }
    }
}
