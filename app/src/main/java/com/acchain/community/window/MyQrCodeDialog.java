package com.acchain.community.window;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;

import butterknife.BindView;

/**
 * Created by rsp on 2018/1/16.
 */

public class MyQrCodeDialog extends Dialog {
    @BindView(R.id.nikeName)
    TextView nikeName;
    @BindView(R.id.location)
    TextView location;
    @BindView(R.id.qrcode)
    ImageView qrcode;
    private Window window;
    private WindowManager.LayoutParams attributesParams;
    public MyQrCodeDialog(@NonNull Context context) {
        super(context);
        window = getWindow();
        window.requestFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    public void show() {
        super.show();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams
                .FLAG_FULLSCREEN);
        attributesParams = window.getAttributes();
        attributesParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        attributesParams.dimAmount = 0.4f;
        int sreemWidth = window.getWindowManager().getDefaultDisplay().getWidth();
        int windowWidth = (int) (sreemWidth * 0.75);
        window.setLayout(windowWidth, WindowManager.LayoutParams.WRAP_CONTENT);
        setContentView(R.layout.dialog_qr_code);
    }
}
