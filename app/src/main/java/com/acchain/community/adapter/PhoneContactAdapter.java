package com.acchain.community.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.bean.PhoneContactResult;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rong.imkit.widget.AsyncImageView;

import static com.acchain.community.util.Const.KEY_HEAD_IMG;
import static com.acchain.community.util.Const.KEY_NIKE_NAME;
import static com.acchain.community.util.Const.KEY_TARGET_FRIEND_ID;
import static com.acchain.community.util.Const.KEY_TYPE_ADD_FRIEND_FROM;
import static com.acchain.community.util.Const.TYPE_ADD_FRIEND_FROM_PHONE;

/**
 * Created by rsp on 2018/3/1.
 */

public class PhoneContactAdapter extends ArrayAdapter<PhoneContactResult> {
    private List<PhoneContactResult> objects;

    public PhoneContactAdapter(@NonNull Context context, int resource, @NonNull List<PhoneContactResult> objects) {
        super(context, resource, objects);
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(getContext(), R.layout.item_phone_contact, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        PhoneContactResult item = getItem(position);
        viewHolder.nikeName.setText(item.getName());
        viewHolder.phoneName.setText(item.getPhoneName());
        Glide.with(convertView)
                .load(item.getPhotoImg())
                .apply(new RequestOptions()
                        .centerCrop()
                        .placeholder(R.drawable.default_head_img)
                        .error(R.drawable.default_head_img))
                .into(viewHolder.headImg);
        if (item.isFriend()) {
            viewHolder.add.setText("已添加");
            viewHolder.add.setTextColor(getContext().getResources().getColor(R.color.text_color_gray));
            viewHolder.add.setBackgroundColor(Color.TRANSPARENT);
            viewHolder.add.setOnClickListener(null);
        } else {
            viewHolder.add.setText("添加");
            viewHolder.add.setTextColor(getContext().getResources().getColor(R.color.white));
            viewHolder.add.setBackgroundResource(R.drawable.new_friend_btn_bg);
            viewHolder.add.setOnClickListener(v -> ARouter.getInstance()
                    .build(ARouterConst.Activity_FriendAddActivity)
                    .withString(KEY_NIKE_NAME, item.getName())
                    .withString(KEY_HEAD_IMG, item.getPhotoImg())
                    .withString(KEY_TARGET_FRIEND_ID, item.getId() + "")
                    .withString(KEY_TYPE_ADD_FRIEND_FROM, TYPE_ADD_FRIEND_FROM_PHONE)
                    .navigation());
        }
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.headImg)
        AsyncImageView headImg;
        @BindView(R.id.phoneName)
        TextView phoneName;
        @BindView(R.id.nikeName)
        TextView nikeName;
        @BindView(R.id.add)
        TextView add;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
