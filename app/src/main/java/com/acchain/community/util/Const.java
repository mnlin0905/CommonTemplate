package com.acchain.community.util;

/**
 * Created by Administrator on 17-1-6.
 */
public class Const {
    //闹铃服务发送的广播,pendingIntent对应的requestCode
    public static final String ALARM_BROADCAST = "com.mnlin.hotchpotch.alarm";
    public static final int ALARM_REQUESTCODE = 1001;

    /**
     * RxBus传递信息,BaseEventBean,信息类型
     * <p>
     * 弹出toast
     * 弹出登录框
     * ...
     */
    public static final int SHOW_TOAST = 2001;
    public static final int SHOW_LOGIN_DIALOG = SHOW_TOAST + 6;
    public static final int SHOW_PROGRESS = SHOW_TOAST + 1;
    public static final int HIDDEN_PROGRESS = SHOW_TOAST + 2;
    public static final int REQUEST_OPEN_BLUETOOTH = SHOW_TOAST + 3;
    public static final int INIT_FLV_SCAN_HAVEBEMATCHEDDEVICE = SHOW_TOAST + 4;
    public static final int REFRESH_FRIEND_REQUEST_LIST = SHOW_TOAST + 5;

    /**
     * 用于传递字段的key,字段,基本类型/String
     * <p>
     * 用于传递bundle时id值,int值
     * 用于传递bundle时type值,int值
     * 用于传递bundle时位置信息key值,int值
     * <p>
     * 上传的照片路径,key值:正面
     * 上传的照片路径,key值:反面
     * 上传的照片路径,key值:手持身份证
     * <p>
     * 币种简称
     * 币种所属chain
     * <p>
     * 人工审核重置交易密码时,需要传递状态信息;在审核通过时,只有修改完密码后才可以重置审核状态
     * 人工审核时,个人审核状态的id
     * <p>
     * 是否为其他界面跳转而来的activity
     * 手机号
     * <p>
     * 用于信息过滤的数据源,集合类型
     * 输入的过滤信息,用于再次过滤数据
     * <p>
     * 帐号
     * 已有的密码
     * 验证码
     * <p>
     * 简介信息
     * <p>
     * 融云聊天朋友的id
     * 红包id
     * <p>
     * 头像路径
     * 昵称
     * 备注信息
     * (转账)数量
     * 转账时附加的信息
     */
    public static final String KEY_ID = "key_id";
    public static final String KEY_TYPE = "key_type";
    public static final String KEY_POSITION = "key_position";

    public static final String KEY_PICTURE_POSITIVE = "key_picture_positive";
    public static final String KEY_PICTURE_NEGATIVE = "key_picture_negative";
    public static final String KEY_PICTURE_MULTIPLE = "key_picture_multiple";

    public static final String KEY_CURRENCY_SHORT_NAME = "key_currency_short_name";
    public static final String KEY_CURRENCY_CHAIN = "key_currency_chain";

    public static final String KEY_TRANS_PWD_STATUS = "key_trans_pwd_status";
    public static final String KEY_STAFF_SERVICE_ID = "key_staff_service_id";

    public static final String KEY_IS_FROM_OTHER_ACTIVITY = "key_is_from_other_activity";
    public static final String KEY_PHONE_NUMBER = "key_phone_number";

    public static final String KEY_FILTER_SOURCE = "key_filter_source";
    public static final String KEY_FILTER_INPUT_KEYS = "key_filter_input_keys";

    public static final String KEY_ACCOUNT = "key_account";
    public static final String KEY_OLD_PASSWORD = "key_old_password";
    public static final String KEY_RANDOM_CODE = "key_random_code";

    public static final String KEY_INTRODUCE = "key_introduce";

    public static final String KEY_TARGET_FRIEND_ID = "key_target_friend_id";
    public static final String KEY_RED_PACKAGE_ID = "key_red_package_id";

    public static final String KEY_HEAD_IMG = "key_head_img";
    public static final String KEY_NIKE_NAME = "key_nike_name";
    public static final String KEY_REMARK = "key_remark";
    public static final String KEY_AMOUNT = "key_amount";
    public static final String KEY_TRANSFER_MESSAGE = "key_transfer_message";


    /**
     * 查询微信支付结果要带的参数
     * <p>
     * 商品类型0：抢购1：预售 2：领养 3：行权
     * 单号
     */
    public static final String KEY_PRODUCT_TYPE = "key_product_type";
    public static final String KEY_ORDER_CODES = "key_order_codes";


    /**
     * 领养id,用来查看基地位置
     * 0--看视频，1--选标的
     * //商品名字，用于显示标题
     */
    public static final String KEY_ADOPT_ID = "key_adopt_id";
    public static final String KEY_PRODUCT_NAME = "key_product_name";

    public static final String KEY_OBJECT_TYPE = "key_object_type";
    public static final int TYPE_WATCH_VIDEO  = 0;
    public static final int TYPE_CHOOSE_OBJECT = 1;

    /**
     * 支付状态
     */
    public static final String KEY_PAY_STATE = "key_pay_state";
    public static final int TYPE_PAY_SUCCESS = 0;
    public static final int TYPE_PAY_CANCEL = 1;
    public static final int TYPE_PAY_FAIL = 2;


    /**
     * 有明确值的常量
     * <p>
     * 非法的位置
     */
    public static final int VALUE_POSITION_NULL = -1;

    /**
     * 传递model的关键字key:
     * <p>
     * 钱包模块具体记录信息传递,model:TransactionItemBean
     * 单个币种,model:CurrencyBean
     * 联系人中朋友:model:Friend
     * 预支付订单的对象
     * 融云聊天朋友信息
     */
    public static final String MODEL_TRANSACTION_RECORD_ITEM = "model_transaction_record_item";
    public static final String MODEL_CURRENCY_BEAN = "model_currency_bean";
    public static final String MODEL_CONTACT_FRIEND = "model_contact_friend";
    public static final String MODEL_DELIVERY_ADDRESS = "model_delivery_address";
    public static final String MODEL_WEIXIN_PAY = "model_weixin_pay";
    public static final String MODEL_USERINFO = "model_userinfo";

    /**
     * 请求码,用于请求其他activity返回bundle信息
     */
    public static final int REQUEST_CODE_ONE = 1;
    public static final int REQUEST_CODE_TWO = REQUEST_CODE_ONE + 1;
    public static final int REQUEST_CODE_THREE = REQUEST_CODE_ONE + 2;

    /**
     * 转出记录查询时携带的tag信息
     * <p>
     * 转出平台
     * 转给朋友
     * 转入
     * 行权
     * 全部流水集合
     */
    public static final String KEY_TAG_TRANSACTION_RECORD = "key_tag_transaction_record";
    public static final String TAG_TRANSACTION_RECORD_PLATFORM = "transfer";
    public static final String TAG_TRANSACTION_RECORD_FRIEND = "transferFriend";
    public static final String TAG_TRANSACTION_RECORD_INCOME = "recharge";
    public static final String TAG_TRANSACTION_RECORD_EXERCISE = "exercise";
    public static final String TAG_TRANSACTION_RECORD_FLOW = "allList";

    /**
     * 转移数字资产,操作类型
     * <p>
     * 转账(通过平台)
     * 转账(直接转给朋友)
     */
    public static final int TYPE_TRANSFER_TO_PLATFORM = 0;
    public static final int TYPE_TRANSFER_TO_FRIEND = 1;

    /**
     * 商品类型
     * <p>
     * 1:预售 2:领养 3:行权 4:抢购
     */
    public static final String KEY_TYPE_PRODUCT = "key_type_product";
    public static final int TYPE_PRODUCT_PRE_ORDER = 1;
    public static final int TYPE_PRODUCT_ADOPT = 2;
    public static final int TYPE_PRODUCT_EXERCISE = 3;
    public static final int TYPE_PRODUCT_PANIC = 4;

    /**
     * 币种所属区链
     * <p>
     * ACC链
     * ETH链
     * BTC链
     */
    public static final String TYPE_CHAIN_ACC = "ACC";
    public static final String TYPE_CHAIN_ETH = "ETH";
    public static final String TYPE_CHAIN_BTC = "BTC";

    /**
     * 找回登录密码的方式
     * <p>
     * 通过手机
     * 通过邮箱
     */
    public static final String KEY_TYPE_FIND_LOGIN_PASSWORD = "key_type_find_login_password";
    public static final int TYPE_FIND_LOGIN_PASSWORD_BY_PHONE = 1;
    public static final int TYPE_FIND_LOGIN_PASSWORD_BY_EMAIL = 2;

    /**
     * 添加朋友时,通过何种方式添加
     * <p>
     * 联系人搜索
     * 个人名片
     * 名片分享
     * 手机联系人
     */
    public static final String KEY_TYPE_ADD_FRIEND_FROM = "key_type_add_friend_from";
    public static final String TYPE_ADD_FRIEND_FROM_PHONE_SEARCH = "type_add_friend_from_phone_search";
    public static final String TYPE_ADD_FRIEND_FROM_CARD = "type_add_friend_from_card";
    public static final String TYPE_ADD_FRIEND_FROM_SHARE = "type_add_friend_from_share";
    public static final String TYPE_ADD_FRIEND_FROM_PHONE = "type_add_friend_from_phone";

    /**
     * 身份认证方式:身份证0,军官证1,护照2,int类型,
     */
    public static final String KEY_ID_CARD_TYPE = "key_id_card_type";
    public static final int TYPE_ID_CARD_IDENTIFICATION = 0;
    public static final int TYPE_ID_CARD_POLICE = 1;
    public static final int TYPE_ID_CARD_FOREIGN = 2;


    /**
     * 单页数据量
     * <p>
     * 默认:20
     * 最大:100
     */
    public static final int CONST_PER_PAGE_SIZE_DEFAULT = 20;
    public static final int CONST_PER_PAGE_SIZE_BIGGEST = 100;
}
