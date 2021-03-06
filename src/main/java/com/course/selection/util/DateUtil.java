package com.course.selection.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DateUtil {
    public final static String FORMAT_PATTERN1="yyyy-MM-dd HH:mm:ss";
    public final static String FORMAT_PATTERN2="yyyyMMddHHmmss";
    public final static String FORMAT_PATTERN3="yyyy-MM-dd";
    public final static String FORMAT_PATTERN4="yyyyMMdd";

    /**
     * 将localDate 按照一定的格式转换成String
     * @param localDate
     * @param pattern
     * @return
     */
    public static String localDateFormat(LocalDate localDate, String pattern){
        return localDate.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 将localDateTime 按照一定的格式转换成String
     * @param localDateTime
     * @param pattern
     * @return
     */
    public static String localDateTimeFormat(LocalDateTime localDateTime, String pattern){
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 将String 按照pattern 转换成LocalDate
     * @param time
     * @param pattern
     * @return
     */
    public static LocalDate localDateParse(String time,String pattern){
        return LocalDate.parse(time,DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 将String 按照pattern 转换成LocalDateTime
     * @param time
     * @param pattern
     * @return
     */
    public static LocalDateTime localDateTimeParse(String time,String pattern){
        return LocalDateTime.parse(time,DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 将date转换成String
     * @param date
     * @param pattern
     * @return
     */
    public static String dateFormat(Date date,String pattern){
        return localDateTimeFormat(dateToLocalDateTime(date),pattern);
    }

    /**
     * 将LocalDate 转换成 Date
     * @param localDate
     * @return
     */
    public static Date localDateToDate(LocalDate localDate){
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 将LocalDateTime 转换成 Date
     * @param localDateTime
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime){
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 将 Date 转换成LocalDate
     * atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
     * @param date
     * @return
     */
    public static LocalDate dateToLocalDate(Date date){
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDate();
    }

    /**
     * 将 Date 转换成LocalDateTime
     * atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date){
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    /**
     * 计算两个LocalDateTime 之间的毫秒数
     * @param time1
     * @param time2
     * @return
     */
    public static Long minusToMillsLocalDateTime(LocalDateTime time1,LocalDateTime time2){
        return Duration.between(time1, time2).toMillis();
    }

    /**
     * 计算两个LocalTime 之间的毫秒数
     * @param time1
     * @param time2
     * @return
     */
    public static Long minusToMillsLocalTime(LocalTime time1,LocalTime time2){
        return Duration.between(time1, time2).toMillis();
    }

    /**
     * 计算两个LocalDate 之间的毫秒数
     * @param time1
     * @param time2
     * @return
     */
    public static Long minusToMillsLocalDate(LocalDate time1,LocalDate time2){
        return Duration.between(time1, time2).toMillis();
    }
    /**
     * 计算两个LocalDate 之间的Period
     * @param time1
     * @param time2
     * @return
     */
    public static Period periodLocalDate(LocalDate time1,LocalDate time2){
        return Period.between(time1,time2);
    }
    /**
     * 计算两个Date 之间的Period
     * @param date1
     * @param date2
     * @return
     */
    public static Period periodDate(Date date1, Date date2){
        return periodLocalDate(dateToLocalDate(date1),dateToLocalDate(date2));
    }
    /**
     * 计算两个Date之间的 Period
     * @param time1
     * @param time2
     * @return
     */
    public static Long minusToMillsDate(Date time1, Date time2){
        return minusToMillsLocalDateTime(dateToLocalDateTime(time1),dateToLocalDateTime(time2));
    }


    /**
     * 返回时间的毫秒值
     * @param localDateTime
     * @return
     */
    public static Long getTimestamp(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 根据毫秒值获取时间
     * @param timestamp
     * @return
     */
    public static LocalDateTime getLocalDataTime(Long timestamp) {
        return LocalDateTime.ofEpochSecond(timestamp / 1000, 0, ZoneOffset.ofHours(8));
    }

    /**
     * 两个日期相差几天
     * @param newData
     * @param oldData
     * @return
     */
    public static long getBetwwenDays(LocalDate newData, LocalDate oldData) {
        return oldData.until(newData, ChronoUnit.DAYS);
    }
    public static Long getTimestamp(){
        return new Date().getTime();
    }
    public static int getTimestamp_10(){
        return (int) (new Date().getTime()/1000);
    }

    public static void main(String[] args) {
//        System.out.println(LocalDateTime.of(2019, 2, 19, 14, 0, 0));
//        System.out.println(minusToMillsLocalDateTime(LocalDateTime.parse("2019-03-14 10:18:36",
//                DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")),LocalDateTime.now()));

//        LocalDateTime local1 = LocalDateTime.parse("2019-03-14 14:54:36",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        long l1 = local1.toInstant(ZoneOffset.of("+8")).toEpochMilli();
//        long l2 = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
//        System.out.println(l1+"-"+l2+"-"+Math.abs(l1-l2));
//        if(Math.abs(l1-l2)<60*1000) {
//            System.out.println(l1+"-"+l2+"-=="+Math.abs(l1-l2));
//        }
        System.out.println( getBetwwenDays(LocalDate.now(),LocalDate.now()));
    }
}
