package com.acchain.community.window;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.acchain.community.R;


/**
 * 功能----大型toast
 * <p>
 * Created by ACChain on 2017/9/19.
 */

public class BigToast {
    private static Toast toast;

    public static Toast makeText(Context context, String msg, int duration) {
        if (toast == null) {
            toast = new Toast(context.getApplicationContext());
            LinearLayout view = (LinearLayout) LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.layout_toast_success, null);
            toast.setView(view);
            toast.setGravity(Gravity.CENTER, 0, 0);
        }
        ((TextView) toast.getView().findViewById(R.id.msg)).setText(msg);
        toast.setDuration(duration);
        return toast;
    }

    private BigToast() {

    }
}
