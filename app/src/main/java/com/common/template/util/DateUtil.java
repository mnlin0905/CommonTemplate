package com.common.template.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 时间转换工具类
 *
 * @date 2015.10.1
 */
public class DateUtil {

    public static final TimeZone tz = TimeZone.getTimeZone("GMT+8:00");
    public static final SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat shortYearFormat = new SimpleDateFormat("yy-MM-dd");
    public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm");
    public static final SimpleDateFormat sequenceFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    public static final SimpleDateFormat yearFormat2 = new SimpleDateFormat("yyyy年MM月dd日");
    public static final SimpleDateFormat monthDayFormat = new SimpleDateFormat("M.d");

    private static final long ONEDAY = 86400000;
    public static final int SHOW_TYPE_SIMPLE = 0;
    public static final int SHOW_TYPE_COMPLEX = 1;
    public static final int SHOW_TYPE_ALL = 2;
    public static final int SHOW_TYPE_CALL_LOG = 3;
    public static final int SHOW_TYPE_CALL_DETAIL = 4;

    /**
     * 系统当前时间
     */
    public static long getCurrentMill() {
        return System.currentTimeMillis();
    }

    /*判断是不是快速点击，间隔1s*/
    public static boolean isFastClick(long lastTime) {
        return getCurrentMill() - lastTime < 1000;
    }

    /**
     * 当前小时是否在两个小时段之间，
     * 在之前，返回0
     * 在之间，返回距离结束时间还有多久
     * 在之后，返回1
     */
    public static long isBetweenHour(String beginTime, String endTime) {
        long currentMill = getNeedMill(getCurrentDate());
        long beginMill = getNeedMill(beginTime);
        long endMill = getNeedMill(endTime);
        if (currentMill < beginMill) {
            return 0;
        } else if (currentMill > endMill) {
            return 1;
        } else {
            return endMill - currentMill;
        }
    }

    /**
     * 拿到当前时间的时间戳
     */
    public static String getCurrentDate() {
        Date date = new Date();
        String str = simpleDateFormat.format(date);
        return str;
    }

    /**
     * 拿到时间戳的时分秒的毫秒值，过滤掉了年月日
     *
     * @param dateString 时间戳
     */
    public static long getNeedMill(String dateString) {
        String[] split = dateString.split(" ");
        String[] split1 = split[1].split(":");
        int hour = Integer.parseInt((split1[0]));
        int min = Integer.parseInt((split1[1]));
        int s = Integer.parseInt((split1[2]));
        return hour * 60 * 60 * 1000 + min * 60 * 1000 + s * 1000;
    }

    /*通过时间戳拿到商品结束的剩余时间，过滤掉年月日*/
    public static long getRemainingTimeByDate(String dateString) {
        return getNeedMill(dateString) - getNeedMill(getCurrentDate());
    }

    /**
     * 时间戳转毫秒值
     */
    public static long getDataToMill(String dateString) {
        try {
            Date parse = simpleDateFormat.parse(dateString);
            return parse.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

//    /**
//     * 时间戳拿到小时分（天,时:分:秒.毫秒）
//     */
//    public static String getHourMinByDate(String dateString) {
//        long dataToMill = getDataToMill(dateString);
//        long day = dataToMill / (24 * 60 * 60 * 1000);
//        long hour = (dataToMill / (60 * 60 * 1000) - day * 24);
//        long min = ((dataToMill / (60 * 1000)) - day * 24 * 60 - hour * 60);
//        return hour+":"+min;
//    }

    /**
     * 时间戳拿到小时分（天,时:分:秒.毫秒）
     */
    public static String getHourMinByDate(String dateString) {
        return dateString.substring(11, 16);
    }

    /*得到两个时间戳之间的天数*/
    public static int getBetweenDay(String startDate, String endDate) {
        long startMill = getDataToMill(startDate);
        long endMill = getDataToMill(endDate);
        long day = (endMill - startMill) / (1000 * 60 * 60 * 24);
        return (int) day;
    }


    /**
     * 获取指定格式日期的毫秒
     */
    public static long getFormatTimeLong(DateFormat format, String dateString) {
        if (format == null) {
            return -1;
        }
        try {
            Date parse = format.parse(dateString);
            return parse.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }


    /**
     * 毫秒值转换为时间（天,时:分:秒.毫秒）
     */
    public static String formatDateTime(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
        return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
    }


    public static String getCurrentTime(String format) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        String currentTime = sdf.format(date);
        return currentTime;
    }

    public static String getCurrentTime() {
        return getCurrentTime("yyyy-MM-dd  HH:mm:ss");
    }


    /**
     * 两个时间串比较
     *
     * @return
     */
    public static int compareTo(String dataStr1, String dataStr2) {
        try {
            Date date1 = simpleDateFormat.parse(dataStr1);
            Date date2 = simpleDateFormat.parse(dataStr2);
            return date1.compareTo(date2);
        } catch (ParseException e) {
        }
        //比较不出来,返回0
        return 0;
    }

    /**
     * 获取当前当天日期的毫秒数 2012-03-21的毫秒数
     *
     * @return
     */
    public static long getCurrentDayTime() {
        Date d = new Date(System.currentTimeMillis());
        String formatDate = yearFormat.format(d);
        try {
            return (yearFormat.parse(formatDate)).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String formatDate(int year, int month, int day) {
        Date d = new Date(year - 1900, month, day);
        return yearFormat.format(d);
    }

    public static long getDateMills(int year, int month, int day) {
        //Date d = new Date(year, month, day);
        // 1960 4 22
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.set(year, month, day);
        TimeZone tz = TimeZone.getDefault();
        calendar.setTimeZone(tz);
        return calendar.getTimeInMillis();
    }

    public static String getDateString(long time, int type) {
        Calendar c = Calendar.getInstance();
        c = Calendar.getInstance(tz);
        c.setTimeInMillis(time);
        long currentTime = System.currentTimeMillis();
        Calendar current_c = Calendar.getInstance();
        current_c = Calendar.getInstance(tz);
        current_c.setTimeInMillis(currentTime);

        int currentYear = current_c.get(Calendar.YEAR);
        int y = c.get(Calendar.YEAR);
        int m = c.get(Calendar.MONTH) + 1;
        int d = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        long t = currentTime - time;
        long t2 = currentTime - getCurrentDayTime();
        String dateStr = "";
        if (t < t2 && t > 0) {
            if (type == SHOW_TYPE_SIMPLE) {
                dateStr = (hour < 10 ? "0" + hour : hour) + ":"
                        + (minute < 10 ? "0" + minute : minute);
            } else if (type == SHOW_TYPE_COMPLEX) {
                dateStr = "今天  " + (hour < 10 ? "0" + hour : hour) + ":"
                        + (minute < 10 ? "0" + minute : minute);
            } else if (type == SHOW_TYPE_CALL_LOG) {
                dateStr = "今天  " + (hour < 10 ? "0" + hour : hour) + ":"
                        + (minute < 10 ? "0" + minute : minute);
            } else if (type == SHOW_TYPE_CALL_DETAIL) {
                dateStr = "今天  ";
            } else {
                dateStr = (hour < 10 ? "0" + hour : hour) + ":"
                        + (minute < 10 ? "0" + minute : minute) + ":"
                        + (second < 10 ? "0" + second : second);
            }
        } else if (t < (t2 + ONEDAY) && t > 0) {
            if (type == SHOW_TYPE_SIMPLE || type == SHOW_TYPE_CALL_DETAIL) {
                dateStr = "昨天  ";
            } else if (type == SHOW_TYPE_COMPLEX) {
                dateStr = "昨天  " + (hour < 10 ? "0" + hour : hour) + ":"
                        + (minute < 10 ? "0" + minute : minute);
            } else if (type == SHOW_TYPE_CALL_LOG) {
                dateStr = "昨天  " + (hour < 10 ? "0" + hour : hour) + ":"
                        + (minute < 10 ? "0" + minute : minute);
            } else {
                dateStr = "昨天  " + (hour < 10 ? "0" + hour : hour) + ":"
                        + (minute < 10 ? "0" + minute : minute) + ":"
                        + (second < 10 ? "0" + second : second);
            }
        } else if (y == currentYear) {
            if (type == SHOW_TYPE_SIMPLE) {
                dateStr = (m < 10 ? "0" + m : m) + "-" + (d < 10 ? "0" + d : d);
            } else if (type == SHOW_TYPE_COMPLEX) {
                dateStr = (m < 10 ? "0" + m : m) + "月" + (d < 10 ? "0" + d : d)
                        + "日";
            } else if (type == SHOW_TYPE_CALL_LOG || type == SHOW_TYPE_COMPLEX) {
                dateStr = (m < 10 ? "0" + m : m) + /* 月 */"-"
                        + (d < 10 ? "0" + d : d) + /* 日 */" "
                        + (hour < 10 ? "0" + hour : hour) + ":"
                        + (minute < 10 ? "0" + minute : minute);
            } else if (type == SHOW_TYPE_CALL_DETAIL) {
                dateStr = y + "-" + (m < 10 ? "0" + m : m) + "-"
                        + (d < 10 ? "0" + d : d);
            } else {
                dateStr = (m < 10 ? "0" + m : m) + "月" + (d < 10 ? "0" + d : d)
                        + "日 " + (hour < 10 ? "0" + hour : hour) + ":"
                        + (minute < 10 ? "0" + minute : minute) + ":"
                        + (second < 10 ? "0" + second : second);
            }
        } else {
            if (type == SHOW_TYPE_SIMPLE) {
                dateStr = y + "-" + (m < 10 ? "0" + m : m) + "-"
                        + (d < 10 ? "0" + d : d);
            } else if (type == SHOW_TYPE_COMPLEX) {
                dateStr = y + "年" + (m < 10 ? "0" + m : m) + "月"
                        + (d < 10 ? "0" + d : d) + "日";
            } else if (type == SHOW_TYPE_CALL_LOG || type == SHOW_TYPE_COMPLEX) {
                dateStr = y + /* 年 */"-" + (m < 10 ? "0" + m : m) + /* 月 */"-"
                        + (d < 10 ? "0" + d : d) + /* 日 */"  "/*
                                                                 * + (hour < 10
																 * ? "0" + hour
																 * : hour) + ":"
																 * + (minute <
																 * 10 ? "0" +
																 * minute :
																 * minute)
																 */;
            } else if (type == SHOW_TYPE_CALL_DETAIL) {
                dateStr = y + "-" + (m < 10 ? "0" + m : m) + "-"
                        + (d < 10 ? "0" + d : d);
            } else {
                dateStr = y + "年" + (m < 10 ? "0" + m : m) + "月"
                        + (d < 10 ? "0" + d : d) + "日 "
                        + (hour < 10 ? "0" + hour : hour) + ":"
                        + (minute < 10 ? "0" + minute : minute) + ":"
                        + (second < 10 ? "0" + second : second);
            }
        }
        return dateStr;
    }

    /**
     * @return
     */
    public static long getCurrentTim() {
        return System.currentTimeMillis() / 1000;
    }

    public static long getActiveTimeLong(String result) {
        try {
            Date parse = yearFormat.parse(result);
            return parse.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis();
    }

    public static Date getDate(String result) {
        try {
            Date parse = yearFormat.parse(result);
            return parse;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 传入yyyy年MM月dd日 返回yyyy-MM-dd
     *
     * @param result
     * @return
     */
    public static String getDate2FormatStr(String result) {
        try {
            Date parse = yearFormat2.parse(result);
            return yearFormat.format(parse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 获取yy-MM-dd格式的字符串
     *
     * @param dateString
     * @return
     */
    public static String getShortYearDate(String dateString) {
        try {
            Date d = simpleDateFormat.parse(dateString);
            return shortYearFormat.format(d);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取yyyy-MM-dd格式的字符串
     *
     * @param dateString
     * @return
     */
    public static String getYearDate(String dateString) {
        try {
            Date d = simpleDateFormat.parse(dateString);
            return yearFormat.format(d);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取HH:mm格式的字符串
     *
     * @param dateString
     * @return
     */
    public static String getHoursTime(String dateString) {
        try {
            Date d = simpleDateFormat.parse(dateString);
            return simpleTimeFormat.format(d);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取MM:dd格式的字符串
     *
     * @param dateString
     * @return
     */
    public static String getMonthDay(String dateString) {
        try {
            Date d = simpleDateFormat.parse(dateString);
            return monthDayFormat.format(d);
        } catch (Exception e) {
            return "";
        }
    }
}
