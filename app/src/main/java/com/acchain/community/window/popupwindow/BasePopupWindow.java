package com.acchain.community.window.popupwindow;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BasePopupWindow {

    private final Unbinder unbinder;
    /*自定义dialog的View*/
    private View view;
    private final PopupWindow popup;

    public BasePopupWindow(Context context) {
        view = LayoutInflater.from(context).inflate(getLayoutId(), null);
        unbinder = ButterKnife.bind(this, view);
        popup = new PopupWindow(view,ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popup.setOutsideTouchable(true);
        popup.setFocusable(true);
        popup.setTouchable(true);
        popup.setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(android.R.color.transparent)));
    }

    protected abstract int getLayoutId();

    /*相对某个控件的位置（正左下方），无偏移*/
    public void show(View anchor) {
        popup.showAsDropDown(anchor);
    }

    /*相对某个控件的位置，有偏移;xoff表示x轴的偏移，正值表示向右，负值表示向左；yoff表示相对y轴的偏移，正值是向下，负值是向上；*/
    public void show(View anchor, int xoff, int yoff) {
        popup.showAsDropDown(anchor, xoff, yoff);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void show(View anchor, int xoff, int yoff, int gravity) {
        popup.showAsDropDown(anchor, xoff, yoff, gravity);
    }

    /*相对于父控件的位置（例如正中央Gravity.CENTER，下方Gravity.BOTTOM等），可以设置偏移或无偏移*/
    public void showAtLocation(View anchor, int gravity, int xoff, int yoff) {
        popup.showAtLocation(anchor, gravity, xoff, yoff);
    }


    public void dismiss() {
        if (popup.isShowing()) {
            popup.dismiss();
        }
    }

    public void setHeight(int height) {
        popup.setHeight(height);
    }

    public void setWidth(int width) {
        popup.setWidth(width);
    }

    /*解绑ButterKnife*/
    public void unbind() {
        unbinder.unbind();
    }
}
