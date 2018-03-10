package com.acchain.community.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseRecyclerViewHolder;
import com.acchain.community.bean.Card;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.acchain.community.util.Const.KEY_HEAD_IMG;
import static com.acchain.community.util.Const.KEY_NIKE_NAME;
import static com.acchain.community.util.Const.KEY_TARGET_FRIEND_ID;
import static com.acchain.community.util.Const.KEY_TYPE_ADD_FRIEND_FROM;
import static com.acchain.community.util.Const.TYPE_ADD_FRIEND_FROM_CARD;

/**
 * Created by rsp on 2018/1/18.
 */

public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.ViewHolder> {
    private List<Card> datas;
    private BaseRecyclerViewHolder.OnViewClickListener listener;

    public CardListAdapter(List<Card> datas, BaseRecyclerViewHolder.OnViewClickListener listener) {
        this.datas = datas;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // TODO: 2018/1/2 绑定具体数据,最后一条数据时,分割线需要消失
        Card card = datas.get(position);
        String company_name = card.getCompany_name();
        String nickname = card.getNickname();
        String main_business = card.getMain_business();
        String zhiwei = card.getPosition();
        String phone = card.getPhone();
        String address = card.getAddress();
        String photo_img = card.getPhoto_img();
        String email = card.getEmail();
        if (!StringUtils.isEmpty(photo_img)) {
            Glide.with(holder.headImg).load(photo_img).into(holder.headImg);
        }
        if (!StringUtils.isEmpty(company_name)) {
            holder.unitName.setText(company_name);
        }
        if (!StringUtils.isEmpty(nickname)) {
            holder.nikeName.setText(nickname);
        }
        if (!StringUtils.isEmpty(nickname)) {
            holder.nikeName.setText(nickname);
        }
        if (!StringUtils.isEmpty(main_business)) {
            holder.business.setText(main_business);
        }
        if (!StringUtils.isEmpty(zhiwei)) {
            holder.zhiwei.setText(zhiwei);
        }
        if (!StringUtils.isEmpty(phone)) {
            holder.phone.setText(phone);
        }
        if (!StringUtils.isEmpty(address)) {
            holder.phone.setText(address);
        }
        if (!StringUtils.isEmpty(email)) {
            holder.email.setText(email);
        }
        if (card.isIs_friends()) {
            holder.addFriend.setVisibility(View.INVISIBLE);
            holder.addFriend.setClickable(false);
        } else {
            holder.addFriend.setVisibility(View.VISIBLE);
            holder.addFriend.setClickable(true);
        }
    }

    @Override
    public int getItemCount() {
      return datas.size();
    }



    class ViewHolder extends BaseRecyclerViewHolder {
        @BindView(R.id.headImg)
        ImageView headImg;
        @BindView(R.id.unit_name)
        TextView unitName;
        @BindView(R.id.business)
        TextView business;
        @BindView(R.id.phone)
        TextView phone;
        @BindView(R.id.email)
        TextView email;
        @BindView(R.id.address)
        TextView address;
        @BindView(R.id.addFriend)
        ImageView addFriend;
        @BindView(R.id.nikeName)
        TextView nikeName;
        @BindView(R.id.zhiwei)
        TextView zhiwei;

        ViewHolder(View itemView, OnViewClickListener listener) {
            super(itemView, listener);
        }

        @OnClick(R.id.addFriend)
        public void onViewClicked() {
            int adapterPosition = getAdapterPosition();
            Card card = datas.get(adapterPosition-1);
            ARouter.getInstance()
                    .build(ARouterConst.Activity_FriendAddActivity)
                    .withString(KEY_TYPE_ADD_FRIEND_FROM, TYPE_ADD_FRIEND_FROM_CARD)
                    .withString(KEY_NIKE_NAME,card.getNickname())
                    .withString(KEY_HEAD_IMG,card.getPhoto_img())
                    .withString(KEY_TARGET_FRIEND_ID,String.valueOf(card.getMember_id()))
                    .navigation();
        }


        @Override
        protected boolean isXRecyclerView() {
            return true;
        }
    }
}
