package zc.commons.util;

import java.text.DateFormat;
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
     * 获取当前时间，格式为yyyy-MM-dd
     * @return
     */
    public static String currentDate(){
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

}
