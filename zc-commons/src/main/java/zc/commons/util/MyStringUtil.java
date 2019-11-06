package zc.commons.util;

public class MyStringUtil {
    public static boolean isEmpty(String str){
        if(str.length() == 0 || str.trim().length() == 0)
            return true;
        return false;
    }
}
