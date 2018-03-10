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
 * function : 选择货物当前状态
 *
 * @author ACChain
 */

public class GoodsStatusDialogFragment extends DialogFragment implements LineMenuView.LineMenuListener {


    @BindView(R.id.lmv_select_0)
    LineMenuView mLmvSelect0;
    @BindView(R.id.lmv_select_1)
    LineMenuView mLmvSelect1;
    @BindView(R.id.tv_close)
    TextView mTvClose;

    private OnGoodsStatusListener listener;
    private View rootView;
    private Unbinder unbinder;
    private int requestSetPosition = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //去除标题
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        //添加view
        rootView = inflater.inflate(R.layout.dialog_fragment_goods_status, null, false);
        unbinder = ButterKnife.bind(this, rootView);
        mLmvSelect0.setSelected(requestSetPosition == 0);
        mLmvSelect1.setSelected(requestSetPosition == 1);
        setOnClickListener(mLmvSelect0, mLmvSelect1);
        return rootView;
    }

    /**
     * @param lmvs 重载监听设置
     */
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
        if (getActivity() instanceof OnGoodsStatusListener) {
            listener = (OnGoodsStatusListener) getActivity();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    ///////////////////////////////// 外部调用接口
    public GoodsStatusDialogFragment setOnGoodsStatusListener(OnGoodsStatusListener listener) {
        this.listener = listener;
        return this;
    }

    /**
     * 提前设置选中的位置
     */
    public GoodsStatusDialogFragment setSelectItem(int position) {
        requestSetPosition = position;
        return this;
    }
    ///////////////////////////////////

    @OnClick(R.id.tv_close)
    public void onViewClicked() {
        if (listener != null && listener.onItemClose(getDialog())) {
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
        v.setSelected(true);
        if (v == mLmvSelect0) {
            mLmvSelect1.setSelected(false);
        } else {
            mLmvSelect0.setSelected(false);
        }

        if (listener != null && listener.onItemSelect(v.getFriendsWithSelf().indexOf(v), getDialog())) {
            return;
        }
        getDialog().dismiss();
    }

    /**
     * 当点击弹出框中按钮时进行回调
     */
    public interface OnGoodsStatusListener {
        boolean onItemSelect(int position, Dialog dialog);

        boolean onItemClose(Dialog dialog);
    }
}