package com.acchain.community.interfaces;

import com.acchain.community.bean.CommitOrderRequest;

import java.util.List;

/**
 * Created on 2018/1/19
 * function : http请求中多个界面都需要用到的接口,会放入这里
 *
 * @author ACChain
 */

public interface CommonHttpInterface {
    /**
     * 获取短信验证码
     *
     * @param mobile 手机号
     * @param tag    操作类型
     * @param code   图形验证码
     */
    void sendMessage(String mobile, String tag, String code);

    /**
     * 获取邮箱验证码
     *
     * @param email 邮箱
     * @param tag   操作类型
     * @param code  图形验证码
     */
    void sendEmail(String email, String tag, String code);

    /**
     * 校验验证码
     *
     * @param username 手机号/邮箱
     * @param tag      操作类型
     * @param code     短信验证码
     */
    void checkCode(String username, String tag, String code);

    /**
     * 1.9 获取会员账户信息
     *
     * @param token token值
     */
    void getAccount(String token);

    /**
     * 1.2.3 设置个人信息
     *
     * @param token    登录标志
     * @param sex      性别
     * @param province 省份
     * @param city     城市
     */
    void setPersonalInfo(String token, int sex, String province, String city);

    /**
     * 1.1.9 修改收货地址
     *
     * @param token    登录标志
     * @param name     收货人姓名
     * @param mobile   手机号码
     * @param province 所在省份
     * @param city     所在城市
     * @param area     所在区
     * @param address  详细地址
     * @param addId    地址ID
     */
    void updateAddress(String token, String name, String mobile, String province, String city, String area, String address, int addId, int defaultStatus);

    /**
     * 1.2.0 删除收货地址
     *
     * @param token 登录标志
     * @param addId 地址ID
     */
    void deleteAddress(String token, int addId);

    /**
     * 1.1.5 绑定邮箱或手机
     *
     * @param token         登录标志
     * @param emailOrMobile 手机或邮箱地址
     * @param payPwd        交易密码
     * @param code          验证码
     */
    void updateEmailOrMobile(String token, String emailOrMobile, String payPwd, String code);

    /**
     * 1.1.6  设置交易密码
     *
     * @param token      登录标志
     * @param confPayPwd 确认交易密码
     * @param payPwd     交易密码
     * @param code       验证码
     */
    void setPayPwd(String token, String confPayPwd, String payPwd, String code);

    /**
     * 1.3.4 获取支付密码重置申请审核状态
     *
     * @param token 登录标志登录标志
     */
    void getPayPwdArtificial(String token);

    /**
     * 1.3.5 修改支付密码(通过原密码)
     *
     * @param token  登录标志登录标志
     * @param oldPwd 原支付密码(SHA256)
     * @param newPwd 新支付密码(此参数为空接口用作校验原支付密码是否正确,SHA256加密)
     */
    void changePayPwd(String token, String oldPwd, String newPwd);

    /**
     * 1.3.6 撤销支付密码重置申请
     *
     * @param token 登录标志登录标志
     * @param id    申请ID
     */
    void cancelArtificial(String token, int id);

    /**
     * 1.1.5  查询钱包用户余额
     *
     * @param token    token
     * @param currency 币种信息
     */
    void accountBlances(String token, String currency);

    /**
     * 1.1.8  钱包流水记录查询
     *
     * @param token    token
     * @param currency 币种信息
     * @param tag      tag标签(transfer转出平台)(transferFriend转出朋友)(recharge转入)(exercise行权)(allList全部流水集合)
     * @param year     年份(非必传)
     * @param month    月份(非必传)
     */
    void queryTransactionList(String token,String currency,String tag,String year,String month);

    /**
     * 加入购物车前检查商品状态
     *
     * @param itemCount              数量
     * @param flashSaleRefId
     * @param productType
     * @param productSubId
     * @param productId
     * @param productAttrValueIds    商品属性id的拼接
     * @param adoptCodeId            领养标的的id的拼接
     * @param token
     */
    void checkProductState(int itemCount, Integer flashSaleRefId, int productType, int productSubId, int productId, String productAttrValueIds, String adoptCodeId, String token);

    /**
     * 加入购物车
     *
     * @param productType         商品类型0：抢购1：预售 2：领养
     * @param productId           主商品ID
     * @param productSubId        抢购/预售/领养ID
     * @param itemCount           商品购买数量
     * @param productAttrValueIds 商品详情对应attrValueId 如: (1,3,5)
     * @param flashSaleId         限购活动ID;商品类型为0时,必传 (非必传)
     * @param adoptCodeId         领养标的id的拼接
     * @param token               会员ID,用户登录时必传 (非必传)
     */
    void addCart(int productType, int productId, int productSubId, int itemCount, String productAttrValueIds, Integer flashSaleId, String adoptCodeId, String token);

    /**
     * @param cardId                购物车ID
     * @param cartType              购物车类型0：抢购1：预售 2：领养
     * @param token              会员ID
     * @param itemCount             商品购买数量
     * @param productSubIds         抢购/预售/领养ID
     * @param productAttrValueIds   商品详情对应attrValueId如: (1,3,5)
     * @param flashSaleRefId        限购活动与商品关联表ID;商品类型为0时,必传（购物车获取）
     * @param adoptCodeIds          领养物品IDs(1,3,5); 商品类型为2时,必传
     * @param productId             主商品ID
     * @param type                  类型
     */
    void editCart(int cardId,int cartType,String token,Integer itemCount,int productSubIds,String productAttrValueIds,Integer flashSaleRefId,String adoptCodeIds,int productId ,int type);

    /**
     * 购物车删除（支持批量）
     *
     * @param token                登录标志，登录了就传
     * @param cartType             购物车类型：  0：抢购1：预售 2：领养
     * @param cardIds              如果是批量删除的话，是购物车id的拼接
     * @param productSubIds        如果是批量删除的话，是商品id的拼接
     * @param type                 0--单个删除，1--批量删除
     */
    void deleteCart(String token,int cartType,String cardIds,String productSubIds,int type);

    /**
     * 确认订单,将三种订单写在一个方法里面
     *
     * @param token              用户登录标识
     * @param productType        商品类型0：抢购1：预售 2：领养 3：行权
     * @param itemCount          购买商品数量
     * @param productSubId       4种具体商品类型商品ID
     * @param flashSaleRefId     只有从抢购详情过来的才用传
     * @param cartIds            非必填，如果是购物车列表页面过来带ids
     * @param productAttrValeIds 商品详情对应attrValueId 如: (1,3,5)
     * @param adoptCodeIds       领养时对应的标的的id拼接 如：（1,3,5）
     * @param type               根据type的不同来返回不同的对象，0--抢购订单，1--购物车抢购订单，2--预售订单，3--购物车预售订单，4--领养订单，5--购物车领养订单
     */
    void confirmOrder(String token, int productType, Integer itemCount, Integer productSubId, Integer flashSaleRefId,String cartIds, String productAttrValeIds,String adoptCodeIds,int type);


    /**
     * 提交订单 将三种订单写在一起
     *
     * @param commitOrderRequests             订单对象集合
     * @param type                     根据type的不同来返回不同的对象，0--抢购订单，1--购物车抢购订单，2--预售订单，3--购物车预售订单，4--领养订单，5--购物车领养订单
     */
    void commitOrder(List<CommitOrderRequest> commitOrderRequests, int type);


    /**
     * 微信生成预交易单号
     *
     * @param token            用户登录标识
     * @param productType      商品类型0：抢购1：预售 2：领养
     * @param orderCodes       单号
     * @param payType          支付方式 1--支付宝，2--微信
     * @param type             0--抢购订单，1--购物车抢购订单，2--预售订单，3--购物车预售订单，4--领养订单，5--购物车领养订单
     */
    void weiXinPay(String token,int productType,String orderCodes,int payType,int type);

    /**
     * 支付宝生成预交易单号
     *
     * @param token            用户登录标识
     * @param productType      商品类型0：抢购1：预售 2：领养
     * @param orderCodes       单号
     * @param payType          支付方式 1--支付宝，2--微信
     * @param type             0--抢购订单，1--购物车抢购订单，2--预售订单，3--购物车预售订单，4--领养订单，5--购物车领养订单
     */
    void aliPay(String token,int productType,String orderCodes,int payType,int type);

    /**
     * 查询微信支付结果
     *
     * @param token       用户登录标识
     * @param productType 商品类型0：抢购1：预售 2：领养 3：行权
     * @param orderCodes  单号
     * @param outTradeNo  支付订单号
     */
    void queryWechatPay(String token,int productType, String orderCodes,String outTradeNo);

    /**
     * 查询支付宝支付结果
     *
     * @param token       用户登录标识
     * @param productType 商品类型0：抢购1：预售 2：领养 3：行权
     * @param orderCodes  单号
     * @param outTradeNo  支付订单号
     * @param type        0--抢购订单，1--购物车抢购订单，2--预售订单，3--购物车预售订单，4--领养订单，5--购物车领养订单
     */
    void queryAliPay(String token,int productType, String orderCodes,String outTradeNo,int type);

    /**
     * 添加或删除收藏
     *
     * @param token           登录标志
     * @param commodityId     商品(类别商品)ID
     * @param commodityType   商品所属类别1:预售 2:领养 3:行权 注:抢购不可收藏
     * @param saleId          商品(主商品)ID
     */
    void addOrDeleteCollection(String token,int commodityId,int commodityType,int saleId);

    /**
     * 添加用户访问足迹
     *
     * @param deviceId         设备ID
     * @param token            token
     * @param commodityId      商品(类别商品)ID
     * @param commodityType    商品所属类别1:预售 2:领养 3:行权 4:抢购
     * @param saleId           商品(主商品)ID
     */
    void addUserFooter(String deviceId,String token,int commodityId,int commodityType,int saleId);

}
