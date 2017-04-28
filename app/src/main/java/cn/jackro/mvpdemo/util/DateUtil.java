package cn.jackro.mvpdemo.util;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期时间工具类
 */
public class DateUtil {
    /**
     * 格式化日期
     *
     * @param strDate 符合格式的字符串
     * @return 格式后的日期（yyyy-MM-dd HH:mm:ss）
     */
    public static Date parseStr(String strDate) throws ParseException {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(strDate);
    }
}
