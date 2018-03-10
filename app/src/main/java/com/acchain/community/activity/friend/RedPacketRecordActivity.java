package com.acchain.community.activity.friend;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.adapter.ReceverRedPacketRecordAdapter;
import com.acchain.community.adapter.SendRedPacketRecordAdapter;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.bean.ReceverRedPacketRecord;
import com.acchain.community.bean.SendRedPacketRecord;
import com.acchain.community.contract.RedPacketRecordContract;
import com.acchain.community.presenter.RedPacketRecordPresenter;
import com.acchain.community.window.SelectRedPacketTypeDialogFragment;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * function---- RedPacketRecordActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/30 12:12:57 (+0000).
 */
@Route(path = ARouterConst.Activity_RedPacketRecordActivity)
public class RedPacketRecordActivity extends BaseActivity<RedPacketRecordPresenter> implements RedPacketRecordContract.View, SelectRedPacketTypeDialogFragment.OnItemClickListener {

    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.headImg)
    CircleImageView headImg;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.left_Value)
    TextView leftValue;
    @BindView(R.id.left_title)
    TextView leftTitle;
    @BindView(R.id.right_value)
    TextView rightValue;
    @BindView(R.id.right_title)
    TextView rightTitle;
    @BindView(R.id.xrcy)
    XRecyclerView xrcy;
    private ReceverRedPacketRecordAdapter redPacketRecordAdapter;
    private SendRedPacketRecordAdapter sendRedPacketRecordAdapter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_red_packet_record;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            type.setZ(100);
        }
        xrcy.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        xrcy.setLoadingMoreEnabled(false);
        xrcy.setPullRefreshEnabled(false);
        mPresenter.loadReceverRedPacket();
        Glide.with(this).load(BasePresenter.singleAccountBean.getImgSrc()).apply(new RequestOptions().centerCrop()).into(headImg);
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }


    @OnClick(R.id.type)
    public void onViewClicked() {
        SelectRedPacketTypeDialogFragment selectRedPacketTypeDialogFragment = new SelectRedPacketTypeDialogFragment();
        selectRedPacketTypeDialogFragment.show(getSupportFragmentManager(), "sss");
    }

    @Override
    public void onItemClickListener(DialogFragment dialog, String clickItem, int index) {
        dialog.dismiss();
        type.setText(clickItem);
        switch (index) {
            case 0:
                //收到的红包
                mPresenter.loadReceverRedPacket();
                break;
            case 1:
                //发出的红包
                mPresenter.loadSendRedPacket();
                break;
        }
    }

    @Override
    public void showRecever(ReceverRedPacketRecord receverRedPacketRecord) {
        leftTitle.setText(R.string.recever_red_packet);
        title.setText(R.string.sumRecever);
        leftValue.setText(String.valueOf(receverRedPacketRecord.getRedEnvelopesCount()));
        rightValue.setText(String.valueOf(receverRedPacketRecord.getCurrencyCount()));
        redPacketRecordAdapter = new ReceverRedPacketRecordAdapter(receverRedPacketRecord.getReceivedRedEnvelopesList());
        xrcy.setAdapter(redPacketRecordAdapter);
    }

    @Override
    public void showSender(SendRedPacketRecord sendRedPacketRecord) {
        leftTitle.setText(R.string.send_Packet);
        title.setText(R.string.sum_sendRedPacket);
        leftValue.setText(String.valueOf(sendRedPacketRecord.getRedEnvelopesCount()));
        rightValue.setText(String.valueOf(sendRedPacketRecord.getCurrencyCount()));
        sendRedPacketRecordAdapter = new SendRedPacketRecordAdapter(sendRedPacketRecord.getIssueRedEnvelopesList());
        xrcy.setAdapter(sendRedPacketRecordAdapter);
    }
}