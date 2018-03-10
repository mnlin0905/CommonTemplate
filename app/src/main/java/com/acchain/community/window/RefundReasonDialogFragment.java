package com.acchain.community.window;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.view.LineMenuView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created on 2018/1/26
 * function :
 *
 * @author ACChain
 */

public class RefundReasonDialogFragment extends DialogFragment implements LineMenuView.LineMenuListener {

    private OnRefundReasonListener listener;
    private View rootView;
    private Unbinder unbinder;

    /**
     * 选中的item
     */
    private int requireCheckedPosition = -1;
    private boolean withGoods;
    private RefundHolderInterface instance;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //去除标题
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        //添加view
        instance = withGoods ? new HolderWithGoods() : new HolderNoGoods();
        rootView = inflater.inflate(withGoods ? R.layout.dialog_fragment_refund_with_goods_reason : R.layout.dialog_fragment_refund_no_goods_reason, null, false);
        unbinder = ButterKnife.bind(instance, rootView);
        LineMenuView[] lmvs = instance.getViews();
        if (requireCheckedPosition != -1) {
            lmvs[requireCheckedPosition].setSelected(true);
        }
        setOnClickListener(lmvs);
        return rootView;
    }

    private void setOnClickListener(LineMenuView... lmvs) {
        for (LineMenuView lmv : lmvs) {
            lmv.setOnClickListener(this);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new Dialog(getActivity(), R.style.ActivityBottomView);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null) {
            Dialog dialog = getDialog();
            Window window = dialog.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            window.setAttributes(attributes);
            window.setGravity(Gravity.BOTTOM);
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams
                    .FLAG_FULLSCREEN);
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setWindowAnimations(R.style.ActivityBottomViewAnimation);
            dialog.setCanceledOnTouchOutside(false);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //如果activity实现了对应接口,则默认添加上点击事件
        if (getActivity() instanceof OnRefundReasonListener) {
            listener = (OnRefundReasonListener) getActivity();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public RefundReasonDialogFragment setOnRefundReasonListener(OnRefundReasonListener listener) {
        this.listener = listener;
        return this;
    }

    /**
     * 设置需要提前选中的item
     */
    public RefundReasonDialogFragment setRequestCheckedPosition(int position) {
        requireCheckedPosition = position;
        return this;
    }

    /**
     * 设置类型
     *
     * @param withGoods 是否为收到货物
     */
    public RefundReasonDialogFragment setRefundReasonType(boolean withGoods) {
        this.withGoods = withGoods;
        return this;
    }

    @OnClick(R.id.tv_close)
    public void onViewClicked() {
        if (listener != null && listener.onClose(getDialog())) {
            return;
        }
        getDialog().dismiss();
    }

    @Override
    public boolean performClickLeft(TextView v) {
        return false;
    }

    @Override
    public boolean performClickRight(TextView v) {
        return false;
    }

    @Override
    public void performSelf(LineMenuView v) {
        v.setRadio(true);
        if (listener != null && listener.onSelect(v.getFriendsWithSelf().indexOf(v), getDialog())) {
            return;
        }
        getDialog().dismiss();
    }

    /**
     * 当点击弹出框中按钮时进行回调
     */
    public interface OnRefundReasonListener {
        boolean onSelect(int position, Dialog dialog);

        boolean onClose(Dialog dialog);
    }

    /**
     * holder的通用接口
     */
    private interface RefundHolderInterface {
        LineMenuView[] getViews();
    }

    /**
     * 收到货的话,将内容存储在此类
     */
    class HolderWithGoods implements RefundHolderInterface {
        @BindView(R.id.lmv_radio_0)
        LineMenuView mLmvRadio0;
        @BindView(R.id.lmv_radio_1)
        LineMenuView mLmvRadio1;
        @BindView(R.id.lmv_radio_2)
        LineMenuView mLmvRadio2;
        @BindView(R.id.lmv_radio_3)
        LineMenuView mLmvRadio3;
        @BindView(R.id.lmv_radio_4)
        LineMenuView mLmvRadio4;
        @BindView(R.id.lmv_radio_5)
        LineMenuView mLmvRadio5;
        @BindView(R.id.lmv_radio_6)
        LineMenuView mLmvRadio6;
        @BindView(R.id.lmv_radio_7)
        LineMenuView mLmvRadio7;

        @Override
        public LineMenuView[] getViews() {
            return new LineMenuView[]{mLmvRadio0, mLmvRadio1, mLmvRadio2, mLmvRadio3, mLmvRadio4, mLmvRadio5, mLmvRadio6, mLmvRadio7,};
        }
    }

    /**
     * 未收到货,view存储
     */
    class HolderNoGoods implements RefundHolderInterface {
        @BindView(R.id.lmv_radio_0)
        LineMenuView mLmvRadio0;
        @BindView(com.acchain.community.R.id.lmv_radio_1)
        LineMenuView mLmvRadio1;

        @Override
        public LineMenuView[] getViews() {
            return new LineMenuView[]{mLmvRadio0, mLmvRadio1};
        }
    }
}
