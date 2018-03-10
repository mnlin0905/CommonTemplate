package com.acchain.community.util;

import android.text.TextUtils;
import com.acchain.community.window.dialog.CommonGoodsAttrsBean;
import java.util.List;

/**
 * 用来处理返回的参数
 */
public class GoodsUtil {

    /**
     * 拿到初始化的默认属性
     *
     * @param attrList 属性集合
     * @return        [1, 3, 5]
     */
    public static int[] initProductAttrValueIds(List<CommonGoodsAttrsBean.AttrListBean> attrList) {
        int[] productAttrValueIds = new int[attrList.size()];
        for (int i = 0; i < attrList.size(); i++) {
            productAttrValueIds[i] = attrList.get(i).getAttrValueList().get(0).getAttrValueId();
        }
        return productAttrValueIds;
    }


    /**
     * 通过属性id转换成文字拼接
     *
     * @param attrList            属性集合
     * @param productAttrValueIds attrList中的attrValueId的集合
     * @return 尺码：S；颜色：深蓝；净含量：500g
     */
    public static String getAttrStr(List<CommonGoodsAttrsBean.AttrListBean> attrList, int[] productAttrValueIds) {
        String attrStr = "";
        for (int i = 0; i < attrList.size(); i++) {
            List<CommonGoodsAttrsBean.AttrListBean.AttrValueListBean> attrValueList = attrList.get(i).getAttrValueList();
            for (int j = 0; j < attrValueList.size(); j++) {
                if (attrValueList.get(j).getAttrValueId() == productAttrValueIds[i]) {
                    attrStr = attrStr + attrList.get(i).getAttrName() + ":" + attrValueList.get(j).getAttrValue() + "；";
                }
            }
        }
        if (!TextUtils.isEmpty(attrStr))
            attrStr = attrStr.substring(0, attrStr.length() - 1);
        return attrStr;
    }

    /**
     * 记录tag选择的位置
     *
     * @param attrList            属性集合
     * @param productAttrValueIds attrList中的attrValueId的集合
     * @return                    [1, 3, 5]
     */
    public static int[] getSelectPosition(List<CommonGoodsAttrsBean.AttrListBean> attrList, int[] productAttrValueIds) {
       int[] selectPosition = new int[productAttrValueIds.length];
        for (int i = 0; i < attrList.size(); i++) {
            List<CommonGoodsAttrsBean.AttrListBean.AttrValueListBean> attrValueList = attrList.get(i).getAttrValueList();
            for (int j = 0; j < attrValueList.size(); j++) {
                if (attrValueList.get(j).getAttrValueId() == productAttrValueIds[i]) {
                    selectPosition[i] = j;
                }
            }
        }
        return selectPosition;
    }

    /**
     * 把数组拼接成字符串
     *
     * @param productAttrValueIds attrList中的attrValueId的集合
     * @return [1, 3, 5]-----"1,3,5" 后台要求的类型
     */
    public static String getStr(int[] productAttrValueIds) {
        String valueIds = "";
        for (int i = 0; i < productAttrValueIds.length; i++) {
            valueIds += productAttrValueIds[i] + ",";
        }
        valueIds = valueIds.substring(0, valueIds.length() - 1);
        return valueIds;
    }

    /**
     * @param attrStr "尺码 :  m,颜色 :  白色,净含量 :  1000g",
     * @return [2, 4, 6]
     */
    public static int[] getAttrValueIds(String attrStr, List<CommonGoodsAttrsBean.AttrListBean> attrList) {
        String[] split = attrStr.split(",");
        int[] productAttrValueIds = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            String[] map = split[i].split(":");
            String value = map[1].trim();
            List<CommonGoodsAttrsBean.AttrListBean.AttrValueListBean> attrValueList = attrList.get(i).getAttrValueList();
            for (int j = 0; j < attrValueList.size(); j++) {
                String trim = attrValueList.get(j).getAttrValue().trim();
                if (trim.equalsIgnoreCase(value)) {
                    int attrValueId = attrValueList.get(j).getAttrValueId();
                    productAttrValueIds[i] = attrValueId;
                }
            }

        }
        L.i("attrStr=" + attrStr);
        return productAttrValueIds;
    }
}
