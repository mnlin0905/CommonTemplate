package com.acchain.community.contract;

import com.acchain.community.bean.AdoptBaseLocation;

import java.util.List;

/**
 * function---- ChooseBaseContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/02/02 05:50:28 (+0000).
 */
public interface ChooseBaseContract {
    interface Presenter{
        void findAdoptLocation(int adoptId);

    }

    interface View{
        void onFindAdoptLocationFinish(List<AdoptBaseLocation> baseList);
    }
}