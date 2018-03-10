package com.acchain.community.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseApplication;
import com.acchain.community.base.BaseFragment;
import com.acchain.community.bean.ConfirmExerciseOrder;
import com.acchain.community.bean.ExerciseGoodsDetail;
import com.acchain.community.contract.ExerciseGoodContract;
import com.acchain.community.presenter.ExerciseGoodPresenter;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.window.dialog.ChooseGoodsAttrsDialog;
import com.acchain.community.window.dialog.CommonGoodsAttrsBean;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * function---- ExerciseGoodFragment
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/16 08:31:29 (+0000).
 */
@Route(path = ARouterConst.Fragment_ExerciseGoodsFragment)
public class ExerciseGoodFragment extends BaseFragment<ExerciseGoodPresenter> implements ExerciseGoodContract.View {
    @BindView(R.id.tv_choose_goods)
    TextView tvChooseGoods;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.describe)
    TextView describe;
    @BindView(R.id.specification)
    TextView specification;
    @BindView(R.id.property)
    TextView property;
    @BindView(R.id.soldCount)
    TextView soldCount;
    @BindView(R.id.end_time)
    TextView endTime;
    @BindView(R.id.isExemption)
    TextView isExemption;
    @BindView(R.id.isRefund)
    TextView isRefund;
    @BindView(R.id.isDeliver)
    TextView isDeliver;
    @BindView(R.id.tv_attrs)
    TextView tvAttrs;
    private ExerciseGoodsDetail exerciseGoodsDetail;
    private ChooseGoodsAttrsDialog chooseGoodsAttrsDialog;
    private int[] productAttrValueIds;
    private ExerciseGoodsDetail.ProductsBean product;
    public int itemCount = 1;//商品购买数量，默认为1

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_exercise_goods;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
    }

    @Override
    protected void injectSelf() {
        fragmentComponent.inject(this);
    }

    @OnClick({R.id.tv_choose_goods})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_choose_goods:
                if (exerciseGoodsDetail == null) {
                    //数据还没请求到，不弹窗
                    return;
                }
                if (chooseGoodsAttrsDialog == null) {
                    initDialog();
                }
                chooseGoodsAttrsDialog.show();
                break;
        }
    }

    public void refreshUi(ExerciseGoodsDetail exerciseGoodsDetail) {
        this.exerciseGoodsDetail = exerciseGoodsDetail;
        product = exerciseGoodsDetail.getProducts().get(0);
        Glide.with(baseActivity).load(BaseApplication.getApplication().getBaseImageUrl() + product.getProductImg()).into(image);
        name.setText(product.getProductName());
        describe.setText(product.getShortDesc());
        specification.setText(exerciseGoodsDetail.getExerciseSpecification());
        specification.setTypeface(Typeface.DEFAULT_BOLD);
        property.setText(product.getProductQty() + "");
        soldCount.setText(product.getSoldCount() + "");
        endTime.setText(exerciseGoodsDetail.getExerciseEndTime());
        isExemption.setVisibility(exerciseGoodsDetail.getIsExemption() == 0 ? View.VISIBLE : View.GONE);
        isRefund.setVisibility(exerciseGoodsDetail.getIsRefund() == 0 ? View.VISIBLE : View.GONE);
        isDeliver.setVisibility(exerciseGoodsDetail.getIsDeliver() == 0 ? View.VISIBLE : View.GONE);
        initProductAttrValueIds();
        tvAttrs.setText(getAttrStr());
    }

    public String getAttrStr() {
        String attrStr = "";
        List<CommonGoodsAttrsBean.AttrListBean> attrList = product.getProductAttrList();
        for (int i = 0; i < attrList.size(); i++) {
            List<CommonGoodsAttrsBean.AttrListBean.AttrValueListBean> attrValueList = attrList.get(i).getAttrValueList();
            for (int j = 0; j < attrValueList.size(); j++) {
                if (attrValueList.get(j).getAttrValueId() == productAttrValueIds[i]) {
                    attrStr = attrStr + attrList.get(i).getAttrName() + ":" + attrValueList.get(j).getAttrValue() + "；";
                }
            }
        }
        if (!TextUtils.isEmpty(attrStr))
            attrStr = attrStr.substring(0, attrStr.length() - 1);
        return attrStr;
    }

    private void initProductAttrValueIds() {
        List<CommonGoodsAttrsBean.AttrListBean> attrList = product.getProductAttrList();
        productAttrValueIds = new int[attrList.size()];
        for (int i = 0; i < attrList.size(); i++) {
            productAttrValueIds[i] = attrList.get(i).getAttrValueList().get(0).getAttrValueId();
        }
    }

    private void initDialog() {
        CommonGoodsAttrsBean commonGoodsAttrsBean = new CommonGoodsAttrsBean(product.getProductImg(), product.getProductDiscountPrice(), productAttrValueIds, product.getProductAttrList(), itemCount, product.getProductQty());
        chooseGoodsAttrsDialog = new ChooseGoodsAttrsDialog(baseActivity, commonGoodsAttrsBean, 0);
        chooseGoodsAttrsDialog.setOnSureClickListener(new ChooseGoodsAttrsDialog.OnSureClickListener() {
            @Override
            public void onSureClick(int itemCount, int[] productAttrValueIds, String attrStr) {
                resetValue(itemCount, productAttrValueIds, attrStr);
            }
        });
    }

    public void resetValue(int itemCount, int[] productAttrValueIds, String attrStr) {
        this.itemCount = itemCount;
        this.productAttrValueIds = productAttrValueIds;
        tvAttrs.setText(attrStr);
    }

    /*把数组拼接成字符串*/
    public String getStr() {
        String valueIds = "";
        for (int i = 0; i < productAttrValueIds.length; i++) {
            valueIds += productAttrValueIds[i] + ",";
        }
        if (!TextUtils.isEmpty(valueIds))
            valueIds = valueIds.substring(0, valueIds.length() - 1);
        return valueIds;
    }

    String valueIds="";
    /*确认订单，给activity调*/
    public void confirmOrder() {
        valueIds=getStr();
        mPresenter.confirmOrder(DefaultPreferenceUtil.getInstance().getToken(), 3, itemCount, exerciseGoodsDetail.getExerciseId(), exerciseGoodsDetail.getProductSaleId(), null, valueIds, null, 6);
    }

    @Override
    public void onConfirmOrderFinish(Object object, int type) {
        super.onConfirmOrderFinish(object, type);
        if (type == 6) {
            ConfirmExerciseOrder confirmExerciseOrder = (ConfirmExerciseOrder) object;
            Bundle bundle = new Bundle();
            bundle.putSerializable("confirmExerciseOrder", confirmExerciseOrder);
            bundle.putString("valueIds",valueIds);
            ARouter.getInstance().build(ARouterConst.Activity_ConfirmExerciseOrderActivity).withBundle("bundle", bundle).navigation();
        }

    }
}