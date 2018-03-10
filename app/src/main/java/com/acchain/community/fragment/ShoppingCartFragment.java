package  com.acchain.community.fragment;

import android.os.Bundle;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseFragment;
import com.acchain.community.contract.ShoppingCartContract;
import com.acchain.community.presenter.ShoppingCartPresenter;
import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * function---- ShoppingCartFragment
 * <p>
 * Created(Gradle default create) by ACChain on 2017/12/25 06:20:29 (+0000).
 */
@Route(path = ARouterConst.Fragment_ShoppingCartFragment)
public class ShoppingCartFragment extends BaseFragment<ShoppingCartPresenter> implements ShoppingCartContract.View{

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_shopping_cart;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {}

    @Override
    protected void injectSelf() {
        fragmentComponent.inject(this);
    }

}