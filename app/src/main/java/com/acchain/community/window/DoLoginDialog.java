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
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseEvent;
import com.acchain.community.rxbus.RxBus;
import com.acchain.community.util.Const;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ScreenUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created on 2018/2/5
 * function : 登录超时弹出框
 *
 * @author ACChain
 */

public class DoLoginDialog extends Dialog {
    /**
     * 保持同一个activity只会弹出一个窗口
     */
    private static DoLoginDialog singleInstance;

    @BindView(R.id.iv_close)
    ImageView mIvClose;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_confirm)
    TextView mTvConfirm;
    private View rootView;

    private Context context;

    /**
     * Creates a dialog window that uses the default dialog theme.
     * <p>
     * The supplied {@code context} is used to obtain the window manager and
     * base theme used to present the dialog.
     *
     * @param context the context in which the dialog should run
     */
    private DoLoginDialog(@NonNull Context context) {
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
    public DoLoginDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
        init();
    }

    public synchronized static DoLoginDialog getInstance(@NonNull Context context) {
        if (singleInstance == null || context != singleInstance.getSelfContext()) {
            singleInstance = new DoLoginDialog(context);
        }
        return singleInstance;
    }

    /**
     * @return 获取自身上下文
     */
    public Context getSelfContext(){
        return context;
    }

    /**
     * 初始化界面
     */
    private void init() {
        //初始化界面
        rootView = LayoutInflater.from(context).inflate(R.layout.dialog_fragment_alert_close, null);
        ButterKnife.bind(this, rootView);
        mTvTitle.setText("登录超时,请重新登录");
        mTvConfirm.setText("去登录");
    }

    @OnClick({R.id.iv_close, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                break;
            case R.id.tv_confirm:
                ARouter.getInstance().build(ARouterConst.Activity_LoginActivity).navigation();
                break;
        }

        dismiss();
    }

    @Override
    public synchronized void dismiss() {
        super.dismiss();
    }

    @Override
    public synchronized void show() {
        try {
            if (!isShowing()) {
                Window window = getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.gravity = Gravity.CENTER;
                window.setAttributes(attributes);
                window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                ViewGroup.LayoutParams params = new FrameLayout.LayoutParams((int) (ScreenUtils.getScreenWidth() * 0.75),
                        ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
                setContentView(rootView, params);
                setCancelable(false);
                super.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            RxBus.getInstance().post(new BaseEvent(Const.SHOW_TOAST, "请重新登录"));
        }
    }
}
