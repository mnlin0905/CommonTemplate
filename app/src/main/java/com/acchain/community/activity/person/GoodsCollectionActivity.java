package com.acchain.community.activity.person;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.interfaces.OnGoodsOperateListener;
import com.acchain.community.util.Const;
import com.acchain.community.util.ProxyViewAnimator;
import com.acchain.community.view.DisableScrollViewPager;
import com.acchain.community.view.DisallowClickTabLayout;
import com.acchain.community.view.TabPagerCombineLayout;
import com.acchain.community.window.popupwindow.ListViewPopup;
import com.acchain.community.window.CancelConfirmDialogFragment;
import com.acchain.community.window.CommonPopWindow;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ScreenUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * function---- GoodsCollectionActivity
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/23 06:06:34 (+0000).
 */
@Route(path = ARouterConst.Activity_GoodsCollectionActivity)
public class GoodsCollectionActivity extends BaseActivity implements TabPagerCombineLayout.onTabPagerListener, ListViewPopup.OnItemListener, CancelConfirmDialogFragment.OnOperateListener, DisableScrollViewPager.ManageScrollInterface, DisallowClickTabLayout.ManageClickInterface {
    protected List<OnGoodsOperateListener> fragments;

    @BindView(R.id.tpcl_goods)
    TabPagerCombineLayout mTpclGoods;
    @BindView(R.id.ll_util)
    LinearLayout mLlUtil;
    @BindView(R.id.sw_select)
    Switch mSwSelect;
    @BindView(R.id.tv_delete)
    TextView mTvDelete;

    /**
     * 当前fragment显示的序号
     * 是否为编辑模式
     */
    private int currentPosition;
    private boolean editMode;

    /**
     * 动画类
     * 删除时选中的items
     */
    private ProxyViewAnimator proxyViewAnimator;
    private int[] selectItems;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_goods_collection;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void initData(Bundle savedInstanceState) {
        //初始化列表
        initFragment();

        //设置工具栏
        proxyViewAnimator = new ProxyViewAnimator(mLlUtil);
        mLlUtil.measure(View.MeasureSpec.makeMeasureSpec(ScreenUtils.getScreenWidth(), View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(getResources().getDimensionPixelSize(R.dimen.max_view_height_64dp), View.MeasureSpec.AT_MOST));
        proxyViewAnimator.setOriginHeight(mLlUtil.getMeasuredHeight());
        proxyViewAnimator.setHeights(0);
    }

    /**
     * 初始化碎片数据
     */
    protected void initFragment() {
        fragments = new ArrayList<>();
        String[] stringArray = getResources().getStringArray(R.array.goods_kind);
        for (int i = 0; i < stringArray.length; i++) {
            fragments.add((OnGoodsOperateListener) ARouter.getInstance()
                    .build(ARouterConst.Fragment_GoodsCollectionFootFragment)
                    .withInt(Const.KEY_POSITION, i)
                    .navigation());
        }
        mTpclGoods.provideFragmentManager(getSupportFragmentManager())
                .provideFragments((List) fragments)
                .provideTitles(stringArray)
                .provideListener(this)
                .provideManageScrollInterface(this)
                .provideManageClickInterface(this)
                .combine();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_and_more, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit://编辑/完成
                //如果是编辑模式,则模仿返回键
                if (editMode) {
                    onBackPressed();
                    break;
                }

                //如果非编辑模式,则判断是否可以进入编辑模式,然后进行动态改变
                if (judgeCanPerform() && fragments.get(currentPosition).onEditModeBegin()) {
                    ObjectAnimator.ofFloat(proxyViewAnimator, "heights", 0, 1).setDuration(300).start();
                    editMode = true;
                    invalidateOptionsMenu();
                }
                break;
            case R.id.action_more://更多,显示popWindow
                CommonPopWindow.getInstance(this, this)
                        .showAtLocation(toolbar, Gravity.TOP | Gravity.RIGHT, 0, toolbar.getHeight());
                break;
        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.getItem(0).setTitle(editMode ? "完成" : "编辑");
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onPagerAppear(int position) {
        currentPosition = position;
    }

    @Override
    public void onPagerReAppear(int position) {

    }

    @Override
    public void onPagerDisappear(int position) {

    }

    @Override
    protected void injectSelf() {
    }

    @Override
    public void onItemClick(int position) {
        // TODO: 2018/1/23 menu功能:搜索
    }

    @Override
    public void onBackPressed() {
        if (editMode) {
            fragments.get(currentPosition).onEditModeExit();
            ObjectAnimator.ofFloat(proxyViewAnimator, "heights", 1, 0).setDuration(300).start();
            editMode = false;
            invalidateOptionsMenu();
            return;
        }
        super.onBackPressed();
    }

    @OnClick({R.id.sw_select, R.id.tv_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sw_select://全不选/全选
                if (editMode) {
                    fragments.get(currentPosition).onSelectAll(mSwSelect.isChecked());
                }
                break;
            case R.id.tv_delete://如果有选中的内容,则提示是否删除
                if (editMode) {
                    selectItems = fragments.get(currentPosition).getSelectItem();
                    if (selectItems.length > 0) {
                        new CancelConfirmDialogFragment().setCancelText("取消")
                                .setConfirmText("删除")
                                .setTitle(String.format(Locale.CHINA, "是否删除选中的 %d 条收藏记录", selectItems.length))
                                .setOnOperateListener(this)
                                .show(getSupportFragmentManager(), "delete");
                    } else {
                        showToast("没有选中的内容");
                    }
                }
                break;
        }
    }

    /**
     * 检测当前是否可以让子fragment来触发事件
     */
    private boolean judgeCanPerform() {
        return fragments != null && fragments.size() > currentPosition && fragments.get(currentPosition) != null;
    }

    @Override
    public boolean onCancel(Dialog dialog) {
        return false;
    }

    @Override
    public boolean onConfirm(Dialog dialog) {
        if (editMode && fragments.get(currentPosition).onDelete(selectItems)) {
            fragments.get(currentPosition).onEditModeExit();
        }
        return false;
    }

    /**
     * 控制当前情况下是否可以滑动viewpager
     */
    @Override
    public boolean currentCanScroll() {
        return !editMode;
    }

    @Override
    public boolean currentCanClick() {
        return !editMode;
    }
}