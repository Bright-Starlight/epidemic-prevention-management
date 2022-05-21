package com.parachute.mian.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 日期跑龙套
 *
 * @author machi
 * @date 2022/04/25
 */
public class DateUtils {
    private DateUtils(){}

    public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE = "yyyy-MM-dd";

    /**
     * string2相等日期
     * string转日期
     *
     * @param s 年代
     * @return {@link Date}
     * @throws ParseException 解析异常
     */
    public static Date string2Date(String s, String pattern) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.parse(s);
    }

    /**
     * date2字符串
     * date转字符串
     *
     * @param date 日期
     * @return {@link String}
     *
     */
    public static String date2String(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME);
        return simpleDateFormat.format(date);
    }
    /**
     * 获取当月所有天
     *
     */
    public  static List<String> getData(){
        List<String> date = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for(long i = 1L;i <= 12L; i++){
            LocalDate localDate = today.minusMonths(i);
            String ss = localDate.toString().substring(0,7);
            date.add(ss);
        }
        return date;
    }

}
