package com.acchain.community.activity.friend;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.bean.Friend;
import com.acchain.community.contract.ContactContract;
import com.acchain.community.drawable.RoundRectShapeDrawable;
import com.acchain.community.fragment.ContactListFragment;
import com.acchain.community.interfaces.OnFriendClickListener;
import com.acchain.community.presenter.ContactPresenter;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;

/**
 * function---- ContactActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/15 11:11:19 (+0000).
 */
@Route(path = ARouterConst.Activity_ContactActivity)
public class ContactActivity extends BaseActivity<ContactPresenter> implements ContactContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.search)
    EditText mTvSearch;
    private ContactListFragment contactListFragment;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_contact;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mTvSearch.post(() -> mTvSearch.setBackground(new RoundRectShapeDrawable(mTvSearch.getWidth(), mTvSearch.getHeight(), getResources().getColor(R.color.blue_search_background))));
        contactListFragment = (ContactListFragment) getSupportFragmentManager().findFragmentById(R.id.contactList);
        contactListFragment.setOnFriendClickListener(new OnFriendClickListener() {
            @Override
            public void onFriendClick(Friend friend) {
                RongIM.getInstance().startPrivateChat(ContactActivity.this, friend.getUserId(), friend.getName());
                finish();
            }
        });
        contactListFragment.setSearchEditText(mTvSearch);
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*if (item.getItemId() == R.id.addContact) {
            ARouter.getInstance().build(ARouterConst.Activity_AddNewFriendActivity).navigation();
        }*/
        showToast("这部分功能还没有");
        return true;
    }

    @OnClick(R.id.new_friend)
    public void onViewClicked() {
        if (DefaultPreferenceUtil.getInstance().hasLogin()) {
            ARouter.getInstance().build(ARouterConst.Activity_NewFriendActivity).navigation();
        } else {
            showToast("请先登录");
        }
    }
}