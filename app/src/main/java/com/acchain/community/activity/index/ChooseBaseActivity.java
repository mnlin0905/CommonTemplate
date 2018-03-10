package com.acchain.community.activity.index;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.bean.AdoptBaseLocation;
import com.acchain.community.contract.ChooseBaseContract;
import com.acchain.community.events.AdoptCode;
import com.acchain.community.events.LoginSuccess;
import com.acchain.community.presenter.ChooseBasePresenter;
import com.acchain.community.rxbus.RxBus;
import com.acchain.community.util.Const;
import com.acchain.community.util.L;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * function---- ChooseBaseActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/02/02 05:50:28 (+0000).
 */
@Route(path = ARouterConst.Activity_ChooseBaseActivity)
public class ChooseBaseActivity extends BaseActivity<ChooseBasePresenter> implements ChooseBaseContract.View {
    @BindView(R.id.tv_base1)
    TextView tvBase1;
    @BindView(R.id.tv_base2)
    TextView tvBase2;
    @BindView(R.id.tv_base3)
    TextView tvBase3;
    private List<AdoptBaseLocation> baseList;
    @Autowired(name = Const.KEY_ADOPT_ID,required = true)
    int adoptId; //领养id,用来查看基地位置
    @Autowired(name = Const.KEY_OBJECT_TYPE,required = true)
     int type;//0--查看视频,1--领养详情界面选择标的,2--领养订单界面选择标的
    @Autowired(name = Const.KEY_PRODUCT_NAME,required = true)
    String productName;//商品名字，用于显示标题

    @Override
    protected int getContentViewId() {
        return R.layout.activity_choose_base;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        setTitle(productName + "基地");
        if (adoptId != 0) {
            mPresenter.findAdoptLocation(adoptId);
        }
        /*收到选择标的完成的消息，将此界面finish*/
        Disposable subscribe = RxBus.getInstance().toObservable(AdoptCode.class).observeOn(AndroidSchedulers.mainThread())
                .subscribe(adoptCode -> {
                    finish();
                });
        mPresenter.addDisposable(subscribe);
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @Override
    public void onFindAdoptLocationFinish(List<AdoptBaseLocation> baseList) {
        if (baseList != null) {
            this.baseList = baseList;
            if (baseList.size() >= 3) {
                tvBase1.setText(baseList.get(0).getLocationName());
                tvBase2.setText(baseList.get(1).getLocationName());
                tvBase3.setText(baseList.get(2).getLocationName());
            } else if (baseList.size() == 2) {
                tvBase1.setText(baseList.get(0).getLocationName());
                tvBase2.setText(baseList.get(1).getLocationName());
            } else if (baseList.size() == 1) {
                tvBase1.setText(baseList.get(0).getLocationName());
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick({R.id.tv_base1, R.id.tv_base2, R.id.tv_base3})
    public void onViewClicked(View view) {
        if (baseList == null)
            return;
        switch (view.getId()) {
            case R.id.tv_base1:
                if (baseList.size() >= 1) {
                    ARouter.getInstance().build(ARouterConst.Activity_AdoptBaseActivity)
                            .withString("title", baseList.get(0).getLocationName())
                            .withInt("type", type)
                            .withInt("adoptId", adoptId)
                            .withInt("locationId", baseList.get(0).getLocaltionId())
                            .navigation();
                }
                break;
            case R.id.tv_base2:
                if (baseList.size() >= 2) {
                    ARouter.getInstance().build(ARouterConst.Activity_AdoptBaseActivity)
                            .withString("title", baseList.get(1).getLocationName())
                            .withInt("type", type)
                            .withInt("adoptId", adoptId)
                            .withInt("locationId", baseList.get(1).getLocaltionId())
                            .navigation();
                }
                break;
            case R.id.tv_base3:
                if (baseList.size() >= 3) {
                    ARouter.getInstance().build(ARouterConst.Activity_AdoptBaseActivity)
                            .withString("title", baseList.get(2).getLocationName())
                            .withInt("type", type)
                            .withInt("adoptId", adoptId)
                            .withInt("locationId", baseList.get(2).getLocaltionId())
                            .navigation();
                }
                break;
        }
    }

}