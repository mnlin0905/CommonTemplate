package com.acchain.community.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

import com.acchain.community.R;

/**
 * Created on 2018/2/6
 * function : 实现上拉下拉的recyclerView
 *代理实现,真正布局为SwipeRefreshLayout
 * @author ACChain
 */

public class PullPushAdapterView extends SwipeRefreshLayout {
    /**
     * Simple constructor to use when creating a SwipeRefreshLayout from code.
     *
     * @param context
     */
    public PullPushAdapterView(Context context) {
        this(context,null);
    }

    /**
     * Constructor that is called when inflating SwipeRefreshLayout from XML.
     *
     * @param context
     * @param attrs
     */
    public PullPushAdapterView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //加载布局,初始化界面
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        //布局
        inflate(getContext(), R.layout.layout_pull_push_layout,this);


    }


}
