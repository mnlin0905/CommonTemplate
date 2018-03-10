package com.acchain.community.base;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created on 2018/1/10
 * function : 基础的AbsListView的ViewHolder
 *
 * @author ACChain
 */

public class BaseAdapterViewHolder {
    View rootView;

    public BaseAdapterViewHolder(Context context, @LayoutRes int layoutRes) {
        rootView = LayoutInflater.from(context).inflate(layoutRes, null);
        if (rootView instanceof ViewGroup) {
            ButterKnife.bind(this, rootView);
        }
        rootView.setTag(this);
    }

    public View getRootView() {
        return rootView;
    }
}