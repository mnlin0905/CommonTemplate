package com.acchain.community.dagger.component;

import com.acchain.community.retrofit.HttpInterface;
import com.acchain.community.base.BaseApplication;
import com.acchain.community.dagger.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 功能----应用的组件
 * <p>
 * Created by ACChain on 2017/9/22.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseApplication application);

    HttpInterface initHttpInterface();
}
