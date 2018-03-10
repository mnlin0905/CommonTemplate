package com.acchain.community.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.jungly.gridpasswordview.GridPasswordView;

/**
 * Created on 2018/1/13
 * function : 定义的密码框,实现高度与宽度相同
 *
 * @author ACChain
 */

public class PasswordView extends GridPasswordView {
    /**
     * 密码长度
     */
    private int passwordLength = -1;

    public PasswordView(Context context) {
        this(context, null);
    }

    public PasswordView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PasswordView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public PasswordView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        //获取密码的长度
        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, com.jungly.gridpasswordview.R.styleable.gridPasswordView, defStyleAttr, 0);
            passwordLength = array.getInt(com.jungly.gridpasswordview.R.styleable.gridPasswordView_gpvPasswordLength, 6);
            array.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (passwordLength != -1) {
            //获取密码框长度
            int measuredWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
            int height = measuredWidth / passwordLength;
            height = height <= 0 ? getMeasuredHeight() : height;
            setMeasuredDimension(getMeasuredWidth(), height);
            super.onMeasure(widthMeasureSpec,MeasureSpec.makeMeasureSpec(height,MeasureSpec.EXACTLY));
        }
    }

    /**
     * @return 获取密码长度
     */
    public int getPasswordLength(){
        return passwordLength;
    }
}
