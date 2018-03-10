package com.acchain.community.activity.friend;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.bean.Friend;
import com.acchain.community.contract.ChatContract;
import com.acchain.community.events.RefreshRedPacketMessage;
import com.acchain.community.manager.FriendManager;
import com.acchain.community.presenter.ChatPresenter;
import com.acchain.community.rongcloud.message.RedPacketMessage;
import com.acchain.community.rongcloud.message.RedPacketMessageProvider;
import com.acchain.community.rxbus.RxBus;
import com.acchain.community.window.OpenRedPacketDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.adapter.MessageListAdapter;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.MessageContent;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

import static com.acchain.community.util.Const.KEY_AMOUNT;
import static com.acchain.community.util.Const.KEY_CURRENCY_SHORT_NAME;
import static com.acchain.community.util.Const.KEY_HEAD_IMG;
import static com.acchain.community.util.Const.KEY_NIKE_NAME;
import static com.acchain.community.util.Const.KEY_REMARK;

/**
 * function---- ChatActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/05 10:22:20 (+0000).
 */
@RuntimePermissions
@Route(path = ARouterConst.Activity_ChatActivity)
public class ChatActivity extends BaseActivity<ChatPresenter> implements ChatContract.View {
    @BindView(R.id.menu)
    ImageView menu;
    private ConversationFragment conversationFragment;
    private String redPacketId;
    private String messageId;
    private RedPacketMessage redPacketMessage;
    private String title;
    private Friend friend;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_chat;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        title = getIntent().getData().getQueryParameter("title");
        if (!TextUtils.isEmpty(title)) {
            setTitle(title);
            toolbar.setTitle(title);
        }
        if (Build.VERSION.SDK_INT >= 23) {
            ChatActivityPermissionsDispatcher.requestPermissionWithPermissionCheck(this);
        }
        conversationFragment = (ConversationFragment) getSupportFragmentManager().findFragmentById(R.id.conversation);
        menu.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                menu.setX(event.getRawX());
                menu.setY(event.getRawY());
            }
            return false;
        });
        Disposable subscribe1 = RxBus.getInstance().toObservable(RefreshRedPacketMessage.class).observeOn(AndroidSchedulers.mainThread())
                .subscribe(refreshRedPacketMessage -> {
                    MessageListAdapter messageAdapter = conversationFragment.getMessageAdapter();
                    int count = messageAdapter.getCount();
                    for (int i = 0; i < count; i++) {
                        UIMessage item = messageAdapter.getItem(i);
                        MessageContent content = item.getMessage().getContent();
                        if (content instanceof RedPacketMessage) {
                            RedPacketMessage redPacketMessage = (RedPacketMessage) content;
                            if (redPacketMessage.getRedPacketId().equals(refreshRedPacketMessage.getEnvelopesId())) {
                                item.setExtra("已领取");
                                break;
                            }
                        }
                    }
                    messageAdapter.notifyDataSetChanged();
                });
        mPresenter.addDisposable(subscribe1);

        Disposable subscribe = RxBus.getInstance().toObservable(RedPacketMessage.class).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RedPacketMessage>() {
                    @Override
                    public void accept(RedPacketMessage redPacket) throws Exception {
                        redPacketId = redPacket.getRedPacketId();
                        messageId = String.valueOf(redPacket.getUiMessage().getMessageId());
                        mPresenter.receiveRedPacket();
                        redPacketMessage = redPacket;
                        friend = FriendManager.getInstance().findFriendById(redPacket.getUiMessage().getSenderUserId());
                    }
                });
        mPresenter.addDisposable(subscribe);
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide mSlide = new Slide();
            mSlide.setInterpolator(new AccelerateInterpolator());
            mSlide.setSlideEdge(Gravity.TOP);
            getWindow().setEnterTransition(mSlide);
            getWindow().setExitTransition(mSlide);
        }*/
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }


    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void requestPermission() {
    }

    @SuppressLint("NeedOnRequestPermissionsResult")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ChatActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnShowRationale({Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void showRationale(final PermissionRequest request) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle("温馨提示").setMessage("您必须提供这些权限才能正常聊天").create();
        alertDialog.show();
        alertDialog.setOnDismissListener(dialog -> request.proceed());
    }

    @OnPermissionDenied({Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onPermissionDenied() {
        //拒绝权限但没有勾选不在询问
    /*AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle("温馨提示").setMessage("没办法玩耍了").create();
        alertDialog.show();*/
    }

    @OnNeverAskAgain({Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_PHONE_STATE})
    void onNerverAskAgain() {
        //拒绝权限并勾选不在询问
        AlertDialog dialog = new AlertDialog.Builder(this).setTitle("警告").setMessage("看来没办法愉快的玩耍了   再见").create();
        dialog.show();
        dialog.setOnDismissListener(dialog1 -> finish());

    }

    @OnClick(R.id.menu)
    public void onViewClicked() {
        Toast.makeText(this, "菜单", Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getRedPacketId() {
        return redPacketId;
    }

    @Override
    public void receiveSuccess() {
        View view = redPacketMessage.getView();
        RedPacketMessageProvider.ViewHolder holder = (RedPacketMessageProvider.ViewHolder) view.getTag();
        holder.title.setText("红包已被领完");
        view.setBackgroundResource(R.drawable.red_pack_recever_yes);
        UIMessage uiMessage = redPacketMessage.getUiMessage();
        uiMessage.setExtra("已领取");
        RongIM.getInstance().setMessageExtra(Integer.parseInt(messageId), "已领取", new RongIMClient.ResultCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {
                if (aBoolean) {
                    OpenRedPacketDialog redPacketOpenDialog = (OpenRedPacketDialog) redPacketMessage.getRedPacketOpenDialog();
                    //ActivityOptionsCompat compat =        ActivityOptionsCompat.makeSceneTransitionAnimation(ChatActivity.this,redPacketOpenDialog.getShareView(),"open_redPacket");
                  //  ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(ChatActivity.this);
                    ARouter.getInstance()
                            .build(ARouterConst.Activity_RedPacketDetailActivity)
                            //.withOptionsCompat(optionsCompat)
                            .withString(KEY_AMOUNT,redPacketMessage.getAmount())
                            .withString(KEY_NIKE_NAME,friend.getName())
                            .withString(KEY_HEAD_IMG, friend.getPortraitUri())
                            .withString(KEY_REMARK, redPacketMessage.getRedPacketTitle())
                            .withString(KEY_CURRENCY_SHORT_NAME, redPacketMessage.getCurrency())
                            .navigation();
                    redPacketMessage.getRedPacketOpenDialog().dismiss();
                }
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });
    }
}