package com.gomeos.mvvm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by songzhiyang on 2016/6/6.
 */
public class TimeUtils {
    public static String getTodayTimeStr(long time) {
        SimpleDateFormat format=new SimpleDateFormat("HH:mm");
        Date d1=new Date(time);
        String t1=format.format(d1);
        return t1;
    }

    public static String getOtherTimeStr(long time) {
        SimpleDateFormat format=new SimpleDateFormat("MM-dd");
        Date d1=new Date(time);
        String t1=format.format(d1);
        return t1;
    }


    /**
     * 是否是今天
     *
     * @param date
     * @return
     */
    public static boolean isToday(final Date date) {
        return isTheDay(date, Calendar.getInstance().getTime());
    }
    /**
     * 是否是指定日期
     *
     * @param date
     * @param day
     * @return
     */
    public static boolean isTheDay(final Date date, final Date day) {
        return date.getTime() >= dayBegin(day).getTime()
                && date.getTime() <= dayEnd(day).getTime();
    }
    /**
     * 获取指定时间的那天 00:00:00.000 的时间
     *
     * @param date
     * @return
     */
    public static Date dayBegin(final Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }
    /**
     * 获取指定时间的那天 23:59:59.999 的时间
     *
     * @param date
     * @return
     */
    public static Date dayEnd(final Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTime();
    }

    /**
     * 爱生活显示时间规范：
     * 1、对于刚刚发表显示刚刚
     * 2、小于60分的显示几分钟前
     * 3、小于24小时的 显示几小时前
     * 4、昨天的显示昨天几点几分
     * 5、前天的显示前天几点几分
     * 6、之后的显示几月几号
     * 7、超过一年的显示几年几月几号
     *
     * @param date
     * @return String
     */
    public static String getDateYMD(long date) {

        String dateDesc = "";
//		SimpleDateFormat ss = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//12小时制
//		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//24小时制

        // 用于判断是今天昨天还是更早
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 用于显示小时和分钟
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat minFormat = new SimpleDateFormat("HH:mm");
        try {

            Date activeTime = new Date(date);
            String nowDateStr = sdf.format(new Date());
            Date nowTime = sdf.parse(nowDateStr);
            long now = nowTime.getTime();
            long active = activeTime.getTime();
            long diff = (now - active) / (24 * 60 * 60 * 1000);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date(date));
            if (diff <= 0) {
                dateDesc = "";
                Date activeDate = new Date(date);
                Date nowDate = format.parse(format.format(new Date()));
                long d = (nowDate.getTime() - activeDate.getTime()) / (60 * 1000);
                if (d <= 1) {//1分钟内
                    dateDesc = "刚刚";
                } else if (d <= 59) {
                    dateDesc = d + "分钟前";
                } else if (d >= 60 && d <= 1440) {
                    dateDesc = ((int) (d / 60)) + "小时前";
                } else {
                    long min = d * 1000;
                    String minStr = minFormat.format(new Date(date));
                    dateDesc = "昨天" + minStr;
                }
            }
            if (diff == 1) {

                String minStr = minFormat.format(new Date(date));
                dateDesc = "前天" + minStr;
            }
            if (diff > 1) {
                Calendar calendarNow = Calendar.getInstance();
                calendarNow.setTime(new Date());
                int year = calendar.get(Calendar.YEAR);
                int yearNow = calendarNow.get(Calendar.YEAR);
                if (year == yearNow) {
                    dateDesc = (calendar.get(Calendar.MONTH) + 1) + "月" + calendar.get(Calendar.DAY_OF_MONTH) + "日";
                } else {
                    dateDesc = year + "年" + (calendar.get(Calendar.MONTH) + 1) + "月" + calendar.get(Calendar.DAY_OF_MONTH) + "日";
                }
            }
            return dateDesc;
        } catch (ParseException e) {
            return dateDesc;
        }
    }


}
