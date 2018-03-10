package com.common.template.base;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.common.template.R;
import com.common.template.dagger.component.ActivityComponent;
import com.common.template.dagger.component.DaggerActivityComponent;
import com.common.template.dagger.module.ActivityModule;
import com.common.template.drawable.ColorTextDrawable;
import com.common.template.rxbus.RxBus;
import com.common.template.util.ActivityUtil;
import com.common.template.util.Const;
import com.jaeger.library.StatusBarUtil;
import com.orhanobut.logger.Logger;

import java.util.Stack;

import javax.inject.Inject;

import butterknife.ButterKnife;

import static android.view.KeyEvent.KEYCODE_MENU;

/**
 * Created by Administrator on 17-1-22.
 *
 * @param <T> MVP模式中,presenter类,
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {
    /**
     * 所有的插件列表,在子activity中可以关闭:
     * <p>
     * 0 : butterknife 自动绑定view和click
     * 1 : Arouter 自动赋值
     */
    protected static final int PLUGIN_BUTTER_KNIFE = 0x00000001;
    protected static final int PLUGIN_ROUTER = 0x00000002;

    public Toolbar toolbar;

    protected ActivityComponent activityComponent;

    @Inject
    protected T mPresenter;

    protected CharSequence activityTitle;

    /**
     * 默认所有插件开关为打开状态
     */
    private int PLUGIN_FLAGS = 0xFFFFFFFF;

    /**
     * 使用dagger注入自身
     */
    protected abstract void injectSelf();

    /**
     * @return 获取布局文件
     */
    @LayoutRes
    protected abstract int getContentViewId();

    /**
     * 初始化数据
     *
     * @param savedInstanceState 已存储对象
     */
    protected abstract void initData(Bundle savedInstanceState);

    @Override
    @SuppressWarnings("all")
    final protected void onCreate(Bundle savedInstanceState) {
        if (BaseApplication.isStrictMode) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads()
                    .detectDiskWrites().detectNetwork().penaltyLog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
        }
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //设置支持动画过渡效果
        ActivityUtil.setActivityContentTransitions(this);
        ActivityUtil.setActivitySupportTransitions(this);
        /*getWindow().setExitTransition(new Fade());*/

        //设置内容全屏
        ActivityUtil.setDecorTransparent(this);

        //设置statubar的颜色
        ActivityUtil.setStatusBarColor(this, getResources().getColor(R.color.transparent));

        //添加布局
        if(getContentViewId()!=0){
            setContentView(getContentViewId());
        }
        if ((PLUGIN_FLAGS & PLUGIN_BUTTER_KNIFE) == PLUGIN_BUTTER_KNIFE) {
            ButterKnife.bind(this);
        }

        //设置ContentFrameLayout的第一个子view,即当前xml文件对应根布局FitsSystemWindows为true
        ViewGroup contentFrameLayout = getWindow().getDecorView().findViewById(android.R.id.content);
        contentFrameLayout.getChildAt(0).setFitsSystemWindows(true);

        //设置toolbar和statusBar颜色;当点击navigation时默认退出活动
        //设置toolbar与activity绑定
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(v -> finish());
        }

        //注入dagger框架
        activityComponent = DaggerActivityComponent.builder().applicationComponent(BaseApplication.getApplicationComponent()).activityModule(new ActivityModule(this)).build();
        injectSelf();

        //注入路由Arouter框架
        if ((PLUGIN_FLAGS & PLUGIN_ROUTER) == PLUGIN_ROUTER) {
            ARouter.getInstance().inject(this);
        }

        //绑定presenter和activity
        if (mPresenter != null) {
            mPresenter.mView = this;
        }

        //获取title值
        activityTitle = getTitle();

        //初始化内容
        initData(savedInstanceState);
    }

    /**
     * 设置状态栏颜色
     */
    @SuppressWarnings("all")
    protected void setStatusBarColor(int color) {
        if (ActivityUtil.getSDKVersion() >= 21) {
            getWindow().setStatusBarColor(color);
        } else {
            StatusBarUtil.setColor(this, color);
        }
    }

    /**
     * 设置导航栏颜色
     */
    protected void setToolbarColor(int color) {
        toolbar.setBackgroundColor(color);
    }

    /**
     * 将一个已有的颜色值加深
     */
    private int getDeepColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        //改变亮度
        hsv[2] = (float) (hsv[2] * 0.8);
        return Color.HSVToColor(hsv);
    }

    /**
     * @param msg 需要显示的toast消息
     */
    public void showToast(String msg) {
        RxBus.getInstance().post(new BaseEvent(Const.SHOW_TOAST, msg == null ? "null" : msg));
    }

    /**
     * 显示snackbar
     */
    protected void showSnackbar(String msg, String button, View.OnClickListener onClickButton) {
        Snackbar singleSnackbar = Snackbar.make(toolbar == null ? findViewById(android.R.id.content) : toolbar, msg, Snackbar.LENGTH_INDEFINITE);
        ((TextView) singleSnackbar.getView().findViewById(android.support.design.R.id.snackbar_text)).setMaxLines(10);
        ((TextView) singleSnackbar.getView().findViewById(android.support.design.R.id.snackbar_text)).setLineSpacing(0, 1.5F);
        singleSnackbar.getView().setAlpha(0.9f);
        singleSnackbar.setActionTextColor(getThemeColorAttribute(com.common.template.R.style.TextInputLayout_HintTextAppearance_Hacker, android.R.attr.textColor));
        singleSnackbar.setAction(button, v -> {
            if (onClickButton != null) {
                onClickButton.onClick(v);
            }
            singleSnackbar.dismiss();
        }).show();
        singleSnackbar.getView().setOnClickListener(v -> singleSnackbar.dismiss());
    }


    /**
     * 当点击menu键时屏蔽任何操作
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KEYCODE_MENU) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void startActivity(Intent intent) {
        if (Build.VERSION.SDK_INT >= 21) {
            super.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        } else {
            super.startActivity(intent);
        }
    }

    @Override
    public void startActivity(Intent intent, @Nullable Bundle options) {
        super.startActivity(Intent.createChooser(intent, "选择一个应用"), options);
    }

    /**
     * 获取drawable
     */
    public Drawable dispatchGetDrawable(@DrawableRes int resId) {
        if (Build.VERSION.SDK_INT >= 21) {
            return getDrawable(resId);
        } else {
            return getResources().getDrawable(resId);
        }
    }

    /**
     * 获取颜色值
     */
    final public int dispatchGetColor(@ColorRes int resId) {
        if (Build.VERSION.SDK_INT < 23) {
            return getResources().getColor(resId);
        } else {
            return getResources().getColor(resId, null);
        }
    }

    /**
     * 获取系统属性中某个值
     */
    public int getThemeColorAttribute(int styleRes, int colorId) {
        int defaultColor = dispatchGetColor(com.common.template.R.color.transparent);
        int[] attrsArray = {colorId};
        TypedArray typedArray = obtainStyledAttributes(styleRes, attrsArray);
        int color = typedArray.getColor(0, defaultColor);

        typedArray.recycle();
        return color;
    }

    /**
     * 当activity销毁时候,关闭资源
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.removeDisposable();
            mPresenter.mView = null;
        }
    }

    /**
     * 需要在oncreate之前就设置,否则不起作用
     *
     * @return 用于关闭插件
     */
    final protected void disablePlugin(int plugin) {
        PLUGIN_FLAGS &= ~plugin;
    }

    /**
     * 需要在oncreate之前就设置,否则不起作用
     *
     * @return 用于关闭插件
     */
    final protected void enablePlugin(int plugin) {
        PLUGIN_FLAGS |= plugin;
        mPresenter.mView = null;
    }

    /**
     * 管理activity实例
     */
    public void manageAddActivity() {
        boolean isExistStack = false;
        for (int i = 0; i < BaseApplication.activityManager.size(); i++) {
            Stack<BaseActivity> temp = BaseApplication.activityManager.get(i);
            if (temp.get(0).getTaskId() == getTaskId()) {
                temp.push(this);
                isExistStack = true;
                break;
            }
        }
        if (!isExistStack) {
            Stack<BaseActivity> stack = new Stack<>();
            stack.push(this);
            BaseApplication.activityManager.add(stack);
        }
    }

    /**
     * 移除需要销毁的activity实例
     */
    public void manageRemoveActivity() {
        for (int i = 0; i < BaseApplication.activityManager.size(); i++) {
            Stack<BaseActivity> temp = BaseApplication.activityManager.get(i);
            if (temp.get(0).getTaskId() == getTaskId()) {
                temp.pop();
                if (temp.size() == 0) {
                    BaseApplication.activityManager.remove(temp);
                }
                break;
            }
        }
    }

    /**
     * 打印活动栈
     */
    protected void logStack() {
        for (int i = 0; i < BaseApplication.activityManager.size(); i++) {
            Stack<BaseActivity> temp = BaseApplication.activityManager.get(i);
            Logger.v(getClass().getSimpleName() + " : " + "\n栈id：" + temp.get(0).getTaskId() + "\n栈底->" + temp.toString() + "栈顶");
        }
    }

    /**
     * 设置toolbar的navigation为text
     */
    protected void setToolbarNavText(Toolbar t, String text, @ColorRes int color) {
        t.post(() -> {
            ColorTextDrawable textDrawable = new ColorTextDrawable(getBaseContext())
                    .setText(text)
                    .setColor(dispatchGetColor(color))
                    .setTextSize(getResources().getDimensionPixelSize(R.dimen.text_size_title_15sp));
            textDrawable.setBounds(0, 0, textDrawable.getIntrinsicWidth(), textDrawable.getIntrinsicHeight());
            t.setNavigationIcon(textDrawable);
        });
    }

    @Override
    public FragmentManager getSFManager() {
        synchronized (BaseView.class) {
            return getSupportFragmentManager();
        }
    }

    @Override
    public BaseActivity getBaseActivity() {
        return this;
    }
}
