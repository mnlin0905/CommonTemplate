package com.acchain.community.activity.person;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.adapter.AdoptCartOrderCodeAdapter;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.bean.ConfirmCartAdoptOrder;
import com.acchain.community.bean.TransactionDetailBean;
import com.acchain.community.contract.TransactionDetailContract;
import com.acchain.community.presenter.TransactionDetailPresenter;
import com.acchain.community.util.Const;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.view.LineMenuView;
import com.acchain.community.view.MarqueeTextView;
import com.acchain.community.window.CommonPopWindow;
import com.acchain.community.window.popupwindow.ListViewPopup;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.acchain.community.util.Const.KEY_ID;
import static com.acchain.community.util.Const.KEY_TYPE;

/**
 * function---- TransactionDetailActivity
 * <p>
 * 交易详情
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/25 03:11:26 (+0000).
 */
@Route(path = ARouterConst.Activity_TransactionDetailActivity)
public class TransactionDetailActivity extends BaseActivity<TransactionDetailPresenter> implements TransactionDetailContract.View, ListViewPopup.OnItemListener {
    @BindView(R.id.tv_id)
    TextView mTvId;
    @BindView(R.id.tv_cancel)
    TextView mTvCancel;
    @BindView(R.id.tv_to_pay)
    TextView mTvToPay;
    @BindView(R.id.lmv_create_time)
    LineMenuView mLmvCreateTime;
    @BindView(R.id.lmv_transaction_type)
    LineMenuView mLmvTransactionType;
    @BindView(R.id.lmv_currency_name)
    LineMenuView mLmvCurrencyName;
    @BindView(R.id.lmv_buy_price)
    LineMenuView mLmvBuyPrice;
    @BindView(R.id.lmv_buy_amount)
    LineMenuView mLmvBuyAmount;
    @BindView(R.id.lmv_arrive_amount)
    LineMenuView mLmvArriveAmount;
    @BindView(R.id.lmv_status)
    LineMenuView mLmvStatus;
    @BindView(R.id.lmv_total_money)
    LineMenuView mLmvTotalMoney;
    @BindView(R.id.iv_divider)
    View mIvDivider;
    @BindView(R.id.lmv_pay_type)
    LineMenuView mLmvPayType;
    @BindView(R.id.ll_pay)
    LinearLayout mLlPay;
    @BindView(R.id.ts_placeholder)
    ViewStub mTsPlaceholder;

    /**
     * 基础类数据存储
     */
    IViewHolder baseHolder;

    /**
     * id和type
     */
    @Autowired(name = KEY_ID, required = true)
    int transactionId;
    @Autowired(name = KEY_TYPE, required = true)
    int transactionType;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_transaction_detail;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //根据不同的类型或者是否完成,控制加载的布局和需要展示的内容
        switch (transactionType + 1) {
            case Const.TYPE_PRODUCT_PRE_ORDER://预售
                mTsPlaceholder.setLayoutResource(R.layout.viewstub_transaction_detail_preorder);
                baseHolder = new ViewHolder_PreOrder();
                break;
            case Const.TYPE_PRODUCT_ADOPT://领养
            default:
                mTsPlaceholder.setLayoutResource(R.layout.viewstub_transaction_detail_adopt);
                baseHolder = new ViewHolder_Adopt();
        }
        mTsPlaceholder.inflate();
        ButterKnife.bind(baseHolder, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getTransactionDetail(DefaultPreferenceUtil.getInstance().getToken(), transactionId);
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_more, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        CommonPopWindow.getInstance(this, this)
                .showAtLocation(toolbar, Gravity.TOP | Gravity.RIGHT, 0, toolbar.getHeight());
        return true;
    }


    /**
     * @param position menu菜单点击处理
     */
    @Override
    public void onItemClick(int position) {

    }

    @OnClick({R.id.tv_cancel, R.id.tv_to_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel://取消本次交易
                break;
            case R.id.tv_to_pay://去支付
                break;
        }
    }


    /**
     * 1.3.9 获取交易详情
     *
     * @param detailBean 交易详细信息
     */
    @Override
    public void getTransactionDetailFinish(TransactionDetailBean detailBean) {
        TransactionDetailBean.Columns columns;
        if (detailBean == null || (columns = detailBean.getColumns()) == null) {
            return;
        }

        //解析数据
        mLmvCreateTime.setBrief(columns.getCreateTime());
        mTvId.setText(columns.getHash());
        mLmvCurrencyName.setBrief(columns.getName());
        mLmvBuyPrice.setBrief(columns.getPrice());
        mLmvBuyAmount.setBrief(columns.getNum());
        mLmvArriveAmount.setBrief(columns.getNum());
        mLmvStatus.setBrief(columns.getStatusStr());
        mLmvStatus.setBriefColor(columns.getStatusColor(this));
        mLmvTotalMoney.setBrief(columns.getAmount());
        mIvDivider.setVisibility(columns.getStatus() == 0 ? View.VISIBLE : View.GONE);
        mLlPay.setVisibility(columns.getStatus() == 0 ? View.VISIBLE : View.GONE);
        mLmvPayType.setBrief(columns.getPayWayStr());

        //解析数据,holder部分
        Glide.with(this)
                .load(columns.getBusinessIcon())
                .apply(new RequestOptions().centerCrop())
                .into(baseHolder.mIvHead);
        baseHolder.mTvHead.setText(columns.getBusinessName());
        baseHolder.mTvTime.setText(columns.getStartTime());

        //商品类别-预售
        if (baseHolder instanceof ViewHolder_PreOrder) {
            mLmvTransactionType.setBrief("预售");
            ViewHolder_PreOrder preOrder = (ViewHolder_PreOrder) this.baseHolder;
            Glide.with(this)
                    .load(columns.getImg())
                    .apply(new RequestOptions().centerCrop())
                    .into(preOrder.mIvIcon);
            preOrder.mTvBrief.setText(columns.getDesc());
            preOrder.mTvName.setText(columns.getName());
            preOrder.mTvSpecification.setText(columns.getSpecification());
        }

        //商品类别-领养
        if (baseHolder instanceof ViewHolder_Adopt) {
            mLmvTransactionType.setBrief("领养");
            ViewHolder_Adopt adopt = (ViewHolder_Adopt) this.baseHolder;
            // TODO: 2018/3/6 item 点击事件处理
            adopt.datas = convertToAdoptCodeList(detailBean.getItems());
            adopt.adapter = new AdoptCartOrderCodeAdapter(this, adopt.datas);
            adopt.mLvBase.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            adopt.mLvBase.setAdapter(adopt.adapter);
        }
    }

    /**
     * @param items 交易记录中item
     * @return 将list对象进行转换
     */
    private List<ConfirmCartAdoptOrder.OrderInfoBean.AdoptCodeBean> convertToAdoptCodeList(List<TransactionDetailBean.Items> items) {
        if (items == null || items.size() == 0) {
            return new ArrayList<>();
        }

        List<ConfirmCartAdoptOrder.OrderInfoBean.AdoptCodeBean> adoptCodeBeanList = new LinkedList<>();
        for (TransactionDetailBean.Items item : items) {
            adoptCodeBeanList.add(new ConfirmCartAdoptOrder.OrderInfoBean.AdoptCodeBean(item.getCode(), item.getId()));
        }
        return adoptCodeBeanList;
    }

    /**
     * 商品基础类,放置基本商品类别
     */
    class IViewHolder {
        @BindView(R.id.tv_introduce)
        TextView mTvIntroduce;
        @BindView(R.id.iv_head)
        ImageView mIvHead;
        @BindView(R.id.tv_head)
        TextView mTvHead;
        @BindView(R.id.tv_time)
        TextView mTvTime;
    }

    /**
     * 预购类
     */
    class ViewHolder_PreOrder extends IViewHolder {
        @BindView(R.id.iv_icon)
        ImageView mIvIcon;
        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.tv_brief)
        MarqueeTextView mTvBrief;
        @BindView(R.id.tv_specification)
        TextView mTvSpecification;
    }

    /**
     * 领养类
     */
    class ViewHolder_Adopt extends IViewHolder {
        @BindView(R.id.lv_base)
        RecyclerView mLvBase;
        List<ConfirmCartAdoptOrder.OrderInfoBean.AdoptCodeBean> datas;
        AdoptCartOrderCodeAdapter adapter;
    }
}