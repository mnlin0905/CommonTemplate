package com.acchain.community.window.dialog;

import java.util.List;

/**
 * Created by Administrator on 2018/1/31.
 * 为选择商品属性的dialog自定义一个商品属性的公共类
 */

public class CommonGoodsAttrsBean {
    private String img;
    private double price;
    private int[] productAttrValueIds;
    private List<AttrListBean> attrList;//属性的id
    private int itemCount;
    private int goodStorge;

    public CommonGoodsAttrsBean(String img, double price, int[] productAttrValueIds, List<AttrListBean> attrList, int itemCount, int goodStorge) {
        this.img = img;
        this.price = price;
        this.productAttrValueIds = productAttrValueIds;
        this.attrList = attrList;
        this.itemCount = itemCount;
        this.goodStorge = goodStorge;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int[] getProductAttrValueIds() {
        return productAttrValueIds;
    }

    public void setProductAttrValueIds(int[] productAttrValueIds) {
        this.productAttrValueIds = productAttrValueIds;
    }

    public List<AttrListBean> getAttrList() {
        return attrList;
    }

    public void setAttrList(List<AttrListBean> attrList) {
        this.attrList = attrList;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public int getGoodStorge() {
        return goodStorge;
    }

    public void setGoodStorge(int goodStorge) {
        this.goodStorge = goodStorge;
    }

    public static class AttrListBean {
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
