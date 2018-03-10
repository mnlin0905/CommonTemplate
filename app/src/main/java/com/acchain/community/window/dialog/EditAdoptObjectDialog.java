package com.acchain.community.window.dialog;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.base.BaseApplication;
import com.acchain.community.base.BaseRecyclerViewHolder;
import com.acchain.community.bean.AdoptGoodDetail;
import com.acchain.community.util.L;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class EditAdoptObjectDialog extends DialogViewHolder {
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.repertory)
    TextView repertory;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.tv_close)
    TextView tvClose;
    @BindView(R.id.tv_acount)
    TextView tvAcount;
    @BindView(R.id.tv_add_object)
    TextView tvAddObject;
    @BindView(R.id.tv_sure)
    TextView tvSure;
    private EditObjectAdapter editObjectAdapter;
    private Context mContext;
//    private List<AdoptGoodDetail.DefaultAdoptCodeBean> defaultAdoptCode;
    private List<AdoptGoodDetail.DefaultAdoptCodeBean> objects = new ArrayList<>();//此dialog中操作这个对象，当点确定时才真正去改变数据源
    private String productImg;
    private int adoptPrice;


    public EditAdoptObjectDialog(Context context, List<AdoptGoodDetail.DefaultAdoptCodeBean> defaultAdoptCode, String productImg, int adoptPrice) {
        super(context);
        this.mContext = context;
        this.objects.addAll(defaultAdoptCode);
        this.productImg=productImg;
        this.adoptPrice=adoptPrice;
        initView();
    }

    /*当dialog已经创建过了，用来刷新内层的dialog*/
    public void setData(List<AdoptGoodDetail.DefaultAdoptCodeBean> defaultAdoptCode){
        this.objects.clear();
        this.objects.addAll(defaultAdoptCode);
        resetCount();
        editObjectAdapter.notifyDataSetChanged();
    }

    private void initView() {
        recycler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        editObjectAdapter = new EditObjectAdapter(mContext);
        recycler.setAdapter(editObjectAdapter);
        tvClose.setOnClickListener(v -> dismiss());
        tvAddObject.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onAddObject();
            }
        });
        tvSure.setOnClickListener(v -> {
            dismiss();
            if (mListener != null) {
                L.i("objects.size="+objects.size());
                mListener.onSure(objects);
            }
        });
        Glide.with(mContext).load(BaseApplication.getApplication().getBaseImageUrl() + productImg).into(image);
        tvPrice.setText(adoptPrice + "");
        resetCount();
    }

    public void resetCount() {
        tvAcount.setText(objects.size()+"");
    }

    private OnObjectListener mListener;

    public interface OnObjectListener {
        void onAddObject();

        void onSure(List<AdoptGoodDetail.DefaultAdoptCodeBean> Objects);
    }

    public void setOnObjectListener(OnObjectListener listener) {
        this.mListener = listener;
    }

    @Override
    protected int getWidth() {
        return WindowManager.LayoutParams.MATCH_PARENT;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_edit_object;
    }

    @Override
    protected int getWindowAnimations() {
        return R.style.dialog_bottom_to_up;
    }

    @Override
    protected int getGravity() {
        return Gravity.BOTTOM;
    }

    public class EditObjectAdapter extends RecyclerView.Adapter<EditObjectAdapter.ViewHold> {
        private Context mContext;

        public EditObjectAdapter(Context mContext) {
            this.mContext = mContext;
        }

        public void setData(List<AdoptGoodDetail.DefaultAdoptCodeBean> objects){
            L.i("object="+objects.size());
            this.notifyDataSetChanged();
        }

        @Override
        public ViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_edit_object_item, parent, false);
            return new ViewHold(view);
        }

        @Override
        public void onBindViewHolder(ViewHold holder, int position) {
            String name = objects.get(position).getAdoptCode();
            holder.tvName.setText(name);
            holder.tvVideoMonitor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            holder.tvPicture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            holder.tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    objects.remove(position);
                    resetCount();
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getItemCount() {
            return objects == null ? 0 : objects.size();
        }

        public class ViewHold extends BaseRecyclerViewHolder {
            @BindView(R.id.tv_name)
            TextView tvName;
            @BindView(R.id.tv_video_monitor)
            TextView tvVideoMonitor;
            @BindView(R.id.tv_picture)
            TextView tvPicture;
            @BindView(R.id.tv_delete)
            TextView tvDelete;

            public ViewHold(View itemView) {
                super(itemView);
            }

            @Override
            protected boolean isXRecyclerView() {
                return false;
            }
        }
    }
}
