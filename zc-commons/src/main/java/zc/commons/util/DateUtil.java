package zc.commons.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    /**
     * 获取当前时间，格式为yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String currentTime(){
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        return df.format(date);
    }
    /**
     * 获取当前日期，格式为yyyy-MM-dd
     * @return
     */
    public static String currentDate(){
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    /**
     * 字符串转为时间，格式为yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date parseTime(String time){
        Date date = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        try {
            date = df.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    /**
     * 字符串转为日期，，格式为yyyy-MM-dd
     * @return
     */
    public static Date parseDate(String datestr){
        Date date = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date =  df.parse(datestr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 计算差几天
     * @param dateStr
     * @return
     */
    public static int calculate(String dateStr){
        Date date = DateUtil.parseDate(dateStr);
        Date curDate = DateUtil.parseDate(DateUtil.currentDate());
        long sub = Math.abs(curDate.getTime() - date.getTime()); // 剩余毫秒数
        return (int)(sub/1000/60/60/24);

    }

}
