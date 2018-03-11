package com.common.template.dagger.module;

import android.os.Build;
import android.widget.Toast;

import com.common.template.base.BaseApplication;
import com.common.template.retrofit.HttpInterface;
import com.common.template.window.BigToast;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 功能----Application的module,为ApplicationComponent提供对象生成器
 * Created by MNLIN on 2017/9/22
 */
@Singleton
@Module
public class ApplicationModule {
    String tag = "";
    private BaseApplication application;

    public ApplicationModule(BaseApplication application) {
        this.application = application;
    }

    /**
     * 全局唯一的toast
     */
    @Provides
    @Singleton
    Toast provideToast() {
        return BigToast.makeText(application, "保存成功", Toast.LENGTH_SHORT);
    }

    @Provides
    @Singleton
    HttpInterface provideHttpInterface() {
        //网络请求的Host
        String baseUrl = BaseApplication.app.getBaseNetUrl();

        //生成JSON转换的库
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .setDateFormat("yyyy:MM:dd HH:mm:ss")
                .create();
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);

        //生成RxJava转换的adapter
        RxJava2CallAdapterFactory rxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create();

        //生成OkHttp网络传输的客户端
        HashMap<String, List<Cookie>> cookieStore = new HashMap<>();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(new CookieJar() {
                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        cookieStore.put(url.host(), cookies);
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        List<Cookie> cookies = cookieStore.get(url.host());
                        return cookies != null ? cookies : new ArrayList<>();
                    }
                })
                .addInterceptor(chain -> {
                    Request request = chain.request()
                            .newBuilder()
                            .addHeader("SDK", String.valueOf(Build.VERSION.SDK_INT ))
                            .build();
                    return chain.proceed(request);
                })
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addNetworkInterceptor(new StethoInterceptor())
                .connectTimeout(2000, TimeUnit.MILLISECONDS)
                .readTimeout(2000, TimeUnit.MILLISECONDS)
                .writeTimeout(2000,TimeUnit.MILLISECONDS)
                .build();

        //最后组合成Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build();

        //将注解后的interface请求接口转换为真正可用的网络请求对象
        return retrofit.create(HttpInterface.class);
    }
}
