package mill.java.base;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;



import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * 用于处理各种日期的工具类
 *
 * @author xie_pkuan
 * @date 2013-3-13 上午10:12:54
 */
public class DateMillUtil {
    /**
     * 营业时间
     */
    public static Integer START_TIME = 8;
    /**
     * 日期转换类
     */
    public static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat();

    /**
     * 日期格式：yyyy-MM-dd
     */
    public final static String DATE_HORIZONTAL_FORMAT = "yyyy-MM-dd";

    /**
     * 日期格式：yyyy-MM-dd HH:mm:ss
     */
    public final static String DATETIME_HORIZONTAL_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期格式：yyyy-MM-dd HH mm ss
     */
    public final static String DATETIME_HORIZONTAL_FORMAT_2 = "yyyy-MM-dd HH mm ss";

    /**
     * 日期格式：yyyy-MM-dd HH:mm:ss.SSS
     */
    public final static String DATETIME_HORIZONTAL_MS_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * 日期格式：yyyy-MM-dd HH:mm:ssSSS
     */
    public final static String DATETIME_HORIZONTAL_MS_FORMAT_1 = "yyyy-MM-dd HH:mm:ssSSS";

    /**
     * 日期格式：yyyyMMdd
     */
    public final static String DATE_TIGHT_FORMAT = "yyyyMMdd";

    /**
     * 日期格式：yyyyMMddHHmmss
     */
    public final static String DATETIME_TIGHT_FORMAT = "yyyyMMddHHmmss";

    /**
     * 日期格式(中文)：yyyy年MM月dd日
     */
    public final static String DATE_CN_FORMAT = "yyyy年MM月dd日";

    /**
     * 日期格式(中文)：yyyy年MM月dd日 HH时mm分ss秒
     */
    public final static String DATETIME_CN_FORMAT = "yyyy年MM月dd日 HH时mm分ss秒";

    /**
     * 日期格式(中文)：yyyy年MM月dd日 HH:mm:ss
     */
    public final static String DATETIME_CN_FORMAT_2 = "yyyy年MM月dd日 HH:mm:ss";

    /**
     *
     */
    public final static String TYPE_DATE = "date";

    /**
     *
     */
    public final static String TYPE_DATETIME = "datetime";

    /**
     * 日期格式(斜线)：yyyy/MM/dd
     */
    public final static String DATE_OBLIQUE_FORMAT = "yyyy/MM/dd";

    /**
     * 日期格式(斜线)：yyyy/MM/dd HH:mm:ss
     */
    public final static String DATETIME_OBLIQUE_FORMAT = "yyyy/MM/dd HH:mm:ss";

    /**
     * 日期格式(斜线)：yyyy/MM/dd HH:mm:ss.SSS
     */
    public final static String DATETIME_OBLIQUE_MS_FORMAT = "yyyy/MM/dd HH:mm:ss.SSS";

    /**
     * 日期格式(斜线)：yyyy/MM/dd HH:mm:ssSSS
     */
    public final static String DATETIME_OBLIQUE_MS_FORMAT_2 = "yyyy/MM/dd HH:mm:ssSSS";

    /**
     * 时间格式：HH:mm:ss
     */
    public final static String TIME_FORMAT = "HH:mm:ss";

    /**
     * 时间格式：HHmmss
     */
    public final static String TIME_FORMAT_TIGHT = "HHmmss";

    /**
     * 日期格式--年月：yyyy-MM
     */
    public final static String YYYY_MM = "yyyy-MM";

    /**
     * 日期格式--年份：yyyy
     */
    public final static String YYYY = "yyyy";

    /**
     * 日期格式--月份：MM
     */
    public final static String MM = "MM";

    /**
     * 日期格式--日：dd
     */
    public final static String DD = "dd";

    /**
     * 转换Timestamp对象为yyyy-MM-dd HH:mm:ss格式字符串
     *
     * @param timestamp
     * @return
     */
    public static String formatTimestamp(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        SIMPLE_DATE_FORMAT.applyPattern(DATETIME_HORIZONTAL_FORMAT);
        return SIMPLE_DATE_FORMAT.format(timestamp);
    }

    /**
     * 转换Timestamp对象为指定日期格式的式字符串
     *
     * @param timestamp
     * @param format
     * @return
     */
    public static String formatTimestamp(Timestamp timestamp, String format) {
        if (timestamp == null) {
            return "";
        }
        SIMPLE_DATE_FORMAT.applyPattern(format);
        return SIMPLE_DATE_FORMAT.format(timestamp);
    }

    /**
     * 将格式为yyyy-mm-dd hh:mm:ss[.f...].的字符串转换为Timestamp对象
     *
     * @param str
     * @return
     */
    public static Timestamp parseTimestamp(String str) {
        return Timestamp.valueOf(str);
    }

    /**
     * 获取当前时间的时间戳
     *
     * @return
     * @author xie_pkuan
     */
    public static Timestamp getNow() {
        return new Timestamp(new Date().getTime());
    }

    /**
     * 转换日期字符串转换成指定格式的日期对象
     *
     * @param timestampStr
     * @param format
     * @return
     * @throws ParseException
     */
    public static Timestamp parseTimestamp(String timestampStr, String format)
            throws ParseException {
        return new Timestamp(parseDate(timestampStr, format).getTime());
    }

    /**
     * 转换日期字符串转换成指定格式的日期对象
     *
     * @param dateStr
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String dateStr, String format) {
        // Date date = null;
        SIMPLE_DATE_FORMAT.applyPattern(format);
        // String dt = dateStr.replaceAll("-", "/");
        // if ((!dt.equals("")) && (dt.length() < format.length())) {
        // dt += format.substring(dt.length()).replaceAll("[YyMmDdHhSs]",
        // "0");
        // }
        // date = SIMPLE_DATE_FORMAT.parse(dt);
        try {
            return SIMPLE_DATE_FORMAT.parse(dateStr);
        } catch (ParseException e) {
        }
        return null;
    }

    /**
     * 将字符串转换成日期格式
     *
     * @param dateStr
     *            日期字符串
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String dateStr) {
        String fmt = getDateTimeFormat(dateStr);
        if (fmt == null) {
            fmt = getDateFormat(dateStr);
            if (fmt == null) {
                return null;
            }
        }
        return parseDate(dateStr, fmt);
    }

    /**
     * 按照指定格式将日期转换成字符串
     *
     * @param date
     *            日期
     * @param format
     *            格式
     * @return
     */
    public static String format(Date date, String format) {
        String result = "";
        try {
            if (date != null) {
                SIMPLE_DATE_FORMAT.applyPattern(format);
                result = SIMPLE_DATE_FORMAT.format(date);
            }
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * 获取日期的年份
     *
     * @param date
     * @return
     * @author xie_pkuan
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取日期的月份
     *
     * @param date
     * @return
     * @author xie_pkuan
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取日期的日子
     *
     * @param date
     * @return
     * @author xie_pkuan
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取一天的最初时间
     *
     * @param date
     * @return
     * @author xie_pkuan
     * @version V2.0
     */
    public static Date getDayBegin(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取一天的最后时间
     *
     * @param date
     * @return
     * @author xie_pkuan
     * @version V2.0
     */
    public static Date getDayEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 获取日期的小时数
     *
     * @param date
     * @return
     * @author xie_pkuan
     */
    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取日期的分钟数
     *
     * @param date
     * @return
     * @author xie_pkuan
     */
    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 获取日期的秒数
     *
     * @param date
     * @return
     * @author xie_pkuan
     */
    public static int getSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 获取日期的时间戳
     *
     * @param date
     *            日期
     * @return
     * @author xie_pkuan
     */
    public static long getMillis(Date date) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar.getTimeInMillis();
        }
        return 0;
    }

    /**
     * 获取HH:mm:ss格式的时间字符串
     *
     * @param date
     *            日期
     * @return
     * @author xie_pkuan
     */
    public static String getTime(Date date) {
        return format(date, TIME_FORMAT);
    }

    /**
     * 获取yyyy/MM/dd HH:mm:ss的日期时间字符串
     *
     * @param date
     *            日期
     * @return
     * @author xie_pkuan
     */
    public static String getDateTime(Date date) {
        return format(date, DATETIME_OBLIQUE_FORMAT);
    }

    /**
     * 获取两个日期之间的相差天数
     *
     * @param date1
     * @param date2
     * @return
     * @author xie_pkuan
     */
    public static int differDay(Date date1, Date date2) {
        return differSecond(date1, date2) / (24 * 3600);
    }

    /**
     * 获取两个日期之间的相差秒数
     *
     * @param date1
     * @param date2
     * @return
     * @author xie_pkuan
     * @version V2.0
     */
    public static int differSecond(Date date1, Date date2) {
        return (int) (Math.abs((getMillis(date1) - getMillis(date2))) / 1000);
    }

    /**
     *
     * @param date1
     * @param date2
     * @return 计算两个日期之间相差的毫秒数。
     */
    public static long differSeconds(Date date1, Date date2) {
        return getMillis(date1) - getMillis(date2);
    }

    /**
     * 获取两个日期之间的相差天数
     *
     * @param date1
     * @param date2
     * @return
     * @throws ParseException
     * @author xie_pkuan
     * @version V2.0
     */
    public static int differDay(String date1, String date2)
            throws ParseException {
        return differDay(parseDate(date1), parseDate(date2));
    }

    /**
     * 获取两个日期之间的相差秒数
     *
     * @param date1
     * @param date2
     * @return
     * @throws ParseException
     * @author xie_pkuan
     * @version V2.0
     */
    public static int differSecond(String date1, String date2)
            throws ParseException {
        return differSecond(parseDate(date1), parseDate(date2));
    }

    /**
     * 获取两个时间差的中文描述
     *
     * @param time1
     * @param time2
     * @return
     * @throws ParseException
     * @author xie_pkuan
     * @version V2.0
     */
    public static String getDiffDateCN_ZH(String time1, String time2)
            throws ParseException {
        Date date1 = parseDate(time1);
        Date date2 = parseDate(time2);
        long lngTime1 = date1.getTime();
        long lngTime2 = date2.getTime();
        StringBuffer diff = new StringBuffer();
        long diffMS = Math.abs(lngTime2 - lngTime1);
        long second = 1000;// 一秒钟的毫秒数
        long minute = second * 60;// 一分钟的毫秒数
        long hour = minute * 60;// 一小时的毫秒数
        long day = hour * 24;// 一天的毫秒数
        long days = diffMS / day;// 计算差多少天
        long hours = diffMS % day / hour;// 计算差多少小时
        long minutes = diffMS % day % hour / minute;// 计算差多少分钟
        // long seconds = diffMS % day % hour % minute / second;//计算差多少秒
        if (days > 0) {
            diff.append(days).append("天");
        }
        if (hours > 0) {
            diff.append(hours).append("小时");
        }
        if (minutes > 0) {
            diff.append(minutes).append("分钟");
        }
        if (diffMS < minute) {
            diff.append("不足1分钟");
        }
        return diff.toString();
    }

    /**
     * 获取月份的第一天
     *
     * @param dateStr
     * @return 返回yyyy-mm-dd格式的字符串
     * @throws ParseException
     */
    public static String getMonthBegin(String dateStr) throws ParseException {
        Date date = parseDate(dateStr);
        return format(date, YYYY_MM) + "-01";
    }

    /**
     * 获取月份的最后一天
     *
     * @param dateStr
     * @return 返回yyyy-mm-dd格式的字符串
     * @throws ParseException
     */
    public static String getMonthEnd(String dateStr) throws ParseException {
        Date date = parseDate(getMonthBegin(dateStr));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return format(calendar.getTime());
    }

    /**
     * 以yyyy-MM-dd格式化日期
     *
     * @param date
     * @return
     * @author xie_pkuan
     */
    public static String format(Date date) {
        return format(date, DATE_HORIZONTAL_FORMAT);
    }

    /**
     * 以yyyy-MM-dd HH:mm:ss格式化日期
     *
     * @param date
     * @return
     * @author xie_pkuan
     */
    public static String formatDateTime(Date date) {
        return format(date, DATETIME_HORIZONTAL_FORMAT);
    }

    /**
     * 日期排序类型-升序
     */
    public final static int DATE_ORDER_ASC = 0;

    /**
     * 日期排序类型-降序
     */
    public final static int DATE_ORDER_DESC = 1;

    /**
     * 用yyyy/MM/dd HH:mm:ss格式字符串转换为日期对象
     *
     * @param dateValue
     *            日期字符串
     * @param dateType
     *            格式化的类型,date或者datetime
     * @return
     * @throws ParseException
     */
    public static Date parseDateByType(String dateValue, String dateType)
            throws ParseException {
        if (dateValue == null)
            return null;
        if (dateType.equals(TYPE_DATE)) {
            SIMPLE_DATE_FORMAT.applyPattern(DATE_HORIZONTAL_FORMAT);
            return SIMPLE_DATE_FORMAT.parse(dateValue);
        } else if (dateType.equals(TYPE_DATETIME)) {
            SIMPLE_DATE_FORMAT.applyPattern(DATETIME_HORIZONTAL_FORMAT);
            return SIMPLE_DATE_FORMAT.parse(dateValue);
        }
        return null;
    }

    /**
     * 用字符串获得java.sql.Date日期
     *
     * @param dateValue
     *            日期字符串
     * @param dateType
     *            格式化的类型,date和datetime
     * @throws ParseException
     */
    public static java.sql.Date parseSqlDate(String dateValue, String dateType)
            throws ParseException {
        Date date = parseDateByType(dateValue, dateType);
        if (date == null) {
            return null;
        }
        return new java.sql.Date(date.getTime());
    }

    /**
     * 将日期加上或减去天数
     *
     * @param dateStr
     *            待处理日期（yyyy-mm-dd）
     * @param days
     *            加减的天数
     * @return 日期
     */
    public static Date dayAdd(String dateStr, int days) {
        java.util.Date date = null;
        try {
            date = java.sql.Date.valueOf(dateStr);
        } catch (Exception e) {
            date = new java.util.Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days); // 日期减 如果不够减会将月变动
        return calendar.getTime();
    }

    /**
     * 日期1是否晚于日期2
     *
     * @param date1
     *            日期1
     * @param date2
     *            日期2
     * @return
     * @author xie_pkuan
     * @version V2.0
     */
    public static boolean after(Date date1, Date date2) {
        boolean flag = false;
        if (date1 != null && date2 != null) {
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(date1);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(date2);
            flag = calendar1.after(calendar2);
        }
        return flag;
    }

    /**
     * 日期1是否早于日期2
     *
     * @param date1
     *            日期1
     * @param date2
     *            日期2
     * @return
     * @author xie_pkuan
     * @version V2.0
     */
    public static boolean before(Date date1, Date date2) {
        boolean flag = false;
        if (date1 != null && date2 != null) {
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(date1);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(date2);
            flag = calendar1.before(calendar2);
        }
        return flag;
    }

    /**
     * 增加天数
     *
     * @param date
     * @param days
     * @return
     * @author xie_pkuan
     * @version V2.0
     */
    public static Date dayAdd(Date date, int days) {
        Calendar strDate = Calendar.getInstance();
        strDate.setTime(date);
        strDate.add(Calendar.DATE, days); // 日期减 如果不够减会将月变动
        return new Date(strDate.getTime().getTime());
    }

    /**
     * 为日期增加或减少指定域的偏移
     *
     * @param date
     *            日期
     * @param offset
     *            偏移
     * @param field
     *            年月日 时分秒 Calendar.field
     * @return
     * @author xie_pkuan
     * @version V2.0
     */
    public static Date dateAdd(Date date, int offset, int field) {
        Calendar strDate = Calendar.getInstance();
        strDate.setTime(date);
        strDate.add(field, offset); // 日期减 如果不够减会将月变动
        return new Date(strDate.getTime().getTime());
    }

    /**
     * 使用指定分隔符格式化日期
     *
     * @param date
     *            日期对象
     * @param split
     *            分隔字符
     * @return 返回指定格式日期字符串，比如split为*，将返回yyyy*mm*dd格式字符串
     */
    public static String formatDateBySplit(Date date, String split) {
        SIMPLE_DATE_FORMAT.applyPattern(YYYY + split + MM + split + DD);
        return SIMPLE_DATE_FORMAT.format(date);
    }

    /**
     * 格式化日期
     *
     * @param dateValue
     *            日期对象，可以是java.util.Date和java.sql.Date
     * @param dateType
     *            格式化的类型, date 和 datetime
     * @return
     */
    public static String format(Object dateValue, String dateType) {
        if (dateValue == null)
            return "";
        if (dateValue instanceof java.sql.Date) {
            return dateValue.toString();
        } else if (dateValue instanceof java.util.Date) {
            if (dateType.equals(TYPE_DATE)) {
                SIMPLE_DATE_FORMAT.applyPattern(DATE_HORIZONTAL_FORMAT);
                return SIMPLE_DATE_FORMAT.format(dateValue);
            } else if (dateType.equals(TYPE_DATETIME)) {
                SIMPLE_DATE_FORMAT.applyPattern(DATETIME_HORIZONTAL_FORMAT);
                return SIMPLE_DATE_FORMAT.format(dateValue);
            } else {
                return "非法日期格式[" + dateType + "]";
            }
        } else {
            return "非日期类型";
        }
    }

    /**
     * 转换日期对象为中文化日期
     *
     * @param dateValue
     *            日期对象，可以是java.util.Date和java.sql.Date
     * @param dateType
     *            格式化的类型,date和datetime
     */
    public static String formatDate2Chinese(Date dateValue, String dateType) {
        if (dateValue == null)
            return "";
        if (dateValue instanceof java.sql.Date) {
            return dateValue.toString();
        } else if (dateValue instanceof java.util.Date) {
            if (dateType.equals(TYPE_DATE)) {
                SIMPLE_DATE_FORMAT.applyPattern(DATE_CN_FORMAT);
                return SIMPLE_DATE_FORMAT.format(dateValue);
            } else if (dateType.equals(TYPE_DATETIME)) {
                SIMPLE_DATE_FORMAT.applyPattern(DATETIME_CN_FORMAT);
                return SIMPLE_DATE_FORMAT.format(dateValue);
            } else {
                return "非法日期格式[" + dateType + "]";
            }
        } else {
            return "非日期类型";
        }
    }

    /**
     * 转化成年月日期
     *
     * @param dateStr
     *            字符型日期：2009-02-02
     * @param split
     *            分割符号比如 / -
     * @return 年月日期 :2009年02月02日
     */
    public static String formatDate2Chinese(String dateStr, String split) {
        String tmpArr[] = dateStr.split(split);
        tmpArr[0] = tmpArr[0] + "年";
        tmpArr[1] = tmpArr[1] + "月";
        tmpArr[2] = tmpArr[2] + "日";
        return tmpArr[0] + tmpArr[1] + tmpArr[2];
    }

    /**
     * 得到系统日期字符串
     *
     * @return YYYY-MM-DD
     */
    public static String getSysDate() {
        Timestamp timeNow = new Timestamp(System.currentTimeMillis());
        return timeNow.toString().substring(0, 10);
    }

    /**
     *
     * @param forMat
     * @param date
     * @return 将指定日期转为指定格式
     */
    public static String getSimpleDateFormat(String forMat, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(forMat);
        return sdf.format(date);
    }

    /**
     * 得到某天是周几
     *
     * @param strDay
     * @return 周几
     */
    public static int getWeekDay(String strDay) {
        Date day = DateMillUtil.dayAdd(strDay, -1);
        Calendar strDate = Calendar.getInstance();
        strDate.setTime(day);
        int meStrDate = strDate.get(Calendar.DAY_OF_WEEK);
        return meStrDate;
    }

    /**
     * 得到某天是周几
     *
     * @return 周几
     */
    public static int getWeekDay(Date date) {
        Date day = DateMillUtil.dayAdd(format(date, "date"), -1);
        Calendar strDate = Calendar.getInstance();
        strDate.setTime(day);
        int meStrDate = strDate.get(Calendar.DAY_OF_WEEK);
        return meStrDate;
    }

    /**
     * 获取两个时间字符串相差的小时数
     *
     * @param time1
     * @param time2
     * @return
     * @throws ParseException
     * @author xie_pkuan
     * @version V2.0
     */
    public static int getBetweenHours(String time1, String time2)
            throws ParseException {
        int hours = 0;
        Date date1 = parseDate(time1);
        Date date2 = parseDate(time2);
        hours = getBetweenHours(date1, date2);
        return hours;
    }

    /**
     * 获取两个日期之间相差的小时数
     *
     * @param date1
     *            日期1
     * @param date2
     *            日期2
     * @return
     * @author xie_pkuan
     * @version V2.0
     */
    private static int getBetweenHours(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return -1;
        }
        int hours = 0;
        long dt1 = date1.getTime();
        long dt2 = date2.getTime();
        long diff = Math.abs(dt1 - dt2);
        hours = (int) (diff / (60 * 60 * 1000));
        return hours;
    }

    /**
     * 判断指定日期是否在一个日期范围内
     *
     * @param fromDate
     *            范围开始日期
     * @param toDate
     *            范围结束日期
     * @param testDate
     *            测试日期
     * @return 在范围内true,否则false
     */
    public static boolean isBetween(java.sql.Date fromDate,
                                    java.sql.Date toDate, java.sql.Date testDate) {
        if (fromDate == null || toDate == null || testDate == null) {
            return false;
        }

        // 1、 交换开始和结束日期
        if (fromDate.getTime() > toDate.getTime()) {
            java.sql.Date tempDate = fromDate;
            fromDate = toDate;
            toDate = tempDate;
        }

        // 2、缩小范围
        long testDateTime = testDate.getTime();
        if ((testDateTime > fromDate.getTime() && testDateTime > toDate
                .getTime())
                || testDateTime < fromDate.getTime()
                && testDateTime < toDate.getTime()) {
            return false;
        }

        return true;
    }

    /**
     * 得到指定年、月的最后一天
     *
     * @param year
     *            年
     * @param month
     *            月
     * @return 本年月的最后一天，如果2009,10，返回结果：2009-10-31
     */
    public static String getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        // 某年某月的最后一天
        int lastDate = cal.getActualMaximum(Calendar.DATE);
        return year + "-" + (month + 1) + "-" + lastDate;
    }

    /**
     * 判断两个日期是否为同一天
     *
     * @param date1
     *            日期一
     * @param date2
     *            日期二
     * @return 同一天true，不是同一天false
     */
    public static boolean isSameDay(Date date1, Date date2) {
        boolean result = false;
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(date2);

        if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
                && c1.get(Calendar.DAY_OF_MONTH) == c2
                .get(Calendar.DAY_OF_MONTH)) {
            result = true;
        }
        return result;
    }

    /**
     * 判断字符串是不是日期格式()
     *
     * @param dateStr
     * @return
     * @author xie_pkuan
     */
    public static boolean isDate(String dateStr) {
        return (getDateFormat(dateStr) != null);
    }

    /**
     * 获取日期字符串的日期格式
     *
     * @param dateStr
     * @return
     * @author xie_pkuan
     * @version V2.0
     */
    public static String getDateFormat(String dateStr) {
        String dateFmt = null;
        List<String> list = Arrays.asList(DATE_CN_FORMAT,
                DATE_HORIZONTAL_FORMAT, DATE_OBLIQUE_FORMAT, DATE_TIGHT_FORMAT);
        for (String pattern : list) {
            Date date = parseDate(dateStr, pattern);
            if (date != null) {
                dateFmt = pattern;
                break;
            }
        }
        return dateFmt;
    }

    /**
     * 获取日期字符串的时间格式
     *
     * @param dateStr
     * @return
     * @author xie_pkuan
     * @version V2.0
     */
    public static String getDateTimeFormat(String dateStr) {
        String datetimeFmt = null;
        List<String> list = Arrays.asList(DATETIME_CN_FORMAT,
                DATETIME_CN_FORMAT_2, DATETIME_HORIZONTAL_FORMAT,
                DATETIME_HORIZONTAL_FORMAT_2, DATETIME_HORIZONTAL_MS_FORMAT_1,
                DATETIME_HORIZONTAL_MS_FORMAT, DATETIME_OBLIQUE_FORMAT,
                DATETIME_OBLIQUE_MS_FORMAT_2, DATETIME_OBLIQUE_MS_FORMAT,
                DATETIME_TIGHT_FORMAT);
        for (String pattern : list) {
            Date date = parseDate(dateStr, pattern);
            if (date != null) {
                datetimeFmt = pattern;
                break;
            }
        }
        return datetimeFmt;
    }

    /**
     * 判断是否时间格式
     *
     * @param dateStr
     * @return
     * @author xie_pkuan
     */
    public static boolean isDateTime(String dateStr) {
        return (getDateTimeFormat(dateStr) != null);
    }

    /**
     * 获取当前系统时间，24小时制
     *
     * @return 当前系统时间
     */
    public static Time getSystemTime() {
        Calendar c1 = Calendar.getInstance();
        int hour = c1.get(Calendar.HOUR_OF_DAY);
        int minute = c1.get(Calendar.MINUTE);
        int second = c1.get(Calendar.SECOND);
        Time systemTime = Time.valueOf(hour + ":" + minute + ":" + second);
        return systemTime;
    }

    /**
     * 是否为周末
     *
     * @param strDate
     * @return true|false
     */
    public static boolean isWeekend(String strDate) {
        int weekDay = getWeekDay(strDate);
        if (weekDay == 6 || weekDay == 7) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 是否为周末
     *
     * @return true|false
     */
    public static boolean isWeekend(Date date) {
        int weekDay = getWeekDay(format(date, "date"));
        if (weekDay == 6 || weekDay == 7) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 日期排序
     *
     * @param dates
     *            日期列表
     * @param orderType
     *            排序类型 <br/>
     *            {@link #DATE_ORDER_ASC}<br/>
     *            {@link #DATE_ORDER_DESC}
     * @return 排序结果
     */
    public static List<? extends java.util.Date> sortDate(
            List<? extends java.util.Date> dates, int orderType) {
        DateComparator comp = new DateComparator(orderType);
        Collections.sort(dates, comp);
        return dates;
    }

    /**
     * 日期分组<br/>
     * 能够对指定日期列表按照连续性分组<br/>
     * 例如：[2010-01-15, 2010-01-16, 2010-01-17, 2010-01-20, 2010-01-21,
     * 2010-01-25]<br/>
     * 分组结果为：<br/>
     * <ul>
     * <li>[2010-01-15, 2010-01-16, 2010-01-17]</li>
     * <li>[2010-01-20, 2010-01-21]</li>
     * <li>[2010-01-25]</li>
     * </ul>
     *
     * @param dates
     *            日期对象
     * @return 连续性分组结果
     */
    public static List<List<? extends Date>> groupDates(
            List<? extends Date> dates) {
        List<List<? extends Date>> result = new ArrayList<List<? extends Date>>();
        // 按照升序排序
        sortDate(dates, DateMillUtil.DATE_ORDER_ASC);
        // 临时结果
        List<Date> tempDates = null;
        // 上一组最后一个日期
        Date lastDate = null;
        // 当前读取日期
        Date cdate = null;
        for (int i = 0; i < dates.size(); i++) {
            cdate = dates.get(i);
            // 第一次增加
            if (tempDates == null) {
                tempDates = new ArrayList<Date>();
                tempDates.add(cdate);
                result.add(tempDates);
            } else {
                /**
                 * 差距为1是继续在原有的列表中添加，大于1就是用新的列表
                 */
                lastDate = tempDates.get(tempDates.size() - 1);
                int days = differDay(lastDate, cdate);
                if (days == 1) {
                    tempDates.add(cdate);
                } else {
                    tempDates = new ArrayList<Date>();
                    tempDates.add(cdate);
                    result.add(tempDates);
                }
            }
        }
        return result;
    }

    /**
     * 将出生日期与当前日期相减，获得年龄
     *
     * @param birthdayDate
     * @return
     */
    public static int getAge(Date birthdayDate) {
        String formatCurrent = new SimpleDateFormat(DATE_HORIZONTAL_FORMAT)
                .format(new Date());
        int firstCu = formatCurrent.indexOf("-");
        int lastCu = formatCurrent.lastIndexOf("-");
        String currentYearStr = formatCurrent.substring(0, firstCu);
        String currentMonthStr = formatCurrent.substring(firstCu + 1, lastCu);
        String currentDayStr = formatCurrent.substring(lastCu + 1);
        int currentYear = Integer.valueOf(currentYearStr);
        int currentMonth = Integer.valueOf(currentMonthStr);
        int currentDay = Integer.valueOf(currentDayStr);

        String formatBirthday = new SimpleDateFormat(DATE_HORIZONTAL_FORMAT)
                .format(birthdayDate);

        int first = formatBirthday.indexOf("-");
        int last = formatBirthday.lastIndexOf("-");
        String birthYearStr = formatBirthday.substring(0, first);
        String birthMonthStr = formatBirthday.substring(first + 1, last);
        String birthDayStr = formatBirthday.substring(last + 1);

        int birthYear = Integer.valueOf(birthYearStr);
        int birthMonth = Integer.valueOf(birthMonthStr);
        int birthDay = Integer.valueOf(birthDayStr);

        if (currentMonth > birthMonth) {
            return currentYear - birthYear;
        } else if (currentMonth == birthMonth) {
            if (currentDay >= birthDay) {
                return currentYear - birthYear;
            } else {
                return currentYear - birthYear - 1;
            }
        } else {
            return currentYear - birthYear - 1;
        }
    }

    /**
     * 获取年月，例如 201009
     *
     * @param dateObj
     * @return
     */
    public static String getYearMonth(Date dateObj) {
        if (dateObj == null) {
            return "";
        }
        Calendar ca = Calendar.getInstance();
        ca.setTime(dateObj);
        int month = ca.get(Calendar.MONTH) + 1;
        String strMonth = month < 10 ? ("0" + month) : String.valueOf(month);
        String yearMonth = ca.get(Calendar.YEAR) + strMonth;
        return yearMonth;
    }

    /**
     * 根据指定年月计算上月年月标示
     *
     * @param yearMonth
     * @return 201010返回201009，201001返回200912
     */
    public static String getPreYearMonth(String yearMonth) {
        if (yearMonth.length() == 6) {
            int year = Integer.valueOf(yearMonth.substring(0, 4));
            int month = Integer.valueOf(yearMonth.substring(4));
            if (month != 1) {
                month -= 1;
            } else {
                year -= 1;
                month = 12;
            }
            return year + (month < 10 ? "0" + month : String.valueOf(month));
        }
        return "";
    }

    /**
     * 获取一天的8点
     *
     * @param date
     * @return
     * @author xie_pkuan
     * @version V2.0
     */
    public static Date getTodayOpenHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, START_TIME);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取后一天的8点
     *
     * @param date
     * @return
     * @author xie_pkuan
     * @version V2.0
     */
    public static Date getAfterDayOpenHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE) + 1);
        calendar.set(Calendar.HOUR_OF_DAY, START_TIME);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取前一天的8点
     *
     * @param date
     * @return
     * @author xie_pkuan
     * @version V2.0
     */
    public static Date getBeforeDayOpenHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE) - 1);
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        return calendar.getTime();
    }

    public static Date strParsetoDate(String format, String data) {
        String format1;
        if (StringMillUtil.notNOE(format)) {
            format1 = format;
        } else {
            format1 = "yyyy-MM-dd";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format1);
        Date date = null;
        try {
            date = simpleDateFormat.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Object getToday() {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND,0);
        return calendar.getTime();
    }

    public static Date delHour(Date time) {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(time);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND,0);
        return calendar.getTime();
    }
}

/**
 * <p>
 * <b>Title：</b>日期大小比较
 * </p>
 * <p>
 * <b>Description：</b>实现比较接口，按照排序类型[升序,降序]排列日期集合
 * </p>
 *
 * @author 解培宽
 */
class DateComparator implements Comparator<Date> {

    int orderType;

    public DateComparator(int orderType) {
        this.orderType = orderType;
    }

    /**
     *
     */
    public int compare(Date d1, Date d2) {
        if (d1.getTime() > d2.getTime()) {
            if (orderType == DateMillUtil.DATE_ORDER_ASC) {
                return 1;
            } else {
                return -1;
            }
        } else {
            if (d1.getTime() == d2.getTime()) {
                return 0;
            } else {
                if (orderType == DateMillUtil.DATE_ORDER_DESC) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }
    }

}