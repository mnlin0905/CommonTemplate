package com.acchain.community.activity.wallet;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.TakeOutSelectFriendContract;
import com.acchain.community.drawable.RoundRectShapeDrawable;
import com.acchain.community.fragment.ContactListFragment;
import com.acchain.community.presenter.TakeOutSelectFriendPresenter;
import com.acchain.community.rongcloud.message.CardMessage;
import com.acchain.community.util.Const;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.StringUtils;

import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;

import static com.acchain.community.util.Const.KEY_TARGET_FRIEND_ID;

/**
 * function---- TakeOutSelectFriendActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/13 06:04:30 (+0000).
 */
@Route(path = ARouterConst.Activity_TakeOutSelectFriendActivity)
public class TakeOutSelectFriendActivity extends BaseActivity<TakeOutSelectFriendPresenter> implements TakeOutSelectFriendContract.View {

    @BindView(R.id.fl_search)
    FrameLayout mFlSearch;
    @BindView(R.id.search)
    EditText search;

    /**
     * 朋友的id
     */
    @Autowired(name = KEY_TARGET_FRIEND_ID,required = false)
    public String targetId;

    /**
     * 已选中的资产
     */
    @Autowired(name = Const.KEY_CURRENCY_SHORT_NAME, required = true)
    String currencyName;

    private ContactListFragment contactListFragment;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_take_out_select_friend;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //更换搜索框背景
        mFlSearch.post(() -> mFlSearch.setBackground(new RoundRectShapeDrawable(mFlSearch.getWidth(), mFlSearch.getHeight(), getResources().getColor(R.color.blue_search_background))));
        contactListFragment = (ContactListFragment) getSupportFragmentManager().findFragmentById(R.id.contactList);
        contactListFragment.setSearchEditText(search);
        contactListFragment.setOnFriendClickListener(friend -> {
            if (!StringUtils.isEmpty(targetId)) {
                CardMessage cardMessage = new CardMessage(friend.getUserId(),friend.getName(), friend.getPortraitUri());
                Message obtain = Message.obtain(targetId, Conversation.ConversationType.PRIVATE, cardMessage);
                RongIM.getInstance().sendMessage(obtain, "收到一个朋友推荐", "收到一个朋友推荐", (IRongCallback.ISendMessageCallback) null);
                finish();
            } else {
                ARouter.getInstance()
                        .build(ARouterConst.Activity_TakeOutFriendActivity)
                        .withString(Const.KEY_CURRENCY_SHORT_NAME, currencyName)
                        .withObject(Const.MODEL_CONTACT_FRIEND, friend)
                        .navigation();
            }
        });
    }
    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @OnClick(R.id.fl_search)
    public void onViewClicked() {
        ARouter.getInstance()
                .build(ARouterConst.Activity_SearchFilterActivity)
                // TODO: 2018/2/8  添加过滤的列表
                .navigation();
    }
}