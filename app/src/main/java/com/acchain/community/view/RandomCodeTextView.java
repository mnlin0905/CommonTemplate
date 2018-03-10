package com.acchain.community.view;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.acchain.community.R;

import java.util.Locale;

/**
 * 获取验证码控件
 * Created by rsp on 2018/1/9.
 */
public class RandomCodeTextView extends AppCompatTextView {
    private CountDownTimer countDownTimer;
    private CharSequence currentText;

    public RandomCodeTextView(Context context) {
        this(context, null);
    }

    public RandomCodeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RandomCodeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        if(TextUtils.isEmpty(getText())){
            setText("获取验证码");
        }
        currentText=getText();
        setTextColor(getResources().getColorStateList(R.color.random_code_color));
    }

    public void start() {
        if (countDownTimer == null) {
            countDownTimer = new CountDownTimer(1000 * 60, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    setText(String.format(Locale.CHINA,getContext().getString(R.string.random_code_text_view_info),millisUntilFinished / 1000 ));
                }

                @Override
                public void onFinish() {
                    setEnabled(true);
                    setText(currentText);
                    if (countDownTimer != null) {
                        countDownTimer = null;
                    }
                }
            };
            setEnabled(false);
            countDownTimer.start();
        }
    }

    public void stop() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }
}
