package com.acchain.community.bean;

import com.acchain.community.base.BaseBean;

/**
 * Created on 2018/1/20
 * function : 商品收藏/足迹列表
 *
 * @author ACChain
 */

public class GoodsCollectionFootBean extends BaseBean {

    /**
     * img : group1/M00/00/02/wKgB_Vpi4tuAd3IyAAFTSLCgDUw599.png
     * productId : 1
     * saleId : 2
     * createTime : 2018-01-25 21:40:35
     * name : 雪山古树
     * type : 1 1:预售 2:领养 3:行权 4:抢购 注:抢购不可收藏
     * desc : 雪山古树2010普洱生茶，，撒面肥壮、显毫，香	陈香
     */

    private String img;
    private int productId;
    private int saleId;
    private String createTime;
    private String name;
    private int type;
    private String desc;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
