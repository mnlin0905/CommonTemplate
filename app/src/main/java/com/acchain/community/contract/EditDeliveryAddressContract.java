package com.acchain.community.contract;

import retrofit2.http.Field;

/**
 * function---- EditDeliveryAddressContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/16 11:23:45 (+0000).
 */
public interface EditDeliveryAddressContract {
    interface Presenter {
        /**
         * 1.1.7 新增收货地址
         *
         * @param token         登录标志
         * @param name          收货人姓名
         * @param mobile        手机号码
         * @param province      所在省份
         * @param city          所在城市
         * @param area          所在区
         * @param address       详细地址
         * @param defaultStatus 默认0
         */
        void addAddress(@Field("token") String token, String name, String mobile, String province, String city, String area, String address, int defaultStatus);
    }

    interface View {
        /**
         * 1.1.7 新增收货地址
         */
        void addAddressFinish();
    }
}