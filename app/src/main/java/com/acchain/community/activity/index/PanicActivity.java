package com.acchain.community.activity.index;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import com.acchain.community.R;
import com.acchain.community.adapter.PanicAdapter;
import com.acchain.community.adapter.PanicTimeAdapter;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.bean.PanicGood;
import com.acchain.community.contract.PanicContract;
import com.acchain.community.presenter.PanicPresenter;
import com.acchain.community.util.DateUtil;
import com.acchain.community.util.L;
import com.acchain.community.util.ScreenUtils;
import com.acchain.community.view.TimeView;
import com.acchain.community.window.popupwindow.ClassificationPopup;
import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * function---- PanicActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/06 07:35:16 (+0000).
 */
@Route(path = ARouterConst.Activity_PanicActivity)
public class PanicActivity extends BaseActivity<PanicPresenter> implements PanicContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.time_view)
    TimeView timeView;
    @BindView(R.id.panic_recycler)
    RecyclerView panicRecycler;
    @BindView(R.id.time_recycler)
    RecyclerView timeRecycler;
    private ClassificationPopup classificationPopup;
    /*限时抢购的数据，先保存起来*/
    private List<PanicGood> flashSales;
    private PanicAdapter panicAdapter;
    private List<PanicGood.ProductListBean> productList;
    private PanicTimeAdapter panicTimeAdapter;
    private List<PanicTime> timeList = new ArrayList<>();

    @Override
    protected int getContentViewId() {
        return R.layout.activity_panic;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_panic, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.classification:
                if (classificationPopup == null) {
                    classificationPopup = new ClassificationPopup(this);
                    classificationPopup.setOnItemListener(new ClassificationPopup.OnItemListener() {
                        @Override
                        public void onItemClick(int position) {

                        }
                    });
                }
                classificationPopup.showAtLocation(toolbar, Gravity.TOP, 0, toolbar.getHeight() + ScreenUtils.getStatusHeight(this));
                break;
        }
        return true;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        timeRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        panicTimeAdapter = new PanicTimeAdapter(this);
        timeRecycler.setAdapter(panicTimeAdapter);
        timeView.setOnTimeFinish(() -> {
            //时间到了
            handPanicData();
        });
        panicRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        panicAdapter = new PanicAdapter(this);
        panicRecycler.setAdapter(panicAdapter);
        mPresenter.getFlashSales();
    }

    @Override
    public void onFlashSalesFinish(List<PanicGood> panicGood) {
        if (panicGood == null) {
            L.i("failed");
        } else {
            this.flashSales = panicGood;
            handPanicData();
        }
    }

    /*处理限时抢购*/
    private void handPanicData() {
        for (PanicGood panicGood : flashSales) {
            PanicTime panicTime = new PanicTime();
            panicTime.time = DateUtil.getHourMinByDate(panicGood.getBeginTime());
            long betweenHour = DateUtil.isBetweenHour(panicGood.getBeginTime(), panicGood.getEndTime());
            if (betweenHour == 0) {//已经过了
                panicTime.type = 0;
//                /*为了测试*/
//                panicAdapter.setData(panicGood);
            } else if (betweenHour == 1) {//还未到时间
                panicTime.type = 1;
//                  /*为了测试*/
//                panicAdapter.setData(panicGood);
            } else {//在范围内
                panicTime.type =2;
                timeView.start((int) betweenHour);
                panicAdapter.setData(panicGood);
            }
            timeList.add(panicTime);
        }
        panicTimeAdapter.setData(timeList);
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timeView.stop();
        if (classificationPopup != null) {
            classificationPopup.unbind();
        }
    }

    /*时间的对象*/
    public static class PanicTime {
        public String time;
        public int type;
    }
}