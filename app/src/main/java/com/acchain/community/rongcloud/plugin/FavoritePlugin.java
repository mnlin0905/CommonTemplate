package com.acchain.community.rongcloud.plugin;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.acchain.community.R;

import io.rong.imkit.RongExtension;
import io.rong.imkit.plugin.IPluginModule;

/**
 * Created by rsp on 2018/1/15.
 */

public class FavoritePlugin implements IPluginModule {
    @Override
    public Drawable obtainDrawable(Context context) {
        return context.getResources().getDrawable(R.drawable.favorite);
    }

    @Override
    public String obtainTitle(Context context) {
        return context.getString(R.string.favorite);
    }

    @Override
    public void onClick(Fragment fragment, RongExtension rongExtension) {
        Toast.makeText(fragment.getActivity(), "收藏", Toast.LENGTH_SHORT).show();
        rongExtension.collapseExtension();
    }

    @Override
    public void onActivityResult(int i, int i1, Intent intent) {

    }
}
