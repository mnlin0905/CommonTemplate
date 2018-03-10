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
import com.blankj.utilcode.util.ScreenUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created on 2018/1/16
 * function : 简单的删除提示框
 *
 * @author ACChain
 */

public class CancelConfirmDialogFragment extends DialogFragment {
    Unbinder unbinder;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_cancel)
    TextView mTvCancel;
    @BindView(R.id.tv_confirm)
    TextView mTvConfirm;

    private OnOperateListener listener;
    private View rootView;
    private String dialogTitle;
    private String cancelStr;
    private String confirmStr;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //去除标题
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        //添加view
        rootView = inflater.inflate(R.layout.dialog_fragment_cancel_confirm, null, false);
        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
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
        mTvCancel.setText(cancelStr);
        mTvConfirm.setText(confirmStr);
        mTvTitle.setText(dialogTitle);
        if (getDialog() != null) {
            Dialog dialog = getDialog();
            Window window = dialog.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            window.setAttributes(attributes);
            window.setGravity(Gravity.CENTER);
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams
                    .FLAG_FULLSCREEN);
            window.setLayout((int) (ScreenUtils.getScreenWidth() * 0.75), WindowManager.LayoutParams.WRAP_CONTENT);
            dialog.setCanceledOnTouchOutside(true);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //如果activity实现了对应接口,则默认添加上点击事件
        if (getActivity() instanceof OnOperateListener) {
            listener = (OnOperateListener) getActivity();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.tv_cancel, R.id.tv_confirm})
    public void onViewClicked(View view) {
        if (listener == null) {
            return;
        }

        boolean consume = false;
        switch (view.getId()) {
            case R.id.tv_cancel:
                consume = listener.onCancel(getDialog());
                break;
            case R.id.tv_confirm:
                consume = listener.onConfirm(getDialog());
                break;
        }

        if (!consume) {
            getDialog().dismiss();
        }
    }


    ////////////////////////////////////////

    public CancelConfirmDialogFragment setTitle(String title) {
        dialogTitle = title;
        return this;
    }

    public CancelConfirmDialogFragment setCancelText(String cancel) {
        cancelStr = cancel;
        return this;
    }

    public CancelConfirmDialogFragment setConfirmText(String confirm) {
        confirmStr = confirm;
        return this;
    }

    public CancelConfirmDialogFragment setOnOperateListener(OnOperateListener listener) {
        this.listener = listener;
        return this;
    }

    ////////////////////////////////////////


    /**
     * 监听listener
     */
    public interface OnOperateListener {
        /**
         * 点击左侧按钮
         *
         * @param dialog dialog
         * @return 返回true则默认不会关闭dialog
         */
        boolean onCancel(Dialog dialog);

        /**
         * 点击右侧按钮
         * @param dialog dialog
         * @return 返回true则默认不会关闭dialog
         */
        boolean onConfirm(Dialog dialog);
    }
}
