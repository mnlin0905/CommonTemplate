package com.acchain.community.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.base.BaseApplication;
import com.acchain.community.bean.CartPreGoods;
import com.acchain.community.bean.StoreInfo;
import com.acchain.community.view.AmountView;
import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 购物车数据适配器
 */
public class CartPreAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<StoreInfo> groups;
    private Map<Integer, List<CartPreGoods.DataSetBean>> children;
    private CheckInterface checkInterface;
    private ModifyInterface modifyInterface;
    private GroupEditorInterface groupEditorInterface;

    public CartPreAdapter(Context context) {
        this(context, null, null);
    }

    public CartPreAdapter(Context context, List<StoreInfo> groups, Map<Integer, List<CartPreGoods.DataSetBean>> children) {
        this.context = context;
        this.groups = groups;
        this.children = children;
    }

    public void setData(List<StoreInfo> groups, Map<Integer, List<CartPreGoods.DataSetBean>> children) {
        this.groups = groups;
        this.children = children;
        notifyDataSetChanged();
    }

    public void setGroupEditListener(GroupEditorInterface groupEditListener) {
        this.groupEditorInterface = groupEditListener;
    }

    public void setCheckInterface(CheckInterface checkInterface) {
        this.checkInterface = checkInterface;
    }

    public void setModifyInterface(ModifyInterface modifyInterface) {
        this.modifyInterface = modifyInterface;
    }

    @Override
    public int getGroupCount() {
        return groups == null ? 0 : groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (groups != null) {
            StoreInfo storeInfo = groups.get(groupPosition);
            int id = storeInfo.getId();
            List<CartPreGoods.DataSetBean> dataSetBeans = children.get(id);
            return dataSetBeans.size();
        } else
            return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        List<CartPreGoods.DataSetBean> childs = children.get(groups.get(groupPosition).getId());
        return childs.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final GroupViewHolder groupHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_cart_group, null);
            groupHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupViewHolder) convertView.getTag();
        }
        final StoreInfo group = (StoreInfo) getGroup(groupPosition);

        groupHolder.tvGroupName.setText(group.getName());
        //点组件上是否选中的监听
        groupHolder.cbGroup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                group.setChoosed(((CheckBox) v).isChecked());
                checkInterface.checkGroup(groupPosition, ((CheckBox) v).isChecked());// 暴露组选接口
            }
        });
        groupHolder.cbGroup.setChecked(group.isChoosed());
        if (group.isEditor()) {
            groupHolder.tvGroupEdit.setText("完成");
        } else {
            groupHolder.tvGroupEdit.setText("编辑");
        }
//        groupHolder.tvGroupEdit.setOnClickListener(new GroupViewClick(groupPosition, groupHolder.tvGroupEdit, group));
        //点组件上编辑的监听
        groupHolder.tvGroupEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (groupEditorInterface != null) {
                    groupEditorInterface.groupEdit(groupPosition);
                }
            }
        });
//        notifyDataSetChanged();
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, final boolean isLastChild, View convertView, final ViewGroup parent) {
        final ChildViewHolder childHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_cart_pre_child, null);
            childHolder = new ChildViewHolder(convertView);
            convertView.setTag(childHolder);
        } else {
            childHolder = (ChildViewHolder) convertView.getTag();
        }

        if (groups.get(groupPosition).isEditor()) {
            childHolder.llEdit.setVisibility(View.VISIBLE);
            childHolder.llNormal.setVisibility(View.GONE);
        } else {
            childHolder.llEdit.setVisibility(View.GONE);
            childHolder.llNormal.setVisibility(View.VISIBLE);
        }
        final CartPreGoods.DataSetBean dataSetBean = (CartPreGoods.DataSetBean) getChild(groupPosition, childPosition);
        if (dataSetBean != null) {
            CartPreGoods.DataSetBean.ProductsBean products = dataSetBean.getProducts();
            Glide.with(context).load(BaseApplication.getApplication().getBaseImageUrl() + products.getProductImg());
            childHolder.tvGoodName.setText(dataSetBean.getProducts().getProductName());

            childHolder.tvNowPrice.setText("￥" + (int) products.getPresellPrice() + "");
            childHolder.tvNowPrice.setTypeface(Typeface.DEFAULT_BOLD);
            childHolder.tvDescribe.setText(products.getShortDesc());
            childHolder.tvGoodsAttrs.setText(dataSetBean.getProductAttr());
            childHolder.tvGoodsCount.setText("*" + dataSetBean.getItemCount());
            childHolder.cbChild.setChecked(dataSetBean.isChoose());
            //点商品中是否选中的监听
            childHolder.cbChild.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    dataSetBean.setChoose(((CheckBox) v).isChecked());
                    childHolder.cbChild.setChecked(((CheckBox) v).isChecked());
                    checkInterface.checkChild(groupPosition, childPosition, ((CheckBox) v).isChecked());// 暴露子选接口
                }
            });
            //商品数量变化的监听
            childHolder.amountView.setNumber(dataSetBean.getItemCount());
            childHolder.amountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
                @Override
                public void onAmountChange(View view, long amount) {
                    modifyInterface.onCountChange(groupPosition, childPosition, amount, childHolder.cbChild.isChecked());//暴露数量改变的接口
                }
            });
            //修改属性
            childHolder.tvProperty.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (modifyInterface != null) {
                        modifyInterface.changeAttrs(groupPosition, childPosition);
                    }
                }
            });
            //删除商品的监听
            childHolder.tvDelete.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Todo 可在此弹窗
                    modifyInterface.childDelete(groupPosition, childPosition);
                }
            });
        }
//        notifyDataSetChanged();
        return convertView;
    }

    /*点击子项的监听，返回true才生效*/
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    /**
     * 复选框接口
     */
    public interface CheckInterface {
        /**
         * 组选框状态改变触发的事件
         *
         * @param groupPosition 组元素位置
         * @param isChecked     组元素选中与否
         */
        void checkGroup(int groupPosition, boolean isChecked);

        /**
         * 子选框状态改变时触发的事件
         *
         * @param groupPosition 组元素位置
         * @param childPosition 子元素位置
         * @param isChecked     子元素选中与否
         */
        void checkChild(int groupPosition, int childPosition, boolean isChecked);
    }

    /**
     * 改变数量的接口
     */
    public interface ModifyInterface {

        /**
         * 数量改变
         *
         * @param groupPosition 组元素位置
         * @param childPosition 子元素位置
         * @param count         改变之后的数量
         * @param isChecked     子元素选中与否
         */
        void onCountChange(int groupPosition, int childPosition, long count, boolean isChecked);

        /**
         * 删除子item
         *
         * @param groupPosition
         * @param childPosition
         */
        void childDelete(int groupPosition, int childPosition);

        void changeAttrs(int groupPosition, int childPosition);
    }

    /**
     * 监听编辑状态
     */
    public interface GroupEditorInterface {
        void groupEdit(int groupPosition);
    }

    /**
     * 使某个组处于编辑状态
     * <p/>
     * groupPosition组的位置
     */
    class GroupViewClick implements OnClickListener {
        private int groupPosition;
        private TextView edtor;
        private StoreInfo group;

        /*edtor是所有group的编辑按钮*/
        public GroupViewClick(int groupPosition, TextView edtor, StoreInfo group) {
            this.groupPosition = groupPosition;
            this.edtor = edtor;
            this.group = group;
        }

        @Override
        public void onClick(View v) {
            int groupId = v.getId();
            if (groupId == edtor.getId()) {
                if (group.isEditor()) {
                    group.setIsEditor(false);
                } else {
                    group.setIsEditor(true);
                }
                notifyDataSetChanged();
            }
        }
    }

    /**
     * 组元素绑定器
     */
    static class GroupViewHolder {
        @BindView(R.id.cb_group)
        CheckBox cbGroup;
        @BindView(R.id.group_image)
        ImageView groupImage;
        @BindView(R.id.tv_group_name)
        TextView tvGroupName;
        @BindView(R.id.tv_group_edit)
        TextView tvGroupEdit;

        GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    /**
     * 子元素绑定器
     */
    static class ChildViewHolder {
        @BindView(R.id.cb_child)
        CheckBox cbChild;
        @BindView(R.id.goods_image)
        ImageView goodsImage;
        @BindView(R.id.tv_good_name)
        TextView tvGoodName;
        @BindView(R.id.tv_now_price)
        TextView tvNowPrice;
        @BindView(R.id.tv_describe)
        TextView tvDescribe;
        @BindView(R.id.tv_goods_attrs)
        TextView tvGoodsAttrs;
        @BindView(R.id.tv_goods_count)
        TextView tvGoodsCount;
        @BindView(R.id.ll_normal)
        LinearLayout llNormal;
        @BindView(R.id.amount_view)
        AmountView amountView;
        @BindView(R.id.tv_property)
        TextView tvProperty;
        @BindView(R.id.tv_delete)
        TextView tvDelete;
        @BindView(R.id.ll_edit)
        LinearLayout llEdit;

        ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }

}
