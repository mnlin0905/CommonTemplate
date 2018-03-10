package com.acchain.community.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acchain.community.R;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created on 2018/2/1
 * function : 仿物流记录界面
 *
 * @author ACChain
 */

public final class FlowLinearLayout extends LinearLayout {
    private final LayoutParams layoutParams;
    ArrayList<ItemView> childAll;
    private Paint paint;
    /**
     * 在添加子view时,禁止刷新布局,避免大量耗时操作
     */
    private boolean disableRequestLayout;
    private List<FlowModel> datas;
    private long PER_ANIMATOR_TIME=200;
    private long repeat;

    public FlowLinearLayout(Context context) {
        this(context, null);
    }

    public FlowLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //设置垂直布局,创建绘图对象,清除已有的背景
        setOrientation(VERTICAL);
        paint = new Paint();
        childAll = new ArrayList<>();
        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    /**
     * 添加数据源
     */
    public FlowLinearLayout addDatas(List<FlowModel> datas) {
        disableRequestLayout = true;

        //如果有子布局,则全部移除
        if (getChildCount() != 0) {
            ((ViewGroup) getParent()).removeAllViews();
        }

        if (datas == null || datas.size() == 0) {
            return this;
        }

        ItemView child;
        for (FlowModel data : datas) {
            //添加view
            child = new ItemView(getContext());

            //绑定数据
            child.mCbStatus.setChecked(data.isChecked());
            child.mTvBrief.setText(data.getBrief());
            child.mTvTime.setText(data.getTime());

            childAll.add(child);
        }

        //当显示后,一个一个添加,避免同时操作大量数据出现卡顿
        Observable.fromIterable(childAll)
                .map(itemView -> {
                    Thread.sleep(repeat==0?PER_ANIMATOR_TIME:repeat);
                    return itemView;
                })
                .subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(itemView -> addView(itemView, layoutParams));

        return this;
    }

    /**
     * 添加数据源
     */
    public void addDatas(List<FlowModel> datas,boolean delay) {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                disableRequestLayout = true;

                //如果有子布局,则全部移除
                if (getChildCount() != 0) {
                    ((ViewGroup) getParent()).removeAllViews();
                }

                if (datas == null || datas.size() == 0) {
                    return;
                }

                ItemView child;
                for (FlowModel data : datas) {
                    //添加view
                    child = new ItemView(getContext());

                    //绑定数据
                    child.mCbStatus.setChecked(data.isChecked());
                    child.mTvBrief.setText(data.getBrief());
                    child.mTvTime.setText(data.getTime());
                    addView(child,layoutParams);
                }
            }
        }, repeat==0?PER_ANIMATOR_TIME:repeat);
    }

    /**
     * 设置动画间隔时间
     */
    public FlowLinearLayout setPerTime(long repeat){
        this.repeat = repeat;
        return this;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public void requestLayout() {
        super.requestLayout();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        return super.drawChild(canvas, child, drawingTime);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        drawLine(canvas);
        super.dispatchDraw(canvas);
    }

    /**
     * 划线处理
     */
    private void drawLine(Canvas canvas) {
        int childCount = getChildCount();

        //如果子布局数量大于1,则绘制连线
        if (childCount > 1) {
            ItemView first = (ItemView) getChildAt(0);
            ItemView last = (ItemView) getChildAt(childCount - 1);

            CheckBox icon_first = first.getIconView();
            CheckBox icon_last = last.getIconView();
            float startX = (float) ((icon_first.getLeft() + icon_first.getRight()) / 2.0);
            float startY = (float) ((icon_first.getTop() + icon_first.getBottom()) / 2.0);
            int divider = last.getTop();
            float endX = (float) ((icon_last.getLeft() + icon_last.getRight()) / 2.0);
            float endY = (float) (divider + (icon_last.getTop() + icon_last.getBottom()) / 2.0);

            int line_color = getResources().getColor(R.color.gray_background_404156);
            int line_width = getResources().getDimensionPixelSize(R.dimen.view_height_4dp);

            paint.setAntiAlias(true);
            paint.setColor(line_color);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(line_width);
            canvas.drawLine(startX, startY, endX, endY, paint);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    /**
     * 数据模型,用于填充数据
     */
    public static class FlowModel {
        private boolean checked;
        private CharSequence brief;
        private CharSequence time;

        public FlowModel(boolean checked, CharSequence brief, CharSequence time) {
            this.checked = checked;
            this.brief = brief;
            this.time = time;
        }

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }

        public CharSequence getBrief() {
            return brief;
        }

        public void setBrief(CharSequence brief) {
            this.brief = brief;
        }

        public CharSequence getTime() {
            return time;
        }

        public void setTime(CharSequence time) {
            this.time = time;
        }
    }

    /**
     * 每一个子布局的结构
     */
    static class ItemView extends ConstraintLayout {

        @BindView(R.id.tv_brief)
        TextView mTvBrief;
        @BindView(R.id.tv_time)
        TextView mTvTime;
        @BindView(R.id.cb_status)
        CheckBox mCbStatus;

        public ItemView(Context context) {
            this(context, null);
        }

        public ItemView(Context context, AttributeSet attrs) {
            this(context, attrs, 0);
        }

        public ItemView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);

            //初始化
            initData();
            setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            setBackground(null);
        }

        /**
         * 初始化
         */
        private void initData() {
            //加载布局
            inflate(getContext(), R.layout.layout_flow_linear_group, this);

            ButterKnife.bind(this);
        }

        /**
         * 获取每个item中icon图标布局的位置
         */
        CheckBox getIconView() {
            return mCbStatus;
        }
    }
}
