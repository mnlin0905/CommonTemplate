package com.acchain.community.activity.friend;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;

import com.acchain.community.R;
import com.acchain.community.adapter.CardListAdapter;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.base.BaseRecyclerViewHolder;
import com.acchain.community.bean.Card;
import com.acchain.community.contract.CardListContract;
import com.acchain.community.drawable.RoundRectShapeDrawable;
import com.acchain.community.presenter.CardListPresenter;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * function---- CardListActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/18 02:36:54 (+0000).
 */
@Route(path = ARouterConst.Activity_CardListActivity)
public class CardListActivity extends BaseActivity<CardListPresenter> implements CardListContract.View {

    @BindView(R.id.search)
    EditText mTvSearch;
    @BindView(R.id.xrcy)
    XRecyclerView xrcy;
    private CardListAdapter cardListAdapter;
    private List<Card> datas;
    @Override
    protected int getContentViewId() {
        return R.layout.activity_card_list;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mTvSearch.post(() -> mTvSearch.setBackground(new RoundRectShapeDrawable(mTvSearch.getWidth(), mTvSearch.getHeight(), getResources().getColor(R.color.blue_search_background))));
        xrcy.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        datas = new ArrayList<>();
        cardListAdapter = new CardListAdapter(datas, new BaseRecyclerViewHolder.OnViewClickListener() {
            @Override
            public void onViewClick(View v, int position) {
                Card card = datas.get(position);
                ARouter.getInstance().build(ARouterConst.Activity_CardDetailActivity).withString("cardId", String.valueOf(card.getId())).withBoolean("isFriend",card.isIs_friends()).navigation();
            }
        });
        xrcy.setAdapter(cardListAdapter);
        mPresenter.loadAllCard();
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @Override
    public void showAllCard(List<Card> cardList) {
        datas.clear();
        datas.addAll(cardList);
        cardListAdapter.notifyDataSetChanged();
    }
}