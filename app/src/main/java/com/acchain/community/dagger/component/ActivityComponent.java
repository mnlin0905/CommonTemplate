package com.acchain.community.dagger.component;

import com.acchain.community.activity.index.ConfirmAdoptOrderActivity;
import com.acchain.community.activity.index.ConfirmCartAdoptOrderActivity;
import com.acchain.community.activity.friend.AddNewFriendActivity;
import com.acchain.community.activity.friend.AddPhoneContactActivity;
import com.acchain.community.activity.friend.CardDetailActivity;
import com.acchain.community.activity.friend.CardListActivity;
import com.acchain.community.activity.friend.ChatActivity;
import com.acchain.community.activity.friend.ChatDetailActivity;
import com.acchain.community.activity.friend.ContactActivity;
import com.acchain.community.activity.friend.FriendAddActivity;
import com.acchain.community.activity.friend.NewFriendActivity;
import com.acchain.community.activity.friend.RedPacketDetailActivity;
import com.acchain.community.activity.friend.RedPacketRecordActivity;
import com.acchain.community.activity.friend.SendAddFriendRequestActivity;
import com.acchain.community.activity.friend.SendRedPacketActivity;
import com.acchain.community.activity.friend.SendRedPacketDetailActivity;
import com.acchain.community.activity.friend.SetFriendRemarkActivity;
import com.acchain.community.activity.friend.ShareCircleActivity;
import com.acchain.community.activity.friend.TransferDetailActivity;
import com.acchain.community.activity.index.AdoptActivity;
import com.acchain.community.activity.index.AdoptBaseActivity;
import com.acchain.community.activity.index.AdoptGoodsActivity;
import com.acchain.community.activity.index.ChooseBaseActivity;
import com.acchain.community.activity.index.ConfirmCartPanicOrderActivity;
import com.acchain.community.activity.index.ConfirmCartPreOrderActivity;
import com.acchain.community.activity.index.ConfirmExerciseOrderActivity;
import com.acchain.community.activity.index.ConfirmPanicOrderActivity;
import com.acchain.community.activity.index.ConfirmPreSellOrderActivity;
import com.acchain.community.activity.index.ExerciseActivity;
import com.acchain.community.activity.index.ExerciseGoodsActivity;
import com.acchain.community.activity.index.PanicActivity;
import com.acchain.community.activity.index.PanicGoodsActivity;
import com.acchain.community.activity.index.PreSellActivity;
import com.acchain.community.activity.index.PreSellGoodsActivity;
import com.acchain.community.activity.index.PreSellNewsActivity;
import com.acchain.community.activity.index.PreSellRecordActivity;
import com.acchain.community.activity.index.ShoppingCartActivity;
import com.acchain.community.activity.other.MainActivity;
import com.acchain.community.activity.other.SelectFunctionActivity;
import com.acchain.community.activity.person.AboutActivity;
import com.acchain.community.activity.person.ApplyForRefundActivity;
import com.acchain.community.activity.person.BanCommandActivity;
import com.acchain.community.activity.person.BeginCertificationActivity;
import com.acchain.community.activity.person.BeginReturnLogisticsActivity;
import com.acchain.community.activity.person.BindEmailActivity;
import com.acchain.community.activity.person.BindMobileActivity;
import com.acchain.community.activity.person.ChangeDeliveryAddressActivity;
import com.acchain.community.activity.person.ChangeEmailActivity;
import com.acchain.community.activity.person.ChangeLocationActivity;
import com.acchain.community.activity.person.ChangeMobileActivity;
import com.acchain.community.activity.person.ChangeNickNameActivity;
import com.acchain.community.activity.person.ChangePasswordActivity;
import com.acchain.community.activity.person.ChangeSexActivity;
import com.acchain.community.activity.person.ChangeTransPasswordActivity;
import com.acchain.community.activity.person.ChangeTransResultActivity;
import com.acchain.community.activity.person.CheckLogisticsActivity;
import com.acchain.community.activity.person.CopyrightInformationActivity;
import com.acchain.community.activity.person.EditDeliveryAddressActivity;
import com.acchain.community.activity.person.EditProfessionCardActivity;
import com.acchain.community.activity.person.EvaluationOrderActivity;
import com.acchain.community.activity.person.EvaluationOrderSuccessActivity;
import com.acchain.community.activity.person.FeedbackActivity;
import com.acchain.community.activity.person.FindPasswordActivity;
import com.acchain.community.activity.person.ForgetPasswordActivity;
import com.acchain.community.activity.person.HelpActivity;
import com.acchain.community.activity.person.InputOldTransPasswordActivity;
import com.acchain.community.activity.person.InputPhoneTransPasswordActivity;
import com.acchain.community.activity.person.LoginActivity;
import com.acchain.community.activity.person.MessageSettingsActivity;
import com.acchain.community.activity.person.NegotiationHistoryActivity;
import com.acchain.community.activity.person.NewsActivity;
import com.acchain.community.activity.person.NewsListActivity;
import com.acchain.community.activity.person.OrderFormDetailActivity;
import com.acchain.community.activity.person.PersonInformationActivity;
import com.acchain.community.activity.person.PrivacyActivity;
import com.acchain.community.activity.person.PrivacyDeclareActivity;
import com.acchain.community.activity.person.QRAndCommandActivity;
import com.acchain.community.activity.person.QuestionActivity;
import com.acchain.community.activity.person.QuestionListActivity;
import com.acchain.community.activity.person.RefundAftermarketActivity;
import com.acchain.community.activity.person.RefundAftermarketDetailActivity;
import com.acchain.community.activity.person.RegisterActivity;
import com.acchain.community.activity.person.ResetPasswordActivity;
import com.acchain.community.activity.person.SafetyProtectionActivity;
import com.acchain.community.activity.person.SelectChangeTransPasswordTypeActivity;
import com.acchain.community.activity.person.SelectRefundServiceActivity;
import com.acchain.community.activity.person.ServiceAgreementActivity;
import com.acchain.community.activity.person.SetTransactionPasswordActivity;
import com.acchain.community.activity.person.ShowCertificationResultActivity;
import com.acchain.community.activity.person.ShowEmailActivity;
import com.acchain.community.activity.person.ShowMobileActivity;
import com.acchain.community.activity.person.SoftSettingActivity;
import com.acchain.community.activity.person.StaffServiceChangeTransActivity;
import com.acchain.community.activity.person.TransactionDetailActivity;
import com.acchain.community.activity.person.UploadCertificationPhotoActivity;
import com.acchain.community.activity.person.UploadPictureTransPwdActivity;
import com.acchain.community.activity.person.VerityIDWhenChangeTransActivity;
import com.acchain.community.activity.wallet.BunkersConsumeDeclareActivity;
import com.acchain.community.activity.wallet.BunkersCurrencyActivity;
import com.acchain.community.activity.wallet.BunkersFlowRecordActivity;
import com.acchain.community.activity.wallet.TakeFlowRecordActivity;
import com.acchain.community.activity.wallet.TakeInCurrencyActivity;
import com.acchain.community.activity.wallet.TakeInCurrencyDetailActivity;
import com.acchain.community.activity.wallet.TakeInCurrencyRecordActivity;
import com.acchain.community.activity.wallet.TakeInRecordDetailActivity;
import com.acchain.community.activity.wallet.TakeOutCurrencyActivity;
import com.acchain.community.activity.wallet.TakeOutCurrencyRecordActivity;
import com.acchain.community.activity.wallet.TakeOutFriendActivity;
import com.acchain.community.activity.wallet.TakeOutPlatformActivity;
import com.acchain.community.activity.wallet.TakeOutRecordDetailActivity;
import com.acchain.community.activity.wallet.TakeOutSelectFriendActivity;
import com.acchain.community.activity.wallet.WalletRecordActivity;
import com.acchain.community.dagger.module.ActivityModule;
import com.acchain.community.dagger.scope.PerActivity;
import com.acchain.community.wxapi.WXPayEntryActivity;

import dagger.Component;

/**
 * 功能----activity组件,提供清单文件
 * <p>
 * Created by ACChain on 2017/9/22.
 */
@PerActivity
@Component(modules = ActivityModule.class,dependencies = ApplicationComponent.class)
public interface ActivityComponent {
    void inject(SelectFunctionActivity activity);

    void inject(MainActivity mainActivity);

    void inject(WalletRecordActivity walletRecordActivity);

    void inject(ChatActivity chatActivity);

    void inject(PanicActivity panicActivity);

    void inject(PanicGoodsActivity panicGoodsActivity);

    void inject(LoginActivity loginActivity);

    void inject(RegisterActivity registerActivity);

    void inject(ConfirmPanicOrderActivity confirmPanicOrderActivity);

    void inject(ConfirmCartPanicOrderActivity confirmCartPanicOrderActivity);

    void inject(TakeInCurrencyActivity takeInCurrencyActivity);

    void inject(TakeInCurrencyDetailActivity takeInCurrencyDetailActivity);

    void inject(TakeInCurrencyRecordActivity takeInCurrencyRecordActivity);

    void inject(TakeInRecordDetailActivity takeInRecordDetailActivity);

    void inject(BunkersCurrencyActivity bunkersCurrencyActivity);

    void inject(BunkersFlowRecordActivity bunkersFlowRecordActivity);

    void inject(BunkersConsumeDeclareActivity bunkersConsumeDeclareActivity);

    void inject(TakeOutCurrencyActivity takeOutCurrencyActivity);

    void inject(TakeOutPlatformActivity takeOutPlatformActivity);

    void inject(TakeOutFriendActivity takeOutFriendActivity);

    void inject(ShoppingCartActivity shoppingCartActivity);

    void inject(TakeOutCurrencyRecordActivity takeOutCurrencyRecordActivity);

    void inject(TakeOutRecordDetailActivity takeOutRecordDetailActivity);

    void inject(PreSellActivity preSellActivity);

    void inject(PreSellGoodsActivity preSellGoodsActivity);

    void inject(ShareCircleActivity shareCircleActivity);

    void inject(TakeOutSelectFriendActivity takeOutSelectFriendActivity);

    void inject(PreSellNewsActivity preSellNewsActivity);

    void inject(PreSellRecordActivity preSellRecordActivity);

    void inject(ConfirmPreSellOrderActivity confirmPreSellOrderActivity);

    void inject(ConfirmCartPreOrderActivity confirmCartPreOrderActivity);

    void inject(WXPayEntryActivity wxPayEntryActivity);

    void inject(AdoptActivity adoptActivity);

    void inject(ContactActivity contactActivity);

    void inject(TakeFlowRecordActivity takeFlowRecordActivity);

    void inject(SoftSettingActivity softSettingActivity);

    void inject(PersonInformationActivity personInformationActivity);

    void inject(QRAndCommandActivity qrAndCommandActivity);

    void inject(ChangeDeliveryAddressActivity changeDeliveryAddressActivity);

    void inject(ChangeLocationActivity changeLocationActivity);

    void inject(ChangeNickNameActivity changeNickNameActivity);

    void inject(ChangeSexActivity changeSexActivity);

    void inject(AdoptGoodsActivity adoptGoodsActivity);

    void inject(ConfirmAdoptOrderActivity confirmAdoptOrderActivity);

    void inject(ConfirmCartAdoptOrderActivity confirmCartAdoptOrderActivity);

    void inject(ExerciseActivity exerciseActivity);

    void inject(ExerciseGoodsActivity exerciseGoodsActivity);

    void inject(ConfirmExerciseOrderActivity confirmExerciseOrderActivity);

    void inject(EditDeliveryAddressActivity editDeliveryAddressActivity);

    void inject(AddNewFriendActivity addNewFriendActivity);

    void inject(NewFriendActivity newFriendActivity);

    void inject(BeginCertificationActivity beginCertificationActivity);

    void inject(UploadCertificationPhotoActivity uploadCertificationPhotoActivity);

    void inject(ShowCertificationResultActivity showCertificationResultActivity);

    void inject(EditProfessionCardActivity editProfessionCardActivity);

    void inject(AdoptBaseActivity adoptBaseActivity);

    void inject(ChooseBaseActivity chooseBaseActivity);

    void inject(CardListActivity cardListActivity);

    void inject(CardDetailActivity cardDetailActivity);

    void inject(ForgetPasswordActivity forgetPasswordActivity);

    void inject(FindPasswordActivity findPasswordActivity);

    void inject(ResetPasswordActivity resetPasswordActivity);

    void inject(MessageSettingsActivity messageSettingsActivity);

    void inject(SafetyProtectionActivity safetyProtectionActivity);

    void inject(ChangePasswordActivity changePasswordActivity);

    void inject(SetTransactionPasswordActivity setTransactionPasswordActivity);

    void inject(RedPacketDetailActivity redPacketDetailActivity);

    void inject(BindEmailActivity bindEmailActivity);

    void inject(BindMobileActivity bindMobileActivity);

    void inject(ChangeEmailActivity changeEmailActivity);

    void inject(ChangeMobileActivity changeMobileActivity);

    void inject(ShowEmailActivity showEmailActivity);

    void inject(ShowMobileActivity showMobileActivity);

    void inject(HelpActivity helpActivity);

    void inject(FeedbackActivity feedbackActivity);

    void inject(QuestionActivity questionActivity);

    void inject(QuestionListActivity questionListActivity);

    void inject(AboutActivity aboutActivity);

    void inject(CopyrightInformationActivity copyrightInformationActivity);

    void inject(ServiceAgreementActivity serviceAgreementActivity);

    void inject(PrivacyActivity privacyActivity);

    void inject(PrivacyDeclareActivity pravicyDeclareActivity);

    void inject(NewsActivity newsActivity);

    void inject(FriendAddActivity friendAddActivity);

    void inject(SetFriendRemarkActivity setFriendRemarkActivity);

    void inject(SendAddFriendRequestActivity sendAddFriendRequestActivity);

    void inject(AddPhoneContactActivity addPhoneContactActivity);

    void inject(ChatDetailActivity chatDetailActivity);

    void inject(NewsListActivity newsListActivity);

    void inject(TransactionDetailActivity transactionDetailActivity);

    void inject(OrderFormDetailActivity orderFormDetailActivity);

    void inject(ApplyForRefundActivity applyForRefundActivity);

    void inject(SelectRefundServiceActivity selectRefundServiceActivity);

    void inject(SendRedPacketActivity sendRedPacketActivity);

    void inject(BanCommandActivity banCommandActivity);

    void inject(SendRedPacketDetailActivity sendRedPacketDetailActivity);

    void inject(RedPacketRecordActivity redPacketRecordActivity);

    void inject(InputOldTransPasswordActivity inputOldTransPasswordActivity);

    void inject(ChangeTransPasswordActivity changeTransPasswordActivity);

    void inject(VerityIDWhenChangeTransActivity verityIDWhenChangeTransActivity);

    void inject(SelectChangeTransPasswordTypeActivity selectChangeTransPasswordTypeActivity);

    void inject(StaffServiceChangeTransActivity staffServiceChangeTransActivity);

    void inject(InputPhoneTransPasswordActivity inputPhoneTransPasswordActivity);

    void inject(UploadPictureTransPwdActivity uploadPictureTransPwdActivity);

    void inject(ChangeTransResultActivity changeTransResultActivity);

    void inject(CheckLogisticsActivity checkLogisticsActivity);

    void inject(RefundAftermarketActivity refundAftermarketActivity);

    void inject(RefundAftermarketDetailActivity refundAftermarketDetailActivity);

    void inject(NegotiationHistoryActivity negotiationHistoryActivity);

    void inject(BeginReturnLogisticsActivity beginReturnLogisticsActivity);

    void inject(TransferDetailActivity transferDetailActivity);

    void inject(EvaluationOrderActivity evaluationOrderActivity);

    void inject(EvaluationOrderSuccessActivity evaluationOrderSuccessActivity);
}
