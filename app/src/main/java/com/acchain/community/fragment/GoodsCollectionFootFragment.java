package com.acchain.community.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.adapter.GoodsCollectionFootAdapter;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseFragment;
import com.acchain.community.base.BasePresenter;
import com.acchain.community.bean.AdoptGoodDetail;
import com.acchain.community.bean.ExerciseGoodsDetail;
import com.acchain.community.bean.GoodsCollectionFootBean;
import com.acchain.community.bean.PanicGoodDetail;
import com.acchain.community.bean.PreSellGoodsDetail;
import com.acchain.community.contract.GoodsCollectionFootContract;
import com.acchain.community.interfaces.CommonProduceDetail;
import com.acchain.community.interfaces.OnGoodsOperateListener;
import com.acchain.community.presenter.GoodsCollectionFootPresenter;
import com.acchain.community.util.ActivityUtil;
import com.acchain.community.util.Const;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.util.Installation;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;

import static com.acchain.community.util.Const.KEY_POSITION;
import static com.acchain.community.util.Const.CONST_PER_PAGE_SIZE_DEFAULT;

/**
 * function---- GoodsCollectionFootFragment
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/23 07:05:11 (+0000).
 */
@Route(path = ARouterConst.Fragment_GoodsCollectionFootFragment)
public class GoodsCollectionFootFragment extends BaseFragment<GoodsCollectionFootPresenter> implements GoodsCollectionFootContract.View, GoodsCollectionFootAdapter.OnGoodsListener, OnGoodsOperateListener, XRecyclerView.LoadingListener {
    @BindView(R.id.tv_time)
    TextView mTvTime;

    /**
     * 数据源,适配器
     */
    @BindView(R.id.xrv_record)
    XRecyclerView mXrvRecord;
    private ArrayList<GoodsCollectionFootBean> goodsBeans;
    private GoodsCollectionFootAdapter goodsAdapter;
    private boolean editMode;

    /**
     * 当前碎片的类型:
     * 0:全部
     * 1:预购
     * 2:领养
     * 3:行权
     * <p>
     * 足迹类型
     * 5:足迹
     */
    @Autowired(name = KEY_POSITION,required = true)
    int fragmentType;

    /**
     * 当前页数
     * 是否已经加载了所有的数据
     */
    private int currentPage;
    private boolean isFinish;
    private HashMap<GoodsCollectionFootBean, CommonProduceDetail> detailMap;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_goods_collection;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //XRecyclerView适配
        goodsBeans = new ArrayList<>();
        detailMap = new HashMap<>();
        goodsAdapter = new GoodsCollectionFootAdapter(detailMap, baseActivity, goodsBeans, this);
        mXrvRecord.setLayoutManager(new LinearLayoutManager(baseActivity, LinearLayoutManager.VERTICAL, false));
        mXrvRecord.setAdapter(goodsAdapter);
        mXrvRecord.setEmptyView(rootView.findViewById(R.id.empty_view));
        mXrvRecord.setLoadingMoreEnabled(true);
        mXrvRecord.setPullRefreshEnabled(true);
        mXrvRecord.setLoadingListener(this);

        onRefresh();
    }

    @Override
    protected void injectSelf() {
        fragmentComponent.inject(this);
    }

    @Override
    public void onClickFindSimilar(int position, View v) {

    }

    @Override
    public void onClickShoppingCart(int position, View v) {
        // TODO: 2018/2/26 添加到购物车

    }

    @Override
    public void onClickShare(int position, View v) {

    }

    @Override
    public void onClickIcon(int position, View v) {

    }

    @Override
    public void onClickSelf(int position, View v) {

    }

    /**
     * @return true表示为足迹类型
     */
    private boolean isGoodFootType() {
        return fragmentType == 5;
    }

    /**
     * 开启编辑模式
     *
     * @return 如果开启成功, 返回true
     */
    @Override
    public boolean onEditModeBegin() {
        if (goodsBeans.size() == 0 || !ActivityUtil.isViewVisible(mXrvRecord) || mXrvRecord.getChildCount() == 0) {
            showToast("没有数据,不可编辑");
            return false;
        }
        mXrvRecord.stopScroll();
        goodsAdapter.setEditMode(editMode = true);
        return true;
    }

    /**
     * 退出编辑模式
     */
    @Override
    public void onEditModeExit() {
        if (editMode) {
            mXrvRecord.stopScroll();
            goodsAdapter.setEditMode(editMode = false);
        } else {
            showToast("界面显示异常,请重新重新进入");
        }
    }

    /**
     * @param selectAll true表示全部选中
     */
    @Override
    public void onSelectAll(boolean selectAll) {
        for (GoodsCollectionFootBean goodsBean : goodsBeans) {
            goodsBean.setSELECTED_STATUS(selectAll);
        }
        goodsAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onDelete(int[] positions) {
        if (isGoodFootType()) {
            mPresenter.delAccessLogs(DefaultPreferenceUtil.getInstance().getToken(), Arrays.toString(positions));
        } else {
            mPresenter.delCollections(DefaultPreferenceUtil.getInstance().getToken(), Arrays.toString(positions));
        }
        return false;
    }

    @Override
    public int[] getSelectItem() {
        LinkedList<Integer> positions = new LinkedList<Integer>();
        for (int i = 0; i < goodsBeans.size(); i++) {
            if (goodsBeans.get(i).isSELECTED_STATUS()) {
                positions.add(i);
            }
        }
        if (positions.size() == 0) {
            return new int[0];
        }
        int[] result = new int[positions.size()];
        for (int i = 0; i < positions.size(); i++) {
            result[i] = positions.get(i);
        }
        return result;
    }

    /**
     * 让adapter出现动画效果
     */
    private void adapterAnimate(int shiftWidth) {
        //先停止滑动效果
        mXrvRecord.stopScroll();

        //再让内容滑动
        int childCount = mXrvRecord.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = mXrvRecord.getChildAt(i);
            View viewById = child.findViewById(R.id.cl_content);
            if (viewById != null) {
                viewById.animate()
                        .translationX(shiftWidth)
                        .setDuration(300)
                        .start();
            }
        }

        goodsAdapter.notifyDataSetChanged();
    }

    @Override
    public void getUserCollectionsFinish(List<GoodsCollectionFootBean> goodsCollectionFootBeans) {
        mXrvRecord.loadMoreComplete();
        mXrvRecord.refreshComplete();

        if (goodsCollectionFootBeans == null) {
            showToast("网络异常");
            return;
        }

        if (goodsCollectionFootBeans.size() == 0) {
            showToast("暂无更多数据");
            isFinish = true;
            return;
        }

        //如果为第一组,则清除再添加
        if (currentPage == 0) {
            goodsBeans.clear();
        }
        goodsBeans.addAll(goodsCollectionFootBeans);
        goodsAdapter.notifyDataSetChanged();

        //获取到数据后请求网络获取价格等信息,需要让请求返回的信息与参与请求的对象对应
        for (GoodsCollectionFootBean bean : goodsCollectionFootBeans) {
            loadDetailData(bean,tag -> {
                //当返回有数据时,则刷新视图列表
                if (tag != null) {
                    detailMap.put(bean, tag);
                    goodsAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    /**
     * 加载更详细的数据
     *
     * @param bean 数据对应商品
     */
    @SuppressWarnings("unchecked")
    private void loadDetailData(GoodsCollectionFootBean bean,BasePresenter.HttpCallback<? extends CommonProduceDetail> callback) {
        switch (bean.getType()) {
            case Const.TYPE_PRODUCT_PRE_ORDER://预售
                mPresenter.getPreSellProductDetail(bean.getProductId(), (BasePresenter.HttpCallback<PreSellGoodsDetail>) callback);
                break;
            case Const.TYPE_PRODUCT_ADOPT://领养
                mPresenter.getAdoptProductDetail(bean.getProductId(), (BasePresenter.HttpCallback<AdoptGoodDetail>) callback);
                break;
            case Const.TYPE_PRODUCT_EXERCISE://行权
                mPresenter.getExerciseProductDetail(bean.getProductId(), (BasePresenter.HttpCallback<ExerciseGoodsDetail>) callback);
                break;
            case Const.TYPE_PRODUCT_PANIC://抢购
                // TODO: 2018/2/26 活动id无法获取
                mPresenter.getFlashSalesDetail(bean.getProductId(),1,(BasePresenter.HttpCallback<PanicGoodDetail>) callback);
                break;
        }
    }

    @Override
    public void onRefresh() {
        //加载数据
        currentPage = 0;
        isFinish = false;
        if (isGoodFootType()) {
            mPresenter.getAccessLog(DefaultPreferenceUtil.getInstance().getToken(), Installation.id(baseActivity), currentPage, CONST_PER_PAGE_SIZE_DEFAULT);
        } else {
            mPresenter.getUserCollections(DefaultPreferenceUtil.getInstance().getToken(), null, String.valueOf(fragmentType), currentPage, CONST_PER_PAGE_SIZE_DEFAULT);
        }
    }

    @Override
    public void onLoadMore() {
        if (isFinish) {
            showToast("暂无更多数据");
            mXrvRecord.loadMoreComplete();
            mXrvRecord.refreshComplete();
            return;
        }

        if (isGoodFootType()) {
            mPresenter.getAccessLog(DefaultPreferenceUtil.getInstance().getToken(), Installation.id(baseActivity), ++currentPage, CONST_PER_PAGE_SIZE_DEFAULT);
        } else {
            mPresenter.getUserCollections(DefaultPreferenceUtil.getInstance().getToken(), null, String.valueOf(fragmentType), ++currentPage, CONST_PER_PAGE_SIZE_DEFAULT);
        }
    }

    @Override
    public void delCollectionsFinish() {
        showToast("删除成功");
    }

    @Override
    public void getAccessLogFinish(List<GoodsCollectionFootBean> accessLogBeans) {
        getUserCollectionsFinish(accessLogBeans);
    }

    @Override
    public void delAccessLogsFinish() {
        delCollectionsFinish();
    }
}