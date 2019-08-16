package com.lz.app;

import android.util.Log;

import com.lz.library.app.LibApplication;


/**
 * <pre>
 *     desc         Application
 * </pre>
 */
public class BaseApplication extends LibApplication {

    /**
     * 程序启动的时候执行
     */
    @Override
    public void onCreate() {
        Log.d("Application", "onCreate");
        super.onCreate();
    }


}


