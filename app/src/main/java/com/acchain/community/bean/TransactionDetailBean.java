package com.acchain.community.bean;

import com.acchain.community.R;
import com.acchain.community.base.BaseActivity;
import com.acchain.community.base.BaseBean;

import java.util.List;

/**
 * Created on 2018/3/5
 * function : 交易列表详细信息内容
 *
 * @author ACChain
 */

public class TransactionDetailBean extends BaseBean {

    /**
     * columns : {"amount":597,"img":"group1/M00/00/02/wKgB_Vpi4tuAd3IyAAFTSLCgDUw592.png","num":3,"businessName":"333","type":1,"createTime":"2018-02-06 15:51:50","price":199,"name":"鳄鱼宝","startTime":"2018-04-18 10:00:00","id":750,"hash":"JY_20180206155149819","status":0,"desc":""}
     * items : [{"code":"EYB01-0001","photo":"","id":4,"video":""},{"code":"EYB01-0002","photo":"","id":5,"video":""},{"code":"EYB01-0003","photo":"","id":6,"video":""}]
     */

    private Columns columns;
    private List<Items> items;

    public Columns getColumns() {
        return columns;
    }

    public void setColumns(Columns columns) {
        this.columns = columns;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public static class Columns {
        /**
         * amount : 597
         * img : group1/M00/00/02/wKgB_Vpi4tuAd3IyAAFTSLCgDUw592.png
         * num : 3
         * businessIcon : 商家图片
         * businessName : 333
         * type : 1
         * createTime : 2018-02-06 15:51:50
         * price : 199
         * name : 鳄鱼宝
         * startTime : 2018-04-18 10:00:00
         * id : 750
         * hash : JY_20180206155149819
         * status : 0 : 订单状态 0-待付款->1-待入账->2-已完成->4-已取消
         * specification: "" 规格
         * “payWay”:0 //支付方式 0-未付款 1-支付宝 2-微信 3-钱包支付,
         * “serialCode”:”支付流水号”,
         * desc :
         */

        private int amount;
        private String img;
        private int num;
        private String businessName;
        private String businessIcon;
        private int type;
        private String createTime;
        private int price;
        private String name;
        private String startTime;
        private int id;
        private String hash;
        private int status;
        private String desc;
        private String specification;
        private String serialCode;
        private int payWay;

        /**
         * @return 返回状态
         */
        public String getStatusStr() {
            switch (status) {
                case 0:
                    return "代付款";
                case 1:
                    return "待入账";
                case 2:
                    return "已完成";
                case 4:
                default:
                    return "已取消";
            }
        }

        /**
         * @return 返回颜色值
         */
        public int getStatusColor(BaseActivity context) {
            switch (status) {
                case 0:
                case 1:
                    return context.dispatchGetColor(R.color.white_selected_1);
                case 2:
                case 4:
                default:
                    return context.dispatchGetColor(R.color.yellow_text_color_ff);
            }
        }

        /**
         * @return 返回支付方式
         */
        public String getPayWayStr() {
            switch (payWay) {
                case 0:
                    return "未付款";
                case 1:
                    return "支付宝";
                case 2:
                    return "微信";
                case 3:
                default:
                    return "钱包支付";
            }
        }

        public String getSerialCode() {
            return serialCode;
        }

        public void setSerialCode(String serialCode) {
            this.serialCode = serialCode;
        }

        public int getPayWay() {
            return payWay;
        }

        public void setPayWay(int payWay) {
            this.payWay = payWay;
        }

        public String getSpecification() {
            return specification;
        }

        public void setSpecification(String specification) {
            this.specification = specification;
        }

        public String getBusinessIcon() {
            return businessIcon;
        }

        public void setBusinessIcon(String businessIcon) {
            this.businessIcon = businessIcon;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getBusinessName() {
            return businessName;
        }

        public void setBusinessName(String businessName) {
            this.businessName = businessName;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public static class Items {
        /**
         * code : EYB01-0001
         * photo :
         * id : 4
         * video :
         */

        private String code;
        private String photo;
        private int id;
        private String video;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }
    }
}
