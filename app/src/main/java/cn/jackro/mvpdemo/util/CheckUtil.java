package cn.jackro.mvpdemo.util;

import android.text.TextUtils;

import java.util.List;

/**
 * CheckUtil
 */
@SuppressWarnings("unused")
public final class CheckUtil {

    public static boolean isListNotNull(List list) {
        return list != null && list.size() > 0;
    }

    public static boolean isEmpty(String str) {
        return TextUtils.isEmpty(str);
    }
}
