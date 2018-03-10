package com.acchain.community.window;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by rsp on 2018/1/19.
 */

public class OpenRedPacketDialog extends Dialog {
    @BindView(R.id.close)
    ImageView close;
    @BindView(R.id.headImg)
    ImageView headImg;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.open)
    ImageView open;
    @BindView(R.id.title)
    TextView title;
    private Window window;
    private String senderNikeName;
    private String redPacketTitle;
    private WindowManager.LayoutParams attributesParams;
    public interface OnOpenListener{
       void onOpen(Dialog dialog);
    }

    public OnOpenListener onOpenListener;
    public OpenRedPacketDialog(@NonNull Context context, String senderNikeName, String redPacketTitle,String headImgStr) {
        super(context);
        window = getWindow();
        window.requestFeature(Window.FEATURE_NO_TITLE);
        this.senderNikeName = senderNikeName;
        this.redPacketTitle = redPacketTitle;
        setContentView(R.layout.dialog_open_red_packet);
        ButterKnife.bind(this);
        title.setText(redPacketTitle);
        name.setText(senderNikeName);
        Glide.with(this.getContext()).asDrawable().apply(new RequestOptions().centerCrop()).load(headImgStr).into(headImg);

    }

    public void setOnOpenListener(OnOpenListener onOpenListener) {
        this.onOpenListener = onOpenListener;
    }

    @Override
    public void show() {
        super.show();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams
                .FLAG_FULLSCREEN);
        attributesParams = window.getAttributes();
        attributesParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        attributesParams.dimAmount = 0.4f;
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);

    }

    public View getShareView() {
        return open;
    }
    @OnClick({R.id.close, R.id.name, R.id.open})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.close:
                dismiss();
                break;
            case R.id.name:

                break;
            case R.id.open:
                if (onOpenListener != null) {
                    AnimationDrawable animationDrawable = (AnimationDrawable)open.getBackground();
                    animationDrawable.start();
                    onOpenListener.onOpen(this);
                }
                break;
        }
    }
}
