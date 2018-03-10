package com.acchain.community.activity.friend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.FriendAddContract;
import com.acchain.community.presenter.FriendAddPresenter;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;
import butterknife.OnClick;

import static com.acchain.community.util.Const.KEY_HEAD_IMG;
import static com.acchain.community.util.Const.KEY_NIKE_NAME;
import static com.acchain.community.util.Const.KEY_REMARK;
import static com.acchain.community.util.Const.KEY_TARGET_FRIEND_ID;
import static com.acchain.community.util.Const.KEY_TYPE_ADD_FRIEND_FROM;
import static com.acchain.community.util.Const.TYPE_ADD_FRIEND_FROM_CARD;
import static com.acchain.community.util.Const.TYPE_ADD_FRIEND_FROM_PHONE;
import static com.acchain.community.util.Const.TYPE_ADD_FRIEND_FROM_PHONE_SEARCH;
import static com.acchain.community.util.Const.TYPE_ADD_FRIEND_FROM_SHARE;

/**
 * function---- FriendAddActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/23 02:13:29 (+0000).
 */
@Route(path = ARouterConst.Activity_FriendAddActivity)
public class FriendAddActivity extends BaseActivity<FriendAddPresenter> implements FriendAddContract.View {
  @BindView(R.id.headImg)
    RoundedImageView ivHeadImg;
    @BindView(R.id.nikeName)
    TextView tvNikeName;
    @BindView(R.id.rela_setRemark)
    RelativeLayout relaSetRemark;
    @BindView(R.id.from)
    TextView from;
    @BindView(R.id.add)
    Button add;
    @Autowired(name = KEY_NIKE_NAME,required = true)
    public String nikeName;
    @Autowired(name = KEY_HEAD_IMG,required = false)
    public String headImg;
    @Autowired(name = KEY_TARGET_FRIEND_ID,required = true)
    public String userId;
    @Autowired(name = KEY_TYPE_ADD_FRIEND_FROM, required = true)
    public String fromStr;
    private boolean isRemark = false;
    @Override
    protected int getContentViewId() {
        return R.layout.activity_friend_add;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        if (TYPE_ADD_FRIEND_FROM_PHONE_SEARCH.equals(fromStr)) {
            from.setText("来自手机号搜索");
        } else if (TYPE_ADD_FRIEND_FROM_CARD.equals(fromStr)) {
            from.setText("来自名片");
        } else if (TYPE_ADD_FRIEND_FROM_SHARE.equals(fromStr)){
            from.setText("来自名片分享");
        }else if(TYPE_ADD_FRIEND_FROM_PHONE.equals(fromStr)){
            from.setText("来自手机联系人");
        }
        tvNikeName.setText(nikeName);
        Glide.with(this)
                .load(headImg)
                .apply(new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.default_head_img)
                .error(R.drawable.default_head_img))
                .into(ivHeadImg);

    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @OnClick({R.id.rela_setRemark, R.id.add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rela_setRemark:
                ARouter.getInstance()
                        .build(ARouterConst.Activity_SetFriendRemarkActivity)
                        .withString(KEY_NIKE_NAME,tvNikeName.getText().toString())
                        .navigation(this,1);
                break;
            case R.id.add:
                ARouter.getInstance()
                        .build(ARouterConst.Activity_SendAddFriendRequestActivity)
                        .withString(KEY_TARGET_FRIEND_ID, userId)
                        .withString(KEY_REMARK, isRemark ? tvNikeName.getText().toString() : null)
                        .navigation();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            isRemark = true;
            relaSetRemark.setVisibility(View.GONE);
            String name = data.getStringExtra(KEY_NIKE_NAME);
            tvNikeName.setText(name);
        }
    }
}