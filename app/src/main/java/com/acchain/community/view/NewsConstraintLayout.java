package com.acchain.community.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.util.ScreenUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.nekocode.badge.BadgeDrawable;

/**
 * Created on 2018/1/23
 * function : 消息布局
 *
 * @author ACChain
 */

public class NewsConstraintLayout extends ConstraintLayout {
    /**
     * 设置图片最大为64dp
     */
    private static final int MAX_PICTURE_SIZE = 64;
    private final AttributeSet attrs;
    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tv_subtitle)
    TextView mTvSubtitle;
    @BindView(R.id.tv_badge)
    TextView mTvBadge;
    private BadgeDrawable drawable;

    public NewsConstraintLayout(Context context) {
        this(context, null);
    }

    public NewsConstraintLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NewsConstraintLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.attrs = attrs;

        inflate(context, R.layout.item_news, this);
        ButterKnife.bind(this,this);

        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.view_padding_margin_16dp);
        setPadding(dimensionPixelSize,dimensionPixelSize,dimensionPixelSize,dimensionPixelSize);

        initData();
    }

    private void initData() {
        TypedArray params = getContext().obtainStyledAttributes(attrs, R.styleable.NewsConstraintLayout);

        for (int i = params.getIndexCount() - 1; i >= 0; i--) {
            int resId = params.getIndex(i);
            switch (resId) {
                //icon
                case R.styleable.NewsConstraintLayout_NewsLinearLayout_icon: {
                    Drawable icon = params.getDrawable(resId);
                    if (icon != null) {
                        icon.setBounds(0, 0,
                                Math.min(icon.getMinimumWidth(), ((int) (MAX_PICTURE_SIZE * ScreenUtils.density))),
                                ((int) (MAX_PICTURE_SIZE * ScreenUtils.density)));
                        mIvIcon.setImageDrawable(icon);
                    }
                    break;
                }

                //title
                case R.styleable.NewsConstraintLayout_NewsLinearLayout_title: {
                    mTvTitle.setText(params.getString(resId));
                    break;
                }

                //time
                case R.styleable.NewsConstraintLayout_NewsLinearLayout_time: {
                    mTvTime.setText(params.getString(resId));
                    break;
                }

                //subtitle
                case R.styleable.NewsConstraintLayout_NewsLinearLayout_subtitle: {
                    mTvSubtitle.setText(params.getString(resId));
                    break;
                }
            }
        }
    }

    public NewsConstraintLayout setIvIcon(Drawable icon) {
        mIvIcon.setImageDrawable(icon);
        return this;
    }

    public NewsConstraintLayout setIvIcon(@DrawableRes int resId) {
        mIvIcon.setImageResource(resId);
        return this;
    }

    public NewsConstraintLayout setTitle(String title) {
        mTvTitle.setText(title);
        return this;
    }

    public NewsConstraintLayout setTime(String time) {
        mTvTime.setText(time);
        return this;
    }

    public NewsConstraintLayout setSubtitle(String subtitle) {
        mTvSubtitle.setText(subtitle);
        return this;
    }

    /**
     * @param number     数字
     * @param textColor  字体颜色
     * @param badgeColor 背景颜色
     */
    public NewsConstraintLayout setBadge(int number, int textColor, int badgeColor) {
        drawable = new BadgeDrawable.Builder()
                .type(BadgeDrawable.TYPE_NUMBER)
                .number(number)
                .badgeColor(badgeColor)
                .textColor(textColor)
                .build();
        mTvBadge.setText(drawable.toSpannable());
        return this;
    }

    public ImageView getmIvIcon() {
        return mIvIcon;
    }

    public TextView getmTvTitle() {
        return mTvTitle;
    }

    public TextView getmTvTime() {
        return mTvTime;
    }

    public TextView getmTvSubtitle() {
        return mTvSubtitle;
    }
}

