package com.acchain.community.view;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by admin on 2017/4/24.
 */

public class CheckRadioButton extends android.support.v7.widget.AppCompatRadioButton {
	public CheckRadioButton(Context context) {
		super(context);
	}

	public CheckRadioButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CheckRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	public void toggle() {
		setChecked(!isChecked());
	}
}
