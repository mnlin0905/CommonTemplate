package com.acchain.community.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;

import com.acchain.community.R;
import com.bigkoo.pickerview.lib.WheelView;

import java.lang.reflect.Field;

/**
 * Created on 2018/1/12
 * function : 自定义line高度(通过设置不同间距来实现)
 *
 * @author ACChain
 */

public class CustomLineWheelView extends WheelView {

    /**
     * 间距
     */
    private float lineSpacingMul;

    public CustomLineWheelView(Context context) {
        this(context, null);
    }

    public CustomLineWheelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.pickerview, 0, 0);
            lineSpacingMul = a.getFloat(R.styleable.pickerview_pickerview_lineSpacingMultiplier, 1.6F);
            setLineSpacingMultiplier(lineSpacingMul);
            a.recycle();//回收内存
        }
    }

    @Override
    public void setLineSpacingMultiplier(float multiplier) {
        /*if (multiplier < lineSpacingMul) {
            multiplier = lineSpacingMul;
        }*/
        Class clazz = getClass().getSuperclass();
        try {
            Field field = clazz.getDeclaredField("lineSpacingMultiplier");
            field.setAccessible(true);
            field.set(this, multiplier);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setGravity(int gravity) {
        switch (getId()){
            case R.id.year:{
                super.setGravity(Gravity.CENTER);
                break;
            }
            case R.id.month:{
                super.setGravity(Gravity.CENTER);
                break;
            }
            default:{
                super.setGravity(Gravity.CENTER);
            }
        }
    }
}
