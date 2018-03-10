package com.acchain.community.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/30.
 */

public class ExerciseProduct {

    /**
     * pageNum : 1
     * pageSize : 1
     * size : 1
     * startRow : 1
     * endRow : 1
     * total : 2
     * pages : 2
     * list : [{"productSpecification":"357g/饼","exerciseId":1,"isDeliver":0,"productBrand":"太姥山","browseNumber":0,"exercisePrice":198,"productName":"长白野山参","exerciseSpecification":"10 HCYH","isPutaway":0,"currencyId":1,"traceSource":"","productId":1,"exerciseStartTime":"2018-01-18 00:00:00","exerciseUnits":"饼","productImg":"group1/M00/00/02/wKgB_Vpi4tuAd3IyAAFTSLCgDUw599.png","freightCharge":10,"productionPlace":"福建福鼎","productTypeId":7,"traderAddress":"","propertyCurrency":"TLS.HCYH","detailDesc":"group1/M00/00/00/wKgAyVgFk9aAB8hwAA-8Q6_7tHw351.jpg","blockHeight":101,"propertyName":"华茶壹号","exerciseEndTime":"2018-05-18 00:00:00","isRefund":0,"circulation":100,"assetsPrecision":100,"shortDesc":"集长白之精华，采天地之灵气集长白之精华，采天地之灵气","isDel":0,"isExemption":0}]
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
         * productSpecification : 357g/饼
         * exerciseId : 1
         * isDeliver : 0
         * productBrand : 太姥山
         * browseNumber : 0
         * exercisePrice : 198
         * productName : 长白野山参
         * exerciseSpecification : 10 HCYH
         * isPutaway : 0
         * currencyId : 1
         * traceSource :
         * productId : 1
         * exerciseStartTime : 2018-01-18 00:00:00
         * exerciseUnits : 饼
         * productImg : group1/M00/00/02/wKgB_Vpi4tuAd3IyAAFTSLCgDUw599.png
         * freightCharge : 10
         * productionPlace : 福建福鼎
         * productTypeId : 7
         * traderAddress :
         * propertyCurrency : TLS.HCYH
         * detailDesc : group1/M00/00/00/wKgAyVgFk9aAB8hwAA-8Q6_7tHw351.jpg
         * blockHeight : 101
         * propertyName : 华茶壹号
         * exerciseEndTime : 2018-05-18 00:00:00
         * isRefund : 0
         * circulation : 100
         * assetsPrecision : 100
         * shortDesc : 集长白之精华，采天地之灵气集长白之精华，采天地之灵气
         * isDel : 0
         * isExemption : 0
         */

        private String productSpecification;
        private int exerciseId;
        private int isDeliver;
        private String productBrand;
        private int browseNumber;
        private int exercisePrice;
        private String productName;
        private String exerciseSpecification;
        private int isPutaway;
        private int currencyId;
        private String traceSource;
        private int productId;
        private String exerciseStartTime;
        private String exerciseUnits;
        private String productImg;
        private int freightCharge;
        private String productionPlace;
        private int productTypeId;
        private String traderAddress;
        private String propertyCurrency;
        private String detailDesc;
        private int blockHeight;
        private String propertyName;
        private String exerciseEndTime;
        private int isRefund;
        private int circulation;
        private int assetsPrecision;
        private String shortDesc;
        private int isDel;
        private int isExemption;

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

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
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

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
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

        public String getProductImg() {
            return productImg;
        }

        public void setProductImg(String productImg) {
            this.productImg = productImg;
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

        public String getShortDesc() {
            return shortDesc;
        }

        public void setShortDesc(String shortDesc) {
            this.shortDesc = shortDesc;
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
    }
}
