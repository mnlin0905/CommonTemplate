package com.acchain.community.contract;

/**
 * function---- UploadPictureTransPwdContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/31 11:46:08 (+0000).
 */
public interface UploadPictureTransPwdContract {
    interface Presenter{
        /**
         * 1.3.3 开始人工审核申请
         *
         * @param token    token
         * @param mobile   接受审核通知手机号码
         * @param positive 身份证件正面照
         * @param back     身份证件反面照
         * @param handheld 手持身份证件照
         * @param cardType 证件类型 1:身份证 2:军官证 3:护照
         */
        void resetPayPwdArtificial(String token, String mobile, String positive, String back, String handheld, int cardType);
    }

    interface View{
        /**
         * 1.3.3 开始人工审核申请
         */
        void resetPayPwdArtificialFinish();

    }
}