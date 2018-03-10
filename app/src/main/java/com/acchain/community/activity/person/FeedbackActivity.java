package com.acchain.community.activity.person;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.FeedbackContract;
import com.acchain.community.presenter.FeedbackPresenter;
import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * function---- FeedbackActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/22 05:48:48 (+0000).
 */
@Route(path = ARouterConst.Activity_FeedbackActivity)
public class FeedbackActivity extends BaseActivity<FeedbackPresenter> implements FeedbackContract.View, RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.rg_type)
    RadioGroup mRgType;
    @BindView(R.id.et_remark)
    EditText mEtRemark;
    @BindView(R.id.bt_submit)
    Button mBtSubmit;

    /**
     * 反馈类型
     */
    int type = 1;
    private String remark;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //切换反馈类型
        mRgType.setOnCheckedChangeListener(this);

    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @OnClick(R.id.bt_submit)
    public void onViewClicked() {
         remark = mEtRemark.getText().toString();
        if (type==4&& TextUtils.isEmpty(remark)){
            showToast("请填写反馈内容");
            return;
        }

        // TODO: 2018/1/22 提交反馈信息
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_1:
                type = 1;
                break;
            case R.id.rb_2:
                type = 2;
                break;
            case R.id.rb_3:
                type = 3;
                break;
            case R.id.rb_4:
                type = 4;
                break;
        }
    }
}