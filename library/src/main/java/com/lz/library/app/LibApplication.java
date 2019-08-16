package com.lz.library.app;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;

import androidx.multidex.MultiDex;

import com.lz.library.config.AppConfig;


/**
 * <pre>
 *     desc  : 可以做一些公共处理逻辑
 *     revise:
 * </pre>
 */
public class LibApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppConfig.INSTANCE.initConfig(this);
        //在子线程中初始化
        InitializeService.start(this);

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    /**
     * 程序终止的时候执行
     */
    @Override
    public void onTerminate() {
        Log.d("Application", "onTerminate");
        super.onTerminate();
    }


    /**
     * 低内存的时候执行
     */
    @Override
    public void onLowMemory() {
        Log.d("Application", "onLowMemory");
        super.onLowMemory();
    }


    /**
     * onConfigurationChanged
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d("Application", "onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
    }


}
