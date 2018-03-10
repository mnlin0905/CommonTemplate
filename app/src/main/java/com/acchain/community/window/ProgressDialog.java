package com.acchain.community.window;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.acchain.community.R;
import com.blankj.utilcode.util.ScreenUtils;

/**
 * Created on 2018/2/5
 * function : 加载进度框
 *
 * @author ACChain
 */

public class ProgressDialog extends Dialog {
    private static ProgressDialog singleInstance;
    private Context context;
    private View rootView;
    private String progressText;
    private TextView mTvText;

    /**
     * Creates a dialog window that uses the default dialog theme.
     * <p>
     * The supplied {@code context} is used to obtain the window manager and
     * base theme used to present the dialog.
     *
     * @param context the context in which the dialog should run
     * @see android.R.styleable#Theme_dialogTheme
     */
    public ProgressDialog(@NonNull Context context) {
        this(context, R.style.ActivityBottomView);
    }

    /**
     * Creates a dialog window that uses a custom dialog style.
     * <p>
     * The supplied {@code context} is used to obtain the window manager and
     * base theme used to present the dialog.
     * <p>
     * The supplied {@code theme} is applied on top of the context's theme. See
     * <a href="{@docRoot}guide/topics/resources/available-resources.html#stylesandthemes">
     * Style and Theme Resources</a> for more information about defining and
     * using styles.
     *
     * @param context    the context in which the dialog should run
     * @param themeResId a style resource describing the theme to use for the
     *                   window, or {@code 0} to use the default dialog theme
     */
    public ProgressDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
        init();
    }

    public synchronized static ProgressDialog getInstance(@NonNull Context context) {
        if (singleInstance == null) {
            singleInstance = new ProgressDialog(context);
        } else if (context != singleInstance.getSelfContext()) {
            singleInstance.dismiss();
            singleInstance = new ProgressDialog(context);
        }
        return singleInstance;
    }

    /**
     * @return 获取自身上下文
     */
    public Context getSelfContext() {
        return context;
    }


    /**
     * 设置文本
     */
    public ProgressDialog setMessage(String text) {
        progressText = text;
        return this;
    }

    /**
     * 初始化界面
     */
    private void init() {
        //初始化界面
        rootView = LayoutInflater.from(context).inflate(R.layout.dialog_fragment_progress, null);
        mTvText = rootView.findViewById(R.id.tv_text);
    }

    @Override
    public synchronized void dismiss() {
        super.dismiss();
        context=null;
    }

    @Override
    public synchronized void show() {
        try {
            if (!isShowing()) {
                mTvText.setText(progressText);
                Window window = getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.y = context.getResources().getDimensionPixelSize(R.dimen.view_padding_margin_48dp);
                attributes.gravity = Gravity.BOTTOM;
                window.setAttributes(attributes);
                window.setWindowAnimations(com.acchain.community.R.style.ActivityBottomViewAnimation);
                window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                ViewGroup.LayoutParams params = new FrameLayout.LayoutParams((int) (ScreenUtils.getScreenWidth() * 0.75),
                        ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
                setContentView(rootView, params);
                setCanceledOnTouchOutside(false);
                super.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
