package  com.acchain.community.fragment;

import com.acchain.community.R;
import com.acchain.community.bean.PreSellGoodsDetail;
import com.acchain.community.presenter.PreSellDetailPresenter;
import  com.acchain.community.contract.PreSellDetailContract;
import  com.acchain.community.base.BaseFragment;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import  com.acchain.community.arouter.ARouterConst;
import com.bumptech.glide.Glide;

import butterknife.BindView;

/**
 * function---- PreSellDetailFragment
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/19 06:11:40 (+0000).
 */
@Route(path = ARouterConst.Fragment_PreSellDetailFragment)
public class PreSellDetailFragment extends BaseFragment<PreSellDetailPresenter> implements PreSellDetailContract.View{
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

    public void refreshUi(PreSellGoodsDetail preSellGoodsDetail) {
        propertyName.setText(preSellGoodsDetail.getPropertyName());
        propertyCurrency.setText(preSellGoodsDetail.getPropertyCurrency());
        circulation.setText(preSellGoodsDetail.getCirculation()+"");
        price.setText((int) preSellGoodsDetail.getPresellPrice()+"");
        productSpecification.setText(preSellGoodsDetail.getProductSpecification());
        exerciseSpecification.setText(preSellGoodsDetail.getExerciseSpecification());
        productBrand.setText(preSellGoodsDetail.getProductBrand());
        productionPlace.setText(preSellGoodsDetail.getProductionPlace());
        exerciseStartTime.setText(preSellGoodsDetail.getExerciseStartTime());
        exerciseEndTime.setText(preSellGoodsDetail.getExerciseEndTime());
        Glide.with(baseActivity).load(baseActivity.getString(R.string.test_base_image_address)+preSellGoodsDetail.getProducts().get(0).getProductImg());
    }
}