package com.acchain.community.window;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created on 2018/1/30
 * function : 全局的弹出框,用于统一跳转逻辑
 *
 * @author ACChain
 */

public class GlobalAlertDialog extends AlertDialog {
    public GlobalAlertDialog(Context context) {
        super(context);
    }

    public GlobalAlertDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public GlobalAlertDialog(Context context, int themeResId) {
        super(context, themeResId);
    }


}
