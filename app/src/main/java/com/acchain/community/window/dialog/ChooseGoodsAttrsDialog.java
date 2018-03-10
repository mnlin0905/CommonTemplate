package com.acchain.community.window.dialog;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.adapter.DialogChooseGoodsAdapter;
import com.acchain.community.util.GoodsUtil;
import com.acchain.community.view.AmountView;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ChooseGoodsAttrsDialog extends DialogViewHolder {
    @BindView(R.id.tv_close)
    TextView tvClose;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.amount_view)
    AmountView amountView;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.repertory)
    TextView repertory;
    @BindView(R.id.tv_attr)
    TextView tvAttr;
    @BindView(R.id.rl_buy_count)
    RelativeLayout rlBuyCount;
    @BindView(R.id.tv_sure)
    TextView tvSure;
    @BindView(R.id.image)
    ImageView image;
    private Context context;
    private CommonGoodsAttrsBean commonGoodsAttrsBean;
    private int type = 0;//0--商品界面，1--购物车界面
    private List<CommonGoodsAttrsBean.AttrListBean> attrList;
    /*tagLayout被选中的项*/
    private int[] selectPosition;
    private int[] productAttrValueIds;
    private String attrStr = "";
    private DialogChooseGoodsAdapter dialogChooseGoodsAdapter;


    public ChooseGoodsAttrsDialog(Context context, CommonGoodsAttrsBean commonGoodsAttrsBean, int type) {
        super(context);
        this.context = context;
        this.commonGoodsAttrsBean = commonGoodsAttrsBean;
        this.type = type;
        attrList = commonGoodsAttrsBean.getAttrList();
        productAttrValueIds=commonGoodsAttrsBean.getProductAttrValueIds();
        initView();
        refreshView();
    }

    public void setData(CommonGoodsAttrsBean commonGoodsAttrsBean) {
        this.commonGoodsAttrsBean = commonGoodsAttrsBean;
        attrList = commonGoodsAttrsBean.getAttrList();
        productAttrValueIds=commonGoodsAttrsBean.getProductAttrValueIds();
        refreshView();
    }

    private void initView() {
        tvClose.setOnClickListener(v -> dismiss());
        /*重写LayoutManager的onMeasure方法，控制显示的item为1项*/
        recycler.setLayoutManager(new LinearLayoutManager(context) {
            @Override
            public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
                if (getChildCount() > 0) {
                    View firstChildView = recycler.getViewForPosition(0);
                    measureChild(firstChildView, widthSpec, heightSpec);
                    setMeasuredDimension(View.MeasureSpec.getSize(widthSpec), firstChildView.getMeasuredHeight() * 1);
                } else
                    super.onMeasure(recycler, state, widthSpec, heightSpec);
            }
        });
        dialogChooseGoodsAdapter = new DialogChooseGoodsAdapter(context);
        dialogChooseGoodsAdapter.setOnItemClickListener((attrPosition, attrValuePosition) -> {
            int attrValueId = attrList.get(attrPosition).getAttrValueList().get(attrValuePosition).getAttrValueId();
            productAttrValueIds[attrPosition] = attrValueId;
            attrStr= GoodsUtil.getAttrStr(attrList,productAttrValueIds);
            selectPosition=GoodsUtil.getSelectPosition(attrList,productAttrValueIds);
            tvAttr.setText(attrStr);
        });
        recycler.setAdapter(dialogChooseGoodsAdapter);
        rlBuyCount.setVisibility(type == 0 ? View.VISIBLE : View.GONE);
    }

    /*如果dialog存在，刷新*/
    private void refreshView() {
        amountView.setGoods_storage(commonGoodsAttrsBean.getGoodStorge());
        amountView.setNumber(commonGoodsAttrsBean.getItemCount());
        Glide.with(context).load(context.getString(R.string.test_base_image_address) + commonGoodsAttrsBean.getImg()).into(image);
        price.setText((int) commonGoodsAttrsBean.getPrice() + "");
        repertory.setText("库存:" + commonGoodsAttrsBean.getGoodStorge());
        attrStr= GoodsUtil.getAttrStr(attrList,productAttrValueIds);
        selectPosition=GoodsUtil.getSelectPosition(attrList,productAttrValueIds);
        tvAttr.setText(attrStr);
        dialogChooseGoodsAdapter.setData(attrList, selectPosition);
    }

    public interface OnSureClickListener {
        void onSureClick(int itemCount, int[] productAttrValueIds, String attrStr);
    }

    private OnSureClickListener mListener;

    public void setOnSureClickListener(OnSureClickListener listener) {
        mListener = listener;
    }

    @OnClick(R.id.tv_sure)
    public void onViewClicked() {
        dismiss();
        if (mListener != null) {
            mListener.onSureClick((int) amountView.getNumber(), productAttrValueIds, attrStr);
        }
    }

    @Override
    protected int getWidth() {
        return WindowManager.LayoutParams.MATCH_PARENT;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_choose_goods;
    }

    @Override
    protected int getWindowAnimations() {
        return R.style.dialog_bottom_to_up;
    }


    @Override
    protected int getGravity() {
        return Gravity.BOTTOM;
    }
}
