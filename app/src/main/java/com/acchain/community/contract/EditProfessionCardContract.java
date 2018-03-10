package com.acchain.community.contract;

/**
 * function---- EditProfessionCardContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/17 10:02:42 (+0000).
 */
public interface EditProfessionCardContract {
    interface Presenter{
        /**
         * 1.2.4 设置名片信息
         *
         * @param token        登录标志登录标志
         * @param position     职位
         * @param companyName  公司名称
         * @param mainBusiness 主营业务
         * @param phone        电话
         * @param address      公司地址
         * @param introduction 企业简介
         */
        void setBusinessCard(String token, String position, String companyName,String mainBusiness, String phone,String email,String address, String area,String introduction);
    }

     interface View{
         /**
          * 1.2.4 设置名片信息
          */
         void setBusinessCardFinish();
    }
}