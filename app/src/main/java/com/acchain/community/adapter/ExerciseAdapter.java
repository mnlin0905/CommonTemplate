package com.acchain.community.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseRecyclerViewHolder;
import com.acchain.community.bean.ExerciseProduct;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder> {
    private Context context;
    private List<ExerciseProduct.ListBean> data;

    public ExerciseAdapter(Context context) {
        this(context, null);
    }

    public ExerciseAdapter(Context context, List<ExerciseProduct.ListBean> data) {
        this.context = context;
        this.data = data;
    }

    public void setData(List<ExerciseProduct.ListBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_exercise, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ExerciseProduct.ListBean listBean = data.get(position);
        Glide.with(context).load(context.getString(R.string.test_base_image_address)+listBean.getProductImg()).into(holder.image);
        holder.name.setText(listBean.getProductName());
        holder.describe.setText(listBean.getShortDesc());
        holder.specification.setText(listBean.getExerciseSpecification());
        holder.specification.setTypeface(Typeface.DEFAULT_BOLD);//设置粗体
        holder.repertory.setText(listBean.getFreightCharge()+"");
        holder.exerciseNum.setText(listBean.getCirculation()+"");
        holder.endTime.setText(listBean.getExerciseEndTime());
        holder.toExercise.setOnClickListener(v -> {
            ARouter.getInstance().build(ARouterConst.Activity_ExerciseGoodsActivity).withInt("exerciseId",listBean.getExerciseId()).navigation();
        });
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }


    public static class ViewHolder extends BaseRecyclerViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.describe)
        TextView describe;
        @BindView(R.id.specification)
        TextView specification;
        @BindView(R.id.repertory)
        TextView repertory;
        @BindView(R.id.exercise_num)
        TextView exerciseNum;
        @BindView(R.id.end_time)
        TextView endTime;
        @BindView(R.id.to_exercise)
        TextView toExercise;
        public ViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
        }

        @Override
        protected boolean isXRecyclerView() {
            return false;
        }
    }
}

