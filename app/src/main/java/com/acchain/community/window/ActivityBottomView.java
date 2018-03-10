package com.acchain.community.window;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;


/**
 * Created by admin on 2017/4/25.
 * <p>
 * 顶部进度框
 */

public class ActivityBottomView extends Dialog {
    private static final String TAG = "ActivityBottomView";

    private Context context;
    private View dialogView;

    /**
     * @param context 上下文
     */
    public ActivityBottomView(Context context, View dialogView) {
        super(context, com.acchain.community.R.style.ActivityBottomView);
        //这里一定要有这个，否则无法保证弹出框全屏
        setOwnerActivity((Activity) context);
        this.context = context;
        this.dialogView = dialogView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void show() {
        super.show();
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.alpha = 1F;
        //这标志表示含义为：所有在该弹出框后的内容都将模糊不清，即显示遮罩窗体
        params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(params);
        //这里一定要有这个，否则无法保证弹出框全屏，但这两个属性会导致弹出框不能再引起adjustResize，因此输入法会遮挡弹出框
//		window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
//		window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        window.setGravity(Gravity.BOTTOM);
        //这里可能需要布局文件的height为wrap_content，否则即便修改为View.MeasureSpec.AT_MOST模式也会占用所有的区域
        int preferHeight = context.getResources().getDisplayMetrics().heightPixels / 2;
//		int maxHeight=View.MeasureSpec.makeMeasureSpec(preferHeight, View.MeasureSpec.AT_MOST);
//		Log.v(TAG,"该底部view最大可设置的高度为："+preferHeight+"    info:"+maxHeight);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams
                .FLAG_FULLSCREEN);
        //这里虽然设置为Match_Parent，但可能系统内部获取屏幕高度时，没有包括状态栏
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setDimAmount(0.35F);
        window.setWindowAnimations(com.acchain.community.R.style.ActivityBottomViewAnimation);
        ViewGroup.LayoutParams p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, preferHeight);
        setContentView(dialogView, p);
    }
}
