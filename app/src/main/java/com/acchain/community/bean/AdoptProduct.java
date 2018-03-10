package com.acchain.community.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/23.
 */

public class AdoptProduct {

    /**
     * pageNum : 1
     * pageSize : 2
     * size : 2
     * startRow : 1
     * endRow : 2
     * total : 2
     * pages : 1
     * list : [{"productSpecification":"红豆杉产品规格","isAutoExercise":1,"traceSource":"","adoptPrice":199,"productId":1,"adoptStartTime":"2018-02-18 00:00:00","adoptName":"红豆杉 第1期","maxAdoptQty":100,"adoptCirculation":2000,"productBrand":"红豆杉产品品牌","exerciseStartTime":"2018-04-18 10:00:00","browseNumber":0,"exercisePrice":199,"productionPlace":"红豆杉产地","productTypeId":5,"exerciseSpecification":"红豆杉行权规格","propertyCurrency":"红豆杉资产币种","adoptedCount":0,"propertyName":"红豆杉","exerciseEndTime":"2018-05-18 10:00:00","circulation":100000,"adoptId":1,"adoptEndTime":"2018-03-18 00:00:00","currencyId":-1},{"productSpecification":"鳄鱼宝产品规格","isAutoExercise":1,"traceSource":"","adoptPrice":199,"productId":2,"adoptStartTime":"2018-02-18 00:00:00","adoptName":"鳄鱼宝 第1期","maxAdoptQty":100,"adoptCirculation":2000,"productBrand":"鳄鱼宝产品品牌","exerciseStartTime":"2018-04-18 10:00:00","browseNumber":0,"exercisePrice":199,"productionPlace":"鳄鱼宝产地","productTypeId":6,"exerciseSpecification":"鳄鱼宝行权规格","propertyCurrency":"鳄鱼宝资产币种","adoptedCount":0,"propertyName":"鳄鱼宝","exerciseEndTime":"2018-05-18 10:00:00","circulation":100000,"adoptId":2,"adoptEndTime":"2018-03-18 00:00:00","currencyId":-1}]
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
         * productSpecification : 红豆杉产品规格
         * isAutoExercise : 1
         * traceSource :
         * adoptPrice : 199
         * productId : 1
         * adoptStartTime : 2018-02-18 00:00:00
         * adoptName : 红豆杉 第1期
         * maxAdoptQty : 100
         * adoptCirculation : 2000
         * productBrand : 红豆杉产品品牌
         * exerciseStartTime : 2018-04-18 10:00:00
         * browseNumber : 0
         * exercisePrice : 199
         * productionPlace : 红豆杉产地
         * productTypeId : 5
         * exerciseSpecification : 红豆杉行权规格
         * propertyCurrency : 红豆杉资产币种
         * adoptedCount : 0
         * propertyName : 红豆杉
         * exerciseEndTime : 2018-05-18 10:00:00
         * circulation : 100000
         * adoptId : 1
         * adoptEndTime : 2018-03-18 00:00:00
         * currencyId : -1
         */

        private String productSpecification;
        private int isAutoExercise;
        private String traceSource;
        private int adoptPrice;
        private int productId;
        private String adoptStartTime;
        private String adoptName;
        private int maxAdoptQty;
        private int adoptCirculation;
        private String productBrand;
        private String exerciseStartTime;
        private int browseNumber;
        private int exercisePrice;
        private String productionPlace;
        private int productTypeId;
        private String exerciseSpecification;
        private String propertyCurrency;
        private int adoptedCount;
        private String propertyName;
        private String exerciseEndTime;
        private int circulation;
        private int adoptId;
        private String adoptEndTime;
        private int currencyId;

        public String getProductSpecification() {
            return productSpecification;
        }

        public void setProductSpecification(String productSpecification) {
            this.productSpecification = productSpecification;
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

        public int getAdoptPrice() {
            return adoptPrice;
        }

        public void setAdoptPrice(int adoptPrice) {
            this.adoptPrice = adoptPrice;
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

        public int getAdoptCirculation() {
            return adoptCirculation;
        }

        public void setAdoptCirculation(int adoptCirculation) {
            this.adoptCirculation = adoptCirculation;
        }

        public String getProductBrand() {
            return productBrand;
        }

        public void setProductBrand(String productBrand) {
            this.productBrand = productBrand;
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

        public int getExercisePrice() {
            return exercisePrice;
        }

        public void setExercisePrice(int exercisePrice) {
            this.exercisePrice = exercisePrice;
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

        public String getExerciseSpecification() {
            return exerciseSpecification;
        }

        public void setExerciseSpecification(String exerciseSpecification) {
            this.exerciseSpecification = exerciseSpecification;
        }

        public String getPropertyCurrency() {
            return propertyCurrency;
        }

        public void setPropertyCurrency(String propertyCurrency) {
            this.propertyCurrency = propertyCurrency;
        }

        public int getAdoptedCount() {
            return adoptedCount;
        }

        public void setAdoptedCount(int adoptedCount) {
            this.adoptedCount = adoptedCount;
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
    }
}
