package cn.jackro.mvpdemo;

import android.app.Application;

/**
 * Application, used to init resource
 * Created by jack on 2017/3/29.
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
