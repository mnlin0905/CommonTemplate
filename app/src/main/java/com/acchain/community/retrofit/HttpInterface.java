package com.acchain.community.retrofit;

import com.acchain.community.base.BaseHttpBean;
import com.acchain.community.bean.AccountBalance;
import com.acchain.community.bean.AdoptBaseCode;
import com.acchain.community.bean.AdoptBaseLocation;
import com.acchain.community.bean.AdoptGoodDetail;
import com.acchain.community.bean.AdoptProduct;
import com.acchain.community.bean.AliPay;
import com.acchain.community.bean.Card;
import com.acchain.community.bean.CardDetail;
import com.acchain.community.bean.CartAdoptGoods;
import com.acchain.community.bean.CartPanicGoods;
import com.acchain.community.bean.CartPreGoods;
import com.acchain.community.bean.CommitOrderResponse;
import com.acchain.community.bean.CommonCart;
import com.acchain.community.bean.ConfirmAdoptOrder;
import com.acchain.community.bean.ConfirmCartAdoptOrder;
import com.acchain.community.bean.ConfirmCartPanicOrder;
import com.acchain.community.bean.ConfirmCartPreOrder;
import com.acchain.community.bean.ConfirmExerciseOrder;
import com.acchain.community.bean.CommitOrderRequest;
import com.acchain.community.bean.ConfirmPanicOrder;
import com.acchain.community.bean.ConfirmPreOrder;
import com.acchain.community.bean.CurrencyAddressBean;
import com.acchain.community.bean.CurrencyBean;
import com.acchain.community.bean.DeliveryAddressBean;
import com.acchain.community.bean.ExercisePay;
import com.acchain.community.bean.ExerciseGoodsDetail;
import com.acchain.community.bean.ExerciseProduct;
import com.acchain.community.bean.Friend;
import com.acchain.community.bean.GetAccountBean;
import com.acchain.community.bean.GoodsCollectionFootBean;
import com.acchain.community.bean.IndexDataBean;
import com.acchain.community.bean.PanicGood;
import com.acchain.community.bean.PanicGoodDetail;
import com.acchain.community.bean.PhoneContactResult;
import com.acchain.community.bean.PreSellGoodsDetail;
import com.acchain.community.bean.PreSellProduct;
import com.acchain.community.bean.QueryPay;
import com.acchain.community.bean.ReceverRedPacketRecord;
import com.acchain.community.bean.RedPacketDetail;
import com.acchain.community.bean.SendRedPacket;
import com.acchain.community.bean.SendRedPacketRecord;
import com.acchain.community.bean.TakeInCurrencyAgent;
import com.acchain.community.bean.TransPasswordStatus;
import com.acchain.community.bean.TransactionDetailBean;
import com.acchain.community.bean.TransactionListBean;
import com.acchain.community.bean.TransactionRecordBean;
import com.acchain.community.bean.TransferInfo;
import com.acchain.community.bean.WalletAdoptList;
import com.acchain.community.bean.WalletPresellList;
import com.acchain.community.bean.WeiXinPay;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * 功能----使用retrofit框架与http交换数据
 * <p>
 * Created by ACChain on 2017/9/25.
 * <p>
 * 注:短信/邮箱 验证码
 */

public interface HttpInterface {
    /**
     * 1.0.1 登录
     *
     * @param username 用户名
     * @param pwd      密码
     * @return 登录返回对象
     */
    @POST("login/doLogin")
    @FormUrlEncoded
    Observable<BaseHttpBean<Object>> doLogin(@Field("username") String username,
                                             @Field("pwd") String pwd);

    /**
     * 1.0.2 动态登录
     *
     * @param username 手机号
     * @param code     验证码
     * @return 登录返回对象
     */
    @POST("login/dynamicLogin")
    @FormUrlEncoded
    Observable<BaseHttpBean<Object>> dynamicLogin(@Field("username") String username,
                                                  @Field("code") String code);

    /**
     * 1.0.3 获取图形验证码
     * 直接访问url下载图片
     */
    @GET("randomCode/getImgCode")
    Observable<BaseHttpBean<Object>> getImgCode();

    /**
     * 1.0.4 获取短信验证码
     *
     * @param mobile 手机号
     * @param tag    操作类型
     * @param code   图形验证码
     * @return 登录返回对象
     */
    @POST("randomCode/sendMessage")
    @FormUrlEncoded
    Observable<BaseHttpBean<Object>> sendMessage(@Field("mobile") String mobile,
                                                 @Field("tag") String tag,
                                                 @Field("code") String code);

    /**
     * 1.0.5 获取邮箱验证码
     *
     * @param email 邮箱
     * @param tag   操作类型
     * @param code  图形验证码
     * @return 登录返回对象
     */
    @POST("randomCode/sendEmail")
    @FormUrlEncoded
    Observable<BaseHttpBean<Object>> sendEmail(@Field("email") String email,
                                               @Field("tag") String tag,
                                               @Field("code") String code);

    /**
     * 1.0.6 校验验证码
     *
     * @param username 手机号/邮箱
     * @param tag      操作类型
     * @param code     短信验证码
     * @return 登录返回对象
     */
    @POST("randomCode/checkCode")
    @FormUrlEncoded
    Observable<BaseHttpBean<Object>> checkCode(@Field("username") String username,
                                               @Field("tag") String tag,
                                               @Field("code") String code);

    /**
     * 1.0.6 用户注册
     *
     * @param username 手机号/邮箱
     * @param pwd      密码
     * @param code     验证码
     * @return 登录返回对象
     */
    @POST("register/newUser")
    @FormUrlEncoded
    Observable<BaseHttpBean<Object>> newUser(@Field("username") String username,
                                             @Field("pwd") String pwd,
                                             @Field("code") String code);

    /**
     * 1.0.8 重置登录密码(手机/邮箱)
     *
     * @param username 手机号/邮箱
     * @param pwd      密码(sha256)
     * @param code     验证码
     * @param confPwd  密码再次确认
     * @return 登录返回对象
     */
    @POST("register/resetPwd")
    @FormUrlEncoded
    Observable<BaseHttpBean<Object>> resetPwd(@Field("username") String username,
                                              @Field("pwd") String pwd,
                                              @Field("code") String code,
                                              @Field("confPwd") String confPwd);

    /**
     * 1.0.9 获取会员账户信息
     *
     * @param token token值
     * @return 请求返回信息
     */
    @POST("account/getAccount")
    @FormUrlEncoded
    Observable<BaseHttpBean<GetAccountBean>> getAccount(@Field("token") String token);

    /**
     * 1.1.0 设置头像
     *
     * @param body 头像文件和token的混合流(token,img)
     * @return 请求返回信息
     */
    @POST("account/setPhoto")
    Observable<BaseHttpBean<Object>> setPhoto(@Body MultipartBody body);

    /**
     * 1.1.1 设置昵称
     *
     * @param token    token
     * @param nickname 昵称
     * @return 请求返回信息
     */
    @POST("account/setNickname")
    @FormUrlEncoded
    Observable<BaseHttpBean<Object>> setNickname(@Field("token") String token,
                                                 @Field("nickname") String nickname);

    /**
     * 1.1.2 实名认证
     * <p>
     * 参数:  token    登录标志
     * 参数:  country  国家地区
     * 参数:  name     真实姓名
     * 参数:  cardType 证件类型 1:身份证 2:军官证 3:护照
     * 参数:  idNo     证件号码
     * 参数:  mobile   手机号码
     * 参数:  code     短信验证码
     * 参数:  positive 证件正面照
     * 参数:  contrary 证件反面照
     * 参数:  hord     手持证件照
     *
     * @return 请求返回信息
     */
    @POST("account/verified")
    Observable<BaseHttpBean<Object>> verified(@Body MultipartBody body);

    /**
     * 1.1.3 获取用户实名信息
     *
     * @param token 登录标志
     * @return 请求返回信息
     */
    @POST("account/getUserVerified")
    @FormUrlEncoded
    Observable<BaseHttpBean<GetAccountBean.TbMemberVerifiedInfo>> getUserVerified(@Field("token") String token);

    /**
     * 1.1.4 修改登录密码
     *
     * @param token  登录标志
     * @param oldPwd 旧密码(sha256)
     * @param newPwd 新密码(sha256)
     * @return 请求返回信息
     */
    @POST("account/updatePwd")
    @FormUrlEncoded
    Observable<BaseHttpBean<Object>> updatePwd(@Field("token") String token,
                                               @Field("oldPwd") String oldPwd,
                                               @Field("newPwd") String newPwd);

    /**
     * 1.1.5 绑定邮箱或手机
     *
     * @param token         登录标志
     * @param emailOrMobile 手机或邮箱地址
     * @param payPwd        交易密码
     * @param code          验证码
     * @return 请求返回信息
     */
    @POST("account/updateEmailOrMobile")
    @FormUrlEncoded
    Observable<BaseHttpBean<Object>> updateEmailOrMobile(@Field("token") String token,
                                                         @Field("emailOrMobile") String emailOrMobile,
                                                         @Field("payPwd") String payPwd,
                                                         @Field("code") String code);

    /**
     * 1.1.6  设置交易密码
     *
     * @param token      登录标志
     * @param confPayPwd 确认交易密码
     * @param payPwd     交易密码
     * @param code       验证码
     * @return 请求返回信息
     */
    @POST("account/setPayPwd")
    @FormUrlEncoded
    Observable<BaseHttpBean<Object>> setPayPwd(@Field("token") String token,
                                               @Field("confPayPwd") String confPayPwd,
                                               @Field("payPwd") String payPwd,
                                               @Field("code") String code);

    /**
     * 1.1.7 新增收货地址
     *
     * @param token    登录标志
     * @param name     收货人姓名
     * @param mobile   手机号码
     * @param province 所在省份
     * @param city     所在城市
     * @param area     所在区
     * @param address  详细地址
     * @return 请求返回信息
     */
    @POST("account/addAddress")
    @FormUrlEncoded
    Observable<BaseHttpBean<Object>> addAddress(@Field("token") String token,
                                                @Field("name") String name,
                                                @Field("mobile") String mobile,
                                                @Field("province") String province,
                                                @Field("city") String city,
                                                @Field("area") String area,
                                                @Field("address") String address,
                                                @Field("defaultStatus") int defaultStatus);

    /**
     * 1.1.8 获取用户收货地址列表
     *
     * @param token    登录标志
     * @param page     请求页码
     * @param pageSize 数据量
     * @return 请求返回信息
     */
    @POST("account/getUserAddress")
    @FormUrlEncoded
    Observable<BaseHttpBean<List<DeliveryAddressBean>>> getUserAddress(@Field("token") String token,
                                                                       @Field("page") int page,
                                                                       @Field("pageSize") int pageSize);

    /**
     * 1.1.9 修改收货地址
     *
     * @param token         登录标志
     * @param name          收货人姓名
     * @param mobile        手机号码
     * @param province      所在省份
     * @param city          所在城市
     * @param defaultStatus 0为默认收货地址
     * @param area          所在区
     * @param address       详细地址
     * @param addId         地址ID
     * @return 请求返回信息
     */
    @POST("account/updateAddress")
    @FormUrlEncoded
    Observable<BaseHttpBean<Object>> updateAddress(@Field("token") String token,
                                                   @Field("name") String name,
                                                   @Field("mobile") String mobile,
                                                   @Field("province") String province,
                                                   @Field("city") String city,
                                                   @Field("area") String area,
                                                   @Field("address") String address,
                                                   @Field("addId") int addId,
                                                   @Field("defaultStatus") int defaultStatus);

    /**
     * 1.2.0 删除收货地址
     *
     * @param token 登录标志
     * @param addId 地址ID
     * @return 请求返回信息
     */
    @POST("account/deleteAddress")
    @FormUrlEncoded
    Observable<BaseHttpBean<Object>> deleteAddress(@Field("token") String token,
                                                   @Field("addId") int addId);

    /**
     * 1.2.1 添加/删除收藏
     *
     * @param token         登录标志
     * @param commodityId   商品(类别商品)ID
     * @param commodityType 商品所属类别 1:预售 2:领养 3:行权 注:抢购不可收藏
     * @param saleId        商品(主商品)ID
     * @return 请求返回信息
     */
    @POST("account/addCollection")
    @FormUrlEncoded
    Observable<BaseHttpBean<Object>> addCollection(@Field("token") String token,
                                                   @Field("commodityId") int commodityId,
                                                   @Field("commodityType") int commodityType,
                                                   @Field("saleId") int saleId);

    /**
     * 1.2.2 获取用户收藏列表
     *
     * @param token         登录标志
     * @param commodityId   商品(类别商品)ID
     * @param commodityType 商品所属类别 1:预售 2:领养 3:行权 注:抢购不可收藏
     * @param page          请求页码
     * @param pageSize      页面容量
     * @return 请求返回信息
     */
    @POST("account/getUserCollections")
    @FormUrlEncoded
    Observable<BaseHttpBean<List<GoodsCollectionFootBean>>> getUserCollections(@Field("token") String token,
                                                                               @Field("commodityId") String commodityId,
                                                                               @Field("commodityType") String commodityType,
                                                                               @Field("page") int page,
                                                                               @Field("pageSize") int pageSize);

    /**
     * 1.2.3 设置个人信息
     *
     * @param token    登录标志
     * @param sex      性别
     * @param province 省份
     * @param city     城市
     * @return 请求返回信息
     */
    @POST("account/setPersonalInfo")
    @FormUrlEncoded
    Observable<BaseHttpBean<Object>> setPersonalInfo(@Field("token") String token,
                                                     @Field("sex") String sex,
                                                     @Field("province") String province,
                                                     @Field("city") String city);

    /**
     * 1.2.4 设置名片信息
     *
     * @param token        登录标志登录标志
     * @param position     职位
     * @param companyName  公司名称
     * @param mainBusiness 主营业务
     * @param phone        电话
     * @param email        邮箱
     * @param address      公司地址
     * @param area         区域
     * @param introduction 企业简介
     * @return 请求返回信息
     */
    @POST("account/setBusinessCard")
    @FormUrlEncoded
    Observable<BaseHttpBean<Object>> setBusinessCard(@Field("token") String token,
                                                     @Field("position") String position,
                                                     @Field("companyName") String companyName,
                                                     @Field("mainBusiness") String mainBusiness,
                                                     @Field("phone") String phone,
                                                     @Field("email") String email,
                                                     @Field("address") String address,
                                                     @Field("area") String area,
                                                     @Field("introduction") String introduction);

    /**
     * 1.2.5 获取名片信息
     *
     * @param token 登录标志登录标志
     * @return 请求返回信息
     */
    @POST("account/getBusinessCard")
    @FormUrlEncoded
    Observable<BaseHttpBean<GetAccountBean.BusinessCard>> getBusinessCard(@Field("token") String token);

    /**
     * 1.2.6 获取用户默认收货地址
     *
     * @param token 登录标志登录标志
     * @return 请求返回信息
     */
    @POST("account/getUserDefaultAddr")
    @FormUrlEncoded
    Observable<BaseHttpBean<DeliveryAddressBean>> getUserDefaultAddr(@Field("token") String token);

    /**
     * 1.2.7 设置(重置/关闭)微口令
     *
     * @param token 登录标志登录标志
     * @return 请求返回信息
     */
    @POST("account/setWeiCode")
    @FormUrlEncoded
    Observable<BaseHttpBean<String>> setWeiCode(@Field("token") String token);

    /**
     * 1.2.8 获取用户微口令
     *
     * @param token 登录标志登录标志
     * @return 请求返回信息
     */
    @POST("account/getWeiCode")
    @FormUrlEncoded
    Observable<BaseHttpBean<String>> getWeiCode(@Field("token") String token);

    /**
     * 1.2.9 添加用户访问足迹
     *
     * @param token         登录标志登录标志
     * @param deviceId      设备ID
     * @param commodityId   商品(类别商品)ID
     * @param commodityType 商品所属类别1:预售 2:领养 3:行权 4:抢购
     * @param saleId        商品(主商品)ID
     * @return 请求返回信息
     */
    @POST("accountOther/setAccessLog")
    @FormUrlEncoded
    Observable<BaseHttpBean<Object>> setAccessLog(@Field("token") String token,
                                                  @Field("deviceId") String deviceId,
                                                  @Field("commodityId") int commodityId,
                                                  @Field("commodityType") int commodityType,
                                                  @Field("saleId") int saleId);

    /**
     * 1.3.0 获取用户访问足迹
     *
     * @param token    登录标志登录标志
     * @param deviceId 设备ID
     * @param page     页码
     * @param pageSize 页面容量
     * @return 请求返回信息
     */
    @POST("account/getAccessLog")
    @FormUrlEncoded
    Observable<BaseHttpBean<List<GoodsCollectionFootBean>>> getAccessLog(@Field("token") String token,
                                                                         @Field("deviceId") String deviceId,
                                                                         @Field("page") int page,
                                                                         @Field("pageSize") int pageSize);

    /**
     * 1.3.1 删除收藏记录
     *
     * @param token 登录标志登录标志
     * @param ids   收藏ID集合JSON字符串
     * @return 请求返回信息
     */
    @POST("account/delCollections")
    @FormUrlEncoded
    Observable<BaseHttpBean<Object>> delCollections(@Field("token") String token,
                                                    @Field("ids") String ids);

    /**
     * 1.3.2 删除访问足迹记录
     *
     * @param token 登录标志登录标志
     * @param ids   足迹id集合
     * @return 请求返回信息
     */
    @POST("account/delAccessLogs")
    @FormUrlEncoded
    Observable<BaseHttpBean<Object>> delAccessLogs(@Field("token") String token,
                                                   @Field("ids") String ids);

    /**
     * 1.3.3 开始人工审核申请
     *
     * @param token    token
     * @param mobile   接受审核通知手机号码
     * @param positive 身份证件正面照
     * @param back     身份证件反面照
     * @param handheld 手持身份证件照
     * @param cardType 证件类型 1:身份证 2:军官证 3:护照
     * @return 请求返回信息
     */
    @POST("account/resetPayPwdArtificial")
    @FormUrlEncoded
    Observable<BaseHttpBean<Object>> resetPayPwdArtificial(@Field("token") String token,
                                                           @Field("mobile") String mobile,
                                                           @Field("positive") String positive,
                                                           @Field("back") String back,
                                                           @Field("handheld") String handheld,
                                                           @Field("cardType") int cardType);

    /**
     * 1.3.4 获取支付密码重置申请审核状态
     *
     * @param token 登录标志登录标志
     * @return 请求返回信息
     */
    @POST("account/getPayPwdArtificial")
    @FormUrlEncoded
    Observable<BaseHttpBean<TransPasswordStatus>> getPayPwdArtificial(@Field("token") String token);

    /**
     * 1.3.5 修改支付密码(通过原密码)
     *
     * @param token  登录标志登录标志
     * @param oldPwd 原支付密码(SHA256)
     * @param newPwd 新支付密码(此参数为空接口用作校验原支付密码是否正确,SHA256加密)
     * @return 请求返回信息
     */
    @POST("account/changePayPwd")
    @FormUrlEncoded
    Observable<BaseHttpBean<Object>> changePayPwd(@Field("token") String token,
                                                  @Field("oldPwd") String oldPwd,
                                                  @Field("newPwd") String newPwd);

    /**
     * 1.3.6 撤销支付密码重置申请
     *
     * @param token 登录标志登录标志
     * @param id    申请ID
     * @return 请求返回信息
     */
    @POST("account/cancelArtificial")
    @FormUrlEncoded
    Observable<BaseHttpBean<Object>> cancelArtificial(@Field("token") String token,
                                                      @Field("id") int id);

    /**
     * 1.3.7 修改支付密码(人工申请通过)
     *
     * @param token  登录标志登录标志
     * @param payPwd 支付密码(SHA256加密)
     * @return 请求返回信息
     */
    @POST("account/changePayPwdForArtificial")
    @FormUrlEncoded
    Observable<BaseHttpBean<Object>> changePayPwdForArtificial(@Field("token") String token,
                                                               @Field("payPwd") String payPwd);

    /**
     * 1.3.8 获取用户交易列表
     *
     * @param token    登录标志登录标志
     * @param type     交易类型  0:预售 1:领养
     * @param page     页码
     * @param pageSize 页面容量
     * @return 请求返回信息
     */
    @POST("account/getTransactions")
    @FormUrlEncoded
    Observable<BaseHttpBean<List<TransactionRecordBean>>> getTransactions(@Field("token") String token,
                                                                          @Field("type") String type,
                                                                          @Field("page") int page,
                                                                          @Field("pageSize") int pageSize);

    /**
     * 1.3.9 获取交易详情
     *
     * @param token 登录标志登录标志
     * @param id    交易ID（非交易哈希）
     * @return 请求返回信息
     */
    @POST("account/getTransactionDetail")
    @FormUrlEncoded
    Observable<BaseHttpBean<TransactionDetailBean>> getTransactionDetail(@Field("token") String token,
                                                                         @Field("id") int id);

    /////////////////////////////////////////////// 钱包模块接口开始

    /**
     * 1.1.0 查询币种列表集合(转入)
     *
     * @param token    token
     * @param currency 币种信息,简称,可用于进行过滤,可为null
     * @return 请求返回信息
     */
    @POST("wallet/currencyList")
    @FormUrlEncoded
    Observable<BaseHttpBean<List<TakeInCurrencyAgent>>> currencyList(@Field("token") String token,
                                                                     @Field("currency") String currency);

    /**
     * 1.1.1 钱包转出(账户余额大于0)
     *
     * @param token    token
     * @param currency 币种信息,简称,可用于进行过滤,可为null
     * @return 请求返回信息
     */
    @POST("wallet/getCurrencyListTransfer")
    @FormUrlEncoded
    Observable<BaseHttpBean<List<CurrencyBean>>> getCurrencyListTransfer(@Field("token") String token,
                                                                         @Field("currency") String currency);

    /**
     * 1.1.2 创建ACC链钱包地址
     *
     * @param token    token
     * @param currency 币种信息
     * @return 请求返回信息
     */
    @POST("wallet/createACC")
    @FormUrlEncoded
    Observable<BaseHttpBean<CurrencyAddressBean>> createACC(@Field("token") String token,
                                                            @Field("currency") String currency);

    /**
     * 1.1.3  创建BTC链钱包地址
     *
     * @param token    token
     * @param currency 币种信息
     * @return 请求返回信息
     */
    @POST("wallet/createBTC")
    @FormUrlEncoded
    Observable<BaseHttpBean<CurrencyAddressBean>> createBTC(@Field("token") String token,
                                                            @Field("currency") String currency);

    /**
     * 1.1.4 创建ETH链钱包地址
     *
     * @param token    token
     * @param currency 币种信息
     * @return 请求返回信息
     */
    @POST("wallet/createETH")
    @FormUrlEncoded
    Observable<BaseHttpBean<CurrencyAddressBean>> createETH(@Field("token") String token,
                                                            @Field("currency") String currency);

    /**
     * 1.1.5  查询钱包用户余额
     *
     * @param token    token
     * @param currency 币种信息
     * @return 请求返回信息
     */
    @POST("wallet/accountBlances")
    @FormUrlEncoded
    Observable<BaseHttpBean<AccountBalance>> accountBlances(@Field("token") String token,
                                                            @Field("currency") String currency);

    /**
     * 1.1.6  转出数字资产
     *
     * @param token            登录标志
     * @param currency         币种简称
     * @param number           转出数量
     * @param transactionPwd   支付密码
     * @param verificationCode 手机验证码
     * @param walletAddress    转出地址
     * @return 请求返回信息
     */
    @POST("wallet/transfer")
    @FormUrlEncoded
    Observable<BaseHttpBean<Object>> transfer(@Field("token") String token,
                                              @Field("currency") String currency,
                                              @Field("number") String number,
                                              @Field("transactionPwd") String transactionPwd,
                                              @Field("verificationCode") String verificationCode,
                                              @Field("walletAddress") String walletAddress);

    /**
     * 1.1.7  转出数字资产获取验证码
     *
     * @param mobile 手机号
     * @param tag    标签 transfer
     * @param code   图形验证码/null
     * @return 请求返回信息
     */
    @POST("wallet/transferSMS")
    @FormUrlEncoded
    Observable<BaseHttpBean<Object>> transferSMS(@Field("mobile") String mobile,
                                                 @Field("tag") String tag,
                                                 @Field("code") String code);

    /**
     * 1.1.8  钱包流水记录查询
     *
     * @param token    token
     * @param currency 币种信息
     * @param tag      tag标签(transfer转出平台)(transferFriend转出朋友)(recharge转入)(exercise行权)(allList全部流水集合)
     * @param year     年份(非必传)
     * @param month    月份(非必传)
     * @return 请求返回信息
     */
    @POST("wallet/queryTransactionList")
    @FormUrlEncoded
    Observable<BaseHttpBean<TransactionListBean>> queryTransactionList(@Field("token") String token,
                                                                       @Field("currency") String currency,
                                                                       @Field("tag") String tag,
                                                                       @Field("year") String year,
                                                                       @Field("month") String month);

    /**
     * 1.1.9  转出数字资产(朋友)
     *
     * @param token          登录标志
     * @param currency       币种简称
     * @param friendId       接收者ID
     * @param number         转出数量
     * @param transactionPwd 支付密码
     * @param remarkes       转账备注
     * @param isRedEnvelopes 转账类型  0 钱包转账(默认)   1 红包转账    2 转账至朋友
     * @return 请求返回信息
     */
    @POST("wallet/assetsTransferOutFriend")
    @FormUrlEncoded
    Observable<BaseHttpBean<TransferInfo>> assetsTransferOutFriend(@Field("token") String token,
                                                                   @Field("currency") String currency,
                                                                   @Field("friendId") String friendId,
                                                                   @Field("number") String number,
                                                                   @Field("transactionPwd") String transactionPwd,
                                                                   @Field("remarkes") String remarkes,
                                                                   @Field("isRedEnvelopes") int isRedEnvelopes);

    /**
     * 1.2.0  钱包资产转出(撤销)
     *
     * @param token 登录标志
     * @param id    交易ID,主键
     * @return 请求返回信息
     */
    @POST("wallet/transferPlatformRevoke")
    @FormUrlEncoded
    Observable<BaseHttpBean<Object>> transferPlatformRevoke(@Field("token") String token,
                                                            @Field("id") String id);

    /**
     * 1.2.1  钱包预购类
     *
     * @param token 登录标志
     * @return 请求返回信息
     */
    @POST("wallet/presellList")
    @FormUrlEncoded
    Observable<BaseHttpBean<List<WalletPresellList>>> presellList(@Field("token") String token);

    /**
     * 1.2.2  钱包领养类
     *
     * @param token 登录标志
     * @return 请求返回信息
     */
    @POST("wallet/adoptList")
    @FormUrlEncoded
    Observable<BaseHttpBean<List<WalletAdoptList>>> adoptList(@Field("token") String token);

    //////////////////////////////////////////////  钱包模块接口结束


    /**
     * 获取首页数据
     */
    @POST("cms/getIndexData")
    @FormUrlEncoded
    Observable<BaseHttpBean<IndexDataBean>> getIndexData(@Field("channelAliasName") String channelAliasName);

    /**
     * 获取限时抢购信息
     *
     * @param isCache 是否读取缓存，默认为1-读取
     */
    @POST("cms/getFlashSales")
    @FormUrlEncoded
    Observable<BaseHttpBean<List<PanicGood>>> getFlashSales(@Field("noCache") int isCache);

    /**
     * 获取限时抢购详情
     *
     * @param purchaseId  商品id
     * @param flashSaleId 活动id
     */
    @POST("cms/getFlashSalesDetail")
    @FormUrlEncoded
    Observable<BaseHttpBean<PanicGoodDetail>> getFlashSalesDetail(@Field("purchaseId") int purchaseId, @Field("flashSaleId") int flashSaleId);

    /**
     * 加入购物车前检查商品状态
     *
     * @param itemCount      商品购买数量
     * @param flashSaleRefId 如果是抢购，必须传这个参数，否则可以不用管 (非必传)
     * @param productType    0：抢购，1：预售，2：领养，3：行权
     * @param productSubId   商品ID
     */
    @POST("cms/checkProductState")
    @FormUrlEncoded
    Observable<BaseHttpBean<CommonCart>> checkProductState(@Field("itemCount") int itemCount,
                                                           @Field("flashSaleRefId") Integer flashSaleRefId,
                                                           @Field("productType") int productType,
                                                           @Field("productSubId") int productSubId);

    /**
     * 加入购物车
     *
     * @param productType         商品类型0：抢购1：预售 2：领养
     * @param productId           主商品ID
     * @param productSubId        抢购/预售/领养ID
     * @param itemCount           商品购买数量
     * @param productAttrValueIds 商品详情对应attrValueId 如: (1,3,5),领养的时候传标的的拼接
     * @param flashSaleRefId      限购活动ID;商品类型为0时,必传 (非必传)
     * @param adoptCodeId         领养标的id的拼接
     * @param token               会员ID,用户登录时必传 (非必传)
     */
    @POST("cms/addCart")
    @FormUrlEncoded
    Observable<BaseHttpBean<CommonCart>> addCart(@Field("productType") int productType,
                                                 @Field("productId") int productId,
                                                 @Field("productSubId") int productSubId,
                                                 @Field("itemCount") int itemCount,
                                                 @Field("productAttrValueIds") String productAttrValueIds,
                                                 @Field("flashSaleRefId") Integer flashSaleRefId,
                                                 @Field("adoptCodeId") String adoptCodeId,
                                                 @Field("token") String token);


    /**
     * 查询预售商品列表
     *
     * @param pageIndex 当前页，大于1
     * @return
     */
    @POST("cms/getPresellProducts")
    @FormUrlEncoded
    Observable<BaseHttpBean<PreSellProduct>> getPreSellProducts(@Field("pageIndex") int pageIndex);

    /**
     * 查询预售商品详情
     *
     * @param presellId 预售ID
     * @return
     */
    @POST("cms/getPresellProductDetail")
    @FormUrlEncoded
    Observable<BaseHttpBean<PreSellGoodsDetail>> getPresellProductDetail(@Field("presellId") int presellId);

    /**
     * 查询领养商品列表
     *
     * @param pageIndex 当前页，大于1
     * @return
     */
    @POST("cms/getAdoptProducts")
    @FormUrlEncoded
    Observable<BaseHttpBean<AdoptProduct>> getAdoptProducts(@Field("pageIndex") int pageIndex);

    /**
     * 查询领养商品详情
     *
     * @param adoptId 商品ID
     * @return
     */
    @POST("cms/getAdoptProductDetail")
    @FormUrlEncoded
    Observable<BaseHttpBean<AdoptGoodDetail>> getAdoptProductDetail(@Field("adoptId") int adoptId);

    /**
     * 查询行权商品列表
     *
     * @param pageIndex 当前页，大于1
     * @return
     */
    @POST("cms/getExerciseProducts")
    @FormUrlEncoded
    Observable<BaseHttpBean<ExerciseProduct>> getExerciseProducts(@Field("pageIndex") int pageIndex);

    /**
     * 查询行权商品详情
     *
     * @param exerciseId 商品ID
     * @return
     */
    @POST("cms/getExerciseProductDetail")
    @FormUrlEncoded
    Observable<BaseHttpBean<ExerciseGoodsDetail>> getExerciseProductDetail(@Field("exerciseId") int exerciseId);


    /**
     * 确认抢购订单
     *
     * @param token              用户登录标识
     * @param productType        商品类型0：抢购1：预售 2：领养 3：行权
     * @param itemCount          购买商品数量
     * @param productSubId       4种具体商品类型商品ID
     * @param flashSaleRefId     只有从抢购详情过来的才用传
     * @param productAttrValeIds 商品详情对应attrValueId 如: (1,3,5)
     * @return
     */
    @POST("cms/buyNow")
    @FormUrlEncoded
    Observable<BaseHttpBean<ConfirmPanicOrder>> confirmPanicOrder(@Field("token") String token,
                                                                  @Field("productType") int productType,
                                                                  @Field("itemCount") int itemCount,
                                                                  @Field("productSubId") int productSubId,
                                                                  @Field("flashSaleRefId") Integer flashSaleRefId,
                                                                  @Field("productAttrValeIds") String productAttrValeIds);

    /**
     * 确认购物车抢购订单
     *
     * @param token       用户登录标识
     * @param productType 商品类型0：抢购1：预售 2：领养 3：行权
     * @param cartIds     非必填，如果是购物车列表页面过来带ids
     * @return
     */
    @POST("cms/buyNow")
    @FormUrlEncoded
    Observable<BaseHttpBean<ConfirmCartPanicOrder>> confirmCartPanicOrder(@Field("token") String token,
                                                                          @Field("productType") int productType,
                                                                          @Field("cartIds") String cartIds);

    /**
     * 确认预购订单
     *
     * @param token              用户登录标识
     * @param productType        商品类型0：抢购1：预售 2：领养 3：行权
     * @param itemCount          购买商品数量
     * @param productSubId       4种具体商品类型商品ID
     * @param productAttrValeIds 商品详情对应attrValueId 如: (1,3,5)
     * @return
     */
    @POST("cms/buyNow")
    @FormUrlEncoded
    Observable<BaseHttpBean<ConfirmPreOrder>> confirmPreOrder(@Field("token") String token,
                                                              @Field("productType") int productType,
                                                              @Field("itemCount") int itemCount,
                                                              @Field("productSubId") int productSubId,
                                                              @Field("productAttrValeIds") String productAttrValeIds);

    /**
     * 确认购物车预购订单
     *
     * @param token       用户登录标识
     * @param productType 商品类型0：抢购1：预售 2：领养 3：行权
     * @param cartIds     非必填，如果是购物车列表页面过来带ids
     * @return
     */
    @POST("cms/buyNow")
    @FormUrlEncoded
    Observable<BaseHttpBean<ConfirmCartPreOrder>> confirmCartPreOrder(@Field("token") String token,
                                                                      @Field("productType") Integer productType,
                                                                      @Field("cartIds") String cartIds);

    /**
     * 确认领养订单
     *
     * @param token        用户登录标识
     * @param productType  商品类型0：抢购1：预售 2：领养 3：行权
     * @param itemCount    购买商品数量
     * @param productSubId 4种具体商品类型商品ID
     * @param adoptCodeIds 商品详情对应attrValueId 如: (1,3,5)
     * @return
     */
    @POST("cms/buyNow")
    @FormUrlEncoded
    Observable<BaseHttpBean<ConfirmAdoptOrder>> confirmAdoptOrder(@Field("token") String token,
                                                                  @Field("productType") int productType,
                                                                  @Field("itemCount") int itemCount,
                                                                  @Field("productSubId") int productSubId,
                                                                  @Field("adoptCodeIds") String adoptCodeIds);

    /**
     * 确认领养订单
     *
     * @param token       用户登录标识
     * @param productType 商品类型0：抢购1：预售 2：领养 3：行权
     * @param cartIds     非必填，如果是购物车列表页面过来带ids
     * @return
     */
    @POST("cms/buyNow")
    @FormUrlEncoded
    Observable<BaseHttpBean<ConfirmCartAdoptOrder>> confirmCartAdoptOrder(@Field("token") String token,
                                                                          @Field("productType") int productType,
                                                                          @Field("cartIds") String cartIds);

    /**
     * 确认行权订单
     *
     * @param token              用户登录标识
     * @param productType        商品类型0：抢购1：预售 2：领养 3：行权
     * @param itemCount          购买商品数量
     * @param productSubId       4种具体商品类型商品ID
     * @param productAttrValeIds 非必填，如果是购物车列表页面过来带ids
     * @return
     */
    @POST("cms/buyNow")
    @FormUrlEncoded
    Observable<BaseHttpBean<ConfirmExerciseOrder>> confirmExerciseOrder(@Field("token") String token,
                                                                        @Field("productType") int productType,
                                                                        @Field("itemCount") int itemCount,
                                                                        @Field("productSubId") int productSubId,
                                                                        @Field("productAttrValeIds") String productAttrValeIds);


    /**
     * 提交行权订单
     *
     * @param confirmExercise 行权订单对象
     */
    @POST("cms/confirm2Order")
    Observable<BaseHttpBean<ExercisePay>> commitExerciseOrder(@Body List<CommitOrderRequest> confirmExercise);

    /**
     * 提交订单 三种
     *
     * @param commitOrderRequests 订单对象
     */
    @POST("cms/confirm2Order")
    Observable<BaseHttpBean<CommitOrderResponse>> commitOrder(@Body List<CommitOrderRequest> commitOrderRequests);


    /**
     * 微信支付生成预支付单号
     *
     * @param token       用户登录标识
     * @param productType 商品类型0：抢购1：预售 2：领养 3：行权
     * @param orderCodes  单号
     * @param payType     支付方式  1--支付宝，2--微信
     */
    @POST("pay/loadAppPayPre")
    @FormUrlEncoded
    Observable<BaseHttpBean<WeiXinPay>> weiXinPay(@Field("token") String token,
                                                  @Field("productType") int productType,
                                                  @Field("orderCodes") String orderCodes,
                                                  @Field("payType") int payType);

    /**
     * 支付宝支付生成预支付单号
     *
     * @param token       用户登录标识
     * @param productType 商品类型0：抢购1：预售 2：领养 3：行权
     * @param orderCodes  单号
     * @param payType     支付方式  1--支付宝，2--微信
     */
    @POST("pay/loadAppPayPre")
    @FormUrlEncoded
    Observable<BaseHttpBean<AliPay>> aliPay(@Field("token") String token,
                                            @Field("productType") int productType,
                                            @Field("orderCodes") String orderCodes,
                                            @Field("payType") int payType);

    /**
     * 支付宝支付生成预支付单号
     *
     * @param token       用户登录标识
     * @param productType 商品类型0：抢购1：预售 2：领养 3：行权
     * @param orderCodes  单号
     * @param outTradeNo  支付订单号
     */
    @POST("pay/queryWechatPay")
    @FormUrlEncoded
    Observable<BaseHttpBean<QueryPay>> queryWechatPay(@Field("token") String token,
                                                      @Field("productType") int productType,
                                                      @Field("orderCodes") String orderCodes,
                                                      @Field("out_trade_no") String outTradeNo);

    /**
     * 支付宝支付生成预支付单号
     *
     * @param token       用户登录标识
     * @param productType 商品类型0：抢购1：预售 2：领养 3：行权
     * @param orderCodes  单号
     * @param outTradeNo  支付订单号
     */
    @POST("pay/queryAliPay")
    @FormUrlEncoded
    Observable<BaseHttpBean<QueryPay>> queryAliPay(@Field("token") String token,
                                                   @Field("productType") int productType,
                                                   @Field("orderCodes") String orderCodes,
                                                   @Field("out_trade_no") String outTradeNo);

    /**
     * 加载抢购购物车
     *
     * @param cartType 购物车类型 0：抢购1：预售 2：领养
     * @param token    会员ID,用户登录时,必传
     */
    @POST("cms/loadCart")
    @FormUrlEncoded
    Observable<BaseHttpBean<CartPanicGoods>> loadPanicCart(@Field("cartType") int cartType, @Field("token") String token);

    /**
     * 加载预购购物车
     *
     * @param cartType 购物车类型 0：抢购1：预售 2：领养
     * @param token    会员ID,用户登录时,必传
     */
    @POST("cms/loadCart")
    @FormUrlEncoded
    Observable<BaseHttpBean<CartPreGoods>> loadPreCart(@Field("cartType") int cartType, @Field("token") String token);

    /**
     * 加载预购购物车
     *
     * @param cartType 购物车类型 0：抢购1：预售 2：领养
     * @param token    会员ID,用户登录时,必传
     */
    @POST("cms/loadCart")
    @FormUrlEncoded
    Observable<BaseHttpBean<CartAdoptGoods>> loadAdoptCart(@Field("cartType") int cartType, @Field("token") String token);

    /**
     * 修改购物车数据
     *
     * @param cardId              购物车ID
     * @param cartType            购物车类型0：抢购1：预售 2：领养
     * @param token               会员ID
     * @param itemCount           商品购买数量
     * @param productSubIds       抢购/预售/领养ID
     * @param productAttrValueIds 商品详情对应attrValueId如: (1,3,5)
     * @param flashSaleRefId      限购活动与商品关联表ID;商品类型为0时,必传（购物车获取）
     * @param adoptCodeIds        领养物品IDs(1,3,5); 商品类型为2时,必传
     * @param productId           主商品ID
     */
    @POST("cms/editCart")
    @FormUrlEncoded
    Observable<BaseHttpBean<CommonCart>> editCart(@Field("cardId") int cardId,
                                                  @Field("cartType") int cartType,
                                                  @Field("token") String token,
                                                  @Field("itemCount") Integer itemCount,
                                                  @Field("productSubIds") int productSubIds,
                                                  @Field("productAttrValueIds") String productAttrValueIds,
                                                  @Field("flashSaleRefId") Integer flashSaleRefId,
                                                  @Field("adoptCodeIds") String adoptCodeIds,
                                                  @Field("productId") int productId);

    /**
     * 购物车删除（支持批量）
     *
     * @param token         登录标志，登录了就传
     * @param cartType      购物车类型：  0：抢购1：预售 2：领养
     * @param cardIds       如果是批量删除的话，是购物车id的拼接
     * @param productSubIds 如果是批量删除的话，是商品id的拼接
     */
    @POST("cms/delCart")
    @FormUrlEncoded
    Observable<BaseHttpBean<CommonCart>> deleteCart(@Field("token") String token,
                                                    @Field("token") int cartType,
                                                    @Field("token") String cardIds,
                                                    @Field("token") String productSubIds);

    /**
     * 名片列表
     *
     * @return
     */
    @POST("friend/queryBusinessCartList")
    @FormUrlEncoded
    Observable<BaseHttpBean<List<Card>>> getCardList(@Field("token") String token, @Field("queryBusinessCard") String queryBusinessCard);

    /**
     * 发送好友添加请求
     *
     * @param token
     * @param userId
     * @param appleContent
     * @return
     */
    @POST("friend/newFriend")
    @FormUrlEncoded
    Observable<BaseHttpBean<Object>> sendAddFriendRequest(@Field("token") String token,
                                                          @Field("friendId") String userId,
                                                          @Field("appleContent") String appleContent,
                                                          @Field("remarks") String remarks
    );

    /**
     * 好友列表(通讯录和添加请求共同一个接口)
     *
     * @param token
     * @param tag   newFriend
     * @return
     */
    @POST("friend/friendApplyList")
    @FormUrlEncoded
    Observable<BaseHttpBean<List<Friend>>> friendList(@Field("token") String token,
                                                      @Field("tag") String tag
    );

    /**
     * 好友状态 0 验证中  1 已通过  2 黑名单  3 已过期
     *
     * @param token
     * @param friendId
     * @param status
     * @return
     */
    @POST("friend/actionFriend")
    @FormUrlEncoded
    Observable<BaseHttpBean<Object>> friendAction(@Field("token") String token,
                                                  @Field("friendId") String friendId,
                                                  @Field("status") String status

    );

    /**
     * 发送红包
     *
     * @param token
     * @param receiveId
     * @param currency
     * @param amount
     * @param transactionPwd
     * @param remarks
     * @return
     */
    @POST("friend/sendRedEnvelopes")
    @FormUrlEncoded
    Observable<BaseHttpBean<SendRedPacket>> sendRedPacket(@Field("token") String token,
                                                          @Field("receiveId") String receiveId,
                                                          @Field("currency") String currency,
                                                          @Field("amount") String amount,
                                                          @Field("transactionPwd") String transactionPwd,
                                                          @Field("remarks") String remarks
    );

    /**
     * 打开红包
     *
     * @param token
     * @param envelopesId 红包id
     * @return
     */
    @POST("friend/openEnvelopes")
    @FormUrlEncoded
    Observable<BaseHttpBean<Object>> openRedPacket(@Field("token") String token,
                                                   @Field("envelopesId") String envelopesId);

    /**
     * 领养商品查看基地视频
     *
     * @param adoptId 领养id
     * @return
     */
    @POST("cms/findAdoptLocation")
    @FormUrlEncoded
    Observable<BaseHttpBean<List<AdoptBaseLocation>>> findAdoptLocation(@Field("adoptId") int adoptId);


    /**
     * 领养商品选择标的
     *
     * @param adoptId 领养id
     * @return
     */
    @POST("cms/chooseAdoptCode")
    @FormUrlEncoded
    Observable<BaseHttpBean<AdoptBaseCode>> chooseAdoptCode(@Field("adoptId") int adoptId, @Field("localtionId") int locationId, @Field("pageIndex") int pageIndex);

    /**
     * 红包详情
     *
     * @param token
     * @param redEnvelopesId
     * @return
     */
    @POST("friend/queryEnvelopesDetail")
    @FormUrlEncoded
    Observable<BaseHttpBean<RedPacketDetail>> loadRedDetail(@Field("token") String token,
                                                            @Field("redEnvelopesId") String redEnvelopesId
    );

    /**
     * 名片详情
     *
     * @param token
     * @param businessCardId
     * @param tag            queryBusinessCardDetail
     * @return
     */
    @POST("friend/queryBusinessCardDetail")
    @FormUrlEncoded
    Observable<BaseHttpBean<List<CardDetail>>> loadCardDetail(@Field("token") String token,
                                                              @Field("businessCardId") String businessCardId,
                                                              @Field("tag") String tag
    );

    /**
     * 收到的红包记录
     *
     * @param token
     * @return
     */
    @POST("friend/receivedRedEnvelopesList")
    @FormUrlEncoded
    Observable<BaseHttpBean<ReceverRedPacketRecord>> loadReceverRedPacket(@Field("token") String token);

    /**
     * 发出的红包记录
     *
     * @param token
     * @return
     */
    @POST("friend/issueRedEnvelopesList")
    @FormUrlEncoded
    Observable<BaseHttpBean<SendRedPacketRecord>> loadSendRedPacket(@Field("token") String token);

    /**
     * 添加或删除收藏
     *
     * @param token         登录标志
     * @param commodityId   商品(类别商品)ID
     * @param commodityType 商品所属类别1:预售 2:领养 3:行权 注:抢购不可收藏
     * @param saleId        商品(主商品)ID
     */
    @POST("account/addCollection")
    @FormUrlEncoded
    Observable<BaseHttpBean<Object>> addOrDeleteCollection(@Field("token") String token,
                                                           @Field("commodityId") int commodityId,
                                                           @Field("commodityType") int commodityType,
                                                           @Field("saleId") int saleId);


    /**
     * 添加用户访问足迹
     *
     * @param deviceId      设备ID
     * @param token         token
     * @param commodityId   商品(类别商品)ID
     * @param commodityType 商品所属类别1:预售 2:领养 3:行权 4:抢购
     * @param saleId        商品(主商品)ID
     */
    @POST("accountOther/setAccessLog")
    @FormUrlEncoded
    Observable<BaseHttpBean<Object>> addUserFooter(@Field("deviceId") String deviceId,
                                                   @Field("token") String token,
                                                   @Field("commodityId") int commodityId,
                                                   @Field("commodityType") int commodityType,
                                                   @Field("saleId") int saleId);

    /**
     * @param token
     * @param phoneList
     * @return
     */
    @POST("friend/phoneList")
    @FormUrlEncoded
    Observable<BaseHttpBean<List<PhoneContactResult>>> phoneList(@Field("token") String token, @Field("phoneList") String phoneList);


}
