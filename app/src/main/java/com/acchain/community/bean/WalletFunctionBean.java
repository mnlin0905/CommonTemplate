package com.acchain.community.bean;

import android.support.annotation.IdRes;

import com.acchain.community.base.BaseBean;

import java.util.ArrayList;

/**
 * Created on 2018/1/2
 * function : 钱包主功能model
 *
 * @author ACChain
 */

public class WalletFunctionBean extends BaseBean {

    /**
     * resOrImageId : 1
     * title :
     * subTitle :
     */

    private int resOrImageId;
    private String title;
    private String subTitle;

    public WalletFunctionBean(int resOrImageId, String title, String subTitle) {
        this.resOrImageId = resOrImageId;
        this.title = title;
        this.subTitle = subTitle;
    }

    public static ArrayList<WalletFunctionBean> getListDatas(@IdRes int[] resId, String[] titles, String[] subTitles) {
        ArrayList<WalletFunctionBean> datas = new ArrayList<>();
        if(resId.length!=titles.length||titles.length!=subTitles.length||subTitles.length==0){
            return datas;
        }
        for (int i = 0; i < titles.length; i++) {
            datas.add(new WalletFunctionBean(resId[i],titles[i],subTitles[i]));
        }
        return datas;
    }

    public int getResOrImageId() {
        return resOrImageId;
    }

    public void setResOrImageId(int resOrImageId) {
        this.resOrImageId = resOrImageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
}
