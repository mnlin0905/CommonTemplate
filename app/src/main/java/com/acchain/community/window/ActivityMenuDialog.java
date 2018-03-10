package com.acchain.community.window;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ArrayRes;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acchain.community.R;


/**
 * Created by admin on 2017/4/11.
 */

public class ActivityMenuDialog extends Dialog implements View.OnClickListener {
    private static final String TAG = "ActivityMenuDialog";
    private Context context;
    private OnItemClickListener onItemClickListener;
    private String[] titles;
    private LinearLayout dialogView;
    private TextView tv_cancel;

    /**
     * 接口类，供外部函数调用
     */
    public interface OnItemClickListener {
        /**
         * 当item被点击时，执行的方法
         *
         * @return 当返回true时，表示不在执行该对象默认的操作
         */
        public abstract boolean onItemClick(ActivityMenuDialog dialog, int position);
    }

    /**
     * 载入监听器
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    /**
     * @param context 上下文
     * @param resId   资源id，即下方需要显示的菜单的对应的String数组
     */
    public ActivityMenuDialog(Context context, @ArrayRes int resId, OnItemClickListener listener) {
        this(context, context.getResources().getStringArray(resId), listener);
    }

    public ActivityMenuDialog(Context context, String[] titles, OnItemClickListener listener) {
        super(context, R.style.ActivityBottomView);
        this.context = context;
        this.onItemClickListener = listener;
        this.titles = titles;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //生成布局文件
        dialogView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout
                .dialog_activity_menu, null);
        tv_cancel = dialogView.findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(v -> dismissDialog());
        int length = titles.length;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int offset = 0;
        for (int i = 0; i < length; i++) {
            TextView tv = new TextView(context);
            /*设置居中，粗体，*/
            tv.setGravity(Gravity.CENTER);
            tv.setText(titles[i]);
            tv.setTypeface(Typeface.DEFAULT_BOLD);
            tv.setMinHeight(context.getResources().getDimensionPixelSize(R.dimen.prefer_view_height_48dp));
            tv.setTag(i);
            dialogView.addView(tv, offset++, params);
            tv.setOnClickListener(this);

            //添加分割线
            if (length > 2 && i < length - 1) {
                ImageView iv = new ImageView(context);
                if (Build.VERSION.SDK_INT < 23) {
                    iv.setBackground(new ColorDrawable(context.getResources().getColor(R.color.colorAccentHacker)));
                } else {
                    iv.setBackground(new ColorDrawable(context.getResources().getColor(R.color.colorAccentHacker, null)));
                }
                dialogView.addView(iv, offset++, new LinearLayout.LayoutParams(ViewGroup.LayoutParams
                        .MATCH_PARENT, 1));
            }
        }
        Log.v(TAG, "初始化完成：菜单数量" + length);
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
        window.setGravity(Gravity.BOTTOM);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams
                .WRAP_CONTENT);
        window.setDimAmount(0.35F);
        window.setWindowAnimations(R.style.ActivityBottomViewAnimation);
        setContentView(dialogView);
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null) {
            if (!onItemClickListener.onItemClick(this, (int) v.getTag())) {
                //如果返回false，表示不中断默认的操作
                dismissDialog();
            }
        }
    }

    /**
     * 当点击“取消”时，关闭弹出框
     */
    public void dismissDialog() {
        dismiss();
    }
}
