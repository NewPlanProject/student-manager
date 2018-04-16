package org.heran.edu.student.util.dispose;

import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 日期相关计算辅助类<br/>
 */
@Log
public class DateUtil {

    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String FORMAT_STR = "yyyy-MM-dd HH:mm:ss";

    /**
     * 计算年龄
     *
     * @param birthDay
     * @return
     * @throws Exception
     */
    public static Integer getAge(Date birthDay) {
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) {
            throw new IllegalArgumentException("出生日期不能在当前日期之后!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth)
                    age--;
            } else {
                age--;
            }
        }

        return age;
    }

    /**
     * 处理开始时间 当天时间的0时0分0秒
     *
     * @return
     */
    public static Date processStartTime(Date start) {
        Calendar c = Calendar.getInstance();
        c.setTime(start);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 处理结束时间 当天时间的23时59分59秒
     *
     * @param endTime
     * @return
     */
    public static Date processEndTime(Date endTime) {
        Calendar c = Calendar.getInstance();
        c.setTime(endTime);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }

    /**
     * 解析日期
     * 格式"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd HH:mm","yyyy-MM-dd HH","yyyy-MM-dd"
     *
     * @param dateStr
     * @return Date
     */
    public static Date parseDate(String dateStr) {
        String[] dateFormatStr = new String[]{"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH", "yyyy-MM-dd"};
        if (!StringUtils.isEmpty(dateStr)) {
            dateStr = dateStr.trim();
            for (int i = 0; i < dateFormatStr.length; i++) {
                if (dateStr.length() >= dateFormatStr[i].length()) {
                    return parseDateByFormat(dateStr, dateFormatStr[i]);
                }
            }
        }
        return null;
    }

    public static Date parseDateByFormat(String dateStr, String dateFormatStr) {
        try {
            return DateUtils.parseDate(dateStr, dateFormatStr);
        } catch (Exception e) {
            log.warning(e.getMessage());
            return null;
        }
    }

    public static String formatDate(Date date, String format) {
        return new SimpleDateFormat(format == null ? "yyyy-MM-dd" : format).format(date);
    }

    /**
     * 通过睡眠时间获取所属日睡眠记录的执行日期
     *
     * @param date
     * @return Date
     */
    public static Date getSleepDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if (c.get(Calendar.HOUR_OF_DAY) < 6) {
            return processStartTime(DateUtils.addDays(date, -1));
        } else {
            return processStartTime(date);
        }
    }

    /**
     * 获取冒号分割的时间字符串
     *
     * @param duration 时长（单位：秒）
     * @param format   时间格式
     * @return String 格式化后的时间字符串
     */
    public static String getTimeStrWithColon(Integer duration, String format) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.add(Calendar.SECOND, duration);

        if (GeneralHelper.isTrimStrEmpty(format)) {
            if (c.get(Calendar.HOUR_OF_DAY) > 0) {
                format = "HH:mm:ss";
            } else if (c.get(Calendar.MINUTE) > 0) {
                format = "mm:ss";
            } else {
                format = "ss";
            }
        }
        return GeneralHelper.date2Str(c.getTime(), format);
    }

    /**
     * 获取汉字分割的时间字符串
     *
     * @param duration 时长（单位：秒）
     * @param format   时间格式
     * @return String 格式化后的时间字符串
     */
    public static String getTimeStrWithChinese(Integer duration, String format) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.add(Calendar.SECOND, duration);

        if (GeneralHelper.isTrimStrEmpty(format)) {
            if (c.get(Calendar.HOUR_OF_DAY) > 0) {
                format = "HH小时mm分ss秒";
            } else if (c.get(Calendar.MINUTE) > 0) {
                format = "mm分ss秒";
            } else {
                format = "ss秒";
            }
        }
        return GeneralHelper.date2Str(c.getTime(), format);
    }

    /**
     * 格式化输出日期
     *
     * @param date
     * @return Date 返回类型
     */
    public static Date getForMatDate(Date date) {
        return getForMatDate(date, FORMAT_STR);
    }

    /**
     * 格式化输出日期
     *
     * @param date
     * @return Date 返回类型
     */
    public static Date getForMatDate(Date date, String pattern) {
        if (StringUtils.isEmpty(pattern)) {
            pattern = FORMAT_STR;
        }
        SimpleDateFormat fmat = new SimpleDateFormat(pattern);
        try {
            return fmat.parse(fmat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static String getDateToString(Date date, String pattern) {
        try {
            if (date == null) {
                return "";
            }
            if (StringUtils.isEmpty(pattern)) {
                pattern = FORMAT_STR;
            }
            SimpleDateFormat fmat = new SimpleDateFormat(pattern);
            return fmat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            log.warning(e.getMessage());
        }
        return "";
    }

    /**
     * 给定事前是否在服务器规定时间范围内
     *
     * @param preTo
     * @return boolean 返回类型
     */
    public static boolean isValid(Date preTo) {
        if (preTo == null) {
            return false;
        }
        Date now = new Date();
        long first = now.getTime() - 24 * 60 * 60 * 1000;
        long second = preTo.getTime();
        long third = now.getTime() + 20 * 60 * 1000;
        return second >= first && second <= third;
    }

    /**
     * @param endTime
     * @return boolean 返回类型
     * @Description: 比较睡眠日期是否合法
     */
    public static boolean isValidSleep(Date endTime) {
        if (endTime == null) {
            return false;
        }
        Date now = new Date();
        long first = now.getTime() - 48 * 60 * 60 * 1000;
        long second = endTime.getTime();
        long third = now.getTime() + 24 * 60 * 60 * 1000;
        return second >= first && second <= third;
    }


    public static Date getAddDate(Date now, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.DATE, day);
        return cal.getTime();
    }

    /**
     * 获取两个时间的差值，pattern 为 y代表年，M代表月
     */
    public static String getDatediff(Date d1, Date d2, char pattern) {
        long timeDeff = d1.getTime() - d2.getTime();
        if ('y' == pattern)
            timeDeff = timeDeff / (12 * 30 * 24 * 60 * 60 * 1000);
        if ('M' == pattern)
            timeDeff = timeDeff / (30 * 24 * 60 * 60 * 1000L);
        if ('d' == pattern)
            timeDeff = timeDeff / (24 * 60 * 60 * 1000);
        if ('H' == pattern)
            timeDeff = timeDeff / (60 * 60 * 1000);
        if ('m' == pattern)
            timeDeff = timeDeff / (60 * 1000);
        if ('s' == pattern)
            timeDeff = timeDeff / (1000);
        return timeDeff + "";
    }

    /**
     * 获取日期前一个月
     *
     * @param date
     * @return
     */
    public static Date getRecentBeforeMonth(Date date) {
        Date temp = (date.getDay() == 1) ? date : DateUtils.addMonths(date, -1);
        return temp;
    }

    /**
     * 获取日期后一个月
     *
     * @param date
     * @return
     */
    public static Date getRecentAfterMonth(Date date) {
        Date temp = (DateUtils.addDays(date, 1).getDay() == 1) ? date : DateUtils.addMonths(date, 1);
        return temp;
    }

    public static int getDays(Date d1, Date d2) {
        return (int) (Math.abs(d1.getTime() - d2.getTime()) / (24 * 60 * 60 * 1000));
    }

    public static Date getFirstDate(Date d1, int unit) {
        Date date = DateUtils.addMonths(d1, 1);
        try {
            date = DateUtils.parseDate(formatDate(date, "yyyy-MM"), "yyyy-MM");
            if (getDays(d1, date) > (30 * unit / 2)) {
                return date;
            } else {
                return DateUtils.addMonths(date, unit);
            }

        } catch (Exception e) {
            log.warning(e.getMessage());
        }
        return d1;
    }

    /**
     * 获得当前日期对应的上海所在时区的日期
     *
     * @return 日期字符串
     */
    public static String getNowDateShanghai() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
        sdf.setTimeZone(tz);
        String dateStr = sdf.format(new Date());
        return dateStr;
    }

    /**
     * 获得当前时间对应的上海所在时区的时间
     *
     * @return 日期字符串
     */
    public static String getNowDateTimeShanghai() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
        sdf.setTimeZone(tz);
        String dateStr = sdf.format(new Date());
        return dateStr;
    }

    /**
     * 获得指定日期的上海所在时区日期
     *
     * @param date
     * @return
     */
    public static String getDateShanghai(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
        sdf.setTimeZone(tz);
        String dateStr = sdf.format(date);
        return dateStr;
    }

    /**
     * 获得指定时间的上海所在时区时间
     *
     * @param date
     * @return
     */
    public static String getDateTimeShanghai(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
        sdf.setTimeZone(tz);
        String dateStr = sdf.format(date);
        return dateStr;
    }


}
