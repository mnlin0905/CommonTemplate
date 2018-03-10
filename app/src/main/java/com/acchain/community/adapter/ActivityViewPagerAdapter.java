package com.acchain.community.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.acchain.community.base.BaseFragment;

import java.util.List;

/**
 * Created on 2017/12/20
 * function : Viewpager适配器
 *
 * @author ACChain
 */

public class ActivityViewPagerAdapter extends FragmentPagerAdapter {
    private List<? extends BaseFragment> data;

    public ActivityViewPagerAdapter(FragmentManager fm, List<? extends BaseFragment> data) {
        super(fm);
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
