package com.acchain.community.view;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created on 2018/1/24
 * function : 可以禁止点击事件的tabLayout
 *
 * @author ACChain
 */

public class DisallowClickTabLayout extends TabLayout {

    private ManageClickInterface manageClickInterface;

    public DisallowClickTabLayout(Context context) {
        super(context);
    }

    public DisallowClickTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DisallowClickTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if ((manageClickInterface == null || !manageClickInterface.currentCanClick())&&ev.getAction() == MotionEvent.ACTION_UP) {
            //当手指抬起时,拦截掉事件,之后不做任何处理
            return true;
        }
        //如果前面条件不满足,则默认会采取拦截点击事件处理
        return super.onInterceptTouchEvent(ev);
    }

    public void setManageClickInterface(ManageClickInterface manageClickInterface) {
        this.manageClickInterface = manageClickInterface;
    }

    /**
     * 控制接口
     */
    public interface ManageClickInterface {
        /**
         * @return true表示当前不拦截任何事件
         */
        boolean currentCanClick();
    }
}
