package com.acchain.community.contract;

import com.acchain.community.base.BasePresenter;
import com.acchain.community.bean.AdoptGoodDetail;
import com.acchain.community.bean.ExerciseGoodsDetail;
import com.acchain.community.bean.GoodsCollectionFootBean;
import com.acchain.community.bean.PanicGoodDetail;
import com.acchain.community.bean.PreSellGoodsDetail;

import java.util.List;

/**
 * function---- GoodsCollectionFootContract
 * <p>
 * Created(Gradle default create) by ACChain on 2018/01/23 07:05:11 (+0000).
 */
public interface GoodsCollectionFootContract {
    interface Presenter {
        /**
         * 1.2.2 获取用户收藏列表
         *
         * @param token         登录标志
         * @param commodityId   商品(类别商品)ID
         * @param commodityType 商品所属类别 1:预售 2:领养 3:行权 注:抢购不可收藏
         * @param page          请求页码
         * @param pageSize      页面容量
         */
        void getUserCollections(String token, String commodityId, String commodityType, int page, int pageSize);

        /**
         * 1.3.1 删除收藏记录
         *
         * @param token 登录标志登录标志
         * @param ids   收藏ID集合JSON字符串
         */
        void delCollections(String token, String ids);

        /**
         * 1.3.0 获取用户访问足迹
         *
         * @param token    登录标志登录标志
         * @param deviceId 设备ID
         * @param page     页码
         * @param pageSize 页面容量
         */
        void getAccessLog(String token, String deviceId, int page, int pageSize);

        /**
         * 1.3.2 删除访问足迹记录
         *
         * @param token 登录标志登录标志
         * @param ids   足迹id集合
         */
        void delAccessLogs(String token, String ids);

        /**
         * 查询预售商品详情
         *
         * @param presellId 预售ID
         */
        void getPreSellProductDetail(int presellId,BasePresenter.HttpCallback<PreSellGoodsDetail> httpCallback);

        /**
         * 查询领养商品详情
         *
         * @param adoptId 商品ID
         */
        void getAdoptProductDetail(int adoptId,BasePresenter.HttpCallback<AdoptGoodDetail> httpCallback);

        /**
         * 查询行权商品详情
         *
         * @param exerciseId 商品ID
         */
        void getExerciseProductDetail(int exerciseId,BasePresenter.HttpCallback<ExerciseGoodsDetail> httpCallback);

        /**
         * 获取限时抢购详情
         *
         * @param purchaseId  商品id
         * @param flashSaleId 活动id
         */
        void getFlashSalesDetail(int purchaseId, int flashSaleId,BasePresenter.HttpCallback<PanicGoodDetail> httpCallback);
    }

    interface View {
        /**
         * 1.2.2 获取用户收藏列表
         *
         * @param goodsCollectionFootBeans 详细收藏记录
         */
        void getUserCollectionsFinish(List<GoodsCollectionFootBean> goodsCollectionFootBeans);

        /**
         * 1.3.1 删除收藏记录
         */
        void delCollectionsFinish();

        /**
         * 1.3.0 获取用户访问足迹
         *
         * @param accessLogBeans 足迹记录
         */
        void getAccessLogFinish(List<GoodsCollectionFootBean> accessLogBeans);

        /**
         * 1.3.2 删除访问足迹记录
         */
        void delAccessLogsFinish();

    }
}