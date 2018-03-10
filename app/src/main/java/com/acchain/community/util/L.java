package com.acchain.community.util;

import android.util.Log;

/**
 * @Description 对log的封装，可以知道在哪个类哪行哪个方法打印的
 * @Author lijie
 * @Date 2017/6/27.
 */
public class L {
    public static boolean isPrint = true;//是否要打印日志

    public static void v(String msg){
        print(Log.VERBOSE,msg);
    }
    public static void e(String msg){
        print(Log.ERROR,msg);
    }
    public static void d(String msg){
        print(Log.DEBUG,msg);
    }
    public static void w(String msg){
        print(Log.WARN,msg);
    }
    public static void i(String msg){
        print(Log.INFO,msg);
    }
    public static void i(String tag,String msg){
        print(Log.INFO,msg);
    }
    public static void a(String msg){
        print(Log.ASSERT,msg);
    }
    private static void print(int level,String msg){
        if (isPrint)
        {
            String tag = getClassName();
            String line = getLineIndicator();
            Log.println(level, tag, line + msg);
        }
    }
    private static void print(int level,String msg,String tag){
        if (isPrint)
        {
            StringBuffer sb=new StringBuffer();
            sb.append(getClassName());
            sb.append("+"+tag);
            String TAG = sb.toString();
            String line = getLineIndicator();
            Log.println(level, TAG, line + msg);
        }
    }

    private static String getClassName()
    {
        StackTraceElement element = ((new Exception()).getStackTrace())[3];

        return element.getFileName();
    }

    //获取行所在的方法指示
    private static String getLineIndicator() {
        //3代表方法的调用深度：0-getLineIndicator，1-performPrint，2-print，3-调用该工具类的方法位置
        StackTraceElement element = ((new Exception()).getStackTrace())[3];
        return "(" +
                element.getFileName() + ":" +
                element.getLineNumber() + ")." +
                element.getMethodName() + ":";
    }
}
