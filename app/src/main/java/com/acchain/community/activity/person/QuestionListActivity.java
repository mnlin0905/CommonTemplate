package com.acchain.community.activity.person;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.acchain.community.R;
import com.acchain.community.adapter.QuestionAdapter;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.bean.QuestionBean;
import com.acchain.community.contract.QuestionListContract;
import com.acchain.community.presenter.QuestionListPresenter;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * function---- QuestionListActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/22 08:32:22 (+0000).
 */
@Route(path = ARouterConst.Activity_QuestionListActivity)
public class QuestionListActivity extends BaseActivity<QuestionListPresenter> implements QuestionListContract.View, AdapterView.OnItemClickListener {

    @BindView(R.id.lv_questions)
    ListView mLvQuestions;
    List<QuestionBean> datas;
    QuestionAdapter adapter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_question_list;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //填充列表信息
        datas = new ArrayList<>();
        adapter = new QuestionAdapter(this, datas);
        mLvQuestions.setAdapter(adapter);
        mLvQuestions.setOnItemClickListener(this);
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //跳转到问题详细信息界面
        ARouter.getInstance()
                .build(ARouterConst.Activity_QuestionActivity)
                // TODO: 2018/1/22 添加参数
                .navigation();
    }
}