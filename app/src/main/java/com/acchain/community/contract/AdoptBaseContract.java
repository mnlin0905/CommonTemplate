package com.acchain.community.contract;

import com.acchain.community.bean.AdoptBaseCode;

/**
 * function---- AdoptBaseContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/19 07:23:18 (+0000).
 */
public interface AdoptBaseContract {
    interface Presenter {
        void chooseAdoptCode(int adoptId, int locationId, int pageIndex);
    }

    interface View {
        void onChooseAdoptCodeFinish(AdoptBaseCode adoptBaseCode);
    }
}