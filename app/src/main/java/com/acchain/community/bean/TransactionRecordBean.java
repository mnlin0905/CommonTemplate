package com.acchain.community.bean;

import com.acchain.community.R;
import com.acchain.community.base.BaseBean;
import com.acchain.community.util.Const;

/**
 * Created on 2018/1/24
 * function : 交易记录列表信息item
 *
 * @author ACChain
 */

public class TransactionRecordBean extends BaseBean {

    /**
     * createTime : 2018-02-05 15:49:59
     * num : 3
     * name : 雪山古树1
     * id : 746
     * type : 1
     * status : 0
     */

    private String createTime;
    private int num;
    private String name;
    private int id;
    private int type;
    private int status;

    /**
     * @return 返回中文格式的类别
     */
    public String getTypeStr() {
        int type = this.type + 1;
        if (type == Const.TYPE_PRODUCT_ADOPT) {
            return "领养";
        } else if (type == Const.TYPE_PRODUCT_PRE_ORDER) {
            return "预售";
        } else {
            return "其他";
        }
    }

    /**
     * @return 获取当前状态
     */
    public String getStatusStr() {
        // TODO: 2018/3/1 完成
        switch (status) {
            default:
                return "完成";
        }
    }

    /**
     * @return 获取当前状态应该显示的颜色
     */
    public int getStatusColor() {
        switch (status) {
            case 0:
                return dispatchGetColor(R.color.white_no_selected_40);
            default:
                return dispatchGetColor(R.color.yellow_text_color_FD);
        }
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
