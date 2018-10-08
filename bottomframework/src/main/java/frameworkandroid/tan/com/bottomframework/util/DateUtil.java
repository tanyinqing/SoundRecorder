package frameworkandroid.tan.com.bottomframework.util;

import android.annotation.SuppressLint;
import android.text.format.Time;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import frameworkandroid.tan.com.bottomframework.util.utils_log.LogTxt;

@SuppressLint("SimpleDateFormat")
@SuppressWarnings("static-access")
public class DateUtil {
    public static final String DATE_ALL_ALL = "yyyy-MM-dd HH:mm:ss";
    public static final String YEAR_MONTH_DAY = "yyyy-MM-dd";
    public static final String YEAR_MONTH = "yyyy-MM";
    public static final String DATE_ALL = "yyyy-MM-dd HH:mm";
    public static final String DATE_TIME = "MM-dd HH:mm";
    public static final String DATE_HOUR_MINUTE = "HH:mm";
    public static final String DATE_HOUR_MINUTE_SEC = "HH:mm:ss";
    public static final long DAY_MILLIS = 86400000L;  //一天有多少秒
    public static final long WEEK_MILLIS = 604800000L;   //一周有多少秒
    //只有最后一位表明L 表明数据类型
    public static final long MONTH_MILLIS = 2592000000L;  //一月有多少秒

    /**
     * 某月的天数
     */
    private static int daysOfMonth = 0;

    /**
     * 判断是否为闰年
     *
     * @param year
     *            指定的年份
     * @return
     */
    public static boolean isLeapYear(int year) {
        if (year % 100 == 0 && year % 400 == 0) {
            return true;
        } else if (year % 100 != 0 && year % 4 == 0) {
            return true;
        }
        return false;
    }

    /**
     * 获取当前日期   用指定的格式来显示
     *
     * @return
     */

    public static String getNowDate(String dateFormat) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat(dateFormat);

        return sDateFormat.format(new Date());
    }

    /**
     * 得到某月有多少天数
     *
     * @param year
     *            目标年份
     * @param month
     *            目标月份
     * @return
     */
    public static int getDaysOfMonth(int year, int month) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                daysOfMonth = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                daysOfMonth = 30;
                break;
            case 2:
                if (isLeapYear(year)) {  //判断是否为闰年
                    daysOfMonth = 29;
                } else {
                    daysOfMonth = 28;
                }

        }
        return daysOfMonth;
    }

    private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    //将指定的时间 以指定的格式进行格式化成字符串
    public static String stringFromDate(Date date, String formatString) {
        DateFormat df = new SimpleDateFormat(formatString);
        return df.format(date);
    }

    //将指定的字符串格式的日期转换成时间对象
    public static Date dateFromString(String dateStr, String formatString) {
        DateFormat df = new SimpleDateFormat(formatString);
        Date date = null;

        try {
            date = df.parse(dateStr);
        } catch (ParseException e) {
            //Log.i("dateFromString exception:", Log.getStackTraceString(e));
            LogTxt.writeLog(Log.getStackTraceString(e), "dateFromString exception:");
        }
        return date;
    }

    //指定的时间 推迟多少天后的日期
    public static Date addDateOfDay(Date date, int addDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, addDay);
        Date d = calendar.getTime();

        return d;
    }

    //指定的时间 推迟多少月后的日期 例如现在是2017-05-30 11:00:34
    // 推迟4天后是 2017-06-03 11:02:08  不过得出的是手机上的时间
    public static Date addDateOfMonth(Date date, int addMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, addMonth);
        Date d = calendar.getTime();

        return d;
    }

    //返回现在的时间的时间码   格式为Long型  例如：1496121576992
    /*System.currentTimeMillis()产生一个当前的毫秒，这个毫秒其实就是自1970年1月1日0时起的毫秒数，*/
    public static long getNowTimeInMillis() {
        Calendar nowCalendar = Calendar.getInstance();
        nowCalendar.getTimeInMillis();

        return System.currentTimeMillis();
    }

    //返回当前天是当前月份的第多少天
    public static int getTadayOfMonth() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    //将Long型时间转化为小时 分钟  秒的形式 例如：415589:19:36
    @SuppressLint("DefaultLocale")
    public static String getStringUseTime(long useTime) {
        int sec = 0;
        int minute = 0;
        int hour = 0;
        String timeText = "";
        //这个是转化成秒值
        sec = (int) (useTime / 1000);
        minute = sec / 60;
        hour = minute / 60;
        timeText = String.format("%02d:%02d:%02d", hour, minute % 60, sec % 60);
        return timeText;
    }

    /**
     * 以友好的方式显示时间  DATE_ALL = "yyyy-MM-dd HH:mm";
     * 和现在的时间比较，超过24小时是前天，24小时以内是昨天
     * 给出的时间和现在的时间相比   是几小时前  或前天  昨天 例如：2017-05-30 06:08  是 8小时前
     * @param sdate
     * @return
     */
    public static String friendly_time(String sdate) {
        Date time = dateFromString(sdate, DATE_ALL);  //将指定的字符串格式的日期转换成时间对象
        if (time == null) {
            return "Unknown";
        }
        String ftime = "";
        Calendar cal = Calendar.getInstance();

        // 判断是否是同一天
        String curDate = dateFormater2.get().format(cal.getTime());
        String paramDate = dateFormater2.get().format(time);

        if (curDate.equals(paramDate)) {//表明是同一天
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0)
                ftime = Math.max(  //两者取最大值
                        (cal.getTimeInMillis() - time.getTime()) / 60000, 1)
                        + "分钟前";
            else
                ftime = hour + "小时前";
            return ftime;
        }

        long lt = time.getTime() / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);
        if (days == 0) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0)
                ftime = Math.max(
                        (cal.getTimeInMillis() - time.getTime()) / 60000, 1)
                        + "分钟前";
            else
                ftime = hour + "小时前";
        } else if (days == 1) {
            ftime = "昨天";
        } else if (days == 2) {
            ftime = "前天";
        } else if (days > 2 && days <= 10) {
            ftime = days + "天前";
        } else if (days > 10) { //这个是大于10天的情形
            ftime = dateFormater2.get().format(time);
        }
        return ftime;
    }

    /**
     * 判断给定字符串时间是否为今日
     *
     * @param sdate
     * @return boolean
     */
    public static boolean isToday(String sdate) {
        boolean b = false;
        Date time = dateFromString(sdate, DATE_ALL);  //将指定的字符串格式的日期转换成时间对象
        Date today = new Date();
        if (time != null) {
            String nowDate = dateFormater2.get().format(today);
            String timeDate = dateFormater2.get().format(time);
            if (nowDate.equals(timeDate)) {
                b = true;
            }
        }
        return b;
    }

    //获取到今年
    public static int getThisYear() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }

    //获取到当月 这个月份需要加 1
    public static int getThisMonth() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.MONTH);
    }

    //获取到当日的信息
    public static int getThisDay() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.DAY_OF_MONTH);
    }


    //得到现在是周几
    public static int getThisWeek() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.DAY_OF_WEEK) - 1;
    }

    //判断现在的时间是今年的第几周  全年是52周
    // 测试的语句  MyLog.ShowLog(String.valueOf(DateUtil.getWeekOfYear(DateUtil.getNowTimeInMillis())));
    public static int getWeekOfYear(long times) {
        Calendar mCal = Calendar.getInstance();
        mCal.setFirstDayOfWeek(Calendar.MONDAY);
        mCal.setTimeInMillis(times);
        return mCal.get(Calendar.WEEK_OF_YEAR);
    }

    //获得本周日的时间的毫秒值   得到现在是周几减1才是东方的时间，国际上是星期天为一周第一天
    // dayOfMonth + (7 - (dayOfWeek - 1))  30+(7-(3-1)))=35  也就是当前天比周日少5天
    public static long getThisWeekOfSunday() {
        Calendar cal = Calendar.getInstance();
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        cal.set(year, month, dayOfMonth + (7 - (dayOfWeek - 1)), 23, 59, 59);
        return cal.getTimeInMillis();
    }

    // MyLog.ShowLog(DateUtil.longToString(DateUtil.getThisWeekOfMonday())); 测试的代码
    //获得本周一的时间戳
//    System.out.println(cal.get(Calendar.DAY_OF_MONTH)); //输出当前日期对象cal是当前月份的第几天
//    dayOfMonth - (dayOfWeek - 2)  30-(3-2)=29  也就是第29天使周一  当前天比周一多2天
    public static long getThisWeekOfMonday() {
        Calendar cal = Calendar.getInstance();
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        cal.set(year, month, dayOfMonth - (dayOfWeek - 2),0,0,0);
        return cal.getTimeInMillis();
    }

    //得到年份的值  android.text.format.Time包中的Time类是android平台的一个时间类，但是这个类目前已经不建议使用了
    public static int longTimeToYear(long times) {
        Time mTime = new Time();
        mTime.set(times);
        return mTime.year;
    }
    //得到月份的值
    public static int longTimeToMonth(long times) {
        Time mTime = new Time();
        mTime.set(times);
        return mTime.month;
    }

    //获得beforeOfMonth个月前那个月的第一天  0表示当月的第一天，例如 2017-07-01 00:00:00
    public static long getLongOfFirstDayOfMonth(int beforeOfMonth) {
        Calendar localCalendar = Calendar.getInstance();
        int latestMonth = localCalendar.get(Calendar.MONTH);
        localCalendar.set(localCalendar.MONTH, latestMonth - beforeOfMonth);
        //获取月中的最小值
        int latestDay = localCalendar.getActualMinimum(Calendar.DAY_OF_MONTH);

        localCalendar.set(localCalendar.DATE, latestDay);
        localCalendar.set(localCalendar.HOUR, 0);
        localCalendar.set(localCalendar.MINUTE, 0);
        localCalendar.set(localCalendar.SECOND, 0);
        localCalendar.set(localCalendar.MILLISECOND, 0);
        return localCalendar.getTimeInMillis();
    }

    //获得beforeOfMonth月前的最后一天  0为当月 2017-07-31 00:00:00
    public static long getLongOfLatestDayOfMonth(int beforeOfMonth) {

        Calendar localCalendar = Calendar.getInstance();
        int latestMonth = localCalendar.get(Calendar.MONTH);
        localCalendar.set(localCalendar.MONTH, latestMonth - beforeOfMonth);
        int latestDay = localCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        localCalendar.set(localCalendar.DATE, latestDay);
        localCalendar.set(localCalendar.HOUR, 0);
        localCalendar.set(localCalendar.MINUTE, 0);
        localCalendar.set(localCalendar.SECOND, 0);
        localCalendar.set(localCalendar.MILLISECOND, 0);
        return localCalendar.getTimeInMillis();
    }

    // 通过string 指定  hh：ss  时间      获取long型时间码
    public static long getLongOfTime(String time) {
        int hour = Integer.valueOf(time.substring(0, 2));
        int min = Integer.valueOf(time.substring(3, 5));
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.HOUR_OF_DAY, hour);//get 和 set 的字段数字，指示一天中的小时。
        calendar.set(calendar.MINUTE, min);// get 和 set 的字段数字，指示一小时中的分钟。
        calendar.set(Calendar.SECOND, 0);       //get 和 set 的字段数字，指示一分钟中的秒。
        calendar.set(Calendar.MILLISECOND, 0); //get 和 set 的字段数字，指示一秒中的毫秒。
        return calendar.getTimeInMillis();
    }

    // 通过string yyyy-mm-dd 时间获取时间码
    public static long getLongOfDayTime(String time) {
        int year = Integer.valueOf(time.substring(0, 4));
        int month = Integer.valueOf(time.substring(5, 7));
        int day = Integer.valueOf(time.substring(8, 10));
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.YEAR, year);
        calendar.set(calendar.MONTH, month - 1);
        calendar.set(calendar.DAY_OF_MONTH, day);

        return calendar.getTimeInMillis();
    }

    // 通过string yyyy-mm-dd, time    时间获取时间码
    public static long getLongOfDayTimeAll(String date, String time) {
        //依次获得年月日 时分秒 转换成时间的毫秒值
        int year = Integer.valueOf(date.substring(0, 4));
        int month = Integer.valueOf(date.substring(5, 7));
        int day = Integer.valueOf(date.substring(8, 10));

        int hour = Integer.valueOf(time.substring(0, 2));
        int min = Integer.valueOf(time.substring(3, 5));

        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.YEAR, year);
        calendar.set(calendar.MONTH, month - 1);
        calendar.set(calendar.DAY_OF_MONTH, day);
        calendar.set(calendar.HOUR_OF_DAY, hour);
        calendar.set(calendar.MINUTE, min);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    //将字符串时间转化为星期几   2017:07:03
    public static int getTimeForWeek(String time){
        int year = Integer.valueOf(time.substring(0, 4));
        int month = Integer.valueOf(time.substring(5, 7));
        int day = Integer.valueOf(time.substring(8, 10));
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.YEAR, year);
        calendar.set(calendar.MONTH, month-1);
        calendar.set(calendar.DAY_OF_MONTH, day);
        return calendar.get(Calendar.DAY_OF_WEEK)-1;
    }

    //将毫秒值转化成 字符串时间  1535960193973  2018-09-03 15:36:33
    public static String longToString(long time){
        Calendar nowCalendar = Calendar.getInstance();
        nowCalendar.setTimeInMillis(time);
        //Calendar和Date的相互转化
        Date d = nowCalendar.getTime();
        SimpleDateFormat sDateFormat = new SimpleDateFormat(DateUtil.DATE_ALL_ALL);
        return sDateFormat.format(d);
    }

    //将毫秒值转化成 字符串时间
    public static String longToString(long time,String format){
        Calendar nowCalendar = Calendar.getInstance();
        nowCalendar.setTimeInMillis(time);
        //Calendar和Date的相互转化
        Date d = nowCalendar.getTime();
        SimpleDateFormat sDateFormat = new SimpleDateFormat(format);
        return sDateFormat.format(d);
    }

    // DateUtil.TimeStamp2Date(addTime, "yyyy-MM-dd HH:mm:ss ")
    // 将unix时间戳转化成标准时间
    public static String TimeStamp2Date(String timestampString, String formats){
        Long timestamp = Long.parseLong(timestampString)*1000+28800000;
        String date = new SimpleDateFormat(formats).format(new Date(timestamp));
        return date;
    }
}