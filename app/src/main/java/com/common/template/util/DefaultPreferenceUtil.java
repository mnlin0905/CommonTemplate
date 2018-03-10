package com.common.template.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;

import com.common.template.base.BaseApplication;
import com.common.template.base.BaseEvent;
import com.common.template.rxbus.RxBus;

import java.io.File;
import java.io.IOException;

/**
 * Created on 2018/1/18
 * function :
 *
 * @author MNLIN
 */

public class DefaultPreferenceUtil {
    /**
     * 登录状态:是否已经登录
     * 登录的token:登录有效期
     * 自己的id
     */
    private static final String LOGIN_STATUS = "account_login_status";
    private static final String LOGIN_TOKEN = "account_login_token";
    private static final String MEMBER_ID = "account_member_id";
    private static final String IS_OFFICIAL_MODE = "IS_OFFICIAL_MODE";

    /**
     *
     */
    private static DefaultPreferenceUtil singleInstance = new DefaultPreferenceUtil();

    /**
     * 编辑对象
     */
    private final SharedPreferences.Editor edit;
    private final SharedPreferences preferences;

    private DefaultPreferenceUtil() {
        preferences = PreferenceManager.getDefaultSharedPreferences(BaseApplication.app);
        edit = preferences.edit();
    }

    /**
     * @return 获取单例对象
     */
    public static DefaultPreferenceUtil getInstance() {
        return singleInstance;
    }

    /**
     * @param context    上下文
     * @param path       后缀路径
     * @param needSDCard 禁止手机在没有sd卡时拍照
     * @return 随机生成存储图片的文件
     */
    public static File getRandomFile(Context context, String path, boolean forbidPhotoWithoutSD) {
        //优先选择外部存储,没有的话选择内部存储
        String basePath;
        if (Environment.isExternalStorageEmulated()) {
            basePath = Environment.getExternalStorageDirectory().getPath();
        } else {
            if (forbidPhotoWithoutSD) {
                RxBus.getInstance().post(new BaseEvent(Const.SHOW_TOAST, "未安装sd卡,无法进行拍照"));
                return null;
            }
            basePath = context.getCacheDir().getPath();
        }

        try {
            File parent = new File(basePath + path);
            if (!parent.exists()) {
                if (!parent.mkdirs()) {
                    RxBus.getInstance().post(new BaseEvent(Const.SHOW_TOAST, "无法创建文件来存储图片"));
                    return null;
                }
            }
            File file = new File(basePath + "/" + System.currentTimeMillis() + ".jpg");
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    RxBus.getInstance().post(new BaseEvent(Const.SHOW_TOAST, "无法创建图片"));
                    return null;
                }
            }
            com.orhanobut.logger.Logger.e("新生成文件路径为:"+file.getPath());
            return file;
        } catch (IOException e) {
            RxBus.getInstance().post(new BaseEvent(Const.SHOW_TOAST, "无法创建文件来存储图片"));
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param context 上下文
     * @param path    后缀路径
     * @return 随机生成存储图片的文件
     */
    public static File getRandomFile(Context context, String path) {
        return getRandomFile(context, path, false);
    }

    /**
     * @return true 表示已经登录
     */
    public boolean hasLogin() {
        return preferences.getBoolean(LOGIN_STATUS, false);
    }

    /**
     * 设置或者清除登录状态
     *
     * @param login true表示设置为登录状态
     * @return true if the new values were successfully written to persistent storage
     */
    public synchronized boolean setLogin(boolean login) {
        return edit.putBoolean("account_login_status", login).commit();
    }

    /**
     * @return String类型, token,null表示没有保存token或者token失效
     */
    public synchronized String getToken() {
        return preferences.getString(LOGIN_TOKEN, null);
    }

    /**
     * 设置或者清除登录token
     *
     * @param token null表示清除token
     * @return true if the new values were successfully written to persistent storage
     */
    public synchronized boolean setToken(String token) {
        return edit.putString(LOGIN_TOKEN, token).commit();
    }

    /**
     * @return String类型, account,null表示没有保存帐号
     */
    public String getAccount() {
        return preferences.getString(LOGIN_TOKEN, null);
    }

    /**
     * 设置或者清除登录帐号
     *
     * @param account null表示清除帐号
     * @return true if the new values were successfully written to persistent storage
     */
    public synchronized boolean setAccount(String account) {
        return edit.putString(LOGIN_TOKEN, account).commit();
    }

    /**
     * 设置或者清除memberId
     *
     * @param memberId null表示memberID
     * @return true if the new values were successfully written to persistent storage
     */
    public synchronized boolean setMemberId(String memberId) {
        return edit.putString(MEMBER_ID, memberId).commit();
    }

    /**
     * @return String类型, memberId,null表示没有保存帐号
     */
    public String getMemberId() {
        return preferences.getString(MEMBER_ID, null);
    }

    /**
     * 默认是正式环境
     *
     * @return true表示正事环境, false表示测试环境
     */
    public boolean isOfficialMode() {
        return preferences.getBoolean(IS_OFFICIAL_MODE, true);
    }

    /**
     * @param isOfficial true表示正事环境,false表示测试环境
     */
    public boolean setOfficialMode(boolean isOfficial) {
        return edit.putBoolean(IS_OFFICIAL_MODE, isOfficial).commit();
    }
}
