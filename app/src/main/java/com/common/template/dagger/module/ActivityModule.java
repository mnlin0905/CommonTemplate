package com.common.template.dagger.module;

import com.common.template.dagger.scope.PerActivity;
import com.common.template.base.BaseActivity;

import dagger.Module;

/**
 * 功能----为activity提供生命周期的对象
 * <p>
 * Created by MNLIN on 2017/9/22.
 */
@PerActivity
@Module
public class ActivityModule {
    private BaseActivity activity;

    public ActivityModule(BaseActivity activity) {
        this.activity = activity;
    }
}
