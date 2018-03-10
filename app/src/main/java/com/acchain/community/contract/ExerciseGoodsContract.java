package com.acchain.community.contract;

import com.acchain.community.bean.ExerciseGoodsDetail;

/**
 * function---- ExerciseGoodsContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/16 08:31:29 (+0000).
 */
public interface ExerciseGoodsContract {
    interface Presenter {
        void getExerciseProductDetail(int exerciseId);

    }

    interface View {
        void onExerciseProductDetailFinish(ExerciseGoodsDetail exerciseGoodsDetail);
    }
}