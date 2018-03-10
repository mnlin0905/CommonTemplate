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
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;
import com.blankj.utilcode.util.ScreenUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created on 2018/1/17
 * function : 带关闭按钮的警示框
 *
 * @author ACChain
 */

public class AlertCloseDialogFragment extends DialogFragment {
    Unbinder unbinder;
    @BindView(R.id.iv_close)
    ImageView mIvClose;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_confirm)
    TextView mTvConfirm;

    private OnAlertListener listener;
    private View rootView;
    private String dialogTitle;
    private String confirmStr;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //去除标题
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        //添加view
        rootView = inflater.inflate(R.layout.dialog_fragment_alert_close, null, false);
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
        if (getActivity() instanceof OnAlertListener) {
            listener = (OnAlertListener) getActivity();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    ////////////////////////////////////////

    public AlertCloseDialogFragment setTitle(String title) {
        dialogTitle = title;
        return this;
    }

    public AlertCloseDialogFragment setConfirmText(String confirm) {
        confirmStr = confirm;
        return this;
    }

    public AlertCloseDialogFragment setOnAlertListener(OnAlertListener listener) {
        this.listener = listener;
        return this;
    }

    @OnClick({R.id.iv_close, R.id.tv_confirm})
    public void onViewClicked(View view) {
        if (listener == null) {
            getDialog().dismiss();
            return;
        }

        boolean consume = false;
        switch (view.getId()) {
            case R.id.iv_close:
                consume = listener.onClose(getDialog());
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


    /**
     * 监听listener
     */
    public interface OnAlertListener {
        /**
         * 点击右上角关闭按钮
         *
         * @return 返回true则默认不会关闭dialog
         */
        boolean onClose(Dialog dialog);

        /**
         * 点击右侧按钮
         */
        boolean onConfirm(Dialog dialog);
    }
}
