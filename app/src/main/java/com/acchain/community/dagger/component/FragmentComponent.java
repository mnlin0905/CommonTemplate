package com.acchain.community.dagger.component;

import com.acchain.community.dagger.scope.PerFragment;
import com.acchain.community.dagger.module.FragmentModule;
import com.acchain.community.fragment.AdoptGoodFragment;
import com.acchain.community.fragment.AdoptGoodsDetailFragment;
import com.acchain.community.fragment.CartAdoptFragment;
import com.acchain.community.fragment.CartPanicFragment;
import com.acchain.community.fragment.CartPreSellFragment;
import com.acchain.community.fragment.ContactListFragment;
import com.acchain.community.fragment.ExerciseDetailFragment;
import com.acchain.community.fragment.ExerciseGoodFragment;
import com.acchain.community.fragment.FriendsFragment;
import com.acchain.community.fragment.GoodsCollectionFootFragment;
import com.acchain.community.fragment.IndexFragment;
import com.acchain.community.fragment.OrderFormRecordFragment;
import com.acchain.community.fragment.PanicGoodDetailFragment;
import com.acchain.community.fragment.PanicGoodFragment;
import com.acchain.community.fragment.PersonFragment;
import com.acchain.community.fragment.PreSellDetailFragment;
import com.acchain.community.fragment.PreSellGoodFragment;
import com.acchain.community.fragment.ShoppingCartFragment;
import com.acchain.community.fragment.TransactionRecordFragment;
import com.acchain.community.fragment.WalletPreOrderFragment;
import com.acchain.community.fragment.WalletAdoptFragment;
import com.acchain.community.fragment.WalletFragment;

import dagger.Component;

/**
 * 功能----碎片组件,用于注入dagger
 * <p>
 * Created by ACChain on 2017/9/23.
 */
@PerFragment
@Component(modules = FragmentModule.class, dependencies = ApplicationComponent.class)
public interface FragmentComponent {
    void inject(FriendsFragment friendsFragment);

    void inject(IndexFragment indexFragment);

    void inject(PersonFragment personFragment);

    void inject(WalletFragment walletFragment);

    void inject(ShoppingCartFragment shoppingCartFragment);

    void inject(WalletPreOrderFragment walletPreOrderFragment);

    void inject(WalletAdoptFragment walletAdoptFragment);

    void inject(PanicGoodFragment panicGoodFragment);

    void inject(CartPanicFragment cartPanicFragment);

    void inject(CartPreSellFragment cartPreSellFragment);

    void inject(CartAdoptFragment cartAdoptFragment);

    void inject(PreSellGoodFragment preSellGoodFragment);

    void inject(AdoptGoodFragment adoptGoodsFragment);

    void inject(AdoptGoodsDetailFragment adoptGoodsDetailFragment);

    void inject(ContactListFragment contactListFragment);

    void inject(ExerciseGoodFragment exerciseGoodFragment);

    void inject(ExerciseDetailFragment exerciseDetailFragment);

    void inject(PreSellDetailFragment preSellDetailFragment);

    void inject(PanicGoodDetailFragment panicGoodDetailFragment);

    void inject(GoodsCollectionFootFragment goodsCollectionFootFragment);

    void inject(TransactionRecordFragment transactionRecordFragment);

    void inject(OrderFormRecordFragment orderFormRecordFragment);
}
