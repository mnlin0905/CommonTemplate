package com.common.template.retrofit;

import com.common.template.base.BaseHttpBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 功能----使用retrofit框架与http交换数据
 * <p>
 * Created by MNLIN on 2017/9/25.
 * <p>
 */

public interface HttpInterface {
    /**
     * 1.0.1 登录
     *
     * @param username 用户名
     * @param pwd      密码
     * @return 登录返回对象
     */
    @POST("login/doLogin")
    @FormUrlEncoded
    Observable<BaseHttpBean<Object>> doLogin(@Field("username") String username,
                                             @Field("pwd") String pwd);
}
