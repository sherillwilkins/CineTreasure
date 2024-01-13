package com.w83ll43.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {
    /**
     * 默认日期格式
     */
    public static String DEFAULT_FORMAT = "yyyy-MM-dd";

    /**
     * 获取上一年年份
     * @return
     */
    public static Integer getLastYear() {
        SimpleDateFormat formats = new SimpleDateFormat("yyyy");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -1);
        Date date = c.getTime();
        // Date类型转String类型
        String format = formats.format(date);
        // String类型转int类型
        int parseInt = Integer.parseInt(format);
        return parseInt;
    }

    /**
     * 格式化日期
     * @param date 日期对象
     * @return String 日期字符串
     */
    public static String formatDate(Date date) {
        SimpleDateFormat f = new SimpleDateFormat(DEFAULT_FORMAT);
        return f.format(date);
    }

    /**
     * 获取当年的第一天
     * @param
     * @return
     */
    public static Date getCurrYearFirst() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }

    /**
     * 获取当年的最后一天
     * @param
     * @return
     */
    public static Date getCurrYearLast() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }

    /**
     * 获取某年第一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currYearFirst);
        return currYearFirst;
    }

    /**
     * 获取某年最后一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearLast(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        return calendar.getTime();
    }

    /**
     * 获取当天的开始时间和结束时间
     */
    public static void main(String[] args) {
        // 获取当天开始时间-00:00:00
        LocalDateTime todayStart = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        String todayStartStr = todayStart.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        // 获取当天结束时间-23:59:59
        LocalDateTime todayEnd = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        String todayEndStr = todayEnd.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

}