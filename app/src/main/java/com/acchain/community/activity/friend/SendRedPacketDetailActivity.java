package com.acchain.community.activity.friend;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.bean.Friend;
import com.acchain.community.bean.RedPacketDetail;
import com.acchain.community.contract.SendRedPacketDetailContract;
import com.acchain.community.manager.FriendManager;
import com.acchain.community.presenter.SendRedPacketDetailPresenter;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.acchain.community.util.Const.KEY_RED_PACKAGE_ID;

/**
 * function---- SendRedPacketDetailActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/30 09:22:48 (+0000).
 */
@Route(path = ARouterConst.Activity_SendRedPacketDetailActivity)
public class SendRedPacketDetailActivity extends BaseActivity<SendRedPacketDetailPresenter> implements SendRedPacketDetailContract.View {
    @Autowired(name = KEY_RED_PACKAGE_ID, required = true)
    public String redPacketId;
    @BindView(R.id.headImg)
    CircleImageView headImg;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.red_title)
    TextView redTitle;
    @BindView(R.id.info)
    TextView info;
    @BindView(R.id.recever_nikeName)
    TextView receverNikeName;
    @BindView(R.id.recever_Time)
    TextView receverTime;
    @BindView(R.id.recever_Amount)
    TextView receverAmount;
    @BindView(R.id.recever_Currency)
    TextView receverCurrency;
    @BindView(R.id.linear_recever)
    LinearLayout linearRecever;
    @BindView(R.id.recever_HeadImg)
    ImageView recever_HeadImg;
    @Override
    protected int getContentViewId() {
        return R.layout.activity_send_red_packet_detail;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        ViewGroup contentFrameLayout = getWindow().getDecorView().findViewById(android.R.id.content);
        contentFrameLayout.getChildAt(0).setFitsSystemWindows(false);
        mPresenter.loadRedDetails();
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @Override
    public String getRedPacketId() {
        return redPacketId;
    }

    @Override
    public void showRedDetails(RedPacketDetail redPacketDetail) {
//        int memberId = redPacketDetail.getMemberId();
        int receiveId = redPacketDetail.getReceiveId();
        Friend receiveFriend = FriendManager.getInstance().findFriendById(String.valueOf(receiveId));
        Glide.with(this).load(BasePresenter.singleAccountBean.getImgSrc()).into(headImg);
        name.setText(BasePresenter.singleAccountBean.getNickname());
        redTitle.setText(redPacketDetail.getRemarks());
        switch (redPacketDetail.getStatus()) {
            case 0:
                //0 待领取
                String string = getString(R.string.red_packet_info, redPacketDetail.getAmount() + " " + redPacketDetail.getCurrency());
                info.setText(string);
                break;
            case 1:
                //1 已领取
                info.setText("1个红包,共"+redPacketDetail.getAmount()+" "+redPacketDetail.getCurrency());
                linearRecever.setVisibility(View.VISIBLE);
                receverNikeName.setText(receiveFriend.getName());
                receverTime.setText(redPacketDetail.getCreateTime());
                receverAmount.setText(redPacketDetail.getAmount());
                receverCurrency.setText(redPacketDetail.getCurrency());
                Glide.with(this).asDrawable().apply(new RequestOptions().centerCrop()).load(receiveFriend.getPortraitUri()).into(recever_HeadImg);
                break;
            case 2:
                //2 已过期
                break;
        }
    }
    @OnClick(R.id.my_red_packet_record)
    public void onViewClicked() {
        ARouter.getInstance().build(ARouterConst.Activity_RedPacketRecordActivity).navigation();
    }
}