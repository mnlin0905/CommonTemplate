package com.acchain.community.contract;

import com.acchain.community.bean.ExercisePay;

/**
 * function---- ConfirmExerciseOrderContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/16 09:05:47 (+0000).
 */
public interface ConfirmExerciseOrderContract {
    interface Presenter{
        void commitExerciseOrder(int productType, String token, int itemCount, int productSubId, int productId, String productAttrValueIds, int addressId);
    }

     interface View{
         void onCommitExerciseOrderFinish(ExercisePay exercisePay);
    }
}