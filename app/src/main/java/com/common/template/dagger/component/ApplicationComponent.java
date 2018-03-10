package com.common.template.dagger.component;

import com.common.template.retrofit.HttpInterface;
import com.common.template.base.BaseApplication;
import com.common.template.dagger.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 功能----应用的组件
 * <p>
 * Created by MNLIN on 2017/9/22.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseApplication application);

    HttpInterface initHttpInterface();
}
