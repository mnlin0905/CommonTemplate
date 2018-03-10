package com.acchain.community.arouter;

/**
 * 功能----路径跳转activity/fragment
 * <p>
 * Created by ACChain on 2017/11/24.
 */

public final class ARouterConst {
    /**
     * 无权限
     * 登录
     * 已开始了实名认证流程
     * 绑定手机
     * 绑定邮箱
     * 交易密码
     * 绿色通道(默认没有,即正常请求都需要进行过滤,如果有目标需要该权限,则表示通往该目标的请求都将被拦截(除非有特殊处理))
     * WIFI网络
     * 流量网络(MOBILE)
     * activity启动:清除任务栈
     * 是否设置了名片;若没有,则无法展示名片与二维码信息
     * 实名认证未成功
     */
    public static final int FLAG_NONE = 0x00000000;
    public static final int FLAG_LOGIN = 0x00000003;
    public static final int FLAG_VERIFY_HAS_BEGIN = 0x00000004;
    public static final int FLAG_PHONE = 0x00000008;
    public static final int FLAG_EMAIL = 0x00000010;
    public static final int FLAG_TRANSACTION_PASSWORD = 0x00000020;
    public static final int FLAG_FORCE_ACCESS = 0x00000040;
    public static final int FLAG_WIFI_NET = 0x00000080;
    public static final int FLAG_MOBILE_NET = 0x00000100;
    public static final int FLAG_ACTIVITY_CLEAR_TOP = 0x00000200;
    public static final int FLAG_BUSINESS_CARD = 0x00000400;
    public static final int FLAG_VERIFY_NOT_SUCCESS = 0x00001800;

    /**
     * activity/fragment
     */
    public static final String Activity_SelectFunctionActivity = "/activity/SelectFunctionActivity";
    public static final String Activity_MainActivity = "/activity/MainActivity";
    public static final String Fragment_FriendsFragment = "/fragment/FriendsFragment";
    public static final String Fragment_IndexFragment = "/fragment/IndexFragment";
    public static final String Fragment_PersonFragment = "/fragment/PersonFragment";
    public static final String Fragment_WalletFragment = "/fragment/WalletFragment";
    public static final String Fragment_ShoppingCartFragment = "/fragment/ShoppingCartFragment";
    public static final String Fragment_WalletPreOrderFragment = "/fragment/WalletPreOrderFragment";
    public static final String Fragment_WalletAdoptFragment = "/fragment/WalletAdoptFragment";
    public static final String Activity_WalletRecordActivity = "/activity/WalletRecordActivity";
    public static final String Activity_ChatActivity = "/activity/ChatActivity";
    public static final String Activity_PanicActivity = "/activity/PanicActivity";
    public static final String Activity_PanicGoodsActivity = "/activity/PanicGoodsActivity";
    public static final String Fragment_PanicGoodFragment = "/fragment/PanicGoodFragment";
    public static final String Fragment_PanicGoodDetailFragment = "/fragment/PanicGoodDetailFragment";
    public static final String Activity_LoginActivity = "/activity/LoginActivity";
    public static final String Activity_RegisterActivity = "/activity/RegisterActivity";
    public static final String Activity_TakeInCurrencyActivity = "/activity/TakeInCurrencyActivity";
    public static final String Activity_TakeInCurrencyDetailActivity = "/activity/TakeInCurrencyDetailActivity";
    public static final String Activity_TakeInCurrencyRecordActivity = "/activity/TakeInCurrencyRecordActivity";
    public static final String Activity_TakeInRecordDetailActivity = "/activity/TkeInRecordDetailActivity";
    public static final String Activity_ConfirmPanicOrderActivity = "/activity/ConfirmPanicOrderActivity";
    public static final String Activity_ConfirmCartPanicOrderActivity = "/activity/ConfirmCartPanicOrderActivity";
    public static final String Activity_BunkersCurrencyActivity = "/activity/BunkersCurrencyActivity";
    public static final String Activity_BunkersFlowRecordActivity = "/activity/BunkersFlowRecordActivity";
    public static final String Activity_BunkersConsumeDeclareActivity = "/activity/BunkersConsumeDeclareActivity";
    public static final String Activity_TakeOutCurrencyActivity = "/activity/TakeOutCurrencyActivity";
    public static final String Activity_TakeOutPlatformActivity = "/activity/TakeOutPlatformActivity";
    public static final String Activity_TakeOutFriendActivity = "/activity/TakeOutFriendActivity";
    public static final String Activity_ShoppingCartActivity = "/activity/ShoppingCartActivity";
    public static final String Fragment_CartPanicFragment = "/fragment/CartPanicFragment";
    public static final String Fragment_CartPreSellFragment = "/fragment/CartPreSellFragment";
    public static final String Fragment_CartAdoptFragment = "/fragment/CartAdoptFragment";
    public static final String Activity_TakeOutCurrencyRecordActivity = "/activity/TakeOutCurrencyRecordActivity";
    public static final String Activity_TakeOutRecordDetailActivity = "/activity/TakeOutRecordDetailActivity";
    public static final String Activity_TakeOutSelectFriendActivity = "/activity/TakeOutSelectFriendActivity";
    public static final String Activity_TakeFlowRecordActivity = "/activity/TakeFlowRecordActivity";
    public static final String Activity_PreSellActivity = "/activity/PreSellActivity";
    public static final String Activity_PreSellGoodsActivity = "/activity/PreSellGoodsActivity";
    public static final String Fragment_PreSellGoodsFragment = "/fragment/PreSellGoodFragment";
    public static final String Activity_ShareCircleActivity = "/activity/ShareCircleActivity";
    public static final String Activity_SoftSettingActivity = "/activity/SoftSettingActivity";
    public static final String Activity_PersonInformationActivity = "/activity/PersonInformationActivity";
    public static final String Activity_QRAndCommandActivity = "/activity/QRAndCommandActivity";
    public static final String Activity_ChangeDeliveryAddressActivity = "/activity/ChangeDeliveryAddressActivity";
    public static final String Activity_ChangeLocationActivity = "/activity/ChangeLocationActivity";
    public static final String Activity_ChangeNickNameActivity = "/activity/ChangeNickNameActivity";
    public static final String Activity_ChangeSexActivity = "/activity/ChangeSexActivity";
    public static final String Activity_PreSellNewsActivity = "/activity/PreSellNewsActivity";
    public static final String Activity_PreSellRecordActivity = "/activity/PreSellRecordActivity";
    public static final String Activity_ConfirmPreSellOrderActivity = "/activity/ConfirmPreSellOrderActivity";
    public static final String Activity_ConfirmCartPreOrderActivity = "/activity/ConfirmCartPreOrderActivity";
    public static final String Activity_WXPayEntryActivity = "/activity/WXPayEntryActivity";
    public static final String Activity_AdoptActivity = "/activity/AdoptActivity";
    public static final String Activity_ContactActivity = "/activity/ContactActivity";
    public static final String Activity_AdoptGoodsActivity = "/activity/AdoptGoodsActivity";
    public static final String Fragment_AdoptGoodsFragment = "/fragment/AdoptGoodFragment";
    public static final String Fragment_AdoptGoodsDetailFragment = "/fragment/AdoptGoodsDetailFragment";
    public static final String Activity_ConfirmAdoptOrderActivity = "/activity/ConfirmAdoptOrderActivity";
    public static final String Activity_ConfirmCartAdoptOrderActivity = "/activity/ConfirmCartAdoptOrderActivity";
    public static final String Fragment_ContactListFragment = "/fragment/ContactListFragment";
    public static final String Activity_ExerciseActivity = "/activity/ExerciseActivity";
    public static final String Activity_ExerciseGoodsActivity = "/activity/ExerciseGoodsActivity";
    public static final String Fragment_ExerciseGoodsFragment = "/fragment/ExerciseGoodFragment";
    public static final String Fragment_ExerciseDetailFragment = "/fragment/ExerciseDetailFragment";
    public static final String Activity_ConfirmExerciseOrderActivity = "/activity/ConfirmExerciseOrderActivity";
    public static final String Activity_EditDeliveryAddressActivity = "/activity/EditDeliveryAddressActivity";
    public static final String Activity_AddNewFriendActivity = "/activity/AddNewFriendActivity";
    public static final String Activity_NewFriendActivity = "/activity/NewFriendActivity";
    public static final String Activity_BeginCertificationActivity = "/activity/BeginCertificationActivity";
    public static final String Activity_UploadCertificationPhotoActivity = "/activity/UploadCertificationPhotoActivity";
    public static final String Activity_ShowCertificationResultActivity = "/activity/ShowCertificationResultActivity";
    public static final String Activity_EditProfessionCardActivity = "/activity/EditProfessionCardActivity";
    public static final String Activity_CompanyIntroduceActivity = "/activity/CompanyIntroduceActivity";
    public static final String Fragment_PreSellDetailFragment = "/fragment/PreSellDetailFragment";
    public static final String Activity_AdoptBaseActivity = "/activity/AdoptBaseActivity";
    public static final String Activity_ChooseBaseActivity = "/activity/ChooseBaseActivity";
    public static final String Activity_CardListActivity = "/activity/CardListActivity";
    public static final String Activity_CardDetailActivity = "/activity/CardDetailActivity";
    public static final String Activity_ForgetPasswordActivity = "/activity/ForgetPasswordActivity";
    public static final String Activity_FindPasswordActivity = "/activity/FindPasswordActivity";
    public static final String Activity_ResetPasswordActivity = "/activity/ResetPasswordActivity";
    public static final String Activity_MessageSettingsActivity = "/activity/MessageSettingsActivity";
    public static final String Activity_SafetyProtectionActivity = "/activity/SafetyProtectionActivity";
    public static final String Activity_ChangePasswordActivity = "/activity/ChangePasswordActivity";
    public static final String Activity_SelectChangeTransPasswordTypeActivity = "/activity/SelectChangeTransPasswordTypeActivity";
    public static final String Activity_SetTransactionPasswordActivity = "/activity/SetTransactionPasswordActivity";
    public static final String Activity_RedPacketDetailActivity = "/activity/RedPacketDetailActivity";
    public static final String Activity_BindEmailActivity = "/activity/BindEmailActivity";
    public static final String Activity_BindMobileActivity = "/activity/BindMobileActivity";
    public static final String Activity_ChangeEmailActivity = "/activity/ChangeEmailActivity";
    public static final String Activity_ChangeMobileActivity = "/activity/ChangeMobileActivity";
    public static final String Activity_ShowEmailActivity = "/activity/ShowEmailActivity";
    public static final String Activity_ShowMobileActivity = "/activity/ShowMobileActivity";
    public static final String Activity_HelpActivity = "/activity/HelpActivity";
    public static final String Activity_FeedbackActivity = "/activity/FeedbackActivity";
    public static final String Activity_QuestionActivity = "/activity/QuestionActivity";
    public static final String Activity_QuestionListActivity = "/activity/QuestionListActivity";
    public static final String Activity_AboutActivity = "/activity/AboutActivity";
    public static final String Activity_CopyrightInformationActivity = "/activity/CopyrightInformationActivity";
    public static final String Activity_ServiceAgreementActivity = "/activity/ServiceAgreementActivity";
    public static final String Activity_PrivacyActivity = "/activity/PrivacyActivity";
    public static final String Activity_PrivacyDeclareActivity = "/activity/PrivacyDeclareActivity";
    public static final String Activity_NewsActivity = "/activity/NewsActivity";
    public static final String Activity_NewsListActivity = "/activity/NewsListActivity";
    public static final String Activity_GoodsCollectionActivity = "/activity/GoodsCollectionActivity";
    public static final String Fragment_GoodsCollectionFootFragment = "/fragment/GoodsCollectionFootFragment";
    public static final String Activity_FootHistoryActivity = "/activity/FootHistoryActivity";
    public static final String Activity_TransactionRecordActivity = "/activity/TransactionRecordActivity";
    public static final String Fragment_TransactionRecordFragment = "/activity/TransactionRecordFragment";
    public static final String Activity_TransactionDetailActivity = "/activity/TransactionDetailActivity";
    public static final String Activity_FriendAddActivity = "/activity/FriendAddActivity";
    public static final String Activity_SetFriendRemarkActivity = "/activity/SetFriendRemarkActivity";
    public static final String Activity_SendAddFriendRequestActivity = "/activity/SendAddFriendRequestActivity";
    public static final String Activity_AddPhoneContactActivity = "/activity/AddPhoneContactActivity";
    public static final String Activity_ChatDetailActivity = "/activity/ChatDetailActivity";
    public static final String Activity_OrderFormRecordActivity = "/activity/OrderFormRecordActivity";
    public static final String Fragment_OrderFormRecordFragment = "/activity/OrderFormRecordFragment";
    public static final String Activity_OrderFormDetailActivity = "/activity/OrderFormDetailActivity";
    public static final String Activity_ApplyForRefundActivity = "/activity/ApplyForRefundActivity";
    public static final String Activity_SelectRefundServiceActivity = "/activity/SelectRefundServiceActivity";
    public static final String Activity_BanCommandActivity = "/activity/BanCommandActivity";
    public static final String Activity_SendRedPacketActivity = "/activity/SendRedPacketActivity";
    public static final String Activity_SendRedPacketDetailActivity="/activity/SendRedPacketDetailActivity";
    public static final String Activity_RedPacketRecordActivity="/activity/RedPacketRecordActivity";
    public static final String Activity_InputOldTransPasswordActivity="/activity/InputOldTransPasswordActivity";
    public static final String Activity_ChangeTransPasswordActivity="/activity/ChangeTransPasswordActivity";
    public static final String Activity_VerityIDWhenChangeTransActivity="/activity/VerityIDWhenChangeTransActivity";
    public static final String Activity_StaffServiceChangeTransActivity="/activity/StaffServiceChangeTransActivity";
    public static final String Activity_InputPhoneTransPasswordActivity="/activity/InputPhoneTransPasswordActivity";
    public static final String Activity_UploadPictureTransPwdActivity="/activity/UploadPictureTransPwdActivity";
    public static final String Activity_ChangeTransResultActivity="/activity/ChangeTransResultActivity";
    public static final String Activity_CheckLogisticsActivity="/activity/CheckLogisticsActivity";
    public static final String Activity_ChangeTransSuccessActivity="/activity/ChangeTransSuccessActivity";
    public static final String Activity_TransferDetailActivity="/activity/TransferDetailActivity";
    public static final String Activity_RefundAftermarketActivity="/activity/RefundAftermarketActivity";
    public static final String Activity_RefundAftermarketDetailActivity="/activity/RefundAftermarketDetailActivity";
    public static final String Activity_NegotiationHistoryActivity="/activity/NegotiationHistoryActivity";
    public static final String Activity_BeginReturnLogisticsActivity="/activity/BeginReturnLogisticsActivity";
    public static final String Activity_EvaluationOrderActivity="/activity/EvaluationOrderActivity";
    public static final String Activity_EvaluationOrderSuccessActivity="/activity/EvaluationOrderSuccessActivity";
    public static final String Activity_SearchFilterActivity="/activity/SearchFilterActivity";
}
