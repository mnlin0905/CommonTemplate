package com.acchain.community.activity.friend;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import com.acchain.community.R;
import com.acchain.community.adapter.NewFriendListAdapter;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.bean.ContactAddRequest;
import com.acchain.community.contract.NewFriendContract;
import com.acchain.community.drawable.RoundRectShapeDrawable;
import com.acchain.community.events.ContactRequestEvent;
import com.acchain.community.events.RefreshFriendList;
import com.acchain.community.presenter.NewFriendPresenter;
import com.acchain.community.rxbus.RxBus;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import org.litepal.crud.DataSupport;
import org.litepal.crud.async.FindMultiExecutor;
import org.litepal.crud.callback.FindMultiCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;

/**
 * 好友申请列表页
 * function---- NewFriendActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/16 08:42:38 (+0000).
 */
@Route(path = ARouterConst.Activity_NewFriendActivity)
public class NewFriendActivity extends BaseActivity<NewFriendPresenter> implements NewFriendContract.View {

    @BindView(R.id.search)
    EditText mTvSearch;
    @BindView(R.id.listView)
    ListView listView;
    private NewFriendListAdapter adapter;
    private List<ContactAddRequest> requestList;
    private String addUserId;
    private ContactAddRequest currentFriend;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_new_friend;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.addContact) {
            ARouter.getInstance().build(ARouterConst.Activity_AddNewFriendActivity).navigation();
        }
        return true;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mTvSearch.post(() -> mTvSearch.setBackground(new RoundRectShapeDrawable(mTvSearch.getWidth(), mTvSearch.getHeight(), getResources().getColor(R.color.blue_search_background))));
        Disposable subscribe = RxBus.getInstance().toObservable(ContactRequestEvent.class).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ContactRequestEvent>() {
                    @Override
                    public void accept(ContactRequestEvent contactRequestEvent) throws Exception {
                        getRequestData();
                    }
                });
        mPresenter.addDisposable(subscribe);
        getRequestData();

    }
    private void getRequestData() {
        FindMultiExecutor allAsync = DataSupport.where("targetUserId = ?", DefaultPreferenceUtil.getInstance().getMemberId()).order("updateTime desc").findAsync(ContactAddRequest.class);
        allAsync.listen(new FindMultiCallback() {
            @Override
            public <T> void onFinish(List<T> t) {
                if (t != null) {
                    showData((List<ContactAddRequest>) t);
                }
            }
        });
    }

    private void showData(List<ContactAddRequest> friends) {
        if (adapter == null) {
            requestList = new ArrayList<>(friends);
            adapter = new NewFriendListAdapter(this, requestList, contactAddRequest -> {
                addUserId = contactAddRequest.getSourceUserId();
                currentFriend = contactAddRequest;
                mPresenter.accept();
            });
            listView.setAdapter(adapter);
        } else {
            requestList.clear();
            requestList.addAll(friends);
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @OnClick(R.id.add_phone_Contact)
    public void onViewClicked() {
        ARouter.getInstance().build(ARouterConst.Activity_AddPhoneContactActivity).navigation();
    }

    @Override
    public String getUserId() {
        return addUserId;
    }

    @Override
    public void acceptSuccess() {
        UserInfo userInfo = currentFriend.getUserInfo();
        RongIM.getInstance().refreshUserInfoCache(userInfo);
        RxBus.getInstance().post(new RefreshFriendList());
    }
}