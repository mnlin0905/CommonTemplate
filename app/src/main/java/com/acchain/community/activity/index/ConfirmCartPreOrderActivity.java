package com.acchain.community.activity.index;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.acchain.community.R;
import com.acchain.community.adapter.PreCartOrderAdapter;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.bean.AliPay;
import com.acchain.community.bean.CommitOrderRequest;
import com.acchain.community.bean.CommitOrderResponse;
import com.acchain.community.bean.ConfirmCartPreOrder;
import com.acchain.community.bean.PayResult;
import com.acchain.community.bean.QueryPay;
import com.acchain.community.bean.WeiXinPay;
import com.acchain.community.contract.ConfirmCartPreOrderContract;
import com.acchain.community.presenter.ConfirmCartPreOrderPresenter;
import com.acchain.community.util.Const;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.util.L;
import com.acchain.community.window.dialog.ChoosePayWayDialog;
import com.acchain.community.wxapi.WXPayUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alipay.sdk.app.PayTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.acchain.community.util.Const.KEY_ORDER_CODES;
import static com.acchain.community.util.Const.KEY_PRODUCT_TYPE;
import static com.acchain.community.util.Const.MODEL_WEIXIN_PAY;

/**
 * function---- ConfirmCartPreOrderActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/02/01 07:50:51 (+0000).
 */
@Route(path = ARouterConst.Activity_ConfirmCartPreOrderActivity)
public class ConfirmCartPreOrderActivity extends BaseActivity<ConfirmCartPreOrderPresenter> implements ConfirmCartPreOrderContract.View {
    @BindView(R.id.order_pre_recycler)
    RecyclerView orderPreRecycler;
    @BindView(R.id.tv_total_fee)
    TextView tvTotalFee;
    @BindView(R.id.tv_pay_now)
    TextView tvPayNow;
    private PreCartOrderAdapter preCartOrderAdapter;
    private ConfirmCartPreOrder confirmCartPreOrder;
    private ChoosePayWayDialog choosePayWayDialog;
    private double totalFee;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_confirm_cart_pre_sell_order;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        orderPreRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        Bundle bundle = getIntent().getBundleExtra("bundle");
        confirmCartPreOrder = (ConfirmCartPreOrder) bundle.getSerializable("confirmCartPreOrder");
        L.i(confirmCartPreOrder.toString());
        preCartOrderAdapter = new PreCartOrderAdapter(this);
        orderPreRecycler.setAdapter(preCartOrderAdapter);
        preCartOrderAdapter.setData(confirmCartPreOrder.getOrderInfo());
        setMoney();
    }

    private void setMoney() {
        totalFee = 0;
        for (ConfirmCartPreOrder.OrderInfoBean ob : confirmCartPreOrder.getOrderInfo()) {
            totalFee += ob.getItemCount() * ob.getProducts().getPresellPrice();
        }
        tvTotalFee.setText((int) totalFee + "");
    }

    @OnClick({R.id.tv_pay_now})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_pay_now:
                if (choosePayWayDialog == null) {
                    choosePayWayDialog = new ChoosePayWayDialog(this, (int) totalFee, new ChoosePayWayDialog.OnCommitPayListener() {
                        @Override
                        public void onCommit(int payWay) {
                            commitOrder(payWay);
                        }
                    });
                }
                choosePayWayDialog.show();
                break;
        }
    }

    public int payWay = 0;

    private void commitOrder(int payWay) {
        this.payWay = payWay;
        if (commitOrderResponse==null) {
            List<CommitOrderRequest> commitOrderRequests = new ArrayList<>();
            List<ConfirmCartPreOrder.OrderInfoBean> orderInfo = confirmCartPreOrder.getOrderInfo();
            for (ConfirmCartPreOrder.OrderInfoBean orderInfoBean : orderInfo) {
                CommitOrderRequest commitOrderRequest = new CommitOrderRequest(DefaultPreferenceUtil.getInstance().getToken(), 1, orderInfoBean.getItemCount(), 154, orderInfoBean.getId());
                commitOrderRequests.add(commitOrderRequest);
            }
            mPresenter.commitOrder(commitOrderRequests, 3);
        } else {
            getPrepayId();
        }
    }

    private CommitOrderResponse commitOrderResponse = null;

    @Override
    public void onCommitOrderFinish(CommitOrderResponse commitOrderResponse, int type) {
        super.onCommitOrderFinish(commitOrderResponse, type);
        if (type == 3) {//从购物车预购提交订单
            if (commitOrderResponse == null) {
                L.i("提交订单失败");
            } else {
                L.i("生成订单成功");
                this.commitOrderResponse = commitOrderResponse;
                getPrepayId();
            }
        }
    }

    /*获取预交易单号*/
    public void getPrepayId() {
        if (payWay == 0) {//微信
            mPresenter.weiXinPay(DefaultPreferenceUtil.getInstance().getToken(), 1, commitOrderResponse.getOrderCode(), 2, 3);
        } else if (payWay == 1) {
            mPresenter.aliPay(DefaultPreferenceUtil.getInstance().getToken(), 1, commitOrderResponse.getOrderCode(), 1, 3);
        }
    }

    @Override
    public void onWeiXinPayFinish(WeiXinPay weiXinPay, int type) {
        if (type == 3) {
            ARouter.getInstance()
                    .build(ARouterConst.Activity_WXPayEntryActivity)
                    .withObject(MODEL_WEIXIN_PAY, weiXinPay)
                    .withInt(KEY_PRODUCT_TYPE,1)
                    .withString(KEY_ORDER_CODES,commitOrderResponse.getOrderCode())
                    .navigation(this, Const.REQUEST_CODE_ONE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Const.REQUEST_CODE_ONE){
            int state = data.getIntExtra(Const.KEY_PAY_STATE, -1);
            if(state==Const.TYPE_PAY_SUCCESS){
                L.i("支付成功");
            }else if(state==Const.TYPE_PAY_CANCEL){
                L.i("取消支付");
            }else if(state==Const.TYPE_PAY_FAIL){
                L.i("支付失败");
            }
        }
    }

    private AliPay aliPay=null;
    @Override
    public void onAliPayFinish(AliPay aliPay, int type) {
        if (type == 3 && aliPay != null) {
            this.aliPay=aliPay;
            Observable
                    .just(aliPay.getData().getForm())
                    .map(orderInfo -> {
                        PayTask payTask = new PayTask(this);
                        //获取支付结果
                        Map<String, String> result = payTask.payV2(orderInfo, true);
                        return new PayResult(result);
                    })
                    .subscribeOn(Schedulers.single())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(payResult -> {
                        if (payResult.getResultStatus().equals("9000") || payResult.getResultStatus().equals("8000") || payResult.getResultStatus().equals("6004")) {//订单支付成功
                            mPresenter.queryAliPay(DefaultPreferenceUtil.getInstance().getToken(),1,commitOrderResponse.getOrderCode(),aliPay.getData().getOut_trade_no(),3);
                        } else if (payResult.getResultStatus().equals("4000")) {
                            showToast("订单支付失败");
                        } else if (payResult.getResultStatus().equals("5000")) {
                            showToast("重复请求");
                        } else if (payResult.getResultStatus().equals("6001")) {
                            showToast("用户中途取消");
                        } else if (payResult.getResultStatus().equals("6002")) {
                            showToast("网络连接出错");
                        }
                    });
        }
    }

    @Override
    public void onQueryAliPayFinish(QueryPay queryPay, int type) {
        if(type==3){
            if (queryPay!=null&&queryPay.getState() == 0) {
                Toast.makeText(this, "支付成功", Toast.LENGTH_LONG).show();
            }else {
                L.i("继续查询");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPresenter.queryAliPay(DefaultPreferenceUtil.getInstance().getToken(),1,commitOrderResponse.getOrderCode(),aliPay.getData().getOut_trade_no(),3);
                    }
                },1000);
            }
        }
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent,null);
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (choosePayWayDialog != null) {
            choosePayWayDialog.unbind();
        }
    }
}