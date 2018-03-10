package com.common.template.dagger.component;

import com.common.template.dagger.scope.PerFragment;
import com.common.template.dagger.module.FragmentModule;
import com.common.template.fragment.WalletFragment;

import dagger.Component;

/**
 * 功能----碎片组件,用于注入dagger
 * <p>
 * Created by MNLIN on 2017/9/23.
 */
@PerFragment
@Component(modules = FragmentModule.class, dependencies = ApplicationComponent.class)
public interface FragmentComponent {
    void inject(WalletFragment walletFragment);
}
