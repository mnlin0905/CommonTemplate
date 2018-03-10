package com.acchain.community.window.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.acchain.community.R;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class DialogViewHolder {
    public View view;//自定义dialog的View
    public final Dialog dialog;
    private Context context;
    private DialogInterface.OnDismissListener listener;
    private Unbinder bind;

    public DialogViewHolder(Context context) {
        this.context = context;
        view = LayoutInflater.from(context).inflate(getLayoutId(), null);
        bind = ButterKnife.bind(this, view);//注册注解
        dialog = new Dialog(context, getStyle());
        dialog.setContentView(view);
        /*消失的监听*/
        dialog.setOnDismissListener(dialog -> {
            if (listener != null)
                listener.onDismiss(dialog);
        });
        if (dialog.getWindow() != null) {
            Window window = dialog.getWindow();
            WindowManager.LayoutParams params = window.getAttributes();
            params.y = getY();
            params.x = getX();
            params.width = getWidth();
            params.height = getHeight();
            params.gravity = getGravity();
            window.setAttributes(params);
            /*设置动画，0表示不设置动画*/
            if (getWindowAnimations() != 0) {
                window.setWindowAnimations(getWindowAnimations());
            }
        }
    }

    public int getX() {
        return 0;
    }
    /*y方向偏移值*/

    protected int getY() {
        return 0;
    }

    protected int getHeight() {
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }

    protected abstract int getWidth();

    protected abstract int getLayoutId();

    /*dialog样式*/
    protected int getStyle() {
        return R.style.default_dialog;
    }

    public View getView() {
        return view;
    }

    /*解绑ButterKnife*/
    public void unbind() {
//        ButterKnife.unbind(this);
        bind.unbind();
    }


    public void show() {
        if (!dialog.isShowing())
            dialog.show();
    }

    public void dismiss() {
        if (dialog.isShowing())
            dialog.dismiss();
    }

    public Context getContext() {
        return context;
    }

    public boolean isShow() {
        return dialog.isShowing();
    }

    /**
     * dialog消失的监听
     *
     * @param listener 监听器
     */
    public void setListener(DialogInterface.OnDismissListener listener) {
        this.listener = listener;
    }

    public Window getWindow() {
        return dialog.getWindow();
    }

    protected int getGravity() {
        return Gravity.CENTER;
    }

    /**
     * 默认不加入出现的动画
     *
     * @return int
     */
    protected int getWindowAnimations() {
        return R.style.dialog_animation;
    }

    public boolean isShowing() {
        return dialog.isShowing();
    }

    public void setCanceledOnTouchOutside(boolean cancelable) {
        dialog.setCanceledOnTouchOutside(cancelable);
    }

}
