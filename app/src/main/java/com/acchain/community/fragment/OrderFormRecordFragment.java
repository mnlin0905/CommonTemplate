package com.acchain.community.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.acchain.community.R;
import com.acchain.community.adapter.OrderFormRecordAdapter;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseFragment;
import com.acchain.community.base.BaseRecyclerViewHolder;
import com.acchain.community.bean.OrderFormRecordBean;
import com.acchain.community.contract.OrderFormRecordContract;
import com.acchain.community.presenter.OrderFormRecordPresenter;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;

import static com.acchain.community.util.Const.KEY_POSITION;

/**
 * function---- OrderFormRecordFragment
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/25 07:04:18 (+0000).
 */
@Route(path = ARouterConst.Fragment_OrderFormRecordFragment)
public class OrderFormRecordFragment extends BaseFragment<OrderFormRecordPresenter> implements OrderFormRecordContract.View, BaseRecyclerViewHolder.OnViewClickListener {
    @BindView(R.id.xrv_record)
    XRecyclerView mXrvRecord;
    private ArrayList<OrderFormRecordBean> recordBeans;
    private OrderFormRecordAdapter recordAdapter;

    /**
     * 当前碎片的类型:
     * 0:全部
     * 1:预购
     * 2:领养
     */
    @Autowired(name = KEY_POSITION,required = true)
     int fragmentType;

    @Override
    protected int getContentViewId() {
         return R.layout.fragment_order_form_record;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //XRecyclerView适配
        recordBeans = new ArrayList<>();
        recordBeans.add(new OrderFormRecordBean());
        recordBeans.add(new OrderFormRecordBean());
        recordBeans.add(new OrderFormRecordBean());
        recordBeans.add(new OrderFormRecordBean());
        recordBeans.add(new OrderFormRecordBean());
        recordBeans.add(new OrderFormRecordBean());
        recordAdapter = new OrderFormRecordAdapter(recordBeans, this);
        mXrvRecord.setLayoutManager(new LinearLayoutManager(baseActivity, LinearLayoutManager.VERTICAL, false));
        mXrvRecord.setAdapter(recordAdapter);
        mXrvRecord.setEmptyView(rootView.findViewById(R.id.empty_view));
        mXrvRecord.setLoadingMoreEnabled(false);
        mXrvRecord.setPullRefreshEnabled(false);

        //onRefresh();
    }

    @Override
    protected void injectSelf() {
        fragmentComponent.inject(this);
    }

    @Override
    public void onViewClick(View v, int position) {
        ARouter.getInstance()
                .build(ARouterConst.Activity_OrderFormDetailActivity)
                // TODO: 2018/1/25 传入参数
                .navigation();
    }
}