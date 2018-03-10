package com.acchain.community.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.base.BaseRecyclerViewHolder;
import com.acchain.community.bean.GoodsCollectionFootBean;
import com.acchain.community.drawable.RoundRectShapeDrawable;
import com.acchain.community.interfaces.CommonProduceDetail;
import com.acchain.community.util.Const;
import com.acchain.community.view.LightTextView;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created on 2018/1/23
 * function :
 *
 * @author ACChain
 */

public class GoodsCollectionFootAdapter extends RecyclerView.Adapter<GoodsCollectionFootAdapter.IViewHolder> {

    /**
     * 缩进长度
     * 上下文
     * 数据源
     * 全局监听(动作监听)
     * 是否可编辑(编辑模式则只能点击选中,不能相应事件)
     */
    private final int dimensionPixelSize;
    private HashMap<GoodsCollectionFootBean, CommonProduceDetail> detailMap;
    private Context context;
    private List<GoodsCollectionFootBean> datas;
    private OnGoodsListener listener;
    private boolean editMode;

    public GoodsCollectionFootAdapter(HashMap<GoodsCollectionFootBean, CommonProduceDetail> detailMap, Context context, List<GoodsCollectionFootBean> datas, OnGoodsListener listener) {
        this.detailMap = detailMap;
        this.context = context;
        this.datas = datas;
        this.listener = listener;

        dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.prefer_view_height_48dp);
    }

    @Override
    public IViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        IViewHolder holder;
        switch (viewType) {
            case Const.TYPE_PRODUCT_ADOPT://领养
                holder = new ViewHolder_PreOrder(LayoutInflater.from(context).inflate(R.layout.item_goods_preorder, parent, false));
                break;
            case Const.TYPE_PRODUCT_PRE_ORDER://预售
            case Const.TYPE_PRODUCT_EXERCISE://行权
            case Const.TYPE_PRODUCT_PANIC://抢购
            default:
                holder = new ViewHolder_Adopt(LayoutInflater.from(context).inflate(R.layout.item_goods_adopt, parent, false));
                break;
        }
        return holder;
    }

    /**
     * 通过type来分别不同商品类型,显示的结构
     * <p>
     * Return the view type of the item at <code>position</code> for the purposes
     * of view recycling.
     * <p>
     * <p>The default implementation of this method returns 0, making the assumption of
     * a single view type for the adapter. Unlike ListView adapters, types need not
     * be contiguous. Consider using id resources to uniquely identify item view types.
     *
     * @param position position to query
     * @return integer value identifying the type of the view needed to represent the item at
     * <code>position</code>. Type codes need not be contiguous.
     */
    @Override
    public int getItemViewType(int position) {
        return datas.get(position).getType();
    }

    @Override
    public void onBindViewHolder(IViewHolder holder, int position) {
        GoodsCollectionFootBean bean = datas.get(position);

        //绑定具体数据,最后一条数据时,分割线需要消失
        holder.mTvName.setText(bean.getName());
        holder.mDvDivider.setVisibility(position == datas.size() - 1 ? View.GONE : View.VISIBLE);

        //编辑保证正常缩进
        if (editMode && holder.mClContent.getTranslationX() == 0) {
            holder.mClContent.animate().translationX(dimensionPixelSize).setDuration(300).start();
        }
        if (!editMode && holder.mClContent.getTranslationX() == dimensionPixelSize) {
            holder.mClContent.animate().translationX(0).setDuration(300).start();
        }

        //当item为选中时,处理事件;如果当前为非编辑模式,则模式选中框不可见
        holder.mCbSelect.setChecked(bean.isSELECTED_STATUS());
        holder.mCbSelect.setVisibility(editMode?View.VISIBLE:View.INVISIBLE);

        //获取填充数据的实体类(包含价格,进度等等信息),如果存在才能进行填充,否则略过
        CommonProduceDetail commonProduceDetail = detailMap.get(bean);
        if (commonProduceDetail != null) {
            //预售类单独处理
            if (holder instanceof ViewHolder_PreOrder) {
                ViewHolder_PreOrder h = ((ViewHolder_PreOrder) holder);
                h.mLtvPrice.setText(String.format("%s%s", commonProduceDetail.getPriceUnit(), String.valueOf(commonProduceDetail.getCurrentPrice())));
                h.mTvPriceOrigin.setText(String.format("%s%s", commonProduceDetail.getPriceUnit(), String.valueOf(commonProduceDetail.getOldPrice())));
                h.mTvIntroduce.setText(commonProduceDetail.getIntroduce());
            }

            //领养类单独处理
            if (holder instanceof ViewHolder_Adopt) {
                ViewHolder_Adopt h = ((ViewHolder_Adopt) holder);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) h.mIvProgress.getLayoutParams();
                layoutParams.weight=commonProduceDetail.getProgress();
                layoutParams= (LinearLayout.LayoutParams) h.mIvProgressResidue.getLayoutParams();
                layoutParams.weight=100-commonProduceDetail.getProgress();
                h.mTvProgress.setText(String.format(Locale.CHINA,"%d%%",commonProduceDetail.getProgress()));
                h.mTvInfo.setText(String.format(Locale.CHINA,"%d人已经领养",commonProduceDetail.getAdoptAmount()));
                h.mTvPriceNum.setText(String.valueOf(commonProduceDetail.getCurrentPrice()));
                h.mTvPriceUnit.setText(commonProduceDetail.getPriceUnit());
                h.mTvTimeNum.setText(String.valueOf(commonProduceDetail.getBetweenTime()));
                h.mTvTimeUnit.setText(commonProduceDetail.getTimeUnit());
            }
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    /**
     * @param editMode 设置当前为编辑模式
     */
    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
        notifyDataSetChanged();
    }

    /**
     * 点击item事件
     */
    public interface OnGoodsListener {
        /**
         * 点击 "找相似"
         * <p>
         * v表示具体被点击的view,即textView
         */
        void onClickFindSimilar(int position, View v);

        /**
         * 点击 "购物车"
         */
        void onClickShoppingCart(int position, View v);

        /**
         * 点击 "分享"
         */
        void onClickShare(int position, View v);

        /**
         * 点击 "头像"/"图标"
         */
        void onClickIcon(int position, View v);

        /**
         * 点击 "itemView自身"
         */
        void onClickSelf(int position, View v);
    }

    /**
     * 预售类商品的holder
     */
    class ViewHolder_PreOrder extends IViewHolder {
        @BindView(R.id.tv_introduce)
        TextView mTvIntroduce;
        @BindView(R.id.tv_price_origin)
        TextView mTvPriceOrigin;
        @BindView(R.id.ltv_price)
        LightTextView mLtvPrice;

        ViewHolder_PreOrder(View itemView) {
            super(itemView);
        }
    }

    /**
     * 领养类商品holder
     */
    class ViewHolder_Adopt extends IViewHolder {

        @BindView(R.id.iv_progress)
        ImageView mIvProgress;
        @BindView(R.id.tv_progress)
        TextView mTvProgress;
        @BindView(R.id.iv_progress_residue)
        ImageView mIvProgressResidue;
        @BindView(R.id.tv_price_num)
        TextView mTvPriceNum;
        @BindView(R.id.tv_price_unit)
        TextView mTvPriceUnit;
        @BindView(R.id.tv_time_num)
        TextView mTvTimeNum;
        @BindView(R.id.tv_time_unit)
        TextView mTvTimeUnit;
        @BindView(R.id.tv_info)
        TextView mTvInfo;

        ViewHolder_Adopt(View itemView) {
            super(itemView);
        }
    }

    /**
     * 提取公共的成员变量
     * <p>
     * 分享
     * 相似
     * 购物车
     * 图标
     * 商品名称
     * 分割线
     * 选中/非选中
     * 内容部分
     */
    abstract class IViewHolder extends BaseRecyclerViewHolder {
        @BindView(R.id.iv_share)
        ImageView mIvShare;
        @BindView(R.id.tv_similar)
        TextView mTvSimilar;
        @BindView(R.id.iv_shopping_cart)
        ImageView mIvShoppingCart;
        @BindView(R.id.iv_icon)
        ImageView mIvIcon;
        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.dv_divider)
        ImageView mDvDivider;
        @BindView(R.id.cb_select)
        CheckBox mCbSelect;
        @BindView(R.id.cl_content)
        ConstraintLayout mClContent;

        private IViewHolder(View itemView, OnViewClickListener listener) {
            super(itemView);
            throw new RuntimeException("forbid");
        }

        IViewHolder(View itemView) {
            super(itemView);
            setOnViewClickListener((v, position) -> {
                if (editMode) {
                    mCbSelect.setChecked(!mCbSelect.isChecked());
                } else if (listener != null) {
                    listener.onClickSelf(position, v);
                }
            });

            //添加背景框
            mTvSimilar.post(() -> mTvSimilar.setBackground(new RoundRectShapeDrawable(mTvSimilar.getWidth(), mTvSimilar.getHeight(), getContext().getResources().getColor(R.color.blue_search_background))));

            //switch切换
            mCbSelect.setOnCheckedChangeListener((buttonView, isChecked) -> datas.get(getCurrentPosition()).setSELECTED_STATUS(isChecked));
        }

        @Override
        protected boolean isXRecyclerView() {
            return false;
        }

        @OnClick({R.id.iv_icon, R.id.tv_similar, R.id.iv_shopping_cart, R.id.iv_share})
        public void onViewClicked(View view) {
            if (listener == null) {
                return;
            }
            if (editMode) {
                mCbSelect.setChecked(!mCbSelect.isChecked());
                return;
            }
            switch (view.getId()) {
                case R.id.iv_icon:
                    listener.onClickIcon(getCurrentPosition(), view);
                    break;
                case R.id.tv_similar:
                    listener.onClickFindSimilar(getCurrentPosition(), view);
                    break;
                case R.id.iv_shopping_cart:
                    listener.onClickShoppingCart(getCurrentPosition(), view);
                    break;
                case R.id.iv_share:
                    listener.onClickShare(getCurrentPosition(), view);
                    break;
            }
        }
    }
}
