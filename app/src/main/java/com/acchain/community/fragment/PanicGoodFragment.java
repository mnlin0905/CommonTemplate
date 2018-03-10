package com.acchain.community.fragment;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseApplication;
import com.acchain.community.base.BaseFragment;
import com.acchain.community.bean.ConfirmPanicOrder;
import com.acchain.community.bean.PanicGoodDetail;
import com.acchain.community.contract.PanicGoodContract;
import com.acchain.community.presenter.PanicGoodPresenter;
import com.acchain.community.util.DateUtil;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.util.GoodsUtil;
import com.acchain.community.util.L;
import com.acchain.community.view.TimeView;
import com.acchain.community.window.dialog.ChooseGoodsAttrsDialog;
import com.acchain.community.window.dialog.CommonGoodsAttrsBean;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * function---- PanicGoodFragment
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/09 07:43:07 (+0000).
 */
@Route(path = ARouterConst.Fragment_PanicGoodFragment)
public class PanicGoodFragment extends BaseFragment<PanicGoodPresenter> implements PanicGoodContract.View {

    @BindView(R.id.tv_choose_goods)
    TextView tvChooseGoods;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.soldCount)
    TextView soldCount;
    @BindView(R.id.time_view)
    TimeView timeView;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.describe)
    TextView describe;
    @BindView(R.id.before_price)
    TextView beforePrice;
    @BindView(R.id.isExemption)
    TextView isExemption;
    @BindView(R.id.isRefund)
    TextView isRefund;
    @BindView(R.id.isDeliver)
    TextView isDeliver;
    @BindView(R.id.tv_standard)
    TextView tvStandard;
    private PanicGoodDetail panicGoodDetail;
    private ChooseGoodsAttrsDialog chooseGoodsAttrsDialog;
    /*商品属性，需要传到dialog*/
    public int itemCount = 1;//商品购买数量，默认为1
    private int[] productAttrValueIds;//属性的id进行拼接
    private String valueIds="";

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_panic_goods;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        timeView.setOnTimeFinish(new TimeView.OnTimeFinish() {
            @Override
            public void onTimeFinish() {
            }
        });
        if (panicGoodDetail != null) {
            refreshData(panicGoodDetail);
        }
    }

    @Override
    protected void injectSelf() {
        fragmentComponent.inject(this);
    }

    @OnClick({R.id.tv_choose_goods})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_choose_goods:
                if (panicGoodDetail == null) {
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

    private void initDialog() {
        CommonGoodsAttrsBean commonGoodsAttrsBean = new CommonGoodsAttrsBean(panicGoodDetail.getProductImg(), panicGoodDetail.getProductDiscountPrice(), productAttrValueIds, panicGoodDetail.getAttrList(), itemCount, panicGoodDetail.getProductQty());
        chooseGoodsAttrsDialog = new ChooseGoodsAttrsDialog(baseActivity, commonGoodsAttrsBean,0);
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
        tvStandard.setText(attrStr);
    }

    public void refreshData(PanicGoodDetail panicGoodDetail) {
        this.panicGoodDetail = panicGoodDetail;
        Glide.with(baseActivity).load(BaseApplication.getApplication().getBaseImageUrl() + panicGoodDetail.getProductImg()).into(image);
        price.setText((int) panicGoodDetail.getProductDiscountPrice() + "");
        soldCount.setText("已售" + panicGoodDetail.getSoldCount());
        timeView.start((int) DateUtil.getRemainingTimeByDate(panicGoodDetail.getEndTime()));
        name.setText(panicGoodDetail.getProductName());
        describe.setText(panicGoodDetail.getShortDesc());
        beforePrice.setText((int) panicGoodDetail.getProductPrice() + "");
        beforePrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        isExemption.setVisibility(panicGoodDetail.getIsExemption() == 0 ? View.VISIBLE : View.GONE);
        isRefund.setVisibility(panicGoodDetail.getIsRefund() == 0 ? View.VISIBLE : View.GONE);
        isDeliver.setVisibility(panicGoodDetail.getIsDeliver() == 0 ? View.VISIBLE : View.GONE);
        productAttrValueIds = GoodsUtil.initProductAttrValueIds(panicGoodDetail.getAttrList());
        tvStandard.setText(GoodsUtil.getAttrStr(panicGoodDetail.getAttrList(),productAttrValueIds));
    }

    /*加入购物车，给activity调*/
    public void addCart() {
        if (panicGoodDetail != null) {
            mPresenter.checkAndAddCart(0, panicGoodDetail.getProductId(), panicGoodDetail.getFlashSaleId(), itemCount, GoodsUtil.getStr(productAttrValueIds), panicGoodDetail.getFlashSaleRefId(), null, DefaultPreferenceUtil.getInstance().getToken());
        }
    }

    /*确认订单，给activity调*/
    public void confirmOrder() {
        valueIds=GoodsUtil.getStr(productAttrValueIds);
        mPresenter.confirmOrder(DefaultPreferenceUtil.getInstance().getToken(), 0, itemCount, panicGoodDetail.getPurchaseId(), panicGoodDetail.getFlashSaleRefId(), null,  valueIds,null, 0);
    }

    @Override
    public void onConfirmOrderFinish(Object object, int type) {
        super.onConfirmOrderFinish(object, type);
        if (object == null) {
            L.i("");
        } else if (type == 0) {
            ConfirmPanicOrder confirmPanicOrder = (ConfirmPanicOrder) object;
            Bundle bundle = new Bundle();
            bundle.putString("valueIds",valueIds);
            bundle.putSerializable("confirmPanicOrder", confirmPanicOrder);
            ARouter.getInstance().build(ARouterConst.Activity_ConfirmPanicOrderActivity).withBundle("bundle", bundle).navigation();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != timeView)
            timeView.stop();
    }
}