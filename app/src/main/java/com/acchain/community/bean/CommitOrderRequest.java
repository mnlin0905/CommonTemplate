package com.acchain.community.bean;

/**
 * Created by Administrator on 2018/2/9.
 */

public class CommitOrderRequest {
    private String token;                  // 用户标识
    private int productType;              //  商品类型  0：抢购，1预售2领养 3 行权
    private Integer itemCount;                //  商品购买数量
    private Integer productSubId;             // 4种商品的子ID(抢购/预售/领养/行权表ID)
    private Integer productId;                //  商品主id
    private String productAttrValueIds;   //  已选择的商品属性IDS
    private Integer flashSaleRefId;       //  限时抢购与活动关联表id抢购商品详情可获取数据
    private int addressId;                //  用户选择的收货地址
    private String adoptCodeIds;          //  领养编号IDs (只有领养商品才传)
    private Integer cartId;               //  购物车id

    /*行权和预购详情*/
    public CommitOrderRequest(String token, int productType, Integer itemCount, Integer productSubId, Integer productId, String productAttrValueIds, int addressId) {
        this.token = token;
        this.productType = productType;
        this.itemCount = itemCount;
        this.productSubId = productSubId;
        this.productId=productId;
        this.productAttrValueIds = productAttrValueIds;
        this.addressId=addressId;
    }

    /*抢购详情*/
    public CommitOrderRequest(String token, int productType, Integer itemCount, Integer productSubId, Integer productId, String productAttrValueIds, Integer flashSaleRefId, int addressId) {
        this.token = token;
        this.productType = productType;
        this.itemCount = itemCount;
        this.productSubId = productSubId;
        this.productId = productId;
        this.productAttrValueIds = productAttrValueIds;
        this.flashSaleRefId = flashSaleRefId;
        this.addressId = addressId;
    }

    /*领养详情*/
    public CommitOrderRequest(String token, int productType, Integer itemCount, Integer productSubId, Integer productId, int addressId, String adoptCodeIds) {
        this.token = token;
        this.productType = productType;
        this.itemCount = itemCount;
        this.productSubId = productSubId;
        this.productId = productId;
        this.addressId = addressId;
        this.adoptCodeIds = adoptCodeIds;
    }

    /*购物车*/
    public CommitOrderRequest(String token, int productType,Integer itemCount, int addressId, Integer cartId) {
        this.token = token;
        this.productType = productType;
        this.itemCount=itemCount;
        this.addressId = addressId;
        this.cartId = cartId;
    }
}
