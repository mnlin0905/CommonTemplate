package com.acchain.community.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acchain.community.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 2018/1/26
 * function : 菜单按钮,包含title,subtitle.icon,navigation
 *
 * @author ACChain
 */

public class MenuTitleSubTitleView extends LinearLayout {
    /**
     * 设置图片最大为36dp
     */
    private static final int MAX_PICTURE_SIZE = 36;
    /**
     * 默认动画执行的时间:单位ms
     */
    private static final int DEFAULT_ANIMATOR_TIME = 300;
    private final AttributeSet attrs;
    @BindView(R.id.iv_icon)
    ImageView mIvIcon;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_subtitle)
    TextView mTvSubtitle;
    @BindView(R.id.iv_navigation)
    ImageView mIvNavigation;


    public MenuTitleSubTitleView(Context context) {
        this(context, null);
    }

    public MenuTitleSubTitleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MenuTitleSubTitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        inflate(getContext(), R.layout.layout_menu_title_subtitle, this);
        setGravity(Gravity.CENTER_VERTICAL);
        ButterKnife.bind(this);
        this.attrs = attrs;

        //解析xml中配置信息
        initAttribute();
    }

    /**
     * 初始化配置信息
     */
    private void initAttribute() {
        TypedArray params = getContext().obtainStyledAttributes(attrs, R.styleable.MenuTitleSubTitleView);

        for (int i = params.getIndexCount() - 1; i >= 0; i--) {
            int resId = params.getIndex(i);
            switch (resId) {
                //处理icon图标
                case R.styleable.MenuTitleSubTitleView_MenuTitleSubTitleView_icon: {
                    int resourceId = params.getResourceId(resId, 0);
                    if (resourceId != 0) {
                        mIvIcon.setImageResource(resourceId);
                    }
                    /*Drawable d = params.getDrawable(resId);
                    if (d != null) {
                        d.setBounds(0, 0,
                                (int) Math.min(d.getMinimumWidth(), MAX_PICTURE_SIZE * ScreenUtils.density),
                                (int) Math.min(d.getMinimumHeight(), MAX_PICTURE_SIZE * ScreenUtils.density));
                        mIvIcon.setImageDrawable(d);
                    }*/
                    break;
                }

                //处理navigation图标
                case R.styleable.MenuTitleSubTitleView_MenuTitleSubTitleView_navigation: {
                    int resourceId = params.getResourceId(resId, 0);
                    if (resourceId != 0) {
                        mIvNavigation.setImageResource(resourceId);
                    }
                    break;
                }

                //处理title
                case R.styleable.MenuTitleSubTitleView_MenuTitleSubTitleView_title: {
                    mTvTitle.setText(params.getString(resId));
                    break;
                }

                //处理subtitle
                case R.styleable.MenuTitleSubTitleView_MenuTitleSubTitleView_subtitle: {
                    mTvSubtitle.setText(params.getString(resId));
                    break;
                }
            }
        }
        params.recycle();
    }
}
