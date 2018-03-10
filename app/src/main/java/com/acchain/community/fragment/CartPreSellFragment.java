package com.acchain.community.fragment;

import com.acchain.community.R;
import com.acchain.community.adapter.CartPreAdapter;
import com.acchain.community.bean.CartPreGoods;
import com.acchain.community.bean.CommonCart;
import com.acchain.community.bean.ConfirmCartPanicOrder;
import com.acchain.community.bean.ConfirmCartPreOrder;
import com.acchain.community.bean.StoreInfo;
import com.acchain.community.presenter.CartPreSellPresenter;
import com.acchain.community.contract.CartPreSellContract;
import com.acchain.community.base.BaseFragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acchain.community.util.DefaultPreferenceUtil;
import com.acchain.community.util.GoodsUtil;
import com.acchain.community.util.L;
import com.acchain.community.window.dialog.ChooseGoodsAttrsDialog;
import com.acchain.community.window.dialog.CommonGoodsAttrsBean;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.acchain.community.arouter.ARouterConst;
import com.alibaba.android.arouter.launcher.ARouter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * function---- CartPreSellFragment
 * <p>
 * Created(Gradle default create) by ACChain on 2018/02/06 01:50:13 (+0000).
 */
@Route(path = ARouterConst.Fragment_CartPreSellFragment)
public class CartPreSellFragment extends BaseFragment<CartPreSellPresenter> implements CartPreSellContract.View, CartPreAdapter.CheckInterface, CartPreAdapter.ModifyInterface, CartPreAdapter.GroupEditorInterface {
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

    private CartPreAdapter cartPreAdapter;
    private List<CartPreGoods.DataSetBean> cartPanicList;
    private boolean isEditState = false;
    private double totalPrice;
    private ChooseGoodsAttrsDialog chooseGoodsAttrsDialog;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_cart;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        cartPreAdapter = new CartPreAdapter(baseActivity);
        // 设置复选框接口
        cartPreAdapter.setCheckInterface(this);
        // 设置商品数量增减接口
        cartPreAdapter.setModifyInterface(this);
        //设置组件编辑或完成接口
        cartPreAdapter.setGroupEditListener(this);
        exListView.setAdapter(cartPreAdapter);
        /*加载预售的购物车*/
        mPresenter.loadPreCart(1, DefaultPreferenceUtil.getInstance().getToken());
    }

    private List<StoreInfo> groups = new ArrayList<>();

    private Map<Integer, List<CartPreGoods.DataSetBean>> children = new HashMap<>();

    @Override
    public void onLoadPreCartFinish(CartPreGoods cartPreGoods) {
        if (cartPreGoods == null) {
            L.i("failed");
        } else {
            cartPanicList = cartPreGoods.getDataSet();
            List<Integer> bidList = new ArrayList<>();//保存商家的数量
            for (CartPreGoods.DataSetBean dataSetBean : cartPanicList) {
                if (!bidList.contains(dataSetBean.getProducts().getBid())) {
                    bidList.add(dataSetBean.getProducts().getBid());
                }
            }
            for (Integer bid : bidList) {
                groups.add(new StoreInfo(bid, "湛均保健"));//bid一样就表示是同一个商家下的商品
                List<CartPreGoods.DataSetBean> goodList = new ArrayList<>();//每一个商家的商品列表
                for (CartPreGoods.DataSetBean dataSetBean : cartPanicList) {
                    if (bid == dataSetBean.getProducts().getBid()) {
                        goodList.add(dataSetBean);
                    }
                }
                children.put(bid, goodList);
            }
            cartPreAdapter.setData(groups, children);
            // 初始化时，将ExpandableListView以展开的方式呈现
            for (int i = 0; i < cartPreAdapter.getGroupCount(); i++) {
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
        List<CartPreGoods.DataSetBean> childs = children.get(group.getId());
        for (int i = 0; i < childs.size(); i++) {
            childs.get(i).setChoose(isChecked);
        }
        isAllCheck();
        cartPreAdapter.notifyDataSetChanged();
        calculateMoney();
    }

    @Override
    public void checkChild(int groupPosition, int childPosition, boolean isChecked) {
        //判断该组下所有子项是不是同一状态，如果是，组件的checkBox的状态也要改变
        boolean isAllChildSameState = true;
        StoreInfo group = groups.get(groupPosition);
        List<CartPreGoods.DataSetBean> childs = children.get(group.getId());
        for (CartPreGoods.DataSetBean child : childs) {
            if (child.isChoose() != isChecked) {
                isAllChildSameState = false;
            }
        }
        if (isAllChildSameState) {
            group.setChoosed(isChecked);
        } else
            group.setChoosed(false);
        isAllCheck();
        cartPreAdapter.notifyDataSetChanged();
        calculateMoney();
    }

    @Override
    public void onCountChange(int groupPosition, int childPosition, long count, boolean isChecked) {
        L.i("onCountChange" + count);
        CartPreGoods.DataSetBean child = (CartPreGoods.DataSetBean) cartPreAdapter.getChild(groupPosition, childPosition);
        child.setItemCount((int) count);
        cartPreAdapter.notifyDataSetChanged();
        calculateMoney();
        mPresenter.editCart(child.getId(), 1, DefaultPreferenceUtil.getInstance().getToken(), (int) count, child.getProductSubId(), null, null, null, child.getProductId(), 2);
    }

    @Override
    public void onEditCartFinish(CommonCart dataSet, int type) {
        super.onEditCartFinish(dataSet, type);
        if (dataSet != null) {
            if (type == 2) {
                showToast("修改预购的数量成功");
            } else if (type == 3) {
                showToast("修改预购的属性成功");
            }
        } else if (type == 2) {
            showToast("修改预购的数量失败");
        } else if (type == 3) {
            showToast("修改预购的属性失败");
        }
    }

    @Override
    public void childDelete(int groupPosition, int childPosition) {
        StoreInfo group = groups.get(groupPosition);
        List<CartPreGoods.DataSetBean> dataSetBeans = children.get(group.getId());
        CartPreGoods.DataSetBean dataSetBean = children.get(group.getId()).get(childPosition);
        mPresenter.deleteCart(DefaultPreferenceUtil.getInstance().getToken(), 1, dataSetBean.getId() + "", dataSetBean.getProductSubId() + "", 0);
        /*真正做删除操作*/
        children.get(group.getId()).remove(childPosition);
        if (dataSetBeans.size() == 0) {//如果子项没有了，把组项也删除
            groups.remove(groupPosition);
        }
        cartPreAdapter.notifyDataSetChanged();
        calculateMoney();
    }

    @Override
    public void onDeleteCartFinish(CommonCart dataSet, int cartType,int type) {
        super.onDeleteCartFinish(dataSet, cartType,type);
        if (cartType == 1) {
            if (dataSet == null) {
                showToast("删除商品失败");
            } else {
                showToast("删除商品成功");
            }
        }
    }

    @Override
    public void changeAttrs(int groupPosition, int childPosition) {
        CartPreGoods.DataSetBean child = (CartPreGoods.DataSetBean) cartPreAdapter.getChild(groupPosition, childPosition);
        CartPreGoods.DataSetBean.ProductsBean products = child.getProducts();
        List<CommonGoodsAttrsBean.AttrListBean> productAttrList = products.getProductAttrList();
        if (chooseGoodsAttrsDialog == null) {
            int[] attrValueIds = GoodsUtil.getAttrValueIds(child.getProductAttr(), products.getProductAttrList());
            CommonGoodsAttrsBean commonGoodsAttrsBean = new CommonGoodsAttrsBean(products.getProductImg(), products.getPresellPrice(), attrValueIds, productAttrList, 0, products.getPresellCirculation());
            chooseGoodsAttrsDialog = new ChooseGoodsAttrsDialog(baseActivity, commonGoodsAttrsBean, 1);
            chooseGoodsAttrsDialog.setOnSureClickListener(new ChooseGoodsAttrsDialog.OnSureClickListener() {
                @Override
                public void onSureClick(int itemCount, int[] productAttrValueIds, String attrStr) {
                    //Todo 发改属性的请求
                    child.setProductAttr(attrStr);
                    cartPreAdapter.notifyDataSetChanged();
                    mPresenter.editCart(child.getId(), 1, DefaultPreferenceUtil.getInstance().getToken(), child.getItemCount(), child.getProductSubId(), GoodsUtil.getStr(productAttrValueIds), null, null, child.getProductId(), 3);
                }
            });
        } else {
            int[] attrValueIds = GoodsUtil.getAttrValueIds(child.getProductAttr(), products.getProductAttrList());
            CommonGoodsAttrsBean commonGoodsAttrsBean = new CommonGoodsAttrsBean(products.getProductImg(), products.getPresellPrice(), attrValueIds, productAttrList, 0, products.getPresellCirculation());
            chooseGoodsAttrsDialog.setData(commonGoodsAttrsBean);
        }
        chooseGoodsAttrsDialog.show();
    }

    @Override
    public void groupEdit(int groupPosition) {
        StoreInfo group = groups.get(groupPosition);
        if (group.isEditor()) {
            group.setIsEditor(false);
        } else
            group.setIsEditor(true);
        cartPreAdapter.notifyDataSetChanged();
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
            List<CartPreGoods.DataSetBean> childs = children.get(group.getId());
            for (CartPreGoods.DataSetBean child : childs) {
                if (child.isChoose()) {
                    totalPrice += child.getProducts().getPresellPrice() * child.getItemCount();
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
                for (CartPreGoods.DataSetBean bean : cartPanicList) {
                    if (bean.isChoose()) {
                        cardIds += bean.getId() + ",";
                    }
                }
                if (!TextUtils.isEmpty(cardIds)) {
                    if (DefaultPreferenceUtil.getInstance().hasLogin()) {
                        cardIds = cardIds.substring(0, cardIds.length() - 1);
                        mPresenter.confirmOrder(DefaultPreferenceUtil.getInstance().getToken(), 1, null, null, null, cardIds, null, null, 3);
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
            if (type == 3) {
                ConfirmCartPreOrder confirmCartPreOrder = (ConfirmCartPreOrder) object;
                Bundle bundle = new Bundle();
                bundle.putSerializable("confirmCartPreOrder", confirmCartPreOrder);
                ARouter.getInstance().build(ARouterConst.Activity_ConfirmCartPreOrderActivity).withBundle("bundle", bundle).navigation();
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
            cartPreAdapter.notifyDataSetChanged();
        } else {
            llEdit.setVisibility(View.GONE);
            llClearing.setVisibility(View.VISIBLE);
            for (StoreInfo group : groups) {
                group.setIsEditor(false);
            }
            cartPreAdapter.notifyDataSetChanged();
        }
    }

    //全选，组项和子项的状态都需要改
    public void allChoose(CheckBox checkBox) {
        for (StoreInfo group : groups) {
            group.setChoosed(checkBox.isChecked());
            List<CartPreGoods.DataSetBean> childs = children.get(group.getId());
            for (CartPreGoods.DataSetBean child : childs) {
                child.setChoose(checkBox.isChecked());
            }
        }
        cartPreAdapter.notifyDataSetChanged();
        calculateMoney();
    }

    //批量删除
    private void doDelete() {
        String cartIds = "";
        String productSubIds = "";
        for (int i = 0; i < groups.size(); i++) {
            List<CartPreGoods.DataSetBean> childs = children.get(groups.get(i).getId());
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
            mPresenter.deleteCart(DefaultPreferenceUtil.getInstance().getToken(), 1, cartIds, productSubIds, 1);
        }
        /*真正做批量删除操作*/
        List<StoreInfo> toBeDeleteGroups = new ArrayList<StoreInfo>();// 待删除的组元素列表
        for (int i = 0; i < groups.size(); i++) {
            StoreInfo group = groups.get(i);
            if (group.isChoosed()) {
                toBeDeleteGroups.add(group);
            }
            List<CartPreGoods.DataSetBean> toBeDeleteChilds = new ArrayList<>();// 待删除的子元素列表
            List<CartPreGoods.DataSetBean> childs = children.get(group.getId());
            for (int j = 0; j < childs.size(); j++) {
                if (childs.get(j).isChoose()) {
                    toBeDeleteChilds.add(childs.get(j));
                }
            }
            childs.removeAll(toBeDeleteChilds);
        }
        groups.removeAll(toBeDeleteGroups);
        //记得重新设置购物车
        cartPreAdapter.notifyDataSetChanged();
        calculateMoney();
    }
}