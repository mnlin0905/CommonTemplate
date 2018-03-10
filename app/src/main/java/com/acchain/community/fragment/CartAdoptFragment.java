package com.acchain.community.fragment;

import com.acchain.community.R;
import com.acchain.community.adapter.CartAdoptAdapter;
import com.acchain.community.adapter.CartPreAdapter;
import com.acchain.community.bean.AdoptGoodDetail;
import com.acchain.community.bean.CartAdoptGoods;
import com.acchain.community.bean.CartPreGoods;
import com.acchain.community.bean.CommonCart;
import com.acchain.community.bean.ConfirmCartAdoptOrder;
import com.acchain.community.bean.ConfirmCartPanicOrder;
import com.acchain.community.bean.StoreInfo;
import com.acchain.community.events.AdoptCode;
import com.acchain.community.presenter.CartAdoptPresenter;
import com.acchain.community.contract.CartAdoptContract;
import com.acchain.community.base.BaseFragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acchain.community.rxbus.RxBus;
import com.acchain.community.util.Const;
import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.util.L;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.acchain.community.arouter.ARouterConst;
import com.alibaba.android.arouter.launcher.ARouter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * function---- CartAdoptFragment
 * <p>
 * Created(Gradle default create) by ACChain on 2018/02/24 02:40:51 (+0000).
 */
@Route(path = ARouterConst.Fragment_CartAdoptFragment)
public class CartAdoptFragment extends BaseFragment<CartAdoptPresenter> implements CartAdoptContract.View, CartAdoptAdapter.CheckInterface, CartAdoptAdapter.ModifyInterface, CartAdoptAdapter.GroupEditorInterface {
    @BindView(R.id.exListView)
    ExpandableListView exListView;
    @BindView(R.id.ll_clearing)
    LinearLayout llClearing;
    @BindView(R.id.cb_clearing)
    CheckBox cbClearing;
    @BindView(R.id.tv_all_money)
    TextView tvAllMoney;
    @BindView(R.id.tv_clearing)
    TextView tvClearing;
    //编辑
    @BindView(R.id.ll_edit)
    LinearLayout llEdit;
    @BindView(R.id.cb_edit)
    CheckBox cbEdit;
    @BindView(R.id.tv_move_to_favorite)
    TextView tvMoveToFavorite;//移入收藏夹
    @BindView(R.id.tv_all_delete)
    TextView tvAllDelete;

    private CartAdoptAdapter cartAdoptAdapter;
    private List<CartAdoptGoods.DataSetBean> cartAdoptList;
    private boolean isEditState = false;
    private double totalPrice;
    private CartAdoptGoods.DataSetBean currentChild;//记录当前修改的子项

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_cart;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        cartAdoptAdapter = new CartAdoptAdapter(baseActivity);
        // 设置复选框接口
        cartAdoptAdapter.setCheckInterface(this);
        // 设置商品数量增减接口
        cartAdoptAdapter.setModifyInterface(this);
        //设置组件编辑或完成接口
        cartAdoptAdapter.setGroupEditListener(this);
        exListView.setAdapter(cartAdoptAdapter);
        /*加载预售的购物车*/
        mPresenter.loadAdoptCart(2, DefaultPreferenceUtil.getInstance().getToken());
        /*添加标的的回调*/
        Disposable subscribe = RxBus.getInstance().toObservable(AdoptCode.class).observeOn(AndroidSchedulers.mainThread())
                .subscribe(adoptCode -> {
                    if (adoptCode.getType() == 1) {
                        if (currentChild != null) {
                             /*真正做添加标的的操作*/
                            currentChild.getAdoptCode().addAll(adoptCode.getList());
                            mPresenter.editCart(currentChild.getId(), 2, DefaultPreferenceUtil.getInstance().getToken(), null, currentChild.getProductSubId(), null, null, getAdoptCodeIds(currentChild.getAdoptCode()), currentChild.getProductId(), 4);
                            cartAdoptAdapter.notifyDataSetChanged();
                            calculateMoney();
                        }
                    }
                });
        mPresenter.addDisposable(subscribe);
    }

    private List<StoreInfo> groups = new ArrayList<>();

    private Map<Integer, List<CartAdoptGoods.DataSetBean>> children = new HashMap<>();

    @Override
    public void onLoadAdoptCartFinish(CartAdoptGoods cartAdoptGoods) {
        if (cartAdoptGoods == null) {
            L.i("failed");
        } else {
            cartAdoptList = cartAdoptGoods.getDataSet();
            List<Integer> bidList = new ArrayList<>();//保存商家的数量
            for (CartAdoptGoods.DataSetBean dataSetBean : cartAdoptList) {
                if (!bidList.contains(dataSetBean.getProducts().getBid())) {
                    bidList.add(dataSetBean.getProducts().getBid());
                }
            }
            for (Integer bid : bidList) {
                groups.add(new StoreInfo(bid, "湛均保健"));//bid一样就表示是同一个商家下的商品
                List<CartAdoptGoods.DataSetBean> goodList = new ArrayList<>();//每一个商家的商品列表
                for (CartAdoptGoods.DataSetBean dataSetBean : cartAdoptList) {
                    if (bid == dataSetBean.getProducts().getBid()) {
                        goodList.add(dataSetBean);
                    }
                }
                children.put(bid, goodList);
            }
            cartAdoptAdapter.setData(groups, children);
            // 初始化时，将ExpandableListView以展开的方式呈现
            for (int i = 0; i < cartAdoptAdapter.getGroupCount(); i++) {
                exListView.expandGroup(i);
            }
        }
    }

    @Override
    protected void injectSelf() {
        fragmentComponent.inject(this);
    }

    @Override
    public void checkGroup(int groupPosition, boolean isChecked) {
        StoreInfo group = groups.get(groupPosition);
        List<CartAdoptGoods.DataSetBean> childs = children.get(group.getId());
        for (int i = 0; i < childs.size(); i++) {
            childs.get(i).setChoose(isChecked);
        }
        isAllCheck();
        cartAdoptAdapter.notifyDataSetChanged();
        calculateMoney();
    }

    @Override
    public void checkChild(int groupPosition, int childPosition, boolean isChecked) {
        //判断该组下所有子项是不是同一状态，如果是，组件的checkBox的状态也要改变
        boolean isAllChildSameState = true;
        StoreInfo group = groups.get(groupPosition);
        List<CartAdoptGoods.DataSetBean> childs = children.get(group.getId());
        for (CartAdoptGoods.DataSetBean child : childs) {
            if (child.isChoose() != isChecked) {
                isAllChildSameState = false;
            }
        }
        if (isAllChildSameState) {
            group.setChoosed(isChecked);
        } else
            group.setChoosed(false);
        isAllCheck();
        cartAdoptAdapter.notifyDataSetChanged();
        calculateMoney();
    }

    @Override
    public void childDelete(int groupPosition, int childPosition) {
        StoreInfo group = groups.get(groupPosition);
        List<CartAdoptGoods.DataSetBean> dataSetBeans = children.get(group.getId());
        CartAdoptGoods.DataSetBean dataSetBean = children.get(group.getId()).get(childPosition);
        mPresenter.deleteCart(DefaultPreferenceUtil.getInstance().getToken(), 2, dataSetBean.getId() + "", dataSetBean.getProductSubId() + "", 0);
        /*真正做删除操作*/
        children.get(group.getId()).remove(childPosition);
        if (dataSetBeans.size() == 0) {//如果子项没有了，把组项也删除
            groups.remove(groupPosition);
        }
        cartAdoptAdapter.notifyDataSetChanged();
        calculateMoney();
    }

    @Override
    public void onDeleteCartFinish(CommonCart dataSet, int cartType, int type) {
        super.onDeleteCartFinish(dataSet, cartType, type);
        if (cartType == 2) {
            if (dataSet == null) {
                showToast("删除商品失败");
            } else {
                showToast("删除商品成功");
            }
        }
    }

    @Override
    public void addObject(int groupPosition, int childPosition) {
        StoreInfo group = groups.get(groupPosition);
        List<CartAdoptGoods.DataSetBean> dataSetBeans = children.get(group.getId());
        CartAdoptGoods.DataSetBean child = children.get(group.getId()).get(childPosition);
        this.currentChild = child;
        ARouter.getInstance().build(ARouterConst.Activity_ChooseBaseActivity).withInt(Const.KEY_ADOPT_ID, child.getProducts().getAdoptId()).withInt(Const.KEY_OBJECT_TYPE, Const.TYPE_CHOOSE_OBJECT).withString(Const.KEY_PRODUCT_NAME, child.getProducts().getAdoptName()).navigation();
    }

    @Override
    public void deleteObject(int groupPosition, int childPosition, int codePosition) {
        StoreInfo group = groups.get(groupPosition);
        List<CartAdoptGoods.DataSetBean> dataSetBeans = children.get(group.getId());
        CartAdoptGoods.DataSetBean child = children.get(group.getId()).get(childPosition);
        List<AdoptGoodDetail.DefaultAdoptCodeBean> adoptCodes = child.getAdoptCode();
        /*真正做删除操作*/
        child.getAdoptCode().remove(codePosition);
        mPresenter.editCart(child.getId(), 2, DefaultPreferenceUtil.getInstance().getToken(), null, child.getProductSubId(), null, null, getAdoptCodeIds(adoptCodes), child.getProductId(), 5);
        cartAdoptAdapter.notifyDataSetChanged();
        calculateMoney();
    }

    private String getAdoptCodeIds(List<AdoptGoodDetail.DefaultAdoptCodeBean> adoptCodes) {
        String adoptCodeIds = "";
        for (AdoptGoodDetail.DefaultAdoptCodeBean adoptCode : adoptCodes) {
            adoptCodeIds += adoptCode.getId() + ",";
        }
        if (!TextUtils.isEmpty(adoptCodeIds)) {
            adoptCodeIds = adoptCodeIds.substring(0, adoptCodeIds.length() - 1);
        }
        L.i("adoptCodeIds="+adoptCodeIds);
        return adoptCodeIds;
    }

    @Override
    public void onEditCartFinish(CommonCart dataSet, int type) {
        super.onEditCartFinish(dataSet, type);
        if (dataSet != null) {
            if (type == 4) {
                showToast("增加领养标的成功");
            } else if (type == 5) {
                showToast("删除领养标的成功");
            }
        } else if (type == 4) {
            showToast("增加领养标的失败");
        } else if (type == 5) {
            showToast("删除领养标的失败");
        }
    }

    @Override
    public void groupEdit(int groupPosition) {
        StoreInfo group = groups.get(groupPosition);
        if (group.isEditor()) {
            group.setIsEditor(false);
        } else
            group.setIsEditor(true);
        cartAdoptAdapter.notifyDataSetChanged();
        calculateMoney();

    }

    /*判断是不是所有的商品都是选中,如果是则改变全局checkBox的状态*/
    private boolean isAllCheck() {
        boolean isAllCheck = true;
        for (StoreInfo group : groups) {
            if (!group.isChoosed())
                isAllCheck = false;
        }
        if (isAllCheck) {
            if (isEditState) {
                cbEdit.setChecked(true);
            } else
                cbClearing.setChecked(true);
        } else {
            if (isEditState) {
                cbEdit.setChecked(false);
            } else
                cbClearing.setChecked(false);
        }
        return isAllCheck;
    }

    /**
     * 统计操作<br>
     * 1.先清空全局计数器<br>
     * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作<br>
     * 3.给底部的textView进行数据填充
     */
    private void calculateMoney() {
        totalPrice = 0.00;
        for (int i = 0; i < groups.size(); i++) {
            StoreInfo group = groups.get(i);
            List<CartAdoptGoods.DataSetBean> childs = children.get(group.getId());
            for (CartAdoptGoods.DataSetBean child : childs) {
                if (child.isChoose()) {
//                    totalPrice += child.getProducts().getAdoptPrice() * child.getItemCount();
                    totalPrice += child.getProducts().getAdoptPrice() * child.getAdoptCode().size();
                }
            }
        }
        tvAllMoney.setText((int) totalPrice + "");
    }

    @OnClick({R.id.cb_clearing, R.id.tv_clearing, R.id.cb_edit, R.id.tv_move_to_favorite, R.id.tv_all_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cb_clearing://结算时的全选
                allChoose(cbClearing);
                break;
            case R.id.cb_edit://编辑时的全选
                allChoose(cbEdit);
                break;
            case R.id.tv_clearing://结算
                //拿到所有已经选择的商品的购物车id的拼接
                String cardIds = "";
                for (CartAdoptGoods.DataSetBean bean : cartAdoptList) {
                    if (bean.isChoose()) {
                        cardIds += bean.getId() + ",";
                    }
                }
                if (!TextUtils.isEmpty(cardIds)) {
                    if (DefaultPreferenceUtil.getInstance().hasLogin()) {
                        cardIds = cardIds.substring(0, cardIds.length() - 1);
                        mPresenter.confirmOrder(DefaultPreferenceUtil.getInstance().getToken(), 2, null, null, null, cardIds, null, null, 5);
                    }
                } else {
                    showToast("请选择商品");
                }
                break;
            case R.id.tv_move_to_favorite://移入收藏夹
                break;
            case R.id.tv_all_delete://批量删除
                doDelete();
                break;
        }
    }

    @Override
    public void onConfirmOrderFinish(Object object, int type) {
        super.onConfirmOrderFinish(object, type);
        if (object != null) {
            if (type == 5) {
                ConfirmCartAdoptOrder confirmCartAdoptOrder = (ConfirmCartAdoptOrder) object;
                Bundle bundle = new Bundle();
                bundle.putSerializable("confirmCartAdoptOrder", confirmCartAdoptOrder);
                ARouter.getInstance().build(ARouterConst.Activity_ConfirmCartAdoptOrderActivity).withBundle("bundle", bundle).navigation();
            }
        } else {
            L.i("提交订单失败");
        }
    }

    /*activity点击编辑或者完成*/
    public void editOrCompleteAll(boolean isAllEdit) {
        this.isEditState = isAllEdit;
        if (isAllEdit) {
            llEdit.setVisibility(View.VISIBLE);
            llClearing.setVisibility(View.GONE);
            for (StoreInfo group : groups) {
                group.setIsEditor(true);
            }
            cartAdoptAdapter.notifyDataSetChanged();
        } else {
            llEdit.setVisibility(View.GONE);
            llClearing.setVisibility(View.VISIBLE);
            for (StoreInfo group : groups) {
                group.setIsEditor(false);
            }
            cartAdoptAdapter.notifyDataSetChanged();
        }
    }

    //全选，组项和子项的状态都需要改
    public void allChoose(CheckBox checkBox) {
        for (StoreInfo group : groups) {
            group.setChoosed(checkBox.isChecked());
            List<CartAdoptGoods.DataSetBean> childs = children.get(group.getId());
            for (CartAdoptGoods.DataSetBean child : childs) {
                child.setChoose(checkBox.isChecked());
            }
        }
        cartAdoptAdapter.notifyDataSetChanged();
        calculateMoney();
    }

    //批量删除
    private void doDelete() {
        String cartIds = "";
        String productSubIds = "";
        for (int i = 0; i < groups.size(); i++) {
            List<CartAdoptGoods.DataSetBean> childs = children.get(groups.get(i).getId());
            for (int j = 0; j < childs.size(); j++) {
                if (childs.get(j).isChoose()) {
                    cartIds += childs.get(j).getId() + ",";
                    productSubIds += childs.get(j).getProductSubId() + ",";
                }
            }
        }
        if (!TextUtils.isEmpty(cartIds) && !TextUtils.isEmpty(productSubIds)) {
            cartIds = cartIds.substring(0, cartIds.length() - 1);
            productSubIds = productSubIds.substring(0, productSubIds.length() - 1);
            mPresenter.deleteCart(DefaultPreferenceUtil.getInstance().getToken(), 2, cartIds, productSubIds, 1);
        }
        /*真正做批量删除操作*/
        List<StoreInfo> toBeDeleteGroups = new ArrayList<StoreInfo>();// 待删除的组元素列表
        for (int i = 0; i < groups.size(); i++) {
            StoreInfo group = groups.get(i);
            if (group.isChoosed()) {
                toBeDeleteGroups.add(group);
            }
            List<CartAdoptGoods.DataSetBean> toBeDeleteChilds = new ArrayList<>();// 待删除的子元素列表
            List<CartAdoptGoods.DataSetBean> childs = children.get(group.getId());
            for (int j = 0; j < childs.size(); j++) {
                if (childs.get(j).isChoose()) {
                    toBeDeleteChilds.add(childs.get(j));
                }
            }
            childs.removeAll(toBeDeleteChilds);
        }
        groups.removeAll(toBeDeleteGroups);
        //记得重新设置购物车
        cartAdoptAdapter.notifyDataSetChanged();
        calculateMoney();
    }
}