package com.acchain.community.fragment;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.acchain.community.R;
import com.acchain.community.adapter.ConversationListAdapterEx;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseFragment;
import com.acchain.community.bean.GetAccountBean;
import com.acchain.community.contract.FriendsContract;
import com.acchain.community.drawable.RoundRectShapeDrawable;
import com.acchain.community.events.LoginSuccess;
import com.acchain.community.manager.FriendManager;
import com.acchain.community.presenter.FriendsPresenter;
import com.acchain.community.rxbus.RxBus;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.util.L;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.StringUtils;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

/**
 * function---- FriendsFragment
 * <p>
 * Created(Gradle default create) by ACChain on 2017/12/20 10:49:19 (+0000).
 */
@Route(path = ARouterConst.Fragment_FriendsFragment)
public class FriendsFragment extends BaseFragment<FriendsPresenter> implements FriendsContract.View, Toolbar.OnMenuItemClickListener {
//          String token = "TP6xOp2UOtaLFKnmkkzkVNIoXuOh43LmcugPKIOjt+Y7EXGypbXoqYeruE1vZnkq92YzqJyR+JI=";//2
//    String token = "IhgedpC3qpgvdaRTsyhZ0xL0aIpw9Rjr3VwR4sby4bwjzM4zAlidA+EZLyzOmoQrdyst3U2MKsCjRs3diaJPKg==";//0 系统
//    String token = "Nb1Rb4CIPk9RQrIyi+FUHnoaL/WC1crBcxbXZUimVH1fLp5zC2vQh85epXwD1oA8rdgz6VwvcfI=";//3 系统
    String rongCould;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_search)
    TextView mTvSearch;
    @BindView(R.id.linear_friend)
    LinearLayout mLinearFriend;
    @BindView(R.id.messageList)
    FrameLayout mMessageList;
    private Conversation.ConversationType[] mConversationsTypes = null;
    private ConversationListFragment mConversationListFragment = null;
    private Disposable subscribe;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_friends;
    }

    @Override
    public void getAccountFinish(GetAccountBean accountBean) {
        super.getAccountFinish(accountBean);
        rongCould = accountBean.getRongCouldToken();
        if (!StringUtils.isEmpty(rongCould)) {
            FriendManager.getInstance();
            //如果当前已经登录   先退出当前登录的账号   重新登录
            RongIM.getInstance().logout();
            mConversationListFragment = null;
            connect();
            // }
        }
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected void initData(Bundle savedInstanceState) {
        //初始化toolbar,设置顶部padding值,设置menu
        mToolbar.setOverflowIcon(getResources().getDrawable(R.drawable.icon_scan));
        mToolbar.inflateMenu(R.menu.menu_fragment_friends);
        ((MenuBuilder) mToolbar.getMenu()).setOptionalIconsVisible(true);
        mToolbar.setOnMenuItemClickListener(this);
        mTvSearch.post(() -> mTvSearch.setBackground(new RoundRectShapeDrawable(mTvSearch.getWidth(), mTvSearch.getHeight(), getResources().getColor(R.color.blue_search_background))));
        if (DefaultPreferenceUtil.getInstance().hasLogin()) {
            L.i("朋友界面请求用户信息");
            mPresenter.getAccount(DefaultPreferenceUtil.getInstance().getToken());
        }
        Disposable subscribe = RxBus.getInstance().toObservable(LoginSuccess.class).observeOn(AndroidSchedulers.mainThread()).subscribe(loginSuccess -> mPresenter.getAccount(DefaultPreferenceUtil.getInstance().getToken()));
        mPresenter.addDisposable(subscribe);
    }

    private void connect() {
        //token = "61Ya+p3oJoZX1OX6hSoLRWhJM4ac2Q8trObQqPVlvkPU1VRK6+BUAWQXawGCI2nRQW89pDHHk5tBim+t/jRcUnpu8T4Ivf+N";
        RongIM.connect(rongCould, new RongIMClient.ConnectCallback() {

            /**
             * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
             *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
             */
            @Override
            public void onTokenIncorrect() {
                L.i("融云连接错误：token无效:"+rongCould);
            }

            /**
             * 连接融云成功
             * @param userid 当前 token 对应的用户 id
             */
            @Override
            public void onSuccess(String userid) {
                L.i("融云连接成功：userId="+userid);
                initConversationList();
                getChildFragmentManager().beginTransaction().replace(R.id.messageList, mConversationListFragment).commitAllowingStateLoss();
            }

            /**
             * 连接融云失败
             * @param errorCode 错误码，可到官网 查看错误码对应的注释
             */
            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                L.i("融云连接失败：message="+errorCode.getMessage());
            }
        });
    }


    /**
     * 加载会话列表
     */
    private void initConversationList() {
        if (mConversationListFragment == null) {//有token就是空的
            ConversationListFragment listFragment = new ConversationListFragment();
            listFragment.setAdapter(new ConversationListAdapterEx(RongContext.getInstance()));
            Uri uri;
            uri = Uri.parse("rong://" + baseActivity.getApplicationInfo().packageName).buildUpon()
                    .appendPath("conversationlist")
                    .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话是否聚合显示
                    .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//群组
                    .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
                    .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//订阅号
                    .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")//系统
                    .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")
                    .build();
            mConversationsTypes = new Conversation.ConversationType[]{Conversation.ConversationType.PRIVATE,
                    Conversation.ConversationType.GROUP,
                    Conversation.ConversationType.PUBLIC_SERVICE,
                    Conversation.ConversationType.APP_PUBLIC_SERVICE,
                    Conversation.ConversationType.SYSTEM,
                    Conversation.ConversationType.DISCUSSION
            };

            listFragment.setUri(uri);
            mConversationListFragment = listFragment;
//            mConversationListFragment = new SubConversationListFragment();
        }

    }

    @Override
    protected void injectSelf() {
        fragmentComponent.inject(this);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        // TODO: 2017/12/21 处理menu事件
        return true;
    }

    @OnClick({R.id.Ivsearch, R.id.linear_Share, R.id.linear_mingpian, R.id.linear_contract, R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Ivsearch:
                Toast.makeText(baseActivity, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_search:
                Toast.makeText(baseActivity, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.linear_Share:
                //Toast.makeText(baseActivity, "分享圈", Toast.LENGTH_SHORT).show();
                //ARouter.getInstance().build(ARouterConst.Activity_ShareCircleActivity).navigation();
                break;
            case R.id.linear_mingpian:
                if (DefaultPreferenceUtil.getInstance().hasLogin()) {
                    ARouter.getInstance().build(ARouterConst.Activity_CardListActivity).navigation();
                } else {
                    showToast("请先登录");
                }
                break;
            case R.id.linear_contract:
                if (DefaultPreferenceUtil.getInstance().hasLogin()) {
                    ARouter.getInstance().build(ARouterConst.Activity_ContactActivity).navigation();
                } else {
                    showToast("请先登录");
                }
                break;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            if (mConversationListFragment != null) {
                mConversationListFragment.focusUnreadItem();
            }
        }
    }
}