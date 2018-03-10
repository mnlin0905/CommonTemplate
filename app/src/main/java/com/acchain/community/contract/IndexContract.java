package com.acchain.community.contract;

import com.acchain.community.bean.IndexDataBean;

/**
 * function---- IndexContract
 * <p>
 * Created(Gradle default create) by ACChain on 2017/12/20 10:48:47 (+0000).
 */
public interface IndexContract {
    interface Presenter{
        /*获取首页数据*/
        void getIndexData();
    }

     interface View{
        void onIndexDataFinish(IndexDataBean indexDataBean);
    }
}