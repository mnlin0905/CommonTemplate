package com.acchain.community.adapter;

import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.acchain.community.R;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.bean.QuestionBean;
import com.acchain.community.view.LineMenuView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2018/1/22
 * function : 问题列表
 *
 * @author ACChain
 */

public class QuestionAdapter extends BaseAdapter {
    private BaseActivity context;
    private List<QuestionBean> datas;

    public QuestionAdapter(BaseActivity context, List<QuestionBean> datas) {
        this.context = context;
        this.datas = datas == null ? new ArrayList<QuestionBean>() : datas;
    }

    @Override
    public int getCount() {
        return  datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO: 2018/1/11
        //QuestionBean bean = datas.get(position);
        if (convertView == null) {
            LineMenuView lmv = new LineMenuView(context);
            lmv.setPlugin(1);
            lmv.setMenuTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimensionPixelSize(R.dimen.text_size_normal_14sp));
            lmv.setNavigation(context.dispatchGetDrawable(R.drawable.icon_arrow_right_gray));
            int padding = context.getResources().getDimensionPixelSize(R.dimen.view_padding_margin_16dp);
            lmv.setPadding(padding, 0, padding, 0);
            convertView = lmv;
        }
        
        // TODO: 2018/1/22 更改标题
        ((LineMenuView) convertView).setMenu("标题信息");
        return convertView;
    }
}
