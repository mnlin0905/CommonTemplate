package com.acchain.community.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.acchain.community.R;
import com.acchain.community.bean.ContactAddRequest;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rsp on 2018/1/22.
 */

public class NewFriendListAdapter extends ArrayAdapter<ContactAddRequest> {
    private Context context;
    private OnAddFriendListener listener;
    public interface OnAddFriendListener{
       void onAddFriend(ContactAddRequest contactAddRequest);
    }
    public NewFriendListAdapter(@NonNull Context context, @NonNull List<ContactAddRequest> objects,OnAddFriendListener listener) {
        super(context, 0, objects);
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_friend_request_message, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ContactAddRequest item = getItem(position);
        Glide.with(convertView).load(item.getHeadImg()).into(viewHolder.headImg);
        viewHolder.nikeName.setText(item.getName());
        viewHolder.message.setText(item.getMessage());
        if (item.getOperation().equals(ContactAddRequest.CONTACT_OPERATION_REQUEST)) {
            viewHolder.tvOperate.setText("接受");
            viewHolder.tvOperate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onAddFriend(item);
                    }
                }
            });
            viewHolder.tvOperate.setBackgroundResource(R.drawable.new_friend_btn_bg);
        } else if (item.getOperation().equals(ContactAddRequest.CONTACT_OPERATION_ACCEPT_RESPONSE)) {
            viewHolder.tvOperate.setText("已接受");
            viewHolder.tvOperate.setBackgroundColor(Color.TRANSPARENT);
            viewHolder.tvOperate.setOnClickListener(null);
        }
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.headImg)
        ImageView headImg;
        @BindView(R.id.nikeName)
        TextView nikeName;
        @BindView(R.id.message)
        TextView message;
        @BindView(R.id.tv_operate)
        TextView tvOperate;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
