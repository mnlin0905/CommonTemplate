package com.acchain.community.fragment;

import android.os.Bundle;
import android.widget.ImageView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseFragment;
import com.acchain.community.bean.PanicGoodDetail;
import com.acchain.community.contract.PanicGoodDetailContract;
import com.acchain.community.presenter.PanicGoodDetailPresenter;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import butterknife.BindView;

/**
 * function---- PanicGoodDetailFragment
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/22 12:59:51 (+0000).
 */
@Route(path = ARouterConst.Fragment_PanicGoodDetailFragment)
public class PanicGoodDetailFragment extends BaseFragment<PanicGoodDetailPresenter> implements PanicGoodDetailContract.View {

    @BindView(R.id.image)
    ImageView image;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_panic_detail;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
    }

    @Override
    protected void injectSelf() {
        fragmentComponent.inject(this);
    }

    public void refreshUi(PanicGoodDetail panicGoodDetail) {
//        Glide.with(baseActivity).load(baseActivity.getString(R.string.image_address)+panicGoodDetail.getDetailDescApp()).into(image);
        Glide.with(baseActivity).load(baseActivity.getString(R.string.test_base_image_address)+"group1/M00/00/02/wKgB_Vpi4tuAd3IyAAFTSLCgDUw599.png").into(image);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}