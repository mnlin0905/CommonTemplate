package com.common.template.dagger.component;

import com.common.template.activity.other.SelectFunctionActivity;
import com.common.template.dagger.module.ActivityModule;
import com.common.template.dagger.scope.PerActivity;

import dagger.Component;

/**
 * 功能----activity组件,提供清单文件
 * <p>
 * Created by MNLIN on 2017/9/22.
 */
@PerActivity
@Component(modules = ActivityModule.class,dependencies = ApplicationComponent.class)
public interface ActivityComponent {
    void inject(SelectFunctionActivity activity);
}
