package com.acchain.community.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseApplication;
import com.acchain.community.base.BaseFragment;
import com.acchain.community.bean.ConfirmPreOrder;
import com.acchain.community.bean.PreSellGoodsDetail;
import com.acchain.community.contract.PreSellGoodContract;
import com.acchain.community.presenter.PreSellGoodPresenter;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.util.GoodsUtil;
import com.acchain.community.util.L;
import com.acchain.community.window.dialog.ChooseGoodsAttrsDialog;
import com.acchain.community.window.dialog.CommonGoodsAttrsBean;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * function---- PreSellGoodFragment
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/12 12:54:14 (+0000).
 */
@Route(path = ARouterConst.Fragment_PreSellGoodsFragment)
public class PreSellGoodFragment extends BaseFragment<PreSellGoodPresenter> implements PreSellGoodContract.View {
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.tv_news)
    TextView tvNews;
    @BindView(R.id.tv_video_monitor)
    TextView tvVideoMonitor;
    @BindView(R.id.tv_pre_sell_record)
    TextView tvPreSellRecord;
    @BindView(R.id.tv_choose_goods)
    TextView tvChooseGoods;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.describe)
    TextView describe;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.circulation)
    TextView circulation;
    @BindView(R.id.sold_count)
    TextView soldCount;
    @BindView(R.id.tv_attr)
    TextView tvAttr;
    @BindView(R.id.cb_agreement)
    CheckBox cbAgreement;
    @BindView(R.id.start_time)
    TextView startTime;
    @BindView(R.id.end_time)
    TextView endTime;
    private PreSellGoodsDetail preSellGoodsDetail;
    private PreSellGoodsDetail.ProductsBean productsBean;
    private ChooseGoodsAttrsDialog chooseGoodsAttrsDialog;
    private int itemCount = 1;//商品购买数量
    private int[] productAttrValueIds;
    private String valueIds="";

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_pre_sell_goods;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
    }

    @Override
    protected void injectSelf() {
        fragmentComponent.inject(this);
    }


    @OnClick({R.id.tv_share, R.id.tv_news, R.id.tv_video_monitor, R.id.tv_pre_sell_record, R.id.tv_choose_goods})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_share:
                break;
            case R.id.tv_news:
                ARouter.getInstance().build(ARouterConst.Activity_PreSellNewsActivity).navigation();
                break;
            case R.id.tv_video_monitor:
                break;
            case R.id.tv_pre_sell_record:
                ARouter.getInstance().build(ARouterConst.Activity_PreSellRecordActivity).navigation();
                break;
            case R.id.tv_choose_goods:
                if (preSellGoodsDetail == null) {
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
        CommonGoodsAttrsBean commonGoodsAttrsBean = new CommonGoodsAttrsBean(productsBean.getProductImg(), productsBean.getProductDiscountPrice(), productAttrValueIds, productsBean.getProductAttrList(), itemCount, productsBean.getProductQty());
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
        tvAttr.setText(attrStr);
    }

    public void refreshUi(PreSellGoodsDetail preSellGoodsDetail) {
        this.preSellGoodsDetail = preSellGoodsDetail;
        productsBean = preSellGoodsDetail.getProducts().get(0);
        Glide.with(baseActivity).load(BaseApplication.getApplication().getBaseImageUrl() + productsBean.getProductImg()).into(image);
        tvShare.setText(productsBean.getProductName());
        describe.setText(productsBean.getShortDesc());
        price.setText("￥" + (int) productsBean.getProductPrice() + "");
        price.setTypeface(Typeface.DEFAULT_BOLD);
        circulation.setText(productsBean.getProductQty() + "");
        soldCount.setText(productsBean.getSoldCount() + "");
        productAttrValueIds=GoodsUtil.initProductAttrValueIds(productsBean.getProductAttrList());
        tvAttr.setText(GoodsUtil.getAttrStr(productsBean.getProductAttrList(),productAttrValueIds));
    }

    public boolean isAgreement() {
        return cbAgreement.isChecked();
    }


    /*给activity调*/
    public void addCart() {
        valueIds=GoodsUtil.getStr(productAttrValueIds);
        if (preSellGoodsDetail != null) {
            mPresenter.checkAndAddCart(1, preSellGoodsDetail.getProducts().get(0).getId(), preSellGoodsDetail.getId(), itemCount, valueIds, null, null, DefaultPreferenceUtil.getInstance().getToken());
        }
    }

    /*确认订单，给activity调*/
    public void confirmOrder() {
        mPresenter.confirmOrder(DefaultPreferenceUtil.getInstance().getToken(), 1, itemCount, preSellGoodsDetail.getProductId(), null, null, GoodsUtil.getStr(productAttrValueIds), null, 2);
    }

    @Override
    public void onConfirmOrderFinish(Object object, int type) {
        super.onConfirmOrderFinish(object, type);
        if (object == null) {
            L.i("");
        } else if (type == 2) {//提交预购订单
            ConfirmPreOrder confirmPreOrder = (ConfirmPreOrder) object;
            Bundle bundle = new Bundle();
            bundle.putString("valueIds",valueIds);
            bundle.putSerializable("confirmPreOrder", confirmPreOrder);
            ARouter.getInstance().build(ARouterConst.Activity_ConfirmPreSellOrderActivity).withBundle("bundle", bundle).navigation();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}