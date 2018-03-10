package com.acchain.community.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.bean.QueryPay;
import com.acchain.community.bean.WeiXinPay;
import com.acchain.community.contract.WXPayEntryContract;
import com.acchain.community.presenter.WXPayEntryPresenter;
import com.acchain.community.util.Const;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.util.L;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

@Route(path = ARouterConst.Activity_WXPayEntryActivity)
public class WXPayEntryActivity extends BaseActivity<WXPayEntryPresenter> implements IWXAPIEventHandler, WXPayEntryContract.View {
    @Autowired(name = Const.MODEL_WEIXIN_PAY, required = true)
    WeiXinPay weiXinPay;
    @Autowired(name = Const.KEY_PRODUCT_TYPE, required = true)
    int productType;          //商品类型0：抢购1：预售 2：领养 3：行权
    @Autowired(name = Const.KEY_ORDER_CODES, required = true)
    String orderCodes;      //用于生成预交易支付的单号
    private IWXAPI api;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_wexin_pay;
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        L.i("WXPayEntryActivity,onCreate");
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        L.i("WXPayEntryActivity,initData");
        api = WXAPIFactory.createWXAPI(getApplicationContext(), null);
        if (!api.isWXAppInstalled()) {
            Toast.makeText(this, "请先安装微信", Toast.LENGTH_SHORT).show();
            return;
        }
        api.registerApp(weiXinPay.getData().getAppid()); //注册appid  appid可以在开发平台获取
        //这里注意要放在子线程
        Runnable payRunnable = () -> {
            PayReq request = new PayReq(); //调起微信APP的对象
            request.appId = weiXinPay.getData().getAppid();
            request.partnerId = weiXinPay.getData().getPartnerid();
            request.prepayId = weiXinPay.getData().getPrepayid();
            request.packageValue = "Sign=WXPay";
            request.nonceStr = weiXinPay.getData().getNoncestr();
            request.timeStamp = weiXinPay.getData().getTimestamp();
            request.sign = weiXinPay.getData().getSign();
            api.sendReq(request);//发送调起微信的请求
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        boolean be = api.handleIntent(intent, this);
        if (!be) {
            finish();
        }
    }

    @Override
    public void onReq(BaseReq req) {
        switch (req.getType()) {
            case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX:
                L.i("");
                break;
            case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:
                L.i("");
                break;
            default:
                break;
        }
    }

    @Override
    public void onResp(BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (resp.errCode == 0) {
                mPresenter.queryWechatPay(DefaultPreferenceUtil.getInstance().getToken(), productType, orderCodes, weiXinPay.getData().getOut_trade_no());
            } else {
                Intent intent = new Intent();
                if (resp.errCode == -2) {
                    Toast.makeText(this, "取消支付", Toast.LENGTH_LONG).show();
                    intent.putExtra(Const.KEY_PAY_STATE, Const.TYPE_PAY_CANCEL);
                } else {
                    Toast.makeText(this, "支付失败", Toast.LENGTH_LONG).show();
                    intent.putExtra(Const.KEY_PAY_STATE, Const.TYPE_PAY_FAIL);
                }
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }

    @Override
    public void onQueryWechatPayFinish(QueryPay queryPay) {
        super.onQueryWechatPayFinish(queryPay);
        if (queryPay != null) {
            if (queryPay.getState() == 0) {
                Toast.makeText(this, "支付成功", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.putExtra(Const.KEY_PAY_STATE, Const.TYPE_PAY_SUCCESS);
                setResult(RESULT_OK, intent);
                finish();
            }else {
                L.i("继续查询");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPresenter.queryWechatPay(DefaultPreferenceUtil.getInstance().getToken(), productType, orderCodes, weiXinPay.getData().getOut_trade_no());
                    }
                },1000);
            }
        }
    }
}