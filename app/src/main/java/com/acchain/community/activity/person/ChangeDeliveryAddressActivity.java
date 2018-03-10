package com.acchain.community.activity.person;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.acchain.community.R;
import com.acchain.community.adapter.ChangeDeliveryAddressAdapter;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.bean.DeliveryAddressBean;
import com.acchain.community.contract.ChangeDeliveryAddressContract;
import com.acchain.community.presenter.ChangeDeliveryAddressPresenter;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.window.CancelConfirmDialogFragment;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

import static com.acchain.community.util.Const.CONST_PER_PAGE_SIZE_BIGGEST;
import static com.acchain.community.util.Const.MODEL_DELIVERY_ADDRESS;

/**
 * function---- ChangeDeliveryAddressActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/15 12:42:34 (+0000).
 */
@Route(path = ARouterConst.Activity_ChangeDeliveryAddressActivity)
public class ChangeDeliveryAddressActivity extends BaseActivity<ChangeDeliveryAddressPresenter> implements ChangeDeliveryAddressContract.View, ChangeDeliveryAddressAdapter.onDeliveryItemClickerListener, CancelConfirmDialogFragment.OnOperateListener {
    @BindView(R.id.xrv_record)
    XRecyclerView mXrvRecord;
    ChangeDeliveryAddressAdapter changeDeliveryAddressAdapter;
    List<DeliveryAddressBean> deliveryAddressBeans;

    /**
     * 当前正在操作的对象
     */
    private int currentSelect;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_change_delivery_address;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //初始化列表
        deliveryAddressBeans = new ArrayList<>();
        changeDeliveryAddressAdapter = new ChangeDeliveryAddressAdapter(deliveryAddressBeans, this);
        mXrvRecord.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mXrvRecord.setAdapter(changeDeliveryAddressAdapter);
        mXrvRecord.setEmptyView(findViewById(R.id.empty_view));
        mXrvRecord.setLoadingMoreEnabled(false);
        mXrvRecord.setPullRefreshEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //请求刷新数据
        mPresenter.getUserAddress(DefaultPreferenceUtil.getInstance().getToken(), 0, CONST_PER_PAGE_SIZE_BIGGEST);
    }

    @Override
    protected void injectSelf() {
        activityComponent.inject(this);
    }

    @OnClick(R.id.bt_add)
    public void onViewClicked() {
        // 新增地址时不对id赋值
        if (deliveryAddressBeans.size() > CONST_PER_PAGE_SIZE_BIGGEST) {
            showToast(String.format(Locale.CHINA, "最多只能添加%d个收货地址", CONST_PER_PAGE_SIZE_BIGGEST));
            return;
        }

        ARouter.getInstance()
                .build(ARouterConst.Activity_EditDeliveryAddressActivity)
                .navigation();
    }

    @Override
    public void onClickDefault(int position, ChangeDeliveryAddressAdapter.DefaultCallback callback) {
        // 设为默认地址
        DeliveryAddressBean bean = deliveryAddressBeans.get(position);
        if(bean.isDefaultAddress()){
           showToast("当前已是默认地址");
           return;
        }
        mPresenter.updateAddress(DefaultPreferenceUtil.getInstance().getToken(),bean.getName(),bean.getMobile(),bean.getProvince(),bean.getCity(),bean.getArea(),bean.getAddress(),bean.getId(),bean.getDefaultStatus());
    }

    @Override
    public void onDelete(int position) {
        currentSelect = position;
        new CancelConfirmDialogFragment()
                .setTitle("确认要删除该地址么?")
                .setCancelText("取消")
                .setConfirmText("删除")
                .setOnOperateListener(this)
                .show(getSupportFragmentManager(), "delete");
    }

    @Override
    public void onEdit(int position) {
        ARouter.getInstance()
                .build(ARouterConst.Activity_EditDeliveryAddressActivity)
                .withObject(MODEL_DELIVERY_ADDRESS, deliveryAddressBeans.get(position))
                .navigation();
    }

    @Override
    public boolean onCancel(Dialog dialog) {
        return false;
    }

    @Override
    public boolean onConfirm(Dialog dialog) {
        mPresenter.deleteAddress(DefaultPreferenceUtil.getInstance().getToken(), deliveryAddressBeans.get(currentSelect).getId());
        return false;
    }

    @Override
    public void getUserAddressFinish(List<DeliveryAddressBean> deliveryAddressBeans) {
        if (deliveryAddressBeans != null) {
            this.deliveryAddressBeans.clear();
            this.deliveryAddressBeans.addAll(deliveryAddressBeans);
            changeDeliveryAddressAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void updateAddressFinish() {
        //更新地址后,需要再次更新界面
        showToast("设置成功");
        mPresenter.getUserAddress(DefaultPreferenceUtil.getInstance().getToken(), 0, CONST_PER_PAGE_SIZE_BIGGEST);
    }

    @Override
    public void deleteAddressFinish() {
        super.deleteAddressFinish();

        //若删除地址成功,则弹出并刷新数据
        showToast("地址已删除");
        mPresenter.getUserAddress(DefaultPreferenceUtil.getInstance().getToken(), 0, CONST_PER_PAGE_SIZE_BIGGEST);
    }
}