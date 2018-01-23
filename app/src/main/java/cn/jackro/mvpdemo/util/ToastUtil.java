package cn.jackro.mvpdemo.util;

import android.content.Context;
import android.widget.Toast;

import cn.jackro.mvpdemo.MyApp;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;

/**
 * Toast Util
 */
public class ToastUtil {

    public static void showShort(String msg) {
        show(MyApp.getMyAppApplicationContext(), msg, LENGTH_SHORT);
    }

    @SuppressWarnings("unused")
    public static void showLong(String msg) {
        show(MyApp.getMyAppApplicationContext(), msg, LENGTH_LONG);
    }

    private static void show(Context context, String msg, int duration) {
        if (context == null || msg == null) {
            return;
        }
        Toast.makeText(context, msg, duration).show();
    }
}
