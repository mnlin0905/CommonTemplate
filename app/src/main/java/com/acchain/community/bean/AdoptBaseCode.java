package com.acchain.community.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/2/2.
 */

public class AdoptBaseCode implements Serializable {
    /**
     * pageNum : 1
     * pageSize : 1
     * size : 1
     * startRow : 1
     * endRow : 1
     * total : 3
     * pages : 3
     * list : [{"videoSurveillanceInfo":"","adoptCode":"HDS01-0001","photoAlbumInfo":"","updateTime":"2018-01-18 00:00:00","adoptStatus":0,"createBy":"","createTime":"2018-03-18 00:00:00","updateBy":"","locationId":1,"versionNo":0,"adoptId":1,"id":1,"isDel":0}]
     * prePage : 0
     * nextPage : 2
     * isFirstPage : true
     * isLastPage : false
     * hasPreviousPage : false
     * hasNextPage : true
     * navigatePages : 8
     * navigatepageNums : [1,2,3]
     * navigateFirstPage : 1
     * navigateLastPage : 3
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
         * videoSurveillanceInfo :
         * adoptCode : HDS01-0001
         * photoAlbumInfo :
         * updateTime : 2018-01-18 00:00:00
         * adoptStatus : 0
         * createBy :
         * createTime : 2018-03-18 00:00:00
         * updateBy :
         * locationId : 1
         * versionNo : 0
         * adoptId : 1
         * id : 1
         * isDel : 0
         */
        private boolean isChoose;//增加的字段
        private String videoSurveillanceInfo;
        private String adoptCode;
        private String photoAlbumInfo;
        private String updateTime;
        private int adoptStatus;
        private String createBy;
        private String createTime;
        private String updateBy;
        private int locationId;
        private int versionNo;
        private int adoptId;
        private int id;
        private int isDel;

        public boolean isChoose() {
            return isChoose;
        }

        public void setChoose(boolean choose) {
            isChoose = choose;
        }

        public String getVideoSurveillanceInfo() {
            return videoSurveillanceInfo;
        }

        public void setVideoSurveillanceInfo(String videoSurveillanceInfo) {
            this.videoSurveillanceInfo = videoSurveillanceInfo;
        }

        public String getAdoptCode() {
            return adoptCode;
        }

        public void setAdoptCode(String adoptCode) {
            this.adoptCode = adoptCode;
        }

        public String getPhotoAlbumInfo() {
            return photoAlbumInfo;
        }

        public void setPhotoAlbumInfo(String photoAlbumInfo) {
            this.photoAlbumInfo = photoAlbumInfo;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getAdoptStatus() {
            return adoptStatus;
        }

        public void setAdoptStatus(int adoptStatus) {
            this.adoptStatus = adoptStatus;
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

        public int getLocationId() {
            return locationId;
        }

        public void setLocationId(int locationId) {
            this.locationId = locationId;
        }

        public int getVersionNo() {
            return versionNo;
        }

        public void setVersionNo(int versionNo) {
            this.versionNo = versionNo;
        }

        public int getAdoptId() {
            return adoptId;
        }

        public void setAdoptId(int adoptId) {
            this.adoptId = adoptId;
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
}
