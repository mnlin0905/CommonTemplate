package com.common.template.util;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.os.StatFs;
import android.provider.Settings;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.Palette;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.blankj.utilcode.util.ActivityUtils.startActivity;

/**
 * Created by Administrator on 16-12-30.
 * <p/>
 * 用于设置activity特征,或读取一一些activity信息的的工具类
 */
public class ActivityUtil {
    /**
     * 设置activity无actionBar
     *
     * @return true表示设置成功，否则表示失败
     */
    public static boolean setActivtyNoTitle(Activity activity) {
        return activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    /**
     * 设置activity页面跳转时接受动画效果
     *
     * @return true表示设置成功，否则表示失败
     */
    public static boolean setActivityContentTransitions(Activity activity) {
        return activity.requestWindowFeature(Window.FEATURE_CONTENT_TRANSITIONS);
    }

    /**
     * 设置activity切换时支持共享元素动画
     *
     * @return true表示设置成功，否则表示失败
     */
    public static boolean setActivitySupportTransitions(Activity activity) {
        return activity.getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
    }

    /**
     * 沉浸式布局：让状态栏等内容完全消失
     * 设置activity为全屏状态(无状态栏，导航栏等内容)
     */
    public static void setImmersiveMode(Activity activity) {
        if (Build.VERSION.SDK_INT < 16) {
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager
                    .LayoutParams.FLAG_FULLSCREEN);
        } else {
            if (activity.getActionBar() != null) {
                activity.getActionBar().hide();
            }

            if (Build.VERSION.SDK_INT >= 19) {
                activity.getWindow().getDecorView().setSystemUiVisibility(View
                        .SYSTEM_UI_FLAG_LAYOUT_STABLE | View
                        .SYSTEM_UI_FLAG_LOW_PROFILE | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }
        }
    }

    /**
     * 获取屏幕信息，包括屏幕宽度高度的像素值，density值
     *
     * @return 屏幕信息
     */
    public static DisplayMetrics getScreenInfomations(Activity activity) {
        DisplayMetrics metrics;
        if ((metrics = activity.getResources().getDisplayMetrics()) != null) {
            activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        }
        return metrics;
    }

    /**
     * 安卓内容全屏化：状态栏透明,且高度不占用整体布局
     * <p/>
     * 安卓屏幕元素：状态栏->ActionBar->内容区->底部导航栏
     * <p/>
     * 设置安卓透明状态栏、透明导航栏效果
     */
    public static void setDecorTransparent(Activity activity) {
        //api大于21，则让顶部状态栏、底部导航栏透明
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        //使ActionBar消失
        ActionBar actionBar = activity.getActionBar();
        if (actionBar != null) actionBar.hide();
    }

    /**
     * 设置状态栏颜色
     */
    public static void setStatusBarColor(Activity activity, int color) {
        //api大于21，则让顶部状态栏、底部导航栏透明
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            activity.getWindow().setStatusBarColor(color);
        } else {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        //使ActionBar消失
        ActionBar actionBar = activity.getActionBar();
        if (actionBar != null) actionBar.hide();
    }

    /**
     * 切换系统输入法是否为可见状态
     *
     * @param visibility true表示设置输入法可见
     */
    public static void toggleInputMethodVisibility(Activity activity, View view, boolean
            visibility) {
        InputMethodManager manager = (InputMethodManager) activity.getSystemService(Context
                .INPUT_METHOD_SERVICE);
        if (!visibility) {
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } else {
            activity.getWindow().setSoftInputMode(WindowManager.LayoutParams
                    .SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            manager.showSoftInput(view, 0);
        }
    }

    /**
     * 设置系统输入法显示的模式
     *
     * @param visibility true表示设置输入法模式为一直可见
     */
    public static void setWindowSoftInputMode(Activity activity, boolean visibility) {
        if (!visibility) {
            activity.getWindow().setSoftInputMode(WindowManager.LayoutParams
                    .SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        } else {
            activity.getWindow().setSoftInputMode(WindowManager.LayoutParams
                    .SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    /**
     * 获取设备GPS状态
     *
     * @return true表示GPS功能已打开
     */
    public static boolean getGPSState(Activity activity) {
        final LocationManager manager = (LocationManager) activity.getSystemService(Context
                .LOCATION_SERVICE);
        if (manager == null) {
            return false;
        } else {
            return manager.isProviderEnabled("gps");
        }
    }

    /**
     * 获取app的名称
     *
     * @param packageName app的包名
     * @return null表示出现异常，正常情况返回app名字的字符串形式
     */
    public static String getAPPName(Activity activity, String packageName) {
        try {
            ApplicationInfo applicationInfo = activity.getPackageManager().getPackageInfo(packageName,
                    0).applicationInfo;
            return (String) activity.getPackageManager().getApplicationLabel(applicationInfo);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取手机SDK版本
     */
    public static int getSDKVersion() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 判断网络是否可用
     *
     * @return 返回一个整数，表示可用的网络：
     * 1——无线网络  2——移动网络  3——其他网络  0——无可用网络
     */
    public static int isConnection(Activity activity) {
        ConnectivityManager manager = (ConnectivityManager) activity.getSystemService(Context
                .CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                return 1;
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                return 2;
            } else {
                return 3;
            }
        } else {
            return 0;
        }
    }

    /**
     * 强行退出程序
     */
    public static void exitApplication() {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    /**
     * 通过MD5加密数据
     */
    public static byte[] encodeByMD5(byte[] message) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(message);
            return digest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 判断运行该方法的线程是不是主线程
     */
    public static boolean currentIsMainThread() {
        try {
            return Thread.currentThread() == Looper.getMainLooper().getThread();
        } catch (Exception e) {
            try {
                return Looper.myLooper() == Looper.getMainLooper();
            } catch (Exception e1) {
                return false;
            }
        }
    }

    /**
     * 获取屏幕状态栏高度（以像素为单位）
     */
    public static int getStatusBarHeightPixels(Context context) {
        try {
            Class c = Class.forName("com.android.internal.R$dimen");
            Object o = c.newInstance();
            Field f = c.getField("status_bar_height");
            int x = Integer.parseInt(f.get(o).toString());
            return context.getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 判断当前界面是否是 桌面HOME
     */
    public static boolean presentActivityIsHome(Context context) {
        //获取当前正在运行的活动栈
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = am.getRunningTasks(1);

        //获取属于桌面的应用的应用程序包名
        List<String> names = new ArrayList<String>();
        PackageManager pm = context.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        List<ResolveInfo> resolveInfos = pm.queryIntentActivities(intent, PackageManager
                .MATCH_DEFAULT_ONLY);
        for (ResolveInfo o : resolveInfos) {
            names.add(o.activityInfo.packageName);
        }

        //判断当前活动栈的包名和属于桌面的应用程序包名是否相同
        return names.contains(runningTaskInfos.get(0).topActivity.getPackageName());
    }

    /**
     * 判断某个intent对应的apk是否已经安装，只有安装成功后才可以正常响应其他程序发起的intent
     *
     * @return 如果存在则返回true，否则返回false
     */
    public static boolean activityIsExistForIntent(Context context, Intent intent) {
        PackageManager pm = context.getPackageManager();
        //pm.queryBroadcastReceivers
        //pm.queryIntentServices
        //pm.queryContentProviders
        List<ResolveInfo> resolveInfos = pm.queryIntentActivities(intent, PackageManager
                .MATCH_DEFAULT_ONLY);
        if (resolveInfos == null || resolveInfos.size() == 0) {
            return false;
        }
        return true;
    }

    /**
     * 安装指定的APK文件，传入apk文件所在的路径，调用内置软件对应用进行安装
     *
     * @param filePathForAPK apk文件的路径
     * @return 是否可以正常安装
     */
    public static boolean installAPK(Context context, String filePathForAPK) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.parse("file://" + filePathForAPK), "application/vnd.android" + "" +
                    ".package-archive");
            context.startActivity(Intent.createChooser(intent, "请选择安装APK的应用"));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向剪贴板中传入数据
     *
     * @param msg 需要向剪贴板中传入的数据
     * @return 是否可以成功传入数据
     */
    public static boolean saveMsgToClipboard(Context context, String msg) {
        try {
            ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            cm.setPrimaryClip(ClipData.newPlainText(null, msg));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 从剪贴板中取出之前存入的数据
     *
     * @return 当剪贴板中无数据时，返回null；否则返回存入的数据
     */
    public static String getMsgFromClipboard(Context context) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = cm.getPrimaryClip();
        if (clip != null && clip.getItemCount() > 0) {
            return clip.getItemAt(0).coerceToText(context).toString();
        }
        return null;
    }

    /**
     * 通过移动设备直接拨号
     *
     * @param tel 电话号码
     * @return true表示可以成功进行拨号
     */
    public static boolean dialDirectly(Context context, String tel) {
        try {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tel));
            context.startActivity(Intent.createChooser(intent, "直接拨号"));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向可以拨号的程序传入电话号码,然后再进行拨号
     *
     * @param tel 电话号码
     * @return true表示可以成功进行拨号
     */
    public static boolean dialByOtherApplication(Context context, String tel) {
        try {
            //Intent intent=new Intent("com.android.phone.action.TOUCH_DIALER");
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tel));
            context.startActivity(Intent.createChooser(intent, "向可以拨号的程序传入号码"));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 调用系统浏览器浏览网页
     *
     * @param url 网址
     * @return true表示可以成功进行浏览
     */
    public static boolean browseWebsite(Context context, String url) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(Intent.createChooser(intent, "浏览网址"));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 调用系统程序查看联系人
     *
     * @return true表示可以成功进行查看
     */
    public static boolean browseContacts(Context context) {
        try {
            Intent intent = new Intent("com.android.contacts.action.LIST_CONTACTS");
            context.startActivity(Intent.createChooser(intent, "查看联系人"));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 查看系统设置界面
     *
     * @return true表示可以成功进行查看
     */
    public static boolean intoSettingsSurface(Context context) {
        try {
            Intent intent = new Intent("android.settings.SETTINGS");
            context.startActivity(Intent.createChooser(intent, "进入系统设置界面"));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置Activity进入退出时的动画效果
     *
     * @param enterAnim 进入时的动画效果
     * @param exitAnim  退出时的动画效果
     */
    public static void overridePendingTransition(Activity activity, int enterAnim, int exitAnim) {
        activity.overridePendingTransition(enterAnim, exitAnim);
    }

    /**
     * 从某个应用返回桌面
     */
    public static void returnHome(Context context) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        context.startActivity(intent);
    }

    /**
     * 是否存在activity去响应某个Intent
     *
     * @return true 表示存在activity可以去响应intent
     */
    public static boolean existActivityToResponse(Context context, Intent intent) {
        return intent.resolveActivity(context.getPackageManager()) != null;

    }

    /**
     * @return 检测某个辅助服务是否开启
     */
    public static boolean isAccessibilitySettingsOn(Context context, Class clazz) {
        try {
            String service = context.getPackageName() + "/" + clazz.getCanonicalName();
            int enable = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure
                    .ACCESSIBILITY_ENABLED);
            if (enable == 1) {
                String value = Settings.Secure.getString(context.getContentResolver(), Settings.Secure
                        .ENABLED_ACCESSIBILITY_SERVICES);
                TextUtils.SimpleStringSplitter splitter = new TextUtils.SimpleStringSplitter(':');
                if (value != null) {
                    splitter.setString(value);
                    while (splitter.hasNext()) {
                        String temp = splitter.next();
                        Log.v("以下辅助服务已经开启：", temp);
                        if (temp.equalsIgnoreCase(service)) {
                            //有权限
                            return true;
                        }
                    }
                }
            }
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 通过调色板获取符合风格的颜色
     */
    @TargetApi(21)
    public static int getPaletteColor(final Activity activity, @DrawableRes int resourceId) {
        Bitmap b = BitmapFactory.decodeResource(activity.getResources(), resourceId);
        Palette.Builder builder = Palette.from(b);
        Palette palette = builder.generate();
        //获取到充满活力的色调
        List<Palette.Swatch> swatches = palette.getSwatches();
        if (swatches != null && swatches.size() > 0) {
            int color = swatches.get(0).getRgb();
            return color;
        }
        return Color.GRAY;
    }

    /**
     * 判断应用是否具有某个权限
     */
    @SuppressWarnings("all")
    public static boolean checkPermission(Context context, String permission) {
        if (permission.equalsIgnoreCase(Manifest.permission.SYSTEM_ALERT_WINDOW) && getSDKVersion() >=
                23) {
            return Settings.canDrawOverlays(context);
        } else {
            return ContextCompat.checkSelfPermission(context, permission) == PackageManager
                    .PERMISSION_GRANTED;
        }
    }

    /**
     * 获取应用当前内存,单位：KB
     *
     * @return 若失败则返回-1，则否返回总的内存值
     */
    public static String getTotalMemory(Context context) {
        String path = "/proc/meminfo";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(path), 8192);
            String info = reader.readLine();
            reader.close();
            return info;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取当前可用内存（RAM）,单位：KB
     *
     * @return 若失败则返回空数组，则否返回有效数组infos：pos0表示总内存，pos1表示可用的内存，pos2表示内存警戒线，即开始垃圾回收时可用的内存值
     */
    public static long[] getRAMInfo(Context context) {
        long[] infos = new long[3];
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        infos[0] = mi.totalMem;
        infos[1] = mi.availMem;
        infos[2] = mi.threshold;
        return infos;
    }

    /**
     * 获取当前ROM信息,单位：KB
     *
     * @return 若失败则返回空数组，则否返回有效数组infos：pos0表示总内存，pos1表示可用的内存
     */
    public static long[] getROMInfo(Context context) {
        long[] infos = new long[2];
        File path = Environment.getDataDirectory();
        StatFs statFs = new StatFs(path.getPath());
        long blockSize = statFs.getBlockSizeLong();
        long blockAmountTotal = statFs.getBlockCountLong();
        long blockAmountAvailable = statFs.getAvailableBlocksLong();
        infos[0] = blockAmountTotal * blockSize;
        infos[1] = blockAmountAvailable * blockSize;
        return infos;
    }

    /**
     * 获取当前CPU信息
     *
     * @return 若失败则返回空数组，则否返回有效数组infos：pos0表示cpu使用率,pos1表示用户进行执行cpu时间；pos2表示系统进程执行cpu时间
     */
    public static int[] getCPUInfo(Context context) {
        int[] infos = new int[3];
        try {
            String[] cmd = new String[]{"top", "-m", "2", "-n", "1"};
            Process process = Runtime.getRuntime().exec(cmd);
            process.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = reader.readLine();
            if (line == null) return infos;
            reader.close();
            String[] cpus = line.split(",");
            infos[1] = Integer.parseInt(cpus[0].trim().split(" ")[1].replaceAll("[%\\s]", ""));
            infos[2] = Integer.parseInt(cpus[1].trim().split(" ")[1].replaceAll("[%\\s]", ""));
            infos[0] = infos[1] + infos[2];
            Log.e(TAG, "getCPUInfo: " + line + "  " + infos[0] + "  " + infos[1] + "  " + infos[2]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return infos;
    }

    /**
     * 设备是否具有root权限
     */
    public static boolean hasRootPermission(Context context) {
        try {
            Process process = Runtime.getRuntime().exec("su");
            if (process.getInputStream().available() == 0) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 进入系统的权限请求界面
     */
    public static void intoPermissionSettingPage(Context context) {
        Intent intent1 = new Intent();
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent1.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent1.setData(Uri.fromParts("package", context.getPackageName(), null));
        ComponentName componentName = intent1.resolveActivity(context.getPackageManager());
        if (componentName != null) {
            startActivity(intent1);
        }
    }

    /**
     * 判断某个view是否可见
     */
    public static boolean isViewVisible(View view) {
        if (view == null) {
            return false;
        }

        boolean isVisible = false;

        //检测是否被父元素遮挡
        Rect rect = new Rect();
        boolean can = view.getLocalVisibleRect(rect);
        if (can) {
            isVisible = (rect.bottom - rect.top) * (rect.right - rect.left) > 0;
        }

        /*
        * 检测可见性：
        * 1、查看view是否在当前的window窗口内；
        * 2、检测自身以及所有的父view是否为VISIBLE*/
        return isVisible && view.hasWindowFocus() && view.isShown() && view.getVisibility() == View.VISIBLE;
    }

}

