package com.acchain.community.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.acchain.community.R;
import com.acchain.community.adapter.IndexPanicAdapter;
import com.acchain.community.adapter.IndexPreAdapter;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseApplication;
import com.acchain.community.base.BaseFragment;
import com.acchain.community.bean.IndexDataBean;
import com.acchain.community.bean.PayResult;
import com.acchain.community.contract.IndexContract;
import com.acchain.community.drawable.RoundRectShapeDrawable;
import com.acchain.community.presenter.IndexPresenter;
import com.acchain.community.util.DateUtil;
import com.acchain.community.util.L;
import com.acchain.community.view.TimeView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alipay.sdk.app.PayTask;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * function---- IndexFragment
 * Created(Gradle default create) by ACChain on 2017/12/20 10:48:47 (+0000).
 */
@Route(path = ARouterConst.Fragment_IndexFragment)
public class IndexFragment extends BaseFragment<IndexPresenter> implements IndexContract.View, Toolbar.OnMenuItemClickListener {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.banner)
    ConvenientBanner banner;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.time_view)
    TimeView timeView;
    @BindView(R.id.rl_panic)
    View rlPanic;
    @BindView(R.id.panic_recycler)
    RecyclerView panicRecycler;
    @BindView(R.id.pre_recycler)
    RecyclerView preRecycler;
    @BindView(R.id.exercise_recycler)
    RecyclerView exerciseRecycler;
    @BindView(R.id.search)
    TextView search;
    private List<String> bannerList = new ArrayList<>();
    private IndexPanicAdapter panicAdapter;
    private IndexPreAdapter preAdapter;
    private IndexPreAdapter exerciseAdapter;
    /*限时抢购的数据，先保存起来*/
    private List<IndexDataBean.FlashSalesBean> flashSales;
    private boolean isPanicShow = false;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(baseActivity, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(baseActivity, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
            }
        }
    };

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_index;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //初始化toolbar
        mToolbar.inflateMenu(R.menu.menu_fragment_index);
        mToolbar.setOnMenuItemClickListener(this);
        mToolbar.setNavigationOnClickListener(v -> {
            Toast.makeText(BaseApplication.getApplication(), "扫码", Toast.LENGTH_SHORT).show();
        });
        timeView.setOnTimeFinish(() -> {
            //时间到了
            handPanicData();
        });
        search.post(() -> search.setBackground(new RoundRectShapeDrawable(search.getWidth(), search.getHeight(), getResources().getColor(R.color.blue_search_background))));
        //当布局显示时,动态设置toolbar高度
        linearLayout.post(() -> {
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) mToolbar.getLayoutParams();
            TypedValue typedValue = new TypedValue();
            baseActivity.getTheme().resolveAttribute(android.R.attr.actionBarSize, typedValue, true);
            layoutParams.height = getResources().getDimensionPixelSize(typedValue.resourceId) + linearLayout.getMeasuredHeight();
            layoutParams = (ViewGroup.MarginLayoutParams) banner.getLayoutParams();
            layoutParams.bottomMargin = linearLayout.getMeasuredHeight();
            linearLayout.getParent().requestLayout();
        });
        initRecycler();
        mPresenter.getIndexData();
    }

    private void initBanner() {
        //自定义你的Holder，实现更多复杂的界面，不一定是图片翻页，其他任何控件翻页亦可。
        banner.setPages(
                () -> new LocalImageHolderView(), bannerList)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.dian1, R.drawable.dian0})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
        //设置翻页的效果，不需要翻页效果可用不设
        //.setPageTransformer(Transformer.DefaultTransformer);    集成特效之后会有白屏现象，新版已经分离，如果要集成特效的例子可以看Demo的点击响应。
//        banner.setManualPageable(false);//设置不能手动影响
        banner.setOnItemClickListener(position -> Logger.i("position=" + position));
        banner.startTurning(3000);
    }

    private void initRecycler() {
        panicRecycler.setLayoutManager(new LinearLayoutManager(baseActivity, LinearLayoutManager.HORIZONTAL, false));
        panicAdapter = new IndexPanicAdapter(baseActivity);
        panicRecycler.setAdapter(panicAdapter);

        preRecycler.setLayoutManager(new LinearLayoutManager(baseActivity, LinearLayoutManager.HORIZONTAL, false));
        preAdapter = new IndexPreAdapter(baseActivity);
        preRecycler.setAdapter(preAdapter);

        exerciseRecycler.setLayoutManager(new LinearLayoutManager(baseActivity, LinearLayoutManager.HORIZONTAL, false));
        exerciseAdapter = new IndexPreAdapter(baseActivity);
        exerciseRecycler.setAdapter(exerciseAdapter);
    }

    @Override
    public void onIndexDataFinish(IndexDataBean indexDataBean) {
        if (indexDataBean == null) {
            L.i("failed");
        } else {
            L.i("success");
            List<IndexDataBean.ChannelsBean> channels = indexDataBean.getChannels();
            for (IndexDataBean.ChannelsBean chanelBean : channels) {
                if (chanelBean.getChannelAliasName().equals("mall_app_index")) {//app的数据
                    List<IndexDataBean.ChannelsBean.BoothListBean> boothList = chanelBean.getBoothList();
                    for (IndexDataBean.ChannelsBean.BoothListBean boothBean : boothList) {
                        if (boothBean.getBoothAliasName().equals("mall_app_index_banner_booth")) {//banner图
                            List<IndexDataBean.ChannelsBean.BoothListBean.AdvertListBean> advertList = boothBean.getAdvertList();
                            for (IndexDataBean.ChannelsBean.BoothListBean.AdvertListBean advertBean : advertList) {
//                                bannerList.add(getString(R.string.image_address) + advertBean.getAdImgUrl());
                                bannerList.add(getString(R.string.test_base_image_address) + "group1/M00/00/02/wKgB_Vpi4tuAd3IyAAFTSLCgDUw599.png");
                            }
                            initBanner();
                        } else if (boothBean.getBoothAliasName().equals("mall_app_index_product_booth")) {// 4个功能点包含图片

                        } else if (boothBean.getBoothAliasName().equals("mall_app_index_purchase_product_booth")) {//抢购商品展位,多余的，先不管

                        } else if (boothBean.getBoothAliasName().equals("mall_app_index_presell_product_booth")) {//预售商品展位
                            List<IndexDataBean.ChannelsBean.BoothListBean.ProductListBean> productList = boothBean.getProductList();
                            preAdapter.setData(productList);
                        }
                    }
                }
            }
            flashSales = indexDataBean.getFlashSales();//限时抢购的信息
            if (flashSales != null) {
                handPanicData();
            }
        }
    }

    /*处理限时抢购*/
    private void handPanicData() {
        for (IndexDataBean.FlashSalesBean flashSalesBean : flashSales) {
            long betweenHour = DateUtil.isBetweenHour(flashSalesBean.getBeginTime(), flashSalesBean.getEndTime());
            if (betweenHour == 0) {//已经过了
                L.i("已经过了");
            } else if (betweenHour == 1) {//还未到时间
                L.i("还未到时间");
            } else {//在范围内
                L.i("在范围内");
                timeView.start((int) betweenHour);
                isPanicShow = true;
                List<IndexDataBean.FlashSalesBean.ProductListBeanX> productList = flashSalesBean.getProductList();
                panicAdapter.setData(productList);
            }
        }
        //没有东西，隐藏控件
        panicRecycler.setVisibility(isPanicShow ? View.VISIBLE : View.GONE);
        rlPanic.setVisibility(isPanicShow ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void injectSelf() {
        fragmentComponent.inject(this);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_message:
                break;
            case R.id.action_shopping_cart:
                ARouter.getInstance().build(ARouterConst.Activity_ShoppingCartActivity).navigation();
                break;
        }
        return false;
    }

    @OnClick({R.id.search, R.id.tv_panic, R.id.tv_pre_sell, R.id.tv_adopt, R.id.tv_exercise, R.id.rl_panic, R.id.rl_pre_sell, R.id.rl_adopt, R.id.rl_exercise})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search:
                scan();
                break;
            case R.id.tv_panic:
            case R.id.rl_panic:
                ARouter.getInstance().build(ARouterConst.Activity_PanicActivity).navigation();
                break;
            case R.id.tv_pre_sell:
            case R.id.rl_pre_sell:
                ARouter.getInstance().build(ARouterConst.Activity_PreSellActivity).navigation();
                break;
            case R.id.tv_adopt:
            case R.id.rl_adopt:
                ARouter.getInstance().build(ARouterConst.Activity_AdoptActivity).navigation();
                break;
            case R.id.tv_exercise:
            case R.id.rl_exercise:
                ARouter.getInstance().build(ARouterConst.Activity_ExerciseActivity).navigation();
                break;
        }
    }

    /**
     * 扫码
     */
    private void scan() {
        ZXingLibrary.initDisplayOpinion(baseActivity);
        Intent intent = new Intent(baseActivity, CaptureActivity.class);
        startActivityForResult(intent, 0);
    }

    /**
     * 扫码的回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(baseActivity, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(baseActivity, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void pay() {
        final String orderInfo = "alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2018012602079572&biz_content=%7B%22out_trade_no%22%3A%22SC_20180308145704381%22%2C%22total_amount%22%3A0.01%2C%22subject%22%3A%22%E5%BE%AE%E8%BF%88%E5%95%86%E5%9F%8E%E8%AE%A2%E5%8D%95%22%2C%22timeout_express%22%3A%2215m%22%2C%22disable_paymethod%22%3A%22creditCardExpress%22%2C%22body%22%3A%22%E5%BE%AE%E8%BF%88%E5%95%86%E5%9F%8E%E6%94%AF%E4%BB%98%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fwww.yzzao.com&return_url=http%3A%2F%2Fwww.yzzao.com&sign=Jz0LXY%2FIz5uarieC41ib%2BZeblJl8p%2F83iMmo7KfJVC72LlCduINTdnzY2yRZ3RVF9v8H3xo1guES4Qu%2Fb%2FtjJIOCvVqnU1k6SUtzNFOSZYqWDzpXFwOoK5PmfsNpudO2754riiV6J1cZHjiMFBcC2VlI5TDsDMFYJDyJuoEANfFb6vjQDkB4RGV1EFIc3OPNwuVcgRNbjYbWw9lFKCBW08eLHXT7zeRp%2FqQ7B5mZx8c5jyI1VUAx9zIX%2BsBWjsuOqfusUQPvGDJvh%2BHtgPv0q1sw%2BnznTigtjNcwpA4%2FzHFJOAttZCa%2BcjCDQU3iPQl3%2B8hVqdk0xKv9q%2BWOH0O8VA%3D%3D&sign_type=RSA2&timestamp=2018-03-08+14%3A57%3A04&version=1.0";
        Observable
                .just(orderInfo)
                .map(orderI -> {
                    PayTask alipay = new PayTask(baseActivity);
                    //获取支付结果
                    Map<String, String> result = alipay.payV2(orderI, true);
                    return new PayResult(result);
                })
                .subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(payResult -> {

                }, throwable -> {

                });
    }

    public class LocalImageHolderView implements Holder<String> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, final int position, String data) {
            Glide.with(context).load(data).into(imageView);
        }
    }
}