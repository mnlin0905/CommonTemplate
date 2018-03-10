package com.acchain.community.activity.index;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.adapter.AdoptBaseAdapter;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.bean.AdoptBaseCode;
import com.acchain.community.bean.AdoptGoodDetail;
import com.acchain.community.contract.AdoptBaseContract;
import com.acchain.community.events.AdoptCode;
import com.acchain.community.events.LoginSuccess;
import com.acchain.community.presenter.AdoptBasePresenter;
import com.acchain.community.rxbus.RxBus;
import com.acchain.community.util.L;
import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * function---- AdoptBaseActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/19 07:23:18 (+0000).
 */
@Route(path = ARouterConst.Activity_AdoptBaseActivity)
public class AdoptBaseActivity extends BaseActivity<AdoptBasePresenter> implements AdoptBaseContract.View {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_choose_code)
    TextView tvChooseCode;
    @BindView(R.id.tv_sure)
    TextView tvSure;
    @BindView(R.id.ll_choose)
    LinearLayout llChoose;
    private AdoptBaseAdapter adoptBaseAdapter;
    private int type;//0--查看视频,1--领养详情界面选择标的,2--领养订单界面选择标的
    private AdoptBaseCode adoptBaseCode;
    private List<AdoptGoodDetail.DefaultAdoptCodeBean> list = new ArrayList<>();//自定义一个添加标的的dialog可以直接用的对象
    private int count;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_adopt_base;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        int locationId = getIntent().getIntExtra("locationId", 0);
        int adoptId = getIntent().getIntExtra("adoptId", 0);
        type = getIntent().getIntExtra("type", 0);
        String title = getIntent().getStringExtra("title");
        mPresenter.chooseAdoptCode(adoptId, locationId, 1);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adoptBaseAdapter = new AdoptBaseAdapter(this, type);
        recycler.setAdapter(adoptBaseAdapter);
        setTitle(title);
        llChoose.setVisibility(type == 0 ? View.GONE : View.VISIBLE);

        if (type == 1||type==2) {
            adoptBaseAdapter.setOnChooseListener(new AdoptBaseAdapter.OnChooseListener() {
                @Override
                public void onChoose(int position, boolean isChecked) {
                    setCount();
                }
            });
        }
        setCount();
    }

    public void setCount() {
        count = 0;
        list.clear();
        if (adoptBaseCode != null) {
            for (AdoptBaseCode.ListBean bean : adoptBaseCode.getList()) {
                if (bean.isChoose()) {
                    AdoptGoodDetail.DefaultAdoptCodeBean defaultAdoptCodeBean = new AdoptGoodDetail.DefaultAdoptCodeBean(bean.getVideoSurveillanceInfo(), bean.getAdoptCode(), bean.getPhotoAlbumInfo(), bean.getLocationId(), bean.getAdoptId(), bean.getId(), bean.getAdoptStatus());
                    list.add(defaultAdoptCodeBean);
                    count++;
                }
            }
        }
        tvChooseCode.setText("已选择" + count + "个标的");
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @Override
    public void onChooseAdoptCodeFinish(AdoptBaseCode adoptBaseCode) {
        if (adoptBaseCode != null) {
            this.adoptBaseCode = adoptBaseCode;
            adoptBaseAdapter.setData(adoptBaseCode.getList());
        }
    }

    @OnClick(R.id.tv_sure)
    public void onViewClicked() {
        RxBus.getInstance().post(new AdoptCode(list,type));
        finish();
    }
}