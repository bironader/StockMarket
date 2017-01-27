package com.example.biro.stockmarket;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by biro on 12/1/2016.
 */

public class MyApp extends Application {

    private static  MyApp Instance;

    @Override
    public void onCreate() {
        Instance = this;
    }

    public static MyApp getInstance() {
        return Instance;
    }

    public static Context getAppContext()
    {
        return Instance.getApplicationContext();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
