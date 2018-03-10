package com.acchain.community.window.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acchain.community.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 底部弹出的dialog,带取消
 */
public class ChoosePayWayDialog extends DialogViewHolder {

    @BindView(R.id.tv_close)
    TextView tvClose;
    @BindView(R.id.tv_weixin)
    TextView tvWeixin;
    @BindView(R.id.tv_alipay)
    TextView tvAlipay;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.ll_pay)
    LinearLayout llPay;
    private Context mContext;
    private OnCommitPayListener onCommitPayListener;
    private int payWay = -1;//0--微信，1--支付宝

    public ChoosePayWayDialog(Context context, double totalFee, OnCommitPayListener onCommitPayListener) {
        super(context);
        this.mContext=context;
        this.onCommitPayListener = onCommitPayListener;
        initView(totalFee);
    }

    private void initView(double totalFee) {
        this.setCanceledOnTouchOutside(true);//点击外面是否取消
        tvMoney.setText("￥" + (int)totalFee);
    }

    @OnClick({R.id.tv_close, R.id.tv_weixin, R.id.tv_alipay,R.id.ll_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_close:
                dismiss();
                break;
            case R.id.tv_weixin:
                if (payWay == 0) {
                    return;
                }
                tvWeixin.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.weixinzhifu, 0, R.drawable.xunzhezhifufangshi, 0);
                tvAlipay.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.zhifubao, 0, 0, 0);
                payWay = 0;
                break;
            case R.id.tv_alipay:
                if (payWay == 1) {
                    return;
                }
                tvAlipay.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.zhifubao, 0, R.drawable.xunzhezhifufangshi, 0);
                tvWeixin.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.weixinzhifu, 0, 0, 0);
                payWay = 1;
                break;
            case R.id.ll_pay:
                if(payWay==-1){
                    return;
                }
                dismiss();
                if (onCommitPayListener != null) {
                    onCommitPayListener.onCommit(payWay);
                }
                break;
        }
    }

    public interface OnCommitPayListener {
        void onCommit(int payWay);
    }

    @Override
    protected int getStyle() {
        return super.getStyle();
    }

    @Override
    protected int getWindowAnimations() {
        return R.style.dialog_bottom_to_up;
    }

    @Override
    protected int getWidth() {
        return WindowManager.LayoutParams.MATCH_PARENT;
    }

    @Override
    protected int getHeight() {
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_choose_pay_way;
    }

    @Override
    protected int getGravity() {
        return Gravity.BOTTOM;
    }

}
