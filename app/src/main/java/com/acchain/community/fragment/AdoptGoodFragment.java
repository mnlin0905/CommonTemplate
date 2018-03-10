package com.acchain.community.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseFragment;
import com.acchain.community.bean.AdoptGoodDetail;
import com.acchain.community.bean.ConfirmAdoptOrder;
import com.acchain.community.contract.AdoptGoodContract;
import com.acchain.community.events.AdoptCode;
import com.acchain.community.presenter.AdoptGoodPresenter;
import com.acchain.community.rxbus.RxBus;
import com.acchain.community.util.Const;
import com.acchain.community.util.DateUtil;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.util.L;
import com.acchain.community.view.NumberProgressView;
import com.acchain.community.window.dialog.EditAdoptObjectDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * function---- AdoptGoodFragment
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/16 03:01:17 (+0000).
 */
@Route(path = ARouterConst.Fragment_AdoptGoodsFragment)
public class AdoptGoodFragment extends BaseFragment<AdoptGoodPresenter> implements AdoptGoodContract.View {

    @BindView(R.id.tv_news)
    TextView tvNews;
    @BindView(R.id.tv_video_monitor)
    TextView tvVideoMonitor;
    @BindView(R.id.tv_pre_sell_record)
    TextView tvPreSellRecord;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.time_limit)
    TextView timeLimit;
    @BindView(R.id.count_limit)
    TextView countLimit;
    @BindView(R.id.tv_default_num)
    TextView tvDefaultNum;
    @BindView(R.id.cb_agreement)
    CheckBox cbAgreement;
    @BindView(R.id.start_time)
    TextView startTime;
    @BindView(R.id.end_time)
    TextView endTime;
    @BindView(R.id.number_progress)
    NumberProgressView numberProgressView;
    private EditAdoptObjectDialog objectDialog;
    private AdoptGoodDetail adoptGoodDetail;
    //外层的对象
    private List<AdoptGoodDetail.DefaultAdoptCodeBean> adoptObjects = new ArrayList<>();
    private String adoptCodeIds;


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_adopt_goods;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        /*收到选择标的完成的消息，刷新dialog*/
        Disposable subscribe = RxBus.getInstance().toObservable(AdoptCode.class).observeOn(AndroidSchedulers.mainThread())
                .subscribe(adoptCode -> {
                    if (adoptCode.getType() == 1) {
                        adoptObjects.addAll(adoptCode.getList());
                        if (objectDialog != null) {
                            objectDialog.setData(adoptObjects);
                        }
                    }
                });
        mPresenter.addDisposable(subscribe);
    }

    @Override
    protected void injectSelf() {
        fragmentComponent.inject(this);
    }

    @OnClick({R.id.tv_share, R.id.tv_news, R.id.tv_video_monitor, R.id.tv_pre_sell_record, R.id.rl_random_number})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_share:
                break;
            case R.id.tv_news:
                ARouter.getInstance().build(ARouterConst.Activity_PreSellNewsActivity).navigation();
                break;
            case R.id.tv_video_monitor:
                ARouter.getInstance().build(ARouterConst.Activity_ChooseBaseActivity).withInt(Const.KEY_ADOPT_ID, adoptGoodDetail.getId()).withInt(Const.KEY_OBJECT_TYPE, Const.TYPE_WATCH_VIDEO).withString(Const.KEY_PRODUCT_NAME, adoptGoodDetail.getPropertyName()).navigation();
                break;
            case R.id.tv_pre_sell_record:
                ARouter.getInstance().build(ARouterConst.Activity_PreSellRecordActivity).navigation();
                break;
            case R.id.rl_random_number:
                if (objectDialog == null) {
                    objectDialog = new EditAdoptObjectDialog(baseActivity, adoptObjects, adoptGoodDetail.getProducts().getProductImg(), adoptGoodDetail.getAdoptPrice());
                    objectDialog.setOnObjectListener(new EditAdoptObjectDialog.OnObjectListener() {
                        @Override
                        public void onAddObject() {
                            ARouter.getInstance().build(ARouterConst.Activity_ChooseBaseActivity).withInt(Const.KEY_ADOPT_ID, adoptGoodDetail.getId()).withInt(Const.KEY_OBJECT_TYPE, Const.TYPE_CHOOSE_OBJECT).withString(Const.KEY_PRODUCT_NAME, adoptGoodDetail.getPropertyName()).navigation();
                        }

                        @Override
                        public void onSure(List<AdoptGoodDetail.DefaultAdoptCodeBean> objects) {
                            /*点了确定，就覆盖外层的对象*/
                            adoptObjects.clear();
                            adoptObjects.addAll(objects);
                            setDefaultNum();
                        }
                    });
                    objectDialog.show();
                } else {
                    objectDialog.show();
                    objectDialog.setData(adoptObjects);
                }
                break;
        }
    }

    public void refreshUi(AdoptGoodDetail adoptGoodDetail) {
        this.adoptGoodDetail = adoptGoodDetail;
        AdoptGoodDetail.DefaultAdoptCodeBean defaultAdoptCode = adoptGoodDetail.getDefaultAdoptCode();
        adoptObjects.add(defaultAdoptCode);
        Glide.with(baseActivity).load(baseActivity.getString(R.string.test_base_image_address) + adoptGoodDetail.getProducts().getProductImg());
        tvShare.setText(adoptGoodDetail.getAdoptName());
        L.i("count=" + adoptGoodDetail.getAdoptedCount() + "   Circulation=" + adoptGoodDetail.getAdoptCirculation());
        numberProgressView.setProgress((adoptGoodDetail.getAdoptedCount() * 100) / adoptGoodDetail.getAdoptCirculation());
        tvPrice.setText(adoptGoodDetail.getAdoptPrice() + "元");
        timeLimit.setText(DateUtil.getBetweenDay(adoptGoodDetail.getAdoptStartTime(), adoptGoodDetail.getAdoptEndTime()) + "天");
        countLimit.setText(adoptGoodDetail.getMaxAdoptQty() + "");
        startTime.setText(adoptGoodDetail.getExerciseStartTime());
        endTime.setText(adoptGoodDetail.getExerciseEndTime());
        setDefaultNum();
    }

    /*刷新已选择的显示*/
    public void setDefaultNum() {
        String numStr = "";
        adoptCodeIds = "";
        itemCount = adoptObjects.size();
        if (adoptObjects.size() != 0) {
            for (AdoptGoodDetail.DefaultAdoptCodeBean object : adoptObjects) {
                numStr += object.getAdoptCode() + ",";
                adoptCodeIds += object.getId() + ",";
            }
            numStr = numStr.substring(0, numStr.length() - 1);
            adoptCodeIds = adoptCodeIds.substring(0, adoptCodeIds.length() - 1);
        }
        if (TextUtils.isEmpty(numStr))
            numStr = "请选择标的";
        tvDefaultNum.setText(numStr);
    }

    private int itemCount = 1;

    /*给activity调*/
    public void addCart() {
        if (adoptGoodDetail != null) {
            L.i("adoptCodeIds="+adoptCodeIds);
            mPresenter.checkAndAddCart(2, adoptGoodDetail.getProducts().getId(), adoptGoodDetail.getId(), itemCount, null, null, adoptCodeIds, DefaultPreferenceUtil.getInstance().getToken());
        }
    }

    public boolean isAgreement() {
        return cbAgreement.isChecked();
    }

    /*确认订单，给activity调*/
    public void confirmOrder() {
        if (itemCount == 0) {
            showToast("请选择标的");
        } else
            mPresenter.confirmOrder(DefaultPreferenceUtil.getInstance().getToken(), 2, itemCount, adoptGoodDetail.getProductId(), null, null, null, adoptCodeIds, 4);
    }

    @Override
    public void onConfirmOrderFinish(Object object, int type) {
        super.onConfirmOrderFinish(object, type);
        if (type == 4) {
            ConfirmAdoptOrder confirmAdoptOrder = (ConfirmAdoptOrder) object;
            Bundle bundle = new Bundle();
            bundle.putSerializable("confirmAdoptOrder", confirmAdoptOrder);
            ARouter.getInstance().build(ARouterConst.Activity_ConfirmAdoptOrderActivity).withBundle("bundle", bundle).navigation();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (objectDialog != null) {
            objectDialog.unbind();
        }
    }
}