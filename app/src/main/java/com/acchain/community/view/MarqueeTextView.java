package com.acchain.community.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by admin on 2017/4/28.
 *
 * 可以实现跑马灯效果的TextView
 */

public class MarqueeTextView extends TextView {
	public MarqueeTextView(Context context) {
		this(context,null);
	}

	public MarqueeTextView(Context context, AttributeSet attrs) {
		this(context, attrs,0);
	}

	public MarqueeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		this(context, attrs, defStyleAttr,0);
	}

	public MarqueeTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init();
	}

	private void init(){
		//设置跑马灯效果
		setEllipsize(TextUtils.TruncateAt.MARQUEE);
		setSingleLine();
		setMarqueeRepeatLimit(-1);
	}

	@Override
	public boolean isFocused() {
		return true;
	}
}
