package com.acchain.community.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/2/9.
 */

public class CartAdoptGoods {

    /**
     * pageInfo : {"pageNum":1,"pageSize":2,"size":1,"startRow":1,"endRow":1,"total":1,"pages":1,"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1}
     * dataSet : [{"flashSaleRefId":-1,"adoptCodeId":"3","productId":1,"adoptCode":[{"videoSurveillanceInfo":"","adoptCode":"HDS01-0003","photoAlbumInfo":"","updateTime":"2018-01-18 00:00:00","adoptStatus":0,"createBy":"","createTime":"2018-03-18 00:00:00","updateBy":"","locationId":1,"versionNo":0,"adoptId":1,"id":3,"isDel":0}],"cartType":2,"id":1467,"productSubId":1,"isDel":0,"memberId":28,"productAttr":"","itemCount":1,"products":{"productSpecification":"红豆杉产品规格","adoptPrice":199,"adoptName":"红豆杉 第1期","maxAdoptQty":100,"productBrand":"红豆杉产品品牌","browseNumber":0,"exercisePrice":199,"exerciseSpecification":"红豆杉行权规格","adoptedCount":0,"adoptId":1,"adoptEndTime":"2018-03-18 00:00:00","currencyId":2,"isAutoExercise":1,"traceSource":"","productId":1,"adoptStartTime":"2018-02-18 00:00:00","adoptCirculation":2000,"exerciseStartTime":"2018-04-18 10:00:00","productionPlace":"红豆杉产地","productTypeId":5,"propertyCurrency":"RET","propertyName":"红豆杉","exerciseEndTime":"2018-05-18 10:00:00","circulation":100000,"bid":0}}]
     */

    private PageInfoBean pageInfo;
    private List<DataSetBean> dataSet;

    public PageInfoBean getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfoBean pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<DataSetBean> getDataSet() {
        return dataSet;
    }

    public void setDataSet(List<DataSetBean> dataSet) {
        this.dataSet = dataSet;
    }

    public static class PageInfoBean {
        /**
         * pageNum : 1
         * pageSize : 2
         * size : 1
         * startRow : 1
         * endRow : 1
         * total : 1
         * pages : 1
         * prePage : 0
         * nextPage : 0
         * isFirstPage : true
         * isLastPage : true
         * hasPreviousPage : false
         * hasNextPage : false
         * navigatePages : 8
         * navigatepageNums : [1]
         * navigateFirstPage : 1
         * navigateLastPage : 1
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

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }
    }

    public static class DataSetBean {
        /**
         * flashSaleRefId : -1
         * adoptCodeId : 3
         * productId : 1
         * adoptCode : [{"videoSurveillanceInfo":"","adoptCode":"HDS01-0003","photoAlbumInfo":"","updateTime":"2018-01-18 00:00:00","adoptStatus":0,"createBy":"","createTime":"2018-03-18 00:00:00","updateBy":"","locationId":1,"versionNo":0,"adoptId":1,"id":3,"isDel":0}]
         * cartType : 2
         * id : 1467
         * productSubId : 1
         * isDel : 0
         * memberId : 28
         * productAttr :
         * itemCount : 1
         * products : {"productSpecification":"红豆杉产品规格","adoptPrice":199,"adoptName":"红豆杉 第1期","maxAdoptQty":100,"productBrand":"红豆杉产品品牌","browseNumber":0,"exercisePrice":199,"exerciseSpecification":"红豆杉行权规格","adoptedCount":0,"adoptId":1,"adoptEndTime":"2018-03-18 00:00:00","currencyId":2,"isAutoExercise":1,"traceSource":"","productId":1,"adoptStartTime":"2018-02-18 00:00:00","adoptCirculation":2000,"exerciseStartTime":"2018-04-18 10:00:00","productionPlace":"红豆杉产地","productTypeId":5,"propertyCurrency":"RET","propertyName":"红豆杉","exerciseEndTime":"2018-05-18 10:00:00","circulation":100000,"bid":0}
         */

        private boolean isChoose;//增加的字段
        private int flashSaleRefId;
        private String adoptCodeId;
        private int productId;
        private int cartType;
        private int id;
        private int productSubId;
        private int isDel;
        private int memberId;
        private String productAttr;
        private int itemCount;
        private ProductsBean products;
        private List<AdoptGoodDetail.DefaultAdoptCodeBean> adoptCode;

        public boolean isChoose() {
            return isChoose;
        }

        public void setChoose(boolean isChoose) {
            this.isChoose = isChoose;
        }

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

        public int getProductSubId() {
            return productSubId;
        }

        public void setProductSubId(int productSubId) {
            this.productSubId = productSubId;
        }

        public int getIsDel() {
            return isDel;
        }

        public void setIsDel(int isDel) {
            this.isDel = isDel;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public String getProductAttr() {
            return productAttr;
        }

        public void setProductAttr(String productAttr) {
            this.productAttr = productAttr;
        }

        public int getItemCount() {
            return itemCount;
        }

        public void setItemCount(int itemCount) {
            this.itemCount = itemCount;
        }

        public ProductsBean getProducts() {
            return products;
        }

        public void setProducts(ProductsBean products) {
            this.products = products;
        }

        public List<AdoptGoodDetail.DefaultAdoptCodeBean> getAdoptCode() {
            return adoptCode;
        }

        public void setAdoptCode(List<AdoptGoodDetail.DefaultAdoptCodeBean> adoptCode) {
            this.adoptCode = adoptCode;
        }

        public static class ProductsBean {
            /**
             * productSpecification : 红豆杉产品规格
             * adoptPrice : 199
             * adoptName : 红豆杉 第1期
             * maxAdoptQty : 100
             * productBrand : 红豆杉产品品牌
             * browseNumber : 0
             * exercisePrice : 199
             * exerciseSpecification : 红豆杉行权规格
             * adoptedCount : 0
             * adoptId : 1
             * adoptEndTime : 2018-03-18 00:00:00
             * currencyId : 2
             * isAutoExercise : 1
             * traceSource :
             * productId : 1
             * adoptStartTime : 2018-02-18 00:00:00
             * adoptCirculation : 2000
             * exerciseStartTime : 2018-04-18 10:00:00
             * productionPlace : 红豆杉产地
             * productTypeId : 5
             * propertyCurrency : RET
             * propertyName : 红豆杉
             * exerciseEndTime : 2018-05-18 10:00:00
             * circulation : 100000
             * bid : 0
             */

            private String productSpecification;
            private int adoptPrice;
            private String adoptName;
            private int maxAdoptQty;
            private String productBrand;
            private int browseNumber;
            private int exercisePrice;
            private String exerciseSpecification;
            private int adoptedCount;
            private int adoptId;
            private String adoptEndTime;
            private int currencyId;
            private int isAutoExercise;
            private String traceSource;
            private int productId;
            private String adoptStartTime;
            private int adoptCirculation;
            private String exerciseStartTime;
            private String productionPlace;
            private int productTypeId;
            private String propertyCurrency;
            private String propertyName;
            private String exerciseEndTime;
            private int circulation;
            private int bid;

            public String getProductSpecification() {
                return productSpecification;
            }

            public void setProductSpecification(String productSpecification) {
                this.productSpecification = productSpecification;
            }

            public int getAdoptPrice() {
                return adoptPrice;
            }

            public void setAdoptPrice(int adoptPrice) {
                this.adoptPrice = adoptPrice;
            }

            public String getAdoptName() {
                return adoptName;
            }

            public void setAdoptName(String adoptName) {
                this.adoptName = adoptName;
            }

            public int getMaxAdoptQty() {
                return maxAdoptQty;
            }

            public void setMaxAdoptQty(int maxAdoptQty) {
                this.maxAdoptQty = maxAdoptQty;
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

            public int getAdoptedCount() {
                return adoptedCount;
            }

            public void setAdoptedCount(int adoptedCount) {
                this.adoptedCount = adoptedCount;
            }

            public int getAdoptId() {
                return adoptId;
            }

            public void setAdoptId(int adoptId) {
                this.adoptId = adoptId;
            }

            public String getAdoptEndTime() {
                return adoptEndTime;
            }

            public void setAdoptEndTime(String adoptEndTime) {
                this.adoptEndTime = adoptEndTime;
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

            public String getAdoptStartTime() {
                return adoptStartTime;
            }

            public void setAdoptStartTime(String adoptStartTime) {
                this.adoptStartTime = adoptStartTime;
            }

            public int getAdoptCirculation() {
                return adoptCirculation;
            }

            public void setAdoptCirculation(int adoptCirculation) {
                this.adoptCirculation = adoptCirculation;
            }

            public String getExerciseStartTime() {
                return exerciseStartTime;
            }

            public void setExerciseStartTime(String exerciseStartTime) {
                this.exerciseStartTime = exerciseStartTime;
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

            public int getBid() {
                return bid;
            }

            public void setBid(int bid) {
                this.bid = bid;
            }
        }
    }
}
