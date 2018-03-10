package com.acchain.community.contract;

import com.acchain.community.bean.ExerciseProduct;

/**
 * function---- ExerciseContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/16 07:41:57 (+0000).
 */
public interface ExerciseContract {
    interface Presenter{
        void getExerciseProducts(int pageIndex);

    }

     interface View{
        void onExerciseProductsFinish(ExerciseProduct exerciseProduct);
    }
}