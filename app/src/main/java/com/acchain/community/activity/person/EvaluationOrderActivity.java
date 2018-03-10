package com.acchain.community.activity.person;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.contract.EvaluationOrderContract;
import com.acchain.community.presenter.EvaluationOrderPresenter;
import com.acchain.community.view.LineMenuView;
import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * function---- EvaluationOrderActivity
 * <p>
 * 评价订单
 * <p>
 * Created(Gradle default create) by ACChain on 2018/02/06 05:59:13 (+0000).
 */
@Route(path = ARouterConst.Activity_EvaluationOrderActivity)
public class EvaluationOrderActivity extends BaseActivity<EvaluationOrderPresenter> implements EvaluationOrderContract.View,LineMenuView.LineMenuListener {

    @BindView(R.id.rb_comment)
    RatingBar mRbComment;
    @BindView(R.id.iv_picture_one)
    ImageView mIvPictureOne;
    @BindView(R.id.ll_upload)
    LinearLayout mLlUpload;
    @BindView(R.id.rb_service)
    RatingBar mRbService;
    @BindView(R.id.rb_logistic)
    RatingBar mRbLogistic;

    @Override
    protected int getContentViewId() {
        return R.layout.activiy_evaluation_order;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_text_submit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO: 2018/2/6 提交信息
        return true;
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @OnClick(R.id.ll_upload)
    public void onViewClicked() {

    }

    /**
     * 点击左侧文本
     *
     * @param v 被点击到的v;此时应该是左侧的TextView
     * @return 是否消费该点击事件, 如果返回true, 则performSelf将不会被调用
     */
    @Override
    public boolean performClickLeft(TextView v) {
        return false;
    }

    /**
     * @param v 被点击到的v;此时应该是右侧的TextView
     * @return 是否消费该点击事件, 如果返回true, 则performSelf将不会被调用
     */
    @Override
    public boolean performClickRight(TextView v) {
        return false;
    }

    /**
     * @param v 被点击到的v;此时应该是该view自身:LineMenuView
     */
    @Override
    public void performSelf(LineMenuView v) {

    }
}