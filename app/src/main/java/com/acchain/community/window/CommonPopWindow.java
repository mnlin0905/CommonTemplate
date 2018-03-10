package com.acchain.community.window;

import com.acchain.community.R;
import com.acchain.community.arouter.ARouterConst;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.base.BaseApplication;
import com.acchain.community.window.popupwindow.ListViewPopup;
import com.alibaba.android.arouter.launcher.ARouter;

import java.util.LinkedList;

/**
 * Created on 2018/1/24
 * function : 多个activity中通用的popwindow
 *·
 * @author ACChain
 */

public class CommonPopWindow {
    private CommonPopWindow() {

    }

    public static ListViewPopup getInstance(BaseActivity activity, ListViewPopup.OnItemListener listener, boolean[] appear) {
        int[] total = new int[]{R.drawable.icon_message_white, R.drawable.icon_home, R.drawable.icon_service_white, R.drawable.icon_comment_white};

        LinkedList<Integer> linkedList = new LinkedList();
        for (int i = 0; i < appear.length; i++) {
            if (appear[i]) {
                linkedList.add(total[i]);
            }
        }
        int[] temp = new int[linkedList.size()];
        for (int i = 0; i < linkedList.size(); i++) {
            temp[i] = linkedList.get(i);
        }

        ListViewPopup listViewPopup = new ListViewPopup(
                BaseApplication.app,
                BaseApplication.app.getResources().getStringArray(R.array.goods_transaction_foot_pop_menu),
                temp);

        listViewPopup.setOnItemListener(position -> {
            switch (position) {
                case 0://消息
                    ARouter.getInstance().build(ARouterConst.Activity_NewsActivity).navigation();
                    break;
                case 1://首页
                    ARouter.getInstance().build(ARouterConst.Activity_MainActivity).navigation();
                    break;
                case 2://在线客服
                    ARouter.getInstance().build(ARouterConst.Activity_MainActivity).navigation();
                    break;
                case 3://我的评价
                    // TODO: 2018/2/2 评价
                    break;

            }
        });

        return listViewPopup;
    }

    public static ListViewPopup getInstance(BaseActivity activity, ListViewPopup.OnItemListener listener) {

        ListViewPopup listViewPopup = new ListViewPopup(
                BaseApplication.app,
                BaseApplication.app.getResources().getStringArray(R.array.goods_transaction_foot_pop_menu),
                new int[]{R.drawable.icon_message_white, R.drawable.icon_home, R.drawable.icon_service_white, R.drawable.icon_comment_white});

        listViewPopup.setOnItemListener(position -> {
            switch (position) {
                case 0://消息
                    ARouter.getInstance().build(ARouterConst.Activity_NewsActivity).navigation();
                    break;
                case 1://首页
                    ARouter.getInstance().build(ARouterConst.Activity_MainActivity).navigation();
                    break;
                case 2://在线客服
                    ARouter.getInstance().build(ARouterConst.Activity_MainActivity).navigation();
                    break;
                case 3://我的评价
                    // TODO: 2018/2/2 评价
                    break;

            }
        });

        return listViewPopup;
    }
}
