package com.common.template.dagger.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * 功能----定义每个activity的生命周期,供dagger框架使用
 * <p>
 * Created by MNLIN on 2017/9/22.
 */
@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
