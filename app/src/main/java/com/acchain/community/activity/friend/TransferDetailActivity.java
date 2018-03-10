package com.acchain.community.activity.friend;

import android.os.Bundle;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.contract.TransferDetailContract;
import com.acchain.community.presenter.TransferDetailPresenter;
import com.acchain.community.rongcloud.message.TranferMessage;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.acchain.community.util.Const.KEY_TRANSFER_MESSAGE;

/**
 * function---- TransferDetailActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/02/03 07:19:35 (+0000).
 */
@Route(path = ARouterConst.Activity_TransferDetailActivity)
public class TransferDetailActivity extends BaseActivity<TransferDetailPresenter> implements TransferDetailContract.View {
    @BindView(R.id.headImg)
    CircleImageView headImg;
    @BindView(R.id.nikeName)
    TextView nikeName;
    @BindView(R.id.amount)
    TextView amount;
    @BindView(R.id.pay_type)
    TextView payType;
    @BindView(R.id.toAccount)
    TextView toAccount;
    @BindView(R.id.createTime)
    TextView createTime;
    @BindView(R.id.tranferId)
    TextView tranferId;
    @BindView(R.id.remark)
    TextView remark;
    @Autowired(name = KEY_TRANSFER_MESSAGE, required = true)
    public TranferMessage tranferMessage1;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_transfer_detail;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        String senderUserId = tranferMessage1.getSenderUserId();
        String memberId = BasePresenter.singleAccountBean.getMemberId();
        Glide.with(this).load(tranferMessage1.getSenderHeadImg()).apply(new RequestOptions().centerCrop()).into(headImg);
        nikeName.setText(tranferMessage1.getOwen_name());
        payType.setText(tranferMessage1.getPayment_model());
        createTime.setText(tranferMessage1.getCreate_time());
        tranferId.setText(tranferMessage1.getTransId());
        remark.setText(tranferMessage1.getRemark());
        if (memberId.equals(senderUserId)) {
            //发送者打开转账详情
            toAccount.setText(tranferMessage1.getTarger_name());
            amount.setText("-" + tranferMessage1.getAmount() + " " + tranferMessage1.getCurrency());
        } else {
            //接收者打开转账详情
            toAccount.setText(tranferMessage1.getOwen_name());
            amount.setText("+" + tranferMessage1.getAmount() + " " + tranferMessage1.getCurrency());
        }
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }


}