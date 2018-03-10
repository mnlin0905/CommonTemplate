package com.acchain.community.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/23.
 */

public class PreSellProduct {


    /**
     * pageNum : 1
     * pageSize : 1
     * size : 1
     * startRow : 1
     * endRow : 1
     * total : 2
     * pages : 2
     * list : [{"traceSource":"","productId":1,"presellEndTime":"2018-02-04 10:00:00","exerciseStartTime":"2018-02-04 10:00:00","browseNumber":0,"exercisePrice":198.000004,"presellSoldCount":10,"presellStartTime":"2018-01-23 18:09:00","productTypeId":3,"products":{"soldCount":0,"productDiscountPrice":198.000001,"productImg":"group1/M00/00/02/wKgB_Vpi4tuAd3IyAAFTSLCgDUw599.png","shortDesc":"集长白之精华，采天地之灵气集长白之精华，采天地之灵气","productPackUnitId":"kg","id":1,"productQty":20,"bid":0,"productName":"长白野山参","productPrice":199.000001,"productAttrList":[{"isImage":0,"attrId":1,"aliases":"尺码","productId":1,"attrValueList":[{"attrId":1,"attrValueId":1,"imageUrl":"","attrValue":"S"},{"attrId":1,"attrValueId":2,"imageUrl":"","attrValue":"m"}],"attrName":"尺码"},{"isImage":0,"attrId":2,"aliases":"颜色","productId":1,"attrValueList":[{"attrId":2,"attrValueId":3,"imageUrl":"","attrValue":"深蓝"},{"attrId":2,"attrValueId":4,"imageUrl":"","attrValue":"白色"}],"attrName":"颜色"},{"isImage":0,"attrId":3,"aliases":"净含量","productId":1,"attrValueList":[{"attrId":3,"attrValueId":5,"imageUrl":"","attrValue":"500g"},{"attrId":3,"attrValueId":6,"imageUrl":"","attrValue":"1000g"}],"attrName":"净含量"}]},"isPutaway":0,"presellCirculation":20,"exerciseEndTime":"2018-02-04 10:00:00","circulation":100,"id":1,"currencyId":0,"isDel":0}]
     * prePage : 0
     * nextPage : 2
     * isFirstPage : true
     * isLastPage : false
     * hasPreviousPage : false
     * hasNextPage : true
     * navigatePages : 8
     * navigatepageNums : [1,2]
     * navigateFirstPage : 1
     * navigateLastPage : 2
     */

    private int pageNum;
    private int pageSize;
    private int size;
    private int startRow;
    private int endRow;
    private int total;
    private int pages;
    private int prePage;
    private int nextPage;
    private boolean isFirstPage;
    private boolean isLastPage;
    private boolean hasPreviousPage;
    private boolean hasNextPage;
    private int navigatePages;
    private int navigateFirstPage;
    private int navigateLastPage;
    private List<ListBean> list;
    private List<Integer> navigatepageNums;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public boolean isIsFirstPage() {
        return isFirstPage;
    }

    public void setIsFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public boolean isIsLastPage() {
        return isLastPage;
    }

    public void setIsLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public int getNavigateFirstPage() {
        return navigateFirstPage;
    }

    public void setNavigateFirstPage(int navigateFirstPage) {
        this.navigateFirstPage = navigateFirstPage;
    }

    public int getNavigateLastPage() {
        return navigateLastPage;
    }

    public void setNavigateLastPage(int navigateLastPage) {
        this.navigateLastPage = navigateLastPage;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public List<Integer> getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(List<Integer> navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }

    public static class ListBean {
        /**
         * traceSource :
         * productId : 1
         * presellEndTime : 2018-02-04 10:00:00
         * exerciseStartTime : 2018-02-04 10:00:00
         * browseNumber : 0
         * exercisePrice : 198.000004
         * presellSoldCount : 10
         * presellStartTime : 2018-01-23 18:09:00
         * productTypeId : 3
         * products : {"soldCount":0,"productDiscountPrice":198.000001,"productImg":"group1/M00/00/02/wKgB_Vpi4tuAd3IyAAFTSLCgDUw599.png","shortDesc":"集长白之精华，采天地之灵气集长白之精华，采天地之灵气","productPackUnitId":"kg","id":1,"productQty":20,"bid":0,"productName":"长白野山参","productPrice":199.000001,"productAttrList":[{"isImage":0,"attrId":1,"aliases":"尺码","productId":1,"attrValueList":[{"attrId":1,"attrValueId":1,"imageUrl":"","attrValue":"S"},{"attrId":1,"attrValueId":2,"imageUrl":"","attrValue":"m"}],"attrName":"尺码"},{"isImage":0,"attrId":2,"aliases":"颜色","productId":1,"attrValueList":[{"attrId":2,"attrValueId":3,"imageUrl":"","attrValue":"深蓝"},{"attrId":2,"attrValueId":4,"imageUrl":"","attrValue":"白色"}],"attrName":"颜色"},{"isImage":0,"attrId":3,"aliases":"净含量","productId":1,"attrValueList":[{"attrId":3,"attrValueId":5,"imageUrl":"","attrValue":"500g"},{"attrId":3,"attrValueId":6,"imageUrl":"","attrValue":"1000g"}],"attrName":"净含量"}]}
         * isPutaway : 0
         * presellCirculation : 20
         * exerciseEndTime : 2018-02-04 10:00:00
         * circulation : 100
         * id : 1
         * currencyId : 0
         * isDel : 0
         */

        private String traceSource;
        private int productId;
        private String presellEndTime;
        private String exerciseStartTime;
        private int browseNumber;
        private double exercisePrice;
        private int presellSoldCount;
        private String presellStartTime;
        private int productTypeId;
        private ProductsBean products;
        private int isPutaway;
        private int presellCirculation;
        private String exerciseEndTime;
        private int circulation;
        private int id;
        private int currencyId;
        private int isDel;

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

        public ProductsBean getProducts() {
            return products;
        }

        public void setProducts(ProductsBean products) {
            this.products = products;
        }

        public int getIsPutaway() {
            return isPutaway;
        }

        public void setIsPutaway(int isPutaway) {
            this.isPutaway = isPutaway;
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

        public int getCirculation() {
            return circulation;
        }

        public void setCirculation(int circulation) {
            this.circulation = circulation;
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

        public int getIsDel() {
            return isDel;
        }

        public void setIsDel(int isDel) {
            this.isDel = isDel;
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
            private String shortDesc;
            private String productPackUnitId;
            private int id;
            private int productQty;
            private int bid;
            private String productName;
            private double productPrice;
            private List<ProductAttrListBean> productAttrList;

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

            public List<ProductAttrListBean> getProductAttrList() {
                return productAttrList;
            }

            public void setProductAttrList(List<ProductAttrListBean> productAttrList) {
                this.productAttrList = productAttrList;
            }

            public static class ProductAttrListBean {
                /**
                 * isImage : 0
                 * attrId : 1
                 * aliases : 尺码
                 * productId : 1
                 * attrValueList : [{"attrId":1,"attrValueId":1,"imageUrl":"","attrValue":"S"},{"attrId":1,"attrValueId":2,"imageUrl":"","attrValue":"m"}]
                 * attrName : 尺码
                 */

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

                public static class AttrValueListBean {
                    /**
                     * attrId : 1
                     * attrValueId : 1
                     * imageUrl :
                     * attrValue : S
                     */

                    private int attrId;
                    private int attrValueId;
                    private String imageUrl;
                    private String attrValue;

                    public int getAttrId() {
                        return attrId;
                    }

                    public void setAttrId(int attrId) {
                        this.attrId = attrId;
                    }

                    public int getAttrValueId() {
                        return attrValueId;
                    }

                    public void setAttrValueId(int attrValueId) {
                        this.attrValueId = attrValueId;
                    }

                    public String getImageUrl() {
                        return imageUrl;
                    }

                    public void setImageUrl(String imageUrl) {
                        this.imageUrl = imageUrl;
                    }

                    public String getAttrValue() {
                        return attrValue;
                    }

                    public void setAttrValue(String attrValue) {
                        this.attrValue = attrValue;
                    }
                }
            }
        }
    }
}
