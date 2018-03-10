package com.acchain.community.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.acchain.community.R;

/**
 * 自定义组件：购买数量，带减少增加按钮
 * Created by hiwhitley on 2016/7/4.
 */
public class AmountView extends LinearLayout implements View.OnClickListener {

    private static final String TAG = "AmountView";
    private long amount = 1; //购买数量
    private long goods_storage = 10; //商品库存
    private OnAmountChangeListener mListener;
    private boolean isFilterFastClick = false;//是否过滤掉快速点击
    private EditText etAmount;
    private ImageView btnDecrease;
    private ImageView btnIncrease;

    public AmountView(Context context) {
        this(context, null);
    }

    public AmountView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_amount, this);
        etAmount = (EditText) findViewById(R.id.etAmount);
        btnDecrease = (ImageView) findViewById(R.id.btnDecrease);
        btnIncrease = (ImageView) findViewById(R.id.btnIncrease);
        btnDecrease.setOnClickListener(this);
        btnIncrease.setOnClickListener(this);
        btnDecrease.setEnabled(false);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.AmountView);
        int type = obtainStyledAttributes.getInt(R.styleable.AmountView_type, 0);
        int etColor = obtainStyledAttributes.getColor(R.styleable.AmountView_etColor, Color.parseColor("#ffffff"));
        int btnWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_btWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        int etWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_etWidth, 140);//dp和sp都会乘以3
        int etTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_etSize, 12);
        obtainStyledAttributes.recycle();
        LayoutParams btnParams = new LayoutParams(btnWidth, LayoutParams.MATCH_PARENT);
        btnDecrease.setLayoutParams(btnParams);
        btnIncrease.setLayoutParams(btnParams);
        LayoutParams textParams = new LayoutParams(etWidth, LayoutParams.MATCH_PARENT);
        etAmount.setLayoutParams(textParams);
        Log.i("AmountView", "\ntype=" + type + "\netColor=" + etColor + "\nbtnWidth=" + btnWidth + "\ntvWidth=" + etWidth + "\ntextSize=" + etTextSize);
        etAmount.setTextSize(etTextSize);
        etAmount.setTextColor(etColor);

        if (type == 0) {//商品弹窗里面那个，白色的,24px,#484e5a,width=85px
            btnDecrease.setImageResource(R.drawable.selecter_less_white);
            btnIncrease.setImageResource(R.drawable.selecter_add_white);
            etAmount.setBackgroundResource(R.drawable.shape_amount_view_bg);
        } else if (type == 1) {//预购里面那个，蓝色小的 26px,white,width=65px
            btnDecrease.setImageResource(R.drawable.selecter_less_blue_small);
            btnIncrease.setImageResource(R.drawable.selecter_add_blue_small);
        } else if (type == 2) {//购物车里面那个,蓝色大的 ,34px white,width=105px
            btnDecrease.setImageResource(R.drawable.selecter_less_blue_big);
            btnIncrease.setImageResource(R.drawable.selecter_add_blue_big);
        }
    }

    public void setOnAmountChangeListener(OnAmountChangeListener onAmountChangeListener) {
        this.mListener = onAmountChangeListener;
    }

    public void setGoods_storage(long goods_storage) {
        this.goods_storage = goods_storage;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDecrease://减
                amount--;
                show();
                break;
            case R.id.btnIncrease://加
                amount++;
                show();
                break;
        }
    }

    private void show() {
        if (amount == 1) {
            btnDecrease.setEnabled(false);
        } else
            btnDecrease.setEnabled(true);
        if (amount == goods_storage) {
            btnIncrease.setEnabled(false);
        } else
            btnIncrease.setEnabled(true);
        etAmount.setText(amount + "");
        etAmount.clearFocus();

        if (mListener != null) {
            mListener.onAmountChange(this, amount);
        }
    }

    public interface OnAmountChangeListener {
        void onAmountChange(View view, long amount);
    }

    public void setNumber(long amount) {
        this.amount = amount;
        etAmount.setText(amount + "");
        /*设置是否点击*/
        if (amount == 1) {
            btnDecrease.setEnabled(false);
        } else
            btnDecrease.setEnabled(true);
        if (amount == goods_storage) {
            btnIncrease.setEnabled(false);
        } else
            btnIncrease.setEnabled(true);
    }

    public long getNumber() {
        return amount;
    }

}
