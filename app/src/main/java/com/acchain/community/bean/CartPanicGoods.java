package com.acchain.community.bean;

import com.acchain.community.window.dialog.CommonGoodsAttrsBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/24.
 */

public class CartPanicGoods {

    /**
     * pageInfo : {"pageNum":1,"pageSize":2,"size":1,"startRow":1,"endRow":1,"total":1,"pages":1,"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1}
     * dataSet : [{"flashSaleRefId":1,"adoptCodeId":"-1","productId":1,"cartType":0,"id":1435,"productSubId":1,"isDel":0,"memberId":1,"productAttr":"尺码 :  S","itemCount":1,"products":{"flashSaleRefId":1,"purchaseId":1,"productImg":"group1/M00/00/02/wKgB_Vpi4tuAd3IyAAFTSLCgDUw599.png","quotaQty":10,"boughtQty":0,"flashProductPrice":199.998,"productName":"长白野山参","flashProductQty":100,"productSaleId":1,"saleProductDiscountPrice":198.000001,"shortDesc":"集长白之精华，采天地之灵气集长白之精华，采天地之灵气","bid":0,"saleProductPrice":199.000001,"isExemption":0,"productAttrList":[{"isImage":0,"attrId":1,"aliases":"尺码","productId":1,"attrValueList":[{"attrId":1,"attrValueId":1,"imageUrl":"","attrValue":"S"},{"attrId":1,"attrValueId":2,"imageUrl":"","attrValue":"m"}],"attrName":"尺码"},{"isImage":0,"attrId":2,"aliases":"颜色","productId":1,"attrValueList":[{"attrId":2,"attrValueId":3,"imageUrl":"","attrValue":"深蓝"},{"attrId":2,"attrValueId":4,"imageUrl":"","attrValue":"白色"}],"attrName":"颜色"},{"isImage":0,"attrId":3,"aliases":"净含量","productId":1,"attrValueList":[{"attrId":3,"attrValueId":5,"imageUrl":"","attrValue":"500g"},{"attrId":3,"attrValueId":6,"imageUrl":"","attrValue":"1000g"}],"attrName":"净含量"}]}}]
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
         * flashSaleRefId : 1
         * adoptCodeId : -1
         * productId : 1
         * cartType : 0
         * id : 1435
         * productSubId : 1
         * isDel : 0
         * memberId : 1
         * productAttr : 尺码 :  S
         * itemCount : 1
         * products : {"flashSaleRefId":1,"purchaseId":1,"productImg":"group1/M00/00/02/wKgB_Vpi4tuAd3IyAAFTSLCgDUw599.png","quotaQty":10,"boughtQty":0,"flashProductPrice":199.998,"productName":"长白野山参","flashProductQty":100,"productSaleId":1,"saleProductDiscountPrice":198.000001,"shortDesc":"集长白之精华，采天地之灵气集长白之精华，采天地之灵气","bid":0,"saleProductPrice":199.000001,"isExemption":0,"productAttrList":[{"isImage":0,"attrId":1,"aliases":"尺码","productId":1,"attrValueList":[{"attrId":1,"attrValueId":1,"imageUrl":"","attrValue":"S"},{"attrId":1,"attrValueId":2,"imageUrl":"","attrValue":"m"}],"attrName":"尺码"},{"isImage":0,"attrId":2,"aliases":"颜色","productId":1,"attrValueList":[{"attrId":2,"attrValueId":3,"imageUrl":"","attrValue":"深蓝"},{"attrId":2,"attrValueId":4,"imageUrl":"","attrValue":"白色"}],"attrName":"颜色"},{"isImage":0,"attrId":3,"aliases":"净含量","productId":1,"attrValueList":[{"attrId":3,"attrValueId":5,"imageUrl":"","attrValue":"500g"},{"attrId":3,"attrValueId":6,"imageUrl":"","attrValue":"1000g"}],"attrName":"净含量"}]}
         */

        private boolean isChoose;//增加的字段
        private int flashSaleRefId;
        private String adoptCodeId;
        private int productId;
        private int cartType;
        private int id;
        private int bid;
        private int productSubId;
        private int isDel;
        private int memberId;
        private String productAttr;
        private int itemCount;
        private ProductsBean products;

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

        public static class ProductsBean {
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
             * productAttrList : [{"isImage":0,"attrId":1,"aliases":"尺码","productId":1,"attrValueList":[{"attrId":1,"attrValueId":1,"imageUrl":"","attrValue":"S"},{"attrId":1,"attrValueId":2,"imageUrl":"","attrValue":"m"}],"attrName":"尺码"},{"isImage":0,"attrId":2,"aliases":"颜色","productId":1,"attrValueList":[{"attrId":2,"attrValueId":3,"imageUrl":"","attrValue":"深蓝"},{"attrId":2,"attrValueId":4,"imageUrl":"","attrValue":"白色"}],"attrName":"颜色"},{"isImage":0,"attrId":3,"aliases":"净含量","productId":1,"attrValueList":[{"attrId":3,"attrValueId":5,"imageUrl":"","attrValue":"500g"},{"attrId":3,"attrValueId":6,"imageUrl":"","attrValue":"1000g"}],"attrName":"净含量"}]
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
            private List<CommonGoodsAttrsBean.AttrListBean> productAttrList;

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

            public List<CommonGoodsAttrsBean.AttrListBean> getProductAttrList() {
                return productAttrList;
            }
            public void setProductAttrList(List<CommonGoodsAttrsBean.AttrListBean> productAttrList) {
                this.productAttrList = productAttrList;
            }
        }
    }
}
