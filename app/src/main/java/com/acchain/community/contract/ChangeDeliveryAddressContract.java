package com.acchain.community.contract;

import com.acchain.community.bean.DeliveryAddressBean;

import java.util.List;

/**
 * function---- ChangeDeliveryAddressContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/15 12:42:34 (+0000).
 */
public interface ChangeDeliveryAddressContract {
    interface Presenter{
        /**
         * 1.1.8 获取用户收货地址列表
         *
         * @param token    登录标志
         * @param page     请求页码
         * @param pageSize 数据量
         */
        void getUserAddress(String token,int page, int pageSize);


    }

     interface View{
         /**
          * 1.1.8 获取用户收货地址列表
          *
          * 地址列表
          */
         void getUserAddressFinish(List<DeliveryAddressBean> deliveryAddressBeans);
    }
}