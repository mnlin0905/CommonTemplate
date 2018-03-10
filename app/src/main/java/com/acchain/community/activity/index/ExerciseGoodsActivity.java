package com.acchain.community.activity.index;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.acchain.community.R;
import com.acchain.community.adapter.ActivityViewPagerAdapter;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.base.BaseFragment;
import com.acchain.community.bean.ExerciseGoodsDetail;
import com.acchain.community.contract.ExerciseGoodsContract;
import com.acchain.community.fragment.ExerciseDetailFragment;
import com.acchain.community.fragment.ExerciseGoodFragment;
import com.acchain.community.presenter.ExerciseGoodsPresenter;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.util.Installation;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * function---- ExerciseGoodsActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/16 08:18:01 (+0000).
 */
@Route(path = ARouterConst.Activity_ExerciseGoodsActivity)
public class ExerciseGoodsActivity extends BaseActivity<ExerciseGoodsPresenter> implements ExerciseGoodsContract.View {
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private ActivityViewPagerAdapter activityViewPagerAdapter;
    private List<BaseFragment> fragments = new ArrayList<>();
    private ExerciseGoodFragment exerciseGoodFragment;
    private ExerciseDetailFragment exerciseDetailFragment;
    private ExerciseGoodsDetail exerciseGoodsDetail;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_exercise_goods;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_exercise_goods, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.more:

                break;
        }
        return true;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        setTitle("");
        //初始化tabLayout和viewpager
        fragments = new ArrayList<>();
        exerciseGoodFragment = new ExerciseGoodFragment();
        exerciseDetailFragment = new ExerciseDetailFragment();
        fragments.add(exerciseGoodFragment);
        fragments.add(exerciseDetailFragment);
        fragments.add(new ExerciseDetailFragment());
        String[] tabs = getResources().getStringArray(R.array.index_pre_sell_goods);
        activityViewPagerAdapter = new ActivityViewPagerAdapter(getSupportFragmentManager(), fragments) {
            @Override
            public CharSequence getPageTitle(int position) {
                return tabs[position];
            }
        };
        viewPager.setAdapter(activityViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        int exerciseId = getIntent().getIntExtra("exerciseId", 0);//商品id
        mPresenter.getExerciseProductDetail(exerciseId);
        mPresenter.addUserFooter(Installation.id(this),DefaultPreferenceUtil.getInstance().getToken(),exerciseId,1,exerciseId);
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }


    @OnClick({R.id.tv_index,R.id.tv_collect,R.id.tv_customer_service,R.id.tv_exercise_now})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_index:
                ARouter.getInstance().build(ARouterConst.Activity_MainActivity).navigation();
                break;
            case R.id.tv_collect://抢购的不可收藏
                mPresenter.addOrDeleteCollection(DefaultPreferenceUtil.getInstance().getToken(),exerciseGoodsDetail.getExerciseId(),3,exerciseGoodsDetail.getExerciseId());
                break;
            case R.id.tv_customer_service:
                break;
            case R.id.tv_exercise_now:
                exerciseGoodFragment.confirmOrder();
                break;
        }
    }

    @Override
    public void onAddOrDeleteCollectFinish(int type, boolean b) {
        super.onAddOrDeleteCollectFinish(type, b);
        if(type==3) {
            if (b) {
                showToast("操作成功");
            } else
                showToast("操作失败");
        }
    }


    @Override
    public void onExerciseProductDetailFinish(ExerciseGoodsDetail exerciseGoodsDetail) {
        if(exerciseGoodsDetail==null){

        }else {
            this.exerciseGoodsDetail=exerciseGoodsDetail;
            exerciseGoodFragment.refreshUi(exerciseGoodsDetail);
            exerciseDetailFragment.refreshUi(exerciseGoodsDetail);
        }
    }
}