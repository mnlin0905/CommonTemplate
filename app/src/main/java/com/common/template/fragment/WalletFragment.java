package com.common.template.fragment;

import android.os.Bundle;

import com.common.template.R;
import com.common.template.arouter.ARouterConst;
import com.common.template.base.BaseFragment;
import com.common.template.contract.WalletContract;
import com.common.template.presenter.WalletPresenter;
import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * function---- WalletFragment
 * <p>
 * Created(Gradle default create) by MNLIN on 2017/12/20 10:48:55 (+0000).
 */
@Route(path = ARouterConst.Fragment_WalletFragment)
public class WalletFragment extends BaseFragment<WalletPresenter> implements WalletContract.View {
    @Override
    protected int getContentViewId() {
        return R.layout.fragment_wallet;
    }

    /**
     * 数据处理
     *
     * @param savedInstanceState
     */
    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    /**
     * 使用dagger注入自身
     */
    @Override
    protected void injectSelf() {
        fragmentComponent.inject(this);
    }
}