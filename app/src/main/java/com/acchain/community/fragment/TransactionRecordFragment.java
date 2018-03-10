package com.acchain.community.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.acchain.community.R;
import com.acchain.community.adapter.TransactionRecordAdapter;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseFragment;
import com.acchain.community.base.BaseRecyclerViewHolder;
import com.acchain.community.bean.TransactionRecordBean;
import com.acchain.community.contract.TransactionRecordContract;
import com.acchain.community.presenter.TransactionRecordPresenter;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.acchain.community.util.Const.KEY_ID;
import static com.acchain.community.util.Const.KEY_POSITION;
import static com.acchain.community.util.Const.KEY_TYPE;
import static com.acchain.community.util.Const.CONST_PER_PAGE_SIZE_DEFAULT;

/**
 * function---- TransactionRecordFragment
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/24 11:23:45 (+0000).
 */
@Route(path = ARouterConst.Fragment_TransactionRecordFragment)
public class  TransactionRecordFragment extends BaseFragment<TransactionRecordPresenter> implements TransactionRecordContract.View, BaseRecyclerViewHolder.OnViewClickListener, XRecyclerView.LoadingListener {

    @BindView(R.id.xrv_record)
    XRecyclerView mXrvRecord;
    private ArrayList<TransactionRecordBean> recordBeans;
    private TransactionRecordAdapter recordAdapter;

    /**
     * 当前碎片的类型:
     * 0:全部
     * 1:预购
     * 2:领养
     */
    @Autowired(required = true, name = KEY_POSITION)
    int fragmentType;

    /**
     * 当前页数
     * 是否已经加载了所有的数据
     */
    private int currentPage;
    private boolean isFinish;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_transaction_record;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //XRecyclerView适配
        recordBeans = new ArrayList<>();
        recordAdapter = new TransactionRecordAdapter(recordBeans, this);
        mXrvRecord.setLayoutManager(new LinearLayoutManager(baseActivity, LinearLayoutManager.VERTICAL, false));
        mXrvRecord.setAdapter(recordAdapter);
        mXrvRecord.setEmptyView(rootView.findViewById(R.id.empty_view));
        mXrvRecord.setLoadingMoreEnabled(true);
        mXrvRecord.setPullRefreshEnabled(true);
        mXrvRecord.setLoadingListener(this);

        onRefresh();
    }

    /**
     * @return 生成请求列表时的请求字段
     * null:全部
     * 0:预售
     * 1:领养
     */
    private String getRequestType() {
        switch (fragmentType) {
            case 2://领养
            case 1://预售
                return String.valueOf(fragmentType - 1);
            case 0://全部
            default:
                return null;
        }
    }

    @Override
    protected void injectSelf() {
        fragmentComponent.inject(this);
    }

    @Override
    public void onViewClick(View v, int position) {
        ARouter.getInstance()
                .build(ARouterConst.Activity_TransactionDetailActivity)
                .withInt(KEY_ID,recordBeans.get(position).getId())
                .withInt(KEY_TYPE,recordBeans.get(position).getType())
                .navigation();
    }

    @Override
    public void onRefresh() {
        //加载数据
        currentPage = 0;
        isFinish = false;
        mPresenter.getTransactions(DefaultPreferenceUtil.getInstance().getToken(), getRequestType(), currentPage, CONST_PER_PAGE_SIZE_DEFAULT);
    }

    @Override
    public void onLoadMore() {
        if (isFinish) {
            showToast("暂无更多数据");
            mXrvRecord.loadMoreComplete();
            mXrvRecord.refreshComplete();
            return;
        }

        mPresenter.getTransactions(DefaultPreferenceUtil.getInstance().getToken(), getRequestType(), ++currentPage, CONST_PER_PAGE_SIZE_DEFAULT);
    }

    /**
     * 1.3.8 获取用户交易列表
     *
     * @param transactionRecordBeans 交易记录列表
     */
    @Override
    public void getTransactionsFinish(List<TransactionRecordBean> transactionRecordBeans) {
        mXrvRecord.loadMoreComplete();
        mXrvRecord.refreshComplete();

        if (transactionRecordBeans == null) {
            showToast("网络异常");
            return;
        }

        if (transactionRecordBeans.size() == 0) {
            showToast("暂无更多数据");
            isFinish = true;
            return;
        }

        //如果为第一组,则清除再添加
        if (currentPage == 0) {
            recordBeans.clear();
        }
        recordBeans.addAll(transactionRecordBeans);
        recordAdapter.notifyDataSetChanged();
    }

}