package com.acchain.community.activity.person;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.adapter.QuestionAdapter;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.bean.QuestionBean;
import com.acchain.community.contract.QuestionContract;
import com.acchain.community.drawable.RoundRectShapeDrawable;
import com.acchain.community.presenter.QuestionPresenter;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * function---- QuestionActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/22 05:49:41 (+0000).
 */
@Route(path = ARouterConst.Activity_QuestionActivity)
public class QuestionActivity extends BaseActivity<QuestionPresenter> implements QuestionContract.View, AdapterView.OnItemClickListener {

    @BindView(R.id.tv_positive)
    TextView mTvPositive;
    @BindView(R.id.tv_negative)
    TextView mTvNegative;

    @BindView(R.id.lv_questions)
    ListView mLvQuestions;
    List<QuestionBean> datas;
    QuestionAdapter adapter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_question;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //设置边框
        // TODO: 2018/1/22 颜色取值
        mTvPositive.post(() -> mTvPositive.setBackground(new RoundRectShapeDrawable(mTvPositive.getWidth(), mTvPositive.getHeight(), getResources().getColor(R.color.blue_search_background))));
        mTvNegative.post(() -> mTvNegative.setBackground(new RoundRectShapeDrawable(mTvNegative.getWidth(), mTvNegative.getHeight(), getResources().getColor(R.color.blue_search_background))));

        //填充列表信息
        datas = new ArrayList<>();
        adapter = new QuestionAdapter(this, datas);
        mLvQuestions.setAdapter(adapter);
        mLvQuestions.setOnItemClickListener(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        // TODO: 2018/1/22 重新加载数据进行显示
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @OnClick({R.id.tv_positive, R.id.tv_negative})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_positive:
                break;
            case R.id.tv_negative:
                break;
        }
        // TODO: 2018/1/22 处理完事件后退出界面 
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //还是在本页面展示数据
        datas.clear();
        adapter.notifyDataSetChanged();
        ARouter.getInstance().build(ARouterConst.Activity_QuestionActivity).navigation();
    }
}