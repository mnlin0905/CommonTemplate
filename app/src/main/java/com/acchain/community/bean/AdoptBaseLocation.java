package com.acchain.community.bean;

/**
 * Created by Administrator on 2018/2/2.
 */

public class AdoptBaseLocation {

    /**
     * localtionId : 1
     * locationName : 红豆杉1号基地
     * locationCode : hds_first_code
     */

    private int localtionId;
    private String locationName;
    private String locationCode;

    public int getLocaltionId() {
        return localtionId;
    }

    public void setLocaltionId(int localtionId) {
        this.localtionId = localtionId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }
}
