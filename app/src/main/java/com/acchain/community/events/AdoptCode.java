package com.acchain.community.events;

import com.acchain.community.bean.AdoptGoodDetail;

import java.util.List;

/**
 * Created by Administrator on 2018/2/2.
 */

public class AdoptCode {
    private List<AdoptGoodDetail.DefaultAdoptCodeBean> list;
    private int type;
    public AdoptCode(List<AdoptGoodDetail.DefaultAdoptCodeBean> list,int type) {
        this.list = list;
        this.type=type;
    }

    public List<AdoptGoodDetail.DefaultAdoptCodeBean> getList() {
        return list;
    }

    public void setList(List<AdoptGoodDetail.DefaultAdoptCodeBean> list) {
        this.list = list;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
