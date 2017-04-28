package cn.jackro.mvpdemo.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import cn.jackro.mvpdemo.MyApp;

/**
 * SharedPreferences Util, put value and get value
 */
public class SharedPreferencesUtil {

    private SharedPreferences mSharedPreferences;

    private SharedPreferencesUtil() {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(MyApp
                .getMyAppApplicationContext());
    }

    private static class Singleton {
        private static final SharedPreferencesUtil INSTANCE = new SharedPreferencesUtil();
    }

    public static SharedPreferencesUtil getInstance() {
        return Singleton.INSTANCE;
    }

    /**
     * put t to SharedPreferences xml
     *
     * @param key SharedPreferences key
     * @param t   SharedPreferences value, type is T
     * @param <T> Type T
     */
    public <T> void put(String key, T t) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        if (t instanceof String) {
            editor.putString(key, (String) t);
        } else if (t instanceof Integer) {
            editor.putInt(key, (Integer) t);
        } else if (t instanceof Float) {
            editor.putFloat(key, (Float) t);
        } else if (t instanceof Boolean) {
            editor.putBoolean(key, (Boolean) t);
        } else if (t instanceof Long) {
            editor.putLong(key, (Long) t);
        }
        editor.apply();
    }

    /**
     * obtained T from SharedPreferences xml
     *
     * @param key SharedPreferences key
     * @param t   SharedPreferences default value
     * @param <T> Type T
     * @return return obtained T
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String key, T t) {
        if (t instanceof String) {
            String str = mSharedPreferences.getString(key, (String) t);
            t = (T) str;
        } else if (t instanceof Integer) {
            Integer i = mSharedPreferences.getInt(key, (Integer) t);
            t = (T) i;
        } else if (t instanceof Float) {
            Float f = mSharedPreferences.getFloat(key, (Float) t);
            t = (T) f;
        } else if (t instanceof Boolean) {
            Boolean b = mSharedPreferences.getBoolean(key, (Boolean) t);
            t = (T) b;
        } else if (t instanceof Long) {
            Long l = mSharedPreferences.getLong(key, (Long) t);
            t = (T) l;
        }
        return t;
    }
}
