package com.acchain.community.fragment;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseFragment;
import com.acchain.community.bean.AdoptGoodDetail;
import com.acchain.community.contract.AdoptGoodsDetailContract;
import com.acchain.community.presenter.AdoptGoodsDetailPresenter;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;

import butterknife.BindView;

/**
 * function---- AdoptGoodsDetailFragment
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/23 11:57:50 (+0000).
 */
@Route(path = ARouterConst.Fragment_AdoptGoodsDetailFragment)
public class AdoptGoodsDetailFragment extends BaseFragment<AdoptGoodsDetailPresenter> implements AdoptGoodsDetailContract.View {

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
    protected void initData(Bundle savedInstanceState) {
    }

    @Override
    protected void injectSelf() {
        fragmentComponent.inject(this);
    }

    public void refreshUi(AdoptGoodDetail adoptGoodDetail) {
        propertyName.setText(adoptGoodDetail.getPropertyName());
        propertyCurrency.setText(adoptGoodDetail.getPropertyCurrency());
        circulation.setText(adoptGoodDetail.getCirculation()+"");
        price.setText(adoptGoodDetail.getAdoptPrice()+"");
        productSpecification.setText(adoptGoodDetail.getProductSpecification());
        exerciseSpecification.setText(adoptGoodDetail.getExerciseSpecification());
        productBrand.setText(adoptGoodDetail.getProductBrand());
        productionPlace.setText(adoptGoodDetail.getProductionPlace());
        exerciseStartTime.setText(adoptGoodDetail.getExerciseStartTime());
        exerciseEndTime.setText(adoptGoodDetail.getExerciseEndTime());
        Glide.with(baseActivity).load(baseActivity.getString(R.string.test_base_image_address)+adoptGoodDetail.getProducts().getDetailDescApp());
    }

}