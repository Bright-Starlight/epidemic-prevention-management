package com.parachute.main.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
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
     * 获取过去一年
     *
     */
    public  static List<String> getYear(){
        List<String> date = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for(long i = 1L;i <= 12L; i++){
            LocalDate localDate = today.minusMonths(i);
            String ss = localDate.toString().substring(0,7);
            date.add(ss);
        }
        return date;
    }

    /**
     * 得到近月日期
     *
     * @return {@link List}<{@link String}>
     */
    public static List<String> getNearlyMonthDates() {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            //过去一月
            c.setTime(new Date());
            String today = format.format(new Date());
            c.add(Calendar.MONTH, -1);
            Date m = c.getTime();
            String mon = format.format(m);
            List<String> result = getBetweenDates(mon,today,false);

            return result;
        }


    /**
     * 获取日期之间
     * 补全给定起止时间区间内的所有日期
     *
     * @param startTime          开始时间
     * @param endTime            结束时间
     * @param isIncludeStartTime 是包括开始时间
     * @return {@link List}<{@link String}>
     */
    public static List<String> getBetweenDates(String startTime, String endTime,boolean isIncludeStartTime){
        List<String> result = new ArrayList<>();
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            //定义起始日期
            Date d1 = new SimpleDateFormat("yyyy-MM-dd").parse(startTime);
            //定义结束日期  可以去当前月也可以手动写日期。
            Date d2 = new SimpleDateFormat("yyyy-MM-dd").parse(endTime);
            //定义日期实例
            Calendar dd = Calendar.getInstance();
            //设置日期起始时间
            dd.setTime(d1);
            if(isIncludeStartTime) {
                result.add(format.format(d1));
            }
            //判断是否到结束日期
            while (dd.getTime().before(d2)) {
                //进行当前日期加1
                dd.add(Calendar.DATE, 1);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String str = sdf.format(dd.getTime());
                result.add(str);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


}
