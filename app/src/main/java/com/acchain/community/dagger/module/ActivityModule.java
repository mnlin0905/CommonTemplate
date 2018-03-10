package com.acchain.community.dagger.module;

import com.acchain.community.dagger.scope.PerActivity;
import com.acchain.community.base.BaseActivity;

import dagger.Module;

/**
 * 功能----为activity提供生命周期的对象
 * <p>
 * Created by ACChain on 2017/9/22.
 */
@PerActivity
@Module
public class ActivityModule {
    private BaseActivity activity;

    public ActivityModule(BaseActivity activity) {
        this.activity = activity;
    }
}
