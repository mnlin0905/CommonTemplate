package com.acchain.community.view;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acchain.community.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 2018/2/1
 * function : 仿物流系统的线路显示
 *
 * @author ACChain
 */

public class FlowConstraintLayout extends ConstraintLayout {

    private ViewHolder headHolder;

    public FlowConstraintLayout(Context context) {
        this(context, null);
    }

    public FlowConstraintLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        //加载布局
        inflate(getContext(), R.layout.layout_flow_constraint, this);

        //开始一组的布局
        headHolder=new ViewHolder();
        ButterKnife.bind(headHolder,this);
    }

    /**
     * 添加子布局内容
     *
     * @param o 数据源
     */
    private void addItemContent(Object o) {
        View one = provideDateView();
    }

    class ViewHolder {
        @BindView(R.id.tv_date)
        TextView mTvDate;
        @BindView(R.id.tv_time)
        TextView mTvTime;
        @BindView(R.id.ll_child_one)
        LinearLayout mLlChildOne;
        @BindView(R.id.iv_icon)
        ImageView mIvIcon;
        @BindView(R.id.tv_status)
        TextView mTvStatus;
        @BindView(R.id.tv_brief)
        TextView mTvBrief;
    }

    /**
     * @return 提供一个时间,日期的布局
     */
    private View provideDateView(){
        return null;
    }

    /**
     * @return 提供一个icon
     */
    private View provideIconView(){
        return null;
    }

    /**
     * @return 提供一个包含节点,简单说明文字的布局
     */
    private View provideInfoView(){
        return null;
    }
}
