package cn.jackro.mvpdemo;

import android.app.Application;

/**
 * Application, 用于初始化资源等
 */

public class MyApp extends Application {

    private static MyApp myAppApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        myAppApplicationContext = this;
    }

    public static MyApp getMyAppApplicationContext() {
        return myAppApplicationContext;
    }
}
