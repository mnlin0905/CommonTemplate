package  com.acchain.community.fragment;

import com.acchain.community.R;
import com.acchain.community.bean.ExerciseGoodsDetail;
import com.acchain.community.presenter.ExerciseDetailPresenter;
import  com.acchain.community.contract.ExerciseDetailContract;
import  com.acchain.community.base.BaseFragment;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import  com.acchain.community.arouter.ARouterConst;
import com.bumptech.glide.Glide;

import butterknife.BindView;

/**
 * function---- ExerciseDetailFragment
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/30 11:39:29 (+0000).
 */
@Route(path = ARouterConst.Fragment_ExerciseDetailFragment)
public class ExerciseDetailFragment extends BaseFragment<ExerciseDetailPresenter> implements ExerciseDetailContract.View{
    @BindView(R.id.property_name)
    TextView propertyName;
    @BindView(R.id.property_currency)
    TextView propertyCurrency;
    @BindView(R.id.circulation)
    TextView circulation;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.product_specification)
    TextView productSpecification;
    @BindView(R.id.exercise_specification)
    TextView exerciseSpecification;
    @BindView(R.id.product_brand)
    TextView productBrand;
    @BindView(R.id.production_place)
    TextView productionPlace;
    @BindView(R.id.exercise_start_time)
    TextView exerciseStartTime;
    @BindView(R.id.exercise_end_time)
    TextView exerciseEndTime;
    @BindView(R.id.image)
    ImageView image;
    @Override
    protected int getContentViewId() {
        return R.layout.fragment_pre_sell_detail;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {}

    @Override
    protected void injectSelf() {
        fragmentComponent.inject(this);
    }

    public void refreshUi(ExerciseGoodsDetail exerciseGoodsDetail) {
        propertyName.setText(exerciseGoodsDetail.getPropertyName());
        propertyCurrency.setText(exerciseGoodsDetail.getPropertyCurrency());
        circulation.setText(exerciseGoodsDetail.getCirculation()+"");
        price.setText(exerciseGoodsDetail.getExercisePrice()+"");
        productSpecification.setText(exerciseGoodsDetail.getProductSpecification());
        exerciseSpecification.setText(exerciseGoodsDetail.getExerciseSpecification());
        productBrand.setText(exerciseGoodsDetail.getProductBrand());
        productionPlace.setText(exerciseGoodsDetail.getProductionPlace());
        exerciseStartTime.setText(exerciseGoodsDetail.getExerciseStartTime());
        exerciseEndTime.setText(exerciseGoodsDetail.getExerciseEndTime());
        Glide.with(baseActivity).load(baseActivity.getString(R.string.test_base_image_address)+exerciseGoodsDetail.getProducts().get(0).getProductImg());
    }
}