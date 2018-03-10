package com.acchain.community.contract;

import java.io.File;

/**
 * function---- PersonInformationContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/15 07:20:03 (+0000).
 */
public interface PersonInformationContract {
    interface Presenter {
        /**
         * 1.1.0 设置头像
         *
         * @param imgFile 头像文件(需要添加token成混合流(token,img))
         * @return 账户返回对象
         */
        void setPhoto(File imgFile);
    }

    interface View {
        /**
         * 1.1.0 设置头像
         */
        void setPhotoFinish();
    }
}